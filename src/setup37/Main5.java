package setup37;

import java.util.HashMap;

class Tree5 {
    static class Node {
        int data;
        Node left;
        Node right;
        Node parent;

        Node(int data) {
            this.data =data;
        }
    }
    Node root;
    HashMap<Integer, Node> rootMap;
    Tree5 (int size) {
        rootMap = new HashMap<Integer, Node>();
        root = makeBST(0, size-1, null);
    }
    Node makeBST(int start, int end, Node parent) {
        if (start>end) return null;
        int mid = (start+end)/2;
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
    class Result {
        Node node;
        boolean isAncestor;
        Result (Node n, boolean isAnc) {
            node = n;
            isAncestor = isAnc;
        }
    }
    Node commonAncestor(int d1, int d2) {
        Node p = getNode(d1);
        Node q = getNode(d2);
        Result r = commonAncestor(root, p, q);
        if (r.isAncestor) {
            return r.node;
        }
        return null;
    }
    Result commonAncestor (Node root, Node p, Node q) {
        if (root == null) return new Result(null, false);
        if (root == p && root == q) return new Result(root, true);
        Result rx = commonAncestor(root.left, p, q);
        if(rx.isAncestor) return rx;

        Result ry = commonAncestor(root.right, p, q);
        if (rx.node != null && ry.node!= null) {
            return new Result(root, true);
        } else if (root == p || root == q) {
            boolean isAncestor = rx.node != null || ry.node != null;
            return new Result(root, isAncestor);
        } else {
            return new Result(rx.node != null ? rx.node : ry.node, false);
        }
    }
}
public class Main5 {
    public static void main(String[] args) {
        Tree5 t = new Tree5(10);
        Tree5.Node fa = t.commonAncestor(0,3);
        System.out.println("The first common ancestor is " + fa.data);
    }
}
