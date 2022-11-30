package com.devlife.pf_sql_controller.exception;

import java.util.Arrays;

public class RoleNotFoundException extends IllegalArgumentException{
    public RoleNotFoundException(String... s) {
        super("Role not found \n " + Arrays.toString(s));
    }
}
