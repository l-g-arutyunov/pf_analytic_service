package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Get set of project by userId
     * @param userId user id
     * @return set of project
     */
    @Query("select project from Project project " +
            "inner join UserGroupUser ugu on project.userGroup.id = ugu.userGroupUserId.userGroupId " +
            "where ugu.userGroupUserId.userId = :userId")
    Page<Project> getProjectsByUserId(@Param("userId") Long userId, Pageable pageable);

    /**
     * Get count rows by userId
     * @param userId user id
     */
    @Query("select count(project) from Project project " +
            "inner join UserGroupUser ugu on project.userGroup.id = ugu.userGroupUserId.userGroupId " +
            "where ugu.userGroupUserId.userId = :userId")
    Long getCountByUserId(@Param("userId") Long userId);

}
