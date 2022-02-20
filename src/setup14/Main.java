package setup14;

class LinkedList {
    Node header;
    static class Node {
        int data;
        Node next= null;

        public Node(int data) {
            this.data  = data;
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
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    void retrieve() {
        Node n = header.next;
        while(n.next != null) {
            System.out.print((char) n.data + "->");
            n = n.next;
        }
        System.out.println((char) n.data);
    }

    public Node get(int i) {
        Node n = header;
        for (int j=0; j<i; j++) {
            n = n.next;
        }
        return n;
    }
}
class Storage {
    public LinkedList.Node node;
    public boolean result;

    public Storage(LinkedList.Node node, boolean result) {
        this.node = node;
        this.result = result;
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
        int length = getListLength(head);
        Storage storage = isPalindromeRecursive(head, length);
        return storage.result;
    }

    private static Storage isPalindromeRecursive(LinkedList.Node head, int length) {
        if (head == null || length <= 0) {
            return new Storage(head, true);
        } else if (length == 1) {
            return new Storage(head.next, true);
        }
        Storage storage = isPalindromeRecursive(head.next, length-2);
        if (!storage.result || storage.node == null) {
            return storage;
        }
        storage.result = (head.data == storage.node.data);
        storage.node = storage.node.next;
        return storage;
    }

    private static int getListLength(LinkedList.Node l) {
        int total = 0;
        while(l != null) {
            total++;
            l = l.next;
        }
        return total;
    }
}
