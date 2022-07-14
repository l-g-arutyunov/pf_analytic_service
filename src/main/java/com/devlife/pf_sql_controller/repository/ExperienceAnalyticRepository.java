//package com.devlife.pf_sql_controller.repository;
//
//import com.devlife.pf_sql_controller.dto.analyticFormDto.bdModel.ExpModel;
//import com.devlife.pf_sql_controller.dto.analyticFormDto.request.ExpFormRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@RequiredArgsConstructor
//@Repository
//public class ExperienceAnalyticRepository {
//    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
//
//    public ExpModel getUserAllExp(ExpFormRequest request){
//        Map<String, Object> params = Collections.singletonMap("userId", request.getUserId());
//
//        return namedParameterJdbcOperations.queryForObject(
//                "select " +
////                        "project_role.id, " +
//                        "project_role.user_id as userId, " +
////                        "project_role.role_id, " +
////                        "project_role.project_id, " +
//                        "SUM(YEAR(project_role.end_date) - YEAR(project_role.start_date)) as years , " +
//                        "SUM(MONTH(project_role.end_date) - MONTH(project_role.start_date)) as months , " +
//                        "SUM(DAY(project_role.end_date) - DAY(project_role.start_date)) as days" +
//                "from project_role " +
//                        "where " +
//                        "project_role.user_id = : userId " +
//                        "group by project_role.user_id",
//                params, new ExpMapper()
//        );
//    }
//
//    public ExpModel getUserAllExpByRole(ExpFormRequest request){
//        Map<String, Object> params = new HashMap<>();
//        params.put("userId", request.getUserId());
//        params.put("roleId", request.getRoleId());
//
//        return namedParameterJdbcOperations.queryForObject(
//                "select " +
////                        "project_role.id, " +
//                        "project_role.user_id as userId, " +
//                        "project_role.role_id as roleId, " +
////                        "project_role.project_id, " +
//                        "SUM(YEAR(project_role.end_date) - YEAR(project_role.start_date)) as years , " +
//                        "SUM(MONTH(project_role.end_date) - MONTH(project_role.start_date)) as months , " +
//                        "SUM(DAY(project_role.end_date) - DAY(project_role.start_date)) as days" +
//                        "from project_role " +
//                        "where " +
//                        "project_role.user_id = : userId and " +
//                        "project_role.role_id = : roleId " +
//                        "group by project_role.user_id, project_role.role_id",
//                params, new ExpMapper()
//        );
//    }
//
//    public ExpModel getUserAllExpByProject(Long userId, Long projectId){
//        Map<String, Object> params = new HashMap<>();
//        params.put("userId", userId);
//        params.put("projectId", projectId);
//
//        return namedParameterJdbcOperations.queryForObject(
//                "select " +
////                        "project_role.id, " +
//                        "project_role.user_id as userId, " +
////                        "project_role.role_id as roleId, " +
//                        "project_role.project_id as projectId, " +
//                        "SUM(YEAR(project_role.end_date) - YEAR(project_role.start_date)) as years , " +
//                        "SUM(MONTH(project_role.end_date) - MONTH(project_role.start_date)) as months , " +
//                        "SUM(DAY(project_role.end_date) - DAY(project_role.start_date)) as days" +
//                        "from project_role " +
//                        "where " +
//                        "project_role.user_id = : userId and " +
//                        "project_role.role_id = : projectId " +
//                        "group by project_role.user_id, project_role.projectId",
//                params, new ExpMapper()
//        );
//    }
//
//    private static class ExpMapper implements RowMapper<ExpModel> {
//        @Override
//        public ExpModel mapRow(ResultSet resultSet, int i) throws SQLException {
//            Long userId = resultSet.getLong("userId");
//            Long roleId = resultSet.getLong("roleId");
//            Long projectId = resultSet.getLong("projectId");
//            Integer years = resultSet.getInt("years");
//            Integer months = resultSet.getInt("months");
//            Integer days = resultSet.getInt("days");
//            return new ExpModel(days, months, years, userId, roleId, projectId);
//        }
//}
//
//}
