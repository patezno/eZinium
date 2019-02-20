import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TokenContractTest {

    private TokenContract contract = null;
    private Address address  = null;

    @Before
    public void init() {

        address = new Address();
        address.generateKeyPair();

        contract = new TokenContract(address);
        contract.setName("Test");
        contract.setSymbol("€");
        contract.setTotalSupply(200);

        contract.addOwner(address.getPK(), contract.getTotalSupply());

    }

    @Test
    public void gettersTest() {

        assertEquals("Test", contract.getName());
        assertEquals("€", contract.getSymbol());
        assertEquals(200, contract.getTotalSupply(), 0.0);
        assertNotNull(contract.getAddress());

    }

    @Test
    public void numOwnersTest() {

        Address rick = new Address();
        rick.generateKeyPair();

        contract.addOwner(rick.getPK(), contract.getTotalSupply());

        assertEquals(2, contract.numOwners());
    }

    @Test
    public void balanceOfTest() {
        assertEquals(200, contract.balanceOf(address.getPK()), 0.0);
    }

    @Test
    public void addOwnerRepeatTest() {

        contract.addOwner(address.getPK(), 500);

        assertEquals(1, contract.numOwners());
    }

    @Test
    public void removeCoinsOwnerTest() {
        contract.removeCoinsOwner(2d);
        assertEquals(198, contract.getTotalSupply(), 0.0);
    }
    /*
    @Test
    public void payable_test() {

        Address rick = new Address();
        rick.generateKeyPair();
        TokenContract ricknillos = new TokenContract(rick);
        ricknillos.addOwner(rick.getPK(), 100d);
        Address morty = new Address();
        morty.generateKeyPair();

        morty.addEZI(20d);

        // verifico la transferencia de entradas
        ricknillos.payable(morty.getPK(), morty.getBalance());
        assertEquals(4d, ricknillos.balanceOf(morty.getPK()), 0d);   

        // verifico la trasnferencia de EZI
        assertEquals(20d, ricknillos.owner().getBalance(), 0d);

    }
    */
}
