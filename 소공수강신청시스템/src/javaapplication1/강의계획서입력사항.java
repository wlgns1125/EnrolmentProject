package javaapplication1;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Calendar;
import javax.swing.table.TableModel;

public class 강의계획서입력사항 extends javax.swing.JFrame {
    private String 과목코드;
    private String 교수코드;
    private int 분반;
    /** Creates new form 강의계획서입력사항 */
    public 강의계획서입력사항() {
        initComponents();
    }

    public 강의계획서입력사항(String 과목코드, String 교수코드, int 분반){
        initComponents();
        this.과목코드 = 과목코드;
        this.교수코드 = 교수코드;
        this.분반 = 분반;

        교과목코드.setText(과목코드);
        교수정보읽어오기();
        과목정보읽어오기();
        강의계획서읽어오기();
        강의계획읽어오기();
        선수과목읽어오기();
    }
    private String 현재날짜반환(){
        Calendar now = Calendar.getInstance();
        String date = now.get(Calendar.YEAR) + "-";

        if (now.get(Calendar.MONTH) + 1 < 10) date = date + "0";
        date = date + (now.get(Calendar.MONTH) + 1) + "-";

        if (now.get(Calendar.DATE) < 10) date = date + "0";
        date = date + (now.get(Calendar.DATE)) + " ";

        return date;
    }
    private void 과목정보읽어오기(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        DBConn dbconn = new DBConn();
        conn = dbconn.getConnection();

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from 과목, 교과과정내역 where 과목.과목코드='"+과목코드+"' and 과목.과목코드=교과과정내역.과목코드");
            if(rs.next()) {
                과목명.setText(rs.getString("교과목명"));
                학점.setText(rs.getString("학점"));
                이수학년.setText(rs.getString("학년"));
            }
        }catch(Exception e){ e.printStackTrace(); }
        finally{
            try{
                if(rs!=null) rs.close();
                if(stmt!=null) stmt.close();
            }catch(SQLException e){  }
        }
    }
    private void 교수정보읽어오기(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        DBConn dbconn = new DBConn();
        conn = dbconn.getConnection();

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from 교수, 학부 where 교수.교수코드='"+교수코드+"' and 교수.소속코드=학부.소속코드");
            if(rs.next()) {
                교수명.setText(rs.getString("이름"));
                연구실.setText(rs.getString("연구실"));
                교수email.setText(rs.getString("이메일"));
                교수전화번호.setText(rs.getString("일반전화"));
                행정실전화.setText(rs.getString("전화번호"));
                개설전공.setText(rs.getString("전공명"));
            }
        }catch(Exception e){ e.printStackTrace(); }
        finally{
            try{
                if(rs!=null) rs.close();
                if(stmt!=null) stmt.close();
            }catch(SQLException e){  }
        }
    }
    private void 강의계획서읽어오기(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        DBConn dbconn = new DBConn();
        conn = dbconn.getConnection();

        try{
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from 강의계획서 where 강의계획서.분반='"+분반+"' and 강의계획서.과목코드='"+과목코드+"'");
            // 존재하면 띄움
            if(rs.next()){
                작성일시.setText(rs.getDate("작성일자").toString());
                교재명.setText(rs.getString("교재명"));
                참고문헌.setText(rs.getString("참고문헌"));
                과목개요.setText(rs.getString("과목개요"));
                교수목표.setText(rs.getString("교수목표"));
                수업방법.setText(rs.getString("수업방법"));
                과제물작성요령.setText(rs.getString("과제물작성요령"));
                과제물평가방법.setText(rs.getString("과제물평가방법"));
                성적평가방법.setText(rs.getString("성적평가방법"));
                권장선수과목.setText(rs.getString("권장선수과목"));
                수강생유의사항.setText(rs.getString("수강생유의사항"));
                문제분석설계방법.setText(rs.getString("문제분석_설계방법"));
            }
        }catch(Exception e){ e.printStackTrace(); }
        finally{
            try{
                if(rs!=null) rs.close();
                if(stmt!=null) stmt.close();
                if(conn!=null) conn.close();
            }catch(Exception e){}
        }
    }
    private void 강의계획읽어오기(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        int i=0;
        DBConn dbconn = new DBConn();
        conn = dbconn.getConnection();

        TableModel tm = 강의계획.getModel();

        try{
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from 강의계획 where 강의계획.분반='"+분반+"' and 강의계획.과목코드='"+과목코드+"'");
            // 존재하면 띄움
            while(rs.next()){
                if(rs.getString("주제") == null) tm.setValueAt("", i, 1);
                else tm.setValueAt(rs.getString("주제"), i, 1);
                
                if(rs.getString("내용")==null) tm.setValueAt("", i, 2);
                else tm.setValueAt(rs.getString("내용"), i, 2);

                if(rs.getString("과제") == null) tm.setValueAt("", i, 3);
                else tm.setValueAt(rs.getString("과제"), i, 3);

                if(rs.getString("평가") == null) tm.setValueAt("", i, 4);
                else tm.setValueAt(rs.getString("평가"), i, 4);

                i++;
            }
        }catch(Exception e){ e.printStackTrace(); }
        finally{
            try{
                if(rs!=null) rs.close();
                if(stmt!=null) stmt.close();
                if(conn!=null) conn.close();
            }catch(Exception e){}
        }
    }
    private void 선수과목읽어오기(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        DBConn dbconn = new DBConn();
        conn = dbconn.getConnection();

        try{
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from 선수과목, 과목 where 선수과목.이수가능과목코드='"+과목코드+"' and 선수과목.선수과목코드=과목.과목코드");
            // 존재하면 띄움
            if(rs.next()){
                필수선수과목명.setText(rs.getString("과목명"));
            }
        }catch(Exception e){ e.printStackTrace(); }
        finally{
            try{
                if(rs!=null) rs.close();
                if(stmt!=null) stmt.close();
                if(conn!=null) conn.close();
            }catch(Exception e){}
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        교재명 = new javax.swing.JTextField();
        학점 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        강의시간 = new javax.swing.JTextField();
        행정실전화 = new javax.swing.JTextField();
        작성일시 = new javax.swing.JTextField();
        교수명 = new javax.swing.JTextField();
        연구실 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        이수학년 = new javax.swing.JTextField();
        교수전화번호 = new javax.swing.JTextField();
        교수email = new javax.swing.JTextField();
        권장선수과목 = new javax.swing.JTextField();
        교과목코드 = new javax.swing.JTextField();
        필수선수과목명 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        개설전공 = new javax.swing.JTextField();
        과목명 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        강의계획 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        저장 = new javax.swing.JButton();
        취소 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        참고문헌 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        과목개요 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        수업방법 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        과제물작성요령 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        과제물평가방법 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        교수목표 = new javax.swing.JTextArea();
        성적평가방법 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        수강생유의사항 = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        문제분석설계방법 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("강의계획서");
        setResizable(false);

        jPanel3.setPreferredSize(new java.awt.Dimension(700, 1500));

        jLabel10.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel10.setText("연구실전화/휴대폰");

        jLabel11.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel11.setText("교수 E-MAIL");

        jLabel8.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel8.setText("교과목코드");

        교재명.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        학점.setEditable(false);
        학점.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel7.setText("참고문헌");

        강의시간.setEditable(false);
        강의시간.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        행정실전화.setEditable(false);
        행정실전화.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        작성일시.setEditable(false);
        작성일시.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        교수명.setEditable(false);
        교수명.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        연구실.setEditable(false);
        연구실.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel1.setText("교과목명");

        jLabel9.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel9.setText("이수학년");

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel2.setText("개설학부(전공)");

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel4.setText("교수연구실위치");

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel3.setText("담당교수");

        이수학년.setEditable(false);
        이수학년.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        교수전화번호.setEditable(false);
        교수전화번호.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        교수email.setEditable(false);
        교수email.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        권장선수과목.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        교과목코드.setEditable(false);
        교과목코드.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        필수선수과목명.setEditable(false);
        필수선수과목명.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel12.setText("권장선수과목");

        jLabel13.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel13.setText("학점");

        jLabel14.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel14.setText("강의시간");

        jLabel6.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel6.setText("교재명");

        jLabel15.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel15.setText("행정실전화");

        jLabel5.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel5.setText("필수선수과목명");

        jLabel16.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel16.setText("작성일시");

        개설전공.setEditable(false);
        개설전공.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        과목명.setEditable(false);
        과목명.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        강의계획.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1주차", null, null, null, null},
                {"2주차", null, null, null, null},
                {"3주차", null, null, null, null},
                {"4주차", null, null, null, null},
                {"5주차", null, null, null, null},
                {"6주차", null, null, null, null},
                {"7주차", null, null, null, null},
                {"8주차", null, null, null, null},
                {"9주차", null, null, null, null},
                {"10주차", "", null, null, null},
                {"11주차", null, null, null, null},
                {"12주차", null, null, null, null},
                {"13주차", null, null, null, null},
                {"14주차", null, null, null, null},
                {"15주차", null, null, null, null}
            },
            new String [] {
                "주차", "강의주제", "강의내용", "과제", "평가"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        강의계획.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(강의계획);
        강의계획.getColumnModel().getColumn(0).setResizable(false);
        강의계획.getColumnModel().getColumn(1).setResizable(false);
        강의계획.getColumnModel().getColumn(1).setPreferredWidth(200);
        강의계획.getColumnModel().getColumn(2).setResizable(false);
        강의계획.getColumnModel().getColumn(2).setPreferredWidth(400);
        강의계획.getColumnModel().getColumn(3).setResizable(false);
        강의계획.getColumnModel().getColumn(3).setPreferredWidth(120);
        강의계획.getColumnModel().getColumn(4).setResizable(false);
        강의계획.getColumnModel().getColumn(4).setPreferredWidth(100);

        jLabel18.setFont(new java.awt.Font("맑은 고딕", 0, 13)); // NOI18N
        jLabel18.setText("2. 교수목표");

        jLabel25.setFont(new java.awt.Font("맑은 고딕", 0, 13)); // NOI18N
        jLabel25.setText("3. 강의계획");

        jLabel17.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel17.setText("1. 과목개요");

        저장.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        저장.setText("저장");
        저장.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                저장ActionPerformed(evt);
            }
        });

        취소.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        취소.setText("취소");
        취소.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                취소ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel27.setText("5. 과제물 작성요령");

        jLabel28.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel28.setText("6. 과제물 평가방법");

        jLabel29.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel29.setText("7. 성적평가방법");

        jLabel26.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel26.setText("4. 수업방법");

        참고문헌.setColumns(20);
        참고문헌.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        참고문헌.setRows(2);
        jScrollPane2.setViewportView(참고문헌);

        과목개요.setColumns(20);
        과목개요.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        과목개요.setRows(2);
        jScrollPane3.setViewportView(과목개요);

        수업방법.setColumns(20);
        수업방법.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        수업방법.setRows(4);
        jScrollPane4.setViewportView(수업방법);

        과제물작성요령.setColumns(20);
        과제물작성요령.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        과제물작성요령.setRows(2);
        jScrollPane5.setViewportView(과제물작성요령);

        과제물평가방법.setColumns(20);
        과제물평가방법.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        과제물평가방법.setRows(2);
        jScrollPane6.setViewportView(과제물평가방법);

        교수목표.setColumns(20);
        교수목표.setFont(new java.awt.Font("맑은 고딕", 0, 13)); // NOI18N
        교수목표.setRows(5);
        jScrollPane7.setViewportView(교수목표);

        성적평가방법.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel19.setText("8. 수강생 유의사항");

        수강생유의사항.setColumns(20);
        수강생유의사항.setFont(new java.awt.Font("맑은 고딕", 0, 13)); // NOI18N
        수강생유의사항.setRows(5);
        jScrollPane8.setViewportView(수강생유의사항);

        jLabel20.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel20.setText("9. 문제분석 및 설계방법");

        문제분석설계방법.setColumns(20);
        문제분석설계방법.setFont(new java.awt.Font("맑은 고딕", 0, 13)); // NOI18N
        문제분석설계방법.setRows(5);
        jScrollPane10.setViewportView(문제분석설계방법);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(성적평가방법, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(필수선수과목명)
                                    .addComponent(연구실)
                                    .addComponent(교수명)
                                    .addComponent(개설전공)
                                    .addComponent(과목명, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel12))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(이수학년, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(교과목코드, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(교수전화번호, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                            .addComponent(교수email, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                            .addComponent(권장선수과목, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel16))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(교재명)
                                    .addComponent(작성일시)
                                    .addComponent(행정실전화)
                                    .addComponent(학점, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(강의시간, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 717, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(358, Short.MAX_VALUE)
                .addComponent(저장)
                .addGap(59, 59, 59)
                .addComponent(취소)
                .addGap(300, 300, 300))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(교수명, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(과목명, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(개설전공, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(연구실, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(필수선수과목명, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(행정실전화, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(학점, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(강의시간, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addComponent(작성일시, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(교재명, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(교수전화번호, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(교과목코드, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(이수학년, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(교수email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(권장선수과목, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel25))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel28)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(성적평가방법, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(저장)
                    .addComponent(취소))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void 취소ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_취소ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_취소ActionPerformed

    private void 저장ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_저장ActionPerformed
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        int cnt = 0;
        DBConn dbconn = new DBConn();
        conn = dbconn.getConnection();

        try{
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from 강의계획서 where 강의계획서.분반='"+분반+"' and 강의계획서.과목코드='"+과목코드+"'");
            // 존재하면 update
            if(rs.next()){
                stmt.close();
                stmt = conn.createStatement();

                cnt = stmt.executeUpdate("update 강의계획서 set 교재명='"+교재명.getText() +"', 참고문헌='"+참고문헌.getText()+"', 과목개요='"+과목개요.getText()+"', 교수목표='"+교수목표.getText()+"', "
                        + "수업방법='"+수업방법.getText()+"', 과제물작성요령='"+과제물작성요령.getText()+"', 과제물평가방법='"+과제물평가방법.getText()+"', 성적평가방법='"+성적평가방법.getText()+"', "
                        + "작성일자='" + 현재날짜반환() + "', 권장선수과목='" + 권장선수과목.getText() + "', 수강생유의사항='" + 수강생유의사항.getText() + "', 문제분석_설계방법='" + 문제분석설계방법.getText() + "'"
                        + " where 과목코드='" +과목코드+"' and 분반='" + 분반+"'");
                강의계획저장(false);
                if(cnt>=1) JOptionPane.showMessageDialog(null, "강의계획서 수정 완료.");
            }
            //아니면 insert;
            else{
                stmt.close();
                stmt = conn.createStatement();
                cnt = stmt.executeUpdate("insert into 강의계획서(교과과정코드, 분반, 과목코드, 교재명, 참고문헌, 과목개요, 교수목표, 수업방법, 과제물작성요령, 과제물평가방법, 성적평가방법, 작성일자, 권장선수과목, 수강생유의사항, 문제분석_설계방법) values('"
                        + 과목코드.substring(0, 3) + "', '" + 분반 + "', '" + 과목코드 + "', '" + 교재명.getText() + "', '" + 참고문헌.getText() + "', '" + 과목개요.getText() + "', '" + 교수목표.getText() + "', '" + 수업방법.getText() + "', '" + 과제물작성요령.getText() + "', '" + 과제물평가방법.getText()
                        + "', '" + 성적평가방법.getText() + "', '" + 현재날짜반환() + "', '" + 권장선수과목.getText() + "', '" + 수강생유의사항.getText() + "', '" + 문제분석설계방법.getText() + "')");
                강의계획저장(true);
                if(cnt>=1) JOptionPane.showMessageDialog(null, "강의계획서 입력 완료.");
            }
        }catch(Exception e){ e.printStackTrace(); }
        finally{
            try{ 
                if(rs!=null) rs.close();
                if(stmt!=null) stmt.close();
                if(conn!=null) conn.close();
            }catch(Exception e){}
        }
        this.dispose();
    }//GEN-LAST:event_저장ActionPerformed

    private void 강의계획저장(boolean flag){
        Connection conn = null;
        ResultSet rs =null;
        int cnt = 0;
        PreparedStatement pstmt=null;
        DBConn dbconn = new DBConn();
        conn = dbconn.getConnection();

        try{
            // 강의계획추가
            if(flag){
                for(int i=1; i<16; i++){
                    pstmt = conn.prepareStatement("insert into 강의계획(교과과정코드, 분반, 과목코드, 주차, 주제, 내용, 과제, 평가) values(?, ?, ?, ?, ?, ?, ?, ?)");
                    pstmt.setString(1, 과목코드.substring(0, 3));
                    pstmt.setString(2, String.valueOf(분반));
                    pstmt.setString(3, 과목코드);
                    pstmt.setString(4, String.valueOf(i));
                    if(강의계획.getValueAt(i-1, 1) == null) pstmt.setString(5, "");
                    else pstmt.setString(5, (String) 강의계획.getValueAt(i - 1, 1));
                    if(강의계획.getValueAt(i-1, 2) == null) pstmt.setString(6, "");
                    else pstmt.setString(6, (String) 강의계획.getValueAt(i - 1, 2));
                    if(강의계획.getValueAt(i-1, 3) == null) pstmt.setString(7, "");
                    else pstmt.setString(7, (String) 강의계획.getValueAt(i - 1, 3));
                    if(강의계획.getValueAt(i-1, 4) == null) pstmt.setString(8, "");
                    else pstmt.setString(8, (String) 강의계획.getValueAt(i - 1, 4));

                    cnt =  pstmt.executeUpdate();
                    if(cnt<0) System.out.println("미쳤음 왜안됨!!");
                }
            }
            //아니면 갱신
            else{
                for(int i=1; i<16; i++){
                    pstmt = conn.prepareStatement("update 강의계획 set 주제=?, 내용=?, 과제=?, 평가=? where 과목코드='" +과목코드+"' and 분반='" + 분반+"' and 주차='" + i + "'");
                    if(강의계획.getValueAt(i-1, 1) == null) pstmt.setString(1, "");
                    else pstmt.setString(1, (String) 강의계획.getValueAt(i - 1, 1));
                    if(강의계획.getValueAt(i-1, 2) == null) pstmt.setString(2, "");
                    else pstmt.setString(2, (String) 강의계획.getValueAt(i - 1, 2));
                    if(강의계획.getValueAt(i-1, 3) == null) pstmt.setString(3, "");
                    else pstmt.setString(3, (String) 강의계획.getValueAt(i - 1, 3));
                    if(강의계획.getValueAt(i-1, 4) == null) pstmt.setString(4, "");
                    else pstmt.setString(4, (String) 강의계획.getValueAt(i - 1, 4));

                    cnt =  pstmt.executeUpdate();
                    
                    if(cnt<0) System.out.println("미쳤음 왜안됨!!");
                }
            }
        }catch(Exception e){ e.printStackTrace(); }
        finally{
            try{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
                if(conn!=null) conn.close();
            }catch(Exception e){}
        }
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new 강의계획서입력사항().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable 강의계획;
    private javax.swing.JTextField 강의시간;
    private javax.swing.JTextField 개설전공;
    private javax.swing.JTextArea 과목개요;
    private javax.swing.JTextField 과목명;
    private javax.swing.JTextArea 과제물작성요령;
    private javax.swing.JTextArea 과제물평가방법;
    private javax.swing.JTextField 교과목코드;
    private javax.swing.JTextField 교수email;
    private javax.swing.JTextField 교수명;
    private javax.swing.JTextArea 교수목표;
    private javax.swing.JTextField 교수전화번호;
    private javax.swing.JTextField 교재명;
    private javax.swing.JTextField 권장선수과목;
    private javax.swing.JTextArea 문제분석설계방법;
    private javax.swing.JTextField 성적평가방법;
    private javax.swing.JTextArea 수강생유의사항;
    private javax.swing.JTextArea 수업방법;
    private javax.swing.JTextField 연구실;
    private javax.swing.JTextField 이수학년;
    private javax.swing.JTextField 작성일시;
    private javax.swing.JButton 저장;
    private javax.swing.JTextArea 참고문헌;
    private javax.swing.JButton 취소;
    private javax.swing.JTextField 필수선수과목명;
    private javax.swing.JTextField 학점;
    private javax.swing.JTextField 행정실전화;
    // End of variables declaration//GEN-END:variables

}
