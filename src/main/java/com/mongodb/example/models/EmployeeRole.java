package com.mongodb.example.models;


public enum EmployeeRole {
    SOCIAL("SOCIAL"),
    TECHNICAL("TECHNICAL");
    final String role;
    EmployeeRole(String role) {
        this.role = role;
    }

}
