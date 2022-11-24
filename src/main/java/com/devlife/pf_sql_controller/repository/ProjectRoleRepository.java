package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.dto.analyticFormDto.bdModel.ExpFormBd;
import com.devlife.pf_sql_controller.entity.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProjectRoleRepository extends JpaRepository<ProjectRole, Long> {

    @Query("select new com.devlife.pf_sql_controller.dto.analyticFormDto.bdModel.ExpFormBd(" +
            "pr.startDate," +
            "pr.endDate) " +
            "from ProjectRole pr " +
            "where " +
            "pr.user.id = :userId")
    List<ExpFormBd> getUserAllExp(
            @Param("userId") Long userId
    );

    @Query("select new com.devlife.pf_sql_controller.dto.analyticFormDto.bdModel.ExpFormBd(" +
            "pr.startDate," +
            "pr.endDate) " +
            "from ProjectRole pr " +
            "where " +
            "pr.user.id = :userId " +
            "and pr.role.id = :roleId")
    List<ExpFormBd> getUserExpByRole(
            @Param("userId") Long userId,
            @Param("roleId") Long roleId
    );

    @Query("select new com.devlife.pf_sql_controller.dto.analyticFormDto.bdModel.ExpFormBd(" +
            "pr.startDate," +
            "pr.endDate) " +
            "from ProjectRole pr " +
            "where " +
            "pr.user.id = :userId " +
            "and pr.project.id = :projectId")
    List<ExpFormBd> getUserExpByProject(
            @Param("userId") Long userId,
            @Param("projectId") Long projectId
    );

    @Query("select new com.devlife.pf_sql_controller.dto.analyticFormDto.bdModel.ExpFormBd(" +
            "pr.startDate," +
            "pr.endDate) " +
            "from ProjectRole pr inner join Project p " +
            "on pr.project.id = p.id " +
            "where " +
            "pr.user.id = :userId " +
            "and p.employer.id = :employerId")
    List<ExpFormBd> getUserExpByEmployer(
            @Param("userId") Long userId,
            @Param("employerId") Long employerId
    );

    @Query("select pr from ProjectRole pr " +
            "where pr.project.id = :projectId " +
            "and pr.role.id = :roleId " +
            "and pr.user.id = :userId ")
    Set<ProjectRole> userAndRoleAlreadyExistInProject(
            @Param("projectId") Long projectId,
            @Param("roleId") Long roleId,
            @Param("userId") Long userId
    );

}
