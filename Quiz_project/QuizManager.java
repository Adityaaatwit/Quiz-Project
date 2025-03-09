package Quiz_project;
import java.io.*;
import java.util.*;

public class QuizManager {
    private List<MCQQuestion> questions = new ArrayList<>();
    private String txtFilePath;

    // Constructor to initialize the selected quiz file
    public QuizManager(String selectedQuiz) {
        this.txtFilePath = "src/resources/" + selectedQuiz; // Ensure correct path
    }

    // Method to load the quiz
    public void loadQuiz() {
        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            String line;
            boolean firstLine = true; // To skip header

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip header
                    continue;
                }

                String[] data = line.split(",");
                if (data.length != 9) continue; // Ensure correct format

                int questionNumber = Integer.parseInt(data[0].trim());
                String questionText = data[1].trim();
                String[] options = { data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim() };
                String answer = data[6].trim();
                String complement = data[7].trim();
                int eloRating = Integer.parseInt(data[8].trim());

                // Create MCQQuestion object and add to list
                questions.add(new MCQQuestion(questionNumber, questionText, options, answer, complement, eloRating));
            }
            System.out.println("Quiz Loaded Successfully! Total Questions: " + questions.size());

        } catch (IOException e) {
            System.out.println("Error reading Txt file: " + e.getMessage());
        }
    }

    // Method to get the list of questions
    public List<MCQQuestion> getQuestions() {
        return questions;
    }
}
