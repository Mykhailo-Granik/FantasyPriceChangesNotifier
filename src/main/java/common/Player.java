package common;

public class Player {

    private String name;
    private String club;
    private String position;
    private int price;
    private int target;

    public Player(String name, String club, String position, double price, double target) {
        this.name = name;
        this.club = club;
        this.position = position;
        this.price = (int) Math.round(price * 10);
        this.target = (int) Math.round(target * 10);
    }

}
