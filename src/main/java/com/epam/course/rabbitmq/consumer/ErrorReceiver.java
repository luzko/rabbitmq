package com.epam.course.rabbitmq.consumer;

import static com.epam.course.rabbitmq.constant.RabbitMQConstant.DEAD_MESSAGE_QUEUE;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.FAILED_MESSAGE_QUEUE;

import com.epam.course.rabbitmq.model.Receipt;
import com.epam.course.rabbitmq.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ErrorReceiver {

    private final ReceiptRepository receiptRepository;

    @RabbitListener(queues = FAILED_MESSAGE_QUEUE)
    public void receiveErrorMessage(Receipt message) {
        log.info("Error message received: {}", message);
        receiptRepository.save(message);
        log.info("Error message saved successfully");
    }

    @RabbitListener(queues = DEAD_MESSAGE_QUEUE)
    public void receiveDeadMessage(Receipt message) {
        log.info("Dead message received: {}", message);
        receiptRepository.save(message);
        log.info("Dead message saved successfully");
    }
}
