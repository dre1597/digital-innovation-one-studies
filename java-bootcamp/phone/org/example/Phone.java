package org.example;

public class Phone implements Cellphone, MusicPlayer, Browser {

  @Override
  public void viewPage() {
    System.out.println("Viewing page...");
  }

  @Override
  public void addNewTab() {
    System.out.println("Adding new tab...");
  }

  @Override
  public void refreshPage() {
    System.out.println("Refreshing page...");
  }
  
  @Override
  public void call() {
    System.out.println("Calling...");
  }

  @Override
  public void message() {
    System.out.println("Messaging...");
  }

  @Override
  public void answer() {
    System.out.println("Answering...");
  }

  @Override
  public void play() {
    System.out.println("Playing...");
  }

  @Override
  public void pause() {
    System.out.println("Pausing...");
  }

  @Override
  public void selectTrack() {
    System.out.println("Selecting track...");
  }
}
