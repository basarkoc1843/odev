package com.example.demo.dto;

import lombok.Data;

@Data
public class ChangePassword {
    private String username;
    private String password1;
    private String password2;
}
