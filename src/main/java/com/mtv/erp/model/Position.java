package com.mtv.erp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private int id;
    private String name;
    private List<User> users;
    private Department department;

    public Position(int id, String name, List<User> users, Department department) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.department = department;
    }

    public Position(){}

    public Position(int id) {
        this(id, "", new ArrayList<>(), new Department(0));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Department getDepartament() {
        return department;
    }

    public void setDepartament(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getId() == position.getId() && Objects.equals(getName(), position.getName()) && Objects.equals(getUsers(), position.getUsers()) && Objects.equals(getDepartament(), position.getDepartament());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUsers(), getDepartament());
    }
}
