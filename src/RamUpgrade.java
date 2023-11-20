public class RamUpgrade extends LaptopDecorator {
    public RamUpgrade(Laptop laptop) {
        super(laptop);
    }


    @Override
    public String getDescription() {
        return laptop.getDescription() + ", +8GB RAM";
    }

    @Override
    public int getPrice() {
        return laptop.getPrice() + 20000;
    }
    @Override
    public void charge() {
    }
}