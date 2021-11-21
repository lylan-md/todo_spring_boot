package com.testSpring.entities;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String description;
    private int categoryId;
    private String plannedOn;
    private boolean isDone = false;
    private boolean important = false;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setPlannedOn(String plannedOn) {
        this.plannedOn = plannedOn;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getPlannedOn() {
        return plannedOn;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean isImportant() {
        return important;
    }
}
