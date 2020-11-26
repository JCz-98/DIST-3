package book.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Named
@RequestScoped
public class ProductTable implements Serializable {

    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private List<Product> dataList = new ArrayList<>();
    private List<Product> filteredOutDataList = new ArrayList<>();
    private HtmlDataTable table;
    private int rowsOnPage;
    private String criteria = "all";
    private double totalPrice = 0.00;

    @EJB
    private BetweenReqStorage betweenReqStorage;

    @PostConstruct
    public void init() {
        betweenReqStorage = BetweenReqStorage.getSingleton();
        rowsOnPage = 5;
        loadAllData();
    }

    // getters and setters
    public List<Product> getDataList() {
        return dataList;
    }

    public void setDataList(List<Product> dataList) {
        this.dataList = dataList;
    }

    public List<Product> getFilteredOutDataList() {
        return filteredOutDataList;
    }

    public void setFilteredOutDataList(List<Product> filteredOutDataList) {
        this.filteredOutDataList = filteredOutDataList;
    }

    public HtmlDataTable getTable() {
        return table;
    }

    public void setTable(HtmlDataTable table) {
        this.table = table;
    }

    public int getRowsOnPage() {
        return rowsOnPage;
    }

    public void setRowsOnPage(int rowsOnPage) {
        this.rowsOnPage = rowsOnPage;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public BetweenReqStorage getBetweenReqStorage() {
        return betweenReqStorage;
    }

    public void setBetweenReqStorage(BetweenReqStorage betweenReqStorage) {
        this.betweenReqStorage = betweenReqStorage;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // methods

    private void getDataBetweenReq() {

        if (betweenReqStorage.getFilteredDataList().size() > 0 && !criteria.equals("all")) {
            dataList.clear();
            dataList.addAll(betweenReqStorage.getFilteredDataList());
        }
    }

    private void loadAllData() {
        dataList.add(new Product("00000100", "brown shirts", 34.43, 300));
        dataList.add(new Product("10000011", "green shorts", 32.41, 231));
        dataList.add(new Product("02234344", "shaving cream", 14.23, 443));
        dataList.add(new Product("12341233", "green beer", 4.43, 29));
        dataList.add(new Product("12100222", "black shoes", 67.41, 94));
        dataList.add(new Product("12231222", "coffee", 10.23, 99));
        dataList.add(new Product("10001222", "black combs", 1.43, 23));
        dataList.add(new Product("14311222", "printer red ink", 43.43, 745));
        dataList.add(new Product("02111222", "scissors", 24.55, 12));
        dataList.add(new Product("13111222", "red tape", 4.03, 45));
        dataList.add(new Product("14111222", "black pens", 2.43, 32));
        dataList.add(new Product("15111222", "red pens", 1.43, 134));
        dataList.add(new Product("16111222", "pencils", 0.43, 231));
        dataList.add(new Product("17111222", "hard drives", 50.54, 222));
        dataList.add(new Product("18111222", "sofas", 400.43, 3));
        dataList.add(new Product("19111222", "chairs", 314.43, 32));
        dataList.add(new Product("12001222", "mp3 players", 20.43, 67));
        dataList.add(new Product("12111222", "white shoes", 44.53, 62));
        dataList.add(new Product("12121222", "pen drives", 19.43, 33));
        dataList.add(new Product("12131222", "tooth paste", 4.43, 54));
        dataList.add(new Product("12141222", "dental floss", 4.53, 12));
        dataList.add(new Product("12151222", "hair brushes", 36.43, 44));
        dataList.add(new Product("12161222", "alarm clocks", 9.43, 56));
        dataList.add(new Product("12171222", "watches", 87.43, 11));
        dataList.add(new Product("12181222", "tires", 78.98, 78));
        dataList.add(new Product("12191222", "car oil", 34.32, 21));
        dataList.add(new Product("12111223", "cooking oil", 19.88, 225));
        dataList.add(new Product("12111224", "note books", 6.55, 21));
        dataList.add(new Product("12111225", "vitamin A", 18.99, 4));
        dataList.add(new Product("12111222", "vitamin C", 21.87, 55));
        dataList.add(new Product("12119222", "vitamin B", 17.83, 3));
        dataList.add(new Product("12111222", "vitamin D", 12.32, 12));
        dataList.add(new Product("12118222", "zinc", 20.56, 56));
        dataList.add(new Product("12711222", "coke", 1.23, 11));
        dataList.add(new Product("12111292", "pepsi", 1.23, 76));
        dataList.add(new Product("12511222", "scotch", 60.43, 33));
        dataList.add(new Product("12311222", "bourbon", 40.44, 10));
        dataList.add(new Product("12191222", "rum", 32.43, 30));
        dataList.add(new Product("12181222", "dark beer", 10.43, 67));
        dataList.add(new Product("12171222", "light beer", 7.43, 22));
        dataList.add(new Product("12161222", "hats", 21.44, 12));
        dataList.add(new Product("12151222", "capes", 34.78, 78));
        dataList.add(new Product("12131222", "umbrellas", 4.21, 22));
    }

    public void goToFirstPage() {
        getDataBetweenReq();
        table.setFirst(0);
    }

    public void goToPreviousPage() {
        getDataBetweenReq();
        table.setFirst(table.getFirst() - table.getRows());
    }

    public void goToNextPage() {
        getDataBetweenReq();
        table.setFirst(table.getFirst() + table.getRows());
        if (table.getFirst() >= table.getRowCount() - table.getRows()) {
            rowsOnPage = table.getRowCount() - table.getFirst();
        } else {
            rowsOnPage = 5;
        }

    }

    public void goToLastPage() {
        getDataBetweenReq();
        int totalRows = table.getRowCount();
        int displayRows = table.getRows();

        System.out.println("Total rows: " + totalRows);
        System.out.println("Display rows: " + displayRows);

        int rowsOnLastPage = totalRows % displayRows;     /*   / displayRows; */

        if (rowsOnLastPage == 0) rowsOnLastPage = 5;

        System.out.println("Rows on last page: " + rowsOnLastPage);
        table.setFirst(totalRows - rowsOnLastPage);
        rowsOnPage = rowsOnLastPage;

        betweenReqStorage.setRowsOnPage(rowsOnPage);

        /*int totalRows = table.getRowCount();
        int displayRows = table.getRows();
        int full = totalRows / displayRows;
        int modulo = totalRows % displayRows;

        if (modulo > 0) {
            table.setFirst(full * displayRows);
        } else {
            table.setFirst((full - 1) * displayRows);
        }*/
    }

    public void addTableFilter() {

        if (criteria.equals("all")) {
            betweenReqStorage.clearAll();
            return;
        }

        dataList.sort(Comparator.comparingDouble(Product::getPricePerUnit));

        for (Product product : dataList) {
            System.out.println(product.getProductName() + " Product price: " + product.getPricePerUnit());

        }
        System.out.println();
        if (criteria.equals(">=26")) {
            for (int i = 0; i < dataList.size(); i++) {
                Product product = dataList.get(i);

                System.out.println(i + " " + product.getProductName() + " Product price: " + product.getPricePerUnit());

                if (product.getPricePerUnit() < 26) {
                    System.out.println("Este producto no pasa: " + product.getProductName());
                    filteredOutDataList.add(dataList.remove(i));
                    i = -1;
                }
            }
            betweenReqStorage.setFilteredDataList(dataList);
            int rowCount = dataList.size();
            if (rowCount < 5)
                rowsOnPage = rowCount;
        }

        if (criteria.equals("<26")) {
            for (int i = 0; i < dataList.size(); i++) {
                Product product = dataList.get(i);
                if (product.getPricePerUnit() >= 26) {
                    filteredOutDataList.add(dataList.remove(i));
                    i = 0;
                }
            }
            betweenReqStorage.setFilteredDataList(dataList);
            int rowCount = dataList.size();
            if (rowCount < 5)
                rowsOnPage = rowCount;
        }

        table.setFirst(0);
    }


}
