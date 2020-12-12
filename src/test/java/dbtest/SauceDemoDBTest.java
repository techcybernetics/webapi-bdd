package dbtest;

import io.cucumber.skeleton.pages.sauceDemoPages.SauceDemoHomePage;
import io.cucumber.skeleton.pages.sauceDemoPages.SauceDemoLoginPage;
import io.cucumber.skeleton.utils.CommonHelper;
import io.cucumber.skeleton.utils.DriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SauceDemoDBTest extends CommonHelper {
    SauceDemoHomePage sauceDemoHomePage;
    //driver and url
    static final String JDBC_DRIVER="org.h2.Driver";
    static final String DB_URL="jdbc:h2:~/test";

    // database credential
    static final String USER="sa";
    static final String PASSWORD="";
    @Test
   public void test() throws  Exception{
        ArrayList<String> str=new ArrayList<>();
        driver=DriverManager.initDriver("chrome","http://www.saucedemo.com");
        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage(driver);
        sauceDemoHomePage= sauceDemoLoginPage.login("standard_user","secret_sauce");
               for(WebElement el:sauceDemoHomePage.getItems()){
            str.add(el.getText());

        }
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
            String sql="SELECT id,products from INVENTORY";
            ResultSet rs= statement.executeQuery(sql);
            // Extract the data from the result set
            while (rs.next()){
                int id=rs.getInt("id");
                String product=rs.getString("products");
                System.out.println(product);
               Assert.assertTrue( str.contains(product));


            }
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



    @Test
    public void testDBUpdate() throws  Exception{

        driver=DriverManager.initDriver("chrome","http://www.saucedemo.com");
        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage(driver);
        sauceDemoHomePage= sauceDemoLoginPage.login("standard_user","secret_sauce");
        ArrayList<String> str=new ArrayList<>();

        for(WebElement el:sauceDemoHomePage.getItems()){
            str.add(el.getText());

        }

       for(String pr:str){
           String itemDesc= sauceDemoHomePage.getItemDescription(pr);
           if(itemDesc.contains("'")){
               itemDesc= itemDesc.replace("'","");
               dbUpdt(itemDesc,pr);
           }
           else{
               dbUpdt(itemDesc,pr);
           }

       }




    }

    public void dbUpdt(String itemDesc, String item) throws Exception{

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
            String sql="UPDATE INVENTORY SET description=" +"'"+itemDesc+"'"+" where products="+"'"+item+"'";
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
