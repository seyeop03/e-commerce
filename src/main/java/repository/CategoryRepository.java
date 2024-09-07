package repository;

import domain.Category;
import exception.CustomDbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static repository.connection.DBConnectionUtil.*;

public class CategoryRepository {

    public List<Category> findAll() {
        String sql = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Category> categoryList = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3)
                );
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
