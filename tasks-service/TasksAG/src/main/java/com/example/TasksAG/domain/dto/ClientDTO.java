package com.example.TasksAG.domain.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ClientDTO {
    private String email;
    private String username;
    private String password;
}
