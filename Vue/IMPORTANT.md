# 一、后端相关

## 1. stream封装多级菜单

相关代码，参考项目中 `MenuServiceImpl`。

使用 stream流，用来封装菜单，下面是实体类 `Menu`：

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Menu对象", description = "菜单管理")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("parentId")
    @ApiModelProperty(value = "父级菜单的id")
    private Integer parentId;

    @TableField(exist = false)
    @ApiModelProperty(value = "当前菜单的下一级菜单")
    private List<Menu> children;
}
```



递归计算，封装成多级菜单：

```java
/**
* 封装菜单的多级分类
*/
public List<Menu> levelMenu() {
    // 查出所有menu
    List<Menu> menus = menuMapper.queryAllMenus();

    // 先查出所有一级菜单
    List<Menu> levelMenu = menus.stream().filter(menu -> menu.getParentId() != null && menu.getParentId() == 1)

        // 再递归放置二级菜单
        .map(menu -> {
            menu.setChildren(getChildren(menu, menus));
            return menu;
        })
        .collect(Collectors.toList());
    return levelMenu;
}

public List<Menu> getChildren(Menu root, List<Menu> menus) {
    return menus.stream().filter(menu -> {
        Integer id = menu.getParentId() == null ? 0 : menu.getParentId();
        return id.equals(root.getId());
    }).map(menu -> {
        menu.setChildren(getChildren(menu, menus));
        return menu;
    }).collect(Collectors.toList());
}
```



```java
// distinct() 去重
集合.stream().distinct().collect(Collectors.toList());

// 将返回值为 ture 的过滤出来
levelMenus.stream().filter(menu -> ids.contains(menu.getId())).collect(Collectors.toList());
```



## 2. 后端接口权限设计

**(1) 根据请求的 URI，分析出该请求需要哪些角色才能访问**

```java
/**
 * 根据请求 uri，分析出请求需要哪些角色才能访问
 */
