package com.github.security.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private boolean enabled;
}
