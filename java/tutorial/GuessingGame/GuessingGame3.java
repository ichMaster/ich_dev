import java.util.Scanner;

public class GuessingGame3 {

	public static void main(String[] args){
		System.out.println("Welcome to the guessing game");

		Scanner input = new Scanner(System.in);

		System.out.println("What is the sercret number:");
		int secretNum = input.nextInt();

		System.out.println("Gues the number:");
		int num = input.nextInt();

		for (int count = 5; count > 0; count--) {
			if (secretNum == num)
				break;

			if (num < secretNum)
				if (secretNum - num < 5)
					System.out.println("You are low but very close");
				else 
					System.out.println("You are to low");
			else
				if (num - secretNum < 5)
					System.out.println("You are high but very close");
				else
					System.out.println("You are too high");


			if (count == 1)
				System.out.println("You have " + count + " guess left");
			else 
				System.out.println("You have " + count + " guesses left");


			System.out.println("Guess again:");
			num = input.nextInt();	
		}


		if (num == secretNum)
			System.out.println("You win!");
		else
			System.out.println("You've lost");

		System.out.println("Game over");
		input.close();
	}

}