package service;

import com.mysql.cj.x.protobuf.MysqlxCursor;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dao.EnrollmentDAO;
import persistence.dao.OpenSubjectDAO;
import persistence.dto.OpenSubjectDTO;
import persistence.dto.SubjectDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OpenSubjectService {

    //삽입 테스트 케이스
    OpenSubjectDTO openSubjectDTO1 = new OpenSubjectDTO("CS0016-01","CS0016","a123","2",40,30,2,true,"목89,금67","D327");



    private final OpenSubjectDAO openSubjectDAO;

    public OpenSubjectService(OpenSubjectDAO openSubjectDAO){
        this.openSubjectDAO = openSubjectDAO;
    }

    public String selectAllOpenSubject(){
        List<OpenSubjectDTO> openSubjectDTOS = openSubjectDAO.selectAllOpenSubject();
        String temp = "";

        for(int i =0; i<openSubjectDTOS.size(); i++){
            temp += openSubjectDTOS.get(i).toString();
            temp += "\n";
        }
        return temp;
    }

    public String selectByResProfessor(String 교수이름){
        List<OpenSubjectDTO> openSubjectDTOS = openSubjectDAO.selectByResProfessor(교수이름);;
        System.out.println("담당교수 : " + 교수이름);

        String temp = "";

        for(int i = 0; i < openSubjectDTOS.size(); i++){
            temp += openSubjectDTOS.get(i).toStringProfesserName();
            temp += "\n";
        }

        return temp;


        //openSubjectDTOS.stream().forEach(v-> System.out.println(v.toStringProfesserName()));



    }

    public void selectByGrade(String grade){
        List<OpenSubjectDTO> openSubjectDTOS = openSubjectDAO.selectByGrade(grade);;
        openSubjectDTOS.stream().forEach(v-> System.out.println(v.toString()));
    }

    public void insertOpenSubject(String 개설과목ID, String 교과목ID, String 교수ID, String 수강신청학년, int 최대수강인원, int 분반 , String 강의시간, String 강의실){
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO(개설과목ID,교과목ID,교수ID,수강신청학년,최대수강인원,0,분반,false,강의시간,강의실);
        openSubjectDAO.insertOpenSubject(openSubjectDTO);
    }

    public void updateOpenSubject(String 개설과목ID, String 강의실, int 최대수강인원){

        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDTO.set강의실(강의실);
        openSubjectDTO.set최대수강인원(최대수강인원);

        openSubjectDAO.updateOpenSubject(openSubjectDTO);
    }

    public void applicationValidTrue(boolean 수강신청가능){
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set수강신청가능여부(수강신청가능);

        openSubjectDAO.updateApplicationValidTrue(openSubjectDTO);
    }

    public void applicationValidFalse(boolean 수강신청가능){
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set수강신청가능여부(수강신청가능);

        openSubjectDAO.updateApplicationValidFalse(openSubjectDTO);
    }

    public void selectByGradeAndProfessor(String 학년, String 교수이름){

        List<OpenSubjectDTO> list = null;
        list = openSubjectDAO.selectByGradeAndProfessor(학년,교수이름);
        System.out.println("교수 : " + 교수이름 + ", 학년 : " + 학년);
        list.stream().forEach(v-> System.out.println(v.toString2()));

    }

    public void updateProffessor(String 개설과목ID, String 교수ID)
    {
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDTO.set교수ID(교수ID);

        openSubjectDAO.updateProffessor(openSubjectDTO);
    }

    public void updateGrade(String 개설과목ID, String 수강신청학년)
    {
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDTO.set수강신청학년(수강신청학년);

        openSubjectDAO.updateGrade(openSubjectDTO);
    }

    public void updateMax(String 개설과목ID, int 최대수강인원)
    {
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDTO.set최대수강인원(최대수강인원);

        openSubjectDAO.updateMax(openSubjectDTO);
    }

    public void updateApplyNum(String 개설과목ID, int 신청인원)
    {
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDTO.set신청인원(신청인원);

        openSubjectDAO.updateApplyNum(openSubjectDTO);
    }

    public void updateDistribution(String 개설과목ID, int 분반)
    {
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDTO.set분반(분반);

        openSubjectDAO.updateDistribution(openSubjectDTO);
    }

    public String selectAllOpenSubjectOnlyName(){
        List<OpenSubjectDTO> openSubjectDTOS = openSubjectDAO.selectAllOpenSubjectOnlyName();
        String temp = "";
        if(openSubjectDTOS.size() > 0) {
            for (int i = 0; i < openSubjectDTOS.size() - 1; i++) {
                temp += openSubjectDTOS.get(i).toStringName();
                temp += "\n";
            }
            temp += openSubjectDTOS.get(openSubjectDTOS.size() - 1).toStringName();
        }

        return temp;
    }

    //11/28
    public String selectByID(String 개설과목ID){
        List<OpenSubjectDTO> openSubjectDTOS = openSubjectDAO.selectByID(개설과목ID);

        String temp = "";

        for(int i = 0; i < openSubjectDTOS.size(); i++){
            temp += openSubjectDTOS.get(i).toString();
            temp += "\n";
        }

        return temp;
    }


    public void updateValid(String 개설과목ID, boolean 수강신청가능여부)
    {
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDTO.set수강신청가능여부(수강신청가능여부);

        openSubjectDAO.updateValid(openSubjectDTO);
    }

    public void updateTime(String 개설과목ID, String 강의시간)
    {
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDTO.set강의시간(강의시간);

        openSubjectDAO.updateTime(openSubjectDTO);
    }

    public void updateRoom(String 개설과목ID, String 강의실)
    {
        OpenSubjectDTO openSubjectDTO = new OpenSubjectDTO();
        openSubjectDTO.set개설과목ID(개설과목ID);
        openSubjectDTO.set강의실(강의실);

        openSubjectDAO.updateRoom(openSubjectDTO);
    }



    public String selectByProfessorID(String 교수id){
        List<OpenSubjectDTO> openSubjectDTOS = openSubjectDAO.selectByProfessorID(교수id);

        String temp = "";

        for(int i = 0; i < openSubjectDTOS.size(); i++){
            temp += openSubjectDTOS.get(i).toString();
            temp += "\n";
        }

        return temp;


        //openSubjectDTOS.stream().forEach(v-> System.out.println(v.toStringProfesserName()));



    }

    public String 교수본인시간표조회(String 교수ID){
        List<OpenSubjectDTO> openSubjectDTOS =  openSubjectDAO.selectProfessorSchedule(교수ID);
        ArrayList<강의시간표> 월요일 = new ArrayList<강의시간표>();
        ArrayList<강의시간표> 화요일 = new ArrayList<강의시간표>();
        ArrayList<강의시간표> 수요일 = new ArrayList<강의시간표>();
        ArrayList<강의시간표> 목요일 = new ArrayList<강의시간표>();
        ArrayList<강의시간표> 금요일 = new ArrayList<강의시간표>();

        for(int i=0; i<openSubjectDTOS.size(); i++){
            insert시간표(월요일, 화요일, 수요일, 목요일, 금요일, openSubjectDTOS.get(i).get교과목명(), openSubjectDTOS.get(i).get강의시간(), openSubjectDTOS.get(i).get강의실(), openSubjectDTOS.get(i).get이름(), openSubjectDTOS.get(i).get개설과목ID());
        }
        String 월요일시간표, 화요일시간표, 수요일시간표, 목요일시간표, 금요일시간표, 시간표 = "";
        sort(월요일);
        sort(화요일);
        sort(수요일);
        sort(목요일);
        sort(금요일);

        월요일시간표 = toString교수시간표(월요일);
        화요일시간표 = toString교수시간표(화요일);
        수요일시간표 = toString교수시간표(수요일);
        목요일시간표 = toString교수시간표(목요일);
        금요일시간표 = toString교수시간표(금요일);

        시간표 = "월요일 : " + 월요일시간표 + "\n" + "화요일 : " + 화요일시간표 + "\n" + "수요일 : " + 수요일시간표 + "\n" + "목요일 : " + 목요일시간표 + "\n" + "금요일 : " + 금요일시간표;

        return 시간표;
    }

    public String 학생본인시간표조회(String 학번){
        List<OpenSubjectDTO> openSubjectDTOS =  openSubjectDAO.selectStudentSchedule(학번);
        ArrayList<강의시간표> 월요일 = new ArrayList<강의시간표>();
        ArrayList<강의시간표> 화요일 = new ArrayList<강의시간표>();
        ArrayList<강의시간표> 수요일 = new ArrayList<강의시간표>();
        ArrayList<강의시간표> 목요일 = new ArrayList<강의시간표>();
        ArrayList<강의시간표> 금요일 = new ArrayList<강의시간표>();

        for(int i=0; i<openSubjectDTOS.size(); i++){
            insert시간표(월요일, 화요일, 수요일, 목요일, 금요일, openSubjectDTOS.get(i).get교과목명(), openSubjectDTOS.get(i).get강의시간(), openSubjectDTOS.get(i).get강의실(), openSubjectDTOS.get(i).get이름(), openSubjectDTOS.get(i).get개설과목ID());
        }
        String 월요일시간표, 화요일시간표, 수요일시간표, 목요일시간표, 금요일시간표, 시간표 = "";
        sort(월요일);
        sort(화요일);
        sort(수요일);
        sort(목요일);
        sort(금요일);

        월요일시간표 = toString시간표(월요일);
        화요일시간표 = toString시간표(화요일);
        수요일시간표 = toString시간표(수요일);
        목요일시간표 = toString시간표(목요일);
        금요일시간표 = toString시간표(금요일);

        시간표 = "월요일 :" + 월요일시간표 + "\n" + "화요일 :" + 화요일시간표 + "\n" + "수요일 :" + 수요일시간표 + "\n" + "목요일 :" + 목요일시간표 + "\n" + "금요일 :" + 금요일시간표;

        return 시간표;
    }

    public static void insert시간표(ArrayList<강의시간표> 월요일,ArrayList<강의시간표> 화요일,ArrayList<강의시간표> 수요일,ArrayList<강의시간표> 목요일,ArrayList<강의시간표> 금요일, String 과목명, String 강의시간, String 강의실, String 교수명, String 개설과목Id) {
        String[] arr = 강의시간.split(",");

        for(int i=0; i<arr.length; i++) {
            강의시간표 in = new 강의시간표(과목명, arr[i], 강의실, 교수명, 개설과목Id);
            if(arr[i].charAt(0) == '월') {
                월요일.add(in);
            } else if(arr[i].charAt(0) == '화') {
                화요일.add(in);
            }else if(arr[i].charAt(0) == '수') {
                수요일.add(in);
            }else if(arr[i].charAt(0) == '목') {
                목요일.add(in);
            }else if(arr[i].charAt(0) == '금') {
                금요일.add(in);
            }
        }
    }

    public static String toString시간표(ArrayList<강의시간표> 요일) {
        String s = "";

        if(요일.size() > 0){
            for(int i=0; i<요일.size() - 1; i++) {
                s += 요일.get(i) + ",\t";
            }
            s += 요일.get(요일.size() - 1);
        }
        return s;
    }

    public static String toString교수시간표(ArrayList<강의시간표> 요일) {
        String s = "";


        if(요일.size() > 0){
            for(int i=0; i<요일.size() - 1; i++) {
                s += 요일.get(i).toStringFor교수() + ",\t";
            }
            s += 요일.get(요일.size() - 1).toStringFor교수();

        }
        return s;
    }

    public static void sort(ArrayList<강의시간표> 요일) {
        for(int i=1; i<요일.size(); i++) {
            for(int j=0; j<요일.size() - i; j++) {
                if(요일.get(j).get강의시간().compareTo(요일.get(j + 1).get강의시간()) > 0) {
                    Collections.swap(요일, j, j+1);
                    //swap(요일, j, j + 1);
                }
            }
        }
    }

}



