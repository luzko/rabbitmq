package com.epam.course.rabbitmq.consumer;

import static com.epam.course.rabbitmq.constant.RabbitMQConstant.QUEUE_1;

import com.epam.course.rabbitmq.model.Receipt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Receiver {

    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = QUEUE_1)
    public void receiveMessage(Receipt message) {
        log.info("Message received: {}", message);
        //for failed message receive
        throw new RuntimeException("Processing failed");
    }
}
