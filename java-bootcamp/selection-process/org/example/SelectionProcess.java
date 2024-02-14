package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SelectionProcess {
  public static void main(String[] args) {
    List<String> participants = selectParticipants();

    for (var participant : participants) {
      callParticipant(participant);
    }
  }

  static List<String> selectParticipants() {
    final var participants = List.of("John", "Peter", "Mary", "Jenifer", "Linda", "Michael", "David", "Sarah", "Tom", "Alex", "Oliver", "Emma");
    final var selectedParticipants = new ArrayList<String>();

    var selected = 0;
    var current = 0;
    while (selected < 5 && current < participants.size()) {
      final var currentParticipant = participants.get(current);
      final var wantedValue = mockWantedValue();

      System.out.println(wantedValue + " - " + currentParticipant);

      if (checkParticipant(wantedValue)) {
        System.out.println("Selected " + currentParticipant);
        selectedParticipants.add(currentParticipant);
        selected++;
      }

      current++;
    }

    return selectedParticipants;
  }

  static double mockWantedValue() {
    return ThreadLocalRandom.current().nextDouble(1800, 2200);
  }

  static void callParticipant(final String name) {
    var tries = 0;
    var keepCalling = true;
    var answered = true;
    do {
      answered = mockCall();
      keepCalling = !answered;
      tries++;
    } while (keepCalling && tries < 3);

    if (answered) {
      System.out.println(name + " answered on " + tries + " tries");
    } else {
      System.out.println(name + " didn't answer on " + tries + " tries");
    }
  }

  static boolean mockCall() {
    return new Random().nextInt(3) == 1;
  }

  static boolean checkParticipant(final double wantedSalary) {
    final var baseSalary = 2000.0;
    if (baseSalary > wantedSalary) {
      System.out.println("Call participant");
      return true;
    } else if (wantedSalary == baseSalary) {
      System.out.println("Call participant with counteroffer");
      return true;
    }

    System.out.println("Waiting for other participants");
    return false;

  }
}
