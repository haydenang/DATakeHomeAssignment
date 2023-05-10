package com.doctoranywhere.Assignment.controller;
import com.doctoranywhere.Assignment.model.Task;
import com.doctoranywhere.Assignment.repository.TaskRepo;
import com.doctoranywhere.Assignment.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Task> saveTask(@RequestBody @Valid Task task){
        return new ResponseEntity<>(taskService.uploadTask(task), HttpStatus.CREATED);
    }

    @GetMapping(path="/tasks")
    public ResponseEntity<List<Task>> getAllTask(){
        return new ResponseEntity<>(taskService.getAllTask(), HttpStatus.OK);
    }
    @PutMapping(path="/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody @Valid Task task){
        return new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);
    }
    @DeleteMapping(path = "/tasks/{id}")
    public String deleteTask(@PathVariable long id){
        taskService.deleteTask(id);
        return "Deleted!";
    }

}
