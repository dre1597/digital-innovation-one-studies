package org.example;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;

import java.util.stream.IntStream;

import static io.reactivex.rxjava3.schedulers.Schedulers.computation;
import static java.util.concurrent.TimeUnit.MILLISECONDS;


public class Backpressure {
  public static void main(String[] args) {
    Flowable.create(
            Backpressure::emit,
            BackpressureStrategy.BUFFER
//          BackpressureStrategy.DROP
//          BackpressureStrategy.ERROR
//          BackpressureStrategy.LATEST
//          BackpressureStrategy.MISSING
        ).observeOn(computation(), true, 2)
        .subscribe(Backpressure::process);
    sleep(10000);
  }

  private static void process(Integer number) {
    System.out.println("processing " + number);
    sleep(1000);
  }

  public static void emit(FlowableEmitter<Integer> emitter) {
    IntStream.rangeClosed(1, 10).forEach(n -> {
      System.out.println("emitting " + n);
      emitter.onNext(n);
      sleep(500);
    });
  }

  private static void sleep(Integer mills) {
    try {
      MILLISECONDS.sleep(mills);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
