import java.util.Scanner;

public class GuessingGame2 {

	public static void main(String[] args){
		System.out.println("Welcome to the guessing game");

		Scanner input = new Scanner(System.in);

		System.out.println("What is the sercret number:");
		int secretNum = input.nextInt();

		System.out.println("Gues the number:");
		int num = input.nextInt();

		if (num == secretNum)
			System.out.println("You win!");
		else
			System.out.println("You lost");

		System.out.println("Game over");
		input.close();
	}

}