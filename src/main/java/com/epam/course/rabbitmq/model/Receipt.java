package com.epam.course.rabbitmq.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receipt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @Id
    private long id;
}
