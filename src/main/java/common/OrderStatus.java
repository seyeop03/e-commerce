package common;

public enum OrderStatus {
    //결제 대기중, 결제 완료, 상품 준비중, 배송중, 배송 완료
    PAYMENT_WAITING, PAYMENT_COMPLETED, PREPARING, DELIVER_BEFORE, DELIVER_AFTER;
}
