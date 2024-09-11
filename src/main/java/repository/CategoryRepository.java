package repository;

import domain.Category;
import domain.Item;
import exception.CustomDbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static repository.connection.DBConnectionUtil.*;

public class CategoryRepository {

    public Long findIdByCategoryName(String categoryName) {
        String sql = "SELECT category_id FROM category WHERE category_name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, categoryName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getLong("category_id");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
