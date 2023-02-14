package persistence;

import java.util.List;

public class DummyDestinationRepository implements DestinationRepository {
    @Override
    public List<Destination> findAll() {
        return List.of(
                new Destination(95.0, -98.0, new DestinationAddress(DestinationAddressType.EMAIL, "fake@abc.com")),
                new Destination(96.0, -97.0, new DestinationAddress(DestinationAddressType.TWITTER, "fake"))
        );
    }
}
