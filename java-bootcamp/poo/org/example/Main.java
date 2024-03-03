package org.example;

import org.example.domain.Bootcamp;
import org.example.domain.Course;
import org.example.domain.Dev;
import org.example.domain.Mentoring;

import java.time.LocalDate;

public class Main {
  public static void main(String[] args) {
    Course course = new Course();
    course.setTitle("Java");
    course.setDescription("Java course");
    course.setDuration(5.0);

    Course courseTwo = new Course();
    courseTwo.setTitle("Javascript");
    courseTwo.setDescription("Javascript course");
    courseTwo.setDuration(4.0);

    Mentoring mentoring = new Mentoring();
    mentoring.setTitle("Mentoring");
    mentoring.setDescription("Mentoring course");
    mentoring.setDate(LocalDate.now());

    Bootcamp bootcamp = new Bootcamp();
    bootcamp.setName("Bootcamp");
    bootcamp.setDescription("Bootcamp course");
    bootcamp.getContents().add(course);
    bootcamp.getContents().add(courseTwo);
    bootcamp.getContents().add(mentoring);

    Dev dev = new Dev();
    dev.setName("Dev");
    dev.subscribeBootcamp(bootcamp);
    dev.toProgress();
    dev.toProgress();
    System.out.println(dev.calculateTotalExp());
  }
}
