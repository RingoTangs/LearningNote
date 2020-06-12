## 1.Docker安装

### 1.1.安装Docker

>环境查看

```shell
# 系统内核是3.10以上的
[root@centos-7-test1 ~]# uname -r
3.10.0-1127.el7.x86_64
```

```	shell
# 系统版本
[root@centos-7-test1 ~]# cat /etc/os-release 
NAME="CentOS Linux"
VERSION="7 (Core)"
ID="centos"
ID_LIKE="rhel fedora"
VERSION_ID="7"
PRETTY_NAME="CentOS Linux 7 (Core)"
ANSI_COLOR="0;31"
CPE_NAME="cpe:/o:centos:centos:7"
HOME_URL="https://www.centos.org/"
BUG_REPORT_URL="https://bugs.centos.org/"

CENTOS_MANTISBT_PROJECT="CentOS-7"
CENTOS_MANTISBT_PROJECT_VERSION="7"
REDHAT_SUPPORT_PRODUCT="centos"
REDHAT_SUPPORT_PRODUCT_VERSION="7"
```

>安装Docker

帮助文档：https://docs.docker.com/engine/install/centos/

```shell
# 1、卸载旧的Docker版本
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
# 2、需要的安装包
sudo yum install -y yum-utils

#3、设置镜像的仓库
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo # 默认是国外的下载超级慢！

sudo yum-config-manager \
    --add-repo \   
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo # 阿里云的镜像

# 更新yum软件包索引
yum makecache fast

# 4、安装docker相关的软件 docker-ce社区版 docker-ee企业版
sudo yum install docker-ce docker-ce-cli containerd.io

# 5、启动docker
sudo systemctl start docker

# 6、测试docker安装成功？
docker version

# 7、永远的 [HelloWorld] ^_^ 能够成功拉取image表示docker安装成功！
docker run hello-world
```

```shell
# docker踩到的坑:运行[docker run hello-world]遇到的坑？会报错？
docker: Error response from daemon: Get https://registry-1.docker.io/v2/library/hello-world/manifests/latest: Get https://auth.docker.io/token?scope=repository%3Alibrary%2Fhello-world%3Apull&service=registry.docker.io: net/http: TLS handshake timeout.

# 解决办法：在/etc/docker下创建daemon.json文件。拷贝如下代码:
{
  "registry-mirrors":["https://registry.docker-cn.com","http://hub-mirror.c.163.com"]
}
# 保存退出！
# 重启docker服务：service docker restart
```

> 卸载Docker

```shell
# 1、卸载依赖
sudo yum remove docker-ce docker-ce-cli containerd.io

# 2、删除资源
sudo rm -rf /var/lib/docker

# /var/lib/docker是Docker默认的工作路径！！！
```

### 1.2.阿里云镜像加速

> 设置阿里云镜像步骤

1、登录 [阿里云--->控制台] 找到容器服务。

![image-20200612163401160](D:\git_workspace\LearningNote\Docker\images\image-20200612163401160.png)

2、找到镜像加速地址。

![image-20200612163621219](D:\git_workspace\LearningNote\Docker\images\image-20200612163621219.png)

3、配置加速器使用

```shell
sudo mkdir -p /etc/docker

sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://phapqiqc.mirror.aliyuncs.com"]
}
EOF

sudo systemctl daemon-reload

sudo systemctl restart docker
```



> docker run 的运行流程图

![docker运行流程](D:\git_workspace\LearningNote\Docker\images\image-20200612164518264.png)

### 1.3.底层原理

> docker是怎么工作的？

Docker是一个Client-Server结构的系统，Docker的守护进程运行在主机上，通过Socket从客户端访问。

DockerServer接收到DockerClient的指令，就会执行这个命令。

![docker底层原理](D:\git_workspace\LearningNote\Docker\images\QQ截图20200612193602.jpg)

>docker为什么比VM快？

1、Docker有着比虚拟机更少的抽象层。

2、Docker利用的是宿主机的内核。所以说新建一个容器的时候，docker不需要像虚拟机一样重新加载个操作系统内核。



## 2.Docker的常用命令

### 2.1.帮助命令

```shell
docker version	#显示docker的版本信息
docker info		#显示Docker的系统信息，包括镜像和容器的数量
docker [命令] --help	#docker帮助信息
```

Docker帮助文档地址：https://docs.docker.com/reference/



### 2.2.镜像命令

> docker images  查看所有本地的主机上的镜像

```shell
[root@centos-7-test1 ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
hello-world         latest              bf756fb1ae65        5 months ago        13.3kB

#解释
REPOSITORY	镜像的仓库源
TAG			镜像的标签
IMAGE ID	镜像的ID
CREATED		镜像的创建时间
SIZE		镜像的大小

#可选项
  -a, --all             #列出所有的镜像
  -q, --quiet           #只显示镜像的id
```

> docker search   去dockerhub搜索镜像

