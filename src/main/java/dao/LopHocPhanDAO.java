package dao;

import db.DatabaseConnector;
import model.LopHocPhan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for the LopHocPhan (Course Section) table.
 * Handles all database operations for LopHocPhan.
 */
public class LopHocPhanDAO {
    private static final Logger logger = Logger.getLogger(LopHocPhanDAO.class.getName());

    public List<LopHocPhan> getAllLopHocPhan() {
        List<LopHocPhan> danhSachLHP = new ArrayList<>();
        String sql = "SELECT * FROM LopHocPhan ORDER BY NamHoc DESC, HocKy DESC, MaLop";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                danhSachLHP.add(mapResultSetToLopHocPhan(rs));
            }
            logger.info("Retrieved " + danhSachLHP.size() + " course sections");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all course sections", e);
        }
        return danhSachLHP;
    }

    public boolean addLopHocPhan(LopHocPhan lhp) {
        // Check if already exists
        if (exists(lhp.getMaLop())) {
            logger.warning("Course section already exists: " + lhp.getMaLop());
            return false;
        }

        String sql = "INSERT INTO LopHocPhan (MaLop, MaMonHoc, MaGiangVien, HocKy, NamHoc) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lhp.getMaLop());
            pstmt.setString(2, lhp.getMaMonHoc());
            pstmt.setString(3, lhp.getMaGiangVien());
            pstmt.setInt(4, lhp.getHocKy());
            pstmt.setInt(5, lhp.getNamHoc());

            int rowsAffected = pstmt.executeUpdate();
            boolean success = rowsAffected > 0;
            
            if (success) {
                logger.info("Successfully added course section: " + lhp.getMaLop());
            } else {
                logger.warning("Failed to add course section: " + lhp.getMaLop());
            }
            
            return success;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding course section: " + lhp.getMaLop(), e);
            return false;
        }
    }

    // ... other methods with similar enhancements

    private LopHocPhan mapResultSetToLopHocPhan(ResultSet rs) throws SQLException {
        LopHocPhan lhp = new LopHocPhan();
        lhp.setMaLop(rs.getString("MaLop"));
        lhp.setMaMonHoc(rs.getString("MaMonHoc"));
        lhp.setMaGiangVien(rs.getString("MaGiangVien"));
        lhp.setHocKy(rs.getInt("HocKy"));
        lhp.setNamHoc(rs.getInt("NamHoc"));
        return lhp;
    }
}
