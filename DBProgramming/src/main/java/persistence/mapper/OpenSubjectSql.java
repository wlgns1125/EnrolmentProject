package persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import persistence.dto.OpenSubjectDTO;

public class OpenSubjectSql {
    public String selectByGrade(String day){
        SQL sql = new SQL(){{ //익명클래스
            SELECT("*");
            FROM("개설교과목");
            WHERE("수강신청학년 like CONCAT('%',#{수강신청학년},'%')");
        }};
        return sql.toString();
    }

    public String selectByResProfessor(String name){
        SQL sql = new SQL(){{ //익명클래스
            SELECT("개설교과목.개설과목ID,교과목.교과목명,교수.이름,개설교과목.강의시간,개설교과목.강의실,개설교과목.신청인원,개설교과목.최대수강인원");
            FROM("개설교과목");
            JOIN("교수 ON 개설교과목.교수ID = 교수.교수ID");
            JOIN("교과목 ON 교과목.교과목ID = 개설교과목.교과목ID");
            WHERE("교수.이름 like CONCAT('%',#{교수.이름},'%')");
        }};
        return sql.toString();
    }
    public String selectByGradeAndProfessor(String grade, String name){
        SQL sql = new SQL(){{ //익명클래스
            SELECT("개설교과목.개설과목ID,교과목.교과목명,교수.이름,개설교과목.강의시간,개설교과목.강의실,개설교과목.신청인원,개설교과목.최대수강인원");
            FROM("개설교과목");
            JOIN("교수 ON 개설교과목.교수ID = 교수.교수ID");
            JOIN("교과목 ON 교과목.교과목ID = 개설교과목.교과목ID");
            WHERE("개설교과목.수강신청학년 = #{arg0} AND 교수.이름 = #{arg1}");
        }};
        return sql.toString();
    }

    public String selectBy개설과목id(String 개설과목id){
        SQL sql = new SQL(){{ //익명클래스
            SELECT("수강신청.학번,학생.이름");
            FROM("수강신청");
            JOIN("개설교과목 ON 수강신청.개설과목ID = 개설교과목.개설과목ID");
            JOIN("학생 ON 수강신청.학번 = 학생.학번");
            WHERE("수강신청.개설과목ID like CONCAT('%',#{수강신청.개설과목ID},'%')");
        }};
        return sql.toString();
    }

    public String selectByID(String 교수id){
        SQL sql = new SQL(){{ //익명클래스
            SELECT("*");
            FROM("개설교과목");
            WHERE("교수ID = #{arg0}");
        }};
        return sql.toString();
    }

}
