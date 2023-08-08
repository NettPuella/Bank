package banking3;

//////////////신용신뢰계좌////////////////////

public class HighCreditAccount extends Account{
	private String creditRating;
	
	public HighCreditAccount(String accountNum, String name, int balance, 
			double interestRate, String creditRating) {
			super(accountNum, name, balance, interestRate);
			this.creditRating  =  creditRating;
	}
	public String getCreditRating() {
		return creditRating;
	}
	@Override
	//이자계산
	public void calculateInterest() {
		double additionalInterestRate = 0;
		switch (creditRating) {
		case "A":
			additionalInterestRate = 0.07;
			break;
		case "B":
			additionalInterestRate = 0.04;
			break;
		case "C":
			additionalInterestRate = 0.02;
			break;
		}
		balance += (int)(balance * (getInterestRate() + additionalInterestRate) / 100);

	}	
}
