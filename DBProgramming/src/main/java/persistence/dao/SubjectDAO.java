package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.SubjectDTO;

import java.util.List;

public class SubjectDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SubjectDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }



    public List<SubjectDTO> selectAll(){
        List<SubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.SubjectMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }

    public List<SubjectDTO> selectWithGrade(int 학년){
        List<SubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.SubjectMapper.selectWithGrade", 학년);
        } finally {
            session.close();
        }
        return list;
    }



    public void insert(SubjectDTO subjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.insert("mapper.SubjectMapper.insert", subjectDTO);
            session.commit();
        }
        finally{
            session.close();
        }
    }



    public void updateWithBoardId(SubjectDTO subjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.update("mapper.SubjectMapper.updateName", subjectDTO);
            session.commit();
        }
        finally{
            session.close();
        }
    }



    public List<SubjectDTO> selectWithId(String id){
        List<SubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.SubjectMapper.selectWithId", id);
        } finally {
            session.close();
        }
        return list;
    }

    public boolean subjectIsExist(String 교과목ID){
        List<SubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("mapper.SubjectMapper.selectWithId", 교과목ID);
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




    public void updateSubjectClarifyWithBoardId(SubjectDTO subjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.update("mapper.SubjectMapper.updateSubjectClassify", subjectDTO);
            session.commit();
        }
        finally{
            session.close();
        }
    }

    public void updateNameWithBoardId(SubjectDTO subjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.update("mapper.SubjectMapper.updateName", subjectDTO);
            session.commit();
        }
        finally{
            session.close();
        }
    }

    public void updateGradesWithBoardId(SubjectDTO subjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.update("mapper.SubjectMapper.updateGrades", subjectDTO);
            session.commit();
        }
        finally{
            session.close();
        }
    }

    public void updateGradeWithBoardId(SubjectDTO subjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.update("mapper.SubjectMapper.updateGrade", subjectDTO);
            session.commit();
        }
        finally{
            session.close();
        }
    }

    public void updateSemesterWithBoardId(SubjectDTO subjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.update("mapper.SubjectMapper.updateSemester", subjectDTO);
            session.commit();
        }
        finally{
            session.close();
        }
    }

    public void delete(SubjectDTO subjectDTO){     // 추가한것 11/24
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.delete("mapper.SubjectMapper.delete", subjectDTO);
            session.commit();
        }
        finally{
            session.close();
        }
    }


}