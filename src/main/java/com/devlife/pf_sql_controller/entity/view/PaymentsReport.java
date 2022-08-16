package com.devlife.pf_sql_controller.entity.view;

import com.devlife.pf_sql_controller.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Immutable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "v_payments_report")
public class PaymentsReport {
    @Id
    private Long id;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = PaymentCategory.class)
    @JoinColumn(name = "payment_category_id")
    private PaymentCategory paymentCategory;

    @ManyToOne(targetEntity = Employer.class)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToOne(targetEntity = UserGroup.class)
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "qty")
    private BigDecimal qty;

}
