package com.mtv.erp.response;

import java.util.List;
import java.util.Objects;

public class UsersGetFromDateDtoResponse {
    private List<UserGetFromDateDtoResponse> userList;

    public UsersGetFromDateDtoResponse(List<UserGetFromDateDtoResponse> users) {
        this.userList = users;
    }

    public List<UserGetFromDateDtoResponse> getUserList() {
        return userList;
    }

    public void setUserList(List<UserGetFromDateDtoResponse> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersGetFromDateDtoResponse)) return false;
        UsersGetFromDateDtoResponse that = (UsersGetFromDateDtoResponse) o;
        return Objects.equals(getUserList(), that.getUserList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserList());
    }
}
