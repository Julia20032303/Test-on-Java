package sample;

public class Questions {

    private String question;
    private String[] answers;

    //An object that consists of text and an array of responses
    public Questions(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }

    //Method for getting the last element from an array
    public String correctAnswer() {
        return this.answers[answers.length - 1];
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

}