@Slf4j
@Component
public class ParseUrl2UserRoleFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private MenuServiceImpl menuServiceImpl;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        // 拿到请求的 URI
        String uri = ((FilterInvocation) object).getHttpRequest().getRequestURI();

        // 获得所有菜单(封装了访问该菜单需要的角色)
        List<Menu> menus = menuServiceImpl.getAllMenusWithRoles();

        // 1、访问路径匹配成功 ==> 获取访问该路径需要的角色
        for (Menu menu : menus) {
            // 如果路径匹配成功,返回该路径(菜单)需要的角色
            if (antPathMatcher.match(menu.getUrl(), uri)) {
                List<Role> roles = menu.getRoles();
                String[] names = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    names[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(names);
            }
        }

        // 2、访问路径匹配失败 ==> 登录后即可访问
        // ROLE_login 只是一个需要登录的标记 "SecurityConfigConstant.NEED_LOGIN_ROLE_MARK" = "ROLE_login"
        return SecurityConfig.createList(SecurityConfigConstant.NEED_LOGIN_ROLE_MARK);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
```



**(2) 判断当前用户是否拥有上述角色**

```java
/**
 * 判断当前用户是否有该 uri 需要的角色
 */
@Component
public class CurrentUserHasNeedRoleAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        
        // 遍历就用ForEach 就可以了，不要用 lambda 表达式
        for (ConfigAttribute configAttribute : configAttributes) {
            String needRole = configAttribute.getAttribute();

            System.out.println("needRole -->" + needRole);

            // 1、如果 needRole = "ROLE_login", 代表需要先登录才可以
            if (StringUtils.equals(needRole, SecurityConfigConstant.NEED_LOGIN_ROLE_MARK)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    System.out.println(authentication instanceof AnonymousAuthenticationToken);
                    // authentication 是"匿名登录" ==> 需要登录
                    throw new AccessDeniedException("尚未登录,请登录！");
                } else {
                    // 已经登录, 方法结束
                    return;
                }
            }

            // 2、这里证明已经登录了

            // 拿到用户拥有的所有角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            // 只要 用户的角色 和 需要的角色有交集即可
            for (GrantedAuthority authority : authorities) {
                System.out.println("authority -->" + authority.getAuthority());
                if (StringUtils.equals(authority.getAuthority(), needRole)) {
                    return;
                }
            }
        }

        // 3、用户角色不具备 uri 需要的角色
        throw new AccessDeniedException("用户权限不足, 请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
```



**(3) SpringSecurity 设置，使之生效**

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(SecurityConfigConstant.LOGIN_PAGE)
        .permitAll()
        .anyRequest()
        .authenticated()
        
        // 配置使之生效
        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setAccessDecisionManager(accessDecisionManager);
                object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                return object;
            }
        })

        .and()
        .formLogin()
        // 表单登录使用该接口
        .loginProcessingUrl(SecurityConfigConstant.LOGIN_PROCESSING_URL)

        // 如果未登录则重定向 ==> /login 接口；spring-security 默认 /login 是 html 页面
        // 这里如果返回 json 需要我们在 Controller 中自定义该接口
        .loginPage(SecurityConfigConstant.LOGIN_PAGE)
        .successHandler(loginSuccessHandler)
        .failureHandler(loginFailureHandler)
        .permitAll()

        .and()
        .logout()
        .logoutUrl(SecurityConfigConstant.LOGOUT_URL)
        .logoutSuccessHandler(logoutAppSuccessHandler)

        .and()
        .csrf().disable();
}
```

## 3. 请求失败处理（未登录不重定向）

**注意事项**：

1、之前配置的 `loginPage("/login")`会被拦截，需要手动放行。

```java
// 在 SecurityConfig 中配置即可
@Override
public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/login");
}
```



2、如果不希望使用第一种方式，直接给 spring-security 配置一个 `RequestFailureHandler` 即可。

详情去看spring-boot2笔记  spring-security 1.17，有详细配置。

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .anyRequest()
        .authenticated()

        .and()
        .formLogin()
        // 表单登录使用该接口
        .loginProcessingUrl(SecurityConfigConstant.LOGIN_PROCESSING_URL)

        // 如果未登录则重定向 ==> /login 接口；spring-security 默认 /login 是 html 页面
        // 这里如果返回 json 需要我们在 Controller 中自定义该接口
        //                .loginPage(SecurityConfigConstant.LOGIN_PAGE)
        .successHandler(loginSuccessHandler)
        .failureHandler(loginFailureHandler)
        .permitAll()

        .and()
        .logout()
        .logoutUrl(SecurityConfigConstant.LOGOUT_URL)
        .logoutSuccessHandler(logoutAppSuccessHandler)

        // 请求失败时处理器 ==> 默认重定向到 "/login" ==> 现在不让重定向了, 直接返回提示
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(requestFailureHandler)
}
```



3、`AuthenticationEntryPoint`

```java
@Component
public class RequestFailureHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ResponseBean responseBean;
        if (authException instanceof InsufficientAuthenticationException) {
            responseBean = ResponseBean.failure("尚未登录, 请登录!");
        } else {
            responseBean = ResponseBean.failure("访问失败!");
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(new ObjectMapper().writeValueAsString(responseBean));
        printWriter.flush();
        printWriter.close();
    }
}
```



## 4. 日期显示格式

```java
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@TableField("createDate")
private LocalDateTime createDate;
```



## 5. 主键自增获取id

```java
 public ResponseBean addDept(Department dept) {
      // 插入父部门 ==> id 会自动封装到 dept 中
      int ret = departmentMapper.insert(dept);
     
     // 之后通过 dept.getId() 就可以获得插入的 id 了
     // ...
 }
```



## 6. 数据导出Excel

**(1) 后端依赖和代码**

```xml
<!-- POI依赖 -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>3.7</version>
</dependency>

<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.7</version>
</dependency>
```

```java
// 工具类
public class POIUtils {

