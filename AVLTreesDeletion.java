class AVLTreesDeletion {

    static class Node {
        int data;
        Node left;
        Node right;
        int height;

        Node(int data) {
            this.data = data;
            height = 1;
        }
    }

    static Node rightRotate(Node currNode) {
        Node tempNode = currNode.left;
        Node rightTempNode = tempNode.right;

        tempNode.right = currNode;
        currNode.left = rightTempNode;

        currNode.height = Math.max((currNode.left == null ? 0 : currNode.left.height),
                (currNode.right == null ? 0 : currNode.right.height)) + 1;
        tempNode.height = Math.max((tempNode.left == null ? 0 : tempNode.left.height),
                (tempNode.right == null ? 0 : tempNode.right.height));

        return tempNode;
    }

    static Node leftRotate(Node currNode) {
        Node tempNode = currNode.right;
        Node leftTempNode = tempNode.left;

        tempNode.left = currNode;
        currNode.right = leftTempNode;

        currNode.height = Math.max((currNode.left == null ? 0 : currNode.left.height),
                (currNode.right == null ? 0 : currNode.right.height)) + 1;

        tempNode.height = Math.max((tempNode.left == null ? 0 : tempNode.left.height),
                (tempNode.right == null ? 0 : tempNode.right.height)) + 1;

        return tempNode;
    }

    static Node insertValue(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (root.data > value)
            root.left = insertValue(root.left, value);
        else if (root.data < value)
            root.right = insertValue(root.right, value);
        else
            return root;

        root.height = 1
                + Math.max(root.left == null ? 0 : root.left.height, root.right == null ? 0 : root.right.height);

        int balanceFactor = ((root == null) ? 0
                : ((root.left == null ? 0 : root.left.height) - (root.right == null ? 0 : root.right.height)));

        if (balanceFactor > 1 && value < root.left.data)
            return rightRotate(root);

        if (balanceFactor < -1 && value > root.right.data)
            return leftRotate(root);

        if (balanceFactor > 1 && value > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balanceFactor < -1 && value < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    static void preOrder(Node root) {
        if (root == null)
            return;
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    static Node deleteNode(Node root, int value) {
        if (root == null)
            return root;

        if (value < root.data)
            root.left = deleteNode(root.left, value);

        else if (value > root.data)
            root.right = deleteNode(root.right, value);

        else {
            if (root.left == null || root.right == null) {
                Node temp = root.left == null ? root.right : root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = root.right;

                while (temp.left != null) {
                    temp = temp.left;
                }

                root.data = temp.data;
                root.right = deleteNode(root.right, temp.data);
            }
        }

        if (root == null)
            return root;

        root.height = 1
                + Math.max(root.left == null ? 0 : root.left.height, root.right == null ? 0 : root.right.height);

        int balanceFactor = ((root == null) ? 0
                : ((root.left == null ? 0 : root.left.height) - (root.right == null ? 0 : root.right.height)));

        if (balanceFactor > 1 && value < root.left.data)
            return rightRotate(root);

        if (balanceFactor < -1 && value > root.right.data)
            return leftRotate(root);

        if (balanceFactor > 1 && value > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balanceFactor < -1 && value < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public static void main(String[] args) {
        Node root = insertValue(null, 5);
        root = insertValue(root, 4);
        root = insertValue(root, 1);
        root = insertValue(root, 10);
        root = insertValue(root, 3);
        root = insertValue(root, 8);
        preOrder(root);
        System.out.println("-------------");
        root = deleteNode(root, 4);
        preOrder(root);
    }
}