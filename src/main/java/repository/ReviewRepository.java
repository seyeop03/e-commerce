package repository;

import domain.Review;
import exception.CustomDbException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 이 부분이 connection 을 import 하는 부분
import static repository.connection.DBConnectionUtil.*;

public class ReviewRepository {

    // (제품별)리뷰보기 -> 정렬기준(최신순/별점 높은순/별점 낮은순)
    public void findByAll(Long itemId, int sort) {
        String sql = "SELECT *" +
                " FROM review r" +
                " WHERE r.item_id = ?" +
                " order by ";
        switch (sort) {
            case 1:
                sql += "r.date DESC"; // 최신순
                break;
            case 2:
                sql += "r.star DESC"; // 별점 높은순
                break;
            case 3:
                sql += "r.star ASC"; // 별점 낮은순
                break;
            default:
                sql += "r.date DESC"; // 기본값 최신순
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
                        rs.getInt("star"),
                        rs.getString("contents"),
                        rs.getString("date"),
                        rs.getLong("member_id"),
                        rs.getLong("item_id")
                );
                itemReviewList.add(review);
            }
        }
        catch (SQLException e){
            throw new CustomDbException(e);
        }
        finally {
            close(conn, pstmt, rs);
        }
    }


    // (나의)리뷰보기
    public void findReviewById(Long memberId) {
        String sql = "select *" +
                " from review r" +
                " where r.member_id = ?" +
                " order by r.date desc";

    }
    // 리뷰보기
    public List<Review> findByMemberId(Long id) {
        String sql = "SELECT * FROM review WHERE member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
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
            return list;
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
            pstmt.setLong(4, id);
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

    public boolean existsByIdAndMemberId(Long reviewId, Long memberId){
        String sql = "SELECT EXISTS(" +
                "SELECT 1" +
                "FROM review r" +
                "where r.review_id = ? AND r.member_id = ?)";
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
            if(rs.getBoolean(1)){
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e){
            throw new CustomDbException(e);
        }
        finally {
            close(conn, pstmt, rs);
        }
    }




}
