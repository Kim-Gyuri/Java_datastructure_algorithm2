package setup23;

import java.util.Stack;

class StackWithMin2 extends Stack<Integer> {
    Stack<Integer> s2;
    public StackWithMin2() {
        s2 = new Stack<Integer>();
    }
    public int min() {
        if (s2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return s2.peek();
        }
    }

    public void push(int value) {
        if(value <  min()) {
            s2.push(value);
        }
        super.push(value);
    }

    public Integer pop() {
        int value = super.pop();
        if(value == min()) {
            s2.pop();
        }
        return value;
    }
}

public class Main {
    public static void main (String[] args) {

        StackWithMin2 stack = new StackWithMin2();
        stack.push(3);
        stack.push(5);
        stack.push(1);
        stack.push(2);


        System.out.println("min: " + stack.min());
        System.out.println("pop: " + stack.pop());
        System.out.println("min: " + stack.min());
        System.out.println("pop: " + stack.pop());
        System.out.println("min: " + stack.min());
        System.out.println("pop: " + stack.pop());
        System.out.println("min: " + stack.min());



    }
}
