package com.mtv.erp.mybatis.daoimpl;

import com.mtv.erp.mybatis.mappers.DepartmentMapper;
import com.mtv.erp.mybatis.mappers.HoursMapper;
import com.mtv.erp.mybatis.mappers.PositionMapper;
import com.mtv.erp.mybatis.mappers.UserMapper;
import com.mtv.erp.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class DaoImplBase {

    protected SqlSession getSession() {
        MyBatisUtils.initSqlSessionFactory();
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class);
    }

    protected DepartmentMapper getDepartmentMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(DepartmentMapper.class);
    }

    protected PositionMapper getPositionMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(PositionMapper.class);
    }

    protected HoursMapper getHoursMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(HoursMapper.class);
    }

}