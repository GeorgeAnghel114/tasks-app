package com.example.TasksAG.domain.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientDTO {
    private String email;
    private String username;
    private String password;
}
