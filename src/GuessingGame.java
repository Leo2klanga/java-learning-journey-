import java.io.*;
import java.util.*;
public class GuessingGame{
	public static void main (String[]args){
		try (Scanner s = new Scanner(System.in)){
			Random r = new Random();
			String level, repeat, answer;
			
			ArrayList<LeaderboardEntry> leaderboard = new ArrayList<>();
			
			do {
				System.out.print("Enter name: ");
				String name = s.nextLine().trim();
				int maxAttempts = 0, guess = 0, roundsWon = 0, roundsPlayed = 0, roundsLost = 0, score = 0, total = 0;
				do{	
					int secretNumber = r.nextInt(10)+1;
					level = validateAnswer(s,"Select level easy, medium, hard", "easy", "medium", "hard");
					
					if(level.equalsIgnoreCase("easy")) { maxAttempts = 7; }
					else if(level.equalsIgnoreCase("medium")) {	maxAttempts = 5; }
					else if(level.equalsIgnoreCase("hard")) { maxAttempts = 3; }
						
					while(maxAttempts>0){
						guess = validateNumber(s, 1, 10);
						
						if(guess == -1) {
							System.out.println("Till next time "+name);
						    System.out.println("Thanks for playing\n");
						    System.out.println("==== Scores ====");
						    System.out.println("You played "+roundsPlayed+" times");
						    System.out.println("You won "+roundsWon+" times");
						    System.out.println("You lost "+roundsLost+" times");
						    System.out.println("Your score is "+total+" points");
						    savedProgress(leaderboard);
							return;
						}
						
						if(secretNumber == guess){
							System.out.println("Well done "+name+" You guessed it ");
							roundsWon++;
							if(level.equalsIgnoreCase("easy")) { score = 1; }
							else if(level.equalsIgnoreCase("medium")) { score = 2; }
							else if(level.equalsIgnoreCase("hard")) { score = 3; }
							total += score;
							
							LeaderboardEntry player = findPlayer(leaderboard, name);
							if(player == null) {
								player = new LeaderboardEntry(name, score);
								leaderboard.add(player);
							}else {
								player.addScore(score);
							}
							break;
							
						}else {
							maxAttempts--;
							System.out.println(maxAttempts+" attempts left"+"\n");	
							if(maxAttempts != 0){
								if(secretNumber < guess) System.out.println("Too High, guess again");
								else System.out.println("Too Low, guess again");
							}else { 
								System.out.println("Game Over! "+name); 
								System.out.println("The number was "+secretNumber);
								roundsLost++;
							}	
						}
					}
					
					s.nextLine().trim();
					repeat = validateAnswer(s,"Do you want to play again ?", "yes", "no");
					roundsPlayed++;
					if (repeat.equalsIgnoreCase("no")){
					    System.out.println("Thanks for playing\n");
					    System.out.println("==== Scores ====");
					    System.out.println("You played "+roundsPlayed+" times");
					    System.out.println("You won "+roundsWon+" times");
					    System.out.println("You lost "+roundsLost+" times");
					    System.out.println("Your score is "+total+" points\n");
					}
				}while(repeat.equalsIgnoreCase("yes"));
				
				answer = validateAnswer(s,"Do you want another player to join ?", "yes", "no");
				if (answer.equalsIgnoreCase("no")){
					System.out.println(" ==== Leader Board ====");
					leaderboard.sort(Comparator.comparingInt(LeaderboardEntry::getTotal).reversed());
				    for(LeaderboardEntry e: leaderboard) {
				    	System.out.println(e);
				    }
				}
			}while(answer.equalsIgnoreCase("yes"));
			savedProgress(leaderboard);
			System.out.println("Thank you for playing");
		}
	}

	public static int validateNumber(Scanner s, int a, int b) 
	{
		int number = 0;
		do {
			System.out.println("Enter the number (1-10)");
			if(s.hasNextInt()) {
				number = s.nextInt();
				if(number < a || number > b) {
					System.err.println("Error");
				}
			}else {
				String input = s.next();
				if(input.equalsIgnoreCase("quit")) {
					return -1;
				}
				System.out.println("Thats not a number");
			}
		}while(number < a || number > b);
		return number;
	}
	
	public static String validateAnswer(Scanner s, String msg, String... options) {
		String answer;
		boolean valid;
		do {
			System.out.println(msg);
			answer = s.nextLine().trim();
			valid = false;
			for(String option : options) {
				if(answer.equalsIgnoreCase(option)) {
					valid = true;
					break;
				}
			}
			if(!valid) {
				System.out.println("Error");
			}
		}while(!valid);
		return answer;
	}
	
	public static void savedProgress(ArrayList<LeaderboardEntry> leaderboard) {
		try {
			FileWriter fw = new FileWriter("New.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			for(LeaderboardEntry e: leaderboard) {
				bw.write(e.toString());
				bw.write("\n");
			}
			bw.close();
		}
		catch(IOException i) { System.out.println(i.getMessage()); }
	}

	public static class LeaderboardEntry{
		private String playerName;
		private int total; 
		
		public LeaderboardEntry(String playerName, int total) {
			this.playerName = playerName;
			this.total = total;
		}
		
		public String getPlayerName() { return playerName; }
		public int getTotal() { return total; }
		public void addScore(int score) {  total+=score; }

		@Override
		public String toString() {
			return "|" + playerName + " | total = " + total + "|";
		}
	}
	
	public static LeaderboardEntry findPlayer(ArrayList<LeaderboardEntry> leaderboard, String name){
		for(LeaderboardEntry e: leaderboard) {
			if(e.getPlayerName().equalsIgnoreCase(name)) {
				return e;
			}
		}
		return null;
	}
}