package mappers;

import models.Certificate;
import models.Entrant;
import models.Subject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class EntrantMapper implements Mapper<Entrant> {

    private static boolean isExist(ResultSet resultSet, String columnName) throws SQLException {
        if (columnName == null || (columnName = columnName.trim()).isEmpty())
            return false;

        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++)
            if (columnName.equals(metaData.getColumnName(i)))
                return true;

        return false;
    }

    @Override
    public Entrant map(ResultSet resultSet) throws SQLException {
        Entrant entrant = new Entrant();
        entrant.setId(resultSet.getLong("id"));
        entrant.setName(resultSet.getString("name"));

            if (isExist(resultSet, "mark") && isExist(resultSet, "certificate_id")) {
                int certificateMark = resultSet.getInt("mark");
                long certificateId = resultSet.getLong("certificate_id");
                entrant.setCertificate(new Certificate(certificateMark, certificateId));

            }
            if (isExist(resultSet,"subject_mark") && isExist(resultSet, "subject_name") && isExist(resultSet, "subject_id")) {
                String marksString = resultSet.getString("subject_mark");
                String namesString = resultSet.getString("subject_name");
                String idString = resultSet.getString("subject_id");
                List<Subject> subjects = createSubject(marksString, namesString, idString);
                entrant.setSubjects(subjects);
            }



        return entrant;
    }

    private List<Subject> createSubject(String marksString, String namesString, String idString){
        String[] marks = marksString.split(",");
        String[] names = namesString.split(",");
        String[] ids = idString.split(",");
        if (marks.length == names.length){
           return Stream.iterate(0, integer -> integer + 1)
                    .limit(marks.length)
                    .map(integer -> {
                       String name = names[integer];
                       int mark = Integer.parseInt(marks[integer]);
                       long id = Long.parseLong(ids[integer]);
                       return new Subject(name, mark, id);
                    })
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("number of marks not equal to the number of subjects");


    }

}
