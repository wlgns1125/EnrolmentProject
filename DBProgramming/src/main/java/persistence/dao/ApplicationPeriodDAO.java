package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.ApplicationPeriodDTO;
import persistence.mapper.ApplicationPeriodMapper;

import javax.sql.DataSource;
import java.util.List;

public class ApplicationPeriodDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public ApplicationPeriodDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<ApplicationPeriodDTO> selectAllApplicationPeriod() {
        SqlSession session = sqlSessionFactory.openSession();
        ApplicationPeriodMapper mapper = session.getMapper(ApplicationPeriodMapper.class);
        List<ApplicationPeriodDTO> all = mapper.getAll();
        return all;
    }

    public void updateApplicationPeriod(ApplicationPeriodDTO applicationPeriodDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        ApplicationPeriodMapper mapper = session.getMapper(ApplicationPeriodMapper.class);
        try {
            mapper.update(applicationPeriodDTO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void insertApplicationPeriod(ApplicationPeriodDTO applicationPeriodDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        ApplicationPeriodMapper mapper = session.getMapper(ApplicationPeriodMapper.class);
        try {
            mapper.insert(applicationPeriodDTO);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }
}