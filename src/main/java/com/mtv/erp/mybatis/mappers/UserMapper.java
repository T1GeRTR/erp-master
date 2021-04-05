package com.mtv.erp.mybatis.mappers;

import com.mtv.erp.model.Department;
import com.mtv.erp.model.LaborRecord;
import com.mtv.erp.model.Position;
import com.mtv.erp.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.time.LocalDate;
import java.util.List;

public interface UserMapper {

    @Insert({"INSERT INTO user (id, firstname, lastname, email) VALUES (#{user.id}, #{user.firstname}, #{user.lastname}, #{user.email}"})
    void insert(@Param("user") User user);

    @Insert({"INSERT INTO user (id, firstname, lastname, email, saved) VALUES ((select max(b.id) From user as a left join user as b on a.id = b.id WHERE a.id < 6000000) + 1, #{user.firstname}, #{user.lastname}, #{user.email}, 1)"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer save(@Param("user") User user);

    @Select({"SELECT id, firstname, lastname, email FROM user WHERE id = #{id} AND NOT deleted = 1"})
    User getById(@Param("id") int id);


    @Update({"UPDATE user SET firstname = #{user.firstname}, lastname = #{user.lastname}, email = #{user.email} WHERE id = #{user.id}"})
    boolean update(@Param("user") User user);

    @Delete("UPDATE user SET deleted = 1 WHERE id = #{id}")
    Integer delete(@Param("id") Integer userId);

    @Insert({"<script>",
            "INSERT INTO user (id, firstname, lastname, email) VALUES",
            "<foreach item='user' collection= 'list' separator=', '>",
            "(#{user.id}, #{user.firstname}, #{user.lastname}, #{user.email})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAll(@Param("list") List<User> list);

    @Select({"SELECT id, firstname, lastname, email FROM user WHERE NOT deleted = 1 ORDER BY lastname"})
    List<User> getAll();

    @Select({"SELECT user.id as id, user.firstName as firstname, user.lastName as lastname, user.email as email, MIN(user_hours.date) as fromdate, MAX(user_hours.date) as todate FROM user, user_hours WHERE user.id = #{id} AND user_hours.date BETWEEN #{from} AND #{to} AND user.deleted = 0"})
    @Results({
            @Result(property = "userHours", column = "{from = fromdate, to = todate, userId = id}", javaType = List.class,
                    many = @Many(select = "com.mtv.erp.mybatis.mappers.HoursMapper.getFromDateByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "hours", column = "{from = fromdate, to = todate, userId = id}", javaType = List.class,
                    many = @Many(select = "com.mtv.erp.mybatis.mappers.HoursMapper.getFromDateByUserIdHours", fetchType = FetchType.LAZY))
    })
    User getByIdWithUserHours(@Param("id") int id, @Param("from") LocalDate from, @Param("to") LocalDate to);

    @Select({"SELECT user.id as id, user.firstName as firstname, user.lastName as lastname, user.email as email, MIN(user_hours.date) as fromdate, MAX(user_hours.date) as todate FROM user, user_hours WHERE user.id = #{id} AND user_hours.date BETWEEN #{from} AND #{to} AND user.deleted = 0"})
    @Results({
            @Result(property = "hours", column = "{from = fromdate, to = todate, userId = id}", javaType = List.class,
                    many = @Many(select = "com.mtv.erp.mybatis.mappers.HoursMapper.getFromDateByUserIdHours", fetchType = FetchType.LAZY))
    })
    User getByIdWithHours(@Param("id") int id, @Param("from") LocalDate from, @Param("to") LocalDate to);

//    @Delete({"DELETE FROM session WHERE sessionId = #{sessionId}"})
//    Integer logout(@Param("sessionId") String sessionId);
//
//    @Insert({"INSERT INTO user (login, password, firstName, lastName, patronymic, type) VALUES "
//            + "(#{user.login}, #{user.password}, #{user.firstName}, #{user.lastName}, #{user.patronymic}, #{user.userType})"})
//    @Options(useGeneratedKeys = true, keyProperty = "user.id")
//    void insertUser(@Param("user") User user);
//
//    @Update({"UPDATE user SET password = #{user.password}, firstName = #{user.firstName}, " +
//            "lastName = #{user.lastName}, patronymic = #{user.patronymic} WHERE id = #{user.id}"})
//    void updateUser(@Param("user") User user);
//
//    @Select({"SELECT * FROM user WHERE id = (SELECT userId FROM session WHERE sessionId = #{session})"})
//    User getBySession(String session);
//
//    @Select({"SELECT * FROM session WHERE sessionId = #{sessionId}"})
//    @Results({
//            @Result(property = "sessionId", column = "sessionId"),
//            @Result(property = "user", column = "sessionId", javaType = Session.class,
//                    one = @One(select = "net.thumbtack.school.hospital.mybatis.mappers.UserMapper.getBySession", fetchType = FetchType.EAGER))})
//    Session getSession(@Param("sessionId") String sessionId);
//
//    @Delete("DELETE FROM user WHERE id != 1")
//    Integer clear();
//
//    @Delete("DELETE FROM session")
//    Integer clearSession();
//
//    @Select("SELECT * FROM user WHERE login = #{login}")
//    User getByLogin(String login);
}
