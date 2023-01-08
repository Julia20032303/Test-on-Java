package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller {

    /*
    Referencing objects within the design
     */
    @FXML
    private ToggleGroup answers;

    @FXML
    private Text question_text;

    @FXML
    private RadioButton radio_btn_1;

    @FXML
    private RadioButton radio_btn_2;

    @FXML
    private RadioButton radio_btn_3;

    @FXML
    private RadioButton radio_btn_4;

    @FXML
    private Button answerBtn;


    /*An array based on the class Questions.
      Each object is a question with a set of possible answers.
    */
    private Questions[] questions = new Questions[] {
            new Questions("Який модифікатор доступу необхідно використовувати, щоб змінну було видно лише в поточному класі?",
                    new String[] {"default (package visible)", "public", "protected", "private"}),
            new Questions("Правильне присвоєння нового значення багатовимірному масиву?",
                    new String[] {"a(0)(0) = 1;", "a[0 0] = 1;", "a{0}{0} = 1;", "a[0][0] = 1;"}),
            new Questions("Що з наведеного нижче є примітивним типом даних?",
                    new String[] {"String", "int[]", "Integer", "char"}),
            new Questions("Який варіант оголошення масиву у стилі Java?",
                    new String[] {"String birthdays [] = String[10];", "String birthdays [] = new String[10];", "String birthdays = String[];", "String [] birthdays = new String[10];"}),
            new Questions("Скільки параметрів може використовувати функція?",
                    new String[] {"5", "100", "1", "Необмежену кількість"})
    };

    //Variables for setting the current question number and for counting the number of correct answers
    private int nowQuestion = 0, correctAnswers;
    //This variable will be set to the correct answer of the current question.
    private String nowCorrectAnswer;

    @FXML
    public void initialize() {
        //We take the correct answer for the current question
        nowCorrectAnswer = questions[nowQuestion].correctAnswer();

        //Tracking clicks on the "Відповісти" button
        answerBtn.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();
            //The code is not executed if the answer is not selected
            if(selectedRadioButton != null) {
                //Get response text
                String toogleGroupValue = selectedRadioButton.getText();

                //Checking the answer with the correct one
                if(toogleGroupValue.equals(nowCorrectAnswer)) {
                    //We display information about the correctness of the answer
                    System.out.println("Відповідь правильна");
                    correctAnswers++;
                } else {
                    System.out.println("Відповідь не правильна");
                }

                //If now was the last question, then hide all fields
                if(nowQuestion + 1 == questions.length) {
                    radio_btn_1.setVisible(false);  //Hiding all selection fields
                    radio_btn_2.setVisible(false);
                    radio_btn_3.setVisible(false);
                    radio_btn_4.setVisible(false);
                    answerBtn.setVisible(false);   //Hiding the answer button

                    //Show result text
                    question_text.setText("Ви відповіли правильно на " + correctAnswers + " з " + questions.length + " питань!");
                } else {
                    //Increase the number of the current question if there are more questions
                    nowQuestion++;

                    //Specify the new text of the correct answer
                    nowCorrectAnswer = questions[nowQuestion].correctAnswer();
                    //Change the text of the question in the program
                    question_text.setText(questions[nowQuestion].getQuestion());
                    //Getting an array of responses
                    String[] answers = questions[nowQuestion].getAnswers();

                    //Convert array to list
                    List<String> intList = Arrays.asList(answers);

                    //Sort randomly
                    Collections.shuffle(intList);

                    //We substitute answers in the radio button
                    radio_btn_1.setText(intList.get(0));
                    radio_btn_2.setText(intList.get(1));
                    radio_btn_3.setText(intList.get(2));
                    radio_btn_4.setText(intList.get(3));

                    //Remove the selection that the user has previously specified
                    selectedRadioButton.setSelected(false);
                }

            }
        });
    }

}
