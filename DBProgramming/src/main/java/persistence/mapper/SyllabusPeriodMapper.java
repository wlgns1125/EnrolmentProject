package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.dto.ApplicationPeriodDTO;
import persistence.dto.OpenSubjectDTO;
import persistence.dto.SyllabusPeriodDTO;

import java.util.List;

public interface SyllabusPeriodMapper {
    final String getAll = "SELECT * FROM 강의계획서입력기간테이블";
    @Select(getAll)
    @Results(id = "result", value = {
            @Result(property = "연도학기", column = "연도학기"),
            @Result(property = "시작일", column = "시작일"),
            @Result(property = "끝일", column = "끝일")
    })
    List<SyllabusPeriodDTO> getAll();

    final String SELECTBYID = "SELECT * FROM 강의계획서입력기간테이블 WHERE 연도학기 = #{연도학기}";
    @Select(SELECTBYID)
    @ResultMap("result")
    public List<OpenSubjectDTO> getByYear(String 연도학기);


    final String INSERT = "INSERT INTO 강의계획서입력기간테이블 (연도학기, 시작일, 끝일) VALUE(#{연도학기}, #{시작일}, #{끝일})";
    @Insert(INSERT)
    @ResultMap("result")
    public void insert(SyllabusPeriodDTO syllabusPeriodDTO);

    final String UPDATE = "UPDATE 강의계획서입력기간테이블 SET 시작일 = #{시작일}, 끝일 = #{끝일} where 연도학기=#{연도학기}";
    @Update(UPDATE)
    @ResultMap("result")
    public void update(SyllabusPeriodDTO syllabusPeriodDTO);

    final String SELECTVALID = "SELECT * FROM 강의계획서입력기간테이블 WHERE NOW() BETWEEN 시작일 AND 끝일";
    @Select(SELECTVALID)
    @ResultMap("result")
    public List<OpenSubjectDTO> getValid();

}