    /**
     * 员工列表导出为Excel
     *
     * @param empList 员工表
     * @param titles  员工表的标题列表
     * @author Ringo
     * @since 2021/3/26
     */
    public static ResponseEntity<byte[]> emp2Excel(List<Employee> empList, List<EmpTitle> titles) {
        // 1、创建一个Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2、创建文档摘要
        workbook.createInformationProperties();

        // 3、获取并配置文档摘要信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        docInfo.setCategory("员工类别");            // 文档类别
        docInfo.setManager("ymy");                 // 文档管理员
        docInfo.setCompany("无");                  // 公司

        // 4、获取文档摘要信息
        SummaryInformation summaryInfo = workbook.getSummaryInformation();
        summaryInfo.setTitle("员工信息表");         // 文档标题
        summaryInfo.setAuthor("ymy");              // 作者
        summaryInfo.setComments("备注");           // 备注

        // 5、创建样式

        // 创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 6、创建Excel表
        HSSFSheet sheet = workbook.createSheet("员工信息表");

        // 7、创建标题行
        HSSFRow row0 = sheet.createRow(0);
        for (int i = 0; i < titles.size(); ++i) {
            HSSFCell column = row0.createCell(i);
            column.setCellStyle(headerStyle);
            column.setCellValue(titles.get(i).getTitle());
            sheet.setColumnWidth(i, 10 * 256);
        }

        // 8、创建Excel表的内容
        for (int i = 0; i < empList.size(); ++i) {
            HSSFRow row = sheet.createRow(i + 1);
            Employee e = empList.get(i);
            row.createCell(0).setCellValue(e.getId());
            row.createCell(1).setCellValue(e.getName());
            row.createCell(2).setCellValue(e.getGender());
            // ...
        }

        // 9、返回输出
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("员工表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(bao);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(bao.toByteArray(), headers, HttpStatus.CREATED);
    }
}
```



**(2) 前端代码点击事件**

```javascript
exportData() {
    // 打开一个新的URL 不打开新的页面！
    window.open("/employee/basic/export", "_parent");
}
```



## 7. 数据导入解析Excel

```java
// 前端使用的是 ElementUI 的 upload组件
// 接收文件使用 MultipartFile
@PostMapping("/import")
public ResponseBean importData(MultipartFile file) {
    return employeeServiceImpl.importData(file);
}
```



```java
/**
     * 将 Excel 文件解析成 List<Employee>
     *
     * @author Ringo
     * @since `2021/3/27`
     */
public static List<Employee> excel2EmployeeList(MultipartFile file) throws IOException {
    List<Employee> es = new ArrayList<>();
    Employee employee = null;

    // 1、创建 HSSFWorkbook 对象
    HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 2、获取 workbook 中 Excel表 的数量, 并遍历
    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        // 3、根据下标获取 workbook 中的 Excel 表
        HSSFSheet sheet = workbook.getSheetAt(i);
        int rows = sheet.getPhysicalNumberOfRows(); // 获取 Excel 表的行数
        for (int j = 1; j < rows; j++) {            // 从第一行开始解析
            HSSFRow row = sheet.getRow(j);
            if (row == null)
                continue;
            employee = new Employee();
            // 4、获取列, 并遍历
            for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                HSSFCell cell = row.getCell(k);
                switch (k) {
                    case 1:
                        employee.setName(cell.getStringCellValue());
                        break;
                    case 2:
                        employee.setGender(cell.getStringCellValue());
                        break;
                    case 3:
                        employee.setBirthday(LocalDate.parse(cell.getStringCellValue(), fmt));
                        break;
                        // ....
                }
            }
            es.add(employee);
        }
    }
    return es;
}
```



## 8. RabbitMQ和发送邮件

**（1）依赖**

```xml
<!-- vhr-web发送消息需要引入 -->
<!-- mail-server 邮件服务器接收消息也需要引入 -->
<!-- rabbitmq  -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

```xml
<!-- mail邮件服务 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
<!-- thymeleaf -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

**（2）邮件服务器配置**

```yaml
server:
  port: 8002
spring:
  application:
    name: mail-server
  rabbitmq:
    host: .........
    port: 5672
    username: guest
    password: guest
    virtual-host: /
  mail:
    username: ringotangs@qq.com
    password: .......
    host: smtp.qq.com
    default-encoding: UTF-8
    port: 587
```



**（3）RabbitMQ配置类**：由于使用`LocalDate`，消息转换需要特殊处理。

```java
@Configuration
public class RabbitMQConfig {
    /**
     * 消息序列化
     *
     * @author Ringo
     * @since 2021/3/28
     */
    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(om);
    }

    /**
     * 定义队列
     *
     * @author Ringo
     * @since 2021/3/28
     */
    @Bean
    public Queue queue() {
        return new Queue("ymy.mail.welcome");
    }
}
```



**（4）vhr-web 当添加完员工后就将员工信息发送的MQ中**

```java
List<Employee> employees = employeeMapper.queryDetails(employee.getId(), null);
rabbitTemplate.convertAndSend("ymy.mail.welcome", employees.get(0));
```



**（5）邮件服务器接收消息，并发送邮件**

```java
@Slf4j
@Component
public class MailReceiver {

    // 发送邮件
    @Resource
    private JavaMailSender mailSender;

    // thymeleaf 模板引擎渲染
    @Resource
    private SpringTemplateEngine templateEngine;

    // mail 邮件的配置文件
    private MailProperties mailProperties;

