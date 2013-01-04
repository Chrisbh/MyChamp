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

    protected SQLServerDataSource dataSource;

    /*
     * Contains the settings for the SQL server.
     */
    public MyChampDBManager() throws Exception
    {
        Properties props = new Properties();
        props.load(new FileReader("MyChamp.cfg"));

        dataSource = new SQLServerDataSource();

        dataSource.setServerName(props.getProperty("SERVER"));
        dataSource.setPortNumber(Integer.parseInt(props.getProperty("PORT")));
        dataSource.setDatabaseName(props.getProperty("DATABASE"));
        dataSource.setUser(props.getProperty("USER"));
        dataSource.setPassword(props.getProperty("PASSWORD"));
    }
}
