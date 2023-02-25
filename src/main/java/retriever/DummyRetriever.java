package retriever;

import common.Player;
import common.Position;

import java.util.List;

public class DummyRetriever implements Retriever{
    @Override
    public List<Player> retrieve() {
        return List.of(
                new Player("Player 1", "Club 1", Position.GK, 1.0, 1.0),
                new Player("Player 2", "Club 2", Position.DEF, 2.0, 2.0),
                new Player("Player 3", "Club 3", Position.MID, 3.0, 3.0),
                new Player("Player 4", "Club 4", Position.FWD, 4.0, 4.0)
        );
    }
}
