package com.mtv.erp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Hours {
    private int id;
    private User user;
    private LocalDate date;
    private String hours;
    private boolean saved;
    private int type;

    public Hours(int id, User user, LocalDate date, String hours, boolean saved, int type) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.hours = hours;
        this.saved = saved;
        this.type = type;
    }

    public Hours(int id, User user, LocalDate date, String hours, int type) {
        this(id, user, date, hours, false, type);
    }

    public Hours() {
        setType(1);
    }

    public Hours(String hours, LocalDate date, User user, int type) {
        setHours(hours);
        setDate(date);
        setUser(user);
        setType(type);
    }

    public Hours(int id, String hours) {
        this(id, null, null, hours, 1);
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

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hours)) return false;
        Hours hours1 = (Hours) o;
        return getId() == hours1.getId() &&
                getHours() == hours1.getHours() &&
                isSaved() == hours1.isSaved() &&
                Objects.equals(getUser(), hours1.getUser()) &&
                Objects.equals(getDate(), hours1.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDate(), getHours(), isSaved());
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
