package repository;

import domain.Item;
import exception.CustomDbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static repository.connection.DBConnectionUtil.close;
import static repository.connection.DBConnectionUtil.getConnection;

public class CategoryItemRepository {

    public List<Item> findItemsByCategoryId(Long categoryId) {
        String sql = "SELECT i.* " +
                "FROM category_item ci INNER JOIN item i " +
                "ON ci.item_id = i.item_id " +
                "WHERE category_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, categoryId);
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
}
