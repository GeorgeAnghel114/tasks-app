package com.example.TasksAG.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class SearchDTO {
    private Long clientId;
    private String subject;
    private LocalDate duedate;
    private String status;
}
