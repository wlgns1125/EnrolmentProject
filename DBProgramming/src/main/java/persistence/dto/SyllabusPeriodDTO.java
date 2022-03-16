package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SyllabusPeriodDTO {
    private String 연도학기;
    private Date 시작일;
    private Date 끝일;
}