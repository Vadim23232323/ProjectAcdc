package com.javarush.khmelov.service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

public class ImageService {
    private ServletContext servletContext;

    public ImageService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void serveImage(String imageName, HttpServletResponse resp) throws IOException {
        String imagePath = "/WEB-INF/img/" + imageName;
        File imageFile = new File(servletContext.getRealPath(imagePath));
        if (imageFile.exists()) {
            resp.setContentType("image/jpeg");
            try (InputStream is = new FileInputStream(imageFile);
                 OutputStream os = resp.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Изображение не найдено: " + imageName);
        }
    }
}
