package com.devlife.pf_sql_controller.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
@Data
public class Payment {

    @Id
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "date")
    private Long date;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "pay_category_id")
    private Long payCategoryId;

    @Column(name = "qty")
    private BigDecimal qty;
}
