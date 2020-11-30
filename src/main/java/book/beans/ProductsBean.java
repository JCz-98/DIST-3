package book.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Named("products")
@ApplicationScoped
public class ProductsBean implements Serializable {

    private ArrayList<Product> productData = new ArrayList<>();
    private ArrayList<Product> filteredProducts = new ArrayList<>();
    private static ProductsBean singleton = new ProductsBean();
    private Lock lock = new ReentrantLock();

    public ProductsBean(){
        try {
            readProductData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ProductsBean getSingleton(){
        return singleton;
    }

    private ArrayList<Product> getProductData() {
        return productData;
    }

    //TRYING
    public ArrayList<Product> getProductCopy() {
        ArrayList<Product> productsCopy = new ArrayList<>();
        lock.lock();
        for (Product p: productData)
            if(p.getStockNum() > 0)
                productsCopy.add(new Product(p));

        lock.unlock();
        return productsCopy;
    }

    public void setProductData(ArrayList<Product> productData) {
        this.productData = productData;
    }

    public ArrayList<Product> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(ArrayList<Product> filteredProducts) {
        this.filteredProducts.clear();
        this.filteredProducts.addAll(filteredProducts);
    }

    public void readProductData() throws IOException {
        ArrayList<Product> productData = new ArrayList<>();

        if (getProductData().isEmpty()) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("Products.csv");

            InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null; ) {
                String[] data = line.split(",");
                productData.add(new Product(data[0], data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Double.parseDouble(data[5])));
            }
        }
        setProductData(productData);
    }

    public void clearAll() {
        filteredProducts.clear();
    }

    public ArrayList<Product> validatePurchase(ArrayList<Product> purchased){
        ArrayList<Product> invalidProds = new ArrayList<>();
        boolean validPurchase = true;
        lock.lock();
        for(Product p: purchased){
            for(Product prod_real: productData){
                if (p.getSerialNum().equals(prod_real.getSerialNum())){
                    p.setStockNum(prod_real.getStockNum());
                    if(p.getPurchaseNum() > p.getStockNum()){
                        validPurchase = false;
                        p.setPurchaseNum(0);
                        invalidProds.add(p);
                    }
                }
            }
        }

        if (validPurchase){
            for(Product p: purchased){
                if (p.getPurchaseNum() == 0)
                    continue;

                for(Product prod_real: productData){
                    if (p.getSerialNum().equals(prod_real.getSerialNum())){
                        p.setStockNum(prod_real.getStockNum());
                        if(p.getPurchaseNum() <= p.getStockNum()){
                            prod_real.setStockNum(prod_real.getStockNum()-p.getPurchaseNum());
                        }
                    }
                }
            }
        }
        lock.unlock();
        return invalidProds;
    }
}
