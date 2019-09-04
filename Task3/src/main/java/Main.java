import dao.SubjectDao;
import models.Certificate;
import models.Entrant;
import models.Statement;
import models.Subject;
import service.*;
import utils.EntrantChecker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

//    public static int marksSum(Entrant entrant){
//        int sum = 0;
//        sum+= entrant.getCertificate().getMark();
//        sum+= entrant.getSubjects().stream().mapToInt(Subject::getMark).sum();
//        return sum;
//    }

    public static void main(String[] args) {
//        EntrantService entrantService = new EntrantService();
//        System.out.println(entrantService.getEntrants());
//        Entrant entrant = new Entrant();
//        entrant.setName("rty");
//        Certificate certificate = new Certificate(6);
//        List<Subject> subjects = new ArrayList<>();
//        subjects.add(new Subject("aaa", 7));
//        subjects.add(new Subject("aaa", 5));
//        subjects.add(new Subject("aaa", 9));
//        entrant.setCertificate(certificate);
//        entrant.setSubjects(subjects);
//        //entrantService.createEntrant(entrant);
//       //entrantService.deleteEntrant((long) 16);
//       // entrantService.getEntrantById((long) 17);
//        entrantService.updateEntrant(entrant);
//        System.out.println(entrantService.getEntrants());
        FacultyService facultyService = new FacultyService();
        //System.out.println(facultyService.getFacultyByName("fac1"));
//        System.out.println(facultyService.getFaculty());
//
//        EntrantService entrantService = new EntrantService();
//        List<Entrant> entrants = entrantService.getEntrants();
//        System.out.println(entrants);
//        entrants.sort(Comparator.comparingInt(Main::marksSum));
//        System.out.println(entrants);

        StatementService statementService = new StatementService();
//
//        System.out.println(statementService.getStatementById(35L));
//        System.out.println(statementService.getStatement());
//        statementService.deleteStatement();

//        statementService.deleteStatement();
//        facultyService.getFaculty()
//                .stream()
//                .map(faculty -> EntrantChecker.selectTopEntrants(faculty.getEntrants(),2))
//                .forEach(entrants -> statementService.createStatement(new Statement(entrants)));
//
//
//
        AdministratorService administratorService = new AdministratorService();
        System.out.println(administratorService.isAdmin(24L));
    }
}


