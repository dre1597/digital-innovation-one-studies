package org.example;

public class Queue<T> {
  private Node<T> frontRef;

  public Queue() {
    this.frontRef = null;
  }

  public boolean isEmpty() {
    return this.frontRef == null;
  }

  public T first() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return frontRef.getData();
  }

  public void enqueue(final Node<T> newNode) {
    if (isEmpty()) {
      frontRef = newNode;
    } else {
      Node<T> current = frontRef;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(newNode);
    }
  }

  public T dequeue() {
    if (isEmpty()) {
      return null;
    }
    var data = frontRef.getData();
    frontRef = frontRef.getNext();
    return data;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    Node<T> current = frontRef;
    while (current != null) {
      sb.append(current.getData());
      if (current.getNext() != null) {
        sb.append(", ");
      }
      current = current.getNext();
    }
    sb.append("]");
    return sb.toString();
  }
}
