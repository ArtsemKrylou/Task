package controllers;

import models.Statement;
import service.FacultyService;
import service.StatementService;
import utils.EntrantChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/statement")
public class StatementController extends HttpServlet {

    private StatementService statementService;
    private FacultyService facultyService;

    public void init(){
        statementService = new StatementService();
        facultyService = new FacultyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        statementService.deleteStatement();
        facultyService.getFaculty()
                .stream()
                .map(faculty -> EntrantChecker.selectTopEntrants(faculty.getEntrants(),2))
                .forEach(entrants -> statementService.createStatement(new Statement(entrants)));
        req.getRequestDispatcher("faculty.html").forward(req, resp);

    }
}
