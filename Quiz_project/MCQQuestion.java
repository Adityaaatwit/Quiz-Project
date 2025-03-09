package Quiz_project;

public class MCQQuestion extends Question {
    private String[] options;

    // Constructor
    public MCQQuestion(int questionNumber, String questionText, String[] options, String answer, String complement, int eloRating) {
        super(questionNumber, questionText, answer, complement, eloRating);
        this.options = options;
    }

    // Getter for options
    public String[] getOptions() {
        return options;
    }

    // Check if the answer is correct
    public boolean checkAnswer(int userChoice) {
        return options[userChoice - 1].equalsIgnoreCase(answer);
    }
}
