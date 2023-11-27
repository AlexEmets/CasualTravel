package com.casualTravel.restservice.parcer;

import com.casualTravel.restservice.models.Interest;
import com.casualTravel.restservice.models.Place;
import com.casualTravel.restservice.service.InterestService;
import com.casualTravel.restservice.service.PlaceService;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Parcer {
  private final InterestService interestService;
  private final PlaceService placeService;

  public Parcer(InterestService interestService, PlaceService placeService) {
    this.interestService = interestService;
    this.placeService = placeService;
  }
  public void parce() {
    try {
      FileInputStream file = new FileInputStream(new File("src/main/resources/base.xlsx"));

      Workbook workbook = WorkbookFactory.create(file);
      List<Place> placeList = new ArrayList<>();
      Interest historic = new Interest("Historical","Love of history");
      Interest natural = new Interest("Naturialical","Love of nature");
      Interest artistic = new Interest("Artistic","Love of artistic");
      Interest entertainment = new Interest("Entertainment","Love of entertainment");
      interestService.createInterest(historic);
      interestService.createInterest(natural);
      interestService.createInterest(artistic);
      interestService.createInterest(entertainment);
      Sheet sheet = workbook.getSheetAt(0); // Отримання першого аркуша
      int j = 0;
      for (Row row : sheet) {

        if(j <=1)
        {
          j++;
          continue;
        }
        Place place = new Place();
        List<String> parts = new ArrayList<>();
        if(!isRowEmpty(row)){
          for (int i = 1; i < 28;i++) {
            parts.add(String.valueOf(row.getCell(i)));
          }
          place.setPlaceName(parts.get(0));
          place.setPositionX(parts.get(1));
          place.setPositionY(parts.get(2));
          place.addInterest(historic, Double.valueOf(parts.get(3)));
          place.addInterest(natural, Double.valueOf(parts.get(4)));
          place.addInterest(artistic, Double.valueOf(parts.get(5)));
          place.addInterest(entertainment, Double.valueOf(parts.get(6)));
          place.setVisitTime((int) (Double.parseDouble(parts.get(7))));
          place.setVisitCost(Float.valueOf(parts.get(8)));
          place.setChildCost(Float.valueOf(parts.get(9)));
          place.setPreferentialCost(Float.valueOf(parts.get(10)));
          place.setSports(Boolean.valueOf(parts.get(11)));
          place.setOverview(Boolean.valueOf(parts.get(12)));
          place.setPassiveAction(Boolean.valueOf(parts.get(13)));
          place.setEducationalAction(Boolean.valueOf(parts.get(14)));
          place.setOutdoorAction(Boolean.valueOf(parts.get(15)));
          place.setInvalid(Boolean.valueOf(parts.get(16)));
          place.setFamily(Boolean.valueOf(parts.get(17)));
          place.setMustVisit(Boolean.valueOf(parts.get(18)));
          place.setMuseum(Boolean.valueOf(parts.get(19)));
          place.setArchitecturalMonument(Boolean.valueOf(parts.get(20)));
          place.setSculptur(Boolean.valueOf(parts.get(21)));
          place.setPark(Boolean.valueOf(parts.get(22)));
          place.setNaturalNationObject(Boolean.valueOf(parts.get(23)));
          place.setActiveRecreationArea(Boolean.valueOf(parts.get(24)));
          place.setAlley(Boolean.valueOf(parts.get(25)));
          place.setObservationDeck(Boolean.parseBoolean(parts.get(26)));

          placeList.add(place);
      }
        parts.clear();
        place = null;
      workbook.close();
      file.close();
    }
      for (Place place: placeList) {
        placeService.createPlace(place);
        //System.out.println(place);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  private static boolean isRowEmpty(Row row) {
    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
      Cell cell = row.getCell(c);
      if (cell != null && cell.getCellType() != CellType.BLANK) {
        return false;
      }
    }
    return true;
  }
}