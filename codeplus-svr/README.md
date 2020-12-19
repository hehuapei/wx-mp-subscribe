# codeplus-svr

> springboot项目,数据库为sqlite

* 该服务依赖 jdk8+ 和 gradle 5.4 环境
* 请先在server/src/main/resources/application.properties里面修改数据库文件目录和小程序配置
* 定时推送消息方法为 server/src/main/java/com.wiken.codeplus.server.service.impl/MpServiceImpl 文件的 send() 方法,请先在里面配置自己的消息模板
* 做完以上准备后再该项目根目录执行 gradle build 即可编译jar包, jar包路径为 server/build/libs/server.jar
* 服务器上直接 java -jar /path/server.jar 即可运行(注意数据库文件要放置在自己配置的路径中,不然运行不起来)
