package controller;

import config.AppConfig;
import service.ItemService;
import service.MemberService;
import service.OrderService;

import java.sql.SQLException;
import java.util.Scanner;

public class AppController {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();
    ItemService itemService = appConfig.itemService();

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            displayMenu(); //메뉴 출력
            int choice = sc.nextInt(); //사용자 입력
            dispatch(choice, sc); //입력 값에 따라 서비스 호출
        }
    }

    private void displayMenu() {
        System.out.println("\n===== Welcome to coupong =====");
        System.out.println("1. 회원 관련 서비스");
        System.out.println("2. 주문 관련 서비스");
        System.out.println("3. 상품 관련 서비스");
        System.out.println("0. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }

    private void dispatch(int choice, Scanner sc) {
        switch (choice){
            case 1:
                memberService.handleMemberService(sc); //회원 서비스
                break;
            case 2 :
                itemService.handleItemService(sc); //상품 서비스 => 카테고리/장바구니/리뷰
                break;
            case 3 :
                orderService.handleOrderService(sc); //주문 서비스
                break;
            case 0:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }
}
