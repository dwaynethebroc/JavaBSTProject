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

    static String menu() {
        System.out.print("Menu: \n\n" +
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

    static Node makeNodeTree(int[] initialArray) {
        Node root = null;
        for (int data : initialArray) {
            root = insert(root, data);
        }

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

        if (root.data < nodeToDelete) {
            root.left = deleteNode(nodeToDelete, root.left);
        } else if (root.data > nodeToDelete) {
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

            }
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
        Node standardTree = makeNodeTree(standardArray);
        String response = menu();
        System.out.println(response);

        switch (response) {
            case "1":
                standardTree = makeNodeTree(standardArray);
                break;
            case "2":
                System.out.print("What is the value of the Node you want to add?: ");
                String inputAdd = scanner.nextLine();

                int inputToIntA = Integer.parseInt(inputAdd);

                standardTree = insert(standardTree, inputToIntA);
                break;
            case "3":
                System.out.print("What is the value of the Node you want deleted?: ");
                String inputDelete = scanner.nextLine();

                int inputToIntD = Integer.parseInt(inputDelete);
                deleteNode(inputToIntD, standardTree);
                break;
            case "4":
                printTreeInOrder(standardTree);
                break;
            case "5":
                printTreePreOrder(standardTree);
                break;
            case "6":
                printTreePostOrder(standardTree);
                break;
            case "7":
                System.out.println("Program has been exited. Have a nice day");
                scanner.close();
                break;
        }
    }

}