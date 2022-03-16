package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.OpenSubjectDTO;
import persistence.dto.SyllabusPeriodDTO;
import persistence.mapper.OpenSubjectMapper;
import persistence.mapper.SyllabusPeriodMapper;

import java.util.List;

public class SyllabusPeriodDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SyllabusPeriodDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<SyllabusPeriodDTO> selectAllSyllabusPeriod() {
        SqlSession session = sqlSessionFactory.openSession();
        SyllabusPeriodMapper mapper = session.getMapper(SyllabusPeriodMapper.class);
        List<SyllabusPeriodDTO> all = mapper.getAll();
        return all;
    }

    public void updateSyllabusPeriod(SyllabusPeriodDTO syllabusPeriodDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        SyllabusPeriodMapper mapper = session.getMapper(SyllabusPeriodMapper.class);
        try {
            mapper.update(syllabusPeriodDTO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void insertSyllabusPeriod(SyllabusPeriodDTO syllabusPeriodDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        SyllabusPeriodMapper mapper = session.getMapper(SyllabusPeriodMapper.class);
        try {
            mapper.insert(syllabusPeriodDTO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }

    public boolean isDateExist(String year){
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        SyllabusPeriodMapper mapper = session.getMapper(SyllabusPeriodMapper.class);
        try {
            list = mapper.getByYear(year);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return !(list.isEmpty());
    }

    public boolean isDateValid(){
        List<OpenSubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        SyllabusPeriodMapper mapper = session.getMapper(SyllabusPeriodMapper.class);
        try {
            list = mapper.getValid();
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return (!list.isEmpty());
    }

}