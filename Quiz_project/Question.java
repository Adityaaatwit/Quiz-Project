package Quiz_project;

public class Question {
    protected int questionNumber;
    protected String questionText;
    protected String answer;
    protected String complement;
    protected int eloRating;

    // Constructor
    public Question(int questionNumber, String questionText, String answer, String complement, int eloRating) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.answer = answer;
        this.complement = complement;
        this.eloRating = eloRating;
    }

    // Getter methods
    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public String getComplement() {
        return complement;
    }

    public int getEloRating() {
        return eloRating;
    }
}
