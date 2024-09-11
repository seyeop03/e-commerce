package repository;

import common.OrderStatus;
import domain.Order;
import exception.CustomDbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static repository.connection.DBConnectionUtil.close;
import static repository.connection.DBConnectionUtil.getConnection;

public class OrderRepository {

    public Long save(Order order) {
        String sql = "INSERT INTO orders(date, status, member_id) VALUES (?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
//        Long orderId = 0L;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,order.getDate());
            pstmt.setString(2,order.getStatus().name());
            pstmt.setLong(3,order.getMemberId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderId(rs.getLong(1));
            }
            return order.getOrderId();

        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public Long getOrderPk(Order order) {
        String sql = "INSERT INTO orders(status, member_id) values (?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,order.getStatus().name());
            pstmt.setLong(2,order.getMemberId());

        } catch (SQLException e) {
            throw new CustomDbException(e);
        }
        return order.getOrderId();
    }

    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Order> orderList = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order(
                        rs.getLong("order_id"),
                        rs.getString("date"),
                        rs.getInt("total_price"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getLong("member_id")
                );
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn,pstmt,rs);
        }
    }

    public Optional<Order> findById(Long id) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            Order order = null;
            if (rs.next()) {
                order = Order.of(
                        rs.getString("date"),
//                        rs.getInt("total_price"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getLong("member_id")
                );
            }
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<Order> findByMemberId(Long id) {
        String sql = "SELECT * FROM orders WHERE member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);
            rs = pstmt.executeQuery();

            List<Order> orderList = new ArrayList<>();
            while (rs.next()){
                Order order = Order.of(
                        rs.getString("date"),
//                        rs.getInt("total_price"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getLong("member_id")
                );
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }


    public void updateById(Long id, OrderStatus status) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status.name());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
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
            close(conn, pstmt, null);
        }
    }
}
