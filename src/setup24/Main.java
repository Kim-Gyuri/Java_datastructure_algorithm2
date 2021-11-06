package setup24;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

class SetOfStacks {
    int capacity;
    ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();

    SetOfStacks(int capacity) {
        this.capacity = capacity;
    }

    public Stack<Integer> getLastStack() {
        if (stacks.size() == 0) return null;
        return stacks.get(stacks.size() -1);
    }

    public void addStack() {
        stacks.add(new Stack<Integer>());
    }

    public void removeLastStack() {
        stacks.remove(stacks.size() -1);
    }

    public void push(int data) {
        Stack<Integer> last = getLastStack();
        if (last == null || last.size() == capacity) {
            addStack();;
            last = getLastStack();
        }
        last.push(data);
    }

    public int pop() {
        Stack<Integer> last = getLastStack();
        if (last == null || last.isEmpty()) {
            throw new EmptyStackException();
        }
        int data = last.pop();
        if (last.isEmpty()) {
            removeLastStack();
        }
        return data;
    }
}

public class Main {
    public static void main(String[] args) {
        SetOfStacks sos = new SetOfStacks(3);
        sos.push(1);
        sos.push(2);
        sos.push(3);

        sos.push(4);
        sos.push(5);
        sos.push(6);

        sos.push(7);
        sos.push(8);


        System.out.println(sos.pop());
        System.out.println(sos.pop());
        System.out.println(sos.pop());
        System.out.println(sos.pop());
    }
}
