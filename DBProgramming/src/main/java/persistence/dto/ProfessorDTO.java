package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfessorDTO {
    private String 교수ID;
    private String 이름;
    private String 연구실;
    private String 이메일;
    private String 전화번호;
    private String 생년월일;

    public String toStringProfessor(){
        return "교수ID : " + 교수ID + " | 이름 : " + 이름  +  " | 연구실 : " + 연구실 + " | 이메일 : " + 이메일 + " | 전화번호 : " + 전화번호 + " | 생년월일 : " + 생년월일;
    }


}