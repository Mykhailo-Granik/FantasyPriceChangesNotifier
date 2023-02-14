package persistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DestinationAddress {

    private final DestinationAddressType type;
    private final String address;

}
