package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.Department;

public interface DepartmentDao {
    Department getById(int id) throws ServerException;
}
