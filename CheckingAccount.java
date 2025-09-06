import java.io.Serializable;

public class CheckingAccount extends BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final double CHECKING_INTEREST_RATE = 1.5;
    private double overdraftLimit;

    public CheckingAccount(String accountHolderName, String accountNumber, double initialBalance, int pin) {
        super(accountHolderName, accountNumber, initialBalance, pin, CHECKING_INTEREST_RATE);
        this.overdraftLimit = 500.0; // Default overdraft limit
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
    
    @Override
    public void withdraw(double amount) {
        if (isLocked()) {
            System.out.println("Account is locked. Please contact support.");
            return;
        }

        if (amount > 0) {
            if (amount <= balance + overdraftLimit) {
                balance -= amount;
                System.out.printf("Successfully withdrew: $%.2f%n", amount);
                recordTransaction("Withdrawal: -$" + amount);
                
                if (balance < 0) {
                    System.out.printf("Overdraft protection used. Current balance: $%.2f%n", balance);
                }
            } else {
                System.out.println("Withdrawal amount exceeds available balance and overdraft limit.");
            }
        } else {
            System.out.println("Withdraw amount must be greater than zero.");
        }
    }
    
    // Checking account specific methods
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
    public void setOverdraftLimit(double overdraftLimit, int adminPin) {
        if (adminPin == ADMIN_PIN) {
            this.overdraftLimit = overdraftLimit;
            System.out.println("Overdraft limit changed to: $" + overdraftLimit);
            recordTransaction("Overdraft limit changed to: $" + overdraftLimit);
        } else {
            System.out.println("Invalid admin PIN.");
        }
    }
}