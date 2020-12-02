package book.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;


@Named("shop_session")
@SessionScoped
public class ShopManager implements Serializable {

    FacesContext fCtx = FacesContext.getCurrentInstance();
    private ProductsBean productsBean;
    private HtmlDataTable table;
    private int rowsOnPage;
    private String nameCriteria = "";
    private String priceCriteria = "all";
    private ArrayList<Product> purchasedProducts = new ArrayList<>();
    private ArrayList<Product> filteredProducts = new ArrayList<>();
    private ArrayList<ArrayList<Product>> purchases = new ArrayList<>();
    private String errorString = "";
    private ArrayList<Product> lastPurchase = new ArrayList<>();
    public ShopManager() {
        productsBean = ProductsBean.getSingleton();
        rowsOnPage = 5;

        setPurchasedProducts(productsBean.getProductCopy());
        setFilteredProducts(new ArrayList<>(purchasedProducts));

    }

    public ArrayList<Product> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(ArrayList<Product> newFiltered) {
        filteredProducts.clear();
        filteredProducts.addAll(newFiltered);
    }

    public ArrayList<ArrayList<Product>> getPurchases() {
        return purchases;
    }

    public ArrayList<Product> getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(ArrayList<Product> lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public ProductsBean getProductsBean() {
        return productsBean;
    }

    public void setProductsBean(ProductsBean productsBean) {
        this.productsBean = productsBean;
    }

    public ArrayList<Product> getProducts() {
        return purchasedProducts;
    }

    public void setProducts(ArrayList<Product> products) {
        this.productsBean.setProductData(products);
    }

    public ArrayList<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(ArrayList<Product> purchasedProducts) {
        //this.purchasedProducts.addAll(purchasedProducts);
        this.purchasedProducts = new ArrayList<>(purchasedProducts);
    }

    public double getLastPurchaseTotalPrice() {
        double total = 0;
        for (Product p : lastPurchase) {
            total += p.getTotalPrice();
        }
        return (double) Math.round(total * 100d) / 100d;
    }
}
