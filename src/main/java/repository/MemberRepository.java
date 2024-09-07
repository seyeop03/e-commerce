package repository;

import common.Role;
import domain.Member;
import exception.CustomDbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static repository.connection.DBConnectionUtil.close;
import static repository.connection.DBConnectionUtil.getConnection;

public class MemberRepository {

    public void save(Member member) {
        String sql = "INSERT INTO member(username, password, name, birth, phone, email, address, home, role) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,member.getUsername());
            pstmt.setString(2,member.getPassword());
            pstmt.setString(3,member.getName());
            pstmt.setString(4,member.getBirth());
            pstmt.setString(5,member.getPhone());
            pstmt.setString(6,member.getEmail());
            pstmt.setString(7,member.getAddress());
            pstmt.setString(8,member.getHome());
            pstmt.setString(9,member.getRole().name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Member> memberList = new ArrayList<>();
            while (rs.next()){
                Member member = Member.of(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("home"),
                        Role.valueOf(rs.getString("role"))
                );
                memberList.add(member);
            }
            return memberList;
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn,pstmt,rs);
        }
    }

    public Optional<Member> findById(Long id) {
        String sql = "SELECT * FROM member WHERE member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            Member member = null;
            if (rs.next()) {
                member = Member.of(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("home"),
                        Role.valueOf(rs.getString("role"))
                );
            }
            return Optional.ofNullable(member);
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public Optional<Member> findByUsername(String username) {
        String sql = "SELECT * FROM member WHERE username = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            Member member = null;
            if (rs.next()) {
                member = Member.of(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("home"),
                        Role.valueOf(rs.getString("role"))
                );
            }
            return Optional.ofNullable(member);
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM member WHERE member_id = ?";
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

    public void updateById(Long id, Member member) {
        String sql = "UPDATE member " +
                "SET password = ?, name = ?, birth = ?, phone = ?, email = ?, address = ?, home = ?" +
                "WHERE member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getPassword());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getBirth());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, member.getAddress());
            pstmt.setString(7, member.getHome());
            pstmt.setLong(8, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }
}
