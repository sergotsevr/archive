import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class fil {
   static public List print(Map<String, String[]> parameterMap) throws NullPointerException {
     /*   File folder = new File("С:/Архив/" + parameterMap.get("name")[0] + "/");
       //System.out.println(folder.getName());
        File[] listOfFiles = folder.listFiles();*/


       String dirPath = "c:/Архив/" + parameterMap.get("name")[0] + "/" ;
       File file = new File(dirPath);
       File[] files = file.listFiles();
       File folder = new File(dirPath);
       List<String> res=new ArrayList<>();
       //   resp.sendRedirect(req.getContextPath() + "/static/nofolder.html");

       //If this pathname does not denote a directory, then listFiles() returns null.
       if (folder.isDirectory()){

            for (File filer : folder.listFiles())
            {
               // System.out.println(filer.getName());
                String size=sizeofFile(filer);
                res.add(filer.getName()+"_"+size);
            }
        }
        else {
           //System.out.println("не отсканированно ни одного дела");
            throw new NullPointerException ("не отсканированно ни одного дела");
            //res.add("не отсканированно ни одного дела");
           // System.out.println(dirPath);
        }

        return (res);
    }
    static public List printn(Map<String, String[]> parameterMap) {
     /*   File folder = new File("С:/Архив/" + parameterMap.get("name")[0] + "/");
       //System.out.println(folder.getName());
        File[] listOfFiles = folder.listFiles();*/


        String dirPath = "c:/Архив/" + parameterMap.get("name")[0] + "/" ;
        File file = new File(dirPath);
        File[] files = file.listFiles();
        File folder = new File(dirPath);
        List<String> res=new ArrayList<>();

        //If this pathname does not denote a directory, then listFiles() returns null.
        if (folder!=null) {

            for (File filer : folder.listFiles())
            {
                // System.out.println(filer.getName());
                String size=sizeofFile(filer);
                res.add(filer.getName());
            }
        }
        else {
            res.add("не отсканированно ни одного дела");
            // System.out.println(dirPath);
        }

        return (res);
    }
    static private String sizeofFile(File file)
    {
        if(file.exists()) {
            final double bytes = file.length();
            final double kilobytes = bytes / 1024;
            final double megabytes = kilobytes / 1024;
            if(megabytes<1)
            {
                return Double.toString(kilobytes).substring(0,4)+"kB";
            }
            else {
                return Double.toString(megabytes).substring(0,4)+"mB";
            }
        } else {
            System.out.println("Файл не существует.");
        }
        return ("null");
    }
    static public String[] decodeClients(String str){
        String[] subStr;
        String delimeter = "_"; // Разделитель
        subStr = str.split(delimeter); // Разделения строки str с помощью метода split()
        String[] result=new String[subStr.length+1];
        result[0]=decoder(subStr[0],"clients");
        result[1]=subStr[1];
        result[2]=" "+decoder(subStr[2],"documents");
        result[3]=subStr[3];
        int lastDot = subStr[4].lastIndexOf(".");//ищет последнее вхождение точки чтобы обрезать и выделить формат и страницы
        result[4]=subStr[4].substring(0,lastDot);
        result[5]=subStr[4].substring(lastDot,subStr[4].length());
        result[6]=subStr[5];
        return result;
    }
    static private String decoder(String parametr, String table)
    {
        String res="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/site","root","1234");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM " + table+" where code = "+ parametr+ ";");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            res=resultSet.getString(2);
        } catch (ClassNotFoundException e) {
            System.out.println("ошибка в декодере");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("ошибка в декодере");
            e.printStackTrace();
        }

        return  res;
    }
}
