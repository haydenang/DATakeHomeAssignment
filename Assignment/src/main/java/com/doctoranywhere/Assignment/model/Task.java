package com.doctoranywhere.Assignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "Task")
@Getter @Setter @NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    @Setter(AccessLevel.PROTECTED)
    private Long id;
    @NotNull(message = "Task Title should not be empty")
    private String title;
    @NotNull(message = "Task Description should not be empty")
    private String description;
    @NotNull(message = "is the Task completed?")
    private Boolean completed;
    //For when updating task to be completed
    public Task(String title, String desc, boolean completed){
        this.title = title;
        this.description = desc;
        this.completed = completed;
    }
    //For when inputting new task that is assumed to be not completed
    public Task(String title, String desc){
        this.title = title;
        this.description = desc;
        this.completed = false;
    }

}
