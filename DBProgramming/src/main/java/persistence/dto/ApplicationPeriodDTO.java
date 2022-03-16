package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ApplicationPeriodDTO {
    private String 수강신청학년;
    private Date 시작일;
    private Date 끝일;
}
