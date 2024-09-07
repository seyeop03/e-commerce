package repository;

import domain.Cart;
import exception.CustomDbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static repository.connection.DBConnectionUtil.*;

public class CartRepository {

    public void save(Cart cart) {
        String sql = "INSERT INTO cart(member_id) VALUES (?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, cart.getMemberId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt,null);
        }
    }

    public Optional<Cart> findById(Long id) {
        String sql = "SELECT * FROM item WHERE cart_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            Cart cart = null;
//        if (rs.next()) {
//            cart = new Cart(
//                rs.getLong("cart_id")
//            );
//        }
            return Optional.ofNullable(cart);
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn,pstmt,rs);
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM cart WHERE cart_id =?";
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

    public void updateById(Long id, Cart cart) {
        String sql = "UPDATE item " +
                "SET name = ?, price = ?, manufacture_date = ?, origin = ?, company = ?, size = ?, color = ? " +
                "WHERE item_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, cart.getMemberId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }
}
