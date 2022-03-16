package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.dto.UserDTO;

import java.util.List;

public interface UserMapper {
    final String getAll = "SELECT * FROM 사용자";
    @Select(getAll)
    @Results(id = "resultSet", value = {
            @Result(property = "ID", column = "ID"),
            @Result(property = "패스워드", column = "패스워드"),
            @Result(property = "사용자구분", column = "사용자구분"),
    })
    List<UserDTO> getAll();


    final String FINDID = "SELECT * FROM 사용자 WHERE ID = #{ID}";
    @Select(FINDID)
    @ResultMap("resultSet")
    public List<UserDTO> selectById(String ID);

}
