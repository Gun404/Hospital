# Hospital
## 22届北邮计院cs专业Java课设大作业医院挂号系统

### 任务要求：
某医院委托我们开发一个专家挂号系统。要求建设一个网站供医生患者挂号，后台数据用数据库支持数据管理。估计大约300，000患者，1000医生，每天平均挂号数量10，000个，访问量20，000，且80%集中在早上8：00左右，因为医院此时开放新一天的预约号。（对这种性能要求提出你的解决方案，本作业不强制要求实际测试）

#### 1.用户管理：
用户包括三种类型，患者、医生和管理员，患者信息包括自己的身份证号、姓名、年龄、性别、住址、联系方式、病历等。医生信息包括身份证号、姓名、年龄、性别、住址、联系方式、所在医院、科室、职称、专长等。管理员包括身份证号、姓名、住址、联系方式等。
管理员可以强制修改所有人信息并自动留下修改记录，可以删除用户并留下删除记录，也可以修改自己的信息。非管理员用户从网上注册，以后只可以修改自身所有信息，特别是可以修改自己的登录密码。管理员负责新注册用户审批，分别是审批医生、患者和管理员。
可以有多个管理员账户，第一个管理员账户为内置的admin账户，初始密码admin。第一次管理员登录强制改密码。

#### 2.专家管理：
专家可以发布和删除自己的出诊信息（比如出诊时间和挂号数量），出诊信息由系统管理员审核后发布。医院暂时不要求而很可能要求的是专家可以发布周期性号源信息。
专家可以查看自己一段时间的出诊信息，也按时间查询自己的安排。

#### 3.患者管理：
挂号：用户登录网站并且选择三天内的时间（比如今天20日，则可以选择21-23日），科室和专家姓名，如有还有号则可以挂号，最后模拟支付（就是直接点击支付就算支付了）。并且记录用户挂号信息。
医院暂时不要求而很可能要求的是增加当日预约功能，从上午7点开始开放，上午9点前可预约当天上午号，14点前可预约当天下午号。可以在挂号到期前一天或更早取消预约。
医院暂时不要求而很可能要求的是历史信息查看，可以查看一段时间或者按时间查询自己的挂号。


### 用例：
管理员：审核用户注册，修改任意用户信息，审核出诊信息,查看所有操作记录。

医生：修改个人信息,发布和删除出诊信息，查看出诊计划。

患者：注册，修改个人信息，挂号，查看挂号历史。


### 设计:
MySql和MyBatis存储管理数据.

Maven管理项目.

JWT令牌管理登录.

AOP实现自动记录操作日志.

三层架构实现核心功能.

md5加密保证用户密码的安全性.

设置定时任务发布周期性号源信息.

早上8点用Redis缓存热点数据如医生出诊信息,避免频繁读取MySql数据库，提高性能。

### 改进:

使用Nginx负载均衡器来分配请求，特别是在高峰时段如早上8点.

分布式数据库，提高性能.

高峰时段用消息队列缓存处理挂号请求，异步处理，减少等待时间。

采用微服务架构，便于维护扩展。

### 开发环境：
 IntelliJ IDEA，基于Spring Boot框架，JDK版本为JDK21

### 启动方式:

Vs Code启动Spring Boot项目：

    第一步：安装扩展：Extension Pack for Java,Spring Boot Extension Pack.

    第二步：导入项目,等待项目初始化(需要一段时间，甚至可能需要十几分钟，因为要联网下载依赖,你可以在下方看到进度)

    第三步: 侧边栏有一个 Spring Boot Dashboard按钮，点击它，在这里你可以直接运行或调试。

    第四步：待程序运行起来，打开浏览器并访问localhost:8080.

### 注意：

Mysql数据库建表应在自己主机上，并在application.yml中配置Mysql数据库的连接信息。否则无法访问数据。这里不再给出数据层的相关信息，只给出服务端的java代码和前端代码。
