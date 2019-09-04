package service;
import static utils.Validator.validateEntrant;

import dao.CertificateDao;
import dao.EntrantDao;
import models.Certificate;
import models.Entrant;

import java.util.List;

public class EntrantService {
    private CertificateService certificateService = new CertificateService();
    private SubjectService subjectService = new SubjectService();
    private EntrantDao entrantDao = new EntrantDao(new ExecutorService());

    public List<Entrant> getEntrants(){return entrantDao.getAll();}

    public void updateEntrant(Entrant entrant){
        if (validateEntrant(entrant)){
            entrantDao.update(entrant);
        }
    }

    public void deleteEntrant(Long id){entrantDao.delete(id);}

    public Entrant createEntrant(Entrant entrant){
         Entrant newEntrant = null;
        if (validateEntrant(entrant)){
            newEntrant = entrantDao.create(entrant);
            Certificate certificate = entrant.getCertificate();
            certificate.setId(newEntrant.getId());
            certificateService.createCertificate(certificate);
            Entrant finalNewEntrant = newEntrant;
            entrant.getSubjects().forEach(subject -> {
                subject.setId(finalNewEntrant.getId());
                subjectService.createSubject(subject);
            });
        }
        return newEntrant;
    }

    public Entrant getEntrantById(Long id){
        return entrantDao.getEntityById(id);
    }
}
