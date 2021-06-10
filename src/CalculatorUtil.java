import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author donglin06
 * @Date 2021/6/9
 **/
public class CalculatorUtil {

    public static Double calculator(String input) {
        List<String> list = new ArrayList<>();
        char[] arr = input.toCharArray();

        StringBuffer tmpStr = new StringBuffer();
        for (char c : arr) {
            if (c >= '0' && c <= '9') {
                tmpStr.append(c);
            } else if (c == '.') {
                if (tmpStr.indexOf("0123456789") > 0) {
                    throw new RuntimeException("非法字符");
                }
                tmpStr.append(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                if (tmpStr.length() > 0) {
                    list.add(tmpStr.toString());
                    tmpStr.setLength(0);
                }
                list.add(c + "");
            } else {
                throw new RuntimeException("非法字符");
            }
        }

        if (tmpStr.length() > 0) {
            list.add(tmpStr.toString());
        }

        //
        List<String> stringList = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        String tmp;
        for (String s : list) {
            if (s.equals("(")) {
                stack.push(s);
            } else if (s.equals(")")) {
                while (!(tmp = stack.pop()).equals("(")) {
                    stringList.add(tmp);
                }
            } else if (s.equals("*") || s.equals("/")) {
                while (!stack.isEmpty()) {
                    tmp = stack.peek();
                    tmp = stack.peek();
                    if (tmp.equals("*") || tmp.equals("/")) {
                        stack.pop();
                        stringList.add(tmp);
                    } else {
                        break;
                    }
                }
                stack.push(s);
            } else if (s.equals("+") || s.equals("-")) {
                while (!stack.isEmpty()) {
                    // 取出栈顶元素
                    tmp = stack.peek();
                    if (!tmp.equals("(")) {
                        stack.pop();
                        stringList.add(tmp);
                    } else {
                        break;
                    }
                }
                stack.push(s);
            } else {
                stringList.add(s);
            }
        }

        while (!stack.isEmpty()) {
            stringList.add(stack.pop());
        }

        //计算
        Stack<Double> newStack = new Stack<>();
        for (String s : stringList) {
            // 若遇运算符，则从栈中退出两个元素，先退出的放到运算符的右边，后退出的放到运算符左边，
            // 运算后的结果再进栈，直到后缀表达式遍历完毕
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                Double b1 = newStack.pop();
                Double b2 = newStack.pop();
                switch (s) {
                    case "+":
                        newStack.push(b2 + b1);
                        break;
                    case "-":
                        newStack.push(b2 - b1);
                        break;
                    case "*":
                        newStack.push(b2 * b1);
                        break;
                    case "/":
                        newStack.push(b2 / b1);
                        break;
                }
            }
            // 如果是数字，入栈
            else {
                newStack.push(Double.parseDouble(s));
            }
        }
        // 最后，栈中仅有一个元素，就是计算结果
        return newStack.peek();
    }
}
