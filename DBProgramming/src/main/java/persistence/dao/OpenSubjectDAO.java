package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.OpenSubjectDTO;
import persistence.mapper.OpenSubjectMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OpenSubjectDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public OpenSubjectDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<OpenSubjectDTO> selectAllOpenSubject(){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        List<OpenSubjectDTO> all = mapper.getAll();
        return all;
    }

    public List<OpenSubjectDTO> selectByResProfessor(String id){
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.getResProfessor(id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;
    }

    public List<OpenSubjectDTO> selectByGrade(String id){
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.getByGrade(id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;
    }

    public void insertOpenSubject(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.insert(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateOpenSubject(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.update(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void 신청인원추가(OpenSubjectDTO openSubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.plus(openSubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }


    public void 신청인원감소(OpenSubjectDTO openSubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.minus(openSubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }






    public void updateApplicationValidTrue(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateApplicationValidTrue(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateApplicationValidFalse(OpenSubjectDTO inputOpensubjectDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateApplicationValidFalse(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }


    public void setFalse(String 개설과목ID){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.setFalse(개설과목ID);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void setTrue(String 개설과목ID){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.setTrue(개설과목ID);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }



    public List<OpenSubjectDTO> selectByGradeAndProfessor(String grade, String name) {
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.getByGradeAndProfessor(grade, name);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;
    }

    public boolean isExist(String id){

        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.selectById(id);
            session.commit();
            return !list.isEmpty();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();

        }

        return false;
    }

    public void delete(String id){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            mapper.delete(id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }

    public boolean isOpenSubjectExist(String id){
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.getById(id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return !(list.isEmpty());
    }


    public void updateProffessor(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateProffessor(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateGrade(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateGrade(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateMax(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateMax(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateApplyNum(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateApplyNum(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateDistribution(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateDistribution(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateValid(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateValid(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateTime(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateTime(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateRoom(OpenSubjectDTO inputOpensubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try{
            mapper.updateRoom(inputOpensubjectDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public boolean isRightProfessor(String 개설과목ID,String id){

        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.selectBy교수Id(개설과목ID,id);
            session.commit();
            return !list.isEmpty();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();

        }

        return false;
    }

    public List<OpenSubjectDTO> selectByProfessorID(String id){
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.getProfessorID(id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;
    }

    public List<OpenSubjectDTO> selectProfessorSchedule(String 교수id) {
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.selectProfessorShedule(교수id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;
    }


    public List<OpenSubjectDTO> selectStudentSchedule(String 학번) {
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.selectStudentShedule(학번);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;
    }


    public List<OpenSubjectDTO> selectAllOpenSubjectOnlyName(){
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        List<OpenSubjectDTO> all = mapper.getByIdOnlyName();
        return all;
    }

    // 11/28
    public List<OpenSubjectDTO> selectByID(String id){
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpenSubjectMapper mapper = session.getMapper(OpenSubjectMapper.class);
        try {
            list = mapper.getById(id);
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



