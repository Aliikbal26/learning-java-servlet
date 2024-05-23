package aliikbal.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet(urlPatterns = "/form-upload")
@MultipartConfig
public class FormUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            URL resourceUrl = FormServlet.class.getResource("/html/form-upload.html");

            // Mengecek apakah resource ditemukan
            if (resourceUrl == null) {
                throw new ServletException("HTML resource not found");
            }
            // Menggunakan URI untuk mengatasi masalah karakter khusus
            URI uri = resourceUrl.toURI();
            Path path = Paths.get(uri);
            String html = Files.readString(path);

            // Membaca isi file HTML
            resp.getWriter().println(html);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Part profile = req.getPart("profile");

        Path pathUploadLocation = Path.of("upload/" + UUID.randomUUID().toString() + profile.getSubmittedFileName());
        Files.copy(profile.getInputStream(), pathUploadLocation);

        String html = """
                <html>
                <body>
                    Name : $name
                    <br>
                    <img width="400px" height="400px" src="/download?file=$profile"/>
                </body>
                </html>
                """
                .replace("$name", name)
                .replace("$profile", pathUploadLocation.getFileName().toString());

        resp.getWriter().println(html);
    }

}
