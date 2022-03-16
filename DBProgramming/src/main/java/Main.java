import persistence.MyBatisConnectionFactory;
import persistence.dao.*;
import service.*;

import java.util.Scanner;
import java.time.LocalDateTime;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String args[]){

        //DAO 생성
        MemberDAO  memberDAO = new MemberDAO();
        Member2DAO member2DAO = new Member2DAO(MyBatisConnectionFactory.getSqlSessionFactory());
        SubjectDAO subjectDAO = new SubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OpenSubjectDAO opensubjectDAO = new OpenSubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ApplicationPeriodDAO applicationPeriodDAO = new ApplicationPeriodDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());




        //Service 생성
        MemberService memberService = new MemberService(memberDAO,member2DAO);
        SubjectService subjectService = new SubjectService(subjectDAO);
        OpenSubjectService openSubjectService = new OpenSubjectService(opensubjectDAO);
        EnrollmentService enrollmentService = new EnrollmentService(enrollmentDAO,opensubjectDAO);
        ApplicationPeriodService applicationPeriodService = new ApplicationPeriodService(applicationPeriodDAO, opensubjectDAO);



        /* 멤버 CRU */

        //관리자,교수,학생 삽입 (하드코딩)
         /*memberService.insertManger();
         memberService.insertprofessor();
         memberService.insertStudent();*/

        //교수,학생 조회
       // memberService.selectStudent();
       // memberService.selectProfessor();

        //교수 전화번호 바꾸기(김성렬교수님 번호 : 010-1234-1234 --> 010 - 2443 - 3453) + 조회
        /*memberService.updateProfessorPn();
        memberService.selectProfessor();*/

        //학생 이름 바꾸기(박지훈 -> 박영근) + 조회
        /*memberService.updateStudentName();
        memberService.selectStudent();*/




        /* 교과목 CRU */

        //subjectService.insert(); //8과목 삽입

        //subjectService.selectAll(); //전체조회
        //subjectService.selectWithGrade(2); //학년별 조회

        /*subjectService.updateWithName("CS0017","수정된 운영체제");
        subjectService.selectAll(); //과목명 변경 + 조회*/


        /* 개설 교과목 CRU */
        //13과목 개설
        /*openSubjectService.insertOpenSubject("CS0010-01","CS0010","b123","1",3,0,1,false,"화12,목67","D438");
        openSubjectService.insertOpenSubject("CS0010-02","CS0010","b123","1",3,0,2,false,"화89,목89","D438");
        openSubjectService.insertOpenSubject("CS0016-01","CS0016","a123","2",3,0,1,false,"목67,금34","D327");
        openSubjectService.insertOpenSubject("CS0016-02","CS0016","a123","2",3,0,2,false,"목89,금67","D327");
        openSubjectService.insertOpenSubject("CS0017-01","CS0017","b123","2",3,0,1,false,"월6,수12","D327");
        openSubjectService.insertOpenSubject("CS0017-02","CS0017","b123","2",3,0,2,false,"월7,수34","D327");
        openSubjectService.insertOpenSubject("CS0069-01","CS0069","a123","2",3,0,1,false,"수12","D331");
        openSubjectService.insertOpenSubject("CS0069-02","CS0069","b123","2",3,0,2,false,"수34","D331");
        openSubjectService.insertOpenSubject("CS0077-01","CS0077","b123","2",3,0,1,false,"목67,금34","D331");
        openSubjectService.insertOpenSubject("CS0077-02","CS0077","b123","2",3,0,2,false,"수67,금67","D331");
        openSubjectService.insertOpenSubject("CS0027-01","CS0027","a123","3",3,0,1,false,"월67,금12","D438");
        openSubjectService.insertOpenSubject("CS0027-02","CS0027","a123","3",3,0,2,false,"월89,금34","D438");
        openSubjectService.insertOpenSubject("CS0035-01","CS0035","b123","4",3,0,1,false,"월6,수34","D329");
*/

        //openSubjectService.selectAllOpenSubject();
        //openSubjectService.selectByGrade("5");
        //System.out.println(openSubjectService.selectByResProfessor("김선명")); // 김선명 교수님 교수코드 : a123
        //openSubjectService.selectByGradeAndProfessor("2","김선명"); // 김성렬 교수님 교수코드 : b123

        /*openSubjectService.updateOpenSubject("CS0010-01","D330",10);
        openSubjectService.selectAllOpenSubject();*/

        //기본 기간 설정 : 2021-11-01 ~ 2021-11-05

        /*수강신청기간*/
        Date start = Date.valueOf("2021-11-08");
        Date end = Date.valueOf("2021-11-13");


      /* applicationPeriodService.updateApplicationPeriod("2", start, end);
       openSubjectService.selectAllOpenSubject();*/






        /* 수강신청 */
       /* enrollmentService.insert(20180457,"CS0016-01");
        enrollmentService.insert(20180458,"CS0016-01");
        enrollmentService.insert(20180459,"CS0017-01");
        enrollmentService.insert(20180460,"CS0017-02");
        enrollmentService.insert(20180457,"CS0077-02");
        enrollmentService.insert(20180458,"CS0077-02");
        enrollmentService.insert(20180459,"CS0069-02");
        enrollmentService.insert(20180460,"CS0069-01");*/

        //enrollmentService.selectSubjectName(20180457); //본인 수강신청 현황 조회

        /*enrollmentService.delete(20180457,"CS0016-01");
        enrollmentService.selectSubjectName(20180457);*/

        //enrollmentService.insert(20180458,"CS0016-02"); // 수강 신청 중복 예외


        //enrollmentService.insert(20180459,"CS0077-02"); // 현재 인원 3/3              //강의시간 : 수67, 금67
        //enrollmentService.insert(20180460,"CS0077-02"); // 최대 수강 인원 초과 예외

        //enrollmentService.insert(20180459,"CS0016-02"); // 강의 시간 중복 신청 예외     //강의시간 : 목89, 금67
        
        
        //memberService.print신청한교과목수강생("CS0077-02"); //페이징


    }

}