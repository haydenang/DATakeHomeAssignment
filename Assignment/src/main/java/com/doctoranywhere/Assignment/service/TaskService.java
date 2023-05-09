package com.doctoranywhere.Assignment.service;

import com.doctoranywhere.Assignment.model.Task;
import com.doctoranywhere.Assignment.repository.TaskRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Getter @Setter
public class TaskService {
    private List<Task> taskList;
    private TaskRepo taskRepo;
    @Autowired
    public TaskService(TaskRepo taskRepo){
        taskList = new ArrayList<>();
        this.taskRepo = taskRepo;
    }

    public Optional<Task> getTask(long id){
        Optional optional = Optional.empty();
        this.taskList = taskRepo.findAll();
        for (Task task:taskList) {
            if (task.getId() == id) {
                optional = Optional.of(task);
                return optional;
            }
        }
        return optional;
    }

    public List<Task> getAllTask(){
        return taskRepo.findAll();
    }

    public void uploadTask(Task task){
        taskRepo.save(task);
    }

    public void updateTask(long id, Task task){
        Optional<Task> retrievedTask = taskRepo.findById(id);
        if(retrievedTask.isPresent()){
            Task toBeUpdatedTask = retrievedTask.get();
            toBeUpdatedTask.setTitle(task.getTitle());
            toBeUpdatedTask.setDescription(task.getDescription());
            toBeUpdatedTask.setCompleted(task.getCompleted());
            taskRepo.save(toBeUpdatedTask);
        }
    }

    public void deleteTask(long id){
        taskRepo.deleteById(id);
    }

}
