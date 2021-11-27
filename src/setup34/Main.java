package setup34;

class Tree {
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

    Tree(int size) {
        this.index = 0;
        this.size = size;
        root = makeBST(0, size - 1);
    }

    Node makeBST(int start, int end) {
        if (start > end ) return null;
        int mid = (start+end)/2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid-1);
        node.right = makeBST(mid+1, end);
        return node;
    }

    boolean isValidateBST1() {
        int[] array = new int[size];
        inorder(root, array);
        for (int i=1; i<array.length; i++) {
            if (array[i] < array[i-1]) {
                return false;
            }
        }
        return true;
    }

    void inorder(Node root, int[] array) {
        if (root != null) {
            inorder(root.left, array);
            array[index] = root.data;
            index++;
            inorder(root.right, array);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Tree t = new Tree(10);
        System.out.println("solution 1 - using inorder " + t.isValidateBST1());
    }
}
