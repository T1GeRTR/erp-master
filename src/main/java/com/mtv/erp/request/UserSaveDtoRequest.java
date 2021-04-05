package com.mtv.erp.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mtv.erp.model.Hours;
import com.mtv.erp.model.LaborRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(value = "handler")
public class UserSaveDtoRequest {
    private int id;
    private String firstname;
    private String lastname;
    private String email;

    public UserSaveDtoRequest(int id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public UserSaveDtoRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSaveDtoRequest)) return false;
        UserSaveDtoRequest that = (UserSaveDtoRequest) o;
        return getId() == that.getId() && Objects.equals(getFirstname(), that.getFirstname()) && Objects.equals(getLastname(), that.getLastname()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail());
    }
}
