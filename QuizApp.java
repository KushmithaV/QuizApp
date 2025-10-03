import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Main application class to run the console-based quiz.
 */
public class QuizApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        // 1. Create a List of questions [cite: 7]
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "What is the capital of France?",
                List.of("1. Berlin", "2. Madrid", "3. Paris", "4. Rome"),
                2 // Index of "Paris" is 2
        ));
        questions.add(new Question(
                "Which company developed Java?",
                List.of("1. Microsoft", "2. Sun Microsystems", "3. Apple", "4. Google"),
                1 // Index of "Sun Microsystems" is 1
        ));
        questions.add(new Question(
                "What is the result of 8 * 7?",
                List.of("1. 56", "2. 64", "3. 48", "4. 60"),
                0 // Index of "56" is 0
        ));

        System.out.println("--- Welcome to the Online Quiz! ---");


        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.getQuestionText());


            for (String option : currentQuestion.getOptions()) {
                System.out.println(option);
            }

            // 2. Accept answer and score user [cite: 8]
            int userAnswer = -1;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Enter your choice (1-4): ");
                try {
                    userAnswer = scanner.nextInt();
                    if (userAnswer >= 1 && userAnswer <= 4) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear the invalid input from the scanner
                }
            }

            if (userAnswer - 1 == currentQuestion.getCorrectAnswerIndex()) {
                System.out.println("Correct! ✅");
                score++;
            } else {
                System.out.println("Wrong! ❌ The correct answer was: " + currentQuestion.getOptions().get(currentQuestion.getCorrectAnswerIndex()));
            }
        }

        // Display final results
        System.out.println("\n--- Quiz Finished! ---");
        System.out.println("Your final score is: " + score + " out of " + questions.size());
        System.out.println("Thank you for playing!");

        scanner.close();
    }
}