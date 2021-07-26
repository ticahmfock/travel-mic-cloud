# travel-mic-cloud
# gateway:
Spring Cloud Gateway作为所有请求流量的入口，在实际生产环境中为了保证高可靠和高可用，尽量避免重启, 需要实现Spring Cloud Gateway动态路由配置。实现动态路由其实很简单, 重点在于 RouteDefinitionRepository 这个接口. 这个接口继承自两个接口, 其中 RouteDefinitionLocator 是用来加载路由的. 它有很多实现类, 其中的 PropertiesRouteDefinitionLocator 就用来实现从yml中加载路由. 另一个 RouteDefinitionWriter 用来实现路由的添加与删除
