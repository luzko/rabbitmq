package com.epam.course.rabbitmq.repository;

import com.epam.course.rabbitmq.model.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer> {

}
