package banking2;

import java.util.Scanner;



public class Account {
    private static final int MAX_ACCOUNTS = 50;
    private AccountInfo[] accountList;
    private int numOfAccounts;

    public Account() {
        accountList = new AccountInfo[MAX_ACCOUNTS];
        numOfAccounts = 0;
    }

    // AccountInfo 클래스로 계좌 정보를 저장하는 내부 클래스
    class AccountInfo {
        String accountNum; // 계좌번호
        String name; // 이름
        int balance; // 잔액

        AccountInfo(String accountNum, String name, int balance) {
            this.accountNum = accountNum;
            this.name = name;
            this.balance = balance;
        }
    }

    // 메뉴 출력 메소드
    public void showMenu() {
        System.out.println("-----Menu------");
        System.out.println("1.계좌개설");
        System.out.println("2.입금");
        System.out.println("3.출금");
        System.out.println("4.전체계좌정보출력");
        System.out.println("5.프로그램종료");
        System.out.print("선택: ");
    }

    // 계좌 개설 메소드
    public void makeAccount(String accountNum, String name, int balance) {
        AccountInfo account = new AccountInfo(accountNum, name, balance);
        accountList[numOfAccounts] = account;
        numOfAccounts++;
        System.out.println("계좌계설이 완료되었습니다.");
    }
    //입금 메소드
    public void depositMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***입   금***");
        System.out.print("계좌번호: ");
        String accountNum = scan.next();
        System.out.print("입금액: ");
        int depositAmount = scan.nextInt();

        AccountInfo account = findAccount(accountNum);
        if (account != null) {
            account.balance += depositAmount;
            System.out.println("입금이 완료되었습니다.");
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }
    
  
	//출금 메소드
    public void withdrawMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***출   금***");
        System.out.print("계좌번호: ");
        String accountNum = scan.next();
        System.out.print("출금액: ");
        int withdrawAmount = scan.nextInt();

        AccountInfo account = findAccount(accountNum);
        if (account != null) {
            if (account.balance >= withdrawAmount) {
                account.balance -= withdrawAmount;
                System.out.println("출금이 완료되었습니다.");
            } else {
                System.out.println("잔고가 부족합니다.");
            }
        } 
        else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }
    

	// 전체 계좌 정보 출력 메소드
    public void showAccInfo() {
        System.out.println("***계좌정보출력***");
        for (int i = 0; i < numOfAccounts; i++) {
            System.out.println("-------------");
            System.out.println("계좌번호 : " + accountList[i].accountNum);
            System.out.println("고객이름 : " + accountList[i].name);
            System.out.println("잔고 : " + accountList[i].balance);
        }
        System.out.println("-------------");
        System.out.println("전체계좌정보 출력이 완료되었습니다.");
    }
    private AccountInfo findAccount(String accountNum) {
        for (int i = 0; i < numOfAccounts; i++) {
            if (accountList[i].accountNum.equals(accountNum)) {
                return accountList[i]; // 계좌 번호가 일치하면 해당 계좌 정보 반환
            }
        }
        return null; // 계좌 번호가 일치하지 않으면 null 반환
    }
    
}
