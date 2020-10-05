package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLDatabaseManager {
    private static final Logger logger = Logger.getLogger(SQLDatabaseManager.class.getName());
    private static SQLDatabaseManager sqlDatabaseManager;
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();
    //String url = "jdbc:mysql://localhost:3306/testBDForProject?serverTimezone=Europe/Kiev&useSSL=false&user=root&password=1234";

    private SQLDatabaseManager() {
        try {
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            System.out.println(getUrlFromProperties());
            cpds.setJdbcUrl(getUrlFromProperties());
            //cpds.setJdbcUrl("jdbc:mysql://localhost:3306/testBDForProject?serverTimezone=Europe/Kiev&useSSL=false");
            cpds.setUser("root");
            cpds.setPassword("1234");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static SQLDatabaseManager getInstance() {
        if (sqlDatabaseManager == null) {
            sqlDatabaseManager = new SQLDatabaseManager();
        }
        return sqlDatabaseManager;
    }

    public Connection getConnection() throws SQLException {

        return cpds.getConnection();
    }

    private String getUrlFromProperties() {
        String out = null;
        InputStream input = null;
        try {
            input = this.getClass().getClassLoader().getResourceAsStream("app.properties");
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            out = prop.getProperty("connectionUrl");
            System.out.println(out);
        } catch (IOException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }
        return out;
    }
}
