import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet()
public class file extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, String[]> names = new HashMap<>();
        List<String[]> res = new ArrayList<>();
        resp.setContentType("application/zip");
        try {
        List<String> List=fil.print(parameterMap);
        List<String> Listn=fil.printn(parameterMap);
        String[] arrn = Listn.toArray(new String[Listn.size()]);
        String[] arr = List.toArray(new String[List.size()]); //конвертируем в массив строк
        List<String> arrd = new ArrayList<>(Arrays.asList(arrn));
        for (String cur : arr) {
            String[] temp = fil.decodeClients(cur);
            res.add(temp);
        }
        String[] temp=fil.decodeClients(arr[0]);//пройдем один раз чтобы получиьт имя клиента
        String name=temp[0];
        Iterator<String> iter = arrd.listIterator();
        req.setAttribute("name", name);
        req.setAttribute("arr", iter);
        req.setAttribute("List", res);
        req.getRequestDispatcher("/WEB-INF/files.jsp").forward(req, resp);
    }

        catch (NullPointerException e)
        {
            resp.sendRedirect(req.getContextPath() + "/static/nofolder.html");
        }
    }
}

