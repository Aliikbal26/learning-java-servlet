package aliikbal.servlet;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(urlPatterns = "/form")
public class FormServlet extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            URL resourceUrl = FormServlet.class.getResource("/html/form.html");

            // Mengecek apakah resource ditemukan
            if (resourceUrl == null) {
                throw new ServletException("HTML resource not found");
            }
            // Menggunakan URI untuk mengatasi masalah karakter khusus
            URI uri = resourceUrl.toURI();
            Path path = Paths.get(uri);

            // Membaca isi file HTML
            String html = Files.readString(path);
            resp.getWriter().println(html);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String fullName = firstName + " " + lastName;
        String response = "Hello , " + fullName + "!";

        resp.getWriter().println(response);
    }
}
