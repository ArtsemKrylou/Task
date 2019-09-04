package controllers;

import com.google.gson.Gson;
import models.Faculty;
import models.Statement;
import models.Subject;
import models.User;
import service.FacultyService;
import service.StatementService;
import service.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@WebServlet("/updateInfo")
public class InfoController extends HttpServlet {

    private SubjectService subjectService;
    private StatementService statementService;
    //private BufferedReader br;

    @Override
    public void init() {
        subjectService = new SubjectService();
        statementService = new StatementService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        List<Subject> subjects = subjectService.getSubjects();
//        Gson gson = new Gson();
//        gson.toJson(subjects);
//        PrintWriter out = resp.getWriter();
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        out.print(gson.toJson(subjects));
//        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> selectors = readSelector(req);
        String selector = selectors.getOrDefault("selector", "");
        switch (selector){
            case "user":
                User user = getUserInfo(req);
                writeInfo(resp, user);
                break;
            case "faculty":
                List<Faculty> faculty = getFacultyInfo();
                writeInfo(resp, faculty);
                break;
            case "status":
                long entrant_id = Long.parseLong(selectors.get("entrant_id"));
                Map<String, Boolean> status = Collections.singletonMap("status", checkStatus(entrant_id));
                writeInfo(resp, status);
                break;
        }
    }

    private boolean checkStatus(Long id){
        Statement statement = statementService.getStatementById(id);
        return !statement.getEntrants().isEmpty();
    }

    private User getUserInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }

    private List<Faculty> getFacultyInfo(){
        FacultyService facultyService = new FacultyService();
        return facultyService.getFaculty();
    }

    private void writeInfo(HttpServletResponse response, Object object) throws IOException {
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(gson.toJson(object));
        out.flush();
    }

    private  Map<String, String> readSelector(HttpServletRequest request) throws IOException {

           BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        if(br != null){
            json = br.readLine();
            return new Gson().fromJson(json, Map.class);
        }
        return null;
    }
}
