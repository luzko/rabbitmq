package com.epam.course.rabbitmq.publisher;

import static com.epam.course.rabbitmq.constant.RabbitMQConstant.ROUTING_KEY_1;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.TOPIC_EXCHANGE;

import com.epam.course.rabbitmq.model.Receipt;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Publisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(long id) {
        Receipt receipt = new Receipt();
        receipt.setId(id);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, ROUTING_KEY_1, receipt);
    }
}
