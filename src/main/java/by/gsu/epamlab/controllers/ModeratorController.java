package by.gsu.epamlab.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.contants.Constants.MODERATOR_INDEX_HTML;
import static by.gsu.epamlab.contants.Constants.MODERATOR_PAGE_URL;

@WebServlet(MODERATOR_PAGE_URL)
public class ModeratorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(MODERATOR_INDEX_HTML).forward(req, resp);
    }
}
