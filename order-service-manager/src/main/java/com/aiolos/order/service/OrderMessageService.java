package com.aiolos.order.service;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息处理相关业务逻辑
 * @author Aiolos
 * @date 2022/1/5 11:00 下午
 */
@Service
public class OrderMessageService {

    /**
     * 声明消息队列、交换机、绑定、消息的处理
     */
    public void handleMessage() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel()
        ) {
            /*-----restaurant-----*/
            channel.exchangeDeclare("exchange.order.restaurant", BuiltinExchangeType.DIRECT, true, false, null);
            channel.queueDeclare("queue.order", true, false, false, null);
            channel.queueBind("queue.order", "exchange.order.restaurant", "key.order");

            /*-----deliveryman-----*/
            channel.exchangeDeclare("exchange.order.deliveryman", BuiltinExchangeType.DIRECT, true, false, null);
            channel.queueBind("queue.order", "exchange.order.deliveryman", "key.order");
        }
    }
}
