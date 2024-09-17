package repository;

import domain.Review;
import exception.CustomDbException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 이 부분이 connection 을 import 하는 부분
import static repository.connection.DBConnectionUtil.*;

public class ReviewRepository {

    // (제품별)리뷰보기 -> 정렬기준(최신순/별점 높은순/별점 낮은순)
    public List<Review> findAllByItemIdAndSort(Long itemId, int sort) {
        String sql = "SELECT *" +
                " FROM review" +
                " WHERE item_id = ?" +
                " ORDER BY ";
        switch (sort) {
            case 1:
                sql += "date DESC"; // 최신순
                break;
            case 2:
                sql += "stars DESC"; // 별점 높은순
                break;
            case 3:
                sql += "stars ASC"; // 별점 낮은순
                break;
            default:
                sql += "date DESC"; // 기본값 최신순
                break;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, itemId);
            rs = pstmt.executeQuery();

            List<Review> itemReviewList = new ArrayList<>();
            while(rs.next()){
                Review review = new Review(
                        rs.getLong("review_id"),
                        rs.getInt("stars"),
                        rs.getString("contents"),
                        rs.getString("date"),
                        rs.getLong("member_id"),
                        rs.getLong("item_id")
                );
                itemReviewList.add(review);
            }
            return itemReviewList;
        } catch (SQLException e){
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<Review> findByMemberId(Long id, int sort) {
        String sql = "SELECT * " +
                "FROM review " +
                "WHERE member_id = ? " +
                "ORDER BY ";
        switch (sort) {
            case 1:
                sql += "date DESC"; // 최신순
                break;
            case 2:
                sql += "stars DESC"; // 별점 높은순
                break;
            case 3:
                sql += "stars ASC"; // 별점 낮은순
                break;
            default:
                sql += "date DESC"; // 기본값 최신순
                break;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            List<Review> itemReviewList = new ArrayList<>();
            while(rs.next()){
                Review review = new Review(
                        rs.getLong("review_id"),
                        rs.getInt("stars"),
                        rs.getString("contents"),
                        rs.getString("date"),
                        rs.getLong("member_id"),
                        rs.getLong("item_id")
                );
                itemReviewList.add(review);
            }
            return itemReviewList;
        } catch (SQLException e){
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<Review> findByItemId(Long itemId) {
        String sql = "SELECT * FROM review WHERE item_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, itemId);
            rs = pstmt.executeQuery();

            List<Review> itemReviewList = new ArrayList<>();
            while(rs.next()){
                Review review = new Review(
                        rs.getLong("review_id"),
                        rs.getInt("stars"),
                        rs.getString("contents"),
                        rs.getString("date"),
                        rs.getLong("member_id"),
                        rs.getLong("item_id")
                );
                itemReviewList.add(review);
            }
            return itemReviewList;
        } catch (SQLException e){
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }


    // 리뷰작성
    public void save(Review review) {
        String sql = "INSERT INTO review(stars, contents, date, member_id, item_id) VALUES(?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            String date = LocalDate.now().toString();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, review.getStars());
            pstmt.setString(2, review.getContents());
            pstmt.setString(3, date);
            pstmt.setLong(4, review.getMemberId());
            pstmt.setLong(5, review.getItemId());
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
            pstmt.setLong(4, id);
            pstmt.setInt(1, review.getStars());
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

    public boolean existsByIdAndMemberId(Long reviewId, Long memberId){
        String sql = "SELECT EXISTS(" +
                "SELECT 1 " +
                "FROM review " +
                "WHERE review_id = ? AND member_id = ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, reviewId);
            pstmt.setLong(2, memberId);
            rs = pstmt.executeQuery();

            rs.next();
            return rs.getBoolean(1);
        }
        catch (SQLException e){
            throw new CustomDbException(e);
        }
        finally {
            close(conn, pstmt, rs);
        }
    }

    // 회원리뷰의 존재여부 확인
    public boolean existByReviewAndMemberId(Long memberId){
        String sql ="SELECT EXISTS(" +
                "SELECT 1 " +
                "FROM review " +
                "WHERE member_id = ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();

            rs.next();
            return rs.getBoolean(1);
        }
        catch (SQLException e){
            throw new CustomDbException(e);
        }
        finally {
            close(conn, pstmt, rs);
        }
    }

}
