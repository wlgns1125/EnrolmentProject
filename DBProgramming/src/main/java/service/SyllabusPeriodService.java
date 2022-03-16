package service;

import org.apache.ibatis.session.SqlSession;
import persistence.dao.SyllabusPeriodDAO;
import persistence.dto.OpenSubjectDTO;
import persistence.dto.SyllabusPeriodDTO;
import persistence.mapper.OpenSubjectMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SyllabusPeriodService {
    private final SyllabusPeriodDAO syllabusPeriodDAO;

    public SyllabusPeriodService(SyllabusPeriodDAO syllabusPeriodDAO)
    {
        this.syllabusPeriodDAO = syllabusPeriodDAO;
    }


    public void insertSyllabusPeriod(String period, Date date, Date date2){

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.add(Calendar.DATE, 1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.add(Calendar.DATE, 1);

        Date temp1 = cal1.getTime();
        Date temp2 = cal2.getTime();



        SyllabusPeriodDTO syllabusPeriodDTO = new SyllabusPeriodDTO();
        syllabusPeriodDTO.set연도학기(period);
        syllabusPeriodDTO.set시작일(temp1);
        syllabusPeriodDTO.set끝일(temp2);


        syllabusPeriodDAO.insertSyllabusPeriod(syllabusPeriodDTO);
    }

    public void updateSyllabusPeriod(String period, Date date, Date date2){

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.add(Calendar.DATE, 1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.add(Calendar.DATE, 1);

        Date temp1 = cal1.getTime();
        Date temp2 = cal2.getTime();

        SyllabusPeriodDTO syllabusPeriodDTO = new SyllabusPeriodDTO();
        syllabusPeriodDTO.set연도학기(period);
        syllabusPeriodDTO.set시작일(temp1);
        syllabusPeriodDTO.set끝일(temp2);


        syllabusPeriodDAO.updateSyllabusPeriod(syllabusPeriodDTO);

//        openSubjectDTO.set수강신청가능여부(true);
//        openSubjectDAO.updateApplicationValidTrue(openSubjectDTO);
//        openSubjectDTO.set수강신청가능여부(false);
//        openSubjectDAO.updateApplicationValidFalse(openSubjectDTO);
    }
}