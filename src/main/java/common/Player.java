package common;

import lombok.ToString;
import util.DoubleWithOnePlace;

@ToString
public class Player {

    private String name;
    private String club;
    private String position;
    private DoubleWithOnePlace price;
    private DoubleWithOnePlace target;

    public Player() {

    }

    public Player(String name, String club, String position, double price, double target) {
        this.name = name;
        this.club = club;
        this.position = position;
        this.price = new DoubleWithOnePlace(price);
        this.target = new DoubleWithOnePlace(target);
    }

    public DoubleWithOnePlace getTarget() {
        return target;
    }

    public static Player createPlayerWithTarget(double target) {
        Player player = new Player();
        player.target = new DoubleWithOnePlace(target);
        return player;
    }

}
