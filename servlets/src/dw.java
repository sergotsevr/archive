import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/dw")
public class dw extends HttpServlet {
    @Override
    protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String name = parameterMap.get("name")[0];

        Cookie[] cookies = req.getCookies();
        String username =meth.checkCookie(cookies);
        if(username!=""){

            resp.setContentType("Content-type: text/zip");
            resp.setHeader("Content-Disposition", "attachment; filename=" + name );
            File file = new File("c:/Архив/" + username + "/"+parameterMap.get("name")[0]);
            System.out.println(parameterMap.get("name")[0]);
            OutputStream out = resp.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            System.out.println(file.getPath());
            byte[] buffer = new byte[9994096];
            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
        }
        else
        {
            resp.sendRedirect(req.getContextPath() + "/test/test.html");
        }
    }
}
 class meth
{
  static String checkCookie(Cookie[] cookies)
    {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/site","root","1234");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users where login = ? and password = ?");
            for (Cookie cookie : cookies) {
                preparedStatement.setString(1, cookie.getName());
                preparedStatement.setString(2, cookie.getValue());
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                String username = cookie.getName();
                String passw = cookie.getValue();

                try { //выкидывает sql экспешн есл инеправильный пароль/логин, но на всякий случай добавим проверку в if
                    String passwf = resultSet.getString(3); //пароль полученный из базы
                    String usernam = resultSet.getString(2); //логин полученный из базы
                    if (usernam.equals(username) && passw.equals(passwf)) {
                        System.out.println("Пользователь " + usernam + " скачивает");
                        return (usernam);
                    }
                } catch (SQLException ex) {
                    System.out.println("Cookie " + cookie.getName() + " не зашли при скачивании");
                }
                try {                //нужно чтобы при неправильных куки сервер не выкидывал Exception

                } catch (Exception e) {
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ("");
    }
}