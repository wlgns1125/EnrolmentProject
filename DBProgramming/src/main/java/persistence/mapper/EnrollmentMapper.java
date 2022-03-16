package persistence.mapper;


import org.apache.ibatis.annotations.*;
import persistence.dto.EnrollmentDTO;
import persistence.dto.OpenSubjectDTO;

import java.util.List;

public interface EnrollmentMapper {
    final String getAll = "SELECT * FROM 수강신청";
    @Select(getAll)

    @Results(id = "resultSet", value = {
            @Result(property = "학번", column = "학번"),
            @Result(property = "개설과목ID", column = "개설과목ID"),
            @Result(property = "이름", column = "이름"),
    })

    List<EnrollmentDTO> getAll();


    final String DUPPLE = "SELECT * FROM 수강신청 WHERE 개설과목ID LIKE #{개설과목ID} AND 학번 = #{학번}";
    @Select(DUPPLE)
    @ResultMap("resultSet")
    public List<EnrollmentDTO> dupple(@Param("개설과목ID") String 개설과목ID, @Param("학번") int 학번);



    final String ALLTIME = "SELECT * from 수강신청 join 개설교과목 on 수강신청.개설과목ID = 개설교과목.개설과목ID where 학번 = #{학번}";
    @Select(ALLTIME)
    public List<OpenSubjectDTO> allTime (int 학번);

    @SelectProvider(type = persistence.mapper.OpenSubjectSql.class, method = "selectBy개설과목id")
    @ResultMap("resultSet")
    public List<EnrollmentDTO> getEnrollmentStudent(String 개설과목id);



}
