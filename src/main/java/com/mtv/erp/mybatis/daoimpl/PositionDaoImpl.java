package com.mtv.erp.mybatis.daoimpl;

import com.mtv.erp.dao.PositionDao;
import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.Position;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionDaoImpl extends DaoImplBase implements PositionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(PositionDaoImpl.class);

    @Override
    public Position getById(int id) throws ServerException {
        LOGGER.debug("DAO getById");
        try (SqlSession sqlSession = getSession()) {
            try {
                Position position = getPositionMapper(sqlSession).getById(id);
                if (position == null) {
                    throw new ServerException(ErrorCode.CANT_GET_USER_BY_ID, id);
                }
                return position;
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get by userId: {}, {}", id, e);
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
        }
    }
}
