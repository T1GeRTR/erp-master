package com.mtv.erp.mybatis.mappers;

import com.mtv.erp.model.Department;
import org.apache.ibatis.annotations.Select;

public interface DepartmentMapper {
    @Select("SELECT * FROM department WHERE id = #{id} AND deleted = FALSE")
    Department getById(int id);
}
