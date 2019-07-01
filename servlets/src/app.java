import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

@WebServlet("/test/app")
public class app extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




       // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
           // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Map<String, String[]> parameterMap = req.getParameterMap();
            String log = parameterMap.get("username")[0];
            String pas = DigestUtils.md5Hex(parameterMap.get("password")[0]);
            String k = "grgr";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/site","root","1234");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users where login = ? and password = ?");
            preparedStatement.setString(1, log);
            preparedStatement.setString(2, pas);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            //String passw = DigestUtils.md5Hex(parameterMap.get("password")[0]); //полученный пароль зашифрованный в md5
            //String password=DigestUtils.md5Hex(passw);
            //System.out.println(passw);
           //  String username = parameterMap.get("username")[0]; //полученный логин
            try { //выкидывает sql экспешн если неправильный пароль/логин, но на всякий случай добавим проверку в if

               String passwf = resultSet.getString(3); //пароль полученный из базы
               String usernam = resultSet.getString(2); //логин полученный из базы
               System.out.println(passwf+"-пароль в базе");
               System.out.println(pas+"-пароль зашифрованный");
               System.out.println(log + " логин полученный");
               System.out.println(usernam+" логин из базы");
              // System.out.println(password);
               if(usernam.equals(log)&&passwf.equals(pas))
               {
                   Cookie cookie = new Cookie(log,pas);
                   cookie.setPath("/");
                   resp.addCookie(cookie);
                   resp.sendRedirect(req.getContextPath() + "/log");
                   System.out.println("Пользователь "+ usernam + " залогинился");
               }
           }
           catch (SQLException ex)
           {
               System.out.println(log);
               System.out.println(pas);

               ex.printStackTrace();
               resp.sendRedirect(req.getContextPath() + "/test/test.html");
           }
        }

        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("не зашло??????????????????????????????????????????????????????????????????");
            e.printStackTrace();
        }
    }

}
