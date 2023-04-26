package com.example.TasksAG.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
public class TaskDTO {
    private String subject;
    private Date date;
    private String status;

}
