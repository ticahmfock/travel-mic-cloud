package com.tk.config.listener;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/** 从注册中心实例监听事件
 * @author: TK
 * @date: 2021/7/22 17:22
 */
@Slf4j
@Configuration
public class EurekaInstanceListener implements ApplicationListener {

  @Override
  public void onApplicationEvent(ApplicationEvent applicationEvent) {
    // 服务挂掉事件
    if (applicationEvent instanceof EurekaInstanceCanceledEvent) {
      EurekaInstanceCanceledEvent event = (EurekaInstanceCanceledEvent) applicationEvent;
      // 获取当前Eureka实例中的节点信息
      PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance()
          .getServerContext().getRegistry();
      Applications applications = registry.getApplications();
      // 遍历获取已注册节点中与当前失效节点ID一致的节点信息
      applications.getRegisteredApplications().forEach((registeredApplication) -> {
        registeredApplication.getInstances().forEach((instance) -> {
          if (instance.getInstanceId().equals(event.getServerId())) {
            log.error("服务：" + instance.getAppName() + " 挂啦。。。");
          }
        });
      });
    }
    //服务注册事件
    if (applicationEvent instanceof EurekaInstanceRegisteredEvent) {
      EurekaInstanceRegisteredEvent event = (EurekaInstanceRegisteredEvent) applicationEvent;
      log.info("服务：" + event.getInstanceInfo().getAppName() + " 注册成功啦。。。");
    }
  /*  //服务续约事件
    if (applicationEvent instanceof EurekaInstanceRenewedEvent) {
      EurekaInstanceRenewedEvent event = (EurekaInstanceRenewedEvent) applicationEvent;
      log.info("心跳检测服务：" + event.getInstanceInfo().getAppName() + "。。");
    }
    //服务可用事件
    if (applicationEvent instanceof EurekaRegistryAvailableEvent) {
      log.info("服务 Aualiable。。");
    }*/
  }
}
