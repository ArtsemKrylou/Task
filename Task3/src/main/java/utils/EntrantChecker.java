package utils;

import models.Entrant;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EntrantChecker {
    public static List<Entrant> selectTopEntrants(List<Entrant> entrants, int capacity){
        entrants.sort(Comparator.comparingInt(SumCount::marksSum));
        return entrants.stream().limit(capacity).collect(Collectors.toList());
    }
}
