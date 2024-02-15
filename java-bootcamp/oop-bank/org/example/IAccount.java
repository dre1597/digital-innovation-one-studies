package org.example;

public interface IAccount {
  void deposit(double amount);

  void withdraw(double amount);

  void transfer(IAccount destination, double amount);
}
