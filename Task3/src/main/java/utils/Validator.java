package utils;

import exceptions.ValidationException;
import models.Certificate;
import models.Entrant;
import models.Subject;
import models.User;

public class Validator {
    private static void stringNameValidator(String stringForValidation) {
            if (stringForValidation == null){
                throw new ValidationException("name not null");
            }
            if (stringForValidation.trim().length() == 0){
                throw new ValidationException("name should not be empty");
            }

    }
   public static boolean validateSubject(Subject subject){
    stringNameValidator(subject.getName());

    if (subject.getMark() < 1 || subject.getMark() > 100){
        throw new ValidationException("mark should be from 1 to 100. current mark:" + subject.getMark());
    }

    return true;
   }

   public static boolean validateCertificate(Certificate certificate){
       if (certificate.getMark() < 1 || certificate.getMark() > 10){
           throw new ValidationException("mark should be from 1 to 10. current mark:" + certificate.getMark());
       }
       return true;
   }

    public static boolean validateEntrant(Entrant entrant) {
       stringNameValidator(entrant.getName());
       return true;
    }

    public static boolean validateUser(User user){
        stringNameValidator(user.getUserName());
        if (user.getPassword().length() < 6){
            throw new ValidationException("password should be more than 6 symbols");
        }
        return true;
    }

}
