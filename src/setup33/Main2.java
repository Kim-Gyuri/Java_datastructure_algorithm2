package setup33;

class Tree {

    class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    Node root;
    Tree (int size) {
        root = makeBST(0, size-1);
        root.right.right.right.right = new Node(10);
    }

    Node makeBST(int start, int end) {
        if (start > end) return null;
        int mid = (start+ end)/2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid -1);
        node.right = makeBST(mid+1, end);
        return node;
    }

    boolean isBalanced(Node root) {
        if (root == null) return true;
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff)>1) {
            return  false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }
    int getHeight(Node root) {
        if (root == null) return -1;
        return Math.max(getHeight(root.left), getHeight(root.right))+1;
    }
}

public class Main2 {
    public static void main(String[] args) {
        Tree t = new Tree(10);
        System.out.println(t.isBalanced(t.root));

    }
}
