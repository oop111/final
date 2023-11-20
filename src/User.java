import java.sql.SQLException;

public class User extends Person implements Observer {
    private String username;

    private String password;
    private int observerStatus = 0;
    private Database d = new Database();
    public User(String username, String password){
        super(username,password);
        this.username = username;
        this.password = password;
    }

    public User(String username) {
        super(username);
        this.username = username;
        this.observerStatus = 0;
    }
    public void checkObserverStatus(String username) throws SQLException, ClassNotFoundException {
        d.getObserverStatus(username);
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getObserverStatus() {
        return observerStatus;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setObserverStatus(int observerStatus) {
        this.observerStatus = observerStatus;
    }

    @Override
    public void update(String sale) {
        if (observerStatus == 1) {
            System.out.println("Hello " + username + "! There is a sale: " + sale);
        } else {
            System.out.println(username + " is not set to be notified.");
        }
    }

}
