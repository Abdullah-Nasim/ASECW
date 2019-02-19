package models;

import java.util.HashMap;
import java.util.TreeMap;

public class Report {

    private static Report report;

    public static Report getInstance(){
        if(report == null)
            report = new Report();
        return report;
    }

    public TreeMap<String, Integer> reportHashMap = new TreeMap<>();

    public Double totalIncome = 0.0;
}
