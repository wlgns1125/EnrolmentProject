package service;

import persistence.dao.EnrollmentDAO;
import persistence.dao.LecturePlanDAO;
import persistence.dao.OpenSubjectDAO;
import persistence.dto.LecturePlanDTO;
import persistence.dto.OpenSubjectDTO;

import java.util.List;

public class LecturePlanService {

    private final LecturePlanDAO lecturePlanDAO;


    public LecturePlanService(LecturePlanDAO lecturePlanDAO){
        this.lecturePlanDAO = lecturePlanDAO;
    }


    public String selectBySubject(String subjectID){
        List<LecturePlanDTO> lecturePlanDTOS = lecturePlanDAO.selectByOpenSubject(subjectID);

        String temp = "";

        for(int i = 0; i < lecturePlanDTOS.size(); i++){
            temp += lecturePlanDTOS.get(i).toStringLecturePlan();
            temp += "\n";
        }

        return temp;


        //openSubjectDTOS.stream().forEach(v-> System.out.println(v.toStringProfesserName()));
    }


    public String selectID(){
        List<LecturePlanDTO> list = lecturePlanDAO.selectID();
        String temp = "";

        for(int i = 0; i < list.size(); i++){
            temp += list.get(i).get개설과목ID();
            temp += "\n";
        }

        return temp;

    }



}
