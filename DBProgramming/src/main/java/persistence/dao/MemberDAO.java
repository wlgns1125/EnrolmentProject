package persistence.dao;

import persistence.PooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class MemberDAO {

    final DataSource ds = PooledDataSource.getDataSource();

    private Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Statement stmt = null;

    public MemberDAO() {
        try {
            conn = ds.getConnection();
        }catch(Exception e) {
            try {
                conn.close();
            }catch(SQLException e1) {}
        }
    }

    public void insertManager(String id, String name, String pn, String pwd) {
        String sql = "insert into 관리자 values(?,?,?)";
        String sql2 = "insert into 사용자 values(?,?,?)";
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,pn);

            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1,id);
            pstmt2.setString(2,pwd);
            pstmt2.setString(3,"관리자");

            pstmt.executeUpdate();
            pstmt2.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProfessor(String id,String name, String lab,
                                String email, String pn,int birth) {
        String sql = "insert into 교수 values(?,?,?,?,?,?)";
        String sql2 = "insert into 사용자 values(?,?,?)";
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,lab);
            pstmt.setString(4,email);
            pstmt.setString(5,pn);
            pstmt.setInt(6,birth);

            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1,id);
            pstmt2.setString(2,Integer.toString(birth));
            pstmt2.setString(3,"교수");

            pstmt.executeUpdate();
            pstmt2.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertStudent(int num,String name,String pn, String department,
                              int grade, int birth, String email, String code) {

        String sql = "insert into 학생 values(?,?,?,?,?,?,?,?)";
        String sql2 = "insert into 사용자 values(?,?,?)";
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,num);
            pstmt.setString(2,name);
            pstmt.setString(3,pn);
            pstmt.setString(4,department);
            pstmt.setInt(5,grade);
            pstmt.setInt(6,birth);
            pstmt.setString(7,email);
            pstmt.setString(8,code);

            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1,Integer.toString(num));
            pstmt2.setString(2,Integer.toString(birth));
            pstmt2.setString(3,"학생");

            pstmt.executeUpdate();
            pstmt2.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }


    public void updateProfesserName(String num,String name) {
        String sql = "update 교수 set 이름=? where 교수ID = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,name);
            pstmt.setString(2,num);

            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProfessorPn(String id,String pn) {
        String sql = "update 교수 set 전화번호 =? where 교수ID = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,pn);
            pstmt.setString(2,id);

            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();

            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProfesser연구실(String num,String name) {
        String sql = "update 교수 set 연구실=? where 교수ID = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,name);
            pstmt.setString(2,num);

            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProfesser이메일(String num,String name) {
        String sql = "update 교수 set 이메일=? where 교수ID = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,name);
            pstmt.setString(2,num);

            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public void updateStudentName(String num,String name) {
        String sql = "update 학생 set 이름=? where 학번 = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,name);
            pstmt.setString(2,num);

            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStudent전화번호(String num,String name) {
        String sql = "update 학생 set 전화번호=? where 학번 = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,name);
            pstmt.setString(2,num);

            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStudent이메일(String num,String name) {
        String sql = "update 학생 set 이메일=? where 학번 = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,name);
            pstmt.setString(2,num);

            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }





    public void selectProfessor() {
        System.out.println("아이디\t이름\t\t연구실\t이메일\t\t\t전화번호\t\t\t생년월일");

        String sql = "select * from 교수";
        ResultSet rs = null;
        Statement stmt = null;


        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getString("교수ID") + "\t" +
                        rs.getString("이름") + "\t" + rs.getString("연구실") + "\t" + rs.getString("이메일")
                        + "\t" + rs.getString("전화번호") + "\t" + rs.getInt("생년월일"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally { {
            try {
                rs.close();
                stmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        }
    }

    public void selectStudent() {
        System.out.println("\t학번\t\t이름\t\t전화번호\t\t\t학과\t\t학년\t생년월일\t\t이메일\t\t교수코드 ");

        String sql = "select * from 학생";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getInt("학번") + "\t" + rs.getString("이름") + "\t" +
                        rs.getString("전화번호") + "\t" + rs.getString("학과") + "\t" + rs.getInt("학년")
                        + "\t" + rs.getInt("생년월일") + "\t" + rs.getString("이메일") + "\t" + rs.getString("교수코드"));

            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally { {
            try {
                rs.close();
                stmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        }

    }

    public int count수강생정보(String 개설과목ID){

        int count = 0;
        String sql = "SELECT count(*) FROM 수강신청 JOIN 학생 ON 학생.학번 = 수강신청.학번 WHERE 개설과목ID = \"" + 개설과목ID + "\"";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                count = rs.getInt(1);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally { {
            try {
                rs.close();
                stmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        }

        return count;
    }

    public void print수강생정보(String 개설과목ID, int offset){

        System.out.println("\t학번\t\t이름\t\t전화번호\t\t\t학년");
        String sql = "SELECT * FROM (SELECT 학생.이름, 학생.학번, 학생.전화번호, 학생.학년 FROM 수강신청 JOIN 학생 ON 학생.학번 = 수강신청.학번 WHERE 개설과목ID = " + "\"" + 개설과목ID + "\"" + ") ORDERS LIMIT 2 offset " + offset;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getInt("학번") + "\t" + rs.getString("이름") + "\t" + rs.getString("전화번호")
                        + "\t" + rs.getInt("학년"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally { {
            try {
                rs.close();
                stmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        }
    }

    public void 교수비밀번호수정(String 교수id, String 비밀번호) {
        String sql = "update 사용자 set 패스워드=? where ID = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,비밀번호);
            pstmt.setString(2,교수id);

            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();

            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void 학생비밀번호수정(String 학번, String 비밀번호) {
        String sql = "update 사용자 set 패스워드=? where ID = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,비밀번호);
            pstmt.setString(2,학번);

            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();

            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }




}