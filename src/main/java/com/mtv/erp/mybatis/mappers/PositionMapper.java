package com.mtv.erp.mybatis.mappers;

import com.mtv.erp.model.Position;
import org.apache.ibatis.annotations.Select;

public interface PositionMapper {
    @Select("SELECT * FROM position WHERE id = #{id} AND deleted = FALSE")
    Position getById(int id);
}
