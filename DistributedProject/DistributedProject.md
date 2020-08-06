# 1.分布式项目环境搭建

## 1.1.MySQL环境

```shell
# 1、拉取镜像
docker pull mysql:5.7

# 2、创建实例并启动
docker run -p 3306:3306 --name mysql \
-v /root/mysql/log:/var/log/mysql \
-v /root/mysql/data:/var/lib/mysql \
-v /root/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=333 \
-d mysql:5.7

# 3、mysql配置 /root/mysql/conf/my.conf
[client]
#mysqlde utf8字符集默认为3位的，不支持emoji表情及部分不常见的汉字，故推荐使用utf8mb4
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
#设置client连接mysql时的字符集,防止乱码
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'

#数据库默认字符集
character-set-server=utf8

#数据库字符集对应一些排序等规则，注意要和character-set-server对应
collation-server=utf8_unicode_ci

# 跳过mysql程序起动时的字符参数设置 ，使用服务器端字符集设置
skip-character-set-client-handshake

# 禁止MySQL对外部连接进行DNS解析，使用这一选项可以消除MySQL进行DNS解析的时间。但需要注意，如果开启该选项，则所有远程主机连接授权都要使用IP地址方式，否则MySQL将无法正常处理连接请求！
skip-name-resolve

# 4、重启mysql容器
docker restart mysql

# 5、进入到mysql容器
docker exec -it mysql /bin/bash

# 6、查看修改的配置文件
cat /etc/mysql/my.conf
```

## 1.2.Redis环境

```shell
# 1、拉取镜像
docker pull redis:6.0.5

# 2、创建文件夹
mkdir -p /root/redis/conf
mkdir -p /root/redis/data
touch /root/redis/conf/redis.conf

# 3、创建实例并启动
docker run -p 6379:6379 --name redis \
-v /root/redis/data:/data \
-v /root/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis:6.0.5 redis-server /etc/redis/redis.conf

# 4、执行redis-cli(默认没有持久化)
docker exec -it redis redis-cli

# 5、修改Redis配置文件(/root/redis/conf/redis.conf)使用AOF存储
appendonly yes

# 6、重启Redis容器
docker restart redis

# redis.conf下载地址：https://github.com/antirez/redis/blob/6.0.5/redis.conf
```

## 1.3.开发工具配置

> Maven环境

```xml
<profile>     
    <id>jdk-1.8</id>       
    <activation>       
        <activeByDefault>true</activeByDefault>       
        <jdk>1.8</jdk>       
    </activation>       
    <properties>       
        <maven.compiler.source>1.8</maven.compiler.source>       
        <maven.compiler.target>1.8</maven.compiler.target>       
        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>       
    </properties>      	 
</profile>
```

> 下载Idea插件

- Lombok。
- MyBatisX。

> 下载VSCode插件

- **下载地址：https://code.visualstudio.com/**
- **VSCode需要安装的插件：**
  - **Auto Close Tag**
  - **Auto Rename Tag**
  - **Chinese (Simplified) Language Pack for Visual Studio Code**
  - **ESLint**
  - **HTML CSS Support**
  - **HTML Snippets**
  - **JavaScript (ES6) code snippets**
  - **Live Server**
  - **open in browser**
  - **Vetur**

## 1.4.数据库初始化

**资料链接：链接：https://pan.baidu.com/s/1D2oJhwzG5wIkpTf-4zmpQA**
**提取码：w8bj**

- `gulimall_oms`：订单系统。
- `gulimall_pms`：商品系统。
- `gulimall_sms`：营销系统。
- `gulimall_ums`：用户系统。
- `gulimall_wms`：库存系统。



## 1.5.Nacos

> 安装Nacos

```shell
# 1、拉取nacos-server镜像
docker pull nacos/nacos-server

# 2、/root/nacos/init.d/custom.properties配置如下
management.endpoints.web.exposure.include=*

# 3、启动docker
docker run -d -p 8848:8848 \
-e MODE=standalone \
-v /root/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties \
-v /root/nacos/logs:/home/nacos/logs \
--name nacos nacos/nacos-server:latest
```

> 依赖

```xml
<!--spring cloud alibaba nacos config 和配置中心相关-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>

<!--spring cloud alibaba nacos discovery 和服务注册相关-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

> Nacos配置中心

1、`namespace`命令空间：配置隔离。`nacos`默认`public`（保留空间），默认所有配置都在`public`空间。

- `开发、测试、生产，利用`namespace`来做环境隔离。

