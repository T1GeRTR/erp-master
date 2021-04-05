package com.mtv.erp.request;

import java.util.List;
import java.util.Objects;


public class UserGetFromDateDtoRequest {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private List<HoursGetUserDtoRequest> hours;
    private int monthLen;
    private String sumHours;

    public UserGetFromDateDtoRequest(int id, String firstname, String lastname, String email, List<HoursGetUserDtoRequest> hours, int monthLen) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hours = hours;
        this.monthLen = monthLen;
        setSumHours();
    }

    public UserGetFromDateDtoRequest() {

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

    public List<HoursGetUserDtoRequest> getHours() {
        return hours;
    }

    public void setHours(List<HoursGetUserDtoRequest> hours) {
        this.hours = hours;
    }

    public int getMonthLen() {
        return monthLen;
    }

    public void setMonthLen(int monthLen) {
        this.monthLen = monthLen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGetFromDateDtoRequest)) return false;
        UserGetFromDateDtoRequest that = (UserGetFromDateDtoRequest) o;
        return getId() == that.getId() &&
                getMonthLen() == that.getMonthLen() &&
                Objects.equals(getFirstname(), that.getFirstname()) &&
                Objects.equals(getLastname(), that.getLastname()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getHours(), that.getHours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail(), getHours(), getMonthLen());
    }

    public String getSumHours() {
        return sumHours;
    }

    private void setSumHours() {
        for (HoursGetUserDtoRequest hours : hours) {
            sumHours += hours.getHours();
        }
    }
}
