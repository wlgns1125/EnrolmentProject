

package Protocol;
import java.io.*;

public class Protocol {

    public static final int PT_UNDEFINED = -1;
    public static final int PT_LOGIN = 0;
    public static final int PT_MANAGER = 1;
    public static final int PT_STUDENT = 2;
    public static final int PT_PROFESSOR = 3;
    public static final int PT_EXIT = 4;
    public static final int UT_UNDEFINED = -1;
    public static final int LOGIN_UT_ID요청 = 0;
    public static final int LOGIN_UT_ID전송 = 1;
    public static final int LOGIN_UT_PASSWORD요청 = 2;
    public static final int LOGIN_UT_PASSWORD전송 = 3;
    public static final int LOGIN_UT_결과 = 4;

    public static final int MANAGER_UT_관리자계정_생성_요청 = 0;
    public static final int MANAGER_UT_관리자계정_생성_결과 = 1;
    public static final int MANAGER_UT_교수_계정_생성_요청 = 2;
    public static final int MANAGER_UT_교수_계정_생성_결과 = 3;
    public static final int MANAGER_UT_학생_계정_생성_요청 = 4;
    public static final int MANAGER_UT_학생_계정_생성_결과 = 5;
    public static final int MANAGER_UT_교과목_생성_요청 = 6;
    public static final int MANAGER_UT_교과목_생성_결과 = 7;
    public static final int MANAGER_UT_교과목_수정_요청 = 8;
    public static final int MANAGER_UT_교과목_수정_결과 = 9;
    public static final int MANAGER_UT_교과목_삭제_요청 = 10;
    public static final int MANAGER_UT_교과목_삭제_결과 = 11;
    public static final int MANAGER_UT_개설교과목_생성_요청 = 12;
    public static final int MANAGER_UT_개설교과목_생성_결과 = 13;
    public static final int MANAGER_UT_개설교과목_수정_요청 = 14;
    public static final int MANAGER_UT_개설교과목_수정_결과 = 15;
    public static final int MANAGER_UT_개설교과목_삭제_요청 = 16;
    public static final int MANAGER_UT_개설교과목_삭제_결과 = 17;
    public static final int MANAGER_UT_강의계획서_입력기간설정_요청 = 18;
    public static final int MANAGER_UT_강의계획서_입력기간설정_응답 = 19;
    public static final int MANAGER_UT_학년별_수강신청기간설정_요청 = 20;
    public static final int MANAGER_UT_학년별_수강신청기간설정_응답 = 21;
    public static final int MANAGER_UT_교수_정보_조회_요청 = 22;
    public static final int MANAGER_UT_교수_정보_조회_응답 = 23;
    public static final int MANAGER_UT_학생_정보_조회_요청 = 24;
    public static final int MANAGER_UT_학생_정보_조회_응답 = 25;
    public static final int MANAGER_UT_개설_교과목_정보_조회_요청 = 26;
    public static final int MANAGER_UT_개설_교과목_정보_조회_응답 = 27;
    public static final int MANAGER_UT_개설_교과목_정보_조회_목록전송 = 28;
    public static final int MANAGER_UT_개설_교과목_정보_조회_목록선택 = 29;
    public static final int MANAGER_UT_교과목_목록_조회_요청 = 30;
    public static final int MANAGER_UT_교과목_목록_조회_응답 = 31;



