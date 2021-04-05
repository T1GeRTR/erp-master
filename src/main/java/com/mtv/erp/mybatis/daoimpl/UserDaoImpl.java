package com.mtv.erp.mybatis.daoimpl;

import com.mtv.erp.dao.UserDao;
import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl extends DaoImplBase implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User insert(User user) throws ServerException {
        LOGGER.debug("DAO insert");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(user);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't insert user {}", user, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
            sqlSession.commit();
            user = getUserMapper(sqlSession).getById(user.getId());
        }
        return user;
    }

    @Override
    public User save(User user) throws ServerException {
        LOGGER.debug("DAO save");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).save(user);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't save user {}", user, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
            sqlSession.commit();
            user = getUserMapper(sqlSession).getById(user.getId());
        }
        return user;
    }

    @Override
    public boolean update(User user) throws ServerException {
        LOGGER.debug("DAO update");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(user);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't update user {}", user, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_UPDATE_USER);
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws ServerException {
        LOGGER.debug("DAO delete");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).delete(id);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't delete userId {}", id, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_DELETE_USER, id);
            }
            sqlSession.commit();
        }
        return true;
    }


    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User getFromDateById(LocalDate from, LocalDate to, int id) throws ServerException {
        LOGGER.debug("DAO get user by id");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getByIdWithUserHours(id, from, to);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get user by id {}", id, e);
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
        }
    }

    @Override
    public List<User> getFromDate(LocalDate from, LocalDate to) throws ServerException {
        LOGGER.debug("DAO get all user");
        try (SqlSession sqlSession = getSession()) {
            try {
                List<User> users = new ArrayList<>();
                for (User user : getUserMapper(sqlSession).getAll()) {
                    User userFromBase = getUserMapper(sqlSession).getByIdWithHours(user.getId(), from, to);
                    if(userFromBase!=null){
                        users.add(userFromBase);
                    }else {
                        users.add(user);
                    }
                }
                return users;
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get all user with hours", e);
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
        }
    }

    @Override
    public List<User> getAll() throws ServerException {
        LOGGER.debug("DAO getAll");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getAll();
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get all users", e);
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
        }
    }

    @Override
    public List<User> insertAll(List<User> users) throws ServerException {
        LOGGER.debug("DAO insert all");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insertAll(users);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't insert users {}", users, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
            sqlSession.commit();
        }
        return users;
    }

}
