package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.dto.analyticFormDto.request.PaymentsFormRequest;
import com.devlife.pf_sql_controller.entity.*;
import com.devlife.pf_sql_controller.entity.view.PaymentsReport;
import com.devlife.pf_sql_controller.entity.view.PaymentsReport_;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PaymentCustomRepositoryImpl implements PaymentCustomRepository {
    private final EntityManager entityManager;


    @Override
    public List<PaymentsReport> getPaymentsByParam(PaymentsFormRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Payment.class);

        Root<PaymentsReport> paymentRoot = criteriaQuery.from(PaymentsReport.class);

        criteriaQuery.select(
                criteriaBuilder.construct(PaymentsReport.class,
                        paymentRoot.get(PaymentsReport_.ID),
                        paymentRoot.get(PaymentsReport_.PROJECT),
                        paymentRoot.get(PaymentsReport_.DATE),
                        paymentRoot.get(PaymentsReport_.USER),
                        paymentRoot.get(PaymentsReport_.PAYMENT_CATEGORY),
                        paymentRoot.get(PaymentsReport_.EMPLOYER),
                        paymentRoot.get(PaymentsReport_.USER_GROUP),
                        paymentRoot.get(PaymentsReport_.ROLE),
                        paymentRoot.get(PaymentsReport_.QTY))
                );

        List<Predicate> predicateList = new ArrayList<>();

        if (request.getProjectId() != null) {
            Predicate projectIdPredicate = criteriaBuilder.equal(paymentRoot.get(PaymentsReport_.PROJECT).get(Project_.ID), request.getProjectId());
            predicateList.add(projectIdPredicate);
        }
        if (request.getUserId() != null) {
            Predicate UserIdPredicate = criteriaBuilder.equal(paymentRoot.get(PaymentsReport_.USER).get(User_.ID), request.getUserId());
            predicateList.add(UserIdPredicate);
        }
        if (request.getPaymentCategoryId() != null) {
            Predicate paymentCategoryIdPredicate = criteriaBuilder.equal(paymentRoot.get(PaymentsReport_.PAYMENT_CATEGORY).get(PaymentCategory_.ID), request.getPaymentCategoryId());
            predicateList.add(paymentCategoryIdPredicate);
        }
        if (request.getUserGroupId() != null) {
            Predicate userGroupIdPredicate = criteriaBuilder.equal(paymentRoot.get(PaymentsReport_.USER_GROUP).get(UserGroup_.ID), request.getUserGroupId());
            predicateList.add(userGroupIdPredicate);
        }
        if (request.getRoleId() != null) {
            Predicate roleIdPredicate = criteriaBuilder.equal(paymentRoot.get(PaymentsReport_.ROLE).get(Role_.ID), request.getRoleId());
            predicateList.add(roleIdPredicate);
        }
        if (request.getEmployerId() != null) {
            Predicate empolyerIdPredicate = criteriaBuilder.equal(paymentRoot.get(PaymentsReport_.EMPLOYER).get(Employer_.ID), request.getEmployerId());
            predicateList.add(empolyerIdPredicate);
        }

        Predicate datePredicate = criteriaBuilder.between(paymentRoot.get(PaymentsReport_.DATE), request.getStartDate(), request.getEndDate());
        predicateList.add(datePredicate);

        criteriaQuery.where(predicateList.toArray(new Predicate[]{}));

        TypedQuery<PaymentsReport> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
