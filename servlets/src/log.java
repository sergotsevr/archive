import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

@WebServlet ("/log")
public class log extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/site", "root", "1234");

            Cookie[] cookies = req.getCookies();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users where login = ? and password = ?");


            if (cookies != null) {
               // System.out.println("cooki ne null");
                for (Cookie cookie : cookies) {

                    String log = cookie.getName();
                    String pas = cookie.getValue();
                    preparedStatement.setString(1, log);
                    preparedStatement.setString(2, pas);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    resultSet.next();

                    try { //выкидывает sql экспешн есл инеправильный пароль/логин, но на всякий случай добавим проверку в if

                        String passwf = resultSet.getString(3); //пароль полученный из базы
                        String usernam = resultSet.getString(2); //логин полученный из базы

                        if (usernam.equals(log) && passwf.equals(pas)) {

                            resp.reset();
                            req.setAttribute("password", pas);//для того чтобы было видно переменную в jsp
                            req.setAttribute("login", usernam);//для того чтобы было видно переменную в jsp
                            try {
                                req.getRequestDispatcher("/WEB-INF/docs.jsp").forward(req, resp);
                            }
                            catch (Exception e)
                            {

                            }
                            System.out.println("Пользователь " + usernam + " залогинился через куки");
                            //resp.sendRedirect(req.getContextPath() + "/index.html");
                        }
                    } catch (SQLException ex) {
                        //resp.sendRedirect(req.getContextPath() + "/test/test.html");
                        System.out.println("Cookie " + cookie.getName() + " не зашли");
                        //ex.printStackTrace();
                        try {               //нужно чтобы при неправильных куки сервер не выкидывал Exception
                           // resp.sendRedirect(req.getContextPath() + "/test/test.html");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                     }

                   }
                }
            /*else {
                resp.sendRedirect(req.getContextPath() + "/test/test.html");
            }*/
            }

            catch (Exception e)
            {
               // resp.sendRedirect(req.getContextPath() + "/test/test.html");
                e.printStackTrace();
            }
        resp.sendRedirect(req.getContextPath() + "/test/test.html");
    }
}
