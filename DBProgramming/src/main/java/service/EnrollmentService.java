package service;

import persistence.dao.EnrollmentDAO;
import persistence.dao.OpenSubjectDAO;
import persistence.dao.SubjectDAO;
import persistence.dto.EnrollmentDTO;
import persistence.dto.OpenSubjectDTO;

import java.sql.*;
import java.util.List;



public class EnrollmentService {


    private final EnrollmentDAO enrollmentDAO;
    private final OpenSubjectDAO openSubjectDAO;

    public EnrollmentService(EnrollmentDAO enrollmentDAO, OpenSubjectDAO openSubjectDAO){
        this.enrollmentDAO = enrollmentDAO;
        this.openSubjectDAO = openSubjectDAO;
    }


    public int insert(int 학번, String 개설과목ID)
    {
        if(!enrollmentDAO.isValid(개설과목ID)){
            if(!enrollmentDAO.isValidDate(개설과목ID)){
                System.out.println("신청실패 (수강신청기간이 아님)");
            }
            else if(enrollmentDAO.isFull(개설과목ID)){
                System.out.println("신청실패 (신청인원 초과)");
            }
        }
        else{
            if(!(enrollmentDAO.isDupple(개설과목ID,학번))){

                if(강의시간중복체크(개설과목ID,학번)){

                    EnrollmentDTO enrollmentDTO = new EnrollmentDTO(학번 , 개설과목ID);
                    OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
                    openSubjectDTO.set개설과목ID(개설과목ID);
                    enrollmentDAO.insert(enrollmentDTO);
                    openSubjectDAO.신청인원추가(openSubjectDTO);
                    System.out.println("신청완료");

                    if(enrollmentDAO.isFull(개설과목ID)){
                        openSubjectDAO.setFalse(개설과목ID);
                    }
                    return 1;
                } else{
                    System.out.println("신청실패 (강의 시간 중복)");
                }

            } else{
                System.out.println("신청실패 (중복 교과목 신청)");
            }


        }
        return 0;
    }

    public void select(String 개설과목ID){

        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.set개설과목ID(개설과목ID);

        List<EnrollmentDTO> enrollmentDTOList = enrollmentDAO.select(enrollmentDTO);

        System.out.println("개설과목ID = "  + 개설과목ID);
        enrollmentDTOList.stream().forEach(v-> System.out.println(v.toString()));
    }

    public boolean select(int 학번, String 개설과목ID){

        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.set개설과목ID(개설과목ID);
        enrollmentDTO.set학번(학번);

        List<EnrollmentDTO> list = enrollmentDAO.select(enrollmentDTO);

        return !list.isEmpty();
    }

    public String selectSubjectName(int 학번){

        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.set학번(학번);

        List<EnrollmentDTO> enrollmentDTOList = enrollmentDAO.selectSubjectName(enrollmentDTO);
        String temp = "";

        for(int i = 0; i < enrollmentDTOList.size(); i++){
            temp += enrollmentDTOList.get(i).toStringSubjectName();
            temp += "\n";
        }
        return temp;

        //enrollmentDTOList.stream().forEach(v-> System.out.println(v.toStringSubjectName()));
    }

    public void delete(int 학번, String 개설과목ID){

        if(enrollmentDAO.isValidDate(개설과목ID)){

            if(enrollmentDAO.isFull(개설과목ID)){
                openSubjectDAO.setTrue(개설과목ID);
            }

        }

        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDAO.신청인원감소(openSubjectDTO);


        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.set개설과목ID(개설과목ID);
        enrollmentDTO.set학번(학번);

        enrollmentDAO.delete(enrollmentDTO);
        System.out.println("수강 신청 삭제 완료");



    }

    public boolean 강의시간중복체크(String 개설과목ID, int 학번){

        List<OpenSubjectDTO> list = null;
        list = enrollmentDAO.AllTime(학번);

        for(int i = 0; i < list.size(); i++){
            if(!enrollmentDAO.수강신청가능(개설과목ID,list.get(i).get개설과목ID())){
                return false;
            }
        }

        return true;
    }

    public String selectByEnrollmentStudent(String 개설과목id){
        List<EnrollmentDTO> enrollmentDTOS = enrollmentDAO.selectBy수강신청학생(개설과목id);

        String temp = "";

        for(int i = 0; i < enrollmentDTOS.size(); i++){
            temp += enrollmentDTOS.get(i).toString학생정보();
            temp += "\n";
        }

        return temp;


        //openSubjectDTOS.stream().forEach(v-> System.out.println(v.toStringProfesserName()));



    }



}
