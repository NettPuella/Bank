package banking3;

/////////////////계좌정보 입출금 관리//////////////////////

import java.util.Scanner;

//추상메서드 
public abstract class Account {
    private String accountNum; //계좌번호
    private String name; //이름
    protected  int balance; //잔액
    protected  double interestRate; //이자율

    public Account(String accountNum, String name, int balance, double interestRate) {
        this.accountNum = accountNum;
        this.name = name;
        this.balance = balance;
        this.interestRate = interestRate;
    

    }
    //출금
    public void withdraw(int amount) {
    	if(balance >= amount) {
    		balance -= amount;
    		calculateInterest();
    		System.out.println("출금이 완료되었습니다.");
    	}
    	else {
    		System.out.println("잔고가 부족합니다.");
    	}
    }
    //입금
    public void deposit(int amount) {
    	if(amount > 0) {
    		balance += amount;
    		System.out.println("입금이 완료되었습니다.");
    	}
    	else {
    		System.out.println("유효하지 않은 입금액입니다.");
    	}
    }
    //이자 계산은 하위 클래스(HighCreditAccount)에서 구현
    
    
//    public void calculateInterest() { //하위 클래스에서 오버라이드 하여 구현	
//    }
    public abstract void calculateInterest();//추상메서드
    
    public String getAccountNum() {
    	return accountNum; 
    }
    public String getName() {
    	return name;
    }
    public int getBalance() {
    	return balance;
    }
    public double getInterestRate() {
    	return interestRate;
    }   
}

















