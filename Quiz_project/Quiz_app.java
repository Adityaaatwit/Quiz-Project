package Quiz_project;
import java.util.Scanner;

import java.io.*;

public class Quiz_app{
	private String username;
	private String selectedQuiz;
	
	public static void main(String args[]) {
		Quiz_app app= new Quiz_app();
		app.startQuiz();
	}
	
	public void startQuiz() {
		Scanner scanner= new Scanner(System.in);
		
		System.out.println("Welcome to the Quiz Application");
		System.out.println("Please enter you name: ");
		username= scanner.nextLine();
		
		System.out.println("\nHello " + username+ "\nPlease choose a quiz topic:");
		System.out.println("1. Sports");
		System.out.println("2. General Knowlegdge");
		System.out.println("3. Popular Media/Entertainment");
		System.out.println("4. Back to School");
		
		int choice = 0;

	    while (true) {
	        System.out.print("Enter your choice (1-4): ");
	        
	        try {
	            choice = Integer.parseInt(scanner.nextLine());

	            if (choice >= 1 && choice <= 4) {
	                break; 
	            } else {
	                System.out.println("Invalid choice! Please enter a number between 1 and 4.");
	            }

	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input! Please enter a number between 1 and 4.");
	        }
	    }
		
		switch (choice) {
        case 1 -> selectedQuiz = "sports_quiz.txt";
        case 2 -> selectedQuiz = "general_knowledge_quiz.txt";
        case 3 -> selectedQuiz = "media_entertainment_quiz.txt";
        case 4 -> selectedQuiz = "back_to_school_quiz.txt";
        
        }
        
    
		String quizName= selectedQuiz.replace("_quiz.txt", "").replace("_"," ");
		System.out.println("You choose "+ quizName+" quiz. \nOpening the quiz");
		QuizManager quizManager = new QuizManager(selectedQuiz);
        quizManager.loadQuiz();
        QuizStart quizStart = new QuizStart(quizManager.getQuestions());
        quizStart.startQuiz();

        // Close scanner
        scanner.close();
	}
}