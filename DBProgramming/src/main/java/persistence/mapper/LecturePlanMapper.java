package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.dto.LecturePlanDTO;
import persistence.dto.OpenSubjectDTO;

import java.util.List;

public interface LecturePlanMapper {
    final String getAll = "SELECT * FROM 강의계획서";
    @Select(getAll)
    @Results(id = "resultSet", value = {
            @Result(property = "개설과목ID", column = "개설과목ID"),
            @Result(property = "교재명", column = "교재명"),
            @Result(property = "참고문헌", column = "참고문헌"),
            @Result(property = "과목개요", column = "과목개요"),
            @Result(property = "수업방법", column = "수업방법")
    })
    List<LecturePlanDTO> getAll();

    final String SELECT = "SELECT * FROM 강의계획서 WHERE 개설과목ID = #{개설과목ID}";
    @Select(SELECT)
    @ResultMap("resultSet")
    List<LecturePlanDTO> selectById(String 개설과목ID);

    final String SELECT2 = "SELECT * FROM 강의계획서";
    @Select(SELECT2)
    @ResultMap("resultSet")
    List<LecturePlanDTO> selectAll();

    final String INSERT = "INSERT INTO 강의계획서 (개설과목ID, 교재명, 참고문헌, 과목개요, 교수목표, 수업방법) VALUES (#{개설과목ID}, #{교재명}, #{참고문헌}, #{과목개요}, #{교수목표}, #{수업방법})";
    @Insert(INSERT)
    @ResultMap("resultSet")
    public void insert(LecturePlanDTO lecturePlanDTO);

    final String UPDATE = "UPDATE 강의계획서 SET 교재명 = #{교재명},참고문헌 = #{참고문헌}, 과목개요 = #{과목개요}, 교수목표 = #{교수목표}, 수업방법 = #{수업방법} where 개설과목ID = #{개설과목ID}";
    @Update(UPDATE)
    @ResultMap("result")
    public void update(LecturePlanDTO lecturePlanDTO);




}
