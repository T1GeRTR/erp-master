package com.mtv.erp.mybatis.mappers;

import com.mtv.erp.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.time.LocalDate;
import java.util.List;

public interface HoursMapper {

//    @Insert({"INSERT INTO user (id, firstname, lastname, email) VALUES (#{user.id}, #{user.firstname}, #{user.lastname}, #{user.email}"})
//    void insert(@Param("user") User user);
//
//    @Select({"SELECT * FROM user WHERE id = #{id} AND deleted = FALSE"})
//    @Results({
//            @Result(property = "department", column = "departmentId", javaType = Department.class,
//                    one = @One(select = "com.mtv.erp.mybatis.mappers.DepartmentMapper.getById", fetchType = FetchType.EAGER)),
//            @Result(property = "position", column = "positionId", javaType = Position.class,
//                    one = @One(select = "com.mtv.erp.mybatis.mappers.PositionMapper.getById", fetchType = FetchType.EAGER))
//    })
//    User getById(@Param("id") int id);
//
//
//    @Update({"UPDATE user_hours SET login = #{user.firstname}, firstname = #{user.lastname}, lastname = #{user.email} WHERE id = #{user.id}"})
//    boolean update(@Param("laborRecord") LaborRecord laborRecord);

//    @Delete("UPDATE user SET deleted = 1 WHERE id = #{id}")
//    Integer delete(@Param("id") Integer userId);

    @Insert({"<script>",
            "INSERT INTO user_hours (userId, date, hours, taskId, taskTitle, projectId, projectTitle) VALUES",
            "<foreach item='laborRecord' collection= 'list' separator=', '>",
            "(#{laborRecord.user.id}, #{laborRecord.date}, #{laborRecord.hours}, #{laborRecord.taskId}, #{laborRecord.taskTitle}, #{laborRecord.projectId}, #{laborRecord.projectTitle})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAll(@Param("list") List<LaborRecord> list);

    @Insert({"<script>",
            "INSERT INTO hours (id, userId, date, hours) VALUES",
            "<foreach item='hours' collection= 'list' separator=', '>",
            "(#{hours.id}, #{hours.user.id}, #{hours.date}, #{hours.hours})",
            "</foreach>",
            "</script>"})
    int insertAllHours(@Param("list") List<Hours> list);

    @Update({"UPDATE user_hours SET deleted = 1 WHERE id = #{id}"})
    Integer delete(@Param("id") int id);

    @Update({"UPDATE hours SET deleted = 1 WHERE id = #{id}"})
    Integer deleteHours(@Param("id") int id);

    @Update({"UPDATE user_hours SET hours = #{elem.hours} WHERE id = #{elem.id}"})
    Integer update(@Param("elem") LaborRecord laborRecord);

    @Update({"UPDATE hours SET hours = #{elem.hours}, type = #{elem.type} WHERE id = #{elem.id} AND saved = 0"})
    Integer updateHours(@Param("elem") Hours hours);

    @Update({"UPDATE hours SET hours = #{elem.hours}, saved = 1, type = #{elem.type} WHERE id = #{elem.id}"})
    Integer saveChanges(@Param("elem") Hours hours);

//    INSERT INTO hours (id, userId, date, hours, saved) VALUES (9000, 6257958, '2000-01-01', 8, 1)
    @Update({"INSERT INTO hours (id, userId, date, hours, saved, type) VALUES ('1' + (SELECT max(b.id) From hours as a left join hours as b on a.id = b.id),#{hours.user.id}, #{hours.date}, #{hours.hours}, 1, #{hours.type})"})
    Integer insertChanges(@Param("hours") Hours hours);

    @Select({"SELECT id, userId, date, hours, taskId, taskTitle, projectId, projectTitle FROM user_hours WHERE date BETWEEN #{from} AND #{to} AND NOT deleted = 1 ORDER BY date"})
    @Results({
            @Result(property = "user", column = "userId", javaType = User.class,
                    one = @One(select = "com.mtv.erp.mybatis.mappers.UserMapper.getById", fetchType = FetchType.LAZY))
    })
    List<LaborRecord> getFromDate(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Select({"SELECT id, userId, date, hours, saved, type FROM hours WHERE date BETWEEN #{from} AND #{to} AND NOT deleted = 1 ORDER BY date"})
    @Results({
            @Result(property = "user", column = "userId", javaType = User.class,
                    one = @One(select = "com.mtv.erp.mybatis.mappers.UserMapper.getById", fetchType = FetchType.LAZY))
    })
    List<Hours> getFromDateHours(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Select({"SELECT id, userId, date, hours, taskId, taskTitle, projectId, projectTitle FROM user_hours WHERE date BETWEEN #{from} AND #{to} AND userId = #{userId} AND NOT deleted = 1 ORDER BY date"})
    @Results({
            @Result(property = "user", column = "userId", javaType = User.class,
                    one = @One(select = "com.mtv.erp.mybatis.mappers.UserMapper.getById", fetchType = FetchType.LAZY))
    })
    List<LaborRecord> getFromDateByUserId(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("userId") int userId);

    @Select({"SELECT id, userId, date, hours, saved, type FROM hours WHERE date BETWEEN #{from} AND #{to} AND userId = #{userId} AND NOT deleted = 1 ORDER BY date"})
    @Results({
            @Result(property = "user", column = "userId", javaType = User.class,
                    one = @One(select = "com.mtv.erp.mybatis.mappers.UserMapper.getById", fetchType = FetchType.LAZY))
    })
    List<Hours> getFromDateByUserIdHours(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("userId") int userId);

//    @Select({"SELECT id, userId, date, hours, taskId, taskTitle, projectId, projectTitle FROM user_hours WHERE date BETWEEN #{from} AND #{to} AND userId = #{userId} AND NOT deleted = 1 ORDER BY date"})
//    @Results({
//            @Result(property = "user", column = "userId", javaType = User.class,
//                    one = @One(select = "com.mtv.erp.mybatis.mappers.UserMapper.getById", fetchType = FetchType.LAZY))
//    })
//    List<LaborRecord> getFromDateByUserId(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("userId") int userId);


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
