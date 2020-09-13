package toolbox.datatools;

import global.Config;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import process_model.process.input.data.utility.DataRow;
import toolbox.Tools;

public class DBTools {
  private static Connection connection=null;
  private static Statement statement=null;
  private static  ResultSet resultSet;
  public static int count=0;
  public static void connnect( String connectionName, String DBName ,String userName, String passWord )
          throws SQLException {
    try 
    {
         Class.forName("oracle.jdbc.OracleDriver"); // this is a package in OJDBC14 whaich must be added to the project library.
         connection=DriverManager.getConnection(connectionName+":"+DBName,userName,passWord);      
    }
    catch (SQLException se)
    {
    //System.out.print(se);
    }
    
    catch(Exception e)
    {
       e.printStackTrace();
     }
  }
  
  
  public static ResultSet executeSQL(String sql) throws SQLException
  {
    String c = "jdbc:oracle:thin:@localhost:1521";
            String d = "XE";
            String u = "biologist";
            String p = "biologist";
    if (connection==null|| connection.isClosed())
         connnect(c, d, u, p);

      statement=connection.createStatement();
      statement.execute(sql);
      resultSet = statement.executeQuery(sql);
      return resultSet;
  }
  
public static void executeDML(String dml) throws SQLException
  {
    try{


    String c = "jdbc:oracle:thin:@localhost:1521";
            String d = "XE";
            String u = "biologist";
            String p = "biologist";
    if (connection==null|| connection.isClosed())
         connnect(c, d, u, p);

   //connection.setAutoCommit(true);
    statement=connection.createStatement();
    statement.executeUpdate(dml );
    //statement.execute(dml);
    //connection.rollback();    
    //connection.commit();
    //connection.close();


 // very important code which comprimises the number of connections and the size of cursor
if (count==99)
{
    connection.commit();
    disconnect();
    if (!connection.isClosed())
        connection.close();

    if (connection.isClosed())
         connnect(c, d, u, p);

    count=0;
 }
 else
     count++;
    //System.out.print("Update Successful");
 }
catch(Exception ex){
    //System.out.print("DBTools ExecuteDML Function: Error in Oracle DataBase connection, or database not found, connection must be xe, user name: biologist, password: biologist");
   Logger.getLogger(DBTools.class.getName()).log(Level.SEVERE, null, ex);
}

  }

