package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.Position;

public interface PositionDao {
    Position getById(int id) throws ServerException;
}
