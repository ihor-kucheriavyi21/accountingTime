package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public static boolean validate(String name,String pass){
        boolean status=false;
        try{
            SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.getInstance();
            Connection con= sqlDatabaseManager.getConnection();

            PreparedStatement ps=con.prepareStatement(
                    "select * from person where login=? and pass=?");
            ps.setString(1,name);
            ps.setString(2,pass);

            ResultSet rs=ps.executeQuery();
            status=rs.next();

        }catch(Exception e){System.out.println(e);}
        return status;
    }
}