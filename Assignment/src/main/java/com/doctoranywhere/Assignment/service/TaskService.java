package com.doctoranywhere.Assignment.service;

import com.doctoranywhere.Assignment.exception.ResourceNotFoundException;
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
    private TaskRepo taskRepo;
    @Autowired
    public TaskService(TaskRepo taskRepo){
        this.taskRepo = taskRepo;
    }

    public Optional<Task> getTask(long id){
        if(taskRepo.existsById(id)){
            return taskRepo.findById(id);
        }
        else{
            throw new ResourceNotFoundException("No task with given id "+id);
        }
    }

    public List<Task> getAllTask(){
        return taskRepo.findAll();
    }

    public Task uploadTask(Task task){
        return taskRepo.save(task);
    }

    public Task updateTask(long id, Task task){
        Optional<Task> retrievedTask = taskRepo.findById(id);
        if(retrievedTask.isPresent()){
            Task toBeUpdatedTask = retrievedTask.get();
            toBeUpdatedTask.setTitle(task.getTitle());
            toBeUpdatedTask.setDescription(task.getDescription());
            toBeUpdatedTask.setCompleted(task.getCompleted());
            return taskRepo.save(toBeUpdatedTask);
        }
        else{
            return uploadTask(task);
        }
    }

    public void deleteTask(long id){
        if(taskRepo.existsById(id)){
            taskRepo.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("No task with given id "+id);
        }
    }

}
