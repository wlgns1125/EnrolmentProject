package service;

import persistence.dao.ApplicationPeriodDAO;
import persistence.dao.OpenSubjectDAO;
import persistence.dto.ApplicationPeriodDTO;
import persistence.dto.OpenSubjectDTO;
import java.util.Date;
import java.util.Calendar;


public class ApplicationPeriodService {

    private final ApplicationPeriodDAO applicationPeriodDAO;
    private final OpenSubjectDAO openSubjectDAO;

    public ApplicationPeriodService(ApplicationPeriodDAO applicationPeriodDAO, OpenSubjectDAO openSubjectDAO) {
        this.applicationPeriodDAO = applicationPeriodDAO;
        this.openSubjectDAO = openSubjectDAO;
    }

    public void insertApplicationPeriod(String grade, Date date, Date date2){

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.add(Calendar.DATE, 1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.add(Calendar.DATE, 1);

        Date temp1 = cal1.getTime();
        Date temp2 = cal2.getTime();



        ApplicationPeriodDTO applicationPeriodDTO = new ApplicationPeriodDTO();
        applicationPeriodDTO.set수강신청학년(grade);
        applicationPeriodDTO.set시작일(temp1);
        applicationPeriodDTO.set끝일(temp2);


        applicationPeriodDAO.insertApplicationPeriod(applicationPeriodDTO);
    }

    public void updateApplicationPeriod(String grade, Date date, Date date2){

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.add(Calendar.DATE, 1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.add(Calendar.DATE, 1);

        Date temp1 = cal1.getTime();
        Date temp2 = cal2.getTime();

        ApplicationPeriodDTO applicationPeriodDTO = new ApplicationPeriodDTO();
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        applicationPeriodDTO.set수강신청학년(grade);
        applicationPeriodDTO.set시작일(temp1);
        applicationPeriodDTO.set끝일(temp2);

        applicationPeriodDAO.updateApplicationPeriod(applicationPeriodDTO);

        openSubjectDTO.set수강신청가능여부(true);
        openSubjectDAO.updateApplicationValidTrue(openSubjectDTO);
        openSubjectDTO.set수강신청가능여부(false);
        openSubjectDAO.updateApplicationValidFalse(openSubjectDTO);
    }

}
