package com.javarush.khmelov.controller;
import com.javarush.khmelov.util.WebPaths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
@Slf4j
@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher(WebPaths.WP_INDEX).forward(req, resp);

        log.info("The home page is open: index.jsp");

    }
}