package setup12;

class LinkedList {
    Node header;

    static class Node {
        int data;
        Node next = null;

        public Node(int data) {
            this.data = data;
        }

        public Node() {
        }
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
        while(n.next != null) {
            System.out.print(n.data +"->");
            n = n.next;
        }
        System.out.println(n.data);
    }
    public Node get(int i) {
        Node n = header;
        for (int j=0; j<i; j++) {
            n = n.next;
        }
        return n;
    }
}
public class Main {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.append(1);
        ll.append(2);
        ll.append(1);
        ll.retrieve();

        System.out.println(isPalindrome(ll.get(1)));
    }
    private static boolean isPalindrome(LinkedList.Node head){
        LinkedList.Node reversed = reversedAndClone(head);
        return isEqual(head, reversed);
    }

    private static LinkedList.Node reversedAndClone(LinkedList.Node node){
        LinkedList.Node head = null;
        while (node != null) {
            LinkedList.Node n = new LinkedList.Node(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    private static boolean isEqual(LinkedList.Node one, LinkedList.Node two) {
        while(one != null && two != null) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }

}
