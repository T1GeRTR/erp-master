package com.mtv.erp.mybatis.daoimpl;

import com.mtv.erp.dao.HoursDao;
import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.Hours;
import com.mtv.erp.model.LaborRecord;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class HoursDaoImpl extends DaoImplBase implements HoursDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HoursDaoImpl.class);

    @Override
    public List<LaborRecord> insertAll(List<LaborRecord> list) throws ServerException {
        LOGGER.debug("DAO insert all");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHoursMapper(sqlSession).insertAll(list);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't insert user_hours {}", list, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
            sqlSession.commit();
        }
        return list;
    }

    @Override
    public List<Hours> insertAllHours(List<Hours> list) throws ServerException {
        LOGGER.debug("DAO insert all");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHoursMapper(sqlSession).insertAllHours(list);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't insert hours {}", list, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
            sqlSession.commit();
        }
        return list;
    }

    @Override
    public List<LaborRecord> getFromDate(LocalDate from, LocalDate to) throws ServerException {
        LOGGER.debug("DAO get from date interval");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getHoursMapper(sqlSession).getFromDate(from, to);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get user_hours from date {}, {}", from, to, e);
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
        }
    }

    @Override
    public List<Hours> getFromDateHours(LocalDate from, LocalDate to) throws ServerException {
        LOGGER.debug("DAO get from date interval");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getHoursMapper(sqlSession).getFromDateHours(from, to);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get hours from date {}, {}", from, to, e);
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
        }
    }

    @Override
    public boolean delete(int id) throws ServerException {
        LOGGER.debug("DAO delete user_hours");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHoursMapper(sqlSession).delete(id);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't delete user_hours {}", id, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_DELETE_HOURS);
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public boolean deleteHours(int id) throws ServerException {
        LOGGER.debug("DAO delete hours");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHoursMapper(sqlSession).deleteHours(id);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't delete hours {}", id, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_DELETE_HOURS);
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public boolean update(LaborRecord laborRecord) throws ServerException {
        LOGGER.debug("DAO update user_hours");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHoursMapper(sqlSession).update(laborRecord);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't update user_hours {}", laborRecord, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_UPDATE_HOURS);
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public boolean updateHours(Hours hours) throws ServerException {
        LOGGER.debug("DAO update hours");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHoursMapper(sqlSession).updateHours(hours);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't update hours {}", hours, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_UPDATE_HOURS);
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public boolean saveChanges(Hours hours) throws ServerException {
        LOGGER.debug("DAO save changed hours");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHoursMapper(sqlSession).saveChanges(hours);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't save changed hours {}", hours, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_UPDATE_HOURS);
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public boolean insertChanges(Hours hours) throws ServerException {
        LOGGER.debug("DAO insert changed hours");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHoursMapper(sqlSession).insertChanges(hours);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't insert changed hours {}", hours, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_UPDATE_HOURS);
            }
            sqlSession.commit();
        }
        return true;
    }
}
