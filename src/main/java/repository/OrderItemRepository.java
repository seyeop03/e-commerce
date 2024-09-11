package repository;

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

    public void save(OrderItem orderItem) {
        String sql = "INSERT INTO order_item(quantity,price,order_id,item_id) VALUES (?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,orderItem.getQuantity());
            pstmt.setInt(2,orderItem.getPrice());
            pstmt.setLong(3,orderItem.getOrderId());
            pstmt.setLong(4,orderItem.getItemId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn,pstmt,null);
        }

    }

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

    public int findPriceByOrderId(Long id) {
        String sql = "SELECT oi.price FROM order_item oi WHERE oi.order_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalPrice = 0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                totalPrice += rs.getInt("price");
            }
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn,pstmt,rs);
        }
        return totalPrice;
    }

    public void deleteByOrderId(Long id) {

        String sql = "DELETE FROM order_item WHRER order_id = ?";


        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn,pstmt,null);
        }

    }
}
