public class Player {
	private String playerName; 
	private String level;
	private int attempts, score, total; 
	
	public Player(String playerName, String level, int attempts, int score, int total) {
		this.playerName = playerName;
		this.level = level;
		this.attempts = attempts;
		this.score = score;
		this.total = total;
	}
	
	public String getPlayerName() { return playerName; }
	public String getLevel() { return level; }
	public int getAttempts() { return attempts; }
	public int getScore() { return score; }
	public int getTotal() { return total; }

	@Override
	public String toString() {
		return "[playerName=" + playerName + ", level=" + level + ", attempts=" + attempts
				+ ", score=" + score + ", total=" + total + "]";
	}
}
