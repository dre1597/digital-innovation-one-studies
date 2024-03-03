package org.example;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    try (Scanner in = new Scanner(System.in)) {
      Phone phone = new Phone();

      int option;

      do {
        System.out.println("Select one option:");
        System.out.println("1. View page");
        System.out.println("2. Add new tab");
        System.out.println("3. Refresh page");
        System.out.println("4. Call");
        System.out.println("5. Message");
        System.out.println("6. Answer");
        System.out.println("7. Play");
        System.out.println("8. Pause");
        System.out.println("9. Select track");
        System.out.println("10. Exit");

        option = in.nextInt();
        switch (option) {
          case 1:
            phone.viewPage();
            Thread.sleep(1000);
            break;
          case 2:
            phone.addNewTab();
            Thread.sleep(1000);
            break;
          case 3:
            phone.refreshPage();
            Thread.sleep(1000);
            break;
          case 4:
            phone.call();
            Thread.sleep(1000);
            break;
          case 5:
            phone.message();
            Thread.sleep(1000);
            break;
          case 6:
            phone.answer();
            Thread.sleep(1000);
            break;
          case 7:
            phone.play();
            Thread.sleep(1000);
            break;
          case 8:
            phone.pause();
            Thread.sleep(1000);
            break;
          case 9:
            phone.selectTrack();
            Thread.sleep(1000);
            break;
          case 10:
            System.out.println("Exiting...");
            Thread.sleep(1000);
            break;
          default:
            System.out.println("Invalid option");
            Thread.sleep(1000);
            break;
        }
      } while (option != 10);
    }
  }
}
