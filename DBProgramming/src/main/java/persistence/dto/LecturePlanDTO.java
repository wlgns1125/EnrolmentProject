package persistence.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturePlanDTO {
    private String 개설과목ID;
    private String 교재명;
    private String 참고문헌;
    private String 과목개요;
    private String 교수목표;
    private String 수업방법;

    public LecturePlanDTO(String 개설과목ID, String 교재명, String 참고문헌, String 과목개요, String 교수목표, String 수업방법){
        this.개설과목ID = 개설과목ID;
        this.교재명 = 교재명;
        this.참고문헌 = 참고문헌;
        this.과목개요 = 과목개요;
        this.교수목표 = 교수목표;
        this.수업방법 = 수업방법;
    }

    public LecturePlanDTO() {}

    public String toStringLecturePlan(){
        return "개설과목ID: " + 개설과목ID + " | 교재명 : " + 교재명 + " | 참고문헌 : " + 참고문헌 + " | 과목개요 : "
                + 과목개요 + " | 교수목표 : " + 교수목표 + " | 수업방법 : " + 수업방법;
    }

}
