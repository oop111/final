public class StorageUpgrade extends LaptopDecorator {
    public StorageUpgrade(Laptop laptop) {
        super(laptop);
    }


    @Override
    public String getDescription() {
        return laptop.getDescription() + ", +256GB SSD";
    }

    @Override
    public int getPrice() {
        return laptop.getPrice() + 55000;
    }
    @Override
    public void charge() {
    }
}