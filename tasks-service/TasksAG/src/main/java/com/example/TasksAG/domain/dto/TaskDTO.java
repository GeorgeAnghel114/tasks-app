package com.example.TasksAG.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@Builder
public class TaskDTO {
    private Long taskId;
    private String subject;
    private LocalDate duedate;
    private String status;
    private boolean isDeleted;
    private String clientEmail;
    private Long clientId;

//    public TaskDTO(String subject, LocalDate duedate, String status, String clientEmail) {
//        this.subject = subject;
//        this.duedate = duedate;
//        this.status = status;
//        this.clientEmail = clientEmail;
//    }
}
