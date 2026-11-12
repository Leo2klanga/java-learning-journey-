import java.util.*;
public class Validations {
	private Scanner s;
	
	public Validations() {
		s = new Scanner(System.in);
	}
	
	public static String validateAnswer(Scanner s, String msg, String... options) {
		String x;
		boolean valid;
		do {
			System.out.println(msg);
			x = s.nextLine().trim();
			valid = false;
			for(String option : options) {
				if(x.equalsIgnoreCase(option)) {
					valid = true;
					break;
				}
			}
			if(!valid) {
				System.out.println("Error");
			}
		}while(!valid);
		return x;
	}

	public static int validateNumber(Scanner s, int a, int b) 
	{
		int n=0;
		do {
			System.out.println("Enter the number (1-10)");
			if(s.hasNextInt()) {
				n = s.nextInt();
				if(n<a || n>b) {
					System.err.println("Error");
				}
			}else {
				String input = s.next();
				if(input.equalsIgnoreCase("quit")) {
					return -1;
				}
				System.out.println("Thats not a number");
			}
		}while(n<a || n>b);
		return n;
	}
}
