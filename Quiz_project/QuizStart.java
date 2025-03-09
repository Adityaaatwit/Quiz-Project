package Quiz_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizStart {
    private List<MCQQuestion> questions;
    private int currentElo;
    private Scanner scanner;
    private TimeManager timer;
    private EloManager eloManager;
    private List<MCQQuestion> askedQuestions;
    private int correctCount;
    private int incorrectCount;

    public QuizStart(List<MCQQuestion> questions) {
        this.questions = questions;
        this.scanner = new Scanner(System.in);
        this.timer = new TimeManager();
        this.eloManager = new EloManager();
        this.currentElo = 1000; // Default starting Elo rating
        this.askedQuestions = new ArrayList<>();
        this.correctCount = 0;
        this.incorrectCount = 0;
    }

    public void startQuiz() {
        System.out.println("Starting the quiz...");

        for (int i = 0; i < 10; i++) { // Quiz has 10 questions
            MCQQuestion question = eloManager.getNextQuestion(questions, currentElo, askedQuestions);
            askedQuestions.add(question); // Keep track of asked questions
            askQuestion(question);
        }

        System.out.println("Quiz completed!");
        System.out.println( "You got " + correctCount + " correct and " + incorrectCount + " incorrect out of 10.");
    }

    private void askQuestion(MCQQuestion question) {
        System.out.println("\nQuestion " + question.getQuestionNumber() + ": " + question.getQuestionText());

        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        timer.startTimer(30); // Start 30-sec countdown
        System.out.print("Your answer (1-4): ");

        int userChoice = getUserInput();
        timer.stopTimer();

        evaluateAnswer(question, userChoice);
    }

    private int getUserInput() {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 4) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a number between 1-4: ");
            }
        }
        return choice;
    }

    private void evaluateAnswer(MCQQuestion question, int userAnswer) {
        if (question.checkAnswer(userAnswer)) {
            System.out.println("Correct! " + question.getComplement());
            eloManager.updateElo(true);
            correctCount++;
        } else {
            System.out.println("Wrong! The correct answer was: " + question.getAnswer());
            eloManager.updateElo(false);
            incorrectCount++;
        }
    }
}
