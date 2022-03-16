package persistence.dto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class OpenSubjectDTO {
    private String 개설과목ID;
    private String 교과목ID;
    private String 교수ID;
    private String 수강신청학년;
    private int 최대수강인원;
    private int 신청인원;
    private int 분반;
    private boolean 수강신청가능여부;
    private String 강의시간;
    private String 강의실;
    private String 교과목명;
    private int 학점;
    private int 학년;
    private String 교과목구분;
    private int 학기;
    private String 이름;
    private Date 시작일;
    private Date 끝일;
    private String 교재명;
    private String 참고문헌;
    private String 과목개요;
    private String 교수목표;
    private String 수업방법;

    public OpenSubjectDTO(String 개설과목ID, String 교과목ID, String 교수ID, String 수강신청학년, int 최대수강인원, int 신청인원, int 분반, boolean 수강신청가능여부, String 강의시간, String 강의실){
        this.개설과목ID = 개설과목ID;
        this.교과목ID = 교과목ID;
        this.교수ID = 교수ID;
        this.수강신청학년 = 수강신청학년;
        this.최대수강인원 = 최대수강인원;
        this.신청인원 = 신청인원;
        this.분반 = 분반;
        this.수강신청가능여부 = 수강신청가능여부;
        this.강의시간 = 강의시간;
        this.강의실 = 강의실;
    }
    public OpenSubjectDTO(){}

    public String toString(){
        return "개설과목ID : " + 개설과목ID + " | 교과목ID : " + 교과목ID + " | 수강신청 학년 : " + 수강신청학년 + " | 최대수강인원 : "
                + 최대수강인원 + " | 신청인원 : " + 신청인원 + " | 분반 : " + 분반 + " | 수강신청 가능여부 : " + 수강신청가능여부 + " | 강의시간 : " + 강의시간 + " | 강의실 : " + 강의실;
    }

    public String toStringName(){
        return "개설과목ID : " + 개설과목ID  + " | 교과목명 : " + 교과목명;
    }

    public String toStringProfesserName(){
        return "개설과목ID : " + 개설과목ID + " | 교과목명 : " + 교과목명 + " | 담당교수 : " + 이름 + " | 강의시간 : " + 강의시간 + " | 강의실 : " + 강의실 + " | 현재신청인원 : " + 신청인원 + " | 최대수강인원 : " + 최대수강인원 ;
    }

    public String toStringValid(){
        return "개설과목ID : " + 개설과목ID + " | 수강신청 가능 여부 : " + 수강신청가능여부;
    }


    public boolean get수강신청가능여부(){
        return 수강신청가능여부;
    }

    public String toString2(){
        return "개설과목ID: " + 개설과목ID + " | 교과목명 : " + 교과목명 + " | 강의시간 : " + 강의시간 + " | 강의실 : " + 강의실 + " | 현재신청인원 : " + 신청인원 + " | 최대수강인원 : " + 최대수강인원 ;
    }


}
