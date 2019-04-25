import dao.SubjectDao;

public class Main {
    public static void main(String[] args) {
        SubjectDao subjectDao = new SubjectDao();
       // subjectDao.create("insert into subject(name, mark) VALUES ('new', 5)");
        //subjectDao.select("SELECT * from subject");
        //subjectDao.delete("delete from subject where mark = ?", 5);
        //subjectDao.update("update subject set name = 'new2' where mark = ?", 5);
        System.out.println(subjectDao.select("SELECT * from subject"));
    }
}
