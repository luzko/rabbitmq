package com.epam.course.rabbitmq.config;

import static com.epam.course.rabbitmq.constant.RabbitMQConstant.DEAD_MESSAGE_ARGUMENT;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.DEAD_MESSAGE_EXCHANGE;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.DEAD_MESSAGE_QUEUE;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.FAILED_MESSAGE_EXCHANGE;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.FAILED_MESSAGE_QUEUE;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.FAILED_ROUTING_KEY;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.QUEUE_1;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.QUEUE_2;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.ROUTING_KEY_1;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.ROUTING_KEY_2;
import static com.epam.course.rabbitmq.constant.RabbitMQConstant.TOPIC_EXCHANGE;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queue1() {
        return QueueBuilder
            .durable(QUEUE_1)
            .ttl(5000)
            .maxLengthBytes(500)
            .maxLength(5)
            .withArgument(DEAD_MESSAGE_ARGUMENT, DEAD_MESSAGE_EXCHANGE)
            .build();
    }

    @Bean
    public Queue queue2() {
        return QueueBuilder
            .nonDurable(QUEUE_2)
            .build();
    }

    @Bean
    public Queue failedMessageQueue() {
        return QueueBuilder
            .nonDurable(FAILED_MESSAGE_QUEUE)
            .build();
    }

    @Bean
    public Queue deadMessageQueue() {
        return QueueBuilder
            .durable(DEAD_MESSAGE_QUEUE)
            .build();
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public TopicExchange failedMessageExchange() {
        return new TopicExchange(FAILED_MESSAGE_EXCHANGE);
    }

    @Bean
    public FanoutExchange deadMessageExchange() {
        return new FanoutExchange(DEAD_MESSAGE_EXCHANGE);
    }

    @Bean
    public Binding binding1(Queue queue1, TopicExchange topicExchange) {
        return BindingBuilder
            .bind(queue1)
            .to(topicExchange)
            .with(ROUTING_KEY_1);
    }

    @Bean
    public Binding binding2(Queue queue2, TopicExchange topicExchange) {
        return BindingBuilder
            .bind(queue2)
            .to(topicExchange)
            .with(ROUTING_KEY_2);
    }

    @Bean
    public Binding failedMessageBinding(Queue failedMessageQueue, TopicExchange failedMessageExchange) {
        return BindingBuilder
            .bind(failedMessageQueue)
            .to(failedMessageExchange)
            .with(FAILED_ROUTING_KEY);
    }

    @Bean
    public Binding deadMessageBinding(Queue deadMessageQueue, FanoutExchange deadMessageExchange) {
        return BindingBuilder
            .bind(deadMessageQueue)
            .to(deadMessageExchange);
    }
}
