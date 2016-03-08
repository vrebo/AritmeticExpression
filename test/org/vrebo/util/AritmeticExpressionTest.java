/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vrebo.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.vrebo.util.AritmeticExpression.Symbol;

/**
 *
 * @author VREBO
 */
public class AritmeticExpressionTest {

    public AritmeticExpressionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of clearExpression method, of class AritmeticExpression.
     */
    @Test
    public void testClearExpression() {
        System.out.println("clearExpression");
        AritmeticExpression instance = new AritmeticExpression();
        instance.clearExpression();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of insertNumber method, of class AritmeticExpression.
     */
    @Test
    public void testInsertNumberOne() {
        System.out.println("insertNumber inserted 1");
        Symbol one = AritmeticExpression.Symbol.ONE;
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(one);
        assertTrue("1".equals(instance.toString()));
    }

    @Test
    public void testInsertNumberDiferentResult() {
        System.out.println("insertNumber inserted 1 expected 2");
        Symbol zero = AritmeticExpression.Symbol.ZERO;
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(zero);
        assertFalse("2".equals(instance.toString()));
    }

    @Test
    public void testInsertNumberOfTwoDigits() {
        System.out.println("insertSymbol inserted 11");

        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.TWO);
        assertTrue("12".equals(instance.toString()));
    }

