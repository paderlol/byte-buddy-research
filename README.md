# byte-buddy-research

### 目的

研究[Byte Buddy](http://bytebuddy.net/#/)在系统性能监控方面的使用



### 使用方式

- 使用maven命令打包项目

  ```java
  mvn clean package 
  ```

  

- 在项目**target**目录下找到`byte-buddy-research-0.0.1-SNAPSHOT.jar` ,并且配置项目启动`VM options`

  ```java
  -javaagent:/自己的项目路径/byte-buddy-research/target/byte-buddy-research-0.0.1-SNAPSHOT.jar
  ```

- 启动项目 ByteBuddyResearchApplication

  

- 在浏览器或者Postman直接输入:http://localhost:8080/api/user/index, 在控制台可以看到监控日志

  

