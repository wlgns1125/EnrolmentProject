package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.ProfessorDTO;
import persistence.dto.StudentDTO;
import persistence.mapper.MemberMapper;

import java.util.List;

public class Member2DAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public Member2DAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<StudentDTO> selectStudent2(){
        SqlSession session = sqlSessionFactory.openSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);
        List<StudentDTO> all = mapper.getAll();
        return all;
    }

    public List<ProfessorDTO> selectProffessor(){
        SqlSession session = sqlSessionFactory.openSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);
        List<ProfessorDTO> all = mapper.getProffessor();
        return all;
    }

    public boolean isIdExist(String id){

        List<ProfessorDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);
        try {
            list = mapper.getProffessorWithId(id);

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
