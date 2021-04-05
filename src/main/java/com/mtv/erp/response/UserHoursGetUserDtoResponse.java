package com.mtv.erp.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mtv.erp.model.User;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(value = "handler")
public class UserHoursGetUserDtoResponse {
    private int id;
    private User user;
    private LocalDate date;
    private float hours;
    private String taskId;
    private String taskTitle;
    private String projectId;
    private String projectTitle;

    public UserHoursGetUserDtoResponse() {
    }

    public UserHoursGetUserDtoResponse(int id, User user, LocalDate date, float hours, String taskId, String taskTitle, String projectId, String projectTitle) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.hours = hours;
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
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
        if (!(o instanceof UserHoursGetUserDtoResponse)) return false;
        UserHoursGetUserDtoResponse that = (UserHoursGetUserDtoResponse) o;
        return getId() == that.getId() && getHours() == that.getHours() && Objects.equals(getUser(), that.getUser()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getTaskId(), that.getTaskId()) && Objects.equals(getTaskTitle(), that.getTaskTitle()) && Objects.equals(getProjectId(), that.getProjectId()) && Objects.equals(getProjectTitle(), that.getProjectTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDate(), getHours(), getTaskId(), getTaskTitle(), getProjectId(), getProjectTitle());
    }
}
