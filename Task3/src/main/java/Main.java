import dao.SubjectDao;
import service.ExecutorService;

public class Main {
    public static void main(String[] args) {
        SubjectDao subjectDao = new SubjectDao(new ExecutorService());
        System.out.println(subjectDao.getAll());
    }
}
