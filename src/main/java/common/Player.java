package common;

public class Player {

    private String name;
    private String club;
    private String position;
    private int price;
    private int target;

    public Player() {

    }

    public Player(String name, String club, String position, double price, double target) {
        this.name = name;
        this.club = club;
        this.position = position;
        this.price = (int) Math.round(price * 10);
        this.target = (int) Math.round(target * 10);
    }

    public double getTarget() {
        return target / 10.0;
    }

    public static Player createPlayerWithTarget(double target) {
        Player player = new Player();
        player.target = (int) Math.round(target * 10);
        return player;
    }

}
