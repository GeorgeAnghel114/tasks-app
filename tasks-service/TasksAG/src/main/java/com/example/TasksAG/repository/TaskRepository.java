package com.example.TasksAG.repository;

import com.example.TasksAG.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * from task where client_id = :id  order by duedate desc",
            nativeQuery = true)
    List<Task> findAllByTaskByDateDesc(Long id);

    @Query(value = "select * from task left join client on task.client_id=client.id order by  duedate desc",
            nativeQuery = true)
    List<Task> findAllTasks();

    @Query(value = "SELECT t FROM Task t WHERE (:subject IS NULL OR LOWER(t.subject) like LOWER(concat('%', cast(:subject as string ),'%'))) " +
            "AND (cast(:duedate as date) IS NULL OR t.duedate > :duedate)" +
            "AND (:clientId is null or t.client.id=:clientId )")
    List<Task> findBySearch(@Param("subject") String subject,
                            @Param("duedate") LocalDate duedate,
                            @Param("clientId") Long clientId);

}