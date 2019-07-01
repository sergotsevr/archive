import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

@WebServlet("/static/reg")
public class reg extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Map<String, String[]> parameterMap = req.getParameterMap();
            String username=parameterMap.get("username")[0];
            String password=DigestUtils.md5Hex(parameterMap.get("password")[0]);
           // System.out.println(username + " " + password);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/site","root","1234");

            try {
                System.out.println("все получилось");
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users (login, password) VALUES (?, ?); ");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();
                resp.sendRedirect(req.getContextPath() + "/index.html");

            }
            catch (Exception e)
            {
                resp.sendRedirect(req.getContextPath() + "/static/reg1.html");
            }
           con.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("не зашло??????????????????????????????????????????????????????????????????");
            e.printStackTrace();
        }
    }

}
