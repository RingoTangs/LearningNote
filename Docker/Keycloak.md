# 一、keycloak部署

## 1. 使用自带的H2数据库部署

```shell
docker run --name keycloak -p 8000:8080 -e KEYCLOAK_USER=Ringo -e KEYCLOAK_PASSWORD=123 -d jboss/keycloak:latest
```



## 2. 使用MySQL数据库部署

```shell
# 创建 docker 网络
docker network create keycloak-network

# 安装mysql（需要提前创建好数据库 默认数据库名keycloak）
docker run -d -p 3306:3306 --name mysql \
-v /root/mysql/conf:/etc/mysql/conf.d \
-v /root/mysql/log:/logs \
-v /root/mysql/data:/var/lib/mysql \
--net keycloak-network \
-e MYSQL_ROOT_PASSWORD=333 \
mysql:5.7

# 安装 keycloak
docker run --name keycloak -d -p 8000:8080 --net keycloak-network \
-e DB_DATABASE=keycloak -e JDBC_PARAMS='useSSL=false&serverTimezone=Asia/Shanghai' \
-e KEYCLOAK_USER=Ringo -e KEYCLOAK_PASSWORD=123 \
-e DB_USER=root -e DB_PASSWORD=333 jboss/keycloak:latest
```



# 二、解决HTTPS require问题

```shell
# 进入到容器
docker exec -it [keycloak containerId] /bin/bash

cd keycloak/bin

# 输入创建的用户名和密码(注意这里的--user Ringo是启动容器时配置的！)
./kcadm.sh config credentials --server http://localhost:8080/auth --realm master --user Ringo

# 禁用 ssl 即可
./kcadm.sh update realms/master -s sslRequired=NONE
```

流程图如下所示：

![keycloak HTTPS required解决办法](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Docker/keycloak HTTPS required 解决办法.2dawt5ovqmo0.png)



# 三、Getting Start

## 1. 创建realm(领域)

> 在 `keycloak` 中一个 `realm` 和 `tanant`  （租客）是等价的。它允许创建独立的应用程序和用户组。在 `keycloak` 中默认有一个 `realm` 称为 `master`。它用于管理整个 `keycloak`，而不是用于自己的应用程序。

创建自己的 `realm`：

- 打开 `keycloak admin console`。
- 鼠标移动到左上角 `Master` 旁边的箭头，然后点击 `Add realm` 按钮。
- 在输入框中添加你准备的 `realm` 名字，然后点击 `Create` 按钮。

![创建keycloak realm领域](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Docker/创建keycloak realm领域.1jpxafn4ngtc.png)



## 2. 创建User(用户)

新的`realm`领域初始化之后，并没有用户。下面来创建用户：

- 打开 `keycloak admin console`。
- 点击 `users` （左侧下方菜单）。然后点击 `Add user` 按钮。
  - 添加`username、FirstName、LastName`。（带 `*` 号是必填字段）。
- 最后点击 `save` 按钮即可。

![adduser](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Docker/adduser.b7irvqn8ye0.png)



创建的用户需要初始化密码才能登录：

- 点击页面上的 `credentials` 选项卡。
- 输入密码并确认密码。
- `Temporary ` 启用（ON），在首次登录时需要修改密码。我们不启用。

![设置密码](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Docker/设置密码.154pu5xbab9c.png)

## 3. 登录到账户控制台

登录到账户控制台来验证用户配置是否正确：

- 访问 `/auth/realms/myrealm/account` 路径。这里的  `myrealm` 是我们自己创建的 `realm` 名字。
- 登录之前创建的账号。点击 `Personal Info` 选项即可完善用户信息。

![自己创建的用户登录控制台](E:\Typora\image\image-20210728110807314.png)



## 4. keycloak保护应用

使用 `keycloak` 保护应用，第一步就是在 `keycloak instance`（实例）上注册你的 `application`（应用）。

- 打开控制台 。访问路径`/auth/admin`。
- 点击 `Clients` 选项。点击 `Create` 按钮。
- 添加以下测试字段。
  - Client ID: `test-client`。
  - Client Protocol：openid-connect。
  - Root URL: `https://www.keycloak.org/app/`。
- 点击 `save`。

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Docker/image.5k0zfbjg5cc0.png)



为了测试方便 Keycloak 官网上提供了一个SPA测试应用。打开[测试网站](https://www.keycloak.org/app/)，修改配置，点击 `save` 保存。

![测试](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Docker/测试keycloak保护英语.2oit6pjljtw0.png)

现在您可以单击`Sign in`以使用您之前启动的 Keycloak 服务器对该应用程序进行身份验证。

```
http://39.97.3%2C60:8080/auth/realms/test-realm/protocol/openid-connect/auth?client_id=myclient&redirect_uri=https%3A%2F%2Fwww.keycloak.org%2Fapp%2F%23url%3Dhttp%3A%2F%2F39.97.3%2C60%3A8080%2Fauth%26realm%3Dtest-realm%26client%3Dmyclient&state=442da6db-f580-458d-8fd5-5068cf432586&response_mode=fragment&response_type=code&scope=openid&nonce=ed47aaeb-04eb-4c96-825b-58d1bfd9c7c2
```

