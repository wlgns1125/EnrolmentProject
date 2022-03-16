package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.UserDTO;
import persistence.mapper.OpenSubjectMapper;
import persistence.mapper.UserMapper;

import java.util.List;

public class UserDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public UserDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public boolean isIdExist(String id){

        List<UserDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
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

    public boolean isPasswordCorrect(String id,String password){

        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<UserDTO> list = null;

        try {
            list = mapper.selectById(id);

            if(list.isEmpty()){
                return false;
            }else{

                if(password.equals(list.get(0).get패스워드())){
                    return true;
                }else{
                    return false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }

        return false;
    }

    public String whoAreYou(String id){

        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<UserDTO> list = null;

        try {
            list = mapper.selectById(id);

            if(list.get(0).get사용자구분().equals("관리자")){
                return "관리자";
            }
            else if(list.get(0).get사용자구분().equals("학생")){
                return "학생";
            }
            else if(list.get(0).get사용자구분().equals("교수")){
                return "교수";
            }


        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return "";

    }




}