    @Autowired
    public MailReceiver(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    public MailReceiver() {
    }

    @RabbitListener(queues = {"ymy.mail.welcome"})
    public void receive(Employee employee) {
        // 收到消息, 发送邮件
        log.info(employee + "");

        // 1、创建复杂的邮件
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        // 2、将 thymeleaf 模板引擎转成字符串
        Context context = new Context();
        Map<String, Object> map = new HashMap<>();
        map.put("name", employee.getName());
        map.put("posname", employee.getPosition().getName());
        map.put("jlname", employee.getJobLevel().getName());
        map.put("deptname", employee.getDepartment().getName());
        context.setVariables(map);
        String mail = templateEngine.process("mail", context);

        // 3、设计邮件内容
        try {
            messageHelper.setTo(employee.getEmail());
            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setSubject("入职欢迎");
            messageHelper.setSentDate(new Date());
            messageHelper.setText(mail, true);
        } catch (MessagingException e) {
            log.info("入职邮件发送失败！");
        }

        // 3、发送邮件
        mailSender.send(message);
    }
}
```





# 二、前端相关

## 1. 设置跨域

vue 项目中，可以在`vue.config.js`中将请求转发到服务器上，来解决前端跨域的问题。

```javascript
module.exports = {
    devServer: {
        open: true,
        host: 'localhost',
        port: 8080,
        https: false,
        //以上的ip和端口是我们本机的;下面为需要跨域的
        proxy: { //配置跨域
            '/': {
                //这里后台的地址模拟的;应该填写你们真实的后台接口
                target: 'http://localhost:8001/', 
                ws: false,
                changOrigin: true, //允许跨域
                pathRewrite: {
                    '^/': '' 
                }
            }
        }
    }
}
```



## 2. axios响应拦截器

```javascript
import axios from 'axios'

import {
    Message
} from 'element-ui'

// ~ ~ ~ ~ ~ ~ element-ui 相关查看官网 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
// 错误消息提示框
export function messageErrorTip(message, showClose) {
    return Message.error({
        message,
        showClose
    });
}

// 消息提醒
export function messageTip(message, type, showClose) {
    return Message({
        message,
        type,
        showClose
    });
}

// ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~   

// 创建 axios 实例
export const instance8001 = axios.create({
    // 配置跨域 暂时不用 baseURL
    // baseURL: 'http://localhost:8001',
    timeout: 5000,
})

// 后台8001端口 响应拦截器
instance8001.interceptors.response.use(success => {

    // 业务上有错误, 如登录失败, 就用 Message提示框 来显示 ==> 请求信息也就不用返回了
    // success.data 是我们后端返回的 ResponseBean
    if (success.status && success.status == 200 && success.data.status == 500) {
        messageTip(success.data.message, 'error', true);
        return;
    }

    // 业务上没有错误, 并且有 message 信息，将 message 弹框提示
    if (success.data.message) {
        messageTip(success.data.message, 'success', true)
    }

    // 业务正常, 直接放行返回的数据即可
    return success.data;
}, error => {
    if (error.response.status == 504 || error.response.status == 404) {
        messageErrorTip('服务器出错了/(ㄒoㄒ)/~~', true);
    } else if (error.response.status == 403) {
        messageErrorTip('没有权限,请联系管理员!', true);
    } else if (error.response.status == 401) {
        messageErrorTip('尚未登录,请登录!', true);
    } else {
        if (error.response.data.message) {
            messageErrorTip(error.response.data.message, true);
        } else {
            messageErrorTip('未知错误', true);
        }
    }
    return;
})
```



## 3. POST请求封装k-v

```javascript
import { instance8001 } from '@/utils/api.js'

// spring-security 默认登录认证 需要传k-v(即表单登录)
export function postKeyValueRequest(url, params) {
    return instance8001({
        method: 'post',
        url,
        data: params,

        // transformRequest 允许在向服务器发送数据之前修改请求数据
        // 只能用在 'PUT', 'POST' 和 'PATCH' 这几个请求方法
        transformRequest: [function (data) {
            let ret = '';

            // 遍历对象时,这里的 i 是对象中的 key
            // 遍历数组时,这里的 i 是数组中的 index 下表
            for (let i in data) {
                ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
            }
            return ret;
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    });
}
```



## 4. sessionStorage封装用户信息

```javascript
// 详情查看 Login.vue
// 登录成功, 将服务器返回的数据保存到 sessionStorage 中
window.sessionStorage.setItem("user", JSON.stringify(res.data));
```



## 5. Menu的封装

```javascript
// vuex store
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    routes: [], // 从服务器加载的路由数组(菜单项)
  },
  mutations: {
    initRoutes(state, data) {
      state.routes = data;
    }
  },
  actions: {},
  modules: {}
})
```



```javascript
import {
    getRequest
} from '@/network/request.js'



/**
 * 加载菜单项
 * 
 * @param {*} router vue-router 的 router 对象
 * @param {*} store vuex 的 store 对象
 */
