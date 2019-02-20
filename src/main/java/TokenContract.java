import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    // Atributos

    private Address address = null;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0;
    private Map<PublicKey, Double> balances = new HashMap<PublicKey, Double>();

    // Constructores

    public TokenContract() {
    }

    public TokenContract(Address address) {
        this.address = address;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    // Getters


    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public Map<PublicKey, Double> getBalances() {
        return balances;
    }

    public void addOwner(PublicKey PK, double totalSupply) {

        if (!getBalances().containsKey(PK)) {
            getBalances().put(PK, totalSupply);
        }

    }

    @Override
    public String toString() {
        return "Name = " + getName() + "\n" +
                "Symbol = " + getSymbol() + "\n" +
                "totalSupply = " + getTotalSupply() + "\n" +
                "owner PK = " + getAddress().hashCode();
    }
}
