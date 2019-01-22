package one;
import java.sql.*;
import javax.swing.*;

public class DBConnection_login {
    private Connection koneksi;

    public Connection konek(){
       //untuk koneksi ke driver
       try{
           Class.forName("org.sqlite.JDBC");
       }catch(ClassNotFoundException cnfe){
           System.out.println("Tidak ada Driver "+cnfe);
       }

       //untuk koneksi ke database
       try{
           String url="jdbc:sqlite:db/Kedai_One.sqlite";
           koneksi=DriverManager.getConnection(url);
           //JOptionPane.showMessageDialog(null, "koneksi ke Database BERHASIL");
       }catch(SQLException se){
           JOptionPane.showMessageDialog(null,"Gagal Koneksi Database","Peringatan",JOptionPane.WARNING_MESSAGE);
       }
       return koneksi;
       
    }
}