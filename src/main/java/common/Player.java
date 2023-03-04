package common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.DoubleWithOnePlace;

@Getter
@EqualsAndHashCode
public class Player {

    private String name;
    private String club;
    private Position position;
    private DoubleWithOnePlace price;
    private DoubleWithOnePlace target;

    public Player() {

    }

    public Player(String name, String club) {
        this.name = name;
        this.club = club;
    }

    public Player(String name, String club, Position position, double price, double target) {
        this.name = name;
        this.club = club;
        this.position = position;
        this.price = new DoubleWithOnePlace(price);
        this.target = new DoubleWithOnePlace(target);
    }

    public static Player createPlayerWithTarget(double target) {
        Player player = new Player();
        player.target = new DoubleWithOnePlace(target);
        return player;
    }

    @Override
    public String toString() {
        return String.format("%s(%s), price=%s, target=%s", name, club, price, target);
    }
}
