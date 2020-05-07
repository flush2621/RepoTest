# Swagger 笔记

## 1.springboot的集成

```pom.xml
<!--导入Swagger2以及UI-->
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger2</artifactId>
  <version>2.9.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger-ui</artifactId>
  <version>2.9.2</version>
</dependency>
```

## 2.configuration

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment){
        //检查当前环境判断是否开启Swagger
        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag) //是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.njit.controller"))
                .paths(PathSelectors.ant("/test/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("milo","https://baidu.com","6651815@qq.com");
        return new ApiInfo(
                "Milo's Swagger Documents",
                "Hello Swagger",
                "v1.0",
                "https://baidu.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
```

## 3.开发环境开启Swagger

环境配置文件：

![image-20200508011852070](\images\image-20200508011852070.png)



## 4.分组配置

```java
@Bean
public Docket docketA(Environment environment){
    Profiles profiles = Profiles.of("dev");
    boolean flag = environment.acceptsProfiles(profiles);
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .groupName("A")
            .enable(flag) //是否启用swagger
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.njit.controller"))
            .paths(PathSelectors.ant("/hello"))
            .build();
}

@Bean
public Docket docketB(Environment environment){
    Profiles profiles = Profiles.of("dev");
    boolean flag = environment.acceptsProfiles(profiles);
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .groupName("B")
            .enable(flag) //是否启用swagger
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.njit.controller"))
            .paths(PathSelectors.ant("/test/**"))
            .build();
}
```



## 5.配置API信息

```java
@ApiModel("用户实体类")
public class User {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密 码")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```

```java
@ApiOperation(value = "获取用户信息")
@PostMapping("/getuser")
public User getUser(){
    User user = new User();
    user.setUserName("zhanshan");
    user.setPassword("666666");
    return user;
}
```

## 6.总结

1.通过Swagger给复杂的实体类和方法做注解

2.接口文档实时更新

3.在线测试可以返回准确错误信息