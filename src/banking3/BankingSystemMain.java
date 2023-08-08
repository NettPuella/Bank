package banking3;

import java.util.Scanner;

public class BankingSystemMain {
    public static void main(String[] args) {
        
    	AccountManager manager = new AccountManager();
    	Scanner sc = new Scanner(System.in);
    	
    	while(true) {
    		manager.showMenu();
    		int choice = sc.nextInt();
    		
    		switch(choice) {
    		case 1:
    			manager.createAccount();
    			break;
    		case 2:
    			manager.depositMoney();
    			break;
    		case 3:
    			manager.withdrawMoney();
    			break;
    		case 4:
    			manager.showAllAccounts();
    			break;
    		case 5:
    			System.out.println("프로그램을 종료합니다.");
    			sc.close();
    			return;
    		default:
    			System.out.println("잘못된 선택입니다. 다시 선택해 주세요");
    		}
    	}
    }
}
