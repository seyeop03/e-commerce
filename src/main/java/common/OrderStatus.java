package common;

public enum OrderStatus {
    //결제 대기중, 결제 완료, 상품 준비중, 배송중, 배송 완료
    PAYMENT_WAITING(1), PAYMENT_COMPLETED(2), PREPARING(3), DELIVER_BEFORE(4), DELIVER_AFTER(5);

    private final int statusNum;

    OrderStatus(int statusNum) {
        this.statusNum = statusNum;
    }

    public int getStatusNum() {
        return statusNum;
    }
}
