import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import java.sql.*;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class SecondScene extends Application {
  private PreparedStatement prpStmt1;
  private PreparedStatement prpStmt2;
  private PreparedStatement prpStmt3;
  private PreparedStatement prpStmt4;
  private PreparedStatement prpStmt5;
  private PreparedStatement prpStmt6;
  private PreparedStatement prpStmt7;
  Connection connection;

  private String studentid;
  private String studentName;
  private String studentSex;
  private String studentAge;
  private String studentCountry;
  private String studentMajor;

  // Main Menu Scene
  final Text mainMenuText = new Text("Main Menu");
  final Label Heading = new Label("Click any of the buttons below to access infomation about them:");
  final Button StudButton = new Button("Student Info");
  final Button TeachButton = new Button("Teacher Info");
  final Button ClassButton = new Button("Class Info");
  final Button CourseButton = new Button("Teacher Info");
  final Button MajorButton = new Button("Major Info");

  // Student Login Variables
  Label studlLabel = new Label("Enter Necessary Information");
  Text dispStud = new Text("Student ID:");
  Button EnterStud = new Button("Check");
  Button Clear = new Button("Clear");
  Button Proceed = new Button("Proceed");
  Text dispMess = new Text("Message");
  Text studentPort = new Text("Student Portal");
  private final TextField studId = new TextField();
  private final Text studMess = new Text();
  boolean status = false;

  // Student Info Variables
  Label SLInfoID = new Label("Student ID:");
  Label SLInfoName = new Label("Full Name:");
  Label SLInfoMajor = new Label("Major:");
  Label SLInfoSex = new Label("Sex:");
  Label SLInfoAge = new Label("Age:");
  Label SLInfoCountry = new Label("Country:");
  Label sLInfoOptions = new Label("Select Options:");
  Text SInfoID = new Text();
  Text SInfoName = new Text();
  Text SInfoMajor = new Text();
  Text SInfoSex = new Text();
  Text SInfoAge = new Text();
  Text SInfoCountry = new Text();
  Text SInfoOptions = new Text();
  private TextArea taResult = new TextArea();
  ScrollPane scrollArea = new ScrollPane(taResult);

  ToggleGroup CheckGroup = new ToggleGroup();
  RadioButton checkMarksButton = new RadioButton("Check Marks");
  RadioButton checkCourses = new RadioButton("Check Courses");

  String marksChoice[] = { "By Course", "By Semester" };
  String courseChoice[] = { "Course Count", "Courses Passed" };

  ComboBox<String> CourseBox = new ComboBox<>();
  ComboBox<String> MarksBox = new ComboBox<>();

  Label forMarks = new Label("Choose Marks Filter");
  Label forCourses = new Label("Choose Courses Options");

  Label enCourseName = new Label("Enter Course Name/Course ID");
  TextField inCourseName = new TextField();

  Label enSemester = new Label("Enter Semester and Year");
  TextField inYear = new TextField();
  TextField inSemester = new TextField();

  Button Submit = new Button("Submit");
  Button MainMenu = new Button("Go to Main Menu");
  Button Close = new Button("Close");
  HBox LastButtons = new HBox();

  @Override
  public void start(final Stage primaryStage) {

    initializeDB();

    // Student Scene

    final GridPane studLog = new GridPane();
    studLog.setPadding(new Insets(10, 10, 10, 10));
    studLog.setMinSize(400, 200);
    studLog.setVgap(5);
    studLog.setHgap(5);
    studLog.setAlignment(Pos.CENTER);
    studLog.add(studentPort, 0, 0);
    studLog.add(studlLabel, 0, 1);
    studLog.add(dispStud, 0, 2);
    studLog.add(studId, 1, 2);
    studLog.add(EnterStud, 0, 3);
    studLog.add(Clear, 1, 3);
    studLog.add(dispMess, 0, 4);
    studLog.add(studMess, 1, 4);
    studLog.add(Proceed, 1, 6);
    EnterStud.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
    EnterStud.setPrefWidth(80.0);
    Clear.setPrefWidth(80.0);
    dispMess.setStyle("-fx-font: normal bold 20px 'serif' ");
    dispStud.setStyle("-fx-font: normal bold 20px 'serif' ");
    studentPort.setStyle("-fx-font: normal bold 30px 'serif' ");
    studentPort.setFill(Color.DARKSLATEBLUE);
    Proceed.setPrefWidth(80.0);
    Proceed.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
    studLog.setStyle("-fx-background-color: BEIGE;");

    final Scene studScene = new Scene(studLog, 600, 600);

    // StudentInfoScene
    final GridPane sinfo = new GridPane();
    sinfo.setMinSize(500, 500);
    sinfo.setPadding(new Insets(10, 10, 10, 10));
    sinfo.setVgap(5);
    sinfo.setHgap(5);
    sinfo.setAlignment(Pos.TOP_LEFT);
    sinfo.add(SLInfoID, 0, 0);
    sinfo.add(SInfoID, 1, 0);
    sinfo.add(SLInfoName, 0, 1);
    sinfo.add(SInfoName, 1, 1);
    sinfo.add(SLInfoMajor, 0, 2);
    sinfo.add(SInfoMajor, 1, 2);
    sinfo.add(SLInfoSex, 0, 3);
    sinfo.add(SInfoSex, 1, 3);
    sinfo.add(SLInfoAge, 0, 4);
    sinfo.add(SInfoAge, 1, 4);
    sinfo.add(SLInfoCountry, 0, 5);
    sinfo.add(SInfoCountry, 1, 5);
    sinfo.add(sLInfoOptions, 0, 6);
    checkCourses.setToggleGroup(CheckGroup);
    checkMarksButton.setToggleGroup(CheckGroup);
    sinfo.add(checkCourses, 0, 7);
    sinfo.add(checkMarksButton, 1, 7);

    ObservableList<String> marksitems = FXCollections.observableArrayList(marksChoice);
    ObservableList<String> courseitems = FXCollections.observableArrayList(courseChoice);

    MarksBox.getItems().addAll(marksitems);
    CourseBox.getItems().addAll(courseitems);
    sinfo.add(forCourses, 0, 8);
    sinfo.add(CourseBox, 1, 8, 1, 1);
    sinfo.add(forMarks, 0, 9);
    sinfo.add(MarksBox, 1, 9, 1, 1);
    sinfo.add(enCourseName, 0, 10);
    sinfo.add(inCourseName, 1, 10);
    sinfo.add(enSemester, 0, 11);
    sinfo.add(inYear, 1, 11);
    sinfo.add(inSemester, 2, 11);
    sinfo.add(scrollArea, 0, 12, 2, 1);
    sinfo.add(MainMenu, 0, 13);

    LastButtons.getChildren().addAll(Submit, Close);
    sinfo.add(LastButtons, 1, 13);

    Submit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
    Submit.setPrefWidth(80.0);
    Close.setPrefWidth(80.0);

    inCourseName.setPromptText("CourseID or Course Name");
    inCourseName.getParent().requestFocus();

    inYear.setPromptText("e.g 1 for Year 1");
    inYear.getParent().requestFocus();
    inSemester.setPromptText("e.g 1 for Semester 1");
    inSemester.getParent().requestFocus();

    SLInfoID.setStyle("-fx-font: normal bold 20px 'serif' ");
    SLInfoName.setStyle("-fx-font: normal bold 20px 'serif' ");
    SLInfoMajor.setStyle("-fx-font: normal bold 20px 'serif' ");
    SLInfoSex.setStyle("-fx-font: normal bold 20px 'serif' ");
    SLInfoAge.setStyle("-fx-font: normal bold 20px 'serif' ");
    SLInfoCountry.setStyle("-fx-font: normal bold 20px 'serif' ");

    SInfoID.setStyle("-fx-font: normal  17px 'serif'");
    SInfoID.setFill(Color.DARKSLATEBLUE);
    SInfoName.setStyle("-fx-font: normal 17px 'serif'");
    SInfoName.setFill(Color.DARKSLATEBLUE);
    SInfoMajor.setStyle("-fx-font: normal 17px 'serif' ");
    SInfoMajor.setFill(Color.DARKSLATEBLUE);
    SInfoSex.setStyle("-fx-font: normal  17px 'serif' ");
    SInfoSex.setFill(Color.DARKSLATEBLUE);
    SInfoAge.setStyle("-fx-font: normal 17px 'serif' ");
    SInfoAge.setFill(Color.DARKSLATEBLUE);
    SInfoCountry.setStyle("-fx-font: normal 17px 'serif' ");
    SInfoCountry.setFill(Color.DARKSLATEBLUE);
    sinfo.setStyle("-fx-background-color: BEIGE;");

    final Scene studInfoScene = new Scene(sinfo, 600, 600);

    // StudentLogin Scene Action EVents
    EnterStud.setOnAction(e -> {
      ProcessStudent();
      studMess.setVisible(true);
    });
    Clear.setOnAction(e -> {
      studId.clear();
      studMess.setVisible(false);
    });

    Proceed.setOnAction((e -> {
      if (status) {
        primaryStage.setScene(studInfoScene);
        DisplayStudentInfo();

      } else {
        studMess.setText("First Enter Student ID and Check");
        studMess.setFill(Color.RED);
        studMess.setVisible(true);
      }
      studId.clear();
    }));

    // StudentInfo Action Events
    Submit.setDefaultButton(true);
    Close.setCancelButton(true);
    Close.setOnAction(e -> primaryStage.close());
    checkMarksButton.setOnAction(e -> {
      CourseBox.setVisible(false);
      MarksBox.setVisible(true);
    });
    checkCourses.setOnAction(e -> {
      CourseBox.setVisible(true);
      MarksBox.setVisible(false);
    });
    Submit.setOnAction(e -> {

      if (checkMarksButton.isSelected()) {
        if (marksitems.indexOf(MarksBox.getValue()) == 0) {
          System.out.println("Options index 0 for marks selected");
          taResult.clear();
          ExecuteMarksOp1();
        } else if (marksitems.indexOf(MarksBox.getValue()) == 1) {
          taResult.clear();
          ExecuteMarksOp2();
          System.out.println("Options index 1 of marks selected");
        } else {
          System.out.println("Not selected any Marks options");
          taResult.setStyle("-fx-text-fill: red");
          taResult.setText("You have not selected any Marks options");
        }
      } 
      else if (checkCourses.isSelected()) {
        if (courseitems.indexOf(CourseBox.getValue()) == 0) {
          System.out.println("Options index 0 for courses selected");
          taResult.clear();
          ExecuteCourseOp1();
        } else if (courseitems.indexOf(CourseBox.getValue()) == 1) {
          System.out.println("options index 1 for courses selected");
          taResult.clear();
          ExecuteCourseOp2();
        } else {
          System.out.println("Not selected any Courses option");
          taResult.setStyle("-fx-text-fill: red");
          taResult.setText("You have not selected any Courses option");
        }

      } 
      else {
        taResult.setStyle("-fx-text-fill: red");
        taResult.setText("Please select either check Marks or check Courses");
      }

    });

    // Whole Second Scene and on actions
    StudButton.setOnAction(e -> {
      primaryStage.setScene(studScene);
      primaryStage.setTitle("Student Info");
    });

    final VBox scenelayout = new VBox(20);
    scenelayout.setAlignment(Pos.CENTER);
    scenelayout.setStyle("-fx-background-color: BEIGE;");

    mainMenuText.setStyle("-fx-font: normal bold 30px 'serif' ");
    mainMenuText.setFill(Color.DARKSLATEBLUE);
    StudButton.setPrefWidth(100.0);
    TeachButton.setPrefWidth(100.0);
    ClassButton.setPrefWidth(100.0);
    CourseButton.setPrefWidth(100.0);
    MajorButton.setPrefWidth(100.0);
    scenelayout.getChildren().addAll(mainMenuText, Heading, StudButton, TeachButton, ClassButton, CourseButton,
        MajorButton);
    final Scene scene = new Scene(scenelayout, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
    MainMenu.setOnAction(e -> {
      primaryStage.setScene(scene);
      studId.clear();
      status=false;
      studMess.setText("Re-Enter Student ID");
      taResult.clear();
      inCourseName.clear();
      inSemester.clear();
      inYear.clear();
       });

  }

  private void ProcessStudent() {
    String correctStID = "default";
    studMess.setVisible(true);

    studentid = studId.getText();
    try {
      final String queryString = "select StudID from Student where StudId like ?  ";
      prpStmt1 = connection.prepareStatement(queryString);
      prpStmt1.setString(1, studentid);
      final ResultSet rset = prpStmt1.executeQuery();

      while (rset.next()) {
        correctStID = rset.getString(1);
      }

      if (correctStID.equalsIgnoreCase(studentid)) {
        status = true;
        System.out.println(status);
        studMess.setFill(Color.GREEN);
        studMess.setText("Student exists, Click Proceed");
      } else {
        status = false;
        studMess.setFill(Color.RED);
        studMess.setText("Student doesn't exist, Clear and Try Again");
      }

      System.out.println(studentid);
      System.out.println(correctStID);

    } catch (final SQLException ex) {
      ex.printStackTrace();
      studMess.setFill(Color.RED);
      studMess.setText("Incorrect Entry, Clear and Try Again");
    }

  }

  private void ExecuteMarksOp1() {
    final String incourseid = inCourseName.getText();
    final String incourseName = inCourseName.getText();
    try {
      final String MarksQ1 = "select CourseID,CourseName,Score,Credits,StudStatus,StudID,StudName,Semester,Year from StudentsMarks where StudID like ? and (CourseID like ? or CourseName like ? )";
      prpStmt3 = connection.prepareStatement(MarksQ1);
      prpStmt3.setString(1, studentid);
      prpStmt3.setString(2, incourseid);
      prpStmt3.setString(3, incourseName);
      final ResultSet rSet = prpStmt3.executeQuery();
      int countrow = 0;
      taResult.setStyle("-fx-text-fill: green");

      while (rSet.next()) {
        String resCourseID = rSet.getString(1);
        String resCourseName = rSet.getString(2);
        String resScore = rSet.getString(3);
        String resCredits = rSet.getString(4);
        String resStudStatus = rSet.getString(5);
        String resStudID = rSet.getString(6);
        String resStudName = rSet.getString(7);
        String resSemester = rSet.getString(8);
        String resYear = rSet.getString(9);
        taResult.appendText("CourseID: " + resCourseID + "  CourseName: " + resCourseName + "\n Score: " + resScore
            + "  Credits: " + resCredits + "  Status: " + resStudStatus + "\n From Semester: " + resSemester
            + "  Year: " + resYear + "\n Student: " + resStudID + " " + resStudName + "\n");
        countrow++;
      }
      if (countrow > 0) {
        taResult.appendText(countrow + " course ");
        ;
      } else {
        taResult.setStyle("-fx-text-fill: red");
        taResult.setText("No courses for this CourseID/Course Name");

      }

    } catch (final SQLException ex) {
      ex.printStackTrace();
    }
    inCourseName.clear();
  }

  private void ExecuteMarksOp2() {
    final String inYearM = inYear.getText();
    final String inSemM = inSemester.getText();
    try {

      final String MarksQ2 = "select CourseID,CourseName,Score,Credits,StudStatus,StudID from StudentsMarks where StudID like ? and (Semester like ? and Year like ? )";
      prpStmt4 = connection.prepareStatement(MarksQ2);
      prpStmt4.setString(1, studentid);
      prpStmt4.setString(2, inSemM);
      prpStmt4.setString(3, inYearM);
      final ResultSet rSet = prpStmt4.executeQuery();
      int countrow = 0;
      taResult.setStyle("-fx-text-fill: blue");

      while (rSet.next()) {
        String resCourseID = rSet.getString(1);
        String resCourseName = rSet.getString(2);
        String resScore = rSet.getString(3);
        String resCredits = rSet.getString(4);
        String resStudStatus = rSet.getString(5);
        String resStudID = rSet.getString(6);

        taResult.appendText("CourseID: " + resCourseID + "  CourseName: " + resCourseName + "\n Score: " + resScore
            + "  Credits: " + resCredits + "  Status: " + resStudStatus + "\n From Semester: " + inSemM + "  Year: "
            + inYearM + "\n StudentID: " + resStudID + "\n\n");
        countrow++;
      }
      if (countrow > 0) {
        taResult.appendText(countrow + " course(s) ");
        ;
      } else {
        taResult.setStyle("-fx-text-fill: red");
        taResult.setText("Enter the correct Sem or Year details like \n"
            + "for 1 or 2 for Year 1 or 2 \n and 1 or 2 for Semester 1 or 2 ");

      }

    } catch (final SQLException ex) {
      ex.printStackTrace();
    }
    inSemester.clear();
    inYear.clear();

  }

  private void ExecuteCourseOp1() {
    final String inYearM = inYear.getText();
    final String inSemM = inSemester.getText();
    try {
      final String CoursesQ1 = "select CourseID,CourseName,Score,Credits,StudStatus,DoneBefore,Semester,Year,ClassNum from StudentsMarks where StudID like ? and (Semester like ? and Year like ?)";
      final String queryT = "select TotalCredits from StudentsTotalCredits where StudID like ?";
      prpStmt6 = connection.prepareStatement(CoursesQ1);
      prpStmt7 = connection.prepareStatement(queryT);

      prpStmt6.setString(1, studentid);
      prpStmt6.setString(2, inSemM);
      prpStmt6.setString(3, inYearM);
      prpStmt7.setString(1, studentid);

      final ResultSet rSet = prpStmt6.executeQuery();
      final ResultSet rSetT = prpStmt7.executeQuery();

      int countrow = 0;
      taResult.setStyle("-fx-text-fill: black");

      while (rSet.next()) {
        String resCourseID = rSet.getString(1);
        String resCourseName = rSet.getString(2);
        String resScore = rSet.getString(3);
        String resCredits = rSet.getString(4);
        String resStudStatus = rSet.getString(5);
        String resDoneBefore = rSet.getString(6);
        String resSemester = rSet.getString(7);
        String resYear = rSet.getString(8);
        String resClassNum = rSet.getString(9);

        taResult.appendText("CourseID: " + resCourseID + "  CourseName: " + resCourseName + "\n Score: " + resScore
            + "  Credits: " + resCredits + "  Status: " + resStudStatus + "\n Retake: " + resDoneBefore
            + "  From Semester: " + resSemester + "  Year: " + resYear + "\n Class Number: " + resClassNum + "\n\n");
        countrow++;
      }
      if (countrow > 0) {
        taResult
            .appendText(countrow + " course(s) done in semester" + inSemM + " and year " + inYearM + " you selected \n");
        while (rSetT.next()) {
          String totCred = rSetT.getString(1);
          taResult.appendText("Total Credits: " + totCred);
        }
      } else {
        taResult.setStyle("-fx-text-fill: red");
        taResult.setText("You have no Courses done for this semester" + inSemM + " in Year " + inYearM);
      }

    } catch (final SQLException ex) {
      ex.printStackTrace();
    }
    inSemester.clear();
    inYear.clear();

  }

  private void ExecuteCourseOp2() {

    final String inYearM = inYear.getText();
    final String inSemM = inSemester.getText();
    try {
      final String CoursesQ1 = "select CourseID,CourseName,Score,Credits,StudStatus,DoneBefore,Semester,Year,ClassNum from StudentsMarks where StudID like ? and (Semester like ? and Year like ?) and StudStatus like ?";
      prpStmt5 = connection.prepareStatement(CoursesQ1);
      prpStmt5.setString(1, studentid);
      prpStmt5.setString(2, inSemM);
      prpStmt5.setString(3, inYearM);
      prpStmt5.setString(4, "PASS");
      final ResultSet rSet = prpStmt5.executeQuery();
      int countrow = 0;
      taResult.setStyle("-fx-text-fill: purple");

      while (rSet.next()) {
        String resCourseID = rSet.getString(1);
        String resCourseName = rSet.getString(2);
        String resScore = rSet.getString(3);
        String resCredits = rSet.getString(4);
        String resStudStatus = rSet.getString(5);
        String resDoneBefore = rSet.getString(6);
        String resSemester = rSet.getString(7);
        String resYear = rSet.getString(8);
        String resClassNum = rSet.getString(9);

        taResult.appendText("CourseID: " + resCourseID + "  CourseName: " + resCourseName + "\n Score: " + resScore
            + "  Credits: " + resCredits + "  Status: " + resStudStatus + "\n Retake: " + resDoneBefore
            + "  From Semester: " + resSemester + "  Year: " + resYear + "\n Class Number: " + resClassNum + "\n\n");
        countrow++;
      }
      if (countrow > 0) {
        taResult.appendText(countrow + " course(s) passed ");
        ;
      } else {
        taResult.setStyle("-fx-text-fill: red");
        taResult.setText("You have no Courses Passed for this semester" + inSemM + " in Year " + inYearM
            + "\n Enter the correct Sem or Year details like: 1 or 2 for the Semester \n and 1 or 2 for Year ");
      }

    } catch (final SQLException ex) {
      ex.printStackTrace();
    }
    inSemester.clear();
    inYear.clear();

  }

  private void DisplayStudentInfo() {
    try {
      final String queryString = "select StudID,StudName,MajName,Sex,Age,Country from StudentView where StudID like ? ";
      prpStmt2 = connection.prepareStatement(queryString);
      prpStmt2.setString(1, studentid);
      final ResultSet rset = prpStmt2.executeQuery();

      while (rset.next()) {
        studentid = rset.getString(1);
        studentName = rset.getString(2);
        studentMajor = rset.getString(3);
        studentSex = rset.getString(4);
        studentAge = rset.getString(5);
        studentCountry = rset.getString(6);
      }
      SInfoID.setText(studentid);
      SInfoName.setText(studentName);
      SInfoMajor.setText(studentMajor);
      SInfoSex.setText(studentSex);
      SInfoAge.setText(studentAge);
      SInfoCountry.setText(studentCountry);
    } catch (final SQLException ex) {
      ex.printStackTrace();
    }

  }

  private void initializeDB() {
    try {
      // Load the JDBC driver
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      System.out.println("Driver loaded");

      // Establish a connection
      connection = DriverManager
          .getConnection("jdbc:sqlserver://localhost:1433;databaseName=PangasJohn;user=sa;password=admin");

      System.out.println("Database connected");

      // Create a statement
    } catch (final Exception ex) {
      ex.printStackTrace();
    }

  }

}
