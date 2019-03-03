import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    // Atributos

    private Address address = null;
    private PublicKey owner = null;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0d;
    private Map<PublicKey, Double> balances = new HashMap<PublicKey, Double>();

    // Constructores

    public TokenContract() {
    }

    public TokenContract(Address address) {

        this.address = address;
        this.owner = address.getPK();

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

    public PublicKey getOwner() {
        return owner;
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

    // Metodos

    public void addOwner(PublicKey PK, double totalSupply) {
        getBalances().putIfAbsent(PK, totalSupply);
    }

    public int numOwners() {
        return getBalances().size();
    }

    public Double balanceOf(PublicKey pk) {
        return getBalances().get(pk);
    }

    public void transfer(PublicKey pk, double tokens) {

        try {

            requiere(tokens);
            removeTokensOwner(tokens);
            addTokens(pk, tokens);

        } catch (AssertionError ignored) {
        }
    }

    private void requiere(double token) {

        if (token > getBalances().get(owner)) {
            throw new AssertionError();
        }

    }

    public void removeTokensOwner(Double tokens) {

        double newTotalSupply = this.getTotalSupply() - tokens;

        getBalances().replace(this.getAddress().getPK(), newTotalSupply);
        setTotalSupply(this.getTotalSupply() - tokens);

    }

    public void addTokens(PublicKey pk, Double tokens) {

        if (!getBalances().containsKey(pk)) {
            addOwner(pk, tokens);
        } else {
            double pkCoins = getBalances().get(pk);
            getBalances().replace(pk, pkCoins + tokens);
        }
    }

    public void transfer(PublicKey owner, PublicKey buyer, double tokens) {

        try {

            requiere(tokens);
            removeTokens(owner, tokens);
            addTokens(buyer, tokens);

        } catch (AssertionError ignored) {
        }
    }

    public void removeTokens(PublicKey owner, double tokens) {

        double ownerTokens = getBalances().get(owner);
        getBalances().replace(owner, ownerTokens - tokens);

    }

    public void owners() {

        for (PublicKey key : getBalances().keySet()) {

            if (!key.equals(this.getAddress().getPK())) {

                System.out.println("Owner: " + key.hashCode() + " " +
                        this.balanceOf(key) + " " +
                        getSymbol());
            }
        }
    }

    public double totalTokensSold() {

        double totalTickets = 0d;

        for (PublicKey key : getBalances().keySet()) {

            if (!key.equals(this.getAddress().getPK())) {
                totalTickets += getBalances().get(key);
            }
        }
        return totalTickets;
    }

    public void payable(PublicKey pk, double ezinium) {

        double tickets = 0d;
        double coins = ezinium;

        while (coins >= 5d) {
            tickets++;
            coins -= 5;
        }

        if (tickets > 0) {
            this.transfer(pk, tickets);
            this.getAddress().transferEZI(this.getAddress(), ezinium);
        }

    }

    @Override
    public String toString() {

        return "Name = " + getName() + "\n" +
                "Symbol = " + getSymbol() + "\n" +
                "totalSupply = " + getTotalSupply() + " " + getSymbol() + "\n" +
                "owner PK = " + getAddress().hashCode();

    }
}
