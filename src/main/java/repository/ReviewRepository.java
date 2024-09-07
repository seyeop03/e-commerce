package repository;

import domain.Member;
import domain.Review;
import exception.CustomDbException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// 이 부분이 connection 을 import 하는 부분
import static repository.connection.DBConnectionUtil.*;

public class ReviewRepository {

    // 리뷰보기
    public void findxxx() {
        String sql = "SELECT * FROM review";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Review> list = new ArrayList<>();
            while(rs.next()){
                Review review = new Review(
                    rs.getLong("review_id"),
                    rs.getInt("star"),
                    rs.getString("contents"),
                    rs.getString("date"),
                    rs.getLong("member_id"),
                    rs.getLong("item_id")
                );
                list.add(review);
            }
        } catch (SQLException e){
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    // 리뷰작성
    public void save(Review review) {
        String sql = "INSERT INTO review(stars, contents, date) VALUES(?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            LocalDateTime localDate = LocalDateTime.now();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, review.getStar());
            pstmt.setString(2, review.getContents());
            pstmt.setObject(3, localDate);
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    // 리뷰수정
    public void updateById(Long id, Review review) {
        String sql = "UPDATE review SET stars=?, contents=?, date=? WHERE review_id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            LocalDateTime localDateTime = LocalDateTime.now();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(4, review.getReviewId());
            pstmt.setInt(1, review.getStar());
            pstmt.setString(2, review.getContents());
            pstmt.setObject(3, localDateTime);
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    // 리뷰삭제
    public void deleteById(Long id) {
        String sql = "DELETE FROM review WHERE review_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

}
