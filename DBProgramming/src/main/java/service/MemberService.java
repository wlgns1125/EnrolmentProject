package service;

import persistence.dao.Member2DAO;
import persistence.dao.MemberDAO;
import persistence.dto.ProfessorDTO;
import persistence.dto.StudentDTO;

import java.util.List;
import java.util.Scanner;

public class MemberService {

    private final MemberDAO memberDAO;
    private final Member2DAO memberDAO2;
    //임의의 관리자,학생4명,교수 2명
    String mId = "2";
    String mName = "관리자";
    String mpn = "1234-1234-1234";
    String pwd = "1234";
    //------------------------------------
    int sId1 = 20180457;
    String sName1 = "박지훈";
    String sPn1 = "010-7751-6694";
    String sDep1 = "컴소공";
    int sGrade1 = 2;
    int sBirth1 = 991125;
    String sEmail1 = "www.naver.com";
    String sCode1 = "1";

    int sId2 = 20180458;
    String sName2 = "김해찬";
    String sPn2 = "010-2323-6694";
    String sDep2 = "컴소공";
    int sGrade2 = 2;
    int sBirth2 = 991126;
    String sEmail2 = "www.naver.com";
    String sCode2 = "1";

    int sId3 = 20180459;
    String sName3 = "박지환";
    String sPn3 = "010-3434-6694";
    String sDep3 = "컴소공";
    int sGrade3 = 2;
    int sBirth3 = 991127;
    String sEmail3 = "www.naver.com";
    String sCode3 = "2";

    int sId4 = 20180460;
    String sName4 = "이호준";
    String sPn4 = "010-7454-6694";
    String sDep4 = "컴소공";
    int sGrade4 = 2;
    int sBirth4 = 991128;
    String sEmail4 = "www.naver.com";
    String sCode4 = "2";

    //----------------------------------------------

    String pId1 = "a123";
    String pName1 = "김선명";
    String pLab1 = "D447";
    String pEmail1 = "www.kumoh.co.kr";
    String pPn1 = "010-1234-1234";
    int pBirth1 =  691123;

    String pId2 = "b123";
    String pName2 = "김성렬";
    String pLab2 = "D448";
    String pEmail2 = "www.kumoh.co.kr";
    String pPn2 = "010-8887-1234";
    int pBirth2 =  691124;



    public MemberService(MemberDAO memberDao, Member2DAO memberDao2){
        this.memberDAO = memberDao;
        this.memberDAO2 = memberDao2;
    }

    public void insertManger(String mId, String mName, String mpn, String pwd)
    {
        memberDAO.insertManager(mId,mName,mpn,pwd);
    }

    public void insertprofessor(String pId, String name, String pLab1, String pEmail, String pPn, int pBirth)
    {
        memberDAO.insertProfessor(pId, name, pLab1, pEmail, pPn, pBirth);
    }

    public void insertStudent(int sId, String sName, String sPn, String sDep, int Grade, int sBirth, String sEmail, String sCode)
    {
        memberDAO.insertStudent(sId,sName,sPn,sDep,Grade,sBirth,sEmail,sCode);
    }

    public void selectStudent()
    {
        memberDAO.selectStudent();
    }

    public void selectProfessor()
    {
        memberDAO.selectProfessor();
    }
    public void updateProfessorPn()
    {
        memberDAO.updateProfessorPn("a123","010-2443-3453");
    }

    public void updateStudentName()
    {
        memberDAO.updateStudentName(Integer.toString(sId1),"박영근");
    }

    public int count수강생정보(String 개설과목ID){
        return memberDAO.count수강생정보(개설과목ID);
    }

    public void print수강생정보(String 개설과목ID, int offset){
        memberDAO.print수강생정보(개설과목ID, offset);
    }

    public void print신청한교과목수강생(String 개설과목ID){
        Scanner sc = new Scanner(System.in);
        int offset;
        System.out.print("개설과목ID : ");
        System.out.println(개설과목ID);
        while(true) {
            int maxPage = (count수강생정보(개설과목ID) + 1) / 2;
            System.out.print("페이지 입력 (최대 " + maxPage + "페이지) (0입력시 종료) : ");
            offset = (sc.nextInt() - 1);
            if(offset == -1) {
                System.out.println("페이징 종료.");
                break;
            }
            if(offset >= maxPage){
                System.out.println("최대 페이지를 초과하였습니다.");
                continue;
            }
            print수강생정보(개설과목ID, offset * 2);
        }
    }

    public String selectStudent2() {
        List<StudentDTO> studentDTOS = memberDAO2.selectStudent2();

        String temp = "";

        for(int i = 0; i < studentDTOS.size(); i++){
            temp += studentDTOS.get(i).toStringStudent();
            temp += "\n";
        }

        return temp;

    }

    public String selectProfessor2() {
        List<ProfessorDTO> professorDTOS = memberDAO2.selectProffessor();

        String temp = "";

        for(int i = 0; i < professorDTOS.size(); i++){
            temp += professorDTOS.get(i).toStringProfessor();
            temp += "\n";
        }

        return temp;

    }


}
