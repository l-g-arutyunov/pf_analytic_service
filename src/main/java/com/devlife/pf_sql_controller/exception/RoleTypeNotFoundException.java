package com.devlife.pf_sql_controller.exception;

import java.util.Arrays;

public class RoleTypeNotFoundException extends IllegalArgumentException {
    public RoleTypeNotFoundException(String... s) {
        super("Project type not found \n " + Arrays.toString(s));
    }
}
