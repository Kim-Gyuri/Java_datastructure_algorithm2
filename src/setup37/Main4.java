package setup37;

import java.util.HashMap;

class Tree4 {
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
    Tree4 (int size) {
        rootMap = new HashMap<Integer, Node> ();
        root = makeBST(0, size-1, null);
    }
    Node makeBST(int start, int end, Node parent) {
        if (start > end) return null;
        int mid = (start + end)/2;
        Node node= new Node(mid);
        node.left = makeBST(start, mid-1, node);
        node.right = makeBST(mid+1, end, node);
        node.parent = parent;
        rootMap.put(mid, node);
        return node;
    }
    Node getNode(int data) {
        return rootMap.get(data);
    }
    Node commonAncestor(int d1, int d2) {
        Node p = getNode(d1);
        Node q = getNode(d2);
        return commonAncestor(root, p, q);
    }
    Node commonAncestor(Node root, Node p, Node q) {
        if (root == null) return null;
        if (root==p && root==q) return root;
        Node x = commonAncestor(root.left, p, q);
        if (x != null && x!=p && x!=q) {
            return x;
        }
        Node y = commonAncestor(root.right, p, q);
        if (y!=null && y!=p && y!=q) {
            return y;
        }
        if (x!=null && y!=null) {
            return root;
        } else if (root==p || root==q) {
            return root;
        } else {
            return x== null ?y:x;
        }
    }
}

public class Main4 {
    public static void main(String[] args) {
        Tree4 t = new Tree4(10);
        Tree4.Node fa = t.commonAncestor(2,8);
        System.out.println("The first common ancestor is " + fa.data);
    }
}
