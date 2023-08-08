package banking3;

////////////컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다/////////////

import java.util.Scanner;
import java.util.InputMismatchException;

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
		
		
		/* int choice = -1;로 변경한 이유는 사용자의 선택을 입력 받을 때, 입력이 잘못된 경우 예외 처리를 위한 초기화를 한 것.
		원래대로라면 sc.nextInt();로 입력을 받을 때, 정상적인 정수가 입력되어야 합니다. 그러나 사용자가 정수 대신 문자를 입력하면 
		InputMismatchException이 발생함. 이때 초기화되지 않은 choice 변수가 그대로 남아있게 되면, 예외 처리 후에도 
		잘못된 값으로 남아있을 수 있음. 따라서 먼저 choice 변수를 -1로 초기화해둠으로써, 예외 발생 시 기본적인 초기값을 사용하도록
		하여 프로그램의 안정성을 높이고자 한 것입니다. 이후 사용자 입력이 정상적인 경우 choice 변수에 올바른 값을 할당하게 됨. */
		int choice = -1; //입력된 값이 잘못되었을 경우 예외처리를 위한 초기화
		
		try {
			choice = sc.nextInt();
		} 
		catch (InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			sc.nextLine(); // 입력버퍼 비우기
			return;
		}
		
		
		//계좌등록
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
		
		Account account = findAccount(accountNum);
		if(account != null) {
			int amount = -1;
			
			while (amount < 0) {
				try {
					System.out.println("입금액:");
					amount = sc.nextInt();
					if(amount < 0) {
						System.out.println("음수를 입금할 수 없습니다.");
					}
					else if (amount % 500 != 0) {
						System.out.println("입금액은 500원 단위로 가능합니다.");
						amount = -1; //조건에 맞지 않을 경우 재입력을 위해 초기화
					}
				} 
				catch (InputMismatchException e) {
					System.out.println("잘못된 입력입니다. 숫자로 입력해주세요.");
					sc.nextLine();
				}
			}
			account.deposit(amount);
			account.calculateInterest();
			System.out.println("입금 및 이자 계산이 완료되었습니다.");
		}
		else {
			System.out.println("계좌를 찾을 수 없습니다.");
		}
		
		System.out.println("입금액:");
		int amount = sc.nextInt();
		
//		Account account = findAccount(accountNum);
//		if(account != null) {
//			account.deposit(amount);
//			account.calculateInterest(); //이자계산
//			System.out.println("입금 및 이자 계산이 완료되었습니다.");
//		}
//		else {
//			System.out.println("계좌를 찾을 수 없습니다.");
//		}
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
