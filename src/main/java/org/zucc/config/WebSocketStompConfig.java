package org.zucc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Administrator
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * 配置消息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端订阅消息的请求前缀
        registry.enableSimpleBroker("/topic", "/data","/directives");
    }

    /**
     * 注册STOMP的节点，并映射指定的url
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册STOMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/public")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
