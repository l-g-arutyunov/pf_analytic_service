package com.devlife.pf_sql_controller.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pf_payment_category")
@Data
public class PaymentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_category_id_seq")
    @SequenceGenerator(name = "payment_category_id_seq", sequenceName = "payment_category_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}

