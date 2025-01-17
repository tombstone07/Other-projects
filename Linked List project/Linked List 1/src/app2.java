import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int[] fileStack = new int[5];
    int fileCount = 0;

    while (true) {
      System.out.print("Please enter the file you want to use (enter 0 to terminate): ");
      int file = scan.nextInt();
      if (file == 0) {
        break;
      }

      boolean isFileInStack = false;
      for (int i = 0; i < fileCount; i++) {
        if (fileStack[i] == file) {
          for (int j = i; j > 0; j--) {
            fileStack[j] = fileStack[j - 1];
          }
          fileStack[0] = file;
          isFileInStack = true;
          break;
        }
      }

      if (!isFileInStack) {
        int returnedFile = 0;
        if (fileCount == 5) {
          returnedFile = fileStack[4];
          fileCount--;
        }
        for (int i = fileCount; i > 0; i--) {
          fileStack[i] = fileStack[i - 1];
        }
        fileStack[0] = file;
        if (fileCount < 5) {
          fileCount++;
        }
        if (returnedFile != 0) {
          System.out.println("File " + returnedFile + " has been returned to the rack.");
        }
      }

      System.out.print("File stack: ");
      for (int i = 0; i < fileCount; i++) {
        System.out.print(fileStack[i]);
      }
      System.out.println();
    }

    System.out.println("Thank you!");
  }
}
