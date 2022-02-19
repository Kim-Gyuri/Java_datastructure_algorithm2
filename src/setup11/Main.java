package setup11;

class LinkedList {
    Node header;
    static class Node{
        int data;
        Node next = null;
    }

    public LinkedList() {
        header = new Node();
    }

    void append(int d) {
        Node end = new Node();
        end.data =d;
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
        for(int j=0; j<i; j++) {
            n = n.next;
        }
        return n;
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.append(7);
        ll.append(2);
        ll.append(8);
        ll.append(5);
        ll.append(3);
        ll.append(4);
        ll.retrieve();

        LinkedList.Node n = Partition2(ll.get(1), 5);
        while(n.next != null) {
            System.out.print(n.data +"->");
            n = n.next;
        }
        System.out.println(n.data);
    }

    private static LinkedList.Node Partition2(LinkedList.Node n, int x) {
        LinkedList.Node head = n;
        LinkedList.Node tail = n;

        while(n != null) {
            LinkedList.Node next = n.next;
            if(n.data < x) {
                n.next = head;
                head = n;
            } else{
                tail.next = n;
                tail = n;
            }
            n = next;
        }
        tail.next = null;
        return head;
    }
}
