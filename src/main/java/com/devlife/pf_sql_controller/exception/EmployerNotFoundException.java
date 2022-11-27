package com.devlife.pf_sql_controller.exception;

public class EmployerNotFoundException extends IllegalArgumentException{
    public EmployerNotFoundException(String... s) {
        super("Employer not found \n " + s.toString());
    }
}