    public static final int PROFESSOR_UT_개인정보_수정_요청 = 0;
    public static final int PROFESSOR_UT_개인정보_수정_결과 = 1;
    public static final int PROFESSOR_UT_비밀번호_수정_요청 = 2;
    public static final int PROFESSOR_UT_비밀번호_수정_결과 = 3;
    public static final int PROFESSOR_UT_강의계획서_입력_요청 = 4;
    public static final int PROFESSOR_UT_강의계획서_입력_결과 = 5;
    public static final int PROFESSOR_UT_강의계획서_수정_요청 = 6;
    public static final int PROFESSOR_UT_강의계획서_수정_결과 = 7;
    public static final int PROFESSOR_UT_교과목_목록_조회_요청 = 8;
    public static final int PROFESSOR_UT_교과목_목록_조회_결과 = 9;
    public static final int PROFESSOR_UT_교과목_강의계획서_조회_요청 = 10;
    public static final int PROFESSOR_UT_교과목_강의계획서_조회_결과 = 11;
    public static final int PROFESSOR_UT_교과목_수강신청_학생_목록_조회_요청 = 12;
    public static final int PROFESSOR_UT_교과목_수강신청_학생_목록_조회_결과 = 13;
    public static final int PROFESSOR_UT_교과목_시간표_조회_요청 = 14;
    public static final int PROFESSOR_UT_교과목_시간표_조회_결과 = 15;
    public static final int PROFESSOR_UT_강의계획서_ID_요청 = 16;
    public static final int PROFESSOR_UT_강의계획서_ID_결과 = 17;
    public static final int PROFESSOR_UT_강의계획서_입력_기간_확인_요청 = 18;
    public static final int PROFESSOR_UT_강의계획서_입력_기간_확인_응답 = 19;


    public static final int STUDENT_UT_개인정보_수정_요청 = 0;
    public static final int STUDENT_UT_개인정보_수정_결과 = 1;
    public static final int STUDENT_UT_비밀번호_수정_요청 = 2;
    public static final int STUDENT_UT_비밀번호_수정_결과 = 3;
    public static final int STUDENT_UT_수강신청_요청 = 4;
    public static final int STUDENT_UT_수강신청_결과 = 5;
    public static final int STUDENT_UT_수강삭제_요청 = 6;
    public static final int STUDENT_UT_수강삭제_결과 = 7;
    public static final int STUDENT_UT_개설교과목_조회_요청 = 8;
    public static final int STUDENT_UT_개설교과목_조회_결과 = 9;
    public static final int STUDENT_UT_선택교과목_강의계획서_조회_요청 = 10;
    public static final int STUDENT_UT_선택교과목_강의계획서_조회_결과 = 11;
    public static final int STUDENT_UT_수강신청_본인현황_요청 = 12;
    public static final int STUDENT_UT_수강신청_본인현황_결과 = 13;
    public static final int STUDENT_UT_본인시간표_조회_요청 = 14;
    public static final int STUDENT_UT_본인시간표_조회_결과 = 15;

    public static final int LEN_PROTOCOL_TYPE = 1;
    public static final int LEN_PROTOCOL_USERTYPE = 1;
    public static final int LEN_PROTOCOL_BODYLEN = 2;
    public static final int LEN_PROTOCOL_FLAG = 1;
    public static final int LEN_PROTOCOL_CODE = 1;
    public static final int LEN_PROTOCOL_LAST = 1;
    public static final int LEN_PROTOCOL_SEQ = 1;

    public static final int LEN_MAX = 2048;
    public static final int LEN_BODY = 2000; //데이터 바디


    protected int protocolType;
    protected int protocolUserType;
    protected int protocolLen; // 실제 데이터 길이!!
    protected int protocolCode;
    protected int protocolFlag;
    protected int protocolLast;
    protected int protocolSeq;

    private byte[] packet;
    
    //기본생성자 사용금지
    
    public Protocol(int protocolType, int protocolUserType){
        this.protocolType = protocolType;
        this.protocolUserType = protocolUserType;
        this.protocolFlag = 0;
        this.protocolLast = 0;
        this.protocolSeq = 0;
        this.protocolCode = 0;
        this.protocolLen = 0;
        getPacket(protocolType, protocolUserType);
    }

