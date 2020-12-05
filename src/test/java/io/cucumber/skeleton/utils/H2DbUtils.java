package io.cucumber.skeleton.utils;

import org.junit.Test;

import java.sql.*;
import java.sql.DriverManager;

public class H2DbUtils {
    //driver and url
    static final String JDBC_DRIVER="org.h2.Driver";
    static final String DB_URL="jdbc:h2:~/test";

    // database credential
    static final String USER="sa";
    static final String PASSWORD="";


    public void create() throws Exception{
        Connection connection=null;
        Statement statement=null;
        try{
            //Registering the jdbc driver --step 1
            Class.forName(JDBC_DRIVER);
            //Open a connection--step 2
            System.out.println("connecting to database....");
            connection= DriverManager.getConnection(DB_URL,USER,PASSWORD);
            //Executing the query --step 3
            System.out.println("creating a table.....");
            statement=connection.createStatement();
            String sql="CREATE TABLE   AUTOMATION " +
                    "(id INTEGER not NULL, " +
                    " topic VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);
            System.out.println("table is created");

            //cleaning up the env

        }
        catch (SQLException ex){
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


    public void insert() throws Exception{
       Connection connection=null;
        Statement statement=null;
        try{
            //Registering the jdbc driver --step 1
            Class.forName(JDBC_DRIVER);
            //Open a connection--step 2
            System.out.println("connecting to database....");
            connection= DriverManager.getConnection(DB_URL,USER,PASSWORD);
            //Executing the query --step 3
            System.out.println("inserting data into the table.....");
            statement=connection.createStatement();

//            String sql="INSERT INTO automation "+"VALUES(1,'Java') ";
//            statement.executeUpdate(sql);
//            System.out.println("data is inserted");

             String sql="INSERT INTO automation "+"VALUES(2,'Python') ";
            statement.executeUpdate(sql);
            System.out.println("data is inserted");

            sql="INSERT INTO automation "+"VALUES(3,'Node Js') ";
            statement.executeUpdate(sql);
            System.out.println("data is inserted");

            sql="INSERT INTO automation "+"VALUES(4,'Java Script') ";
            statement.executeUpdate(sql);
            System.out.println("data is inserted");


            //cleaning up the env

        }
        catch (SQLException ex){
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

    public void read() throws Exception{
        Connection connection=null;
        Statement statement=null;
        try{
            //Registering the jdbc driver --step 1
            Class.forName(JDBC_DRIVER);
            //Open a connection--step 2
            System.out.println("connecting to database....");
            connection= DriverManager.getConnection(DB_URL,USER,PASSWORD);
            //Executing the query --step 3
            System.out.println("inserting data into the table.....");
            statement=connection.createStatement();
            String sql="SELECT id,products from INVENTORY";
            ResultSet rs= statement.executeQuery(sql);
            // Extract the data from the result set
            while (rs.next()){
                int id=rs.getInt("id");
                String product=rs.getString("products");
                System.out.println(id+" ==="+product);
                System.out.println();
            }
            //cleaning up the env
        }
        catch (SQLException ex){
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




//    @Test
//    public ResultSet read(String sql) throws Exception{
//        Connection connection=null;
//        Statement statement=null;
//        ResultSet rs=null;
//        try{
//            //Registering the jdbc driver --step 1
//            Class.forName(JDBC_DRIVER);
//            //Open a connection--step 2
//            System.out.println("connecting to database....");
//            connection= DriverManager.getConnection(DB_URL,USER,PASSWORD);
//            //Executing the query --step 3
//            System.out.println("reading   data from the table.....");
//            statement=connection.createStatement();
//           // String sql="SELECT id,topic from AUTOMATION";
//           rs= statement.executeQuery(sql);
//        }
//        catch (SQLException ex){
//            ex.printStackTrace();
//        }
////        finally {
////            statement.close();
////            connection.close();
////            try {
////                if(statement!=null) statement.close();
////            }
////            catch (SQLException se){
////                se.printStackTrace();
////            }
////            System.out.println("END");
////        }
//
//        return rs;
//    }

    @Test
    public void update() throws Exception{
        Connection connection=null;
        Statement statement=null;
        try{
            //Registering the jdbc driver --step 1
            Class.forName(JDBC_DRIVER);
            //Open a connection--step 2
            System.out.println("connecting to database....");
            connection= DriverManager.getConnection(DB_URL,USER,PASSWORD);
            //Executing the query --step 3
            System.out.println("inserting data into the table.....");
            statement=connection.createStatement();
            //String sql="UPDATE inventory "+"SET name='test update' WHERE id=6 ";
            String sql="UPDATE INVENTORY "+
                    "SET description=NULL";
            statement.executeUpdate(sql);
            System.out.println("data is updated");

            //cleaning up the env

        }
        catch (SQLException ex){
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
    public void delete() throws Exception{
        Connection connection=null;
        Statement statement=null;
        try{
            //Registering the jdbc driver --step 1
            Class.forName(JDBC_DRIVER);
            //Open a connection--step 2
            System.out.println("connecting to database....");
            connection= DriverManager.getConnection(DB_URL,USER,PASSWORD);
            //Executing the query --step 3
            System.out.println("inserting data into the table.....");
            statement=connection.createStatement();
            String sql="DELETE FROM inventory "+"WHERE id=6 ";
            statement.executeUpdate(sql);
            System.out.println("data is deleted");


            String sqlRead="Select * FROM inventory";
            ResultSet rs=statement.executeQuery(sqlRead);

            while (rs.next()){
                System.out.println(rs.getString("products"));
            }
            //cleaning up the env

        }
        catch (SQLException ex){
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


    public static void main(String[] args) throws  Exception {



    }

}
