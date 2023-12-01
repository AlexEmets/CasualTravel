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

      Survey doorArtofTravel = new Survey();
      doorArtofTravel.setSurveyName("Мистецтво Подорожі");
      Survey doorAgeofMysteries = new Survey();
      doorAgeofMysteries.setSurveyName("Епоха Таємниць");
      Survey doorImpresion = new Survey();
      doorImpresion.setSurveyName("Природний Експеримент");
      Survey doorNaturalExperiment = new Survey();
      doorNaturalExperiment.setSurveyName("Симфонія Вражень");

      //
      List<Question> ArtofTravel = new ArrayList<>();
      List<Question> ArtofMysteries = new ArrayList<>();
      List<Question> ArtofExperiment = new ArrayList<>();
      List<Question> ArtofImpression = new ArrayList<>();
      /////
      {
        Question firstArtTravel = new Question();
        firstArtTravel.setText(
            "Ти опиняєшся у великій світлій залі, і перше що бачиш – велике полотно. Що зображено на картині?");

        Answer firstArtFirstAnswer = new Answer();
        firstArtFirstAnswer.setAnswerText("Мрійний пейзаж");
        firstArtFirstAnswer.setQuestion(firstArtTravel);
        firstArtFirstAnswer.setInterest(natural);

        Answer firstArtSecondAnswer = new Answer();
        firstArtSecondAnswer.setAnswerText("Портрет з виразною грою кольорів");
        firstArtSecondAnswer.setQuestion(firstArtTravel);
        firstArtSecondAnswer.setInterest(artistic);

        Answer firstArtThirdAnswer = new Answer();
        firstArtThirdAnswer.setAnswerText("Батальна сцена XVII ст");
        firstArtThirdAnswer.setQuestion(firstArtTravel);
        firstArtThirdAnswer.setInterest(historic);

        Answer firstArtFourthAnswer = new Answer();
        firstArtFourthAnswer.setAnswerText("Натюрморт з польових квітів");
        firstArtFourthAnswer.setQuestion(firstArtTravel);
        firstArtFourthAnswer.setInterest(entertainment);

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
        secondArtFirstAnswer.setInterest(historic);

        Answer secondArtSecondAnswer = new Answer();
        secondArtSecondAnswer.setAnswerText(
            "Сучасна абстракція, яка легко адаптується під різні інтерпретації");
        secondArtSecondAnswer.setQuestion(secondArtTravel);
        secondArtSecondAnswer.setInterest(artistic);

        Answer secondArtThirdAnswer = new Answer();
        secondArtThirdAnswer.setAnswerText("Скульптура людини з вражаючою деталізацією");
        secondArtThirdAnswer.setQuestion(secondArtTravel);
        secondArtThirdAnswer.setInterest(natural);

        Answer secondArtFourthAnswer = new Answer();
        secondArtFourthAnswer.setAnswerText("Незвичайна композиція, що привертає увагу");
        secondArtFourthAnswer.setQuestion(secondArtTravel);
        secondArtFourthAnswer.setInterest(entertainment);

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
        thirdArtFirstAnswer.setInterest(artistic);

        Answer thirdArtSecondAnswer = new Answer();
        thirdArtSecondAnswer.setAnswerText("Довірю вибір стилю картини художнику");
        thirdArtSecondAnswer.setQuestion(thirdArtTravel);
        thirdArtSecondAnswer.setInterest(entertainment);

        Answer thirdArtThirdAnswer = new Answer();
        thirdArtThirdAnswer.setAnswerText("Портрет із реалістичним відтворенням моєї зовнішності");
        thirdArtThirdAnswer.setQuestion(thirdArtTravel);
        thirdArtThirdAnswer.setInterest(natural);

        Answer thirdArtFourthAnswer = new Answer();
        thirdArtFourthAnswer.setAnswerText("Картина у стилі минулих століть");
        thirdArtFourthAnswer.setQuestion(thirdArtTravel);
        thirdArtFourthAnswer.setInterest(historic);

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
        fourthArtFirstAnswer.setInterest(entertainment);

        Answer fourthArtSecondAnswer = new Answer();
        fourthArtSecondAnswer.setAnswerText("Новаторські техніки у фотографії ");
        fourthArtSecondAnswer.setQuestion(fourthArtTravel);
        fourthArtSecondAnswer.setInterest(artistic);

        Answer fourthArtThirdAnswer = new Answer();
        fourthArtThirdAnswer.setAnswerText("Мультимедійне шоу, що піднімає суспільно-важливі теми");
        fourthArtThirdAnswer.setQuestion(fourthArtTravel);
        fourthArtThirdAnswer.setInterest(natural);

        Answer fourthArtFourthAnswer = new Answer();
        fourthArtFourthAnswer.setAnswerText("Великі митці минулого");
        fourthArtFourthAnswer.setQuestion(fourthArtTravel);
        fourthArtFourthAnswer.setInterest(historic);

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
        firstMysteries.setText("Ти опиняєшся в кабінеті дослідника світових таємниць. На столі серед купи сувоїв лежить “Карта Часу”. Куди б ти відправився?");

        Answer firstMysteriesFirstAnswer = new Answer();
        firstMysteriesFirstAnswer.setAnswerText("Середньовіччя, до лицарів та принцес ");
        firstMysteriesFirstAnswer.setQuestion(firstMysteries);
        firstMysteriesFirstAnswer.setInterest(entertainment);

        Answer firstMysteriesSecondAnswer = new Answer();
        firstMysteriesSecondAnswer.setAnswerText("Ренесанс, з його вишуканою культурою та мистецтвом");
        firstMysteriesSecondAnswer.setQuestion(firstMysteries);
        firstMysteriesSecondAnswer.setInterest(artistic);

        Answer firstMysteriesThirdAnswer = new Answer();
        firstMysteriesThirdAnswer.setAnswerText(
            "XX століття, період інтриг та кардинальних змін");
        firstMysteriesThirdAnswer.setQuestion(firstMysteries);
        firstMysteriesThirdAnswer.setInterest(historic);

        Answer firstMysteriesFourthAnswer = new Answer();
        firstMysteriesFourthAnswer.setAnswerText(
            "Епоха Вікінгів та їх великих відкриттів");
        firstMysteriesFourthAnswer.setQuestion(firstMysteries);
        firstMysteriesFourthAnswer.setInterest(natural);

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
        secondMysteries.setText("Поряд з картою лежить артефакт. Що це?");

        Answer secondMysteriesFirstAnswer = new Answer();
        secondMysteriesFirstAnswer.setAnswerText("Давній рукопис із загадковими написами");
        secondMysteriesFirstAnswer.setQuestion(secondMysteries);
        secondMysteriesFirstAnswer.setInterest(entertainment);

        Answer secondMysteriesSecondAnswer = new Answer();
        secondMysteriesSecondAnswer.setAnswerText(
            "Скельце з часів Ренесансу з вишуканим орнаментом");
        secondMysteriesSecondAnswer.setQuestion(secondMysteries);
        secondMysteriesSecondAnswer.setInterest(artistic);

        Answer secondMysteriesThirdAnswer = new Answer();
        secondMysteriesThirdAnswer.setAnswerText(
            "Реліквія із середньовічного культу");
        secondMysteriesThirdAnswer.setQuestion(secondMysteries);
        secondMysteriesThirdAnswer.setInterest(historic);

        Answer secondMysteriesFourthAnswer = new Answer();
        secondMysteriesFourthAnswer.setAnswerText(
            "Твори відомих філософів");
        secondMysteriesFourthAnswer.setQuestion(secondMysteries);
        secondMysteriesFourthAnswer.setInterest(natural);


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
        thirdMysteries.setText("У кабінет заходить дослідник. Він запрошує тебе на карнавал. Обери стиль та епоху свого образу:");

        Answer thirdMysteriesFirstAnswer = new Answer();
        thirdMysteriesFirstAnswer.setAnswerText(
            "Вишуканий стиль 19 століття");
        thirdMysteriesFirstAnswer.setQuestion(thirdMysteries);
        thirdMysteriesFirstAnswer.setInterest(artistic);

        Answer thirdMysteriesSecondAnswer = new Answer();
        thirdMysteriesSecondAnswer.setAnswerText("Елегантний образ рок-н-ролу з 50-х років минулого століття");
        thirdMysteriesSecondAnswer.setQuestion(thirdMysteries);
        thirdMysteriesSecondAnswer.setInterest(entertainment);

        Answer thirdMysteriesThirdAnswer = new Answer();
        thirdMysteriesThirdAnswer.setAnswerText(
            "Самурайська зброя та одяг епохи Старого Японського періоду");
        thirdMysteriesThirdAnswer.setQuestion(thirdMysteries);
        thirdMysteriesThirdAnswer.setInterest(historic);

        Answer thirdMysteriesFourthAnswer = new Answer();
        thirdMysteriesFourthAnswer.setAnswerText("Епічний образ відважного мисливця середньовіччя");
        thirdMysteriesFourthAnswer.setQuestion(thirdMysteries);
        thirdMysteriesFourthAnswer.setInterest(natural);

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
            " В знак вашого знайомства він дарує тобі книгу. Що це за книга?");

        Answer fourthMysteriesFirstAnswer = new Answer();
        fourthMysteriesFirstAnswer.setAnswerText(
            "Військова історія та бойові мистецтва");
        fourthMysteriesFirstAnswer.setQuestion(fourthMysteries);
        fourthMysteriesFirstAnswer.setInterest(historic);


        Answer fourthMysteriesSecondAnswer = new Answer();
        fourthMysteriesSecondAnswer.setAnswerText(
            "Історія розвитку мов та писемності");
        fourthMysteriesSecondAnswer.setQuestion(fourthMysteries);
        fourthMysteriesSecondAnswer.setInterest(artistic);

        Answer fourthMysteriesThirdAnswer = new Answer();
        fourthMysteriesThirdAnswer.setAnswerText(
            "Життя та культура людей у Середньовіччі");
        fourthMysteriesThirdAnswer.setQuestion(fourthMysteries);
        fourthMysteriesThirdAnswer.setInterest(entertainment);

        Answer fourthMysteriesFourthAnswer = new Answer();
        fourthMysteriesFourthAnswer.setAnswerText(
            "Великі динозаври та історія їхнього виникнення ");
        fourthMysteriesFourthAnswer.setQuestion(fourthMysteries);
        fourthMysteriesFourthAnswer.setInterest(natural);

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
        firstExperimentFirstAnswer.setInterest(historic);

        Answer firstExperimentSecondAnswer = new Answer();
        firstExperimentSecondAnswer.setAnswerText("Диваки з \"Аліси в країні чудес\" ");
        firstExperimentSecondAnswer.setQuestion(firstExperiment);
        firstExperimentSecondAnswer.setInterest(artistic);

        Answer firstExperimentThirdAnswer = new Answer();
        firstExperimentThirdAnswer.setAnswerText("Казкові істоти зі світу \"Пітера Пена\"");
        firstExperimentThirdAnswer.setQuestion(firstExperiment);
        firstExperimentThirdAnswer.setInterest(natural);

        Answer firstExperimentFourthAnswer = new Answer();
        firstExperimentFourthAnswer.setAnswerText("Веселуни зі “Шрека”");
        firstExperimentFourthAnswer.setQuestion(firstExperiment);
        firstExperimentFourthAnswer.setInterest(entertainment);

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
        secondExperimentFirstAnswer.setInterest(entertainment);

        Answer secondExperimentSecondAnswer = new Answer();
        secondExperimentSecondAnswer.setAnswerText("Дракон, який виявляється вірним порадником");
        secondExperimentSecondAnswer.setQuestion(secondExperiment);
        secondExperimentSecondAnswer.setInterest(historic);

        Answer secondExperimentThirdAnswer = new Answer();
        secondExperimentThirdAnswer.setAnswerText("Магічний єдиноріг, який виконує бажання");
        secondExperimentThirdAnswer.setQuestion(secondExperiment);
        secondExperimentThirdAnswer.setInterest(artistic);

        Answer secondExperimentFourthAnswer = new Answer();
        secondExperimentFourthAnswer.setAnswerText("Мудра фея, що володіє заклинаннями добра");
        secondExperimentFourthAnswer.setQuestion(secondExperiment);
        secondExperimentFourthAnswer.setInterest(natural);

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
        thirdExperimentFirstAnswer.setInterest(historic);

        Answer thirdExperimentSecondAnswer = new Answer();
        thirdExperimentSecondAnswer.setAnswerText(
            "Магічний еліксир, що змінює смак їжі за вашим бажанням");
        thirdExperimentSecondAnswer.setQuestion(thirdExperiment);
        thirdExperimentSecondAnswer.setInterest(entertainment);

        Answer thirdExperimentThirdAnswer = new Answer();
        thirdExperimentThirdAnswer.setAnswerText(
            "Таємничий напій, який робить вас тимчасово невидимими");
        thirdExperimentThirdAnswer.setQuestion(thirdExperiment);
        thirdExperimentThirdAnswer.setInterest(artistic);

        Answer thirdExperimentFourthAnswer = new Answer();
        thirdExperimentFourthAnswer.setAnswerText(
            "Коктейль з екзотичних фруктів та магічних інгредієнтів");
        thirdExperimentFourthAnswer.setQuestion(thirdExperiment);
        thirdExperimentFourthAnswer.setInterest(natural);

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
        fourthExperimentFirstAnswer.setInterest(natural);

        Answer fourthExperimentSecondAnswer = new Answer();
        fourthExperimentSecondAnswer.setAnswerText(
            "Загадковий антигерой із складною долею та своєю правдою");
        fourthExperimentSecondAnswer.setQuestion(fourthExperiment);
        fourthExperimentSecondAnswer.setInterest(artistic);

        Answer fourthExperimentThirdAnswer = new Answer();
        fourthExperimentThirdAnswer.setAnswerText(
            "Комічна персона, що розсмішує аудиторію своїми пригодами");
        fourthExperimentThirdAnswer.setQuestion(fourthExperiment);
        fourthExperimentThirdAnswer.setInterest(entertainment);

        Answer fourthExperimentFourthAnswer = new Answer();
        fourthExperimentFourthAnswer.setAnswerText(
            "Мудрець, що допомагає героям знаходити вихід зі складних ситуацій");
        fourthExperimentFourthAnswer.setQuestion(fourthExperiment);
        fourthExperimentFourthAnswer.setInterest(historic);

        List<Answer> fourthQuestionArtofExperimentAnswers = new ArrayList<>();
        fourthQuestionArtofExperimentAnswers.add(fourthExperimentFirstAnswer);
        fourthQuestionArtofExperimentAnswers.add(fourthExperimentSecondAnswer);
        fourthQuestionArtofExperimentAnswers.add(fourthExperimentThirdAnswer);
        fourthQuestionArtofExperimentAnswers.add(fourthExperimentFourthAnswer);

        fourthExperiment.setAnswerOptions(fourthQuestionArtofExperimentAnswers);
        ArtofExperiment.add(fourthExperiment);
        fourthExperiment.setSurvey(doorNaturalExperiment);
      }
      {
        Question firstImpresion = new Question();
        firstImpresion.setText(
            "Ти опиняєшся на острові серед океану. Що ти бачиш перед собою?");

        Answer firstImpresionFirstAnswer = new Answer();
        firstImpresionFirstAnswer.setAnswerText("Гори, оточені хмарами та водоспадами");
        firstImpresionFirstAnswer.setQuestion(firstImpresion);
        firstImpresionFirstAnswer.setInterest(historic);

        Answer firstImpresionSecondAnswer = new Answer();
        firstImpresionSecondAnswer.setAnswerText("Ліс, наповнений загадковою атмосферою та екзотичними рослинами ");
        firstImpresionSecondAnswer.setQuestion(firstImpresion);
        firstImpresionSecondAnswer.setInterest(natural);

        Answer firstImpresionThirdAnswer = new Answer();
        firstImpresionThirdAnswer.setAnswerText("Берег з білосніжним піском та глибокими лазуревими водами");
        firstImpresionThirdAnswer.setQuestion(firstImpresion);
        firstImpresionThirdAnswer.setInterest(entertainment);

        Answer firstImpresionFourthAnswer = new Answer();
        firstImpresionFourthAnswer.setAnswerText("Екзотичний кораловий риф з багатою морською флорою та фауною");
        firstImpresionFourthAnswer.setQuestion(firstImpresion);
        firstImpresionFourthAnswer.setInterest(artistic);

        List<Answer> firstQuestionArtofImpresionAnswers = new ArrayList<>();
        firstQuestionArtofImpresionAnswers.add(firstImpresionFirstAnswer);
        firstQuestionArtofImpresionAnswers.add(firstImpresionSecondAnswer);
        firstQuestionArtofImpresionAnswers.add(firstImpresionThirdAnswer);
        firstQuestionArtofImpresionAnswers.add(firstImpresionFourthAnswer);

        firstImpresion.setAnswerOptions(firstQuestionArtofImpresionAnswers);
        ArtofImpression.add(firstImpresion);
        firstImpresion.setSurvey(doorImpresion);
        ///
        Question secondImpresion = new Question();
        secondImpresion.setText("Що ти зробиш найпершим?");

        Answer secondImpresionFirstAnswer = new Answer();
        secondImpresionFirstAnswer.setAnswerText("Піднімусь на вершину гори для медитації");
        secondImpresionFirstAnswer.setQuestion(secondImpresion);
        secondImpresionFirstAnswer.setInterest(artistic);

        Answer secondImpresionSecondAnswer = new Answer();
        secondImpresionSecondAnswer.setAnswerText("Прогуляюсь по лісу, слухаючи пісні птахів та шум природи");
        secondImpresionSecondAnswer.setQuestion(secondImpresion);
        secondImpresionSecondAnswer.setInterest(natural);

        Answer secondImpresionThirdAnswer = new Answer();
        secondImpresionThirdAnswer.setAnswerText("Поплаваю серед коралового рифу та дивитимусь на барвистих рибок");
        secondImpresionThirdAnswer.setQuestion(secondImpresion);
        secondImpresionThirdAnswer.setInterest(entertainment);

        Answer secondImpresionFourthAnswer = new Answer();
        secondImpresionFourthAnswer.setAnswerText("Познайомлюсь з місцевими жителями та їх культурою");
        secondImpresionFourthAnswer.setQuestion(secondImpresion);
        secondImpresionFourthAnswer.setInterest(historic);

        List<Answer> secondQuestionArtofImpresionAnswers = new ArrayList<>();
        secondQuestionArtofImpresionAnswers.add(secondImpresionFirstAnswer);
        secondQuestionArtofImpresionAnswers.add(secondImpresionSecondAnswer);
        secondQuestionArtofImpresionAnswers.add(secondImpresionThirdAnswer);
        secondQuestionArtofImpresionAnswers.add(secondImpresionFourthAnswer);

        secondImpresion.setAnswerOptions(secondQuestionArtofImpresionAnswers);
        ArtofImpression.add(secondImpresion);
        secondImpresion.setSurvey(doorImpresion);
        ///
        Question thirdImpresion = new Question();
        thirdImpresion.setText(
            "Твою увагу привертають тварини, що живуть на острові. Хто вони?");

        Answer thirdImpretionFirstAnswer = new Answer();
        thirdImpretionFirstAnswer.setAnswerText("Тропічний папуги, що заповнюють небо своїми яскравими кольорами");
        thirdImpretionFirstAnswer.setQuestion(thirdImpresion);
        thirdImpretionFirstAnswer.setInterest(artistic);

        Answer thirdImpretionSecondAnswer = new Answer();
        thirdImpretionSecondAnswer.setAnswerText(
            "Мавпи, які розважають своїми витівками");
        thirdImpretionSecondAnswer.setQuestion(thirdImpresion);
        thirdImpretionSecondAnswer.setInterest(entertainment);

        Answer thirdImpretionThirdAnswer = new Answer();
        thirdImpretionThirdAnswer.setAnswerText(
            "Морські черепахи, що виходять на берег для відкладання яєць");
        thirdImpretionThirdAnswer.setQuestion(thirdImpresion);
        thirdImpretionThirdAnswer.setInterest(historic);

        Answer thirdImpretionFourthAnswer = new Answer();
        thirdImpretionFourthAnswer.setAnswerText(
            "Ігуани, що сидять на гілках");
        thirdImpretionFourthAnswer.setQuestion(thirdImpresion);
        thirdImpretionFourthAnswer.setInterest(natural);

        List<Answer> thirdQuestionArtofImpresionAnswers = new ArrayList<>();
        thirdQuestionArtofImpresionAnswers.add(thirdImpretionFirstAnswer);
        thirdQuestionArtofImpresionAnswers.add(thirdImpretionSecondAnswer);
        thirdQuestionArtofImpresionAnswers.add(thirdImpretionThirdAnswer);
        thirdQuestionArtofImpresionAnswers.add(thirdImpretionFourthAnswer);

        thirdImpresion.setAnswerOptions(thirdQuestionArtofImpresionAnswers);
        ArtofImpression.add(thirdImpresion);
        thirdImpresion.setSurvey(doorImpresion);
        ///
        Question fourthImpresion = new Question();
        fourthImpresion.setText("Тобі пропонують стати частиною місцевого екологічного проекту. Що це за проект?");

        Answer fourthImpresionFirstAnswer = new Answer();
        fourthImpresionFirstAnswer.setAnswerText(
            "Вивчення та маркування птахів для моніторингу популяції");
        fourthImpresionFirstAnswer.setQuestion(fourthImpresion);
        fourthImpresionFirstAnswer.setInterest(historic);

        Answer fourthImpresionSecondAnswer = new Answer();
        fourthImpresionSecondAnswer.setAnswerText(
            "Організація чистки берегу від пластикових відходів та сміття");
        fourthImpresionSecondAnswer.setQuestion(fourthImpresion);
        fourthImpresionSecondAnswer.setInterest(natural);

        Answer fourthImpresionThirdAnswer = new Answer();
        fourthImpresionThirdAnswer.setAnswerText(
            "Спостереження за ігуанами та створення бази даних про їхні місця мешкання");
        fourthImpresionThirdAnswer.setQuestion(fourthImpresion);
        fourthImpresionThirdAnswer.setInterest(entertainment);

        Answer fourthImpresionFourthAnswer = new Answer();
        fourthImpresionFourthAnswer.setAnswerText(
            "Розробка інформаційної кампанії з освіти місцевого населення щодо екосистем острова");
        fourthImpresionFourthAnswer.setQuestion(fourthImpresion);
        fourthImpresionFourthAnswer.setInterest(artistic);

        List<Answer> fourthQuestionArtofImpresionAnswers = new ArrayList<>();
        fourthQuestionArtofImpresionAnswers.add(fourthImpresionFirstAnswer);
        fourthQuestionArtofImpresionAnswers.add(fourthImpresionSecondAnswer);
        fourthQuestionArtofImpresionAnswers.add(fourthImpresionThirdAnswer);
        fourthQuestionArtofImpresionAnswers.add(fourthImpresionFourthAnswer);

        fourthImpresion.setAnswerOptions(fourthQuestionArtofImpresionAnswers);
        ArtofImpression.add(fourthImpresion);
        fourthImpresion.setSurvey(doorImpresion);
      }



      doorArtofTravel.setQuestions(ArtofTravel);
      doorAgeofMysteries.setQuestions(ArtofMysteries);
      doorNaturalExperiment.setQuestions(ArtofExperiment);
      doorImpresion.setQuestions(ArtofImpression);
      surveyService.createSurvey(doorArtofTravel);
      surveyService.createSurvey(doorAgeofMysteries);
      surveyService.createSurvey(doorImpresion);
      surveyService.createSurvey(doorNaturalExperiment);

  } catch(
  IOException e)

  {
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