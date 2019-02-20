import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {

    private PublicKey PK = null;
    private PrivateKey SK = null;
    private double balance = 0d;

    public Address() {
    }

    public void setPK(PublicKey PK) {
        this.PK = PK;
    }

    public void setSK(PrivateKey SK) {
        this.SK = SK;
    }

    public PublicKey getPK() {
        return PK;
    }

    public PrivateKey getSK() {
        return SK;
    }

    public double getBalance() {
        return balance;
    }

    public void generateKeyPair() {

        KeyPair pair = GenSig.generateKeyPair();

        setPK(pair.getPublic());
        setSK(pair.getPrivate());

    }

    @Override
    public String toString() {
        return "PK = " + getPK().hashCode() + "\n" +
                "Balance = " + getBalance() + " EZI";
    }
}
