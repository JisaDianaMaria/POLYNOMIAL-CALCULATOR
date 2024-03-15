package org.example.BussinesLogic;

import org.example.DataModels.Polynomial;

public class Operations {

    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial("");
        p1.getPolynom().forEach((power, coef) -> {
            double sum = coef + p2.getPolynom().getOrDefault(power, 0.0);
            result.getPolynom().put(power, sum);
        });
        p2.getPolynom().forEach((power, coef) -> {
            if (!result.getPolynom().containsKey(power)) {
                result.getPolynom().put(power, coef);
            }
        });
        return result;
    }

    public static Polynomial sub(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial("");
        p1.getPolynom().forEach((power, coef) -> {
            double dif = coef - p2.getPolynom().getOrDefault(power, 0.0);
            result.getPolynom().put(power, dif);
        });
        p2.getPolynom().forEach((power, coef) -> {
            if (!result.getPolynom().containsKey(power)) {
                double diff = -coef;
                result.getPolynom().put(power, diff);
            }
        });
        return result;
    }

    public static Polynomial mul(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial("");
        p1.getPolynom().keySet().forEach((power1) -> {
            p2.getPolynom().keySet().forEach((power2) -> {
                int newPower = power1 + power2;
                double newCoef = p1.getPolynom().get(power1) * p2.getPolynom().get(power2);
                double oldCoef = result.getPolynom().getOrDefault(newPower, 0.0);
                result.getPolynom().put(newPower, oldCoef + newCoef);
            });
        });

        return result;
    }


    public static Polynomial[] div(Polynomial p1, Polynomial p2) {
        if (p2.isZero()) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }

        Polynomial quotient = new Polynomial("");
        Polynomial remainder = p1;
        while (!remainder.getPolynom().isEmpty() && remainder.getDegree() >= p2.getDegree()) {
            int PowerDif = remainder.getDegree() - p2.getDegree();
            double CoefRatio = remainder.getCoefficient() / p2.getCoefficient();
            Polynomial term = new Polynomial("");
            term.getPolynom().put(PowerDif, CoefRatio);
            quotient = Operations.add(quotient, term);
            Polynomial product = Operations.mul(p2, term);
            remainder = Operations.sub(remainder, product);
            if(remainder.getCoefficient()==0)
                break;
        }
        return new Polynomial[]{quotient, remainder};
    }


    public static Polynomial derivative(Polynomial p) {
        Polynomial result = new Polynomial("");
        p.getPolynom().keySet().forEach((power) -> {
            if (power == 0) {
                return;
            }
            double coef = power * p.getPolynom().get(power);
            result.getPolynom().put(power - 1, coef);
        });
        return result;
    }


    public static Polynomial integrate(Polynomial p) {
        Polynomial result = new Polynomial("");
        p.getPolynom().keySet().forEach((power) -> {
            double coef = p.getPolynom().get(power) / (power + 1);
            result.getPolynom().put(power + 1, coef);
        });
        result.getPolynom().put(0, 0.0);
        return result;
    }





}
