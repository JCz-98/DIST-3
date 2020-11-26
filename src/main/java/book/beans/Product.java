package book.beans;

public class Product implements Cloneable {

    private String serialNumber;
    private String productName;
    private double pricePerUnit;
    private int stock;

    public Product() {
    }

    public Product(String serialNumber, String productName, double pricePerUnit, int stock) {
        this.serialNumber = serialNumber;
        this.productName = productName;
        this.pricePerUnit = pricePerUnit;
        this.stock = stock;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
