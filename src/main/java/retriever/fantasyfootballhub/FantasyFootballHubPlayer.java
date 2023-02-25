package retriever.fantasyfootballhub;

import common.Player;
import common.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FantasyFootballHubPlayer {

    private String webName;
    private Team team;
    private Data data;


    @lombok.Data
    @AllArgsConstructor
    static class Team {
        private String name;
    }

    @lombok.Data
    @AllArgsConstructor
    static class Data {
        private int positionId;
        private PriceInfo priceInfo;
    }

    @lombok.Data
    @AllArgsConstructor
    static class PriceInfo {
        private double Value;
        private double Target;
    }

    public Player toPlayer() {
        return new Player(
                webName,
                team.getName(),
                fromId(data.getPositionId()),
                data.getPriceInfo().getValue(),
                data.getPriceInfo().getTarget()
        );
    }

    private Position fromId(int id) {
        switch (id) {
            case 1:
                return Position.GK;
            case 2:
                return Position.DEF;
            case 3:
                return Position.MID;
            case 4:
                return Position.FWD;
            default:
                throw new IllegalArgumentException("Unknown position id: " + id);
        }
    }

}
