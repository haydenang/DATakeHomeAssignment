package com.doctoranywhere.Assignment.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Task")
@Getter @Setter @NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    private String title;
    private String description;
    private Boolean completed;

    public Task(String title, String desc, boolean completed){
        this.title = title;
        this.description = desc;
        this.completed = completed;
    }

}
