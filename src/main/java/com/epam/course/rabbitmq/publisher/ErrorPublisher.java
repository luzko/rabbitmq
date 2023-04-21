package com.epam.course.rabbitmq.publisher;

import static com.epam.course.rabbitmq.constant.RabbitMQConstant.ROUTING_KEY_2;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.TOPIC_EXCHANGE;

import com.epam.course.rabbitmq.model.Receipt;
import com.epam.course.rabbitmq.repository.ReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ErrorPublisher {

    private final RabbitTemplate rabbitTemplate;

    private final ReceiptRepository receiptRepository;

    public void publishMessageFromDatabase() {
        final Iterable<Receipt> receipts = receiptRepository.findAll();

        if (receipts.iterator().hasNext()) {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, ROUTING_KEY_2, receipts.iterator().next());
        }
    }
}
