package com.epam.course.rabbitmq.constant;

public class RabbitMQConstant {

    public static final String QUEUE_1 = "queue1";
    public static final String QUEUE_2 = "queue2";
    public static final String FAILED_MESSAGE_QUEUE = "failMessageQueue";
    public static final String DEAD_MESSAGE_QUEUE = "deadMessageQueue";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String FAILED_MESSAGE_EXCHANGE = "failedMessageExchange";
    public static final String DEAD_MESSAGE_ARGUMENT = "x-dead-letter-exchange";
    public static final String DEAD_MESSAGE_EXCHANGE = "deadMessageExchange";
    public static final String ROUTING_KEY_1 = "foo.bar.#";
    public static final String ROUTING_KEY_2 = "foo.bar.2";
    public static final String FAILED_ROUTING_KEY = "failed.messages.#";

    private RabbitMQConstant() {

    }
}