```shell
[root@centos-7-test1 ~]# docker search mysql

#可选项
--filter=stars=3000	#搜索出来的镜像就是stars大于3000的
[root@centos-7-test1 ~]# docker search nginx --filter=stars=3000
NAME                DESCRIPTION                STARS               OFFICIAL            AUTOMATED
nginx               Official build of Nginx.   13323               [OK] 
```

> docker poll	下载镜像

```shell
# 下载镜像 docker pull [镜像名] [:tag]
[root@centos-7-test1 ~]# docker pull mysql
Using default tag: latest # 如果不写tag，默认就下载lastest最新版本
latest: Pulling from library/mysql
8559a31e96f4: Pull complete # 分层下载，docker image的核心，联合文件系统
d51ce1c2e575: Pull complete 
c2344adc4858: Pull complete 
fcf3ceff18fc: Pull complete 
16da0c38dc5b: Pull complete 
b905d1797e97: Pull complete 
4b50d1c6b05c: Pull complete 
c75914a65ca2: Pull complete 
1ae8042bdd09: Pull complete 
453ac13c00a3: Pull complete 
9e680cd72f08: Pull complete 
a6b5dc864b6c: Pull complete 
Digest: sha256:8b7b328a7ff6de46ef96bcf83af048cb00a1c86282bfca0cb119c84568b4caf6 # 签名(防伪标志)
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest # 真实地址

#等价于
docker pull mysql
docker pull docker.io/library/mysql:latest

#指定版本下载 
[root@centos-7-test1 ~]# docker pull mysql:5.7
5.7: Pulling from library/mysql
8559a31e96f4: Already exists # 下载的mysql5.7和之前下载的mysql可以部分文件可以共用
d51ce1c2e575: Already exists 
c2344adc4858: Already exists 
fcf3ceff18fc: Already exists 
16da0c38dc5b: Already exists 
b905d1797e97: Already exists 
4b50d1c6b05c: Already exists 
d85174a87144: Pull complete 
a4ad33703fa8: Pull complete 
f7a5433ce20d: Pull complete 
3dcd2a278b4a: Pull complete 
Digest: sha256:32f9d9a069f7a735e28fd44ea944d53c61f990ba71460c5c183e610854ca4854
Status: Downloaded newer image for mysql:5.7
docker.io/library/mysql:5.7
```

> docker rmi	删除image

```shell
# 根据image ID删除指定的image
[root@centos-7-test1 ~]# docker rmi -f 9cfcce23593a
Untagged: mysql:5.7
Untagged: mysql@sha256:32f9d9a069f7a735e28fd44ea944d53c61f990ba71460c5c183e610854ca4854
Deleted: sha256:9cfcce23593a93135ca6dbf3ed544d1db9324d4c40b5c0d56958165bfaa2d46a
Deleted: sha256:98de3e212919056def8c639045293658f6e6022794807d4b0126945ddc8324be
Deleted: sha256:17e8b88858e400f8c5e10e7cb3fbab9477f6d8aacba03b8167d34a91dbe4d8c1
Deleted: sha256:c04c087c2af9abd64ba32fe89d65e6d83da514758923de5da154541cc01a3a1e
Deleted: sha256:ab8bf065b402b99aec4f12c648535ef1b8dc954b4e1773bdffa10ae2027d3e00

# 递归删除所有image
[root@centos-7-test1 ~]# docker rmi -f $(docker images -aq)

#删除多个image
docker rmi -f [image id1] [image id2]....
```

### 2.3.容器命令

**说明：我们有了image才可以创建容器，下载一个centos镜像来测试和学习。**

> docker run	新建容器并启动

```shell
# docker run [可选参数] image

# 参数说明
--name="Name"   # 给容器取名字
-d				# 后台运行容器并且输出容器的id
-it				# 使用交互方式运行，进入容器查看内容
-p				# (小写的p)容器的端口和Linux主机端口的映射 
	-p 主机端口:容器端口(常用)
	-p ip:主机端口:容器端口
	
-P				# (大写的P)随机指定端口

# 测试，启动centos image并进入容器
[root@centos-7-test1 ~]# docker run -it --name="centos-test1" 470671670cac /bin/bash
[root@91f0881f5e49 /]# ls 
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr

# 退出容器命令
[root@91f0881f5e49 /]# exit
exit
[root@centos-7-test1 /]# ls
bin  boot  dev  etc  home  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
```

> docker ps	列出所有的容器

```shell
# 列出所有正在运行的容器
docker ps [可选参数]

# 可选参数
-a	# 列出所有的容器(包括历史运行的容器)
-n	# 显示n个最后创建的容器
-q 	# 只显示容器的id

# 显示最后一个创建的容器
[root@centos-7-test1 /]# docker ps -n=1
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                     PORTS               NAMES
91f0881f5e49        470671670cac        "/bin/bash"         11 minutes ago      Exited (0) 8 minutes ago                       centos-test1

# 列出所有的容器(包括历史运行的容器)
docker ps -a
```

> 退出容器

```shell
exit	# 直接退出容器并退出
ctrl + P + Q 	# 容器不停止退出
```