![naocs命名空间](https://img-blog.csdnimg.cn/20200717100854580.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



- 注意`bootstrap.yaml`配置上，需要指定命名空间的配置。

```yaml
spring:
  application:
    name: guli-mall-product-service
  cloud:
    nacos:
      config:
        server-addr: 39.97.3.60:8848  # 配置nacos配置中心
        file-extension: yaml
        prefix: global-service-config
        namespace: b2bc5a0e-3f59-4a64-b3f9-7859b1f5e03e # namespace = dev
```



2、配置集：所有的配置的集合就被称为配置集。

配置集ID：类似文件名。就是Nacos中的`DataID`。

配置分组：默认所有的配置集都属于`DEFAULT_GROUP`。

![配置分组](https://img-blog.csdnimg.cn/2020071710202883.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

`bootstrap.yaml`要添加关于分组的配置。

```yaml
spring:
  application:
    name: guli-mall-product-service
  cloud:
    nacos:
      config:
        server-addr: 39.97.3.60:8848  # 配置nacos配置中心
        file-extension: yaml
        prefix: global-service-config
        namespace: b2bc5a0e-3f59-4a64-b3f9-7859b1f5e03e # namespace = dev
        group: DEV # 分组
```

**每个微服务创建自己的namespace，使用配置分组区分环境，dev、test、prod环境。**



3、同时加载多个配置

微服务的任何配置信息都可以放在配置中心，只需要在`bootstrap.properties`说明加载那些配置文件即可。而且配置中心有的配置，优先使用配置中心的配置。

**Nacos中的配置**

![配置](https://img-blog.csdnimg.cn/20200717120818480.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

**bootstrap.properties配置**

```properties
# 微服务名字
spring.application.name=guli-mall-product-service

# 配置中心地址
spring.cloud.nacos.config.server-addr=39.97.3.60:8848

# 名称空间
spring.cloud.nacos.config.namespace=b2bc5a0e-3f59-4a64-b3f9-7859b1f5e03e

# 加载指定的配置文件
# 这里默认加载guli-mall-product-service.yaml
spring.cloud.nacos.config.group=PROD
spring.cloud.nacos.config.file-extension=yaml

# 加载配置中心多个配置文件
# mybatis配置
spring.cloud.nacos.config.ext-config[0].data-id=mybatis.yaml
spring.cloud.nacos.config.ext-config[0].group=DEV
spring.cloud.nacos.config.ext-config[0].refresh=true

# 数据源配置
spring.cloud.nacos.config.ext-config[1].data-id=datasource.yaml
spring.cloud.nacos.config.ext-config[1].group=DEV
spring.cloud.nacos.config.ext-config[1].refresh=true
```

**application.yaml配置**

```yaml
server:
  port: 8002
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 39.97.3.60:8848  # 配置nacos服务注册中心
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

# 2.商品服务

## 2.1.三级分类

### 2.1.1.查询-递归树形结构

```java
package com.ymy.guli.mall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ymy.guli.mall.pms.entity.PmsCategory;
import com.ymy.guli.mall.pms.mapper.PmsCategoryMapper;
import com.ymy.guli.mall.pms.service.PmsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author RingoTangs
 * @since 2020-07-16
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {

    @Resource
    private PmsCategoryMapper pmsCategoryMapper;

    /**
     * 查出所有的分类，并且封装成多级分类(树形结构)
     * @return
     */
    @Override
    public List<PmsCategory> listWithTree() {
        // 1、查出所有分类
        List<PmsCategory> pmsCategories = pmsCategoryMapper.selectList(null);

        // 2、组装成父子的树形结构
        // 找到所有的一级分类
        List<PmsCategory> level1Menus = pmsCategories.stream().filter(pmsCategory ->
                pmsCategory.getParentCid() == 0
        ).map(menu -> {
            // 在一级分类中添加子分类
            menu.setChildren(getChildrens(menu, pmsCategories));
            return menu;
        }).sorted((menu1, menu2) -> {
            return menu1.getSort() - menu2.getSort();
        }).collect(Collectors.toList());

        return level1Menus;
    }

    /**
     * 递归查找所有分类的子分类
     *
     * @param root
     * @param all
     * @return
     */
    private List<PmsCategory> getChildrens(PmsCategory root, List<PmsCategory> all) {
        List<PmsCategory> childrens = all.stream().filter(pmsCategory -> {
            return pmsCategory.getParentCid() == root.getCatId();
        }).map(pmsCategory -> {
            // 1、找到子菜单
            pmsCategory.setChildren(getChildrens(pmsCategory, all));
            return pmsCategory;
        }).sorted((menu1, menu2) -> {
            // 2、菜单的排序
            return ((menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort()));
        }).collect(Collectors.toList());
        return childrens;
    }
}
```

### 2.1.2.配置网关与路径重写

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 39.97.3.60:8848 # nacos服务注册中心
    gateway:
      routes:
        - id: admin_route
          uri: lb://renren-fast
          predicates:
            - Path=/api/**
          filters:
            # 路径重写将"/api/**"重写成"/renren-fast/**"
            - RewritePath=/api/(?<segment>.*),/renren-fast/$\{segment}
```

### 2.1.3网关统一配置跨域

在GateWay服务中写跨域的配置

```java
package com.ymy.guli.mall.gateway.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 配置跨域
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 配置跨域
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}
```

### 2.1.4.前端基本增删改查

```vue
<template>
  <div>
    <!-- tree型三级分类 -->
    <el-tree
      :data="menus"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="catId"
      :default-expanded-keys="expandedKey"
      ref="menuTree"
    >
      <!-- 增加、删除、修改按钮
      使用 scoped slot 会传入两个参数node和data，分别表示当前节点的 Node 对象和当前节点的数据
      -->
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <!-- 显示的分类的名字 -->
        <span>{{ node.label }}</span>
        <span>
          <!-- 添加按钮 -->
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)"
          >Append</el-button>

          <!-- 删除按钮 -->
          <el-button
            v-if="node.childNodes.length == 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >Delete</el-button>

          <!-- 修改按钮 -->
          <el-button type="text" size="mini" @click="() => update(data)">Update</el-button>
        </span>
      </span>
    </el-tree>

    <!-- 对话框 -->
    <el-dialog :title="title" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
      <el-form :model="category">
        <el-form-item label="分类名称" :label-width="formLabelWidth">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标" :label-width="formLabelWidth">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位" :label-width="formLabelWidth">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancleCategory">取 消</el-button>
        <el-button v-if="dialogType===0" type="primary" @click="addCategory">确 定</el-button>
        <el-button v-if="dialogType===1" type="primary" @click="updateCategory">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 批量删除按钮 -->
    <el-row>
      <el-button type="danger" @click="batchDelete">批量删除</el-button>
    </el-row>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  data() {
    return {
      category: {
        catId: null,
        name: "",
        icon: "",
        productUnit: "",
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0
      },
      menus: [], // menus[] 封装的是整个三级分类菜单
      expandedKey: [],
      dialogVisible: false,
      dialogType: 0, // dialogType表示插入,1表示修改
      defaultProps: {
        children: "children",
        label: "name"
      },
      formLabelWidth: "70px",
      title: "提示"
    };
  },
  methods: {
    /**
     * 点击对话框右上角的×号,可以选择是否关闭对话
     */
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          this.category.name = "";
          done();
        })
        .catch(_ => {});
    },

    /**
     * 获得整个三级分类的json数据并赋值给menus
     */
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/pms-category/list/tree"),
        method: "get"
      }).then(({ data }) => {
        // console.log("成功获取到菜单数据..", data.data);
        this.menus = data.data;
      });
    },

    /**
     * 当点击"删除按钮"时会调用remove()方法
     * @param node 当前节点的 Node 对象
     * @param data 当前节点的数据
     */
    remove(node, data) {
      console.log("remove()===>当前节点的Node对象", node);
      console.log("remove()===>当前节点的数据", data);

      // 获取当前节点的catId,后来是根据catID删除的,所以要将catId传给服务器
      let ids = [data.catId];

      // 删除弹窗提示
      this.$confirm(`是否删除【${data.name}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // 点击确定,表示删除该分类,发送POST请求到服务器
          this.$http({
            url: this.$http.adornUrl("/pms-category/delete"),
            method: "post",
            data: this.$http.adornData(ids, false)
          }).then(({ data }) => {
            // 删除类目成功后返回的数据
            console.log("remove()===>删除类目成功后返回的数据", data);

            // 删除成功消息提示
            this.$message({
              type: "success",
              message: "菜单删除成功!"
            });
            // 刷新页面
            this.getMenus();
            // 设置需要默认展开的菜单(展开删除节点的父节点即可)
            this.expandedKey = [node.parent.data.catId];
          });
        })
        .catch(() => {
          // 点击取消按钮,取消删除的提示信息
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },

    /**
     * 点击"批量删除"按钮时会调用batchDelete()方法
     */
    batchDelete() {
      // 获得所有被选中的节点
      let checkedNodes = this.$refs.menuTree.getCheckedNodes();
      console.log("batchDelete()===>被选中的Node====>", checkedNodes);

      let catIds = [];
      for (let i = 0; i < checkedNodes.length; i++) {
        catIds.push(checkedNodes[i].catId);
      }
      console.log("batchDelete()===>catIds===>", catIds);

      // 删除对话框
      this.$confirm(`是否批量删除菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // 点击确认按钮,确定删除
          this.$http({
            url: this.$http.adornUrl("/pms-category/delete"),
            method: "post",
            data: this.$http.adornData(catIds, false)
          }).then(({ data }) => {
            // 删除成功后返回的数据
            console.log("batchDelete()====>删除成功返回的数据===>", data);

            // 删除成功消息提示
            this.$message({
              type: "success",
              message: "菜单删除成功!"
            });

            // 刷新页面
            this.getMenus();
          });
        })
        .catch(() => {
          // 点击取消按钮,取消删除的提示信息
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },

    /**
     * 当点击"添加按钮"时会调用append()方法
     * @param data 当前节点的数据
     */
    append(data) {
      console.log("append()====>传入append()方法中的数据", data);

      // 修改提示标题
      this.title = "添加菜单";
      // dialogType为0代表添加框，dialogType为1代表修改框
      this.dialogType = 0;

      // 打开对话框
      this.dialogVisible = true;

      /**
       * 获取当前节点的catId，赋值给我们要添加的节点parentCid
       * 由于是在当前节点下添加子节点,所以有如下逻辑。
       *
       * 要添加的节点的catLevel是当前节点catLevel加1
       */
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel * 1 + 1;
    },

    /**
     * "添加按钮"弹出的对话框中,点击"确定"就会调用addCategory()方法
     */
    addCategory() {
      console.log(
        "addCategory()======>要提交到服务器的数据=====>",
        this.category
      );

      // 发送POST请求
      this.$http({
        url: this.$http.adornUrl("/pms-category/insert"),
        method: "post",
        data: this.$http.adornData(this.category, false)
      }).then(({ data }) => {
        console.log("addCategory()======>添加记录成功后返回的结果=====>", data);

        // 添加成功提示消息
        this.$message({
          type: "success",
          message: "添加成功!"
        });

        // 添加成功后关闭对话框
        this.dialogVisible = false;

        // 刷新页面
        this.getMenus();

        // 添加成功后设置需要默认展开的菜单
        this.expandedKey = [data.data.catId];
      });
    },

    /**
     * 点击"修改按钮",就会调用update()方法
     * @param data 当前节点的数据
     */
    update(data) {
      console.log("update()====>当前节点的数据", data);

      // 修改对话框的标题
      this.title = "修改菜单";
      // 显示对话框
      this.dialogVisible = true;
      // 表示这是修改对话框
      this.dialogType = 1;

      // 发送请求,获取最新的结点数据
      this.$http({
        url: this.$http.adornUrl(`/pms-category/info/${data.catId}`),
        method: "get"
      }).then(({ data }) => {
        console.log("update()======>获取最新的节点的数据====>", data);
        this.category.catId = data.data.catId;
        this.category.name = data.data.name;
        this.category.icon = data.data.icon;
        this.category.productUnit = data.data.productUnit;
        console.log("update()=====>当前category的值====>", this.category);
      });
    },

    /**
     * 在修改对话框中,当点击"确定"按钮时,调用updateCategory方法
     */
    updateCategory() {
      let { catId, name, icon, productUnit } = this.category;
      console.log("updateCategory()====>要修改的字段====>", {
        catId,
        name,
        icon,
        productUnit
      });
      this.$http({
        url: this.$http.adornUrl("/pms-category/update"),
        method: "put",
        data: this.$http.adornData({ catId, name, icon, productUnit }, false)
      }).then(({ data }) => {
        console.log("updateCategory()=====>修改后数据库中的数据=====>", data);
        // 关闭对话框
        this.dialogVisible = false;

        // 刷新页面
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [data.data.data.catId];
        this.category.name = "";
      });
    },

    // 取消按钮
    cancleCategory() {
      this.dialogVisible = false;
      this.category.name = "";
    }
  },

  /**
   * 在创建vue实例的时候就调用getMenus()方法,获得商品三级分类
   */
  created() {
    this.getMenus();
  }
};
</script>
```







