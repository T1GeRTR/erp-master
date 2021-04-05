package com.mtv.erp.mybatis.daoimpl;

import com.mtv.erp.dao.DepartmentDao;
import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.Department;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartmentDaoImpl extends DaoImplBase implements DepartmentDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoImpl.class);

    @Override
    public Department getById(int id) throws ServerException {
        LOGGER.debug("DAO getById");
        try (SqlSession sqlSession = getSession()) {
            try {
                Department department = getDepartmentMapper(sqlSession).getById(id);
                if (department == null) {
                    throw new ServerException(ErrorCode.CANT_GET_USER_BY_ID, id);
                }
                return department;
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get by userId: {}, {}", id, e);
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
        }
    }
}
