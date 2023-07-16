package org.zucc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 开启使用Stomp协议来传输基于消息broker的消息
 * 这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/data");
    }

    /**
     * 注册STOMP协议的节点(endpoint),并映射指定的url,
     * 添加一个访问端点“/endpointMark”,客户端打开双通道时需要的url,
     * 允许所有的域名跨域访问，指定使用SockJS协议。
     * @param registry 参数
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册STOMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/public")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