> docker rm	删除容器

```shell
# 按照容器id删除容器 不能删除正在运行的容器，如果要强制删除，需要使用 rm -f
docker rm [容器id]

# 删除所有容器
docker rm -f $(docker ps -aq)

docker ps -aq | xargs docker rm		# 删除所有的容器
```

> 启动和停止容器的操作

```shell
docker start [容器id]		# 启动容器
docker restart [容器id]	# 重新启动容器
docker stop [容器id] 		# 停止当前正在运行的容器
docker kill [容器id]		# 强制停止运行容器
```

### 2.4.其他常用命令

> 后台启动容器

```shell
# docker run -d [image]

# 问题：docker ps 之后，返现容器停止运行了？why？

# 常见的坑，docker 容器使用后台运行，就必须要有一个前台的进程，docker发现没有应用，就会自动停止。
```

> 查看日志

```shell
docker logs -f -t --tail [number] [容器id]

# 参数说明
-f	# 跟踪日志输出
-t	# 显示时间戳
--tail	# 显示最近number条日志
```

> 查看容器中进程信息

```shell
docker top [容器id]

[root@centos-7-test1 ~]# docker top db1f61a0c5c9
UID                 PID                 PPID                C                   STIME               TTY      root                7196                7177                0                   22:33               pts/0   
```

> 查看容器的元数据

```shell
docker inspect [containerId]
```

> 进入当前正在运行的容器

```shell
# 容器通常都是后台运行的，需要进入容器 

# 方式一：docker exec
docker exec -it [containerId] [bashshell]

[root@centos-7-test1 ~]# docker exec -it db1f61a0c5c9 /bin/bash
[root@db1f61a0c5c9 /]# ls
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr 

# 方式二：docker attach
docker attach [containerId]

# 问题：docker exec 和 docker attach 的区别？
docker exec		# 进入容器后开启一个新的终端，exit后不会关闭容器(常用)
docker attach 	# 进入容器正在执行的终端，不会启动新的进程，exit后容器会stop。
```

> 从容器内拷贝文件到主机上

```shell
# 命令docker cp
docker cp containerID:容器内路径 目的主机路径


# 测试将centos容器内 /etc/profile 文件拷贝到主机 /opt/ 目录下
[root@centos-7-test1 ~]# docker ps 
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS    db1f61a0c5c9        470671670cac        "/bin/bash"         25 minutes ago      Up 2 minutes                            
[root@centos-7-test1 ~]# docker cp db1f61a0c5c9:/etc/profile /opt/
[root@centos-7-test1 ~]# cd /opt/
[root@centos-7-test1 opt]# ll
total 4
drwxr-xr-x. 3 root root   72 Jun 11 14:53 activemq
drwx--x--x. 4 root root   28 Jun 12 16:01 containerd
drwxr-xr-x. 3 root root   60 Jun 11 10:20 java
-rw-r--r--. 1 root root 2078 Sep 10  2018 profile
drwxr-xr-x. 2 root root    6 Oct 31  2018 rh
drwxr-xr-x. 3 root root   61 Jun 11 10:23 zookeeper
```

### 2.5.小结

```shell
attach	Attach to a running container					# 当前shell下进入到指定的正在运行的container中
build 	Build an image from a Dockerfile				# 通过Dockerfile构建镜像
commit	Create a new image from a container‘s changes	# 提交当前容器为新的镜像
cp		Copy files/folders between a container and the local filesystem	# 从容器中拷贝文件到宿主机指定路径
create	Create a new container							# 创建一个新的容器,同run，但是不会启动容器
diff	Inspect changes to file on container 			# 查看容器文件的变化
events 	Get real time events from the server			# 从docker服务获取容器实时事件
exec	Run a command in a running container			# 在一个已经运行的容器内执行命令
export	Export a container’s filesystem as a tar archive	# 导出容器的内容流作为一个tar归档文件[对应 import]
history	show the history of an image					# 展示一个镜像形成历史
images	List images										# 列出系统中当前的镜像
import	Import the contents form a tarball to create a filesystem image	# 从tar包中的内容创建一个新的文件系统镜像[对应 export]
info	Display system-wide information					# 展示出系统相关信息
inspect	Return low-level information on a container		# 查看容器详细信息
kill	kill one or more running containers				# 强制停止一个或多个正在运行的容器
load	load an image from a tar archive				# 从一个tar包中加载镜像[对应 save]
login	Registy or login to the docker registry server	# 注册或者登陆一个docker源服务器
logout	Logout from a Docker registy server				# 从当前的docker registry server退出
logs	Fetch the logs of a container					# 显示容器的日志
port	show port mappings for a container				# 查看容器端口的映射
pause	Pause all process within the container			# 暂停容器
ps		List containers									# 列出当前系统中的容器列表
pull	Pull an image from a registry					# 从docker源服务器中拉取镜像
push	Push an image or repository to a registry 		# 推送指定镜像或镜像库到docker源服务器
```

