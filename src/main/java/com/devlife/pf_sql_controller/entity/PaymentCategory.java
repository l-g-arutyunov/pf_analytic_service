package com.devlife.pf_sql_controller.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pf_payment_category")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PaymentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_category_id_seq")
    @SequenceGenerator(name = "payment_category_id_seq", sequenceName = "payment_category_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}

