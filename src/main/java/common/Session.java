package common;

import domain.Member;

public class Session {
    private static Session instance; //싱글톤 인스턴스
    private Member currentMember; //현재 로그인된 사용자

    private Session() {} //private 생성자를 통해 외부에서 인스턴스 생성 방지

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }


    public Long getCurrentMemberId() {return currentMember.getMemberId();}

    public Member getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }

    public void removeCurrentMember(){
        this.currentMember = null;
    }

    public boolean isAuthenticated() {
        return currentMember != null;
    }
}
