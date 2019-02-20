public class TokenContract {

    // Atributos

    private Address address = null;
    private String name = null;
    private String symbol = null;
    private int TotalSupply = 0;

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

    public void setTotalSupply(int totalSupply) {
        TotalSupply = totalSupply;
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

    public int getTotalSupply() {
        return TotalSupply;
    }

    @Override
    public String toString() {
        return "Name = " + getName() + "\n" +
                "Symbol = " + getSymbol() + "\n" +
                "totalSupply = " + getTotalSupply() + "\n" +
                "owner PK = " + getAddress().hashCode();
    }
}
