//package common;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class SessionManager {
//
//    //ThreadLocal 로 각 스레드별 Session 인스턴스를 저장
//    private static final ThreadLocal<Session> threadLocalSession = new ThreadLocal<>();
//
//    //현재 스레드에 대한 세션 반환
//    public static Session getSession() {
//        Session session = threadLocalSession.get();
//        if (session == null) {
//            session = new Session();
//            threadLocalSession.set(session);
//        }
//        return session;
//    }
//
//    //현재 스레드의 세션 삭제
//    public static void removeSession() {
//        threadLocalSession.remove();
//    }
//
//}
