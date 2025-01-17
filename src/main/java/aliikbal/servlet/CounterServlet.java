package aliikbal.servlet;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {

    private final AtomicLong atomicLong = new AtomicLong();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long total = atomicLong.incrementAndGet();
        String response = "TOTAL COUNTER" + total;
        resp.getWriter().println(response);
    }
}
