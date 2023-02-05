import java.util.Scanner;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;
    int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Method to add a new node to the front of the linked list
    public void addToFront(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Method to remove the last node from the linked list
    public int removeLast() {
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        int removedData = current.next.data;
        current.next = null;
        size--;
        return removedData;
    }

    // Method to check if the linked list contains a given file
    public boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Method to display the content of the linked list
    public void display() {
        System.out.print("File stack: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        for (int i = 0; i < 5 - size; i++) {
            System.out.print("0 ");
        }
        System.out.println();
    }
}

public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList list = new LinkedList();

        System.out.print("Please enter the file you want to use (enter 0 to terminate): ");
        int file = sc.nextInt();
        while (file != 0) ;
            // If the linked list already contains the file, bring it to the front
            if (list.contains(file)) {
                Node current = list.head;
                while (current.next != null) {
                    if (current.next.data == file) {
                        Node temp = current.next;
                        current.next = temp.next;
                        temp.next = list.head;
                        list.head = temp;
                        break;
                    }
                    current = current.next;
                }
            } else {
                // If the linked list is full, remove the last node and add the new file to the front
                if (list.size == 5) {
                    System.out.println("File " + list.removeLast() + " has been returned to the rack.");
                }
                list.addToFront(file);
            }
            list.display();
            System.out.print("Please enter the file you want to bring to the front."
                    + " (enter 0 to terminate): ");
                    file = sc.nextInt();
                    System.out.println();
        }   
        System.out();
    }   
