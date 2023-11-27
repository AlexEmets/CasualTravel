package com.casualTravel.restservice;
import java.io.File;

import com.casualTravel.restservice.parcer.Parcer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ParcerRunner implements CommandLineRunner {

  private final Parcer parcer;
  private final String flagFileName = "parcer_flag.txt";

  public ParcerRunner(Parcer parcer) {
    this.parcer = parcer;
  }

  @Override
  public void run(String... args) {
    File flagFile = new File(getProjectRootDirectory() + File.separator + flagFileName);
    if (!flagFile.exists()) {
      parcer.parce();
      createFlagFile(flagFile);
    }
  }

  private String getProjectRootDirectory() {
    try {
      return new ClassPathResource("").getFile().getAbsolutePath();
    } catch (Exception e) {
      System.out.println("Error getting project root directory: " + e.getMessage());
    }
    return "";
  }

  private void createFlagFile(File flagFile) {
    try {
      if (flagFile.createNewFile()) {
        System.out.println("Flag file created: " + flagFile.getAbsolutePath());
      }
    } catch (Exception e) {
      System.out.println("Error creating flag file: " + e.getMessage());
    }
  }
}
