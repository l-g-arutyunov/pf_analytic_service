package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.entity.Employer;
import com.devlife.pf_sql_controller.entity.QEmployer;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployerRepository extends
        JpaRepository<Employer, Long>,
        QuerydslPredicateExecutor<Employer>,
        QuerydslBinderCustomizer<QEmployer> {
    @Override
    default void customize(QuerydslBindings bindings, QEmployer root) {
        bindings.bind(root.name).
                all((path, values) -> ExpressionProviderFactory.getPredicate(path, values));
        //bindings.bind(root.userGroup.id)
    }

    @Query("select count(employer) from Employer employer " +
            "where employer.userGroup.id in :userGroupIdSet")
    Long countAllByUserGroupId(@Param("userId") Set<Long> userGroupIdSet);

}
