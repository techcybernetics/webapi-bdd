package io.cucumber.skeleton.utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CommonHelper {
    public WebDriver driver=null;
    static final String JDBC_DRIVER="org.h2.Driver";
    static final String DB_URL="jdbc:h2:~/test";

    // database credential
    static final String USER="sa";
    static final String PASSWORD="";
    public void quitDriver(Scenario scenario){

        if(scenario.isFailed()){

            try{
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                File screenshot_with_scenario_name = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileHandler.copy(screenshot_with_scenario_name,
                        new File("./target/" + scenario.getName() + ".png"));
                System.out.println(scenario.getName());
                scenario.attach(screenshot, "image/png", "snap");
            }

            catch (WebDriverException | IOException somePlatformsDontSupportScreenshots){
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }


        driver.close();
    }

    public static Properties readProperties(String filepath) throws IOException{
        FileInputStream fis=null;
        Properties prop=null;
        try {
            fis=new FileInputStream(filepath);
            prop=new Properties();
            prop.load(fis);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            fis.close();

        }
        return prop;

    }

    public void dbUpdt(String sql) throws Exception{

        Connection connection=null;
        Statement statement=null;
        try{
            //Registering the jdbc driver --step 1
            Class.forName(JDBC_DRIVER);
            //Open a connection--step 2
            System.out.println("connecting to database....");
            connection= java.sql.DriverManager.getConnection(DB_URL,USER,PASSWORD);
            //Executing the query --step 3
            System.out.println("reading data into the table.....");
            statement=connection.createStatement();
             //sql="UPDATE INVENTORY SET description=" +"'"+itemDesc+"'"+" where products="+"'"+item+"'";
            System.out.println(sql);
            statement.executeUpdate(sql);

            //cleaning up the env
        }
        catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        finally {
            statement.close();
            connection.close();
            try {
                if(statement!=null) statement.close();
            }
            catch (SQLException se){
                se.printStackTrace();
            }
            System.out.println("END");
        }
    }

}
