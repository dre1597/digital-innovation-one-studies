package org.example;

public class MainQueue {
  public static void main(String[] args) {
    final var firstNode = new Node<>("1");
    final var secondNode = new Node<>("2");
    final var thirdNode = new Node<>("3");

    System.out.println(firstNode);
    System.out.println(secondNode);
    System.out.println(thirdNode);

    final var queue = new Queue<String>();
    queue.enqueue(firstNode);
    queue.enqueue(secondNode);
    queue.enqueue(thirdNode);

    System.out.println(queue);

    queue.dequeue();
    System.out.println(queue);

    System.out.println(queue.first());

    queue.enqueue(new Node<>("4"));
    System.out.println(queue);
  }
}
