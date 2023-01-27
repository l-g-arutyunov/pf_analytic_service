package com.devlife.pf_sql_controller.exception;

import java.util.Arrays;

public class ProjectRoleNotFoundException extends RequestApiException {
    public ProjectRoleNotFoundException(String... s) {
        super("Project role not found \n " + Arrays.toString(s));
    }
}
