package setup17;

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
        while(tmp != null) {
            cnt++;
            tmp = tmp.next;
        }
        return cnt;
    }

    public static ListNode findLoop(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;

        while(fast!= slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        ListNode headA = create(1);
        headA.next = create(2);
        headA.next.next = create(3);
        headA.next.next.next = create(4);
        headA.next.next.next.next = create(5);
        headA.next.next.next.next.next = create(6);
        headA.next.next.next.next.next.next = create(7);
        headA.next.next.next.next.next.next.next = create(8);

        headA.next.next.next.next.next.next.next = headA.next.next.next;
        ListNode n = findLoop(headA);
        if (n != null) {
            System.out.println("start of node: " + n.data);
        }
    }
}
