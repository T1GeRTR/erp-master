package com.mtv.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(value = "handler")
public class LaborRecord {
    private int id;
    private User user;
    private LocalDate date;
    private float hours;
    private int taskId;
    private String taskTitle;
    private int projectId;
    private String projectTitle;

    public LaborRecord() {
    }

    public LaborRecord(int id, User user, LocalDate date, float hours, int taskId, String taskTitle, int projectId, String projectTitle) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.hours = hours;
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
    }

    public LaborRecord(User user, LocalDate date, float hours, int taskId, String taskTitle, int projectId, String projectTitle) {
        this(0, user, date, hours, taskId, taskTitle, projectId, projectTitle);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LaborRecord)) return false;
        LaborRecord that = (LaborRecord) o;
        return getTaskId() == that.getTaskId() && getProjectId() == that.getProjectId() && Objects.equals(getDate(), that.getDate()) && Objects.equals(getTaskTitle(), that.getTaskTitle()) && Objects.equals(getProjectTitle(), that.getProjectTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getTaskId(), getTaskTitle(), getProjectId(), getProjectTitle());
    }
}
