package com.jobhunter.appjobfestservice.websocket_chat_system.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    /**
     * Purpose: This method configures the message converters used to convert messages to and from the WebSocket channel.
     **/
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        var resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        var converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }

    /**
     * Purpose: Registers STOMP endpoints mapping each to a specific URL and enables SockJS fallback options.
     **/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * registry.addEndpoint("/ws").withSockJS();:
         *
         * Registers the /ws endpoint, which clients will use to connect to the WebSocket server.
         *
         * The withSockJS() method enables SockJS fallback options for browsers that don’t support WebSocket.
         * **/
        registry.addEndpoint("/ws").withSockJS();
    }


    /**
     * registry.enableSimpleBroker("/user");:
     *  * Enables a simple in-memory message broker for destinations prefixed with /user.
     *  * This is where messages will be sent to users.
     * registry.setApplicationDestinationPrefixes("/app");:
     * Defines a prefix for messages bound for methods annotated with @MessageMapping.
     * Messages sent to destinations prefixed with /app will be routed to these methods.
     **/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }
}
