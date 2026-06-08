class AVLNode {
    int score;
    int height;
    AVLNode left, right;

    AVLNode(int score) {
        this.score = score;
        this.height = 1;
    }
}

public class CaseStudyOne {

    AVLNode root;

    // Get height of a node
    int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    // Get balance factor of a node
    int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // Right rotation
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotation
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert a score into AVL Tree
    AVLNode insert(AVLNode node, int score) {

        // Normal BST insertion
        if (node == null)
            return new AVLNode(score);

        if (score < node.score)
            node.left = insert(node.left, score);
        else if (score > node.score)
            node.right = insert(node.right, score);
        else
            return node; // Duplicate scores not allowed

        // Update height
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Check balance factor
        int balance = getBalance(node);

        // LL Case
        if (balance > 1 && score < node.left.score)
            return rightRotate(node);

        // RR Case
        if (balance < -1 && score > node.right.score)
            return leftRotate(node);

        // LR Case
        if (balance > 1 && score > node.left.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Case
        if (balance < -1 && score < node.right.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Inorder traversal (Ascending order)
    void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.score + " ");
            inorder(node.right);
        }
    }

    // Reverse Inorder traversal (Descending order)
    void reverseInorder(AVLNode node) {
        if (node != null) {
            reverseInorder(node.right);
            System.out.print(node.score + " ");
            reverseInorder(node.left);
        }
    }

    public static void main(String[] args) {

        CaseStudyOne tree = new CaseStudyOne();

        int[] scores = {
            50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45
        };

        // Insert scores into AVL tree
        for (int score : scores) {
            tree.root = tree.insert(tree.root, score);
        }

        System.out.println("Leaderboard Scores (Ascending):");
        tree.inorder(tree.root);

        System.out.println("\n\nTop Scores (Descending):");
        tree.reverseInorder(tree.root);

        System.out.println();
    }
}