 public static void executeDDL(String ddl) throws SQLException
  {      
       try {            
            String c = "jdbc:oracle:thin:@localhost:1521";
            String d = "XE";
            String u = "biologist";
            String p = "biologist";
     if (connection==null|| connection.isClosed())
           connnect(c, d, u, p);
            statement=connection.createStatement();
            statement.execute(ddl);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DBTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
//-------------------------------------------------------------------------------------------------------------------------------------------------------
//public void connectToDB(String userName, String passWord)
public static void connectToDB() throws SQLException
{
 
    String c="jdbc:oracle:thin:@sirius.dcs.aber.ac.uk:1521";
    String d="g02006";
    String u="amb04";
    String p="souf95";
    connnect(c,d,u,p);
    //db.executeDML("insert into staff values('ahmed',50)");
    //db.executeSQL("select * from all_tables where owner='HIMET'");
}

      
public static void createTable(String c,String d,String u, String p, String tableName, String attribute, String dataType,int dataSize) throws SQLException
  {
      statement=connection.createStatement();
       try {
            DBTools db = new DBTools();
            /* 
            String c = "jdbc:oracle:thin:@localhost:1521";
            String d = "xe";
            String u = "biologist";
            String p = "biologist";
            String tn="YVariables";
            String at="columnName";
            String dt="varchar2";
            int ds=50;
             */
            db.connnect(c, d, u, p);
            statement.execute("create table"+" "+tableName+" "
                    + "("
                    + attribute+" "+dataType+"("+dataSize+")"+")" );
                    
        } catch (SQLException ex) {
            Logger.getLogger(DBTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

public static void addAttributeToTable(String c,String d,String u, String p, String tableName, String attribute, String dataType,int dataSize) throws SQLException
  {
      statement=connection.createStatement();
       try {
            DBTools db = new DBTools();
            /* 
            String c = "jdbc:oracle:thin:@localhost:1521";
            String d = "xe";
            String u = "biologist";
            String p = "biologist";
            String tn="YVariables";
            String atr="columnName";
            String dt="varchar2";
            int ds=50;
             */
            db.connnect(c, d, u, p);
            statement.execute("Alter table"+" "+tableName+" "+"Add"+ " "+ attribute+" "+dataType+"("+dataSize+"");
                    
        } catch (SQLException ex) {
            Logger.getLogger(DBTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }


  public static void disconnect() throws SQLException
  {
  
if (connection!=null)
      connection.close();
if(statement!=null)
      statement.close();
  }






public static DataFile fileToJavaClass() throws IOException
    {
    Tools tools=new Tools();

    ArrayList<String> fileDataArrayList=new ArrayList<String>();
    ArrayList<String> attributeList=new ArrayList<String>();
    DataFile dataSetFile = new DataFile();
    
    File file = Tools.chooseFile(Config.defaultLocation,null,null,false);      
        if (file != null)
         {                     
            dataSetFile=new DataFile();
            tools.viewAnyFile(file);
            dataSetFile.add(file,tools.fileToArrayList(fileDataArrayList,file.getPath()));
            attributeList=fileDataArrayList;            
            //System.out.print(dataSetFile.getFile().getPath());           
         }
            return dataSetFile;
   }

//----------------------------------------------------------------------------
public static DataFile fileDBTable(File file, String tableName) throws IOException
    {
    
    if (file==null)
        file = Tools.chooseFile(Config.defaultLocation,null,null,false);
    ArrayList<String> fileDataArrayList=new ArrayList<String>();
    ArrayList<String> attributeList=new ArrayList<String>();
    DataFile dataSetFile = new DataFile();
        if (file != null)
         {
          try
          {
            Tools.viewAnyFile(file);
            //dataSetFile.add(file,tools.fileToArrayList(fileDataArrayList,file.getPath()));
            attributeList=fileDataArrayList;
            //System.out.print(dataSetFile.getFile().getPath());
            DBTools db = new DBTools();
                try {
                      String c = "jdbc:oracle:thin:@localhost:1521";
                      String d = "XE";
                      String u = "biologist";
                      String p = "biologist";
                     db.connnect(c, d, u, p);
                } catch (SQLException ex) {
                    Logger.getLogger(DBTools.class.getName()).log(Level.SEVERE, null, ex);
                }


            ArrayList<DataRow> rowsList=Tools.fileToTokenisedArrayList(file.getPath());
            for(Object o:rowsList)
            {
                DataRow row=(DataRow) o;
                //System.out.print(row.toString());
                ArrayList<String> valuesList=row.getValuesList();

                String values="";
                for (Object v:valuesList)
                {
                    String value=(String) v;

                 if (attributeList.indexOf(v)<attributeList.size()-1)
                    values=values+value+", ";
                 else
                     values=values+value;
                }
               db.executeDML("insert into "+tableName+" values( "+values+" )");
               db.disconnect();
            }
           }
            catch (SQLException ex) {
                Logger.getLogger(DBTools.class.getName()).log(Level.SEVERE, null, ex);
            }           catch (IOException ex)
           {
           }
         }
            return dataSetFile;
   }
//-----------------------------------------------------------------------------
//                Build Data base file from data set file
//-----------------------------------------------------------------------------
private String buildDBTable( File file)
   {
    Tools tools=new Tools();
    if (file==null)
        file = Tools.chooseFile(Config.defaultLocation,null,null,false);
    ArrayList<String> fileDataArrayList=new ArrayList<String>();
    ArrayList<String> attributeList=new ArrayList<String>();
    DataFile dataSetFile = new DataFile();
    String tableName=null;

    if (file != null)
       {
            try {
                //System.out.print("---------------------------------------------");
                //System.out.print(dataSetFile.getFile().getPath());

                tools.viewAnyFile(file);
                dataSetFile.add(file, tools.fileToArrayList(fileDataArrayList, file.getPath()));
                attributeList = tools.fileToArrayList(file.getPath());
                tableName=file.getName();
                //System.out.print("---------------------------------------------");
                if (attributeList.size() > 0) {
                    tools.printArrayList(attributeList);
                    try {
                        DBTools db = new DBTools();
                        String c = "jdbc:oracle:thin:@localhost:1521";
                        String d = "XE";
                        String u = "biologist";
                        String p = "biologist";
                        db.connnect(c, d, u, p);
                        //-----------------------------------------------------
                        String oracleDataType = "varchar2(20)";
                        String createStatment = "create table " + tableName + " ( ";
                        for (Object o : attributeList) {
                            String attributeName = (String) o;
                            if (attributeList.indexOf(o) < attributeList.size() - 1) {
                                createStatment = createStatment + attributeName.toString() + " " + oracleDataType + ",";
                            } else {
                                createStatment = createStatment + attributeName.toString() + " " + oracleDataType;
                            }
                        }
                        createStatment = createStatment + " )";
                        db.executeDDL(createStatment);
                        //-----------------------------------------------------
                        //System.out.print(" Table Created Successfully..");
                    } catch (SQLException ex) {
                        Logger.getLogger(DBTools.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DBTools.class.getName()).log(Level.SEVERE, null, ex);
            }
   }
    return tableName;
}

//Inner Class dataFileList
//-----------------------------------------------------------------------------
public static class DataFile {
    private File location;
    private ArrayList<String> dataList=new ArrayList<String>();

    public void addFile(File inFile)
    {
        location=inFile;
    }

    public void addFileData(ArrayList<String> dataList)
    {
        dataList=dataList;
    }
    public void add(File inFile,ArrayList<String> dataList)
    {
        location=inFile;
        dataList=dataList;
    }

    public File getFile() {
        return location;
    }

    public ArrayList<String> getDataList() {
        return dataList;
    }
}

  
}
