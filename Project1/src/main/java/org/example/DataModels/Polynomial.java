package org.example.DataModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    private HashMap<Integer, Double> polynom;

    public Polynomial(String polynomial) {
        polynom = new HashMap<>();
        Pattern termPattern = Pattern.compile("[+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*");
        Matcher termMatcher = termPattern.matcher(polynomial);

        while (termMatcher.find()) {
            String term = termMatcher.group();
            if (term.isEmpty()) continue;
            if (term.equals("x")) polynom.put(1, 1.0);
            else if (term.contains("x^")) {
                String[] parts = term.split("x\\^");
                double coef = 1;
                int power = Integer.parseInt(parts[1]);
                if (parts.length > 0 && !parts[0].isEmpty()) {
                    if(parts[0].equals("-"))  coef=-1;
                    else if(parts[0].equals("+")) coef=1;
                    else coef=Double.parseDouble(parts[0]);
                }
                polynom.put(power, coef);
            } else if (term.contains("x")) {
                double coef;
                int power = 1;
                if (term.startsWith("-x")) coef = -1;
                else if (term.startsWith("+x")) coef = 1;
                else  coef = Double.parseDouble(term.replace("x", ""));
                polynom.put(power, coef);
            } else {
                double coef = Double.parseDouble(term);
                polynom.put(0, coef);
            }
        }
    }


    public String toString() {
        StringBuilder rez = new StringBuilder();
        List<Integer> powers = new ArrayList<>(polynom.keySet());
        Collections.sort(powers, Collections.reverseOrder());
        AtomicBoolean firstTerm = new AtomicBoolean(true);

        powers.forEach((power) -> {
            double coef = polynom.get(power);
            if (coef != 0)
            {  if (firstTerm.get()) {
                    if (coef < 0)
                    {   rez.append("-");
                        coef = -coef;  }
                    firstTerm.set(false); }
                else { rez.append(coef < 0 ? " - " : " + ");
                       coef = Math.abs(coef); }
                if (coef == 1) {
                    if (power == 0)
                    {   coef = Math.abs(coef);
                        rez.append(coef);  }
                    else if (power == 1) rez.append("x");
                    else   rez.append("x^").append(power); }
                else {
                    if (power == 0) rez.append(coef);
                    else if (power == 1) rez.append(coef).append("x");
                    else  rez.append(coef).append("x^").append(power);   }
            }
        });
        if (rez.length() == 0) rez.append("0");
        return rez.toString();
    }

    public HashMap<Integer, Double> getPolynom() {
        return polynom;
    }

    public int getDegree() {
        List<Integer> powers = new ArrayList<>(polynom.keySet());
        Collections.sort(powers, Collections.reverseOrder());
        for (int power : powers) {
            if (polynom.get(power) != 0) {
                return power;
            }
        }
        return 0;
    }

    public double getCoefficient() {
        int degree = getDegree();
        return polynom.getOrDefault(degree,0.0);
    }

    public boolean isZero() {
        for (double coef : polynom.values()) {
            if (coef != 0) {
                return false;
            }
        }
        return true;
    }
}
