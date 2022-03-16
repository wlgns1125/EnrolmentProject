package service;

import persistence.dao.SubjectDAO;
import persistence.dto.SubjectDTO;

import java.util.List;

public class SubjectService {


    /*Test Case*/
    SubjectDTO subjectDTO1 = new SubjectDTO("CS0010","자바프로그래밍",3,1,"전필",2);
    SubjectDTO subjectDTO2 = new SubjectDTO("CS0016","컴퓨터네트워크",4,2,"전필",2);
    SubjectDTO subjectDTO3 = new SubjectDTO("CS0017","운영체제",3,2,"전필",2);
    SubjectDTO subjectDTO4 = new SubjectDTO("CS0069","융합프로젝트",2,2,"전필",2);
    SubjectDTO subjectDTO5 = new SubjectDTO("CS0077","C++프로그래밍",3,2,"전필",2);
    SubjectDTO subjectDTO6 = new SubjectDTO("CS0080","오픈소스소프트웨어",2,2,"전선",2);
    SubjectDTO subjectDTO7 = new SubjectDTO("CS0027","디자인패턴",3,3,"전선",2);
    SubjectDTO subjectDTO8 = new SubjectDTO("CS0035","컴파일러",3,4,"전선",2);



    private final SubjectDAO subjectDAO;

    public SubjectService(SubjectDAO subjectDAO){
        this.subjectDAO = subjectDAO;
    }

    public void selectAll(){
        List<SubjectDTO> boardDTOS = subjectDAO.selectAll();
        boardDTOS.stream().forEach(v-> System.out.println(v.toString()));
    }

    public void selectWithGrade(int 학년){
        List<SubjectDTO> boardDTOS = subjectDAO.selectWithGrade(학년);
        boardDTOS.stream().forEach(v-> System.out.println(v.toString()));
    }

    public void updateWithName(String id, String name){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.set교과목ID(id);
        subjectDTO.set교과목명(name);
        subjectDAO.updateWithBoardId(subjectDTO);
    }


    public void insert(SubjectDTO subjectDTO)
    {
        subjectDAO.insert(subjectDTO);
    }


    public boolean isSubjectExist(String id){
        List<SubjectDTO> boardDTOS = subjectDAO.selectWithId(id);
        if(boardDTOS.isEmpty())
        {
            return false;
        }else{
            return true;
        }

    }

    public void updateWithGrades(String id, int grades){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.set교과목ID(id);
        subjectDTO.set학점(grades);
        subjectDAO.updateGradesWithBoardId(subjectDTO);
    }

    public void updateWithGrade(String id, int grade){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.set교과목ID(id);
        subjectDTO.set학년(grade);
        subjectDAO.updateGradeWithBoardId(subjectDTO);
    }

    public void updateWithSubjectClassify(String id, String subjectClassify){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.set교과목ID(id);
        subjectDTO.set교과목구분(subjectClassify);
        subjectDAO.updateSubjectClarifyWithBoardId(subjectDTO);
    }

    public void updateWithSemester(String id, int semester){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.set교과목ID(id);
        subjectDTO.set학기(semester);
        subjectDAO.updateSemesterWithBoardId(subjectDTO);
    }

    public String returnSelectAll(){
        List<SubjectDTO> subjectDTOS = subjectDAO.selectAll();

        String temp = "";

        for(int i = 0; i < subjectDTOS.size(); i++){
            temp += subjectDTOS.get(i).toString();
            temp += "\n";
        }

        return temp;
    }


}
