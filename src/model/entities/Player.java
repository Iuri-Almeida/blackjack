package model.entities;

public class Player implements Comparable<Player> {
	
	private String name;
	private Integer points;
	private Boolean playing;
	private Boolean overcomed;
	
	public Player(String name) {
		this.name = name;
		this.points = 0;
		this.playing = true;
		this.overcomed = false;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void increasePoints(Integer points) {
		this.points += points;
	}
	
	public void setSituation(boolean playing) {
		this.playing = playing;
	}
	
	public void setOvercomed(boolean overcomed) {
		this.overcomed = overcomed;
		setSituation(false);
	}
	
	public boolean hasOvercomed() {
		return overcomed;
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	@Override
	public String toString() {
		return "[" + name + ", " + points + ", " + playing + "]";
	}

	@Override
	public int compareTo(Player otherPlayer) {
		
		if (otherPlayer.hasOvercomed()) return -1;
		
		if (hasOvercomed()) return 1;
		
		if ((21 - points) < (21 - otherPlayer.getPoints())) return -1;
		
		return 1;
		
	}

}
