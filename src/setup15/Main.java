package setup15;

class LinkedList {
    Node header;
    static class Node{
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

class Storage {
    int carry =0;
    LinkedList.Node result = null;
}

public class Main {
    public static void main(String[] args) {
        LinkedList l1 = new LinkedList();
        l1.append(9);
        l1.append(1);
        l1.append(9);
        l1.retrieve();

        LinkedList l2 = new LinkedList();
        l2.append(0);
        l2.append(8);
        l2.append(4);
        l2.retrieve();

        LinkedList.Node l = sumLists(l1.get(1), l2.get(1));
        while (l.next != null) {
            System.out.print(l.data +"->");
            l = l.next;
        }
        System.out.println(l.data);
    }

    private static LinkedList.Node sumLists(LinkedList.Node l1, LinkedList.Node l2) {
        int len1 = getListLength(l1);
        int len2 = getListLength(l2);

        if(len1 < len2) {
            l1 = LPadList(l2, len2-len1);
        } else{
            l2 = LPadList(l2, len1-len2);
        }
        Storage storage = addLists(l1, l2);
        if(storage.carry == 0) {
            return storage.result;
        } else{
            storage.result = insertBefore(storage.result, storage.carry);
        }
        return storage.result;
    }

    private static Storage addLists(LinkedList.Node l1, LinkedList.Node l2) {
        if(l1 == null && l2 == null) {
            Storage storage = new Storage();
            return storage;
        }
        Storage storage = addLists(l1.next, l2.next);
        int value = storage.carry + l1.data + l2.data;
        int data = value%10;
        storage.result = insertBefore(storage.result, data);
        storage.carry = value/10;
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

    private static LinkedList.Node insertBefore(LinkedList.Node node, int data) {
        LinkedList.Node before = new LinkedList.Node(data);
        if(node != null) {
            before.next = node;
        }
        return before;
    }

    private static LinkedList.Node LPadList(LinkedList.Node l, int length) {
        LinkedList.Node head = l;
        for(int i=0; i<length; i++) {
            head = insertBefore(head,0);
        }
        return head;
    }
}