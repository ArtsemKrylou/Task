package mappers;

import models.Certificate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateMapper implements Mapper<Certificate> {
    @Override
    public Certificate map(ResultSet resultSet) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(resultSet.getLong("id"));
        certificate.setMark(resultSet.getInt("mark"));
        return certificate;
    }
}
