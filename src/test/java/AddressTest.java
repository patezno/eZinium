import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AddressTest {

    private Address address = null;

    @Before
    public void init() {
        address = new Address();
    }

    @Test
    public void createAddress() {
        assertNotNull(address);
    }

    @Test
    public void generateKeyPairTest() {
        address.generateKeyPair();
        assertNotNull(address.getPK());
        assertNotNull(address.getSK());
    }
}
