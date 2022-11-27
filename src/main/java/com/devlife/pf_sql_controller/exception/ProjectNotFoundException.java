package com.devlife.pf_sql_controller.exception;

public class ProjectNotFoundException extends IllegalArgumentException{
    public ProjectNotFoundException(String... s) {
        super("Project not found \n " + s.toString());
    }
}
