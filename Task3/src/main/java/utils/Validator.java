package utils;

import exceptions.ValidationException;
import models.Subject;

public class Validator {
   public static boolean validateSubject(Subject subject){
    if (subject.getName() == null){
        throw new ValidationException("name not null");
    }
    if (subject.getName().length() > 0){
        throw new ValidationException("name should not be empty");
    }

    if (subject.getMark() < 1 || subject.getMark() > 10){
        throw new ValidationException("mark should be from 1 to 10. current mark:" + subject.getMark());
    }

    return true;
   }
}
