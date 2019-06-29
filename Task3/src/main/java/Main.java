import dao.SubjectDao;
import models.Certificate;
import models.Entrant;
import models.Subject;
import service.EntrantService;
import service.ExecutorService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntrantService entrantService = new EntrantService();
        System.out.println(entrantService.getEntrants());
        Entrant entrant = new Entrant();
        entrant.setName("rty");
        Certificate certificate = new Certificate(6);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("aaa", 7));
        subjects.add(new Subject("aaa", 5));
        subjects.add(new Subject("aaa", 9));
        entrant.setCertificate(certificate);
        entrant.setSubjects(subjects);
        //entrantService.createEntrant(entrant);
       //entrantService.deleteEntrant((long) 16);
       // entrantService.getEntrantById((long) 17);
        entrantService.updateEntrant(entrant);
        System.out.println(entrantService.getEntrants());


    }
}
