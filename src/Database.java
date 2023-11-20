import java.sql.*;
import java.util.Scanner;

public class Database {
    Scanner scanner = new Scanner(System.in);
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:postgresql://localhost:5432/shop";
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "postgres", "5636nkC");
        return dbConnection;
    }


    public User saveUser() throws SQLException, ClassNotFoundException {
        System.out.println("Input your username: ");
        String username = scanner.next();
        System.out.println("Input your password: ");
        String password = scanner.next();
        User user = new User(username,password);
        PreparedStatement stmt = getDbConnection().prepareStatement("SELECT * FROM users WHERE username = ?");
        stmt.setString(1, user.getUsername());
        ResultSet rs = stmt.executeQuery();
        if(rs != null){
            PreparedStatement stmt1 = getDbConnection().prepareStatement("INSERT INTO users (username,password,observer) VALUES (?,?,?)");
            stmt1.setString(1,user.getUsername());
            stmt1.setString(2, user.getPassword());
            stmt1.setInt(3, 1);
            stmt1.executeUpdate();
            return user;
        }else{
            System.out.println("This user is already registered!");
            return null;
        }
    }

    public User authUser() throws SQLException, ClassNotFoundException {
        System.out.println("Input your account info: ");
        boolean auth = true;
        User user = null;
        while (auth) {
            System.out.println("Username:");
            String username = scanner.next();
            System.out.println("Password:");
            String password = scanner.next();
            PreparedStatement stmt = getDbConnection().prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                auth = false;
            }

            if (auth == true) {
                System.out.println("Incorrect account data!\n");
            } else {
                user = new User(username, password);
                System.out.println("Correct data!\n");
                break;
            }
        }
            return user;
    }

    public void viewDevices() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = getDbConnection().prepareStatement("SELECT * FROM devices ");
        ResultSet rs = stmt.executeQuery();
        String model = "";
        int id=0;

        while (rs.next()) {
            model = rs.getString("model");
            String company = rs.getString("company");
            int price = rs.getInt("price");
            id = rs.getInt("id");
            System.out.println(id+". "+company+" "+model+" "+price+"tg");
        }
        System.out.println("\n");
    }

    public void buyDevices(String username) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = getDbConnection().prepareStatement("SELECT * FROM devices ");
        ResultSet rs = stmt.executeQuery();
        int id;
        int u_id =0;
        String charge_type="";
        String model = "";

        while (rs.next()) {
            model = rs.getString("model");
            String company = rs.getString("company");
            int price = rs.getInt("price");
            id = rs.getInt("id");
            System.out.println(id+". "+company+" "+model+" "+price+"tg");
        }
        System.out.println("\n");
        System.out.println("Select laptop for buy: ");
        int type = scanner.nextInt();
        PreparedStatement stmt1 = getDbConnection().prepareStatement("SELECT id FROM users WHERE username = ? ");
        stmt1.setString(1,username);
        ResultSet rs1 = stmt1.executeQuery();
        while (rs1.next()) {
            u_id = rs1.getInt("id");
        }

        PowerSource solarPower = new SolarPowerSource();
        Laptop laptop = null;
        if(type==1){
             laptop = new AcerLaptop();
        }else if(type==2){
             laptop = new LenovoLaptop();
        }else if(type==3){
             laptop = new MacbookLaptop();
        }

        System.out.println("Select charging type (1 or 2):\n1. Generator power\n2. Solar power");
        int charge = scanner.nextInt();
        if(charge==1){
            Laptop adapterForGenerator = new PowerAdapter(new GeneratorPowerSource(), laptop);
            charge_type = "Generator";
            adapterForGenerator.charge();
        }else if (charge==2){
            Laptop adapterForSolar = new PowerAdapter(solarPower, laptop);
            charge_type = "Solar";
            adapterForSolar.charge();
        }

        PreparedStatement stmt2 = getDbConnection().prepareStatement("INSERT INTO user_devices (user_id,device_id,charge) VALUES(?,?,?) ");
        stmt2.setInt(1,u_id);
        stmt2.setInt(2,type);
        stmt2.setString(3,charge_type);
        stmt2.executeUpdate();
        int price = laptop.getPrice();

        System.out.println("Do you have add additional 8GB Ram ? (1 - yes, 2 - no)");
        int ram = scanner.nextInt();
        if(ram==1){
            Laptop LaptopRam = new RamUpgrade(laptop);
            System.out.println("Do you have add additional 512GB SSD ? (1 - yes, 2 - no)");
            int ssd = scanner.nextInt();

            if(ssd==1){
                Laptop SsdRamLaptop = new StorageUpgrade(LaptopRam);
                System.out.println("Description: " + SsdRamLaptop.getDescription());
                System.out.println("Price: " + SsdRamLaptop.getPrice());
                price = SsdRamLaptop.getPrice();
            }else{
                System.out.println("Description: " + LaptopRam.getDescription());
                System.out.println("Price: " + LaptopRam.getPrice());
                price = LaptopRam.getPrice();
            }
        }else{
            System.out.println("Do you have add additional 512GB SSD ? (1 - yes, 2 - no)");
            int ssd = scanner.nextInt();
            if(ssd==1) {
                Laptop SsdLaptop = new StorageUpgrade(laptop);
                System.out.println("Description: " + SsdLaptop.getDescription());
                System.out.println("Price: " + SsdLaptop.getPrice());
                price = SsdLaptop.getPrice();
            }
        }

        System.out.println("\nSelect method for payment:\n1-Credit card\n2-Paypal");
        int pay = scanner.nextInt();
        ShoppingCart cart = new ShoppingCart();
        if(pay==1){
            PaymentStrategy creditCardPayment = new CreditCard("1234567890123456", "123", "12/25");
            cart.setPaymentStrategy(creditCardPayment);
            cart.checkout(price);
        }else{
            PaymentStrategy payPalPayment = new PayPal("example@example.com", "password");
            cart.setPaymentStrategy(payPalPayment);
            cart.checkout(price);
        }
    }
    public void getObserverStatus(String username) throws SQLException, ClassNotFoundException {
        int observerStatus = 0;
        LaptopShop shop = new LaptopShop();
        PreparedStatement stmt1 = getDbConnection().prepareStatement("SELECT * FROM users WHERE username = ?");
        stmt1.setString(1,username);
        ResultSet rs1 = stmt1.executeQuery();
        if (rs1.next()) {
            observerStatus = rs1.getInt("observer");
        }

        if (observerStatus == 0) {
            System.out.println("Do you want to receive new discount notifications? (1 - yes, 2 - no)");
            int obs = scanner.nextInt();
            if(obs == 1){
                shop.registerObserver(username);
                System.out.println("Thank you, you will receive new notifications!");
            }
        }else{
            System.out.println("Do you want to not receive new discount notifications? (1 - yes, 2 - no)");
            int obs = scanner.nextInt();
            if(obs == 1){
                shop.removeObserver(username);
                System.out.println("You will not receive new notifications!");
            }
        }

    }

    public void registerObserver(String username) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt1 = getDbConnection().prepareStatement("UPDATE users SET observer = 1 WHERE username = ?");
        stmt1.setString(1,username);
        stmt1.executeUpdate();
    }

    public void removeObserver(String username) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt1 = getDbConnection().prepareStatement("UPDATE users SET observer = 0 WHERE username = ?");
        stmt1.setString(1,username);
        stmt1.executeUpdate();
    }

    public void notifyInMain(String username) throws SQLException, ClassNotFoundException {
        int observerStatus = 0;
        LaptopShop shop = new LaptopShop();
        PreparedStatement stmt1 = getDbConnection().prepareStatement("SELECT * FROM users WHERE username = ?");
        stmt1.setString(1,username);
        ResultSet rs1 = stmt1.executeQuery();
        if (rs1.next()) {
            observerStatus = rs1.getInt("observer");
        }

        if (observerStatus == 1) {
            shop.notifyObservers("\nToday in our store there is a discount of up to 30% for all types of laptops!\n");
        }
    }
}