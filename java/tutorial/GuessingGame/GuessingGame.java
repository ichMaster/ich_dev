import java.util.Scanner;

public class GuessingGame {

	public static void main(String[] args){
		System.out.println("Welcome to the guessing game");

		Scanner input = new Scanner(System.in);
		System.out.println("Gues the number:");
		int num = input.nextInt();

		if (num == 5)
			System.out.println("You win!");
		else
			System.out.println("You lost");

		System.out.println("Game over");
		input.close();
	}

}