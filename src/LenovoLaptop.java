class LenovoLaptop implements Laptop {
    public void charge() {
        System.out.println("Lenovo laptop is charging.");
    }

    @Override
    public String getDescription() {
        return "Lenovo Laptop";
    }

    @Override
    public int getPrice() {
        return 679000;
    }
}