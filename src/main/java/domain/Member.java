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
        return "memberId=" + memberId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", home='" + home + '\'' +
                ", role=" + role;
    }
}
