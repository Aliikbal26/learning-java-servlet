package aliikbal.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Files;

@WebServlet(urlPatterns = "/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String file = req.getParameter("file");
        Path path = Path.of("upload/" + file);

        byte[] bytes = Files.readAllBytes(path);

        String force = req.getParameter("force");
        if ("true".equals(force)) {
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + path.getFileName() + "\"");
        }
        resp.getOutputStream().write(bytes);

    }
}
