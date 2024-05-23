package aliikbal.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/body")
public class BodyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = """
                    <html>
                    <head><title>Body Servlet</title></head>
                    <body>
                    <h1>Hello World!</h1>
                    </body>
                    </html>
                """;
        resp.getWriter().println(html);
    }
}
