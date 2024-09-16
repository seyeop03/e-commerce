package controller;

import common.Role;
import common.Session;
import domain.Member;
//import service.ItemAdminService;
import config.AppConfig;
import service.ItemService;
import service.MemberService;
import service.OrderService;

import java.util.Scanner;

import static common.UserInput.*;

public class AppController {

    AppConfig appConfig = AppConfig.getInstance();
    MemberService memberService = appConfig.getMemberService();
    OrderService orderService = appConfig.getOrderService();
    ItemService itemService = appConfig.getItemService();
//    ItemAdminService itemAdminService = appConfig.getItemAdminService();

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
                displayMenu(); //메뉴 출력
                int choice = inputInt("메뉴를 선택하세요: ", sc); //사용자 입력
                dispatch(choice, sc); //입력 값에 따라 서비스 호출
            }
        }

    private void displayMenu() {
        System.out.println("\n===== Welcome to coupong =====");
        System.out.println("1. 회원 서비스");
        System.out.println("2. 주문 서비스");
        System.out.println("3. 상품 서비스");
        System.out.println("0. 종료");
    }

    private void dispatch(int choice, Scanner sc) {
        switch (choice){
            case 1 :
                memberService.handleMemberService(sc); //회원 서비스
                break;
            case 2 :
                if (Session.getInstance().getCurrentMember() != null) {
                    orderService.handleOrderService(sc); //주문 서비스
                } else {
                    System.out.println("로그인 이후 주문 서비스를 이용할 수 있습니다.");
                }
                break;
            case 3 :
                itemService.handleItemService(sc); //상품 서비스
                break;
            case 0 :
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }
}
