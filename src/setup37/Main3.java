package setup37;

import java.util.HashMap;

class Tree3 {
    static class Node {
        int data;
        Node left;
        Node right;
        Node parent;

        Node (int data) {
            this.data = data;
        }
    }
    Node root;
    HashMap<Integer, Node> rootMap;
    Tree3(int size) {
        rootMap = new HashMap<Integer, Node>();
        root = makeBST(0, size-1, null);
    }
    Node makeBST(int start, int end, Node parent) {
        if (start > end) return null;
        int mid = (start + end) /2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid-1, node);
        node.right = makeBST(mid+1, end, node);
        node.parent = parent;
        rootMap.put(mid, node);
        return node;
    }
    Node getNode(int data) {
        return rootMap.get(data);
    }
    boolean covers(Node root, Node node) {
        if (root == null) return false;
        if (root == node) return true;
        return covers(root.left, node) || covers(root.right, node);
    }

    Node commonAncestor(int d1, int d2) {
        Node p = getNode(d1);
        Node q = getNode(d2);
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        }
        return ancestorHelper(root, p, q);
    }
    Node ancestorHelper(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        boolean plsOnLeft = covers(root.left, p);
        boolean qlsOnLeft = covers(root.left, q);
        if (plsOnLeft != qlsOnLeft) {
            return root;
        }
        Node childSide = plsOnLeft ? root.left : root.right;
        return ancestorHelper(childSide, p, q);
    }

}
public class Main3 {
    public static void main(String[] args) {
        Tree3 t = new Tree3(10);
        Tree3.Node fa = t.commonAncestor(2,8);
        System.out.println("The first common ancestor is " + fa.data);
    }
}
