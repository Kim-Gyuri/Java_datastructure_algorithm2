package setup34;

class Tree3 {

    Node root;
    int size;
    int index;

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }


    Tree3 (int size) {
        this.index = 0;
        this.size = size;
        root = makeBST(0, size - 1);
        //  root.right.right.right.left = new Node(10);
        // this.size++;
        root.right.right.right.left = new Node(9); this.size++;
        root.right.right.right.right = new Node(9); this.size++;
        root.right.right.right.left.left = new Node(9); this.size++;
        root.right.right.right.left.right = new Node(9); this.size++;
        root.right.right.right.left.left.left = new Node(8); this.size++;
        root.right.right.right.left.left.right = new Node(9); this.size++;
    }

    Node makeBST(int start, int end) {
        if (start > end) return null;
        int mid = (start+ end)/2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid -1);
        node.right = makeBST(mid+1, end);
        return node;
    }

    boolean isValidateBST1() {
        int[] array = new int[size];
        inorder(root, array);
        for (int i= 1; i< array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return  true;
    }


    void inorder(Node root, int[] array) {
        if (root != null) {
            inorder(root.left, array);
            array[index] = root.data;
            index++;
            inorder(root.right, array);
        }
    }

    Integer last_printed = null;
    boolean isValidateBST2() {
        return isValidateBST2(root);
    }
    boolean isValidateBST2(Node n) {
        if (n == null) return true;
        if (!isValidateBST2(n.left)) return false;
        if (last_printed != null && n.data < last_printed) {
            return false;
        }
        last_printed = n.data;
        if (!isValidateBST2(n.right)) return false;
        return true;
    }

    boolean isValidateBST3() {
        return isValidateBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isValidateBST3(Node n, int min, int max) {
        if (n== null) {
            return true;
        }
        if (n.data < min || n.data > max) {
            return false;
        }
        if (!isValidateBST3(n.left, min, n.data) || !isValidateBST3(n.right, n.data, max)) {
            return false;
        }
        return true;
    }
}

public class Main3 {
    public static void main(String[] args) {
        Tree3 t = new Tree3(10);
        System.out.println("Solution 1 - using inorder: " + t.isValidateBST1());
        System.out.println("Solution 2 - without array: " + t.isValidateBST2());
        System.out.println("Solution 2 - min/max: " + t.isValidateBST3());

    }
}

