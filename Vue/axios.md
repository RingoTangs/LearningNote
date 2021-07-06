# 一、axios入门

## 1. axios基本使用

**(1) get方式发送无参请求**

```html
<!-- 通过cdn引入axios -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script>
    const baseUrl = 'http://localhost:8080';

    axios({
        // 默认使用 get 方式发送请求,如果不写method，那么请求就是Get
        // 可以在method指定请求方式
        method: 'get',
        url: baseUrl + '/student/getAll',
    }).then(res => {                 // 通过then来拿到返回的数据
        console.log(res);
    });

</script>
```



**(2) get方式发送有参请求**

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script>
    const baseUrl = 'http://localhost:8080';

    axios({
        // 在params中讲请求参数写成key-value的方式
        // 本质就是 http://localhost:8080/student/getStudent?id=2
        method: 'get',
        url: baseUrl + '/student/getStudent',
        params: {
            id: '2',
        },
    }).then(res => {
        console.log(res);
    });
</script>
```



**(3) post方式发送json请求**

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script>
    const baseUrl = 'http://localhost:8080';

    axios({
        method: 'post',
        url: baseUrl + '/student/getStudentByName',
        
        // post中使用data发送的就是json数据了
        // Controller要用@RequestBody来就收
        data: {
            name: '张三',
        },
    }).then(res => {
        console.log(res);
    });
</script>
```



```javascript
// post请求发送 key value
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





## 2. axios并发请求

**(1) 并发请求的基本使用**

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    const baseUrl = 'http://localhost:8080';

    // axios.all() 中参数是Array
    axios.all([
        axios({
            method: 'get',
            url: baseUrl + '/student/getAll',
        }),
        axios({
            method: 'get',
            url: baseUrl + '/student/getStudent',
            params: {
                id: '1',
            },
        }),
    ]).then(res => {
        // 这里返回的数据是数组
        console.log(res);
    }).catch(err => {
        console.log(err);
    });
</script>
```

![image-20210112191043082](E:\Typora\image\image-20210112191043082.png)



**(2) axios.spread() 处理并发请求响应结果**

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    const baseUrl = 'http://localhost:8080';

    // axios.all() 中参数是Array
    axios.all([
        axios({
            method: 'get',
            url: baseUrl + '/student/getAll',
        }),
        axios({
            method: 'get',
            url: baseUrl + '/student/getStudent',
            params: {
                id: '1',
            },
        }),
    ]).then(
        // axios.spread() 可以将并发请求的响应结果分隔开
        axios.spread((res1, res2) => {
            console.log(res1);
            console.log(res2);
        })
    ).catch(err => {
        console.log(err);
    });
</script>
```

![image-20210112191719983](E:\Typora\image\image-20210112191719983.png)



## 3. axios的全局配置

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    // 注意：这里是baseURL
    // 有了baseURL就可以不写前边那一串了
    axios.defaults.baseURL = 'http://localhost:8080/';
    axios.defaults.timeout = 5000;
    axios({
        method: 'get',
        url: '/student/getAll',
    }).then(res => {
        console.log(res);
    }).catch(err => {
        console.log(err);
    });
</script>
```



## 4. axios实例(多模块请求)

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    // 1、创建axios实例
    // axios的instance1表示请求的是localhost下8080端口的student模块的数据
    const instance1 = axios.create({
        baseURL: 'http://localhost:8080/student',
        timeout: 5000,
    });

    // 2、使用axios实例
    instance1({
        method: 'get',
        url: '/getAll',
    }).then(res => {
        console.log(res);
    }).catch(err => {
        console.log(err);
    });
</script>
```

![image-20210112193905502](E:\Typora\image\image-20210112193905502.png)



## 5. axios拦截器

```shell
1、axios提供了两大类拦截器：请求拦截器 和 响应拦截器。
2、拦截器的作用：用于我们在网络请求的时候在发起请求/响应时对操作进行响应的处理。
```

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    // 1、创建axios实例
    const instance = axios.create({
        baseURL: 'http://localhost:8080/student',
        timeout: 5000,
    });

    const config = {
        url: '/getAll',
        method: 'get',
    };

    // 2、实例请求拦截器
    // instance.interceptors.request.use(config, err)
    // config: 表示的是url, method等配置信息
    // err: 表示的是错误信息
    instance.interceptors.request.use(config => {
        console.log(config);

        // 拦截了之后一定要放行
        return config;
    }, err => {
        console.log(err);
    });

    // 3、实例响应拦截器
    // instance.interceptors.response.use(ret, err)
    // ret: 表示返回的数据
    // err: 表示返回的错误信息 
    // error.response 可以拿到错误请求
    instance.interceptors.response.use(ret => {
        console.log(ret);

        // 拦截了之后一定要返回出去
        return ret;
    }, err => {
        console.log(err);
    });

    // 4、发送网络请求
    instance(config).then(res => {
        console.log(res);
    }).catch(err => {
        console.log(err);
    });
</script>
```

![image-20210112221911186](E:\Typora\image\image-20210112221911186.png)



## 6. axios在vue中的模块封装

**(1) 安装axios**

```js
// 1、安装axios
npm install axios --save
```



**(2) 封装request.js**

```js
// 导入axios模块
import axios from 'axios'

// 封装网络请求(这里只是封装了axios实例,相当于请求方法)
export function request(config) {
    // 1、创建axios实例z
    const instance = axios.create({
        baseURL: 'http://localhost:8080/student',
        timeout: 5000,
    });

    // 2、发送网络请求
    return instance(config);
}
```



**(3) 在具体模块中使用request.js中的方法**

```js
// 加上{}是导入函数
import { request } from '@/network/request'

export default {
    // 1、根据name来查询学生
    getStudentByname(name) {
        // step1: 配置信息
        const config = {
            url: '/getStudentByName',
            method: 'post',
            data: {
                name: '张三',
            },
        };

        // step2: 发送网络请求
        return request(config);
    }
}
```



**(4) 在vue组件中使用我们封装的方法**

```vue
<template>
  <div id="app">
    <h1>{{ student }}</h1>
  </div>
</template>

<script>
// 导入相关模块的网络请求
import home from "@/network/home/home.js";

export default {
  name: "App",
  data() {
    return {
      student: null,
    };
  },
  created() {
    // 发送axios网络请求
    this.getStudent("张三");
  },
  methods: {
    // 1、通过name查询学生
    getStudent(name) {
      home
        .getStudentByname(name)
        .then((res) => {
          console.log(res);
          this.student = res.data.data.student;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
```

![image-20210112225857135](E:\Typora\image\image-20210112225857135.png)