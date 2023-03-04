package retriever.fantasyfootballhub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import common.Player;
import common.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FantasyFootballHubPlayer {

    private String webName;
    private Team team;
    private Data data;


    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Team {
        private String name;
    }

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Data {
        private int positionId;
        private PriceInfo priceInfo;
    }

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class PriceInfo {
        @JsonProperty("Value")
        private double value;
        @JsonProperty("Target")
        private double target;
    }

    public Player toPlayer() {
        try {
            return new Player(
                    webName,
                    team.getName(),
                    fromId(data.getPositionId()),
                    data.getPriceInfo().getValue(),
                    data.getPriceInfo().getTarget()
            );
        } catch (Exception e) {
            return null;
        }
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
