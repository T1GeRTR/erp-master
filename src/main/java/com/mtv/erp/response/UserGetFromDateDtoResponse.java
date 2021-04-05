package com.mtv.erp.response;

import java.util.List;
import java.util.Objects;


public class UserGetFromDateDtoResponse {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private List<HoursGetUserDtoResponse> hours;
    private int monthLen;
    private float sumHours;

    public UserGetFromDateDtoResponse(int id, String firstname, String lastname, String email, List<HoursGetUserDtoResponse> hours, int monthLen) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hours = hours;
        this.monthLen = monthLen;
        setSumHours();
    }

    public UserGetFromDateDtoResponse() {

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

    public List<HoursGetUserDtoResponse> getHours() {
        return hours;
    }

    public void setHours(List<HoursGetUserDtoResponse> hours) {
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
        if (!(o instanceof UserGetFromDateDtoResponse)) return false;
        UserGetFromDateDtoResponse that = (UserGetFromDateDtoResponse) o;
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

    public float getSumHours() {
        return sumHours;
    }

    private void setSumHours() {
        for (HoursGetUserDtoResponse hours : hours) {
            try {
                sumHours += Float.parseFloat(hours.getHours());
            } catch (NumberFormatException ex){
                continue;
            }
        }
    }
}
