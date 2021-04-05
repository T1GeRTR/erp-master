package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserDao {
    User insert (User user) throws ServerException;

    User save (User user) throws ServerException;

    boolean delete (int id) throws ServerException;

    boolean update (User user) throws ServerException;

    User getById (int id);

    User getFromDateById(LocalDate from, LocalDate to, int id) throws ServerException;

    List<User> getFromDate(LocalDate from, LocalDate to) throws ServerException;

    List<User> getAll () throws ServerException;

    List<User> insertAll(List<User> users) throws ServerException;

    //List<User> getByDepartamentId(int id);

    //List<User> getByPositionId(int id);

    //User getHeadByDepartamentId(int id);


}
