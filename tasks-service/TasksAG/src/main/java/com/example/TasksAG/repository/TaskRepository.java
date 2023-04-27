package com.example.TasksAG.repository;

import com.example.TasksAG.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * from task where client_id = :id  order by duedate",
    nativeQuery = true)
    List<Task> findAllByTaskByDateDesc(Long id);

}
