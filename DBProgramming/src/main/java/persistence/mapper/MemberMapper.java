package persistence.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import persistence.dto.OpenSubjectDTO;
import persistence.dto.ProfessorDTO;
import persistence.dto.StudentDTO;
import persistence.dto.UserDTO;

import java.util.List;

public interface MemberMapper {

    final String GETALL = "SELECT * FROM 학생";
    @Select(GETALL)
    @Results(id = "result", value = {
            @Result(property = "학번", column = "학번"),
            @Result(property = "이름", column = "이름"),
            @Result(property = "전화번호", column = "전화번호"),
            @Result(property = "학과", column = "학과"),
            @Result(property = "학년", column = "학년"),
            @Result(property = "생년월일", column = "생년월일"),
            @Result(property = "이메일", column = "이메일"),
            @Result(property = "교수코드", column = "교수코드"),
    })
    List<StudentDTO> getAll();


    final String GETALLProfessor = "SELECT * FROM 교수";
    @Select(GETALLProfessor)
    @Results(id = "resultProfessor", value = {
            @Result(property = "학번", column = "학번"),
            @Result(property = "이름", column = "이름"),
            @Result(property = "전화번호", column = "전화번호"),
            @Result(property = "학과", column = "학과"),
            @Result(property = "학년", column = "학년"),
            @Result(property = "생년월일", column = "생년월일"),
            @Result(property = "이메일", column = "이메일"),
            @Result(property = "교수코드", column = "교수코드"),
    })
    List<ProfessorDTO> getProffessor();


    final String GETALLUserWithId = "SELECT * FROM 교수 WHERE 교수ID = #{교수ID}";
    @Select(GETALLUserWithId)
    @ResultMap("resultProfessor")
    List<ProfessorDTO> getProffessorWithId(String 교수ID);



}