class 강의시간표{
    private String 교과목명;
    private String 강의시간;
    private String 강의실;
    private String 교수명;
    private String 개설과목Id;

    public 강의시간표(String 교과목명, String 강의시간, String 강의실, String 교수명, String 개설과목Id) {
        this.교과목명 = 교과목명;
        this.강의시간 = 강의시간;
        this.강의실 = 강의실;
        this.교수명 = 교수명;
        this.개설과목Id = 개설과목Id;
    }

    public 강의시간표(String 교과목명, String 강의시간, String 강의실, String 개설과목Id) {
        this.교과목명 = 교과목명;
        this.강의시간 = 강의시간;
        this.강의실 = 강의실;
        this.개설과목Id = 개설과목Id;
    }

    public String get교과목명() {
        return 교과목명;
    }

    public String get강의시간() {
        return 강의시간;
    }

    public String get강의실() {
        return 강의실;
    }

    public String get교수명() {
        return 교수명;
    }

    public String get개설과목Id() {
        return 개설과목Id;
    }

    public String toStringFor교수() {
        return 교과목명 +"(" + 개설과목Id + ") " + 강의실 + " " + 강의시간;
    }

    public String toString() {
        return 교과목명 +"(" + 개설과목Id + ") " + 교수명 + " " + 강의실 + " " + 강의시간;
    }
}

