import java.util.Scanner;

public class BetterGuessingGame4 {

  public static void main(String[] args) {
    System.out.println("Welcome to the guessing game.");

    Scanner input = new Scanner(System.in);
    System.out.println("What is the secret number?");
    int number = input.nextInt();

    System.out.println("Guess my secret number: ");
    int guess = input.nextInt();

    for (int numGuesses = 4; numGuesses > 0; numGuesses--) {
      if (guess == number)
        break;

      if (guess < number)
        if ((number - guess) < 5)
          System.out.println("You were too low, but you were close!");
        else
          System.out.println("You were way too low.");
      else 
        if ((guess - number) < 5)
          System.out.println("You were too high, but you were close!");
        else
          System.out.println("You were way too high.");

      if (numGuesses == 1)
        System.out.println("You have 1 guess left.");
      else 
        System.out.println("You have " + numGuesses + " guesses left.");

      System.out.println("Guess again: ");
      guess = input.nextInt(); 
    } 

    if (guess == number)
      System.out.println("You win.");
    else
      System.out.println("Sorry, you missed it. Please play again.");

    System.out.println("Game over.");
    input.close();
  }
}