export function initMenu(router, store) {

    // 如果 store 中定义 routes 有值就不用发请求了
    // 这里的判断仅限于不刷新页面时 ==> 因为刷新页面 vuex 数据失效
    // 浏览器刷新时 <==> 从服务器获取最新的数据
    if (store.state.routes.length > 0) {
        console.log(store.state.routes.length);
        return;
    }


    // 发送 GET 请求,请求菜单项
    // 经过 axios 拦截器返回 res 的就是服务器的 ResponseBean
    getRequest('/menus', null).then(res => {
        if (res) {
            // 经过前端格式化之后的 Routes 数组(component 由字符串转成了导入的组件)
            let fmtRoutes = formatRoutes(res.data);

            // 在 vue-router 中添加格式化之后的路由
            // router.addRoutes(fmtRoutes);
            fmtRoutes.forEach(fmtRoute => router.addRoute(fmtRoute));

            // 在 vuex 中的 store 保存格式化之后的路由
            store.commit('initRoutes', fmtRoutes);
        }
    });
}

/**
 * 格式化请求到的routes数组(菜单项)
 * 主要是将routes数组中的`component`由字符串转为组件
 * 
 * @param {*} routes 请求到的routes数组(菜单项)
 */
export function formatRoutes(routes) {
    let ret = [];
    routes.forEach(route => {
        // 将route中的k-v解构
        let {
            path,
            component,
            name,
            iconCls,
            meta,
            children
        } = route;

        // 将二级菜单格式化
        if (children instanceof Array && children.length > 0) {
            children = formatRoutes(children)
        }

        // 格式化菜单
        let formatRoute = {
            path,
            name,
            iconCls,
            meta,
            children,

            // 等价于在 vue router 中导入组件
            // 这种带有 children 的属于嵌套的路由 ==> 就可以使用 <router-view> 在同一页面显示
            component: (resolve) => {
                // 这里其实就是在项目中找组件的位置
                let prefix = path.split('/')[1];
                if (prefix == 'home') {
                    require(['../views/' + component + '.vue'], resolve);
                } else {
                    require(['../views/' + prefix + '/' + component + '.vue'], resolve);
                }

            }
        }
        ret.push(formatRoute);
    })
    return ret;
}
```



## 6. 注销登录

```javascript
/**
 * 重置路由 在 router中写
 */
const createRouter = () =>
  new VueRouter({
    mode: "hash",
    routes
  });

// 定义一个resetRouter 方法，在退出登录时，调用即可
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher;
}
```



```javascript
// 发送 '/logout' 网络请求
getRequest("/logout", null);
// 清除 session
window.sessionStorage.removeItem("user");

// 路由跳转回登录页面
this.$router.replace("/");

// 注销登录, 清空 store 中的 菜单项
this.$store.commit("initRoutes", []);

// 重置路由
resetRouter();
```



## 7. 未登录跳转到登录页

**前端页面如果没有登录，就必须强制到登录页面，可以使用全局导航守卫来完成**。

```javascript
// vue-router ==> index.js

// 全局前置导航守卫：从哪来 ==> 到哪去
// 相当于 java 中的拦截器
router.beforeEach((to, from, next) => {
  let user = JSON.parse(window.sessionStorage.getItem("user"));
  // 用户已经登录了
  if (user != null) {
    initMenu(router, store);
    next();
  } else {
    // 用户未登录
    if (to.path == '/') {
      next();
    } else {
      next('/');
    }
  }
})
```



## 8. font-awesome

```shell
# 安装
npm install font-awesome --save
```

```javascript
/* 在main.js中引入 */
import 'font-awesome/css/font-awesome.min.css'
```



## 9. Delete请求携带参数

```javascript
let params = "?";
for (const i of this.delBatch) {
    params += "ids=" + i.id + "&";
}
console.log(params);
deleteRequest(
    "/system/basic/position/delBatch" + params,
    null
).then((res) => this.getPositions());
```

```java
// 后端用数组接收即可
@DeleteMapping("/delBatch")
public ResponseBean delBatch(Integer[] ids) {
    return positionServiceImpl.delBatch(ids);
}
```



## 10. $refs 用法

**树形控件中是循环来展示的，如何拿到当前选中的树？**

```vue
<el-collapse-item v-for="(role, index) in roles" :key="index">
    <el-tree
             :data="menus"
             :props="defaultProps"
             show-checkbox
             node-key="id"
             :default-checked-keys="defaultCheckKeys"
             ref="tree"
             :key="index"
             >
    </el-tree>
    <button @click="btnClick(index)">按钮</button>
</el-collapse-item>
```





```javascript
// 传入数组的下标，结合 $refs 就可以拿到当前的树了
btnClick(index) {
   let tree = this.$refs.tree[index]; 
}
```

