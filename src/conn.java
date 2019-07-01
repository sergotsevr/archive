import java.sql.Connection;
import java.sql.DriverManager;

public class conn {
    Connection connection;
    Connection conn(){
        String userName = "root";
        String password = "1234";
        String connUrl = "jdbc:mysql://localhost:3306/test";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e)
        {
            System.out.println("Не удается зарегистрировать драйвер");
        }
        try ( Connection connection = DriverManager.getConnection(connUrl, userName, password)){
            System.out.println("подключились");
        }
        catch (Exception e)
        {
            System.out.println("не подключились");
        }
        return(connection);
    }

}
