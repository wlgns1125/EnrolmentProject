package persistence.mapper;


import org.apache.ibatis.annotations.*;
import persistence.dto.OpenSubjectDTO;

import java.util.List;

public interface OpenSubjectMapper {
    final String getAll = "SELECT * FROM 개설교과목";
    @Select(getAll)
    @Results(id = "resultSet", value = {
            @Result(property = "개설과목ID", column = "개설과목ID"),
            @Result(property = "교과목ID", column = "교과목ID"),
            @Result(property = "교수ID", column = "교수ID"),
            @Result(property = "수강신청학년", column = "수강신청학년"),
            @Result(property = "최대수강인원", column = "최대수강인원"),
            @Result(property = "신청인원", column = "신청인원"),
            @Result(property = "분반", column = "분반"),
            @Result(property = "수강신청가능여부", column = "수강신청가능여부"),
            @Result(property = "강의시간", column = "강의시간"),
            @Result(property = "강의실", column = "강의실")
    })

    List<OpenSubjectDTO> getAll();

    @SelectProvider(type = persistence.mapper.OpenSubjectSql.class, method = "selectByResProfessor")
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> getResProfessor(String id);

    @SelectProvider(type = persistence.mapper.OpenSubjectSql.class, method = "selectByGrade")
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> getByGrade(String grade);

    final String INSERT = "INSERT INTO 개설교과목 (개설과목ID, 교과목ID, 교수ID, 수강신청학년, 최대수강인원, 신청인원, 분반, 수강신청가능여부, 강의시간, 강의실) VALUES(#{개설과목ID}, #{교과목ID}, #{교수ID}, #{수강신청학년}, #{최대수강인원}, #{신청인원}, #{분반}, #{수강신청가능여부}, #{강의시간}, #{강의실})";
    @Insert(INSERT)
    @ResultMap("resultSet")
    public void insert(OpenSubjectDTO opensubjectDTO);


    final String UPDATE = "UPDATE 개설교과목 SET 최대수강인원 = #{최대수강인원},강의실 = #{강의실} where 개설과목ID=#{개설과목ID}";
    @Update(UPDATE)
    @ResultMap("result")
    public void update(OpenSubjectDTO opensubjectDTO);

    final String PLUS = "UPDATE 개설교과목  set 신청인원 = 신청인원 + 1 where 개설과목ID = #{개설과목ID}";
    @Update(PLUS)
    @ResultMap("result")
    public void plus(OpenSubjectDTO openSubjectDTO);

    final String MINUS = "update 개설교과목 set 신청인원 = if(신청인원 !=0, 신청인원 - 1, 0)  where 개설과목ID = #{개설과목ID}";
    @Update(MINUS)
    @ResultMap("result")
    public void minus(OpenSubjectDTO openSubjectDTO);

    final String SELECTBYID = "SELECT * FROM 개설교과목 WHERE 개설과목ID = #{개설과목ID}";
    @Select(SELECTBYID)
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> getById(String 개설과목ID);



    final String SELECT = "SELECT * FROM 개설교과목 WHERE 개설과목ID = #{개설과목ID}";
    @Select(SELECT)
    @ResultMap("resultSet")
    public OpenSubjectDTO select(String 개설과목ID);

    final String SELECT2 = "SELECT * FROM 개설교과목 WHERE 개설과목ID = #{개설과목ID}";
    @Select(SELECT2)
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> selectById(String 개설과목ID);


    final String ApplicationValidTrue = "UPDATE 개설교과목 " +
            "JOIN 수강신청기간테이블 " +
            "ON 수강신청기간테이블.수강신청학년 = 개설교과목.수강신청학년 " +
            "SET 개설교과목.수강신청가능여부 = #{수강신청가능여부} " +
            "WHERE 수강신청기간테이블.시작일 < NOW() AND NOW()<수강신청기간테이블.끝일";
    @Update(ApplicationValidTrue)
    public void updateApplicationValidTrue(OpenSubjectDTO opensubjectDTO);

    final String ApplicationValidFalse = "UPDATE 개설교과목 " +
            "JOIN 수강신청기간테이블 " +
            "ON 수강신청기간테이블.수강신청학년 = 개설교과목.수강신청학년 " +
            "SET 개설교과목.수강신청가능여부 = #{수강신청가능여부} " +
            "WHERE 수강신청기간테이블.끝일 < NOW() OR NOW()<수강신청기간테이블.시작일";
    @Update(ApplicationValidFalse)
    public void updateApplicationValidFalse(OpenSubjectDTO opensubjectDTO);


    final String SETFALSE = "UPDATE 개설교과목 SET 수강신청가능여부 = FALSE WHERE 개설과목ID = #{개설과목ID}";
    @Update(SETFALSE)
    public void setFalse(String 개설과목ID);

    final String SETTRUE = "update 개설교과목 set 수강신청가능여부 = true where 개설과목ID = #{개설과목ID}";
    @Update(SETTRUE)
    public void setTrue(String 개설과목ID);



