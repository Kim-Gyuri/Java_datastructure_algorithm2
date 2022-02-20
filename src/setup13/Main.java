package setup13;

import java.util.Stack;

class LinkedList {
    Node header;
    static class Node{
        int data;
        Node next = null;

        public Node(int data) {
            this.data = data;
        }
        public Node() {}
    }
    public LinkedList() {
        header = new Node();
    }
    void append(int d) {
        Node end = new Node();
        end.data = d;
        Node n = header;
        while(n.next != null) {
            n = n.next;
        }
        n.next = end;
    }
    void retrieve() {
        Node n = header.next;
        while (n.next!= null) {
            System.out.print((char) n.data + "->");
            n = n.next;
        }
        System.out.println((char) n.data);
    }
    public Node get(int i) {
        Node n = header;
        for(int j=0; j<i; j++) {
            n = n.next;
        }
        return n;
    }
}
public class Main {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.append('m');
        ll.append('a');
        ll.append('d');
        ll.append('a');
        ll.append('m');
        ll.retrieve();

        System.out.println(isPalindrome(ll.get(1)));
    }

    private static boolean isPalindrome(LinkedList.Node head) {
        LinkedList.Node fast = null;
        LinkedList.Node slow = null;

        Stack<Character> stack = new Stack<Character>();
        while(fast != null && fast.next != null) {
            stack.push((char) slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        while(slow != null) {
            char c = stack.pop();
            if (slow.data != c) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }
}
