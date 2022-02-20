package setup16;

class ListNode {
    int data;
    ListNode next;
}

public class Main {
    static ListNode create(int data) {
        ListNode tmp = new ListNode();
        tmp.data = data;
        return tmp;
    }
    static int length(ListNode tmp) {
        int cnt = 0;
        while (tmp != null) {
            cnt++;
            tmp = tmp.next;
        }
        return cnt;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = length(headA);
        int lenB = length(headB);
        if (lenA > lenB) {
            int cnt = lenA - lenB;
            while(cnt --> 0)
                headA =headA.next;
        } else {
            int cnt = lenA - lenB;
            while (cnt --> 0)
                headB = headB.next;
        }
        while (headA != null && headB != null) {
            if (headA == headB)
                return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode headA = create(5);
        headA.next = create(7);
        headA.next.next = create(9);

        ListNode headB = create(6);
        headB.next = create(8);

        headA.next.next.next = headB.next.next = create(10);
        headA.next.next.next.next = headB.next.next.next = create(7);
        headA.next.next.next.next.next =headB.next.next.next.next = create(6);

        System.out.print("Intersection at node: ");
        System.out.print(getIntersectionNode(headA, headB).data);
    }
}