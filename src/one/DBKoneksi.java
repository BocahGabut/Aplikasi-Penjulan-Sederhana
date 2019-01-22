package one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBKoneksi {
    private Connection koneksi;
    public Connection konek(){
       //untuk koneksi ke driver
       try{
           Class.forName("org.sqlite.JDBC");
           //System.out.println("berhasil load driver");
       }catch(ClassNotFoundException cnfe){
           System.out.println("Tidak ada Driver "+cnfe);
       }

       //untuk koneksi ke database
       try{
           String url="jdbc:sqlite:db/tabel_barang.sqlite";
           koneksi=DriverManager.getConnection(url);
           //System.out.println("Berhasil koneksi");
       }catch(SQLException se){
           System.out.println("Gagal koneksi "+se);
           JOptionPane.showMessageDialog(null,"Gagal Koneksi Database","Peringatan",JOptionPane.WARNING_MESSAGE);
       }
       return koneksi;
    }
    
}
