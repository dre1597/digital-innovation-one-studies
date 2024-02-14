package org.example;

import java.util.Scanner;

public class Counter {
  public static void main(String[] args) {
    try (final var scanner = new Scanner(System.in)) {
      System.out.print("Insert the first number: ");
      var firstNumber = scanner.nextInt();
      System.out.print("Insert the second number: ");
      var secondNumber = scanner.nextInt();
      count(firstNumber, secondNumber);
    }
  }

  static void count(final int firstNumber, final int secondNumber) throws InvalidParametersException {
    if (firstNumber < 0 || secondNumber < 0) {
      throw new InvalidParametersException("Both parameters should be non-negative.");
    }

    if (firstNumber > secondNumber) {
      throw new InvalidParametersException("First parameter should be less than second parameter.");
    }

    final var count = secondNumber - firstNumber;

    for (int i = 1; i <= count; i++) {
      System.out.println("Printing the number " + i);
    }
  }
}
