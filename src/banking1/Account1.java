package banking1;

import java.util.Scanner;

public class Account1 {
    private static final int MAX_ACCOUNTS = 50;
    private AccountInfo[] accountList;
    private int numOfAccounts;

    public Account1() {
        accountList = new AccountInfo[MAX_ACCOUNTS];
        numOfAccounts = 0;
    }

    interface MenuSelect {
        int MAKE = 1;
        int DEPOSIT = 2;
        int WITHDRAW = 3;
        int INQUIRE = 4;
        int EXIT = 5;
    }

    class AccountInfo {
        String accountNum;
        String name;
        int balance;

        AccountInfo(String accountNum, String name, int balance) {
            this.accountNum = accountNum;
            this.name = name;
            this.balance = balance;
        }
    }

    public void showMenu() {
        System.out.println("-----Menu------");
        System.out.println("1.계좌개설");
        System.out.println("2.입금");
        System.out.println("3.출금");
        System.out.println("4.전체계좌정보출력");
        System.out.println("5.프로그램종료");
        System.out.print("선택: ");
    }

    public void makeAccount() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***계좌개설***");
        System.out.print("계좌번호: ");
        String accountNum = scan.next();
        System.out.print("이름: ");
        String name = scan.next();
        System.out.print("잔액: ");
        int balance = scan.nextInt();

        AccountInfo account = new AccountInfo(accountNum, name, balance);
        accountList[numOfAccounts] = account;
        numOfAccounts++;

        System.out.println("계좌계설이 완료되었습니다.");
    }

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
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }

    public void showAccInfo() {
        System.out.println("***계좌정보출력***");
        for (int i = 0; i < numOfAccounts; i++) {
            System.out.println("-------------");
            System.out.println("계좌번호: " + accountList[i].accountNum);
            System.out.println("이름: " + accountList[i].name);
            System.out.println("잔고: " + accountList[i].balance);
        }
        System.out.println("-------------");
        System.out.println("전체계좌정보 출력이 완료되었습니다.");
    }

    private AccountInfo findAccount(String accountNum) {
        for (int i = 0; i < numOfAccounts; i++) {
            if (accountList[i].accountNum.equals(accountNum)) {
                return accountList[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Account1 account = new Account1();

        while (true) {
            account.showMenu();
            int choice = scan.nextInt();

            switch (choice) {
                case Account1.MenuSelect.MAKE:
                    account.makeAccount();
                    break;
                case Account1.MenuSelect.DEPOSIT:
                    account.depositMoney();
                    break;
                case Account1.MenuSelect.WITHDRAW:
                    account.withdrawMoney();
                    break;
                case Account1.MenuSelect.INQUIRE:
                    account.showAccInfo();
                    break;
                case Account1.MenuSelect.EXIT:
                    System.out.println("프로그램을 종료합니다.");
                    scan.close();
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}

