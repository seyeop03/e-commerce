package domain;

import common.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class Member {

    private Long memberId;
    private String username;
    private String password;
    private String name;
    private String birth;
    private String phone;
    private String email;
    private String address;
    private String home;
    private Role role;

    //== 정적 팩토리 메서드 ==//
    public static Member of(String username, String password, String name, String birth, String phone, String email, String address, String home) {
        return new Member(null, username, password, name, birth, phone, email, address, home, Role.USER);
    }

    public static Member of(String username, String password, String name, String birth, String phone, String email, String address, String home, Role role){
        return new Member(null, username, password, name, birth, phone, email, address, home, role);
    }

    @Override
    public String toString() {
        return "======================" +
                "아이디:" + username + '\n' +
                "이름:" + name + '\n' +
                "생년월일:" + birth + '\n' +
                "휴대폰번호:" + phone + '\n' +
                "이메일:" + email + '\n' +
                "주소:" + address + '\n' +
                "집전화:" + home + '\n' +
                "유저 역할:" + role;
    }
}