    @Test
    public void testInsertDot() {
        System.out.println("insertSymbol: Case just one dot");

        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.DECIMAL_DOT);
        System.out.println(instance.toString());
        assertTrue("0.".equals(instance.toString()));
    }

    @Test
    public void testInsertDecimalNumber() {
        System.out.println("insertSymbol: Case decimal number");

        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.FOUR)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.EIGHT);
        System.out.println(instance.toString());
        assertTrue("14.8".equals(instance.toString()));
    }

    @Test
    public void testInsertDoubleDot() {
        System.out.println("insertSymbol: Case decimal number");

        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.FOUR)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.EIGHT)
                .insertSymbol(Symbol.DECIMAL_DOT);
        System.out.println(instance.toString());
        assertTrue("14.8".equals(instance.toString()));
    }

    @Test
    public void testInsertDecimalNumberAndOp() {
        System.out.println("insertSymbol: Case decimal number and operator");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.SIX)
                .insertSymbol(Symbol.FIVE)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.ADDITION);
        System.out.println(instance.toString());
        assertTrue("65.2 + ".equals(instance.toString()));
    }

    @Test
    public void testInsertDecimalOperatorAndDot() {
        System.out.println("insertSymbol: Case decimal number and operator");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.SIX)
                .insertSymbol(Symbol.FIVE)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.DECIMAL_DOT);
        System.out.println(instance.toString());
        assertTrue("65.2 + 0.".equals(instance.toString()));
    }

    @Test
    public void testInsertThreeNumbersExpression() {
        System.out.println("insertSymbol: Case decimal number and operator");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.SIX)
                .insertSymbol(Symbol.FIVE)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.SUBTRACTION);

        System.out.println(instance.toString());
        assertTrue("65.2 + 0.0 - ".equals(instance.toString()));
    }

    @Test
    public void testInsertJustOperator() {
        System.out.println("insertSymbol: Case Just one operator");

        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ADDITION);
        System.out.println("Result: " + instance.toString());
        assertTrue(instance.toString().isEmpty());
    }

    @Test
    public void testInsertNumberAndOperator() {
        System.out.println("insertSymbol: Case one number and one operator");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.ADDITION);
        System.out.println("Result: " + instance.toString());
        assertTrue("1 + ".equals(instance.toString()));
    }

    @Test
    public void testInsertNumbersAndOperator() {
        System.out.println("insertSymbol: Case Number Operator Number");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.ONE);
        System.out.println("Result: " + instance.toString());
        assertTrue("1 + 1".equals(instance.toString()));
    }

    @Test
    public void testInsertNumberOfManyDigits() {
        System.out.println("insertSymbol: Number of many digits");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.THREE)
                .insertSymbol(Symbol.FOUR)
                .insertSymbol(Symbol.FIVE)
                .insertSymbol(Symbol.SIX)
                .insertSymbol(Symbol.SEVEN)
                .insertSymbol(Symbol.EIGHT)
                .insertSymbol(Symbol.NINE)
                .insertSymbol(Symbol.ZERO);
        assertTrue("1234567890".equals(instance.toString()));
    }

    @Test
    public void testReplaceOperator() {
        System.out.println("insertSymbol: Number of many digits");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.SIX)
                .insertSymbol(Symbol.FIVE)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.SUBTRACTION)
                .insertSymbol(Symbol.DECIMAL_DOT);
        assertTrue("65.2 - 0.".equals(instance.toString()));
    }

    @Test
    public void testRemoveSymbol() {
        System.out.println("removeLastSymbol: Remove one digit of two");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.SIX)
                .insertSymbol(Symbol.FIVE)
                .removeLastSymbol();
        assertTrue("6".equals(instance.toString()));
    }

    @Test
    public void testRemoveSymbol2() {
        System.out.println("removeLastSymbol: Remove one digit of two and then add another");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.SIX)
                .insertSymbol(Symbol.FIVE)
                .removeLastSymbol()
                .insertSymbol(Symbol.ZERO);
        assertTrue("60".equals(instance.toString()));
    }

    @Test
    public void testRemoveSymbol3() {
        System.out.println("removeLastSymbol: Add a dot and then remove it");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.DECIMAL_DOT)
                .removeLastSymbol();
        assertTrue("0".equals(instance.toString()));
    }

    @Test
    public void testRemoveSymbol4() {
        System.out.println("removeLastSymbol: Add a dot and then remove it");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.DECIMAL_DOT)
                .removeLastSymbol()
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.TWO);
        assertTrue("0.2".equals(instance.toString()));
    }

    @Test
    public void testRemoveSymbol5() {
        System.out.println("removeLastSymbol: Add a dot and then remove it");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.MULTIPLICATION)
                .removeLastSymbol();
        assertTrue("0.2".equals(instance.toString()));
    }

    @Test
    public void testRemoveSymbol6() {
        System.out.println("removeLastSymbol: Add a dot and then remove it");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.MULTIPLICATION)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.THREE)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.DIVISION)
                .insertSymbol(Symbol.FIVE)
                .insertSymbol(Symbol.SIX)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.DIVISION)
                .removeLastSymbol()
                .removeLastSymbol()
                .removeLastSymbol()
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.SEVEN);
        System.out.println("Result: " + instance.toString());
        assertTrue("0.1 * 23.0 / 56.7".equals(instance.toString()));
    }

    @Test
    public void testEvaluate1() {
        System.out.println("evaluate: Simple addition");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.ONE);
        System.out.println("Result: " + instance.evaluate());
        assertTrue("2.0".equals(instance.evaluate()));
    }

    @Test
    public void testEvaluate2() {
        System.out.println("evaluate: Simple addition");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.ONE);
        System.out.println("Result: " + instance.evaluate());
        assertTrue("2.2".equals(instance.evaluate()));
    }

    @Test
    public void testEvaluate3() {
        System.out.println("evaluate: Simple subtraction");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.SUBTRACTION)
                .insertSymbol(Symbol.ONE);
        String result = instance.evaluate();
        Double x = Double.parseDouble(result);
        System.out.println("Result: " + result);
        assertTrue(x <= 0.2 && x > 0.195);
    }

    @Test
    public void testEvaluate4() {
        System.out.println("evaluate: Simple multiplication");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.ONE)
                .insertSymbol(Symbol.DECIMAL_DOT)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.MULTIPLICATION)
                .insertSymbol(Symbol.THREE);
        String result = instance.evaluate();
        Double x = Double.parseDouble(result);
        System.out.println("Result: " + result);
        assertTrue(x <= 3.6 && x > 3.59999999);
    }

    @Test
    public void testEvaluate5() {
        System.out.println("evaluate: Simple division");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.NINE)
                .insertSymbol(Symbol.DIVISION)
                .insertSymbol(Symbol.THREE);
        String result = instance.evaluate();
        Double x = Double.parseDouble(result);
        System.out.println("Result: " + result);
        assertTrue(x == 3);
    }

    @Test
    public void testEvaluate6(){
        System.out.println("evaluate: Complex expression, addition and substraction");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.EIGHT)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.SUBTRACTION)
                .insertSymbol(Symbol.FOUR);
        String result = instance.evaluate();
        Double x = Double.parseDouble(result);
        System.out.println("Result: "+ result);
        assertTrue(x == 6);
    }
    
    @Test
    public void testEvaluate7() {
        System.out.println("evaluate: Complex expression, addition and multiplication");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.EIGHT)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.MULTIPLICATION)
                .insertSymbol(Symbol.FOUR);
        String result = instance.evaluate();
        Double x = Double.parseDouble(result);
        System.out.println("Result: " + result);
        assertTrue(x == 16);
    }
    
    @Test
    public void testEvaluate8() {
        System.out.println("evaluate: Complex expression, addition and multiplication");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.EIGHT)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.MULTIPLICATION)
                .insertSymbol(Symbol.FOUR);
        String result = instance.evaluate();
        Double x = Double.parseDouble(result);
        System.out.println("Result: " + result);
        assertTrue(x == 16);
    }
    
    @Test
    public void testEvaluate9() {
        System.out.println("evaluate: Complex expression, multiplication and addition");
        AritmeticExpression instance = new AritmeticExpression();
        instance.insertSymbol(Symbol.EIGHT)
                .insertSymbol(Symbol.MULTIPLICATION)
                .insertSymbol(Symbol.TWO)
                .insertSymbol(Symbol.ADDITION)
                .insertSymbol(Symbol.FOUR);
        String result = instance.evaluate();
        Double x = Double.parseDouble(result);
        System.out.println("Result: " + result);
        assertTrue(x == 20);
    }

    /**
     * Test of toString method, of class AritmeticExpression.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AritmeticExpression instance = new AritmeticExpression();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
