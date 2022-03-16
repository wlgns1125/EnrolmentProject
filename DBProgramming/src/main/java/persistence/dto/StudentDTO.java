package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentDTO {
    private String 학번;
    private String 이름;
    private String 전화번호;
    private String 학과;
    private String 학년;
    private String 생년월일;
    private String 이메일;
    private String 교수코드;

    public String toStringStudent(){
        return "학번 : " + 학번 + " | 이름 : " + 이름 + " | 전화번호 : " + 전화번호 + " | 학과 : "
                + 학과 + " | 학년 : " + 학년 + " | 생년월일 : " + 생년월일 + " | 이메일 : " + 이메일 + " | 교수코드 : " + 교수코드;
    }


}