    public byte[] getPacket(int protocolType, int protocolUserType){

        if(packet == null){

            switch(protocolType){

                case PT_UNDEFINED:
                    packet = new byte[LEN_MAX];
                    break;

                case PT_LOGIN:

                    switch (protocolUserType){
                        case UT_UNDEFINED:
                            packet = new byte[LEN_MAX];
                            break;

                        case LOGIN_UT_ID요청:

                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;

                        case LOGIN_UT_ID전송:

                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case LOGIN_UT_PASSWORD요청:

                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;

                        case LOGIN_UT_PASSWORD전송:

                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case LOGIN_UT_결과:

                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG]; // FLAG == 사용자구분용 사용
                            break;

                    }
                    break;

                case PT_MANAGER:

                    switch (protocolUserType) {
                        case MANAGER_UT_관리자계정_생성_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_관리자계정_생성_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_교수_계정_생성_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_교수_계정_생성_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_학생_계정_생성_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_학생_계정_생성_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_교과목_생성_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_교과목_생성_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_교과목_수정_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_교과목_수정_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_교과목_삭제_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_교과목_삭제_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_개설교과목_생성_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_개설교과목_생성_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_개설교과목_수정_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_개설교과목_수정_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_개설교과목_삭제_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_개설교과목_삭제_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_강의계획서_입력기간설정_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_강의계획서_입력기간설정_응답:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_학년별_수강신청기간설정_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_학년별_수강신청기간설정_응답:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_교수_정보_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_교수_정보_조회_응답:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_학생_정보_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_학생_정보_조회_응답:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_개설_교과목_정보_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_개설_교과목_정보_조회_응답:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_개설_교과목_정보_조회_목록전송:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_개설_교과목_정보_조회_목록선택:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case MANAGER_UT_교과목_목록_조회_요청 :
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case MANAGER_UT_교과목_목록_조회_응답 :
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                    }
                    break;

                case PT_STUDENT:

                    switch (protocolUserType) {
                        case STUDENT_UT_수강신청_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case STUDENT_UT_수강신청_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case STUDENT_UT_수강삭제_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case STUDENT_UT_수강삭제_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case STUDENT_UT_개설교과목_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE];
                            break;

                        case STUDENT_UT_개설교과목_조회_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case STUDENT_UT_개인정보_수정_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case STUDENT_UT_개인정보_수정_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;

                        case STUDENT_UT_선택교과목_강의계획서_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case STUDENT_UT_선택교과목_강의계획서_조회_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case STUDENT_UT_비밀번호_수정_요청 :
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case STUDENT_UT_비밀번호_수정_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;

                        case STUDENT_UT_수강신청_본인현황_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case STUDENT_UT_수강신청_본인현황_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case STUDENT_UT_본인시간표_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case STUDENT_UT_본인시간표_조회_결과:

                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;



                    }


                    break;

                case PT_PROFESSOR:

                    switch (protocolUserType) {


                        case PROFESSOR_UT_개인정보_수정_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case PROFESSOR_UT_개인정보_수정_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;

                        case PROFESSOR_UT_강의계획서_입력_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case PROFESSOR_UT_강의계획서_입력_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case PROFESSOR_UT_강의계획서_수정_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case PROFESSOR_UT_강의계획서_수정_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case PROFESSOR_UT_교과목_목록_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case PROFESSOR_UT_교과목_목록_조회_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case PROFESSOR_UT_교과목_수강신청_학생_목록_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case PROFESSOR_UT_교과목_수강신청_학생_목록_조회_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case PROFESSOR_UT_교과목_강의계획서_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case PROFESSOR_UT_교과목_강의계획서_조회_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case PROFESSOR_UT_강의계획서_ID_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case PROFESSOR_UT_강의계획서_ID_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;
                        case PROFESSOR_UT_비밀번호_수정_요청 :
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case PROFESSOR_UT_비밀번호_수정_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case PROFESSOR_UT_교과목_시간표_조회_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case PROFESSOR_UT_교과목_시간표_조회_결과:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ + LEN_BODY];
                            break;

                        case PROFESSOR_UT_강의계획서_입력_기간_확인_요청:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;
                        case PROFESSOR_UT_강의계획서_입력_기간_확인_응답:
                            packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                            break;



                    }


