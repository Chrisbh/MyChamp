/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.FileReader;
import java.util.Properties;

/**
 *
 * @author Dennis
 */
public class MyChampDBManager
{

    protected SQLServerDataSource ds;

    /*
     * Contains the settings for the SQL server.
     */
    public MyChampDBManager() throws Exception
    {
        Properties props = new Properties();
        props.load(new FileReader("MyChamp.cfg"));
   
        
        ds = new SQLServerDataSource();

        ds.setServerName(props.getProperty("SERVER"));
        ds.setPortNumber(Integer.parseInt(props.getProperty("PORT")));
        ds.setDatabaseName(props.getProperty("DATABASE"));
        ds.setUser(props.getProperty("USER"));
        ds.setPassword(props.getProperty("PASSWORD"));
    }
}
