package com.devlife.pf_sql_controller.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_category")
@Data
public class PaymentCategory {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;
}

