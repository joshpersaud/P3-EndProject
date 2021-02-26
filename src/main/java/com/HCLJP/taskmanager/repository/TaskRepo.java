package com.HCLJP.taskmanager.repository;

import com.HCLJP.taskmanager.entity.Task;
import com.HCLJP.taskmanager.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {
    @Override
    boolean existsById(Long id);
    Iterable<Task> findAllByUser(User user);
    Task findTaskById(Long id);
}
