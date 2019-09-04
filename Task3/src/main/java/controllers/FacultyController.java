package controllers;

import com.google.gson.Gson;
import models.Faculty;
import service.FacultyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/facult")
public class FacultyController extends HttpServlet {

    private FacultyService facultyService;

    @Override
    public void init(){
        facultyService = new FacultyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("faculty.html").forward(req, resp);
    }


}
