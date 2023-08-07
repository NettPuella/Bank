package banking1;

import java.util.Scanner;

public class BankingSystemMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Account account = new Account();

        while (true) {
            account.showMenu();
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("***신규계좌개설***");
                    System.out.print("계좌번호 : ");
                    String accountNum = scan.next();
                    System.out.print("고객이름 : ");
                    String name = scan.next();
                    System.out.print("잔고 : ");
                    int balance = scan.nextInt();

                    account.makeAccount(accountNum, name, balance);
                    break;
                case 2:
                	account.depositMoney(); //입금
                    break;
                case 3:
                    account.withdrawMoney(); //출금
                    break;
                case 4:
                    account.showAccInfo(); //전체계좌정보 출력
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다."); //프로그램 종료
                    scan.close();
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}
