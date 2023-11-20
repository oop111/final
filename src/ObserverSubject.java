import java.sql.SQLException;

public interface ObserverSubject {
    void registerObserver(String username) throws SQLException, ClassNotFoundException;

    void removeObserver(String username) throws SQLException, ClassNotFoundException;

    void notifyObservers(String sale);
}
