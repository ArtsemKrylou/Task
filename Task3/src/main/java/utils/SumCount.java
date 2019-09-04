package utils;

import models.Entrant;
import models.Subject;

public class SumCount {

    public static int marksSum(Entrant entrant){
        int sum = 0;
        sum+= entrant.getCertificate().getMark();
        sum+= entrant.getSubjects().stream().mapToInt(Subject::getMark).sum();
        return sum;
    }
}
