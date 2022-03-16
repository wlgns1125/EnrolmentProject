package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.dto.ApplicationPeriodDTO;

import java.util.List;

public interface ApplicationPeriodMapper {
    final String getAll = "SELECT * FROM 수강신청기간테이블";
    @Select(getAll)
    @Results(id = "result", value = {
            @Result(property = "수강신청학년", column = "수강신청학년"),
            @Result(property = "시작일", column = "시작일"),
            @Result(property = "끝일", column = "끝일")
    })
    List<ApplicationPeriodDTO> getAll();

    final String INSERT = "INSERT INTO 수강신청기간테이블 (수강신청학년, 시작일, 끝일) VALUE(#{수강신청학년}, #{시작일}, #{끝일})";
    @Insert(INSERT)
    @ResultMap("result")
    public void insert(ApplicationPeriodDTO applicationPeriodDTO);

    final String UPDATE = "UPDATE 수강신청기간테이블 SET 시작일 = #{시작일}, 끝일 = #{끝일} where 수강신청학년=#{수강신청학년}";
    @Update(UPDATE)
    @ResultMap("result")
    public void update(ApplicationPeriodDTO applicationPeriodDTO);
}