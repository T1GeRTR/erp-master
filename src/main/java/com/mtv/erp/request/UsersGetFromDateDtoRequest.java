package com.mtv.erp.request;

import java.util.List;
import java.util.Objects;

public class UsersGetFromDateDtoRequest {
    private List<UserGetFromDateDtoRequest> userList;

    public UsersGetFromDateDtoRequest(List<UserGetFromDateDtoRequest> users) {
        this.userList = users;
    }

    public List<UserGetFromDateDtoRequest> getUserList() {
        return userList;
    }

    public void setUserList(List<UserGetFromDateDtoRequest> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersGetFromDateDtoRequest)) return false;
        UsersGetFromDateDtoRequest that = (UsersGetFromDateDtoRequest) o;
        return Objects.equals(getUserList(), that.getUserList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserList());
    }
}
