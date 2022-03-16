package javaapplication1;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;


public class LoginForm extends javax.swing.JFrame {

    static public 학과장메뉴 학과장;

    public LoginForm() {
        initComponents();
    }
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        ID입력 = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Pass입력 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("로그인");
        setForeground(new java.awt.Color(255, 255, 0));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("맑은 고딕", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel3.setText("비밀번호");
        jLabel3.setBounds(10, 80, 56, 20);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ID입력.setFont(new java.awt.Font("맑은 고딕", 0, 12));
        ID입력.setBounds(110, 40, 120, 23);
        jLayeredPane1.add(ID입력, javax.swing.JLayeredPane.DEFAULT_LAYER);

        loginButton.setFont(new java.awt.Font("맑은 고딕", 0, 14));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        loginButton.setBounds(150, 120, 80, 30);
        jLayeredPane1.add(loginButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 0, 14));
        jLabel2.setText("아 이 디");
        jLabel2.setBounds(10, 40, 60, 20);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Pass입력.setBounds(110, 80, 120, 21);
        jLayeredPane1.add(Pass입력, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed

        if(ID입력.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "ID를 입력해주세요");
            return;
        }
        if(Pass입력.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
            return;
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        DBConn dbconn = new DBConn();
        conn = dbconn.getConnection();

        try {
            stmt = conn.createStatement();
            String query = "select * from 시스템등록 where 아이디='"+ID입력.getText()+"' and 패스워드='"+Pass입력.getText()+"'";
            rs = stmt.executeQuery(query);

            if(rs.next() == false)
            {
                JOptionPane.showMessageDialog(null, "ID와Password를 확인해 주세요");
                ID입력.setText("");
                Pass입력.setText("");
                return;
            }
            if(rs.getString("사용자구분").equals("학생"))
            {
                수강신청화면 학생 = new 수강신청화면(ID입력.getText());
                학생.setVisible(true);
            }
            else if(rs.getString("사용자구분").equals("교수"))
            {
                교수메뉴 교수 = new 교수메뉴(Pass입력.getText());
                교수.setVisible(true);
            }
            else if(rs.getString("사용자구분").equals("교직원"))
            {
                교직원메뉴 교직원 = new 교직원메뉴(ID입력.getText());
                교직원.setVisible(true);
            }
            else if(rs.getString("사용자구분").equals("학과장"))
            {
                학과장 = new 학과장메뉴(Pass입력.getText());
                학과장.setVisible(true);
            }
            this.dispose();

        } catch (Exception e) {
            System.out.println("Error");
        }
}//GEN-LAST:event_loginButtonActionPerformed
   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID입력;
    private javax.swing.JPasswordField Pass입력;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JButton loginButton;
    // End of variables declaration//GEN-END:variables
}
