package service;

import dao.CertificateDao;
import models.Certificate;
import static utils.Validator.validateCertificate;

import java.util.List;

public class CertificateService {

    private CertificateDao certificateDao = new CertificateDao(new ExecutorService());

    public List<Certificate> getCertificates(){
        return certificateDao.getAll();
    }

    public void updateCertificate(Certificate certificate){
        if (validateCertificate(certificate)){
            certificateDao.update(certificate);
        }
    }

    public void deleteCertificate(Long id){
        certificateDao.delete(id);
    }

    public void createCertificate(Certificate certificate){
        if (validateCertificate(certificate)){
            certificateDao.create(certificate);
        }
    }

    public Certificate getCertificateById(Long id){
        return certificateDao.getEntityById(id);
    }
}
