package setup38;

import java.util.ArrayList;
import java.util.LinkedList;

class Tree {
    static class Node {
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
    }
    Node makeBST(int start, int end) {
        if (start>end) return null;
        int mid = (start+end)/2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid-1);
        node.right = makeBST(mid+1, end);
        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        Tree t = new Tree(5);
        ArrayList<LinkedList<Integer>> result = allSequences(t.root);
        for (LinkedList<Integer> I : result) {
            for (int data : I) {
                System.out.print(data);
            }
            System.out.println();
        }

    }
    static ArrayList<LinkedList<Integer>> allSequences(Tree.Node node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        if (node == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.data);

        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

        for (LinkedList<Integer> left : leftSeq) {
            for (LinkedList<Integer> right : rightSeq) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
                weavedLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }
    static void weavedLists(LinkedList<Integer> first, LinkedList<Integer> second,
                            ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){
        if (first.size()==0 || second.size()==0) {
            LinkedList<Integer> result = new LinkedList<Integer>();
            for (int data : prefix) result.add(data);
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weavedLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weavedLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headFirst);
    }
}