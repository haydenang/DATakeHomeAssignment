package com.doctoranywhere.Assignment.controller;
import com.doctoranywhere.Assignment.model.Task;
import com.doctoranywhere.Assignment.repository.TaskRepo;
import com.doctoranywhere.Assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RouteController {
    private TaskService taskService;

    @Autowired
    public RouteController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping(path="/")
    public String getTask(){
        return "Hello";
    }
    @GetMapping(path="/tasks/{id}")
    public String getTask(@PathVariable long id){
        Optional task = taskService.getTask(id);
        if (task.isPresent()){
            Task retrievedTask =  (Task) task.get();
            return retrievedTask.getTitle();
        }
        return "Can't find";
    }
    @PostMapping(path = "/tasks")
    public String saveTask(@RequestBody Task task){
        taskService.uploadTask(task);
        return "Saved!";
    }

    @GetMapping(path="/tasks")
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }
    @PutMapping(path="/tasks/{id}")
    public String updateTask(@PathVariable long id, @RequestBody Task task){
        taskService.updateTask(id,task);
        return "Updated!";
    }
    @DeleteMapping(path = "/tasks/{id}")
    public String deleteTask(@PathVariable long id){
        taskService.deleteTask(id);
        return "Deleted!";
    }

}
