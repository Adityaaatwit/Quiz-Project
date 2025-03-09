package Quiz_project;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class EloManager {
    private int currentElo;
    private Random random;

    public EloManager() {
        this.currentElo = 1000; // Default starting Elo rating
        this.random = new Random();
    }

    public void updateElo(boolean isCorrect) {
        if (isCorrect) {
            currentElo += 20; // Increase Elo on correct answer
        } else {
            currentElo -= 15; // Decrease Elo on incorrect answer
        }
    }

    public MCQQuestion getNextQuestion(List<MCQQuestion> questions, int userElo, List<MCQQuestion> askedQuestions) {
        // Filter questions within Elo range and NOT already asked
        List<MCQQuestion> filteredQuestions = questions.stream()
                .filter(q -> Math.abs(q.getEloRating() - userElo) <= 100 && !askedQuestions.contains(q))
                .collect(Collectors.toList());

        // If no new question remains, reset the asked list
        if (filteredQuestions.isEmpty()) {
            askedQuestions.clear();
            filteredQuestions = questions.stream()
                    .filter(q -> Math.abs(q.getEloRating() - userElo) <= 100)
                    .collect(Collectors.toList());
        }

        // Select a random question from the filtered list
        return filteredQuestions.get(random.nextInt(filteredQuestions.size()));
    }
}
