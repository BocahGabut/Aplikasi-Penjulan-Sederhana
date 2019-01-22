package Form_Update;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import one.Form_Home_Admin;
import one.db_connection;


public final class Form_Entry_Data_Baru extends javax.swing.JDialog {
private DefaultTableModel model;
    String ex;
    public Form_Entry_Data_Baru() {
     
        initComponents();
            
            tanggal();
            
           }

    private void Kode()
    {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("ddMM");
        
        Random rnd = new Random();
        int no1 = rnd.nextInt(999);
        
        kode.setText("BRG"+s.format(date)+no1);
        kode.setEditable(false);
    }
    
    private void tanggal()
    {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd MMMM YYYY");
        
        label_tanggal.setText(s.format(date));
        setJam();
        tampil();
        Kode();
    }
    
    public void setJam(){
        ActionListener taskPerformer = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {
        String nol_jam = "", nol_menit = "",nol_detik = "";

        java.util.Date dateTime = new java.util.Date();
        int nilai_jam = dateTime.getHours();
        int nilai_menit = dateTime.getMinutes();
        int nilai_detik = dateTime.getSeconds();

        if(nilai_jam <= 9) nol_jam= "0";
        if(nilai_menit <= 9) nol_menit= "0";
        if(nilai_detik <= 9) nol_detik= "0";

        String waktu = nol_jam + Integer.toString(nilai_jam);
        String menit = nol_menit + Integer.toString(nilai_menit);
        String detik = nol_detik + Integer.toString(nilai_detik);

        label_jam.setText(waktu+":"+menit+":"+detik+"");
        }
    };
        new Timer(1000, taskPerformer).start();
    }
    
    private void Save()
    {
        try
        {
         SimpleDateFormat s = new SimpleDateFormat("MMM d, yyyy");
         ex = s.format(expired.getDate());
        Statement stat = (Statement) db_connection.getKoneksi().createStatement();
        String sql = "Insert into d_barang values ('"+kode.getText()+"','"+nama.getText()+"','"+jumlah.getText()+"',"
                + "'"+h_beli.getText()+"','"+h_jual.getText()+"','"+label_tanggal.getText()+"','"+ex+"')";
        PreparedStatement p = (PreparedStatement) db_connection.getKoneksi().prepareStatement(sql);
        p.executeUpdate();
        JOptionPane.showMessageDialog(null, "Save Succes......");
        kosong();
        }
        catch(SQLException err)
        {
        JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    public void tampil()
    {
        model = new DefaultTableModel();
        tabel_barang.setModel(model);
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah Barang");
        model.addColumn("Harga Beli");
        model.addColumn("Harga Jual");
        model.addColumn("Tgl Masuk");
        model.addColumn("Expired");
        
        getdata();
    }
    
    public void getdata()
    {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try
        {
            Statement stat = (Statement) db_connection.getKoneksi().createStatement();
            String sql= "SELECT * FROM d_barang order by nama_barang asc";
            ResultSet res = stat.executeQuery(sql);
            
            while(res.next())
            {
                Object[] obj = new Object[7];
                obj[0] = res.getString("kode_barang");
                obj[1] = res.getString("nama_barang");
                obj[2] = res.getString("jumlah_barang");
                obj[3] = res.getString("harga_beli");
                obj[4] = res.getString("harga_jual");
                obj[5] = res.getString("tgl_masuk");
                obj[6] = res.getString("expired");
                
                model.addRow(obj);
            }
            
        }
        catch(SQLException err)
        {JOptionPane.showMessageDialog(null, err.getMessage());}
    }
    
    private void Delete()
    {
        int i = tabel_barang.getSelectedRow();
        
        if(i==-1)
        {
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Data");
            return;
        }
        else
        {
            int pesan = JOptionPane.showConfirmDialog(null, "Anda Yakin Akan Menghapus Data " + model.getValueAt(i, 0),"Konfirmasi",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(pesan==JOptionPane.OK_OPTION)
            {
                try
                {
                Statement stat = (Statement) db_connection.getKoneksi().createStatement();
                String sql = "Delete from d_barang where kode_barang = '"+model.getValueAt(i, 0)+"'";
                PreparedStatement p = (PreparedStatement) db_connection.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                JOptionPane.showMessageDialog(null, "Delete Success.......");
                getdata();
                }
                catch(SQLException err)
                {JOptionPane.showMessageDialog(null, err.getMessage());}
            }
        }
    }
    
    private void kosong()
    {
        kode.setText(null);
        nama.setText(null);
        jumlah.setText(null);
        h_beli.setText(null);
        h_jual.setText(null);
        expired.setDate(null);
        Kode();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        kode = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        h_beli = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_selesai = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        h_jual = new javax.swing.JTextField();
        expired = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_barang = new javax.swing.JTable();
        btn_refres_tabel = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        label_jam = new javax.swing.JLabel();
        label_tanggal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entry Data Baru", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setText("Kode Barang");

        jLabel4.setText("Nama Barang");

        jLabel6.setText("Harga Beli");

        jLabel7.setText("Harga Jual");

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_delete.setText("Delete Data");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_selesai.setText("Selesai");
        btn_selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selesaiActionPerformed(evt);
            }
        });

        jLabel8.setText("Expired");

        tabel_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Beli", "Harga Jual", "Tanggal Masuk"
            }
        ));
        tabel_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_barangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_barang);

        btn_refres_tabel.setText("Refres Tabel");
        btn_refres_tabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refres_tabelActionPerformed(evt);
            }
        });

        jLabel9.setText("Jumlah Barang");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kode)
                            .addComponent(nama)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(expired, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(h_beli)
                                    .addComponent(h_jual))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jumlah))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_delete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_refres_tabel))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(h_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(h_jual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(expired, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan)
                    .addComponent(btn_delete)
                    .addComponent(btn_refres_tabel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_selesai)
                .addContainerGap())
        );

        label_jam.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        label_jam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_jam.setText("JAM");

        label_tanggal.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        label_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_tanggal.setText("TANGGAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_tanggal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                        .addComponent(label_jam))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_tanggal)
                    .addComponent(label_jam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
         
        Save();
        
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selesaiActionPerformed
       
        this.dispose();
        
    }//GEN-LAST:event_btn_selesaiActionPerformed

    private void btn_refres_tabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refres_tabelActionPerformed
        
        tampil();
        JOptionPane.showMessageDialog(null, "Refesh Berhasil.......");
    }//GEN-LAST:event_btn_refres_tabelActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        
        Delete();
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void tabel_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_barangMouseClicked
        
    }//GEN-LAST:event_tabel_barangMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Entry_Data_Baru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Entry_Data_Baru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Entry_Data_Baru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Entry_Data_Baru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_refres_tabel;
    private javax.swing.JButton btn_selesai;
    private javax.swing.JButton btn_simpan;
    private com.toedter.calendar.JDateChooser expired;
    private javax.swing.JTextField h_beli;
    private javax.swing.JTextField h_jual;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField kode;
    private javax.swing.JLabel label_jam;
    private javax.swing.JLabel label_tanggal;
    private javax.swing.JTextField nama;
    private javax.swing.JTable tabel_barang;
    // End of variables declaration//GEN-END:variables
}
