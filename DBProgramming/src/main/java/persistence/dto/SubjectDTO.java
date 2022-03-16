package persistence.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubjectDTO {
    private String 교과목ID;
    private String 교과목명;
    private int 학점;
    private int 학년;
    private String 교과목구분;
    private int 학기;

    public SubjectDTO(String 교과목ID, String 교과목명, int 학점, int 학년, String 교과목구분, int 학기){
        this.교과목ID = 교과목ID;
        this.교과목명 = 교과목명;
        this.학점 = 학점;
        this.학년 = 학년;
        this.교과목구분 = 교과목구분;
        this.학기 = 학기;
    }

    public SubjectDTO(){
    }

    public String toString(){
        return "교과목코드 : " + 교과목ID + "\t" + "교과목명 : " + 교과목명 + "\t" + "학점 : " + 학점 + "\t\t" + "학년 : " + 학년  + "\t\t" + "교과목구분 : " + 교과목구분  + "\t" + "학기 : " + 학기;
    }

}
