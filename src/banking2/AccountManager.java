package banking2;

////////////컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다/////////////

import java.util.Scanner;

public class AccountManager {
	//계좌개설 갯수
	private static final int MAX_ACCOUNTS = 50;
	private Account[]accountList;
	private int numOfAccounts;
	
	public AccountManager() {
		accountList = new Account[MAX_ACCOUNTS];
		numOfAccounts = 0;
	}
	//기본출력정보
	public void showMenu() {
		System.out.println("-------Menu-------");
		System.out.println("1.계좌개설");
		System.out.println("2.입금");
		System.out.println("3.출금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료");
		System.out.println("선택:");
	}
	//계좌개설
	public void createAccount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("******신규계좌개설******");
		System.out.println("------계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.println("선택:");
		
		//선택받을 변수생성
		int choice = sc.nextInt();
		
		//계좌정보를 받는다
		System.out.println("계좌번호:");
		String accountNum = sc.next();
		System.out.println("고객이름:");
		String name = sc.next();
		System.out.println("잔고:");
		int balance = sc.nextInt();
		
		switch(choice) {
			case 1:
				System.out.print("기본이자%(정수형태로입력):");
				double normalInterestRate = sc.nextDouble();
				accountList[numOfAccounts] = 
						new NormalAccount(accountNum, name, balance, normalInterestRate);
				break;
			case 2:
				System.out.println("기본이자%(정수형태로입력):");
				double highCreditInterestRate = sc.nextDouble();
				System.out.println("신용등급(A,B,C등급):");
				String creditRating = sc.next();
				accountList[numOfAccounts] = 
						new HighCreditAccount(accountNum, name, balance, highCreditInterestRate, creditRating);
				break;
			default:
				System.out.println("잘못된 선택입니다.");
				return;
		}
		numOfAccounts++;
		System.out.println("계좌개설이 완료되었습니다.");
	}
	//입금
	public void depositMoney() {
		Scanner sc = new Scanner(System.in);
		System.out.println("******입 금*****");
		System.out.println("계좌번호:");
		String accountNum = sc.next();
		System.out.println("입금액:");
		int amount = sc.nextInt();
		
		Account account = findAccount(accountNum);
		if(account != null) {
			account.deposit(amount);
			account.calculateInterest(); //이자계산
			System.out.println("입금 및 이자 계산이 완료되었습니다.");
		}
		else {
			System.out.println("계좌를 찾을 수 없습니다.");
		}
	}
	//출금
	public void withdrawMoney() {
		Scanner sc = new Scanner(System.in);
		System.out.println("*****출 금******");
		System.out.println("계좌번호:");
		String accountNum = sc.next();
		System.out.println("출금액:");
		int amount = sc.nextInt();
		
		Account account = findAccount(accountNum);
		if(account != null) {
			account.withdraw(amount);
		}
		else {
			System.out.println("계좌를 찾을 수 없습니다.");
		}
	}
	//전체계좌정보 출력
	public void showAllAccounts() {
		System.out.println("******계좌정보출력******");
		for (int i = 0; i < numOfAccounts; i++) {
			Account account = accountList[i];
			System.out.println("--------------");
			System.out.println("계좌번호>" + account.getAccountNum());
			System.out.println("고객이름>" + account.getName());
			System.out.println("잔고>" + account.getBalance());
			System.out.println("기본이자>" + account.getInterestRate() + "%");
			if(account instanceof HighCreditAccount) {
				HighCreditAccount highCreditAccount = (HighCreditAccount)account;
				System.out.println("신용등급>" + highCreditAccount.getCreditRating());
			}
		}
		System.out.println("-----------------");
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	private Account findAccount(String accountNum) {
		for (int i = 0 ; i < numOfAccounts ; i++) {
			if(accountList[i].getAccountNum().equals(accountNum)) {
				return accountList[i];
			}
		}
		return null;
	}
	

}
