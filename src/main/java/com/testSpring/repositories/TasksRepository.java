package com.testSpring.repositories;

import com.testSpring.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TasksRepository extends CrudRepository<Task, Integer> {
    List<Task> findAll();
    List<Task> findByCategoryIdAndUserId(int categoryId, int userId);
    List<Task> findByImportantAndUserId(boolean important, int userId);
    Optional<Task> findByIdAndUserId(int id, int userId);
    @Transactional
    void deleteByIdAndUserId(int id, int userId);
}
