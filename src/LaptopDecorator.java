public abstract class LaptopDecorator implements Laptop{
    protected Laptop laptop;
    public LaptopDecorator(Laptop laptop){
        this.laptop = laptop;
    }

    public abstract String getDescription();

    public abstract int getPrice();
}
