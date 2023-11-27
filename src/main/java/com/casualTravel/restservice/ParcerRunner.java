package com.casualTravel.restservice;

import com.casualTravel.restservice.parcer.Parcer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ParcerRunner implements CommandLineRunner {

  private final Parcer parcer;

  public ParcerRunner(Parcer parcer) {
    this.parcer = parcer;
  }

  @Override
  public void run(String... args) {
    parcer.parce();
  }
}