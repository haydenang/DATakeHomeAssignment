package com.doctoranywhere.Assignment.repository;

import com.doctoranywhere.Assignment.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Long> {
}
