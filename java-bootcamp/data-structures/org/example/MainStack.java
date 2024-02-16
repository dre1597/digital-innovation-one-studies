package org.example;

public class MainStack {
  public static void main(String[] args) {
    final var firstNode = new Node<>("1");
    final var secondNode = new Node<>("2");
    final var thirdNode = new Node<>("3");

    System.out.println(firstNode);
    System.out.println(secondNode);
    System.out.println(thirdNode);

    final var stack = new Stack<String>();
    stack.push(firstNode);
    stack.push(secondNode);
    stack.push(thirdNode);

    System.out.println(stack);

    stack.pop();
    System.out.println(stack);

    System.out.println(stack.top());

    stack.push(new Node<>("4"));
    System.out.println(stack);
  }
}
