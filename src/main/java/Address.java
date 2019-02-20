import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {

    // Atributos

    private PublicKey PK = null;
    private PrivateKey SK = null;
    private double balance = 0d;
    private String symbol = "EZI";

    // Constructor

    public Address() {
    }

    // Setters

    public void setPK(PublicKey PK) {
        this.PK = PK;
    }

    public void setSK(PrivateKey SK) {
        this.SK = SK;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

    // Getters

    public PublicKey getPK() {
        return PK;
    }

    public PrivateKey getSK() {
        return SK;
    }

    public double getBalance() {
        return balance;
    }

    public String getSymbol() {
        return symbol;
    }

    // Metodos

    public void generateKeyPair() {

        KeyPair pair = GenSig.generateKeyPair();

        setPK(pair.getPublic());
        setSK(pair.getPrivate());

    }

    public void addEZI(double coin) {
        setBalance(coin);
    }

    @Override
    public String toString() {
        return "PK = " + getPK().hashCode() + "\n" +
                "Balance = " + getBalance() + " " + getSymbol();
    }
}
