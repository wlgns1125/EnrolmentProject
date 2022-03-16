
package Protocol;

import lombok.Synchronized;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dao.*;
import persistence.dto.LecturePlanDTO;
import persistence.dto.SubjectDTO;
import service.*;

import java.net.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static Protocol.Protocol.*;



public class Server {
    
    //Key 생성
    public static Object 계정생성Key = new Object();
    public static Object 수강신청Key = new Object();
    public static Object 교과목Key = new Object();

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Socket conn = null;
        ServerSocket sSocket = null;

        try{

            sSocket = new ServerSocket(1501,10);
            //* nullexception 발생시  - > 데이터베이스 접속하기 + 포트번호 수정하기
            System.out.println("클라이언트 접속 대기중...");


            while(true){

                conn = sSocket.accept();
                System.out.println("클라이언트 " + conn.getInetAddress().getHostName() + " 가 접속하였습니다.");
                new handler(conn).start();

            }
        }
        catch(IOException e){
            System.err.println("IOException");
        }
        try{
            sSocket.close();
        }
        catch(IOException ioException){
            System.err.println("Unable to close. IOexception");
        }

}


    static class handler extends Thread{

        private Socket conn;


        handler(Socket conn){
            this.conn = conn;
        }



        public void run() {



            //클라이언트에게 전달할 프로토콜 미리 생성
            Protocol ID요청 = new Protocol(PT_LOGIN, Protocol.LOGIN_UT_ID요청);
            Protocol PASSWORD요청 = new Protocol(PT_LOGIN, Protocol.LOGIN_UT_PASSWORD요청);
            Protocol 결과 = new Protocol(PT_LOGIN, Protocol.LOGIN_UT_결과);

            Protocol 관리자계정생성응답 = new Protocol(PT_MANAGER, Protocol.MANAGER_UT_관리자계정_생성_결과);
            Protocol 교수계정생성응답 = new Protocol(PT_MANAGER, Protocol.MANAGER_UT_교수_계정_생성_결과);
            Protocol 학생계정생성응답 = new Protocol(PT_MANAGER, Protocol.MANAGER_UT_학생_계정_생성_결과);
            Protocol 교과목생성응답 = new Protocol(PT_MANAGER, Protocol.MANAGER_UT_교과목_생성_결과);
            Protocol 교과목수정응답 = new Protocol(PT_MANAGER, MANAGER_UT_교과목_수정_결과);
            Protocol 교과목삭제응답 = new Protocol(PT_MANAGER, MANAGER_UT_교과목_삭제_결과);
            Protocol 개설교과목생성응답 = new Protocol(PT_MANAGER, MANAGER_UT_개설교과목_생성_결과);
            Protocol 개설교과목수정응답 = new Protocol(PT_MANAGER, MANAGER_UT_개설교과목_수정_결과);
            Protocol 개설교과목삭제응답 = new Protocol(PT_MANAGER, MANAGER_UT_개설교과목_삭제_결과);
            Protocol 강의계획서입력기간설정응답 = new Protocol(PT_MANAGER, MANAGER_UT_강의계획서_입력기간설정_응답);
            Protocol 수강신청기간설정응답 = new Protocol(PT_MANAGER, MANAGER_UT_학년별_수강신청기간설정_응답);
            Protocol 교수정보조회응답 = new Protocol(PT_MANAGER, MANAGER_UT_교수_정보_조회_응답);
            Protocol 개설교과목조회응답 = new Protocol(PT_MANAGER, MANAGER_UT_개설_교과목_정보_조회_응답);
            Protocol 학생정보조회응답 = new Protocol(PT_MANAGER, MANAGER_UT_학생_정보_조회_응답);
            Protocol 개설교과목조회목록전송 = new Protocol(PT_MANAGER, MANAGER_UT_개설_교과목_정보_조회_목록전송);
            Protocol 교과목목록응답 = new Protocol(Protocol.PT_MANAGER, Protocol.MANAGER_UT_교과목_목록_조회_응답);




            Protocol 교수_개인정보_수정_결과 = new Protocol(PT_PROFESSOR, PROFESSOR_UT_개인정보_수정_결과);
            Protocol 강의계획서생성응답 = new Protocol(PT_PROFESSOR, PROFESSOR_UT_강의계획서_입력_결과);
            Protocol 강의계획서수정응답 = new Protocol(PT_PROFESSOR, PROFESSOR_UT_강의계획서_수정_결과);
            Protocol 교수_개설과목조회 = new Protocol(PT_PROFESSOR, PROFESSOR_UT_교과목_목록_조회_결과);
            Protocol 교수_교과목_수강신청_학생_목록_조회_결과 = new Protocol(PT_PROFESSOR,PROFESSOR_UT_교과목_수강신청_학생_목록_조회_결과);
            Protocol 교수_개설교과목강의계획서조회결과 = new Protocol(PT_PROFESSOR,PROFESSOR_UT_교과목_강의계획서_조회_결과);
            Protocol 교수_강의계획서ID결과 = new Protocol(PT_PROFESSOR,PROFESSOR_UT_강의계획서_ID_결과);
            Protocol 교수_비밀번호_수정_결과 = new Protocol(PT_PROFESSOR, PROFESSOR_UT_비밀번호_수정_결과);
            Protocol 교수시간표조회결과 = new Protocol(PT_PROFESSOR,PROFESSOR_UT_교과목_시간표_조회_결과);
            Protocol 교수_강의계획서입력기간확인응답 = new Protocol(PT_PROFESSOR, PROFESSOR_UT_강의계획서_입력_기간_확인_응답);


            Protocol 학생_개인정보_수정_결과 = new Protocol(PT_STUDENT, STUDENT_UT_개인정보_수정_결과);
            Protocol 수강신청응답 = new Protocol(PT_STUDENT, STUDENT_UT_수강신청_결과);
            Protocol 수강삭제응답 = new Protocol(PT_STUDENT, STUDENT_UT_수강삭제_결과);
            Protocol 개설교과목조회결과 = new Protocol(PT_STUDENT, STUDENT_UT_개설교과목_조회_결과);
            Protocol 학생_선택교과목강의계획서조회결과 = new Protocol(PT_STUDENT,STUDENT_UT_선택교과목_강의계획서_조회_결과);
            Protocol 학생_비밀번호_수정_결과 = new Protocol(PT_STUDENT, STUDENT_UT_비밀번호_수정_결과);
            Protocol 수강신청본인현황결과 = new Protocol(Protocol.PT_STUDENT, Protocol.STUDENT_UT_수강신청_본인현황_결과);
            Protocol 학생시간표조회결과 = new  Protocol(PT_STUDENT,STUDENT_UT_본인시간표_조회_결과);



            //DAO 생성
            MemberDAO memberDAO = new MemberDAO();
            SubjectDAO subjectDAO = new SubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            EnrollmentDAO enrollmentDAO = new EnrollmentDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            OpenSubjectDAO opensubjectDAO = new OpenSubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            ApplicationPeriodDAO applicationPeriodDAO = new ApplicationPeriodDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            LecturePlanDAO lecturePlanDAO = new LecturePlanDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            SyllabusPeriodDAO syllabusPeriodDAO = new SyllabusPeriodDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            Member2DAO memberDAO2 = new Member2DAO(MyBatisConnectionFactory.getSqlSessionFactory());


            //Service 생성
            MemberService memberService = new MemberService(memberDAO,memberDAO2);
            SubjectService subjectService = new SubjectService(subjectDAO);
            OpenSubjectService openSubjectService = new OpenSubjectService(opensubjectDAO);
            EnrollmentService enrollmentService = new EnrollmentService(enrollmentDAO, opensubjectDAO);
            ApplicationPeriodService applicationPeriodService = new ApplicationPeriodService(applicationPeriodDAO, opensubjectDAO);
            SyllabusPeriodService syllabusPeriodService = new SyllabusPeriodService(syllabusPeriodDAO);
            LecturePlanService lecturePlanService = new LecturePlanService(lecturePlanDAO);


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStart;
            Date dateEnd;



            try {
                OutputStream os = conn.getOutputStream();
                InputStream is = conn.getInputStream();

                String id = "";
                String password = "";
                String data = "";

                //로그인 아이디 요청
                os.write(ID요청.getPacket());


                boolean isEnd = false;

                while (!isEnd) {

                    Protocol 수신프로토콜 = new Protocol(PT_LOGIN, UT_UNDEFINED);          // 새 Protocol 객체 생성 (기본 생성자)

                    byte[] buf = 수신프로토콜.getPacket();
                    is.read(buf);
                    int packetType = buf[0];
                    int packetUserType = buf[1];

                    수신프로토콜.setPacket(packetType, packetUserType, buf); // 패킷 타입을 Protocol 객체의 packet 멤버변수에 buf를 복사


                    switch (packetType) {
                        case Protocol.PT_LOGIN:

                            switch (packetUserType) {

                                case Protocol.LOGIN_UT_ID전송:

                                    id = 수신프로토콜.getData();

                                    if (userDAO.isIdExist(id)) {
                                        os.write(PASSWORD요청.getPacket());
                                    } else {   // 실패 메세지 전송 (CODE = 0번 설정하고 패킷 전송)
                                        ID요청.setProtocolCode(0);
                                        os.write(ID요청.getPacket());
                                    }

                                    break;

                                case Protocol.LOGIN_UT_PASSWORD전송:


                                    password = 수신프로토콜.getData();

                                    if (userDAO.isPasswordCorrect(id, password)) { // 패스워드 맞을시
                                        결과.setProtocolCode(1);

                                        if (userDAO.whoAreYou(id).equals("관리자")) { //관리자
                                            결과.setProtocolFlag(1);
                                        } else if (userDAO.whoAreYou(id).equals("학생")) { //학생
                                            결과.setProtocolFlag(2);
                                        } else if (userDAO.whoAreYou(id).equals("교수")) { //교수
                                            결과.setProtocolFlag(3);
                                        }

                                        os.write(결과.getPacket());
                                    } else { // 패스워드 틀릴 시
                                        결과.setProtocolCode(0);
                                        os.write(결과.getPacket());
                                    }

                                    break;

                            }

                            break;

                        case PT_MANAGER:

                            switch (packetUserType) {
                                case Protocol.MANAGER_UT_관리자계정_생성_요청:

                                    synchronized (계정생성Key){

                                        //1.아이디 중복 확인, 2.계정 생성, 3. code에 값을 담아서 결과 전송
                                        data = 수신프로토콜.getData();
                                        String[] 관리자계정정보 = data.split("[|]");
                                        for (int i = 0; i < 관리자계정정보.length; i++) {
                                            System.out.printf("%s\n", 관리자계정정보[i]);
                                        }
                                        if (userDAO.isIdExist(관리자계정정보[0])) {
                                            System.out.println("이미 존재하는 사용자입니다.");
                                            관리자계정생성응답.setProtocolCode(0);
                                            os.write(관리자계정생성응답.getPacket());
                                        } else {
                                            memberService.insertManger(관리자계정정보[0], 관리자계정정보[1], 관리자계정정보[2], 관리자계정정보[3]);
                                            관리자계정생성응답.setProtocolCode(1);
                                            os.write(관리자계정생성응답.getPacket());
                                        }

                                    }

                                    break;
                                case Protocol.MANAGER_UT_교수_계정_생성_요청:

                                    synchronized (계정생성Key){

                                        //1.아이디 중복 확인, 2.계정 생성, 3. code에 값을 담아서 결과 전송
                                        data = 수신프로토콜.getData();
                                        String[] 교수계정정보 = data.split("[|]");
                                        for (int i = 0; i < 교수계정정보.length; i++) {
                                            System.out.printf("%s\n", 교수계정정보[i]);
                                        }
                                        if (userDAO.isIdExist(교수계정정보[0])) {
                                            System.out.println("이미 존재하는 사용자입니다.");
                                            교수계정생성응답.setProtocolCode(0);
                                            os.write(교수계정생성응답.getPacket());
                                        } else {
                                            memberService.insertprofessor(교수계정정보[0], 교수계정정보[1], 교수계정정보[2], 교수계정정보[3], 교수계정정보[4], Integer.parseInt(교수계정정보[5]));
                                            교수계정생성응답.setProtocolCode(1);
                                            os.write(교수계정생성응답.getPacket());
                                        }

                                    }


                                    break;
                                case Protocol.MANAGER_UT_학생_계정_생성_요청:

                                    synchronized (계정생성Key){

                                        //1.아이디 중복 확인, 2.계정 생성, 3. code에 값을 담아서 결과 전송
                                        data = 수신프로토콜.getData();
                                        String[] 학생계정정보 = data.split("[|]");
                                        for (int i = 0; i < 학생계정정보.length; i++) {
                                            System.out.printf("%s\n", 학생계정정보[i]);
                                        }
                                        if (userDAO.isIdExist(학생계정정보[0])) {
                                            System.out.println("이미 존재하는 사용자입니다.");
                                            학생계정생성응답.setProtocolCode(0);
                                            os.write(학생계정생성응답.getPacket());
                                        } else {
                                            memberService.insertStudent(Integer.parseInt(학생계정정보[0]), 학생계정정보[1], 학생계정정보[2], 학생계정정보[3], Integer.parseInt(학생계정정보[4]), Integer.parseInt(학생계정정보[5]), 학생계정정보[6], 학생계정정보[7]);
                                            학생계정생성응답.setProtocolCode(1);
                                            os.write(학생계정생성응답.getPacket());
                                        }

                                    }

                                    break;

                                case MANAGER_UT_교과목_목록_조회_요청:
                                    String temp = "";
                                    temp += subjectService.returnSelectAll();

                                    if (temp.equals("")) {
                                        교과목목록응답.setProtocolCode(0);
                                        os.write(교과목목록응답.getPacket());
                                    }
                                    else {
                                        교과목목록응답.setProtocolCode(1);
                                        int dataLen = temp.getBytes().length;
                                        if(dataLen > 2000){
                                            int devide = (int)Math.ceil((double)dataLen/2000);
                                            int i = 0;
                                            for(; i < devide - 1; i++){//마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                교과목목록응답.setProtocolFlag(1);
                                                교과목목록응답.setProtocolLast(0);
                                                교과목목록응답.setProtocolSeq(i);
                                                System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                교과목목록응답.setByteData(tempBuf,2000);
                                                os.write(교과목목록응답.getPacket());
                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            교과목목록응답.setProtocolFlag(1);
                                            교과목목록응답.setProtocolLast(1);
                                            교과목목록응답.setProtocolSeq(i);
                                            int tempSize = temp.getBytes().length - i * 2000;
                                            System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            교과목목록응답.setByteData(tempBuf,tempSize);
                                            os.write(교과목목록응답.getPacket());
                                        } else{ // dataLen <= 2000
                                            교과목목록응답.setData(temp);
                                            교과목목록응답.setProtocolFlag(0);
                                            os.write(교과목목록응답.getPacket());
                                        }
                                    }
                                    break;

                                case Protocol.MANAGER_UT_개설_교과목_정보_조회_요청:

                                    temp = openSubjectService.selectAllOpenSubjectOnlyName();

                                    if (temp.equals("")) {
                                        개설교과목조회목록전송.setProtocolCode(0);
                                        os.write(개설교과목조회목록전송.getPacket());
                                    } else {
                                        개설교과목조회목록전송.setProtocolCode(1);

                                        int dataLen = temp.getBytes().length;

                                        if(dataLen > 2000){

                                            int devide = (int)Math.ceil((double)dataLen/2000);

                                            int i = 0;


                                            for(; i < devide - 1; i++){ //마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                개설교과목조회목록전송.setProtocolFlag(1);
                                                개설교과목조회목록전송.setProtocolLast(0);
                                                개설교과목조회목록전송.setProtocolSeq(i);
                                                System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                개설교과목조회목록전송.setByteData(tempBuf,2000);
                                                os.write(개설교과목조회목록전송.getPacket());

                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            개설교과목조회목록전송.setProtocolFlag(1);
                                            개설교과목조회목록전송.setProtocolLast(1);
                                            개설교과목조회목록전송.setProtocolSeq(i);
                                            int tempSize = temp.getBytes().length - i * 2000;
                                            System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            개설교과목조회목록전송.setByteData(tempBuf,tempSize);
                                            os.write(개설교과목조회목록전송.getPacket());

                                        }else{ // dataLen <= 2000
                                            개설교과목조회목록전송.setData(temp);
                                            개설교과목조회목록전송.setProtocolFlag(0);
                                            os.write(개설교과목조회목록전송.getPacket());
                                        }

                                    }

                                    break;



                                case Protocol.MANAGER_UT_개설_교과목_정보_조회_목록선택:
                                    data = 수신프로토콜.getData();
                                    String sData = "";

                                    if(opensubjectDAO.isExist(data))
                                    {
                                        개설교과목조회응답.setProtocolCode(1);
                                        sData = openSubjectService.selectByID(data);
                                        개설교과목조회응답.setData(sData);
                                        os.write(개설교과목조회응답.getPacket());
                                    }
                                    else
                                    {
                                        개설교과목조회응답.setProtocolCode(0);
                                        개설교과목조회응답.setData("개설되지 않은 교과목입니다");
                                        os.write(개설교과목조회응답.getPacket());
                                    }
                                    break;


                                case MANAGER_UT_교과목_생성_요청:

                                    synchronized(교과목Key){

                                    data = 수신프로토콜.getData();
                                    String[] 교과목생성정보 = data.split("[|]");

                                    if (subjectDAO.subjectIsExist(교과목생성정보[0])) {
                                        System.out.println("이미 존재하는 교과목입니다.");
                                        교과목생성응답.setProtocolCode(0);
                                        os.write(교과목생성응답.getPacket());
                                    } else {
                                        SubjectDTO subjectDTO = new SubjectDTO(교과목생성정보[0], 교과목생성정보[1], Integer.parseInt(교과목생성정보[2]), Integer.parseInt(교과목생성정보[3]), 교과목생성정보[4], Integer.parseInt(교과목생성정보[5]));
                                        subjectDAO.insert(subjectDTO);
                                        교과목생성응답.setProtocolCode(1);
                                        os.write(교과목생성응답.getPacket());
                                    }

                                }

                                    break;

                                case MANAGER_UT_교과목_수정_요청:

                                    synchronized(교과목Key){

                                        data = 수신프로토콜.getData();
                                        String[] 교과목수정정보 = data.split("[|]");

                                        if (!subjectDAO.subjectIsExist(교과목수정정보[0])) {
                                            System.out.println("존재하지 않는 교과목입니다.");
                                            교과목수정응답.setProtocolCode(0);
                                            os.write(교과목수정응답.getPacket());
                                        } else {
                                            switch (Integer.parseInt(교과목수정정보[1])) {
                                                case 0:
                                                    subjectService.updateWithName(교과목수정정보[0], 교과목수정정보[2]);
                                                    break;
                                                case 1:
                                                    subjectService.updateWithGrades(교과목수정정보[0], Integer.parseInt(교과목수정정보[2]));
                                                    break;
                                                case 2:
                                                    subjectService.updateWithGrade(교과목수정정보[0], Integer.parseInt(교과목수정정보[2]));
                                                    break;
                                                case 3:
                                                    subjectService.updateWithSubjectClassify(교과목수정정보[0], 교과목수정정보[2]);
                                                    break;
                                                case 4:
                                                    subjectService.updateWithSemester(교과목수정정보[0], Integer.parseInt(교과목수정정보[2]));
                                                    break;
                                            }
                                            교과목수정응답.setProtocolCode(1);
                                            os.write(교과목수정응답.getPacket());
                                        }

                                    }


                                    break;

                                case MANAGER_UT_교과목_삭제_요청:

                                    synchronized(교과목Key){

                                        data = 수신프로토콜.getData();
                                        String[] 교과목삭제정보 = data.split("[|]");

                                        if (!subjectDAO.subjectIsExist(교과목삭제정보[0])) {
                                            System.out.println("존재하지 않는 교과목입니다.");
                                            교과목삭제응답.setProtocolCode(0);
                                            os.write(교과목삭제응답.getPacket());
                                        } else {
                                            SubjectDTO subjectDTO = new SubjectDTO();
                                            subjectDTO.set교과목ID(교과목삭제정보[0]);
                                            subjectDAO.delete(subjectDTO);
                                            교과목삭제응답.setProtocolCode(1);
                                            os.write(교과목삭제응답.getPacket());
                                        }

                                    }

                                    break;

                                case Protocol.MANAGER_UT_개설교과목_생성_요청:

                                    synchronized(교과목Key){

                                        data = 수신프로토콜.getData();
                                        String[] 개설교과목생성정보 = data.split("[|]");
                                        String 개설과목ID = 개설교과목생성정보[0]+"-"+개설교과목생성정보[4];
                                        if(subjectService.isSubjectExist(개설교과목생성정보[0]))
                                        {
                                            if (opensubjectDAO.isOpenSubjectExist(개설과목ID)) {
                                                System.out.println("이미 존재하는 교과목입니다.");
                                                개설교과목생성응답.setProtocolCode(0);
                                                os.write(개설교과목생성응답.getPacket());
                                            } else {
                                                if(memberDAO2.isIdExist(개설교과목생성정보[1]))
                                                {
                                                    openSubjectService.insertOpenSubject(개설과목ID,개설교과목생성정보[0], 개설교과목생성정보[1], 개설교과목생성정보[2], Integer.parseInt(개설교과목생성정보[3]), Integer.parseInt(개설교과목생성정보[4]), 개설교과목생성정보[5], 개설교과목생성정보[6]);
                                                    개설교과목생성응답.setProtocolCode(1);
                                                    os.write(개설교과목생성응답.getPacket());
                                                }
                                                else
                                                {
                                                    개설교과목생성응답.setProtocolCode(3);
                                                    os.write(개설교과목생성응답.getPacket());
                                                }
                                            }
                                        }
                                        else
                                        {
                                            개설교과목생성응답.setProtocolCode(2);
                                            os.write(개설교과목생성응답.getPacket());
                                        }

                                    }

                                    break;

                                case Protocol.MANAGER_UT_개설교과목_수정_요청:

                                    synchronized(교과목Key){

                                        data = 수신프로토콜.getData();
                                        String[] 개설교과목수정정보 = data.split("[|]");

                                        if (!opensubjectDAO.isOpenSubjectExist(개설교과목수정정보[0])) {
                                            System.out.println("존재하지 않는 개설교과목입니다.");
                                            개설교과목수정응답.setProtocolCode(0);
                                            os.write(개설교과목수정응답.getPacket());
                                        } else {
                                            switch (Integer.parseInt(개설교과목수정정보[1])) {
                                                case 0:
                                                    openSubjectService.updateProffessor(개설교과목수정정보[0], 개설교과목수정정보[2]);
                                                    break;
                                                case 1:
                                                    openSubjectService.updateGrade(개설교과목수정정보[0], 개설교과목수정정보[2]);
                                                    break;
                                                case 2:
                                                    openSubjectService.updateMax(개설교과목수정정보[0], Integer.parseInt(개설교과목수정정보[2]));
                                                    break;
                                                case 3:
                                                    openSubjectService.updateApplyNum(개설교과목수정정보[0], Integer.parseInt(개설교과목수정정보[2]));
                                                    break;
                                                case 4:
                                                    openSubjectService.updateDistribution(개설교과목수정정보[0], Integer.parseInt(개설교과목수정정보[2]));
                                                    break;
                                                case 5:
                                                    openSubjectService.updateValid(개설교과목수정정보[0], Boolean.parseBoolean(개설교과목수정정보[2]));
                                                    break;
                                                case 6:
                                                    openSubjectService.updateTime(개설교과목수정정보[0], 개설교과목수정정보[2]);
                                                    break;
                                                case 7:
                                                    openSubjectService.updateRoom(개설교과목수정정보[0], 개설교과목수정정보[2]);
                                                    break;
                                            }
                                            개설교과목수정응답.setProtocolCode(1);
                                            os.write(개설교과목수정응답.getPacket());
                                        }

                                    }


                                    break;


                                case Protocol.MANAGER_UT_개설교과목_삭제_요청:

                                    synchronized(교과목Key) {

                                        data = 수신프로토콜.getData();
                                        String[] 개설교과목삭제정보 = data.split("[|]");

                                        if (!opensubjectDAO.isOpenSubjectExist(개설교과목삭제정보[0])) {
                                            System.out.println("존재하지 않는 개설교과목입니다.");
                                            개설교과목삭제응답.setProtocolCode(0);
                                            os.write(개설교과목삭제응답.getPacket());
                                        } else {
                                            opensubjectDAO.delete(개설교과목삭제정보[0]);
                                            개설교과목삭제응답.setProtocolCode(1);
                                            os.write(개설교과목삭제응답.getPacket());
                                        }
                                    }
                                    break;

                                case Protocol.MANAGER_UT_강의계획서_입력기간설정_요청:

                                    synchronized (교과목Key){

                                        data = 수신프로토콜.getData();
                                        String[] 강의계획서입력기간설정정보 = data.split("[|]");
                                        String[] 연도학기 = 강의계획서입력기간설정정보[1].split("-");

                                        switch(Integer.parseInt(강의계획서입력기간설정정보[0]))
                                        {
                                            case 0 :
                                                if (syllabusPeriodDAO.isDateExist(강의계획서입력기간설정정보[1])) {
                                                    System.out.println("해당 기간은 이미 설정되어 있습니다. 확인해주세요");
                                                    강의계획서입력기간설정응답.setProtocolCode(0);
                                                    os.write(강의계획서입력기간설정응답.getPacket());
                                                } else {
                                                    dateStart = new Date(dateFormat.parse(연도학기[0]+"-"+강의계획서입력기간설정정보[2]).getTime());
                                                    dateEnd = new Date(dateFormat.parse(연도학기[0]+"-"+강의계획서입력기간설정정보[3]).getTime());

                                                    syllabusPeriodService.insertSyllabusPeriod(강의계획서입력기간설정정보[1], dateStart, dateEnd);
                                                    강의계획서입력기간설정응답.setProtocolCode(1);
                                                    os.write(강의계획서입력기간설정응답.getPacket());
                                                }
                                                break;
                                            case 1 :
                                                if (!syllabusPeriodDAO.isDateExist(강의계획서입력기간설정정보[1])) {
                                                    System.out.println("해당 기간은 설정되어있지 않습니다. 연도와 학기를 추가해주세요");
                                                    강의계획서입력기간설정응답.setProtocolCode(0);
                                                    os.write(강의계획서입력기간설정응답.getPacket());
                                                } else {
                                                    dateStart = new Date(dateFormat.parse(연도학기[0]+"-"+강의계획서입력기간설정정보[2]).getTime());
                                                    dateEnd = new Date(dateFormat.parse(연도학기[0]+"-"+강의계획서입력기간설정정보[3]).getTime());

                                                    syllabusPeriodService.updateSyllabusPeriod(강의계획서입력기간설정정보[1], dateStart, dateEnd);
                                                    강의계획서입력기간설정응답.setProtocolCode(1);
                                                    os.write(강의계획서입력기간설정응답.getPacket());
                                                }
                                                break;
                                            case 2 :
                                                syllabusPeriodDAO.selectAllSyllabusPeriod();
                                                강의계획서입력기간설정응답.setProtocolCode(1);
                                                os.write(강의계획서입력기간설정응답.getPacket());
                                                break;
                                        }

                                    }


                                    break;
                                case Protocol.MANAGER_UT_학년별_수강신청기간설정_요청:
                                    data = 수신프로토콜.getData();
                                    String[] 수강신청기간설정정보 = data.split("[|]");

                                    dateStart = new Date(dateFormat.parse(수강신청기간설정정보[1]).getTime());
                                    dateEnd = new Date(dateFormat.parse(수강신청기간설정정보[2]).getTime());
                                    if(1<=Integer.parseInt(수강신청기간설정정보[0])&& Integer.parseInt(수강신청기간설정정보[0])<=4)
                                    {
                                        applicationPeriodService.updateApplicationPeriod(수강신청기간설정정보[0],dateStart, dateEnd);
                                        수강신청기간설정응답.setProtocolCode(1);
                                        os.write(수강신청기간설정응답.getPacket());
                                    }
                                    else
                                    {
                                        System.out.println("해당하는 학년이 없습니다");
                                        수강신청기간설정응답.setProtocolCode(0);
                                        os.write(수강신청기간설정응답.getPacket());
                                    }

                                    break;
                                case MANAGER_UT_학생_정보_조회_요청:
                                    temp = "";
                                    temp += memberService.selectStudent2();

                                    if (temp.equals("")) {
                                        학생정보조회응답.setProtocolCode(0);
                                    } else {
                                        학생정보조회응답.setProtocolCode(1);
                                        int dataLen = temp.getBytes().length;
                                        if(dataLen > 2000){
                                            int devide = (int)Math.ceil((double)dataLen/2000);
                                            int i = 0;
                                            for(; i < devide - 1; i++){//마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                학생정보조회응답.setProtocolFlag(1);
                                                학생정보조회응답.setProtocolLast(0);
                                                학생정보조회응답.setProtocolSeq(i);
                                                System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                학생정보조회응답.setByteData(tempBuf,2000);
                                                os.write(학생정보조회응답.getPacket());
                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            학생정보조회응답.setProtocolFlag(1);
                                            학생정보조회응답.setProtocolLast(1);
                                            학생정보조회응답.setProtocolSeq(i);
                                            int tempSize = temp.getBytes().length - i * 2000;
                                            System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            학생정보조회응답.setByteData(tempBuf,tempSize);
                                            os.write(학생정보조회응답.getPacket());
                                        } else{ // dataLen <= 2000
                                            학생정보조회응답.setData(temp);
                                            학생정보조회응답.setProtocolFlag(0);
                                            os.write(학생정보조회응답.getPacket());
                                        }

                                    }

                                    break;

                                case MANAGER_UT_교수_정보_조회_요청:
                                    String temp2 = "";
                                    temp2 += memberService.selectProfessor2();

                                    if (temp2.equals("")) {
                                        교수정보조회응답.setProtocolCode(0);
                                    } else {
                                        교수정보조회응답.setProtocolCode(1); int dataLen = temp2.getBytes().length;
                                        if(dataLen > 2000){
                                            int devide = (int)Math.ceil((double)dataLen/2000);
                                            int i = 0;
                                            for(; i < devide - 1; i++){//마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                교수정보조회응답.setProtocolFlag(1);
                                                교수정보조회응답.setProtocolLast(0);
                                                교수정보조회응답.setProtocolSeq(i);
                                                System.arraycopy(temp2.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                교수정보조회응답.setByteData(tempBuf,2000);
                                                os.write(교수정보조회응답.getPacket());
                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            교수정보조회응답.setProtocolFlag(1);
                                            교수정보조회응답.setProtocolLast(1);
                                            교수정보조회응답.setProtocolSeq(i);
                                            int tempSize = temp2.getBytes().length - i * 2000;
                                            System.arraycopy(temp2.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            교수정보조회응답.setByteData(tempBuf,tempSize);
                                            os.write(교수정보조회응답.getPacket());
                                        } else{ // dataLen <= 2000
                                            교수정보조회응답.setData(temp2);
                                            교수정보조회응답.setProtocolFlag(0);
                                            os.write(교수정보조회응답.getPacket());
                                        }

                                    }

                                    break;


                            }
                            break;

                        case PT_STUDENT:

                            switch(packetUserType) {

                                case STUDENT_UT_개인정보_수정_요청:

                                    data = 수신프로토콜.getData();

                                    if (수신프로토콜.getProtocolFlag() == 1) { //이름
                                        memberDAO.updateStudentName(id, data);
                                    } else if (수신프로토콜.getProtocolFlag() == 2) { //전화번호
                                        memberDAO.updateStudent전화번호(id, data);
                                    } else if (수신프로토콜.getProtocolFlag() == 3) { //이메일
                                        memberDAO.updateStudent이메일(id, data);
                                    }

                                    학생_개인정보_수정_결과.setProtocolCode(1);
                                    os.write(학생_개인정보_수정_결과.getPacket());

                                    break;

                                case STUDENT_UT_수강신청_요청:

                                    synchronized(수강신청Key){
                                        data = 수신프로토콜.getData();
                                        String 개설과목Id = data;
                                        System.out.printf("%s\n", 개설과목Id);
                                        // insert함수 : 실패하면 0 성공하면 1 return
                                        int code = enrollmentService.insert(Integer.parseInt(id), 개설과목Id);
                                        수강신청응답.setProtocolCode(code);
                                        os.write(수강신청응답.getPacket());
                                    }
                                    break;

                                case STUDENT_UT_수강삭제_요청:

                                    synchronized(수강신청Key){

                                        data = 수신프로토콜.getData();
                                        String 개설과목Id = data;
                                        System.out.printf("%s\n", 개설과목Id);

                                        if (!enrollmentService.select(Integer.parseInt(id), 개설과목Id)) { //삭제하려는데 없을 경우
                                            System.out.println("수강신청 정보 없음");
                                            수강삭제응답.setProtocolCode(0);
                                        } else {
                                            enrollmentService.delete(Integer.parseInt(id), 개설과목Id);
                                            System.out.println("수강취소 완료");
                                            수강삭제응답.setProtocolCode(1);
                                        }
                                        os.write(수강삭제응답.getPacket());

                                    }

                                    break;


                                case STUDENT_UT_수강신청_본인현황_요청:

                                    data = 수신프로토콜.getData();
                                    int 학번 = Integer.parseInt(data);

                                    String temp3 = "";
                                    temp3 += enrollmentService.selectSubjectName(학번);

                                    if (temp3.equals("")) {
                                        수강신청본인현황결과.setProtocolCode(0);
                                        os.write(수강신청본인현황결과.getPacket());
                                    } else {
                                        수강신청본인현황결과.setProtocolCode(1);

                                        int dataLen = temp3.getBytes().length;

                                        if (dataLen > 2000) {

                                            int devide = (int) Math.ceil((double) dataLen / 2000);

                                            int i = 0;


                                            for (; i < devide - 1; i++) { //마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                수강신청본인현황결과.setProtocolFlag(1);
                                                수강신청본인현황결과.setProtocolLast(0);
                                                수강신청본인현황결과.setProtocolSeq(i);
                                                System.arraycopy(temp3.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                수강신청본인현황결과.setByteData(tempBuf, 2000);
                                                os.write(수강신청본인현황결과.getPacket());

                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            수강신청본인현황결과.setProtocolFlag(1);
                                            수강신청본인현황결과.setProtocolLast(1);
                                            수강신청본인현황결과.setProtocolSeq(i);
                                            int tempSize = temp3.getBytes().length - i * 2000;
                                            System.arraycopy(temp3.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            수강신청본인현황결과.setByteData(tempBuf, tempSize);
                                            os.write(수강신청본인현황결과.getPacket());


                                        } else { // dataLen <= 2000
                                            수강신청본인현황결과.setData(temp3);
                                            수강신청본인현황결과.setProtocolFlag(0);
                                            os.write(수강신청본인현황결과.getPacket());
                                        }

                                    }


                                    break;


                                case STUDENT_UT_개설교과목_조회_요청:

                                    String temp = "";
                                    temp += openSubjectService.selectAllOpenSubject();

                                    if (temp.equals("")) {
                                        개설교과목조회결과.setProtocolCode(0);
                                        os.write(개설교과목조회결과.getPacket());
                                    } else {
                                        개설교과목조회결과.setProtocolCode(1);

                                        int dataLen = temp.getBytes().length;

                                        if (dataLen > 2000) {

                                            int devide = (int) Math.ceil((double) dataLen / 2000);

                                            int i = 0;


                                            for (; i < devide - 1; i++) { //마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                개설교과목조회결과.setProtocolFlag(1);
                                                개설교과목조회결과.setProtocolLast(0);
                                                개설교과목조회결과.setProtocolSeq(i);
                                                System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                개설교과목조회결과.setByteData(tempBuf, 2000);
                                                os.write(개설교과목조회결과.getPacket());

                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            개설교과목조회결과.setProtocolFlag(1);
                                            개설교과목조회결과.setProtocolLast(1);
                                            개설교과목조회결과.setProtocolSeq(i);
                                            int tempSize = temp.getBytes().length - i * 2000;
                                            System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            개설교과목조회결과.setByteData(tempBuf, tempSize);
                                            os.write(개설교과목조회결과.getPacket());


                                        } else { // dataLen <= 2000
                                            개설교과목조회결과.setData(temp);
                                            개설교과목조회결과.setProtocolFlag(0);
                                            os.write(개설교과목조회결과.getPacket());
                                        }

                                    }

                                    break;

                                case STUDENT_UT_비밀번호_수정_요청:

                                    data = 수신프로토콜.getData();

                                    String 수정할비밀번호 = data;
                                    System.out.printf("%s\n", 수정할비밀번호);
                                    memberDAO.학생비밀번호수정(id, 수정할비밀번호);
                                    학생_비밀번호_수정_결과.setProtocolCode(1);
                                    os.write(학생_비밀번호_수정_결과.getPacket());

                                    password = 수정할비밀번호;

                                    break;

                                case STUDENT_UT_본인시간표_조회_요청:
                                    data = 수신프로토콜.getData();
                                    String temp5 = "";
                                    temp5 += openSubjectService.학생본인시간표조회(data);

                                    if (temp5.equals("")) {
                                        학생시간표조회결과.setProtocolCode(0);
                                        os.write(학생시간표조회결과.getPacket());
                                    }
                                    else {
                                        학생시간표조회결과.setProtocolCode(1);
                                        int dataLen = temp5.getBytes().length;
                                        if(dataLen > 2000){
                                            int devide = (int)Math.ceil((double)dataLen/2000);
                                            int i = 0;
                                            for(; i < devide - 1; i++){//마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                학생시간표조회결과.setProtocolFlag(1);
                                                학생시간표조회결과.setProtocolLast(0);
                                                학생시간표조회결과.setProtocolSeq(i);
                                                System.arraycopy(temp5.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                학생시간표조회결과.setByteData(tempBuf,2000);
                                                os.write(학생시간표조회결과.getPacket());
                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            학생시간표조회결과.setProtocolFlag(1);
                                            학생시간표조회결과.setProtocolLast(1);
                                            학생시간표조회결과.setProtocolSeq(i);
                                            int tempSize = temp5.getBytes().length - i * 2000;
                                            System.arraycopy(temp5.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            학생시간표조회결과.setByteData(tempBuf,tempSize);
                                            os.write(학생시간표조회결과.getPacket());
                                        } else{ // dataLen <= 2000
                                            학생시간표조회결과.setData(temp5);
                                            학생시간표조회결과.setProtocolFlag(0);
                                            os.write(학생시간표조회결과.getPacket());
                                        }

                                    }

                                    break;



                                case STUDENT_UT_선택교과목_강의계획서_조회_요청:

                                    data = 수신프로토콜.getData();
                                    temp3 = "";
                                    temp3 += lecturePlanService.selectBySubject(data);

                                    if (temp3.equals("")) {
                                        학생_선택교과목강의계획서조회결과.setProtocolCode(0);
                                        os.write(학생_선택교과목강의계획서조회결과.getPacket());
                                    } else {
                                        학생_선택교과목강의계획서조회결과.setProtocolCode(1);
                                        int dataLen = temp3.getBytes().length;
                                        if (dataLen > 2000) {
                                            int devide = (int) Math.ceil((double) dataLen / 2000);
                                            int i = 0;
                                            for (; i < devide - 1; i++) {//마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                학생_선택교과목강의계획서조회결과.setProtocolFlag(1);
                                                학생_선택교과목강의계획서조회결과.setProtocolLast(0);
                                                학생_선택교과목강의계획서조회결과.setProtocolSeq(i);
                                                System.arraycopy(temp3.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                학생_선택교과목강의계획서조회결과.setByteData(tempBuf, 2000);
                                                os.write(학생_선택교과목강의계획서조회결과.getPacket());
                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            학생_선택교과목강의계획서조회결과.setProtocolFlag(1);
                                            학생_선택교과목강의계획서조회결과.setProtocolLast(1);
                                            학생_선택교과목강의계획서조회결과.setProtocolSeq(i);
                                            int tempSize = temp3.getBytes().length - i * 2000;
                                            System.arraycopy(temp3.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            학생_선택교과목강의계획서조회결과.setByteData(tempBuf, tempSize);
                                            os.write(학생_선택교과목강의계획서조회결과.getPacket());
                                        } else { // dataLen <= 2000
                                            학생_선택교과목강의계획서조회결과.setData(temp3);
                                            학생_선택교과목강의계획서조회결과.setProtocolFlag(0);
                                            os.write(학생_선택교과목강의계획서조회결과.getPacket());
                                        }

                                    }

                                    break;
                            }
                            break;

                        case PT_PROFESSOR:

                            switch (packetUserType) {

                                case PROFESSOR_UT_개인정보_수정_요청:

                                    data = 수신프로토콜.getData();

                                    if (수신프로토콜.getProtocolFlag() == 1) { //이름
                                        memberDAO.updateProfesserName(id, data);
                                    } else if (수신프로토콜.getProtocolFlag() == 2) { //연구실
                                        memberDAO.updateProfesser연구실(id, data);
                                    } else if (수신프로토콜.getProtocolFlag() == 3) { //이메일
                                        memberDAO.updateProfesser이메일(id, data);
                                    } else if (수신프로토콜.getProtocolFlag() == 4) {//전화번호
                                        memberDAO.updateProfessorPn(id, data);
                                    }

                                    교수_개인정보_수정_결과.setProtocolCode(1);
                                    os.write(교수_개인정보_수정_결과.getPacket());

                                    break;

                                case PROFESSOR_UT_강의계획서_입력_요청:

                                    synchronized (교과목Key) {

                                        data = 수신프로토콜.getData();
                                        String[] 강의계획서입력정보 = data.split("[|]");
                                        for (int i = 0; i < 강의계획서입력정보.length; i++) {
                                            System.out.printf("%s\n", 강의계획서입력정보[i]);
                                        }
                                        if (!opensubjectDAO.isExist(강의계획서입력정보[0])) {
                                            System.out.println("존재하지 않는 개설과목Id입니다.");
                                            강의계획서생성응답.setProtocolCode(0);
                                            os.write(강의계획서생성응답.getPacket());
                                        } else {
                                            if (opensubjectDAO.isRightProfessor(강의계획서입력정보[0], id)) {
                                                if (lecturePlanDAO.isLecturePlanExist(강의계획서입력정보[0])) {
                                                    System.out.println("이미 존재하는 강의계획서입니다.");
                                                    강의계획서생성응답.setProtocolCode(1);
                                                    os.write(강의계획서생성응답.getPacket());
                                                } else {
                                                    LecturePlanDTO lecturePlanDTO = new LecturePlanDTO(강의계획서입력정보[0], 강의계획서입력정보[1], 강의계획서입력정보[2], 강의계획서입력정보[3], 강의계획서입력정보[4], 강의계획서입력정보[5]);
                                                    lecturePlanDAO.insertLecturePlan(lecturePlanDTO);
                                                    강의계획서생성응답.setProtocolCode(3);
                                                    os.write(강의계획서생성응답.getPacket());
                                                }
                                            } else {
                                                System.out.println("해당 교수가 담당하는 개설과목이 아닙니다");
                                                강의계획서생성응답.setProtocolCode(2);
                                                os.write(강의계획서생성응답.getPacket());
                                            }
                                        }
                                    }
                                    break;

                                case PROFESSOR_UT_강의계획서_입력_기간_확인_요청: // 11/28

                                    if(syllabusPeriodDAO.isDateValid())
                                    {
                                        System.out.println("가능");
                                        교수_강의계획서입력기간확인응답.setProtocolCode(0);// 0 = 가능, 1 = 불가능
                                        os.write(교수_강의계획서입력기간확인응답.getPacket());
                                    }
                                    else
                                    {
                                        System.out.println("불가");
                                        교수_강의계획서입력기간확인응답.setProtocolCode(1);
                                        os.write(교수_강의계획서입력기간확인응답.getPacket());
                                    }

                                    break;

                                case PROFESSOR_UT_강의계획서_수정_요청:

                                    synchronized (교과목Key) {

                                        data = 수신프로토콜.getData();
                                        String[] 강의계획서수정정보 = data.split("[|]");
                                        for (int i = 0; i < 강의계획서수정정보.length; i++) {
                                            System.out.printf("%s\n", 강의계획서수정정보[i]);
                                        }
                                        if (!lecturePlanDAO.isLecturePlanExist(강의계획서수정정보[0])) {
                                            System.out.println("존재하지 않는 강의계획서입니다.");
                                            강의계획서수정응답.setProtocolCode(0);
                                            os.write(강의계획서수정응답.getPacket());
                                        } else if (!opensubjectDAO.isRightProfessor(강의계획서수정정보[0], id)) {
                                            System.out.println("해당 교수가 담당하는 개설과목이 아닙니다");
                                            강의계획서생성응답.setProtocolCode(1);
                                            os.write(강의계획서생성응답.getPacket());
                                        } else {
                                            LecturePlanDTO lecturePlanDTO = new LecturePlanDTO(강의계획서수정정보[0], 강의계획서수정정보[1], 강의계획서수정정보[2], 강의계획서수정정보[3], 강의계획서수정정보[4], 강의계획서수정정보[5]);
                                            lecturePlanDAO.updateLecturePlan(lecturePlanDTO);
                                            강의계획서수정응답.setProtocolCode(2);
                                            os.write(강의계획서수정응답.getPacket());
                                        }

                                    }
                                    break;


                                case PROFESSOR_UT_교과목_목록_조회_요청:

                                    data = 수신프로토콜.getData();
                                    String temp = "";
                                    temp += openSubjectService.selectByProfessorID(data);

                                    if (temp.equals("")) {
                                        교수_개설과목조회.setProtocolCode(0);
                                        os.write(교수_개설과목조회.getPacket());
                                    }
                                    else {
                                        교수_개설과목조회.setProtocolCode(1);
                                        int dataLen = temp.getBytes().length;
                                        if(dataLen > 2000){
                                            int devide = (int)Math.ceil((double)dataLen/2000);
                                            int i = 0;
                                            for(; i < devide - 1; i++){//마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                교수_개설과목조회.setProtocolFlag(1);
                                                교수_개설과목조회.setProtocolLast(0);
                                                교수_개설과목조회.setProtocolSeq(i);
                                                System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                교수_개설과목조회.setByteData(tempBuf,2000);
                                                os.write(교수_개설과목조회.getPacket());
                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            교수_개설과목조회.setProtocolFlag(1);
                                            교수_개설과목조회.setProtocolLast(1);
                                            교수_개설과목조회.setProtocolSeq(i);
                                            int tempSize = temp.getBytes().length - i * 2000;
                                            System.arraycopy(temp.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            교수_개설과목조회.setByteData(tempBuf,tempSize);
                                            os.write(교수_개설과목조회.getPacket());
                                        } else{ // dataLen <= 2000
                                            교수_개설과목조회.setData(temp);
                                            교수_개설과목조회.setProtocolFlag(0);
                                            os.write(교수_개설과목조회.getPacket());
                                        }

                                    }

                                    break;

                                case PROFESSOR_UT_교과목_수강신청_학생_목록_조회_요청:

                                    data = 수신프로토콜.getData();
                                    String temp2 = "";
                                    temp2 += enrollmentService.selectByEnrollmentStudent(data);

                                    if (temp2.equals("")) {
                                        교수_교과목_수강신청_학생_목록_조회_결과.setProtocolCode(0);
                                        os.write(교수_교과목_수강신청_학생_목록_조회_결과.getPacket());
                                    }
                                    else {
                                        교수_교과목_수강신청_학생_목록_조회_결과.setProtocolCode(1);
                                        int dataLen = temp2.getBytes().length;
                                        if(dataLen > 2000){
                                            int devide = (int)Math.ceil((double)dataLen/2000);
                                            int i = 0;
                                            for(; i < devide - 1; i++){//마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                교수_교과목_수강신청_학생_목록_조회_결과.setProtocolFlag(1);
                                                교수_교과목_수강신청_학생_목록_조회_결과.setProtocolLast(0);
                                                교수_교과목_수강신청_학생_목록_조회_결과.setProtocolSeq(i);
                                                System.arraycopy(temp2.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                교수_교과목_수강신청_학생_목록_조회_결과.setByteData(tempBuf,2000);
                                                os.write(교수_교과목_수강신청_학생_목록_조회_결과.getPacket());
                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            교수_교과목_수강신청_학생_목록_조회_결과.setProtocolFlag(1);
                                            교수_교과목_수강신청_학생_목록_조회_결과.setProtocolLast(1);
                                            교수_교과목_수강신청_학생_목록_조회_결과.setProtocolSeq(i);
                                            int tempSize = temp2.getBytes().length - i * 2000;
                                            System.arraycopy(temp2.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            교수_교과목_수강신청_학생_목록_조회_결과.setByteData(tempBuf,tempSize);
                                            os.write(교수_교과목_수강신청_학생_목록_조회_결과.getPacket());
                                        } else{ // dataLen <= 2000
                                            교수_교과목_수강신청_학생_목록_조회_결과.setData(temp2);
                                            교수_교과목_수강신청_학생_목록_조회_결과.setProtocolFlag(0);
                                            os.write(교수_교과목_수강신청_학생_목록_조회_결과.getPacket());
                                        }

                                    }

                                    break;

                                case PROFESSOR_UT_교과목_강의계획서_조회_요청:
                                    data = 수신프로토콜.getData();
                                    String temp4 = "";
                                    temp4 += lecturePlanService.selectBySubject(data);

                                    if (temp4.equals("")) {
                                        교수_개설교과목강의계획서조회결과.setProtocolCode(0);
                                        os.write(교수_개설교과목강의계획서조회결과.getPacket());
                                    }
                                    else {
                                        교수_개설교과목강의계획서조회결과.setProtocolCode(1);
                                        int dataLen = temp4.getBytes().length;
                                        if(dataLen > 2000){
                                            int devide = (int)Math.ceil((double)dataLen/2000);
                                            int i = 0;
                                            for(; i < devide - 1; i++){//마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                교수_개설교과목강의계획서조회결과.setProtocolFlag(1);
                                                교수_개설교과목강의계획서조회결과.setProtocolLast(0);
                                                교수_개설교과목강의계획서조회결과.setProtocolSeq(i);
                                                System.arraycopy(temp4.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                교수_개설교과목강의계획서조회결과.setByteData(tempBuf,2000);
                                                os.write(교수_개설교과목강의계획서조회결과.getPacket());
                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            교수_개설교과목강의계획서조회결과.setProtocolFlag(1);
                                            교수_개설교과목강의계획서조회결과.setProtocolLast(1);
                                            교수_개설교과목강의계획서조회결과.setProtocolSeq(i);
                                            int tempSize = temp4.getBytes().length - i * 2000;
                                            System.arraycopy(temp4.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            교수_개설교과목강의계획서조회결과.setByteData(tempBuf,tempSize);
                                            os.write(교수_개설교과목강의계획서조회결과.getPacket());
                                        } else{ // dataLen <= 2000
                                            교수_개설교과목강의계획서조회결과.setData(temp4);
                                            교수_개설교과목강의계획서조회결과.setProtocolFlag(0);
                                            os.write(교수_개설교과목강의계획서조회결과.getPacket());
                                        }

                                    }

                                    break;

                                case PROFESSOR_UT_비밀번호_수정_요청:

                                    data = 수신프로토콜.getData();

                                    String 수정할비밀번호 = data;
                                    System.out.printf("%s\n", 수정할비밀번호);
                                    memberDAO.교수비밀번호수정(id, 수정할비밀번호);

                                    교수_비밀번호_수정_결과.setProtocolCode(1);
                                    os.write(교수_비밀번호_수정_결과.getPacket());

                                    password = 수정할비밀번호;

                                    break;


                                case PROFESSOR_UT_교과목_시간표_조회_요청:
                                    data = 수신프로토콜.getData();
                                    String temp5 = "";
                                    temp5 += openSubjectService.교수본인시간표조회(data);

                                    if (temp5.equals("")) {
                                        교수시간표조회결과.setProtocolCode(0);
                                        os.write(교수시간표조회결과.getPacket());
                                    }
                                    else {
                                        교수시간표조회결과.setProtocolCode(1);
                                        int dataLen = temp5.getBytes().length;
                                        if(dataLen > 2000){
                                            int devide = (int)Math.ceil((double)dataLen/2000);
                                            int i = 0;
                                            for(; i < devide - 1; i++){//마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                교수시간표조회결과.setProtocolFlag(1);
                                                교수시간표조회결과.setProtocolLast(0);
                                                교수시간표조회결과.setProtocolSeq(i);
                                                System.arraycopy(temp5.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                교수시간표조회결과.setByteData(tempBuf,2000);
                                                os.write(교수시간표조회결과.getPacket());
                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            교수시간표조회결과.setProtocolFlag(1);
                                            교수시간표조회결과.setProtocolLast(1);
                                            교수시간표조회결과.setProtocolSeq(i);
                                            int tempSize = temp5.getBytes().length - i * 2000;
                                            System.arraycopy(temp5.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            교수시간표조회결과.setByteData(tempBuf,tempSize);
                                            os.write(교수시간표조회결과.getPacket());
                                        } else{ // dataLen <= 2000
                                            교수시간표조회결과.setData(temp5);
                                            교수시간표조회결과.setProtocolFlag(0);
                                            os.write(교수시간표조회결과.getPacket());
                                        }

                                    }

                                    break;


                                case PROFESSOR_UT_강의계획서_ID_요청:

                                    String temp3 = "";
                                    temp3 += lecturePlanService.selectID();

                                    if (temp3.equals("")) {
                                        교수_강의계획서ID결과.setProtocolCode(0);
                                        os.write(교수_강의계획서ID결과.getPacket());
                                    } else {
                                        교수_강의계획서ID결과.setProtocolCode(1);

                                        int dataLen = temp3.getBytes().length;

                                        if (dataLen > 2000) {

                                            int devide = (int) Math.ceil((double) dataLen / 2000);

                                            int i = 0;


                                            for (; i < devide - 1; i++) { //마지막 패킷의 이전 패킷까지 전송
                                                byte[] tempBuf = new byte[2000];
                                                교수_강의계획서ID결과.setProtocolFlag(1);
                                                교수_강의계획서ID결과.setProtocolLast(0);
                                                교수_강의계획서ID결과.setProtocolSeq(i);
                                                System.arraycopy(temp3.getBytes(), i * 2000, tempBuf, 0, 2000);
                                                교수_강의계획서ID결과.setByteData(tempBuf, 2000);
                                                os.write(교수_강의계획서ID결과.getPacket());

                                            }

                                            //마지막패킷 전송
                                            byte[] tempBuf = new byte[2000];
                                            교수_강의계획서ID결과.setProtocolFlag(1);
                                            교수_강의계획서ID결과.setProtocolLast(1);
                                            교수_강의계획서ID결과.setProtocolSeq(i);
                                            int tempSize = temp3.getBytes().length - i * 2000;
                                            System.arraycopy(temp3.getBytes(), i * 2000, tempBuf, 0, tempSize);
                                            교수_강의계획서ID결과.setByteData(tempBuf, tempSize);
                                            os.write(교수_강의계획서ID결과.getPacket());


                                        } else { // dataLen <= 2000
                                            교수_강의계획서ID결과.setData(temp3);
                                            교수_강의계획서ID결과.setProtocolFlag(0);
                                            os.write(교수_강의계획서ID결과.getPacket());
                                        }

                                    }




                                    break;


                            }

                            break;


                        case PT_EXIT:

                            isEnd = true;
                            System.out.println(conn.getInetAddress().getHostName() + "가 종료했습니다.");
                            conn.close();
                            break;

                    }
                }

            }
            catch(IOException | ParseException e){
                System.out.println("IOException on socket : " + e);
                e.printStackTrace();
            }


        }

    }


}






