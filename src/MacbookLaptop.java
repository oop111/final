class MacbookLaptop implements Laptop {
    public void charge() {
        System.out.println("Macbook laptop is charging.");
    }

    @Override
    public String getDescription() {
        return "MacBook Laptop";
    }

    @Override
    public int getPrice() {
        return 870000;
    }
}