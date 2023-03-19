package retriever.fplstatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import common.Player;
import common.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FPLStatisticsData {

    @JsonProperty("aaData")
    private List<List<String>> data;

    public List<Player> players() {
        return data.stream()
                .map(this::player)
                .collect(Collectors.toList());
    }

    private Player player(List<String> playerData) {
        return new Player(
                playerData.get(1),
                playerData.get(2),
                position(playerData.get(3)),
                Double.parseDouble(playerData.get(6)),
                Double.parseDouble(playerData.get(11))
        );
    }

    private Position position(String position) {
        switch (position) {
            case "G":
                return Position.GK;
            case "D":
                return Position.DEF;
            case "M":
                return Position.MID;
            case "F":
                return Position.FWD;
            default:
                throw new IllegalArgumentException("Unknown position: " + position);
        }
    }
}
