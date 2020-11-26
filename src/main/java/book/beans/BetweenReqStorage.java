
package book.beans;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class BetweenReqStorage {
    private int rowsOnPage = 0;

    private List<Product> filteredDataList = new ArrayList<>();

    private static BetweenReqStorage singleton = new BetweenReqStorage();

    public static BetweenReqStorage getSingleton() {
        return singleton;
    }

    public List<Product> getFilteredDataList() {
        return filteredDataList;
    }

    public void setFilteredDataList(List<Product> filteredDataList) {
        this.filteredDataList.clear();
        this.filteredDataList.addAll(filteredDataList);
    }

    public void clearAll() {
        filteredDataList.clear();
    }
    public void setRowsOnPage(Integer rowsOnPage) {
        this.rowsOnPage = rowsOnPage;
    }

    public int getRowsOnPage() {
        return rowsOnPage;
    }

}
