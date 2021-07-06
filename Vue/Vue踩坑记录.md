# 一、使用jQuery

## 1. 引入jQuery

```javascript
// 引入jquery
npm install jquery --save

// 使用
import $ from 'jquery';


// 引入js-cookie就可以操作cookie了
npm install js-cookie --save

// 使用
import cookie from 'js-cookie';
```



## 2. 操作cookie

操作cookie的时候需要引入 `js-cookie`。

**API**

**一、创建cookie**。

```javascript
// 注意这里的 key value都应该是字符串
// 如果value是JSON对象, JSON.stringfy(value); 转成字符串即可！

// 1：创建简单的 cookie
cookie.set(key, value);

// 2: 创建有效期为7天的 cookie
cookie.set(key, value, {expires: 7});

// 3: 全页面下都可以使用的 cookie
cookie.set(key, value, {expires: 7, path: '/'});
```

**二、取值**。

```javascript
// 注意：如果 cookie 获取不到就会得到 “undefined”
// 使用 if(cookie.get(key)) 判断可以避免报错
// cookie.get(key) 拿到的是字符串
// httpOnly的cookie，js无法获取, 例如 spring-security 存放的 "自动登录" cookie

// 1: 获取指定的cookie
cookie.get(key);

// 2: 获取所有的cookie
cookie.get();
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/httpOnlyCookie.1wifvc4bi8cg.png" alt="httpOnly" style="zoom:150%;" />



**三、删除**。

```javascript
cookie.remove(key);

// 如果设置cookie的时候指定了path，在删除的时候需要指定路径
cookie.remove(key, {path: '/'});

//注意，删除不存在的cookie不会报错也不会有返回
```



# 二、axios

## 1. 携带cookie

`axios` 请求在跨域的情况下不会携带cookie。

```javascript
// 设置axios请求携带cookie。
import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:9001/';
axios.defaults.timeout = 5000;

axios.defaults.withCredentials = true;				// 该配置axios就会携带cookie了
```



`withCredentials=true`开启后，spring-security应该开启接受跨域cookie。

```java
/**
* spring-security跨域配置
*/
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));

    // 允许携带cookie
    configuration.setAllowCredentials(true);    

    UrlBasedCorsConfigurationSource source =
        new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}

protected void configure(HttpSecurity http) throws Exception {
    http.cors.configurationSource(corsConfigurationSource());
}
```



# 三、vueRouter

## 3.1. 全局前置路由守卫

```javascript
// from：从哪个页面来
// to: 到哪个页面去
// next()：放行当前路径。next('/')：截断当前路径，到 '/' ==> 相当于重新进来一次该函数
router.beforeEach((to, from, next) => { 
	// 省略 ...
}
```

```javascript
// 正确的逻辑
// 全局前置路由守卫
router.beforeEach((to, from, next) => {
    let user = sessionStorage.getItem('user');
    document.title = to.meta.title;
    if (user) {                                 // 已经登录了
        // 已经登录了, 访问 ‘/login’ 截断, 去 '/home'
        if (to.path == '/login')	
            next('/home');
        else
            next();
    } else if (to.path == '/login') { 			// 未登录, 但是请求的路径是 '/login'
        // 这个else保证下面的请求不会发送两次
        next();
    } else {                        // 未登录 ==> 先去服务器查一遍是否有"自动登录"
        // post请求, （携带cookie）拉取用户信息
        // 必须用 promise，否则请求还没过来，代码都执行完了！
        getAuthentication().then(res => {
            console.log(res);
            if (res) {
                sessionStorage.setItem('user', JSON.stringify(res.content));
                user = sessionStorage.getItem('user');
                if (user) next();
                else next('/login');
            } else
                next('/login');
        });
    }
});
```