                    break;

                case PT_EXIT:

                    packet = new byte[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
                    break;

            } // end switch

        } // end if

        packet[0] = (byte)protocolType;
        packet[LEN_PROTOCOL_TYPE] = (byte)protocolUserType;
        return packet;

    }


    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
        packet[0] = (byte)protocolType;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolUserType(int protocolUserType) {
        this.protocolUserType = protocolUserType;
        packet[LEN_PROTOCOL_TYPE] = (byte)protocolUserType;
    }

    public int getProtocolUserType(){
        return protocolUserType;
    }

    public void setProtocolCode(int protocolCode){
        this.protocolCode = protocolCode;
        packet[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN] = (byte) protocolCode;
    }

    public int getProtocolCode(){return protocolCode;}

    public void setProtocolFlag(int protocolFlag){
        this.protocolFlag = protocolFlag;
        packet[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE] = (byte) protocolFlag;
    }

    public int getProtocolFlag(){return protocolFlag;}

    public void setProtocolLast(int protocolLast){
        this.protocolLast = protocolLast;
        packet[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG] = (byte) protocolLast;
    }

    public int getProtocolLast(){return protocolLast;}

    public void setProtocolSeq(int protocolSeq){
        this.protocolSeq = protocolSeq;
        packet[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST] = (byte) protocolSeq;
    }

    public int getProtocolSeq(){return protocolSeq;}

    public int getProtocolLen(){
        return protocolLen;
    }






    public byte[] getPacket(){
        return packet;
    }

    public void setPacket(int protocolType , int protocolUserType, byte[] buf){
        packet = null;
        packet = getPacket(protocolType, protocolUserType);
        this.protocolType = protocolType;
        this.protocolUserType = protocolUserType;


       // this.protocolLen = (int)((buf[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_USERTYPE] << 8)+buf[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_USERTYPE+1]);
        this.protocolLen = (int)buf[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_USERTYPE] * 128 + (int)buf[LEN_PROTOCOL_TYPE+LEN_PROTOCOL_USERTYPE+1];
        this.protocolCode = buf[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN];
        this.protocolFlag = buf[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE];
        this.protocolLast = buf[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG];
        this.protocolSeq = buf[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST];


        System.arraycopy(buf, 0,packet,0,packet.length);
    }

    public String getData(){
        return new String(packet, LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ , protocolLen).trim();
    }

    public byte[] getByteData(){

        byte[] tempBuf = new byte[getProtocolLen()];
        System.arraycopy(packet, LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ, tempBuf, 0, protocolLen);
        return tempBuf;

    }

    public void setData(String data){

        int i = data.getBytes().length;
        byte[] convertBytes = new byte[2];

        convertBytes[0] = (byte) (i / 128);
        convertBytes[1] = (byte) (i % 128);

        protocolLen = i;

        packet[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE] = convertBytes[0];
        packet[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + 1] = convertBytes[1];

        System.arraycopy(data.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ, data.trim().getBytes().length);

    }

    public void setByteData(byte[] data,int size){

        int i = size;

        byte[] convertBytes = new byte[2];
        convertBytes[0] = (byte) (i / 128);
        convertBytes[1] = (byte) (i % 128);
        protocolLen = i;
        packet[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE] = convertBytes[0];
        packet[LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + 1] = convertBytes[1];

        System.arraycopy(data, 0, packet, LEN_PROTOCOL_TYPE + LEN_PROTOCOL_USERTYPE + LEN_PROTOCOL_BODYLEN + LEN_PROTOCOL_CODE + LEN_PROTOCOL_FLAG + LEN_PROTOCOL_LAST + LEN_PROTOCOL_SEQ, i);

    }










}

