package com.example.TasksAG.repository;

import com.example.TasksAG.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * from task where client_id = :id  order by duedate desc",
    nativeQuery = true)
    List<Task> findAllByTaskByDateDesc(Long id);

    @Query(value = "select * from task left join client on task.client_id=client.id order by duedate desc",
            nativeQuery = true)
    List<Task> findAllTasks();

}
