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


    private int nowQuestion = 0, correctAnswers;
    private String nowCorrectAnswer;

    @FXML
    public void initialize() {
        nowCorrectAnswer = questions[nowQuestion].correctAnswer();

        answerBtn.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();
            if(selectedRadioButton != null) {
                String toogleGroupValue = selectedRadioButton.getText();


                if(toogleGroupValue.equals(nowCorrectAnswer)) {
                    System.out.println("Відповідь правильна");
                    correctAnswers++;
                } else {
                    System.out.println("Відповідь не правильна");
                }

                if(nowQuestion + 1 == questions.length) {
                    radio_btn_1.setVisible(false);
                    radio_btn_2.setVisible(false);
                    radio_btn_3.setVisible(false);
                    radio_btn_4.setVisible(false);
                    answerBtn.setVisible(false);

                    question_text.setText("Ви відповіли правильно на " + correctAnswers + " з " + questions.length + " питань!");
                } else {
                    nowQuestion++;
                    nowCorrectAnswer = questions[nowQuestion].correctAnswer();


                    question_text.setText(questions[nowQuestion].getQuestion());
                    String[] answers = questions[nowQuestion].getAnswers();


                    List<String> intList = Arrays.asList(answers);


                    Collections.shuffle(intList);

                    radio_btn_1.setText(intList.get(0));
                    radio_btn_2.setText(intList.get(1));
                    radio_btn_3.setText(intList.get(2));
                    radio_btn_4.setText(intList.get(3));

                    selectedRadioButton.setSelected(false);
                }

            }
        });
    }

}
