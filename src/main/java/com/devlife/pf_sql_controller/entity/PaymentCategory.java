package com.devlife.pf_sql_controller.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pf_payment_category")
@Data
public class PaymentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}

