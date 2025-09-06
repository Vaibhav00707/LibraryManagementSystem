import java.io.Serializable;

public class SavingsAccount extends BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final double SAVINGS_INTEREST_RATE = 2.5;

    public SavingsAccount(String accountHolderName, String accountNumber, double initialBalance, int pin) {
        super(accountHolderName, accountNumber, initialBalance, pin, SAVINGS_INTEREST_RATE);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
    
    // Savings account specific methods can be added here
    public void applyAnnualBonus() {
        double bonus = balance * 0.01; // 1% annual bonus
        balance += bonus;
        recordTransaction("Annual bonus applied: +$" + bonus);
        System.out.printf("Annual bonus of $%.2f applied to your savings account.%n", bonus);
    }
}