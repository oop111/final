class PayPal implements PaymentStrategy {
    private String email;
    private String password;

    public PayPal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
            System.out.println("Paid " + amount + " tg via PayPal");
    }
}