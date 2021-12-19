package setup37;

import java.util.HashMap;

class Tree2 {
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
    Tree2 (int size) {
        rootMap = new HashMap<Integer, Node>();
        root = makeBST(0, size-1, null);
    }
    Node makeBST(int start, int end, Node parent) {
        if (start > end) return null;
        int mid = (start + end)/2;
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
        if (!covers(root,p) || !covers(root,q)) {
            return null;
        } else if (covers(p,q)) {
            return p;
        } else if(covers(q, p)) {
            return q;
        }
        Node sibling = getSibling(p);
        Node parent = p.parent;
        while(!covers(sibling, q)) {
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;
    }
    Node getSibling(Node node) {
        if (node == null || node.parent == null) {
            return null;
        }
        Node parent = node.parent;
        return parent.left == node ? parent.right : parent.left;
    }


}
public class Main2 {
    public static void main(String[] args) {
        Tree2 t = new Tree2(10);
        Tree2.Node fa = t.commonAncestor(6,9);
        System.out.println("The first common ancestor data is " + fa.data);
    }
}
