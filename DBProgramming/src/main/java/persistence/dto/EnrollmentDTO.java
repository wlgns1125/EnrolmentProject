package persistence.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class EnrollmentDTO {
    private int 학번;
    private String 개설과목ID;
    private String 이름;
    private String 전화번호;
    private int 학년;
    private String 교과목명;
    private String 강의시간;
    private String 강의실;


    public EnrollmentDTO(int 학번, String 개설과목ID){
        this.학번 = 학번;
        this.개설과목ID = 개설과목ID;
    }

    public EnrollmentDTO(){

    }

    public String toString(){
        return "이름 : " + 이름 + " | 학번 : " + 학번 + " | 전화번호 : " + 전화번호 + " | 학년 : " + 학년 ;
    }

    public String toStringSubjectName(){
        return "교과목명 : " + 교과목명 + " | 개설과목ID : " + 개설과목ID + " | 강의시간 : " + 강의시간 + " | 강의실 : " + 강의실;
    }

    public String toString학생정보() {
        return "학번 : " + 학번 + " | 학생이름: " + 이름;
    }
}
