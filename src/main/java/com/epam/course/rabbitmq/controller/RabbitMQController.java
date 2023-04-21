package com.epam.course.rabbitmq.controller;

import com.epam.course.rabbitmq.publisher.ErrorPublisher;
import com.epam.course.rabbitmq.publisher.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RabbitMQController {

    private final Publisher publisher;

    private final ErrorPublisher errorPublisher;

    @PostMapping("/receipt/{id}")
    public void publish(@PathVariable long id) {
        publisher.publish(id);
    }

    @PostMapping("/receipt/error")
    public void publishErrorMessage() {
        errorPublisher.publishMessageFromDatabase();
    }
}
