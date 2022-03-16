package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.EnrollmentDTO;
import persistence.dto.OpenSubjectDTO;
import persistence.mapper.EnrollmentMapper;
import persistence.mapper.OpenSubjectMapper;


import java.util.Date;
import java.util.List;

public class EnrollmentDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public EnrollmentDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    public void insert(EnrollmentDTO enrollmentDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("mapper.EnrollmentMapper.insert", enrollmentDTO);
            session.commit();
        } finally {
            session.close();
        }
    }

    public List<EnrollmentDTO> select(EnrollmentDTO enrollmentDTO) {
        List<EnrollmentDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("mapper.EnrollmentMapper.select", enrollmentDTO);
        } finally {
            session.close();
        }
        return list;
    }


    public List<EnrollmentDTO> selectSubjectName(EnrollmentDTO enrollmentDTO) {
        List<EnrollmentDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("mapper.EnrollmentMapper.selectSubjectName", enrollmentDTO);
        } finally {
            session.close();
        }
        return list;
    }

    public void delete(EnrollmentDTO enrollmentDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.delete("mapper.EnrollmentMapper.delete", enrollmentDTO);
            session.commit();
        } finally {
            session.close();
        }
    }


    public boolean isFull(String 개설과목ID) {
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        int max = 0;
        int current = 0;
        try {
            OpenSubjectDTO openSubjectDTO = mapper.select(개설과목ID);
            max = openSubjectDTO.get최대수강인원();
            current = openSubjectDTO.get신청인원();
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }

        if (max <= current) {
            return true;
        }
        return false;

    }

    public boolean isDupple(String 개설과목ID , int 학번) {
        SqlSession session = sqlSessionFactory.openSession();
        EnrollmentMapper mapper = session.getMapper(EnrollmentMapper.class);

        String 교과목ID = 개설과목ID.substring(0, 6);
        개설과목ID = 교과목ID;
        개설과목ID += "%";

        boolean bool = false;
        List<EnrollmentDTO> list = null;

        try {
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
            list = mapper.dupple(개설과목ID,학번);

            if(list.isEmpty()){
                bool = false;
            }
            else{
                bool = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }

        return bool;

    }


    public boolean isValid(String 개설과목ID) {
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        boolean valid = false;

        try {
            OpenSubjectDTO openSubjectDTO = mapper.select(개설과목ID);
            valid = openSubjectDTO.get수강신청가능여부();
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }

        return valid;

    }

    public boolean 수강신청가능(String 개설과목ID1, String 개설과목ID2) {

        String s1;
        String s2;

        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            OpenSubjectDTO dto1 = mapper.select(개설과목ID1);
            OpenSubjectDTO dto2 = mapper.select(개설과목ID2);

            s1 = dto1.get강의시간();
            s2 = dto2.get강의시간();



            String[] c = s1.replace(" ", "").split(",");
            String[] c2 = s2.replace(" ", "").split(",");

            for (int i = 0; i < c.length; i++) {
                for (int j = 0; j < c2.length; j++) {
                    if (c[i].charAt(0) == c2[j].charAt(0)) {

                        for (int k = 1; k < c[i].length(); k++) {

                            for (int u = 1; u < c2[j].length(); u++) {
                                if (c[i].charAt(k) == c2[j].charAt(u)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }

            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }

        return true;
    }

    public List<OpenSubjectDTO> AllTime(int 학번){
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        EnrollmentMapper mapper = session.getMapper(EnrollmentMapper.class);

        try {
            list = mapper.allTime(학번);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }

        return list;
    }

    public boolean isValidDate(String 개설과목ID){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        Date date1;
        Date date2;
        Date now = new Date();

        try {
            OpenSubjectDTO openSubjectDTO = mapper.selectDate(개설과목ID);
            date1 = openSubjectDTO.get시작일();
            date2 = openSubjectDTO.get끝일();

            if(now.before(date2)&&now.after(date1))
                return true;
            else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }

        return false;
    }

    public List<EnrollmentDTO> selectBy수강신청학생(String 개설과목id){
        List<EnrollmentDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        EnrollmentMapper mapper = session.getMapper(EnrollmentMapper.class);
        try {
            list = mapper.getEnrollmentStudent(개설과목id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;
    }



}