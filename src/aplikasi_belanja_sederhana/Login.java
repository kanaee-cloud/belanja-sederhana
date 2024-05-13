package aplikasi_belanja_sederhana;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    private PreparedStatement stat;
    private ResultSet rs;

    koneksi k = new koneksi();

    public Login() {
        initComponents();

        k.connect();
    }

    class User {
        int id_user;
        int id_level;
        String username;
        String password;
        String nama_user;

        public User() {
            this.id_user = 0;
            this.username = text_username.getText();
            this.password = text_password.getText();
            this.nama_user = "";
            this.id_level = 0;
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        text_username = new javax.swing.JTextField();
        text_password = new javax.swing.JPasswordField();
        login_submit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("LOGIN");

        jLabel2.setText("USERNAME");

        jLabel3.setText("PASSWORD");

        text_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
             
            }
        });

        text_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_passwordActionPerformed(evt);
            }
        });

        login_submit.setText("SUBMIT");
        login_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_submitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(text_password, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel2)
                                .addGap(21, 21, 21)
                                .addComponent(text_username, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(206, 206, 206)
                                .addComponent(jLabel1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(login_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addComponent(login_submit, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        pack();
    }                      

    private void text_passwordActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void login_submitActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        User u = new User();
        try {
            this.stat = k.getCon().prepareStatement(
                    "select * from user where username = '" + u.username + "' and password='" + u.password + "';");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                u.id_level = rs.getInt("id_level");
            }
            if (u.id_level == 0) {
                JOptionPane.showConfirmDialog(null, "AKUN TIDAK DITEMUKAN");
            } else {
                switch (u.id_level) {
                    case 1:
                        Register reg = new Register();
                        reg.setVisible(true);
                        this.setVisible(false);
                        break;
                    case 2:
                        Transaksi tran = new Transaksi();
                        tran.setVisible(true);
                        this.setVisible(false);
                        break;
                    case 3:
                        Transaksi tran2 = new Transaksi();
                        tran2.setVisible(true);
                        this.setVisible(false);
                        tran2.cetak_laporan_btn.setEnabled(true);
                        break;
                    case 4:
                        Barang brng = new Barang();
                        brng.setVisible(true);
                        this.setVisible(false);
                        brng.logout_btn.setEnabled(true);
                        break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }                                            

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton login_submit;
    private javax.swing.JPasswordField text_password;
    private javax.swing.JTextField text_username;
}
