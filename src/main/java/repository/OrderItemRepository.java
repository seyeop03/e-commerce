package repository;

import domain.Item;
import domain.OrderItem;
import exception.CustomDbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static repository.connection.DBConnectionUtil.*;

public class OrderItemRepository {

    public List<OrderItem> findAll() {
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        return null;
    }

    public List<OrderItem> findByUsername() {
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        return null;
    }

    public List<OrderItem> findByOrderId(Long id) {
        String sql = "SELECT * FROM order_item WHERE order_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);
            rs = pstmt.executeQuery();

            List<OrderItem> orderItems = new ArrayList<>();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem(
                        rs.getLong("order_item_id"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getLong("order_id"),
                        rs.getLong("item_id")
                );
                orderItems.add(orderItem);
            }
            return orderItems;
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public boolean existsByItemIdAndMemberId(Long itemId, Long memberId) {
        String sql = "SELECT EXISTS (" +
                "SELECT 1 FROM order_item oi " +
                "INNER JOIN order o " +
                "ON o.order_id = oi.order_id " +
                "WHERE item_id = ? AND member_id = ?)";
        // 리뷰의 아이템 아이디와 오더아이템의 아이템 아이디가 같아야한다.
        // 멤버의 멤버아이디와 오더의 멤버 아이디가 같아야 한다.
        // 오더 아이디를 조인해야 order item 아이디가 의미가 있기 때문

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, itemId);
            pstmt.setLong(2, memberId);
            rs = pstmt.executeQuery();

            rs.next();
            if (rs.getBoolean(1)) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
