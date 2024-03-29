package com.devlife.pf_sql_controller.exception;

import java.util.Arrays;

public class ProjectNotFoundException extends RequestApiException {
    public ProjectNotFoundException(String... s) {
        super("Project not found \n " + Arrays.toString(s));
    }
}
