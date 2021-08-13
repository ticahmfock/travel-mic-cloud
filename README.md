# travel-mic-cloud
# gateway:
Spring Cloud Gateway作为所有请求流量的入口，在实际生产环境中为了保证高可靠和高可用，尽量避免重启, 需要实现Spring Cloud Gateway动态路由配置。实现动态路由其实很简单, 重点在于 RouteDefinitionRepository 这个接口. 这个接口继承自两个接口, 其中 RouteDefinitionLocator 是用来加载路由的. 它有很多实现类, 其中的 PropertiesRouteDefinitionLocator 就用来实现从yml中加载路由. 另一个 RouteDefinitionWriter 用来实现路由的添加与删除
#线上项目发布一般有以下几种方案:
 1、停机发布
 2、蓝绿部署
 3、滚动部署
 4、灰度发布
 停机发布 这种发布一般在夜里或者进行大版本升级的时候发布，因为需要停机，所以现在大家都在研究 Devops 方案。
 
 蓝绿部署 需要准备两个相同的环境。一个环境新版本，一个环境旧版本，通过负载均衡进行切换与回滚，目的是为了减少服务停止时间。
 
 滚动部署 就是在升级过程中，并不一下子启动所有新版本，是先启动一台新版本，再停止一台老版本，然后再启动一台新版本，再停止一台老版本，直到升级完成。基于 k8s 的升级方案默认就是滚动部署。
 
 灰度发布 也叫金丝雀发布，灰度发布中，常常按照用户设置路由权重，例如 90%的用户维持使用老版本，10%的用户尝鲜新版本。不同版本应用共存，经常与 A/B 测试一起使用，用于测试选择多种方案。

网关核心功能是路由转发，因此不要有耗时操作在网关上处理，让请求快速转发到后端服务上

网关还能做统一的熔断、限流、认证、日志监控等

https://blog.csdn.net/qq_38380025/article/details/102968559
https://blog.csdn.net/X5fnncxzq4/article/details/80221488

# oauth-center:
采用spring security + Oauth2
##授权服务器配置
@EnableAuthorizationServer注释用于配置的OAuth 2.0授权服务器机制

ClientDetailsServiceConfigurer：定义客户端详细信息服务的配置器。可以初始化客户端详细信息;

AuthorizationServerSecurityConfigurer：定义令牌端点上的安全约束;

AuthorizationServerEndpointsConfigurer：定义授权和令牌端点以及令牌服务;
### 配置客户端详细信息
可以通过实现JdbcClientDetailsService来自定义客户端信息信息;客户端的重要熟悉是

clientId：（必需）客户端ID。

secret：（受信任的客户端需要）客户端机密，如果有的话。

scope：客户端受限的范围。如果范围未定义或为空（默认），则客户端不受范围限制。

authorizedGrantTypes：授权客户端使用的授权类型。默认值为空。

authorities：授予客户端的权限（常规 Spring Security 权限）。
### 管理令牌
该AuthorizationServerTokenServices接口定义了管理 OAuth 2.0 令牌所需的操作

创建访问令牌时，必须存储身份验证，以便接受访问令牌的资源可以稍后引用它。

访问令牌用于加载用于授权其创建的身份验证。

在创建的AuthorizationServerTokenServices实现类，使用DefaultTokenServices具有许多可插入的策略来更改访问令牌的格式和存储的 。
默认情况下，它通过随机值创建令牌并处理除它委托给TokenStore.
