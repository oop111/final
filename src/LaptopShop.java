import java.sql.SQLException;

public class LaptopShop implements ObserverSubject{
    private int observer;
    private String sale;
    Database d = new Database();

    @Override
    public void registerObserver(String username) throws SQLException, ClassNotFoundException {
        d.registerObserver(username);
    }

    @Override
    public void removeObserver(String username) throws SQLException, ClassNotFoundException {
        d.removeObserver(username);
    }

    @Override
    public void notifyObservers(String sale) {
        System.out.println(sale);
    }

    public void setSale(String sale) {
        this.sale = sale;
        notifyObservers(sale);
    }
}
