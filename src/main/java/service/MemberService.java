package service;

import common.Role;
import common.Session;
import domain.Member;
import repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

//문제: 서비스 계층과 리포지토리 계층으로만 나누었는데,
//만약 특정 서비스에서 다른 서비스의 기능을 호출하고 싶을 때 의존 관계 어떻게 할까?
//해결: 컨트롤러 계층을 따로 분리한다.
public class MemberService {

    private final MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //== 회원 서비스 핸들러 ==//
    public void handleMemberService(Scanner sc) {
        while (true) {
            displayMemberMenu();
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            Session session = Session.getInstance();

            switch (choice) {
                case 1:
                    if (session.isAuthenticated()) {
                        System.out.println(session.getCurrentMember().getName() + "님, 이미 로그인되었습니다.");
                    } else {
                        signIn(sc); //로그인
                    }
                    break;
                case 2:
                    if (!session.isAuthenticated()) {
                        System.out.println("로그인도 안했네요.");
                    } else {
                        signOut(); //로그아웃
                    }
                    break;
                case 3:
                    signUp(sc); //회원가입
                    break;
                case 4:
                    if (!session.isAuthenticated()) {
                        System.out.println("로그인 상태가 아닙니다. 로그인 후 진행해주세요.");
                    } else {
                        viewMemberInfo(); //회원 정보 조회
                    }
                    break;
                case 5:
                    if (!session.isAuthenticated()) {
                        System.out.println("로그인 상태가 아닙니다. 로그인 후 진행해주세요.");
                    } else {
                        updateMemberInfo(sc); //회원 정보 수정
                    }
                    break;
                case 6:
                    if (!session.isAuthenticated()) {
                        System.out.println("로그인 상태가 아닙니다. 로그인 후 진행해주세요.");
                    } else {
                        deleteMember(sc); //회원 탈퇴
                    }
                    break;
                case 7:
                    Member currentMember = session.getCurrentMember();
                    if (currentMember != null && currentMember.getRole().equals(Role.ADMIN)) {
                        //회원 전체 조회
                        memberRepository.findAll()
                                .stream()
                                .forEach(m -> System.out.println(m));
                    } else {
                        System.out.println("잘못된 입력입니다. 0~7 사이의 숫자를 입력하세요.");
                    }
                    break;
                case 0:
                    System.out.println("회원 서비스를 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 0~7 사이의 숫자를 입력하세요.");
            }
        }
    }

    private void displayMemberMenu() {
        System.out.println();
        System.out.println("1. 회원 로그인");
        System.out.println("2. 회원 로그아웃");
        System.out.println("3. 회원 가입");
        System.out.println("4. 회원 정보 조회");
        System.out.println("5. 회원 정보 수정");
        System.out.println("6. 회원 탈퇴");
        Member currentMember = Session.getInstance().getCurrentMember();
        if (currentMember != null && currentMember.getRole().equals(Role.ADMIN))
            System.out.println("7. 회원 전체 조회 (관리자)");
        System.out.println("0. 뒤로 가기");
        System.out.print("선택: ");
    }

    //== 회원 가입 ==//
    public void signUp(Scanner sc) {
        System.out.print("아이디: ");
        String username = sc.next();
        System.out.print("패스워드: ");
        String password = sc.next();
        password = passwordEncoder.encode(password);
        System.out.print("이름: ");
        String name = sc.next();
        System.out.print("생년월일: ");
        String birth = sc.next();
        System.out.print("휴대폰 번호: ");
        String phone = sc.next();
        System.out.print("이메일: ");
        String email = sc.next();
        System.out.print("주소: ");
        String address = sc.next();
        System.out.print("집 전화: ");
        String home = sc.next();

        Member member = Member.of(username, password, name, birth, phone, email, address, home, Role.USER);

        //ID 중복 확인
        if (!isUsernameDuplicated(username)) {
            memberRepository.save(member);
        }
    }

    private boolean isUsernameDuplicated(String username) {
        try {
            memberRepository.findByUsername(username)
                    .ifPresent(m -> {throw new IllegalArgumentException();});
        } catch (RuntimeException e) {
            System.out.println("이미 존재하는 아이디입니다.");
            return true;
        }
        return false;
    }

    //== 회원 로그인 ==//
    public void signIn(Scanner sc) {
        System.out.print("아이디를 입력해주세요: ");
        String username = sc.nextLine();
        System.out.print("비밀번호를 입력해주세요: ");
        String password = sc.nextLine();

        Member findMember = memberRepository.findByUsername(username).orElse(null);
        if (findMember == null) {
            System.out.println("존재하지 않는 아이디입니다.");
            return;
        }
        //System.out.println(password + " " + findMember.getPassword());

        if (passwordEncoder.matches(password, findMember.getPassword())) {
            System.out.println("로그인에 성공하였습니다.");
            Session.getInstance().setCurrentMember(findMember);
        } else {
            System.out.println("패스워드가 일치하지 않습니다.");
        }
    }

    //== 회원 로그아웃 ==//
    public void signOut() {
        Session.getInstance().removeCurrentMember();
        System.out.println("로그아웃 되었습니다.");
    }

    //== 전체 회원 조회 ==//
    public List<Member> showAllMembers() {
        return memberRepository.findAll();
    }

    //== 회원 조회 ==//
    public void viewMemberInfo() {
        Member currentMember = Session.getInstance().getCurrentMember();
        System.out.println("아이디:" + currentMember.getUsername());
        System.out.println("이름:" + currentMember.getName());
        System.out.println("주소:" + currentMember.getAddress());
        System.out.println("집:" + currentMember.getHome());
        System.out.println("이메일:" + currentMember.getEmail());
        System.out.println("생년월일:" + currentMember.getBirth());
        System.out.println("유저 역할:" + currentMember.getRole());
    }

    //== 회원 수정 ==//
    public void updateMemberInfo(Scanner sc) {
        System.out.print("아이디: ");
        String username = sc.next();
        System.out.print("패스워드: ");
        String password = sc.next();
        System.out.print("이름: ");
        String name = sc.next();
        System.out.print("생년월일: ");
        String birth = sc.next();
        System.out.print("휴대폰 번호: ");
        String phone = sc.next();
        System.out.print("이메일: ");
        String email = sc.next();
        System.out.print("주소: ");
        String address = sc.next();
        System.out.print("집 전화: ");
        String home = sc.next();

        Member member = Member.of(username, password, name, birth, phone, email, address, home, Role.USER);
        memberRepository.updateById(Session.getInstance().getCurrentMember().getMemberId(), member);
    }

    //== 회원 탈퇴 ==//
    public void deleteMember(Scanner sc) {
        //로그인 돼있을 경우 현재 세션 정보 미리 받아두고, 로그아웃 이후 탈퇴 진행
        Member currentMember = Session.getInstance().getCurrentMember();
        Long memberId = currentMember.getMemberId();
        System.out.println("회원탈퇴를 하시려면 \"" + currentMember.getUsername() + "\"를 입력해주세요.");
        String input = sc.next();

        //입력 문자열이 일치하면 로그아웃 이후 탈퇴 진행
        if (input.equals(currentMember.getUsername())) {
            Session.getInstance().setCurrentMember(null); //로그아웃
            memberRepository.deleteById(memberId);
        } else {
            System.out.println("입력 문자열이 일치하지 않습니다.\n");
        }
    }
}
