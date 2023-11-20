class AcerLaptop implements Laptop {
    public void charge() {
        System.out.println("Acer laptop is charging.");
    }

    @Override
    public String getDescription() {
        return "Acer Laptop";
    }

    @Override
    public int getPrice() {
        return 499900;
    }
}