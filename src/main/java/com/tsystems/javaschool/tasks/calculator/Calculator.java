package com.tsystems.javaschool.tasks.calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */


    public String evaluate(String statement) {
        if (statement == null || statement == "" || statement == " " || statement.contains(",")) return null;
        if (!isValid(statement)) return null;
        if (!checkBracket(statement)) return null;
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(Double.parseDouble(RPNToAnswer(toRPN(statement))));


    }

    public int getPriority(char c){
        if (c == '(') return 1;
        else if (c == ')') return -1;
        else if (c == '*' || c == '/') return 3;
        else if (c == '-' || c == '+') return 2;
        else return 0;

    }

    public String toRPN(String expression){
        Stack<Character> stack = new Stack<>();
        String current = "";
        int priority;
        for (int i = 0; i < expression.length(); i++){
            priority = getPriority(expression.charAt(i));
            if (priority == 0) current += expression.charAt(i);
            if (priority == 1) stack.push(expression.charAt(i));
            if (priority > 1){
                current += ' ';
                while(!stack.empty()){
                    if (getPriority(stack.peek()) >= priority) current += stack.pop();
                    else break;
                }
                stack.push(expression.charAt(i));
            }
            if (priority == -1){
                current += ' ';
                while (getPriority(stack.peek()) != 1){
                    current += stack.pop();
                }
                stack.pop();
            }
        }
        while (!stack.empty()){
            current += stack.pop();
        }

        return current;
    }

    public String RPNToAnswer(String expression){
        String answer = "";
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++){
            if (expression.charAt(i) == ' ') continue;
            if (getPriority(expression.charAt(i)) == 0){
                while(expression.charAt(i) != ' ' && getPriority(expression.charAt(i)) == 0){
                    answer += expression.charAt(i++);
                    if (i == expression.length()) break;
                }
            stack.push(Double.parseDouble(answer));
            answer = "";
            }
        if (getPriority(expression.charAt(i)) > 1){
            double a = stack.pop();
            double b = stack.pop();
            if (expression.charAt(i) == '+') stack.push(b + a);
            if (expression.charAt(i) == '-') stack.push(b - a);
            if (expression.charAt(i) == '*') stack.push(b * a);
            if (expression.charAt(i) == '/') stack.push(b / a);
        }
        }

        return String.valueOf(stack.pop());
    }


    public boolean isValid(String expression){
        String previous;
        String next;
        boolean isValid = true;
        for (int i = 0; i < expression.length()-1; i++) {
            previous = String.valueOf(expression.charAt(i));
            next = String.valueOf(expression.charAt(i+1));
            if (previous.equals(next) && !previous.matches("[0-9]+")){
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public boolean checkBracket(String expression){
        String previous;
        String next;
        boolean isValid = true;
        int first = 0;
        int second = 0;
        for (int i = 0; i < expression.length()-1; i++) {
            previous = String.valueOf(expression.charAt(i));
            next = String.valueOf(expression.charAt(i+1));
            if (previous.equals("(") || next.equals("(")){
                first++;
            }else if (previous.equals(")") || next.equals(")")){
                second++;
            }
        }
        if (first!=second) isValid = false;
        return isValid;
    }


}


