package com.devlife.pf_sql_controller.exception;

import java.util.Arrays;

public class ProjectTypeNotFoundException extends RequestApiException {
    public ProjectTypeNotFoundException(String... s) {
        super("Project type not found \n " + Arrays.toString(s));
    }
}
