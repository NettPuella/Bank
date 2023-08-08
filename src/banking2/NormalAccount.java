package banking2;

//////////////보통예금계좌 ////////////////////

public class NormalAccount extends Account {
	
	public NormalAccount (String accountNum, String name, int balance, double interestRate) {
		super(accountNum, name, balance, interestRate);
	}
	@Override
	public void calculateInterest() {
		balance += (int)(balance * getInterestRate() / 100);
	}
}
