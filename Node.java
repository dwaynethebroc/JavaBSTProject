package JavaBSTProject;

import java.util.Scanner;

public class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    static String menu(Scanner scanner) {
        System.out.print("\nMenu: \n\n" +
                "1)Create a binary search tree \n" +
                "2)Add a node\n" +
                "3)Delete a node\n" +
                "4) Print nodes by InOrder\n" +
                "5) Print nodes by PreOrder\n" +
                "6) Print nodes by PostOrder\n" +
                "7) Exit program \n");
        System.out.print("Enter your selection 1-7: ");
        String input = scanner.nextLine();

        return input;
    }

    static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else
            root.right = insert(root.right, data);

        return root;
    }

    static Node makeNodeTree(int[] initialArray, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node root = new Node(initialArray[mid]);
        root.left = makeNodeTree(initialArray, start, mid - 1);
        root.right = makeNodeTree(initialArray, mid + 1, end);
        return root;
    }

    static Node addNode(int newNodeData, Node tree) {
        Node newTree = insert(tree, newNodeData);

        return newTree;
    }

    static Node deleteNode(int nodeToDelete, Node root) {
        if (root == null) {
            return root;
        }

        if (nodeToDelete < root.data) {
            root.left = deleteNode(nodeToDelete, root.left);
        } else if (nodeToDelete > root.data) {
            root.right = deleteNode(nodeToDelete, root.right);
        } else {
            // root.data == nodeToDelete

            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.right != null && root.left == null) {
                return root.right;
            } else {
                // root has two children
                // we are on the root, go right one node, then left until min value found
                // replace node
                // delete orignal replacement node further down tree
                Node newMinNode = findMinimumValue(root.right);
                root.data = newMinNode.data;
                root.right = deleteNode(newMinNode.data, root.right);
            }
        }

        return root;

    }

    static Node findMinimumValue(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    static void printTreeInOrder(Node treeRoot) {
        if (treeRoot == null) {
            return;
        }
        printTreeInOrder(treeRoot.left);
        System.out.print(treeRoot.data + " ");
        printTreeInOrder(treeRoot.right);
    }

    static void printTreePreOrder(Node treeRoot) {
        if (treeRoot == null) {
            return;
        }
        System.out.print(treeRoot.data + " ");
        printTreePreOrder(treeRoot.left);
        printTreePreOrder(treeRoot.right);
    }

    static void printTreePostOrder(Node treeRoot) {
        if (treeRoot == null) {
            return;
        }
        printTreePostOrder(treeRoot.left);
        printTreePostOrder(treeRoot.right);
        System.out.print(treeRoot.data + " ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] standardArray = { 1, 2, 3, 4, 5, 6, 7 };
        Node standardTree = makeNodeTree(standardArray, 0, standardArray.length - 1);
        String response = "";

        while (!response.equals("7")) {
            response = menu(scanner);
            System.out.println(response);

            switch (response) {
                case "1":
                    System.out.print("Binary Tree Created\n");
                    standardTree = makeNodeTree(standardArray, 0, standardArray.length - 1);
                    break;
                case "2":
                    System.out.print("What is the value of the Node you want to add?: ");
                    String inputAdd = scanner.nextLine();

                    int inputToIntA = Integer.parseInt(inputAdd);

                    standardTree = insert(standardTree, inputToIntA);
                    System.out.print("Node" + inputAdd + "added");
                    break;
                case "3":
                    System.out.print("What is the value of the Node you want deleted?: ");
                    String inputDelete = scanner.nextLine();

                    int inputToIntD = Integer.parseInt(inputDelete);
                    standardTree = deleteNode(inputToIntD, standardTree);
                    System.out.print("Node" + inputDelete + "deleted");
                    break;
                case "4":
                    System.out.print("InOrder Traversal: ");
                    printTreeInOrder(standardTree);
                    System.out.println();
                    break;
                case "5":
                    System.out.print("PreOrder Traversal: ");
                    printTreePreOrder(standardTree);
                    System.out.println();
                    break;
                case "6":
                    System.out.print("PostOrder Traversal: ");
                    printTreePostOrder(standardTree);
                    System.out.println();
                    break;
                case "7":
                    System.out.println("Program has been exited. Have a nice day");
                    System.out.println();
                    break;
            }
        }

        scanner.close();
    }
}