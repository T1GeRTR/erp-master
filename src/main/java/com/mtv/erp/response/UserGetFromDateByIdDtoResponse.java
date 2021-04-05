package com.mtv.erp.response;

import java.util.List;
import java.util.Objects;


public class UserGetFromDateByIdDtoResponse {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private List<UserHoursGetUserDtoResponse> userHours;
    private List<HoursGetUserDtoResponse> hours;
    private int monthLen;
    private String sumHours;

    public UserGetFromDateByIdDtoResponse(int id, String firstname, String lastname, String email, List<UserHoursGetUserDtoResponse> userHours, List<HoursGetUserDtoResponse> hours, int monthLen) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.userHours = userHours;
        this.hours = hours;
        this.monthLen = monthLen;
        setSumHours();
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

    public List<UserHoursGetUserDtoResponse> getUserHours() {
        return userHours;
    }

    public void setUserHours(List<UserHoursGetUserDtoResponse> userHours) {
        this.userHours = userHours;
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
        if (!(o instanceof UserGetFromDateByIdDtoResponse)) return false;
        UserGetFromDateByIdDtoResponse that = (UserGetFromDateByIdDtoResponse) o;
        return getId() == that.getId() &&
                getMonthLen() == that.getMonthLen() &&
                Objects.equals(getFirstname(), that.getFirstname()) &&
                Objects.equals(getLastname(), that.getLastname()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getUserHours(), that.getUserHours()) &&
                Objects.equals(getHours(), that.getHours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail(), getUserHours(), getHours(), getMonthLen());
    }

    public String getSumHours() {
        return sumHours;
    }

    private void setSumHours() {
        for (HoursGetUserDtoResponse hours : hours) {
            sumHours += hours.getHours();
        }
    }
}