    @SelectProvider(type = persistence.mapper.OpenSubjectSql.class, method = "selectByGradeAndProfessor")
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> getByGradeAndProfessor(String arg0, String arg1);


    final String DATECHECK = "SELECT 시작일, 끝일 FROM 개설교과목 JOIN 수강신청기간테이블 ON 개설교과목.수강신청학년 = 수강신청기간테이블.수강신청학년 WHERE 개설과목ID = #{개설과목ID}";
    @Select(DATECHECK)
    @ResultMap("resultSet")
    public OpenSubjectDTO selectDate(String 개설과목ID);


    final String UPDATEPROFFESSOR = "UPDATE 개설교과목 SET 교수ID = #{교수ID} where 개설과목ID=#{개설과목ID}";
    @Update(UPDATEPROFFESSOR)
    @ResultMap("result")
    public void updateProffessor(OpenSubjectDTO opensubjectDTO);

    final String UPDATEGRADE = "UPDATE 개설교과목 SET 수강신청학년 = #{수강신청학년} where 개설과목ID=#{개설과목ID}";
    @Update(UPDATEGRADE)
    @ResultMap("result")
    public void updateGrade(OpenSubjectDTO opensubjectDTO);

    final String UPDATEMAX = "UPDATE 개설교과목 SET 최대수강인원 = #{최대수강인원} where 개설과목ID=#{개설과목ID}";
    @Update(UPDATEMAX)
    @ResultMap("result")
    public void updateMax(OpenSubjectDTO opensubjectDTO);

    final String UPDATEAPPLYNUM = "UPDATE 개설교과목 SET 신청인원 = #{신청인원} where 개설과목ID=#{개설과목ID}";
    @Update(UPDATEAPPLYNUM)
    @ResultMap("result")
    public void updateApplyNum(OpenSubjectDTO opensubjectDTO);

    final String UPDATEDISTRIBUTION = "UPDATE 개설교과목 SET 분반 = #{분반} where 개설과목ID=#{개설과목ID}";
    @Update(UPDATEDISTRIBUTION)
    @ResultMap("result")
    public void updateDistribution(OpenSubjectDTO opensubjectDTO);

    final String UPDATEVALID = "UPDATE 개설교과목 SET 수강신청가능여부 = #{수강신청가능여부} where 개설과목ID=#{개설과목ID}";
    @Update(UPDATEVALID)
    @ResultMap("result")
    public void updateValid(OpenSubjectDTO opensubjectDTO);

    final String UPDATETIME = "UPDATE 개설교과목 SET 강의시간 = #{강의시간} where 개설과목ID=#{개설과목ID}";
    @Update(UPDATETIME)
    @ResultMap("result")
    public void updateTime(OpenSubjectDTO opensubjectDTO);

    final String UPDATEROOM = "UPDATE 개설교과목 SET 강의실 = #{강의실} where 개설과목ID=#{개설과목ID}";
    @Update(UPDATEROOM)
    @ResultMap("result")
    public void updateRoom(OpenSubjectDTO opensubjectDTO);

    final String DELETE = "DELETE FROM 개설교과목 WHERE 개설과목ID = #{개설과목ID}";
    @Delete(DELETE)
    @ResultMap("result")
    public void delete(String 개설과목ID);

    final String SELECT3 = "SELECT * FROM 개설교과목 WHERE 교수ID = #{교수ID} AND 개설과목ID = #{개설과목ID}";
    @Select(SELECT3)
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> selectBy교수Id(@Param("개설과목ID") String 개설과목ID, @Param("교수ID") String 교수ID);

    @SelectProvider(type = persistence.mapper.OpenSubjectSql.class, method = "selectByID")
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> getProfessorID(String id);

    final String PROFESSORSCHEDULE = "SELECT 교과목명,강의시간,강의실,개설교과목.개설과목ID FROM 개설교과목" +
            " JOIN 교과목 ON 개설교과목.교과목ID = 교과목.교과목ID WHERE 개설교과목.교수ID = #{교수ID}";
    @Select(PROFESSORSCHEDULE)
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> selectProfessorShedule(String 교수ID);



    final String STUDENTSCHEDULE = "SELECT 교과목명,강의시간,강의실,개설교과목.개설과목ID,교수.이름 FROM 개설교과목 JOIN 수강신청 ON 개설교과목.개설과목ID = 수강신청.개설과목ID " +
            "JOIN 교과목 ON 개설교과목.교과목ID = 교과목.교과목ID JOIN 교수 ON 개설교과목.교수ID = 교수.교수ID WHERE 수강신청.학번 = #{학번}";
    @Select(STUDENTSCHEDULE)
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> selectStudentShedule(String 학번);

    final String SELECTBYIDONLYNAME = "SELECT 개설과목ID, 교과목명 FROM 개설교과목 JOIN 교과목 ON 교과목.교과목ID = 개설교과목.교과목ID";
    @Select(SELECTBYIDONLYNAME)
    @ResultMap("resultSet")
    public List<OpenSubjectDTO> getByIdOnlyName();

}
