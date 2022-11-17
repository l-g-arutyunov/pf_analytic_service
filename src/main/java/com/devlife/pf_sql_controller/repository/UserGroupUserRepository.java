package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.entity.UserGroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupUserRepository extends JpaRepository<UserGroupUser, Long> {

    /**
     * If user exist in the user group return true else false
     * @param userId
     * @param userGroupId
     * @return true or false
     */
    @Query(value = "select count(ugu) > 0 from UserGroupUser ugu " +
            "where ugu.userGroupUserId.userId = :userId " +
            "and ugu.userGroupUserId.userGroupId = :userGroupId")
    Boolean checkUserExistInUserGroup(@Param("userId") Long userId,
                                      @Param("userGroupId") Long userGroupId);
}
