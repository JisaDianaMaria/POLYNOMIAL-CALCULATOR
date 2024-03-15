package org.example;

import org.example.BussinesLogic.Operations;
import org.example.DataModels.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolynomialTest {

    @Test
    void testAdd() {
        Polynomial p1 = new Polynomial("-x^3+2x^2-5x+4");
        Polynomial p2 = new Polynomial("-4x^2-5");
        Polynomial expected = new Polynomial("-x^3-2.0x^2-5.0x-1.0");
        assertEquals(expected.toString(), Operations.add(p1, p2).toString());
    }

    @Test
    void testAddg() {
        Polynomial p1 = new Polynomial("-x^3+2x^2-5x+4");
        Polynomial p2 = new Polynomial("-4x^2-5");
        Polynomial wrong = new Polynomial("-x^3-2.0x^2-5.0x+1.0");
        assertEquals(wrong.toString(), Operations.add(p1, p2).toString());
    }


    @Test
    void testSub() {
        Polynomial p1 = new Polynomial("-x^3+2x^2-5x+4");
        Polynomial p2 = new Polynomial("-4x^2-5");
        Polynomial expected = new Polynomial("-x^3+6.0x^2-5.0x+9.0");
        assertEquals(expected.toString(), Operations.sub(p1, p2).toString());
    }

    @Test
    void testSubg() {
        Polynomial p1 = new Polynomial("-x^3+2x^2-5x+4");
        Polynomial p2 = new Polynomial("-4x^2-5");
        Polynomial wrong = new Polynomial("x^3+6.0x^2-5.0x");
        assertEquals(wrong.toString(), Operations.sub(p1, p2).toString());
    }

    @Test
    void testMul() {
        Polynomial p1 = new Polynomial("-x^3+2x^2-5x+4");
        Polynomial p2 = new Polynomial("-4x^2-5");
        Polynomial expected = new Polynomial("4.0x^5-8.0x^4+25.0x^3-26.0x^2+25.0x-20.0");
        assertEquals(expected.toString(), Operations.mul(p1, p2).toString());
    }

    @Test
    void testMulg() {
        Polynomial p1 = new Polynomial("-x^3+2x^2-5x+4");
        Polynomial p2 = new Polynomial("-4x^2-5");
        Polynomial wrong = new Polynomial("4.0x^5-8.0x^4+25.0x^3+25.0x+20.0");
        assertEquals(wrong.toString(), Operations.mul(p1, p2).toString());
    }

    @Test
    public void testDiv() {
        Polynomial p1 = new Polynomial("x^3-2x^2+6x-5");
        Polynomial p2 = new Polynomial("x^2-1");

        Polynomial[] result = Operations.div(p1, p2);
        Polynomial quotient = result[0];
        Polynomial remainder = result[1];

        Polynomial expectedQuotient = new Polynomial("x-2");
        Polynomial expectedRemainder = new Polynomial("7.0x-7.0");

        assertEquals(expectedQuotient.toString(), quotient.toString());
        assertEquals(expectedRemainder.toString(), remainder.toString());
    }

    @Test
    public void testDivg() {
        Polynomial p1 = new Polynomial("x^3-2x^2+6x-5");
        Polynomial p2 = new Polynomial("x^2-1");

        Polynomial[] result = Operations.div(p1, p2);
        Polynomial quotient = result[0];
        Polynomial remainder = result[1];

        Polynomial wrongQuotient = new Polynomial("x-2");
        Polynomial wrongRemainder = new Polynomial("7.0x+7.0");

        assertEquals(wrongQuotient.toString(), quotient.toString());
        assertEquals(wrongRemainder.toString(), remainder.toString());
    }


    @Test
    void testDerivative() {
        Polynomial p = new Polynomial("2x^3+3x^2+5x+7");
        Polynomial expected = new Polynomial("6.0x^2+6.0x+5.0");
        assertEquals(expected.toString(), Operations.derivative(p).toString());
    }

    @Test
    void testDerivativeg() {
        Polynomial p = new Polynomial("2x^3+3x^2+5x+7");
        Polynomial wrong = new Polynomial("6.0x^3+6.0x+5.0");
        assertEquals(wrong.toString(), Operations.derivative(p).toString());
    }

    @Test
    void testIntegrate() {
        Polynomial p = new Polynomial("2x^3+3x^2+5x+7");
        Polynomial expected = new Polynomial("0.5x^4+x^3+2.5x^2+7.0x");
        assertEquals(expected.toString(), Operations.integrate(p).toString());
    }

    @Test
    void testIntegrateg() {
        Polynomial p = new Polynomial("2x^3+3x^2+5x+7");
        Polynomial wrong = new Polynomial("0.55x^4+x^3+2.5x^2+7.0x");
        assertEquals(wrong.toString(), Operations.integrate(p).toString());
    }

}
