package retriever;

import common.Player;

import java.util.List;

public class DummyRetriever implements Retriever{
    @Override
    public List<Player> retrieve() {
        return List.of(
                new Player("Lionel Messi", "Barcelona", "Forward", 10.8, 91.5),
                new Player("Cristiano Ronaldo", "Juventus", "Forward", 9.5, 92.5),
                new Player("Neymar", "Paris Saint-Germain", "Forward", 10.2, 93.0),
                new Player("Kylian Mbappe", "Paris Saint-Germain", "Forward", 9.8, 94.5),
                new Player("Mohamed Salah", "Liverpool", "Forward", 9.0, 92.5),
                new Player("Sadio Mane", "Liverpool", "Forward", 8.5, 96.0),
                new Player("Kevin De Bruyne", "Manchester City", "Midfielder", 9.0, 99.5),
                new Player("Eden Hazard", "Real Madrid", "Midfielder", 9.0, 93.5),
                new Player("Luka Modric", "Real Madrid", "Midfielder", 8.5, 93.0),
                new Player("Sergio Ramos", "Real Madrid", "Defender", 8.0, 98.5),
                new Player("Virgil van Dijk", "Liverpool", "Defender", 8.0, 98.5),
                new Player("Alisson", "Liverpool", "Goalkeeper", 8.0, 98.5),
                new Player("Jan Oblak", "Atletico Madrid", "Goalkeeper", 7.5, 100.0)
        );
    }
}
