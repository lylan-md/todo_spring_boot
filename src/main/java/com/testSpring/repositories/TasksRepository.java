package com.testSpring.repositories;

import com.testSpring.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TasksRepository extends CrudRepository<Task, Integer> {
    List<Task> findAll();
    List<Task> findByCategoryId(int CategoryId);
    List<Task> findByImportant(boolean important);
}
