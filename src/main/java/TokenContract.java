import java.security.PublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TokenContract {

    // Atributos

    private Address address = null;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0d;
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

    public int numOwners() {

        int numOwners = 0;

        for (PublicKey key : getBalances().keySet()) {
            numOwners++;
        }

        return numOwners;

    }

    public double balanceOf(PublicKey pk) {

        try {
            return getBalances().get(pk);
        } catch (NullPointerException e) {
            return 0d;
        }

        /*
        for (Iterator<Map.Entry<PublicKey, Double>> entries = getBalances().entrySet().iterator(); entries.hasNext(); ) {

            Map.Entry<PublicKey, Double> entry = entries.next();

            if (entry.getKey().equals(pk)) {
                value = entry.getValue();
            }
        }
        return value;
        */
    }

    public void transfer(PublicKey pk, double coins) {

        this.requiere(coins);
        this.getBalances().replace(this.getAddress().getPK(), (this.getTotalSupply() - coins));
        this.setTotalSupply(this.getTotalSupply() - coins);
        this.getBalances().replace(pk, coins);

    }

    private Boolean requiere(double coins) {
        boolean holds = true;
        double result = this.getTotalSupply() - coins;
        if (result < 0) {
            holds = false;
        }
        return holds;
    }

    @Override
    public String toString() {
        return "Name = " + getName() + "\n" +
                "Symbol = " + getSymbol() + "\n" +
                "totalSupply = " + getTotalSupply() + " " + getSymbol() + "\n" +
                "owner PK = " + getAddress().hashCode();
    }
}
