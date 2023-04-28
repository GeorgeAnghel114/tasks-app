package com.example.TasksAG.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class TaskDTO {
    private Long taskId;
    private String subject;
    private LocalDateTime date;
    private String status;
    private String clientName;
    private Long clientId;
}
