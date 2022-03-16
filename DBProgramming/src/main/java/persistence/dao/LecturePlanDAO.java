package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.LecturePlanDTO;
import persistence.dto.OpenSubjectDTO;
import persistence.mapper.LecturePlanMapper;

import java.util.List;

public class LecturePlanDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public LecturePlanDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<LecturePlanDTO> selectByOpenSubject(String 개설과목id) {
        List<LecturePlanDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        LecturePlanMapper mapper = session.getMapper(LecturePlanMapper.class);
        try {
            list = mapper.selectById(개설과목id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;
    }

    public List<LecturePlanDTO> selectID(){
        List<LecturePlanDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        LecturePlanMapper mapper = session.getMapper(LecturePlanMapper.class);
        try {
            list = mapper.selectAll();
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;

    }



    public void insertLecturePlan(LecturePlanDTO inputLecturePlanDTO){
        SqlSession session = sqlSessionFactory.openSession();
        LecturePlanMapper mapper = session.getMapper(LecturePlanMapper.class);
        try{
            mapper.insert(inputLecturePlanDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public void updateLecturePlan(LecturePlanDTO lecturePlanDTO){
        SqlSession session = sqlSessionFactory.openSession();
        LecturePlanMapper mapper = session.getMapper(LecturePlanMapper.class);
        try{
            mapper.update(lecturePlanDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }

    public boolean isLecturePlanExist(String 개설과목id) {
        List<LecturePlanDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        LecturePlanMapper mapper = session.getMapper(LecturePlanMapper.class);
        try {
            list = mapper.selectById(개설과목id);
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







}
