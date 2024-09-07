package repository;


import domain.Item;
import exception.CustomDbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static repository.connection.DBConnectionUtil.close;
import static repository.connection.DBConnectionUtil.getConnection;

public class ItemRepository {

    public void save(Item item) {
        String sql = "INSERT INTO item(name,price,manufacture_date,origin,company,size,color) values (?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,item.getName());
            pstmt.setInt(2,item.getPrice());
            pstmt.setString(3,item.getManufactureDate());
            pstmt.setString(4,item.getOrigin());
            pstmt.setString(5,item.getCompany());
            pstmt.setString(6,item.getSize());
            pstmt.setString(7,item.getColor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    public List<Item> findAll() {
        String sql = "SELECT * FROM item";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Item> itemList = new ArrayList<>();
            while (rs.next()){
                Item item = new Item(
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("manufacture_date"),
                        rs.getString("origin"),
                        rs.getString("company"),
                        rs.getString("size"),
                        rs.getString("color")
                );
                itemList.add(item);
            }
            return itemList;
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public Optional<Item> findById(Long id) {
        String sql = "SELECT * FROM item WHERE item_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);
            rs = pstmt.executeQuery();
            Item item = null;
            if (rs.next()) {
                item = new Item(
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("manufacture_date"),
                        rs.getString("origin"),
                        rs.getString("company"),
                        rs.getString("size"),
                        rs.getString("color")
                );
            }
            return Optional.ofNullable(item);
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM item WHERE item_id = ?";
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

    public void updateById(Long id, Item item) {
        String sql = "UPDATE item " +
                "SET name = ?, price = ?, manufacture_date = ?, origin = ?, company = ?, size = ?, color = ? " +
                "WHERE item_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setInt(2, item.getPrice());
            pstmt.setString(3, item.getManufactureDate());
            pstmt.setString(4, item.getOrigin());
            pstmt.setString(5, item.getCompany());
            pstmt.setString(6, item.getSize());
            pstmt.setString(7, item.getColor());
            pstmt.setLong(8, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }
}
