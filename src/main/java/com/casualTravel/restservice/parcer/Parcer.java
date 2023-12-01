package com.casualTravel.restservice.parcer;

import com.casualTravel.restservice.models.Answer;
import com.casualTravel.restservice.models.Interest;
import com.casualTravel.restservice.models.Place;
import com.casualTravel.restservice.models.Question;
import com.casualTravel.restservice.models.Survey;
import com.casualTravel.restservice.service.InterestService;
import com.casualTravel.restservice.service.PlaceService;
import com.casualTravel.restservice.service.SurveyService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

@Service
public class Parcer {
  private final InterestService interestService;
  private final PlaceService placeService;
  private final SurveyService surveyService;


  public Parcer(InterestService interestService, PlaceService placeService,
      SurveyService surveyService) {
    this.interestService = interestService;
    this.placeService = placeService;
    this.surveyService = surveyService;
  }
  public void parce() {
    try {
      FileInputStream file = new FileInputStream(new File("src/main/resources/base.xlsx"));

      Workbook workbook = WorkbookFactory.create(file);
      List<Place> placeList = new ArrayList<>();
      Interest historic = new Interest("Historical", "Love of history");
      Interest natural = new Interest("Naturialical", "Love of nature");
      Interest artistic = new Interest("Artistic", "Love of artistic");
      Interest entertainment = new Interest("Entertainment", "Love of entertainment");
      interestService.createInterest(historic);
      interestService.createInterest(natural);
      interestService.createInterest(artistic);
      interestService.createInterest(entertainment);
      Sheet sheet = workbook.getSheetAt(0); // Отримання першого аркуша
      int j = 0;
      for (Row row : sheet) {

        if (j <= 1) {
          j++;
          continue;
        }
        Place place = new Place();
        List<String> parts = new ArrayList<>();
        if (!isRowEmpty(row)) {
          for (int i = 1; i < 28; i++) {
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
      for (Place place : placeList) {
        placeService.createPlace(place);
        //System.out.println(place);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    Survey doorArtofTravel = new Survey();
    doorArtofTravel.setSurveyName("Мистецтво Подорожі");
    Survey doorAgeofMysteries = new Survey();
    doorAgeofMysteries.setSurveyName("Епоха Таємниць");
    Survey doorNaturalExperiment = new Survey();
    doorNaturalExperiment.setSurveyName("Симфонія Вражень");
    //
    List<Question> ArtofTravel = new ArrayList<>();
    List<Question> ArtofMysteries = new ArrayList<>();
    List<Question> ArtofExperiment = new ArrayList<>();
    /////
    {
      Question firstArtTravel = new Question();
      firstArtTravel.setText(
          "Ти опиняєшся у великій світлій залі, і перше що бачиш – велике полотно. Що зображено на картині?");

      Answer firstArtFirstAnswer = new Answer();
      firstArtFirstAnswer.setAnswerText("Мрійний пейзаж");
      firstArtFirstAnswer.setQuestion(firstArtTravel);

      Answer firstArtSecondAnswer = new Answer();
      firstArtSecondAnswer.setAnswerText("Портрет з виразною грою кольорів");
      firstArtSecondAnswer.setQuestion(firstArtTravel);

      Answer firstArtThirdAnswer = new Answer();
      firstArtThirdAnswer.setAnswerText("Батальна сцена XVII ст");
      firstArtThirdAnswer.setQuestion(firstArtTravel);

      Answer firstArtFourthAnswer = new Answer();
      firstArtFourthAnswer.setAnswerText("Натюрморт з польових квітів");
      firstArtFourthAnswer.setQuestion(firstArtTravel);

      List<Answer> firstQuestionArtofTravelAnswers = new ArrayList<>();
      firstQuestionArtofTravelAnswers.add(firstArtFirstAnswer);
      firstQuestionArtofTravelAnswers.add(firstArtSecondAnswer);
      firstQuestionArtofTravelAnswers.add(firstArtThirdAnswer);
      firstQuestionArtofTravelAnswers.add(firstArtFourthAnswer);

      firstArtTravel.setAnswerOptions(firstQuestionArtofTravelAnswers);
      ArtofTravel.add(firstArtTravel);
      firstArtTravel.setSurvey(doorArtofTravel);
      ///////
      Question secondArtTravel = new Question();
      secondArtTravel.setText(
          "В середині зали розташована скульптура, що приховує в собі таємницю. Що це за скульптура?");

      Answer secondArtFirstAnswer = new Answer();
      secondArtFirstAnswer.setAnswerText("Витвір стародавнього світу з довгою історією");
      secondArtFirstAnswer.setQuestion(secondArtTravel);

      Answer secondArtSecondAnswer = new Answer();
      secondArtSecondAnswer.setAnswerText(
          "Сучасна абстракція, яка легко адаптується під різні інтерпретації");
      secondArtSecondAnswer.setQuestion(secondArtTravel);

      Answer secondArtThirdAnswer = new Answer();
      secondArtThirdAnswer.setAnswerText("Скульптура людини з вражаючою деталізацією");
      secondArtThirdAnswer.setQuestion(secondArtTravel);

      Answer secondArtFourthAnswer = new Answer();
      secondArtFourthAnswer.setAnswerText("Незвичайна композиція, що привертає увагу");
      secondArtFourthAnswer.setQuestion(secondArtTravel);

      List<Answer> secondQuestionArtofTravelAnswers = new ArrayList<>();
      secondQuestionArtofTravelAnswers.add(secondArtFirstAnswer);
      secondQuestionArtofTravelAnswers.add(secondArtSecondAnswer);
      secondQuestionArtofTravelAnswers.add(secondArtThirdAnswer);
      secondQuestionArtofTravelAnswers.add(secondArtFourthAnswer);

      secondArtTravel.setAnswerOptions(secondQuestionArtofTravelAnswers);
      ArtofTravel.add(secondArtTravel);
      secondArtTravel.setSurvey(doorArtofTravel);
      ////
      Question thirdArtTravel = new Question();
      thirdArtTravel.setText(
          "Біля скульптури стоїть художник, який пропонує намалювати твій портрет. Як його буде намальовано?");

      Answer thirdArtFirstAnswer = new Answer();
      thirdArtFirstAnswer.setAnswerText(
          "Я оберу несподіваний образ та сюжет, що підкреслить мій характер");
      thirdArtFirstAnswer.setQuestion(thirdArtTravel);

      Answer thirdArtSecondAnswer = new Answer();
      thirdArtSecondAnswer.setAnswerText("Довірю вибір стилю картини художнику");
      thirdArtSecondAnswer.setQuestion(thirdArtTravel);

      Answer thirdArtThirdAnswer = new Answer();
      thirdArtThirdAnswer.setAnswerText("Портрет із реалістичним відтворенням моєї зовнішності");
      thirdArtThirdAnswer.setQuestion(thirdArtTravel);

      Answer thirdArtFourthAnswer = new Answer();
      thirdArtFourthAnswer.setAnswerText("Картина у стилі минулих століть");
      thirdArtFourthAnswer.setQuestion(thirdArtTravel);

      List<Answer> thirdQuestionArtofTravelAnswers = new ArrayList<>();
      thirdQuestionArtofTravelAnswers.add(thirdArtFirstAnswer);
      thirdQuestionArtofTravelAnswers.add(thirdArtSecondAnswer);
      thirdQuestionArtofTravelAnswers.add(thirdArtThirdAnswer);
      thirdQuestionArtofTravelAnswers.add(thirdArtFourthAnswer);

      thirdArtTravel.setAnswerOptions(thirdQuestionArtofTravelAnswers);
      ArtofTravel.add(thirdArtTravel);
      thirdArtTravel.setSurvey(doorArtofTravel);
      ///
      Question fourthArtTravel = new Question();
      fourthArtTravel.setText(
          " У іншій частині зали проходить виставка сучасного мистецтва. Яка її тематика?\n");

      Answer fourthArtFirstAnswer = new Answer();
      fourthArtFirstAnswer.setAnswerText("Творчі експерименти сучасних митців");
      fourthArtFirstAnswer.setQuestion(fourthArtTravel);

      Answer fourthArtSecondAnswer = new Answer();
      fourthArtSecondAnswer.setAnswerText("Новаторські техніки у фотографії ");
      fourthArtSecondAnswer.setQuestion(fourthArtTravel);

      Answer fourthArtThirdAnswer = new Answer();
      fourthArtThirdAnswer.setAnswerText("Мультимедійне шоу, що піднімає суспільно-важливі теми");
      fourthArtThirdAnswer.setQuestion(fourthArtTravel);

      Answer fourthArtFourthAnswer = new Answer();
      fourthArtFourthAnswer.setAnswerText("Великі митці минулого");
      fourthArtFourthAnswer.setQuestion(fourthArtTravel);

      List<Answer> fourthQuestionArtofTravelAnswers = new ArrayList<>();
      fourthQuestionArtofTravelAnswers.add(fourthArtFirstAnswer);
      fourthQuestionArtofTravelAnswers.add(fourthArtSecondAnswer);
      fourthQuestionArtofTravelAnswers.add(fourthArtThirdAnswer);
      fourthQuestionArtofTravelAnswers.add(fourthArtFourthAnswer);

      fourthArtTravel.setAnswerOptions(fourthQuestionArtofTravelAnswers);
      ArtofTravel.add(fourthArtTravel);
      fourthArtTravel.setSurvey(doorArtofTravel);

      /////
    }
    {
      Question firstMysteries = new Question();
      firstMysteries.setText("Ти опиняєшся на острові серед океану. Що ти бачиш перед собою?");

      Answer firstMysteriesFirstAnswer = new Answer();
      firstMysteriesFirstAnswer.setAnswerText("Гори, оточені хмарами та водоспадами");
      firstMysteriesFirstAnswer.setQuestion(firstMysteries);

      Answer firstMysteriesSecondAnswer = new Answer();
      firstMysteriesSecondAnswer.setAnswerText(
          "Ліс, наповнений загадковою атмосферою та екзотичними рослинами ");
      firstMysteriesSecondAnswer.setQuestion(firstMysteries);

      Answer firstMysteriesThirdAnswer = new Answer();
      firstMysteriesThirdAnswer.setAnswerText(
          "Берег з білосніжним піском та глибокими лазуревими водами");
      firstMysteriesThirdAnswer.setQuestion(firstMysteries);

      Answer firstMysteriesFourthAnswer = new Answer();
      firstMysteriesFourthAnswer.setAnswerText(
          "Екзотичний кораловий риф з багатою морською флорою та фауною");
      firstMysteriesFourthAnswer.setQuestion(firstMysteries);

      List<Answer> firstQuestionArtofMysteriesAnswers = new ArrayList<>();
      firstQuestionArtofMysteriesAnswers.add(firstMysteriesFirstAnswer);
      firstQuestionArtofMysteriesAnswers.add(firstMysteriesSecondAnswer);
      firstQuestionArtofMysteriesAnswers.add(firstMysteriesThirdAnswer);
      firstQuestionArtofMysteriesAnswers.add(firstMysteriesFourthAnswer);

      firstMysteries.setAnswerOptions(firstQuestionArtofMysteriesAnswers);
      ArtofMysteries.add(firstMysteries);
      firstMysteries.setSurvey(doorAgeofMysteries);
      ///
      Question secondMysteries = new Question();
      secondMysteries.setText("Що ти зробиш найпершим?");

      Answer secondMysteriesFirstAnswer = new Answer();
      secondMysteriesFirstAnswer.setAnswerText("Піднімусь на вершину гори для медитації");
      secondMysteriesFirstAnswer.setQuestion(secondMysteries);

      Answer secondMysteriesSecondAnswer = new Answer();
      secondMysteriesSecondAnswer.setAnswerText(
          "Прогуляюсь по лісу, слухаючи пісні птахів та шум природи");
      secondMysteriesSecondAnswer.setQuestion(secondMysteries);

      Answer secondMysteriesThirdAnswer = new Answer();
      secondMysteriesThirdAnswer.setAnswerText(
          "Поплаваю серед коралового рифу та дивитимусь на барвистих рибок ");
      secondMysteriesThirdAnswer.setQuestion(secondMysteries);

      Answer secondMysteriesFourthAnswer = new Answer();
      secondMysteriesFourthAnswer.setAnswerText(
          "Познайомлюсь з місцевими жителями та їх культурою");
      secondMysteriesFourthAnswer.setQuestion(secondMysteries);

      List<Answer> secondQuestionArtofMysteriesAnswers = new ArrayList<>();
      secondQuestionArtofMysteriesAnswers.add(secondMysteriesFirstAnswer);
      secondQuestionArtofMysteriesAnswers.add(secondMysteriesSecondAnswer);
      secondQuestionArtofMysteriesAnswers.add(secondMysteriesThirdAnswer);
      secondQuestionArtofMysteriesAnswers.add(secondMysteriesFourthAnswer);

      secondMysteries.setAnswerOptions(secondQuestionArtofMysteriesAnswers);
      ArtofMysteries.add(secondMysteries);
      secondMysteries.setSurvey(doorAgeofMysteries);
      ///
      Question thirdMysteries = new Question();
      thirdMysteries.setText("Твою увагу привертають тварини, що живуть на острові. Хто вони?");

      Answer thirdMysteriesFirstAnswer = new Answer();
      thirdMysteriesFirstAnswer.setAnswerText(
          "Тропічний папуги, що заповнюють небо своїми яскравими кольорами");
      thirdMysteriesFirstAnswer.setQuestion(thirdMysteries);

      Answer thirdMysteriesSecondAnswer = new Answer();
      thirdMysteriesSecondAnswer.setAnswerText("Мавпи, які розважають своїми витівками");
      thirdMysteriesSecondAnswer.setQuestion(thirdMysteries);

      Answer thirdMysteriesThirdAnswer = new Answer();
      thirdMysteriesThirdAnswer.setAnswerText(
          "Морські черепахи, що виходять на берег для відкладання яєць");
      thirdMysteriesThirdAnswer.setQuestion(thirdMysteries);

      Answer thirdMysteriesFourthAnswer = new Answer();
      thirdMysteriesFourthAnswer.setAnswerText("Ігуани, що сидять на гілках");
      thirdMysteriesFourthAnswer.setQuestion(thirdMysteries);

      List<Answer> thirdQuestionArtofMysteriesAnswers = new ArrayList<>();
      thirdQuestionArtofMysteriesAnswers.add(thirdMysteriesFirstAnswer);
      thirdQuestionArtofMysteriesAnswers.add(thirdMysteriesSecondAnswer);
      thirdQuestionArtofMysteriesAnswers.add(thirdMysteriesThirdAnswer);
      thirdQuestionArtofMysteriesAnswers.add(thirdMysteriesFourthAnswer);

      thirdMysteries.setAnswerOptions(thirdQuestionArtofMysteriesAnswers);
      ArtofMysteries.add(thirdMysteries);
      thirdMysteries.setSurvey(doorAgeofMysteries);
      ///
      Question fourthMysteries = new Question();
      fourthMysteries.setText(
          "Тобі пропонують стати частиною місцевого екологічного проекту. Що це за проект?");

      Answer fourthMysteriesFirstAnswer = new Answer();
      fourthMysteriesFirstAnswer.setAnswerText(
          "Вивчення та маркування птахів для моніторингу популяції");
      fourthMysteriesFirstAnswer.setQuestion(fourthMysteries);

      Answer fourthMysteriesSecondAnswer = new Answer();
      fourthMysteriesSecondAnswer.setAnswerText(
          "Організація чистки берегу від пластикових відходів та сміття");
      fourthMysteriesSecondAnswer.setQuestion(fourthMysteries);

      Answer fourthMysteriesThirdAnswer = new Answer();
      fourthMysteriesThirdAnswer.setAnswerText(
          "Спостереження за ігуанами та створення бази даних про їхні місця мешкання");
      fourthMysteriesThirdAnswer.setQuestion(fourthMysteries);

      Answer fourthMysteriesFourthAnswer = new Answer();
      fourthMysteriesFourthAnswer.setAnswerText(
          "Розробка інформаційної кампанії з освіти місцевого населення щодо екосистем острова");
      fourthMysteriesFourthAnswer.setQuestion(fourthMysteries);

      List<Answer> fourthQuestionArtofMysteriesAnswers = new ArrayList<>();
      fourthQuestionArtofMysteriesAnswers.add(fourthMysteriesFirstAnswer);
      fourthQuestionArtofMysteriesAnswers.add(fourthMysteriesSecondAnswer);
      fourthQuestionArtofMysteriesAnswers.add(fourthMysteriesThirdAnswer);
      fourthQuestionArtofMysteriesAnswers.add(fourthMysteriesFourthAnswer);

      fourthMysteries.setAnswerOptions(fourthQuestionArtofMysteriesAnswers);
      ArtofMysteries.add(fourthMysteries);
      fourthMysteries.setSurvey(doorAgeofMysteries);

      /////
    }
    {
      Question firstExperiment = new Question();
      firstExperiment.setText(
          "Ти опиняєшся на святкуванні, де зібралися персонажі з різних казкових світів. Хто стоїть поруч з тобою?");

      Answer firstExperimentFirstAnswer = new Answer();
      firstExperimentFirstAnswer.setAnswerText("Чарівники з “Гаррі Поттера”");
      firstExperimentFirstAnswer.setQuestion(firstExperiment);

      Answer firstExperimentSecondAnswer = new Answer();
      firstExperimentSecondAnswer.setAnswerText("Диваки з \"Аліси в країні чудес\" ");
      firstExperimentSecondAnswer.setQuestion(firstExperiment);

      Answer firstExperimentThirdAnswer = new Answer();
      firstExperimentThirdAnswer.setAnswerText("Казкові істоти зі світу \"Пітера Пена\"");
      firstExperimentThirdAnswer.setQuestion(firstExperiment);

      Answer firstExperimentFourthAnswer = new Answer();
      firstExperimentFourthAnswer.setAnswerText("Веселуни зі “Шрека”");
      firstExperimentFourthAnswer.setQuestion(firstExperiment);

      List<Answer> firstQuestionArtofExperimentAnswers = new ArrayList<>();
      firstQuestionArtofExperimentAnswers.add(firstExperimentFirstAnswer);
      firstQuestionArtofExperimentAnswers.add(firstExperimentSecondAnswer);
      firstQuestionArtofExperimentAnswers.add(firstExperimentThirdAnswer);
      firstQuestionArtofExperimentAnswers.add(firstExperimentFourthAnswer);

      firstExperiment.setAnswerOptions(firstQuestionArtofExperimentAnswers);
      ArtofExperiment.add(firstExperiment);
      firstExperiment.setSurvey(doorNaturalExperiment);
      ///
      Question secondExperiment = new Question();
      secondExperiment.setText("На святкуванні ти знаходиш нового казкового друга, хто це?");

      Answer secondExperimentFirstAnswer = new Answer();
      secondExperimentFirstAnswer.setAnswerText("Веселий ельф, який завжди приносить посмішки");
      secondExperimentFirstAnswer.setQuestion(secondExperiment);

      Answer secondExperimentSecondAnswer = new Answer();
      secondExperimentSecondAnswer.setAnswerText("Дракон, який виявляється вірним порадником");
      secondExperimentSecondAnswer.setQuestion(secondExperiment);

      Answer secondExperimentThirdAnswer = new Answer();
      secondExperimentThirdAnswer.setAnswerText("Магічний єдиноріг, який виконує бажання");
      secondExperimentThirdAnswer.setQuestion(secondExperiment);

      Answer secondExperimentFourthAnswer = new Answer();
      secondExperimentFourthAnswer.setAnswerText("Мудра фея, що володіє заклинаннями добра");
      secondExperimentFourthAnswer.setQuestion(secondExperiment);

      List<Answer> secondQuestionArtofExperimentAnswers = new ArrayList<>();
      secondQuestionArtofExperimentAnswers.add(secondExperimentFirstAnswer);
      secondQuestionArtofExperimentAnswers.add(secondExperimentSecondAnswer);
      secondQuestionArtofExperimentAnswers.add(secondExperimentThirdAnswer);
      secondQuestionArtofExperimentAnswers.add(secondExperimentFourthAnswer);

      secondExperiment.setAnswerOptions(secondQuestionArtofExperimentAnswers);
      ArtofExperiment.add(secondExperiment);
      secondExperiment.setSurvey(doorNaturalExperiment);
      ///
      Question thirdExperiment = new Question();
      thirdExperiment.setText(
          "На чарівному фуршеті постійно з’являються незвичні для тебе страви. Що ти спробуєш найпершим?");

      Answer thirdExperimentFirstAnswer = new Answer();
      thirdExperimentFirstAnswer.setAnswerText("Десерт, який ніколи не закінчується");
      thirdExperimentFirstAnswer.setQuestion(thirdExperiment);

      Answer thirdExperimentSecondAnswer = new Answer();
      thirdExperimentSecondAnswer.setAnswerText(
          "Магічний еліксир, що змінює смак їжі за вашим бажанням");
      thirdExperimentSecondAnswer.setQuestion(thirdExperiment);

      Answer thirdExperimentThirdAnswer = new Answer();
      thirdExperimentThirdAnswer.setAnswerText(
          "Таємничий напій, який робить вас тимчасово невидимими");
      thirdExperimentThirdAnswer.setQuestion(thirdExperiment);

      Answer thirdExperimentFourthAnswer = new Answer();
      thirdExperimentFourthAnswer.setAnswerText(
          "Коктейль з екзотичних фруктів та магічних інгредієнтів");
      thirdExperimentFourthAnswer.setQuestion(thirdExperiment);

      List<Answer> thirdQuestionArtofExperimentAnswers = new ArrayList<>();
      thirdQuestionArtofExperimentAnswers.add(thirdExperimentFirstAnswer);
      thirdQuestionArtofExperimentAnswers.add(thirdExperimentSecondAnswer);
      thirdQuestionArtofExperimentAnswers.add(thirdExperimentThirdAnswer);
      thirdQuestionArtofExperimentAnswers.add(thirdExperimentFourthAnswer);

      thirdExperiment.setAnswerOptions(thirdQuestionArtofExperimentAnswers);
      ArtofExperiment.add(thirdExperiment);
      thirdExperiment.setSurvey(doorNaturalExperiment);
      ///
      Question fourthExperiment = new Question();
      fourthExperiment.setText("Ти вирішуєте стати частиною казкового театру. Обери свою роль:");

      Answer fourthExperimentFirstAnswer = new Answer();
      fourthExperimentFirstAnswer.setAnswerText(
          "Головний герой, що подолав усі труднощі та переміг зло");
      fourthExperimentFirstAnswer.setQuestion(fourthExperiment);

      Answer fourthExperimentSecondAnswer = new Answer();
      fourthExperimentSecondAnswer.setAnswerText(
          "Загадковий антигерой із складною долею та своєю правдою");
      fourthExperimentSecondAnswer.setQuestion(fourthExperiment);

      Answer fourthExperimentThirdAnswer = new Answer();
      fourthExperimentThirdAnswer.setAnswerText(
          "Комічна персона, що розсмішує аудиторію своїми пригодами");
      fourthExperimentThirdAnswer.setQuestion(fourthExperiment);

      Answer fourthExperimentFourthAnswer = new Answer();
      fourthExperimentFourthAnswer.setAnswerText(
          "Мудрець, що допомагає героям знаходити вихід зі складних ситуацій");
      fourthExperimentFourthAnswer.setQuestion(fourthExperiment);

      List<Answer> fourthQuestionArtofExperimentAnswers = new ArrayList<>();
      fourthQuestionArtofExperimentAnswers.add(fourthExperimentFirstAnswer);
      fourthQuestionArtofExperimentAnswers.add(fourthExperimentSecondAnswer);
      fourthQuestionArtofExperimentAnswers.add(fourthExperimentThirdAnswer);
      fourthQuestionArtofExperimentAnswers.add(fourthExperimentFourthAnswer);

      fourthExperiment.setAnswerOptions(fourthQuestionArtofExperimentAnswers);
      ArtofExperiment.add(fourthExperiment);
      fourthExperiment.setSurvey(doorNaturalExperiment);
    }
    doorArtofTravel.setQuestions(ArtofTravel);
   doorAgeofMysteries.setQuestions(ArtofMysteries);
    doorNaturalExperiment.setQuestions(ArtofExperiment);
    surveyService.createSurvey(doorArtofTravel);
    surveyService.createSurvey(doorAgeofMysteries);
    surveyService.createSurvey(doorNaturalExperiment);
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