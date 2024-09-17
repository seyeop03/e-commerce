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
                item = Item.of(
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

    public List<Item> findByKeyword(String keyword) {

        String sql = "SELECT * FROM item WHERE item.name like ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,"%" + keyword + "%");

            rs = pstmt.executeQuery();

            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item(
                        rs.getLong("item_id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("manufacture_date"),
                        rs.getString("origin"),
                        rs.getString("company"),
                        rs.getString("size"),
                        rs.getString("color")
                );
                items.add(item);
            }
            return items;
        } catch (SQLException e){
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<Item> findByKeywordWithPagination(String keyword, int pageNumber, int pageSize) {

        String sql = "SELECT * FROM item WHERE name LIKE ? LIMIT ? OFFSET ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setInt(2, pageSize);
            pstmt.setInt(3, (pageNumber - 1) * pageSize);

            rs = pstmt.executeQuery();

            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item(
                        rs.getLong("item_id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("manufacture_date"),
                        rs.getString("origin"),
                        rs.getString("company"),
                        rs.getString("size"),
                        rs.getString("color")
                );
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public int totalCount(String keyword) {
        String sql = "SELECT COUNT(*) FROM item WHERE name LIKE ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            rs = pstmt.executeQuery();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public int findItemPriceById(Long id) {
        String sql = "SELECT price FROM item WHERE item_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int price = 0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                price = rs.getInt("price");
            }
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn,pstmt,rs);
        }
        return price;
    }
}
