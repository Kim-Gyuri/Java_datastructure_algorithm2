package setup39;

class Tree {
    class Node {
        int data;
        Node left;
        Node right;
        Node (int data) {
            this.data = data;
        }
    }
    Node root;
    Tree(int size) {
        this.root = makeBST(0, size-1);
    }
    Node makeBST(int start, int end) {
        if (start>end) return null;
        int mid = (start+end)/2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid-1);
        node.right = makeBST(mid+1, end);
        return node;
    }
    int countPathsWithSum(int targetSum) {
        return countPathsWithSum(root, targetSum);
    }
    int countPathsWithSum(Node root, int targetSum) {
        if (root == null) return 0;
        int pathsFromRoot = countPathsWithSumFromNode(root, targetSum,0);
        int pathsOnLeft = countPathsWithSum(root.left, targetSum);
        int pathsOnRight = countPathsWithSum(root.right, targetSum);
        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }
    int countPathsWithSumFromNode(Node node, int targetSum, int currSum) {
        if (node == null) return 0;
        currSum += node.data;
        int totalPaths = 0;
        if (currSum == targetSum) {
            totalPaths++;
        }
        totalPaths += countPathsWithSumFromNode(node.left, targetSum, currSum);
        totalPaths += countPathsWithSumFromNode(node.right, targetSum, currSum);
        return totalPaths;
    }
}
public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(10);
        System.out.println(tree.countPathsWithSum(5));
    }
}
