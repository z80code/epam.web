package by.gsu.epamlab.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.contants.Constants.*;


@WebServlet(MODERATOR_EDIT_URL)
public class ModeratorEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(MODERATOR_EDIT_HTML).forward(req, resp);
    }
}