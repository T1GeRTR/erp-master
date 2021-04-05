package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.Hours;
import com.mtv.erp.model.LaborRecord;

import java.time.LocalDate;
import java.util.List;

public interface HoursDao {
    List<LaborRecord> insertAll(List<LaborRecord> list) throws ServerException;

    List<Hours> insertAllHours(List<Hours> list) throws ServerException;

    List<LaborRecord> getFromDate(LocalDate from, LocalDate to) throws ServerException;

    List<Hours> getFromDateHours(LocalDate from, LocalDate to) throws ServerException;

    boolean delete(int id) throws ServerException;

    boolean deleteHours(int id) throws ServerException;

    boolean update(LaborRecord laborRecord) throws ServerException;

    boolean updateHours(Hours hours) throws ServerException;

    boolean saveChanges(Hours hours) throws ServerException;

    boolean insertChanges(Hours hours) throws ServerException;
}
