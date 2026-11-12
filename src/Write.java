import java.io.*;
import java.util.*;
public class Write {
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

}
