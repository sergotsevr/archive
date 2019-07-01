import java.sql.Connection;
import java.sql.DriverManager;

public class user {
    final String company ="";
    void getListNotes(String company)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/site","root","1234");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Пользователь " + company+" не смог получить доступ");
        }
        }
}
