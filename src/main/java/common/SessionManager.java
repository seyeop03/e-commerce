//package common;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class SessionManager {
//
//    private static SessionManager instance; //세션 매니저는 싱글톤 인스턴스
//
//    //UUID, <LOGIN_MEMBER, Member> / UUID, <MEMBER_CART, CART> => UUID, <SESSION_CONST, 객체>
//    private static Map<String, Map<String, Object>> sessionStore = new ConcurrentHashMap<>();
//
//    public SessionManager getInstance() {
//        if (instance == null) {
//            return new SessionManager();
//        }
//        return instance;
//    }
//
//    public void createSession() {
//
//    }
//
//    public Map<> getSession(String sessionId) {
//        sessionStore.get(sessionId);
//    }
//
//    public void removeSession(String sessionId) {
//
//    }
//}
