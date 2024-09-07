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
}
