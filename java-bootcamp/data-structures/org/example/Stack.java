package org.example;

public class Stack<T> {
  private Node<T> topRef;

  public Stack() {
    this.topRef = null;
  }

  public boolean isEmpty() {
    return topRef == null;
  }

  public Node<T> top() {
    return topRef;
  }

  public void push(final Node<T> newNode) {
    Node<T> temp = topRef;
    topRef = newNode;
    topRef.setNext(temp);
  }

  public Node<T> pop() {
    if (this.isEmpty()) {
      return null;
    }

    Node<T> temp = topRef;
    topRef = topRef.getNext();
    return temp;
  }

  @Override
  public String toString() {
    final var sb = new StringBuilder();

    if (this.isEmpty()) {
      return sb.toString();
    }

    Node<T> temp = topRef;
    while (temp != null) {
      sb.append(temp.getData());
      temp = temp.getNext();
    }

    return sb.toString();
  }
}
