package aliikbal.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/unsafe")
public class UnsafeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Long sleep = Long.parseLong(req.getParameter("sleep"));
        String response = "Hello" + name + "Sleep" + sleep;
        try {
            Thread.sleep(Long.parseLong(req.getParameter("sleep")));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        resp.getWriter().println(response);
    }

}
