/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vrebo.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author VREBO
 */
public class AritmeticExpression {

    private static final short DEFAULT_EXPRESSION_SIZE;

    private Symbol[] expression;
    private short lastSymbol;
    private short index;
    private boolean dotInserted;

    static {
        DEFAULT_EXPRESSION_SIZE = 150;
    }

    public static enum Symbol {
        ZERO('0', Symbol.NUMBER),
        ONE('1', Symbol.NUMBER),
        TWO('2', Symbol.NUMBER),
        THREE('3', Symbol.NUMBER),
        FOUR('4', Symbol.NUMBER),
        FIVE('5', Symbol.NUMBER),
        SIX('6', Symbol.NUMBER),
        SEVEN('7', Symbol.NUMBER),
        EIGHT('8', Symbol.NUMBER),
        NINE('9', Symbol.NUMBER),
        DECIMAL_DOT('.', Symbol.DOT),
        ADDITION('+', Symbol.OPERATOR),
        SUBTRACTION('-', Symbol.OPERATOR),
        MULTIPLICATION('*', Symbol.OPERATOR),
        DIVISION('/', Symbol.OPERATOR);

        private final char symbol;
        private final short type;

        public static final short NUMBER = 1;
        public static final short DOT = 2;
        public static final short OPERATOR = 3;

        Symbol(char c, short symbolType) {
            this.symbol = c;
            this.type = symbolType;
        }

        public char getSymbol() {
            return symbol;
        }

        public short getType() {
            return type;
        }
    }

    public AritmeticExpression() {
        expression = new Symbol[DEFAULT_EXPRESSION_SIZE];
    }

    public void clearExpression() {
        index = 0;
    }

    public AritmeticExpression insertSymbol(Symbol symbol) {
        switch (symbol.getType()) {
            case Symbol.NUMBER:
                lastSymbol = Symbol.NUMBER;
                break;
            case Symbol.OPERATOR:
                if (lastSymbol == Symbol.DOT) {
                    insert(Symbol.ZERO);
                } else if (lastSymbol == Symbol.OPERATOR) {
                    index--;
                } else if (isClear()) {
                    return this;
                }
                dotInserted = false;
                lastSymbol = Symbol.OPERATOR;
                break;
            case Symbol.DOT:
                if (!dotInserted) {
                    if (lastSymbol == Symbol.OPERATOR || isClear()) {
                        insert(Symbol.ZERO);
                    }
                    lastSymbol = Symbol.DOT;
                    dotInserted = true;
                } else {
                    return this;
                }
                break;
        }
        insert(symbol);
        return this;
    }

    public AritmeticExpression removeLastSymbol() {
        if (index > 0) {
            if (lastSymbol == Symbol.DOT) {
                dotInserted = false;
            }
            index--;
            lastSymbol = expression[index].getType();
        }
        return this;
    }

    public boolean isClear() {
        return (index == 0);
    }

    private void insert(Symbol symbol) {
        expression[index] = symbol;
        index++;
    }

    public String evaluate() {

        List<Symbol> string = new LinkedList<>();
        Stack<Double> numbers = new Stack<>();
        Stack<Symbol> ops = new Stack<>();

        for (int i = 0; i < index; i++) {
            switch (expression[i]) {
                case ONE:
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                case ZERO:
                case DECIMAL_DOT:
                    string.add(expression[i]);
                    break;
                case ADDITION:
                case SUBTRACTION:
                case MULTIPLICATION:
                case DIVISION:
                    if (!string.isEmpty()) {
                        numbers.push(stringToNumber(string));
                        string.clear();
                    }

                    if (ops.isEmpty()) {
                        ops.push(expression[i]);
                    } else {
                        Symbol lastOp = ops.peek();
                        if (getOpPriority(lastOp) > getOpPriority(expression[i])) {
                            double a, b;
                            a = numbers.pop();
                            b = numbers.pop();
                            try {
                                numbers.push(operate(lastOp, a, b));
                            } catch (Exception e) {
                            }
                            ops.pop();
                            ops.push(expression[i]);
                        } else {
                            ops.push(expression[i]);
                        }
                    }
                    break;
            }
        }
        if (!string.isEmpty()) {
            numbers.push(stringToNumber(string));
        }
        while (!ops.isEmpty()) {
            double a, b;
            a = numbers.pop();
            b = numbers.pop();
            try {
                numbers.push(operate(ops.pop(), b, a));
            } catch (Exception e) {
            }
        }
        return Double.toString(numbers.pop());
    }

    private int getOpPriority(Symbol s) {
        switch (s) {
            case ADDITION:
            case SUBTRACTION:
                return 1;
            case MULTIPLICATION:
            case DIVISION:
                return 2;
        }
        return 0;
    }

    private double operate(Symbol s, double a, double b) throws Exception {
        switch (s) {
            case ADDITION:
                return a + b;
            case SUBTRACTION:
                return a - b;
            case MULTIPLICATION:
                return a * b;
            case DIVISION:
                return a / b;
        }
        throw new Exception();
    }

    private double stringToNumber(List<Symbol> string) {
        StringBuilder sb = new StringBuilder();
        for (Symbol symbol : string) {
            sb.append(symbol.getSymbol());
        }
        return Double.parseDouble(sb.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < index; i++) {
            if (expression[i] == Symbol.ADDITION
                    || expression[i] == Symbol.SUBTRACTION
                    || expression[i] == Symbol.MULTIPLICATION
                    || expression[i] == Symbol.DIVISION) {
                sb.append(' ');
                sb.append(expression[i].getSymbol());
                sb.append(' ');
            } else {
                sb.append(expression[i].getSymbol());
            }
        }
        return sb.toString();
    }

}
