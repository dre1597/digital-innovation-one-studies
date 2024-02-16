package org.example;

public class Node<T> {
  private T data;
  private Node<T> next;

  public Node(final T data) {
    this.next = null;
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void setData(final T data) {
    this.data = data;
  }

  public Node<T> getNext() {
    return next;
  }

  public void setNext(final Node<T> next) {
    this.next = next;
  }

  @Override
  public String toString() {
    return "Node{" +
        "data='" + data + '\'' +
        '}';
  }
}
