package model;

import java.util.Objects;

/**
 * POJO representing the MonHoc table.
 * Model for a subject (e.g., "Calculus 1").
 */
public class MonHoc {
    private String maMonHoc;
    private String maKhoa;
    private String tenMonHoc;
    private int soTinChi;

    public MonHoc() {}

    public MonHoc(String maMonHoc, String maKhoa, String tenMonHoc, int soTinChi) {
        setMaMonHoc(maMonHoc);
        setMaKhoa(maKhoa);
        setTenMonHoc(tenMonHoc);
        setSoTinChi(soTinChi);
    }

    // Getters
    public String getMaMonHoc() { return maMonHoc; }
    public String getMaKhoa() { return maKhoa; }
    public String getTenMonHoc() { return tenMonHoc; }
    public int getSoTinChi() { return soTinChi; }

    // Setters with validation
    public void setMaMonHoc(String maMonHoc) {
        if (maMonHoc == null || maMonHoc.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma mon hoc cannot be null or empty");
        }
        this.maMonHoc = maMonHoc.trim().toUpperCase();
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa; // Could be null if subject doesn't belong to a department
    }

    public void setTenMonHoc(String tenMonHoc) {
        if (tenMonHoc == null || tenMonHoc.trim().isEmpty()) {
            throw new IllegalArgumentException("Ten mon hoc cannot be null or empty");
        }
        this.tenMonHoc = tenMonHoc.trim();
    }

    public void setSoTinChi(int soTinChi) {
        if (soTinChi < 1 || soTinChi > 20) { // Extended range for special cases
            throw new IllegalArgumentException("So tin chi must be between 1 and 20");
        }
        this.soTinChi = soTinChi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonHoc monHoc = (MonHoc) o;
        return Objects.equals(maMonHoc, monHoc.maMonHoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMonHoc);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %d tín chỉ", tenMonHoc, maMonHoc, soTinChi);
    }

    // Utility methods
    public boolean thuocKhoa() {
        return maKhoa != null && !maKhoa.trim().isEmpty();
    }

    // Static factory method
    public static MonHoc of(String maMonHoc, String tenMonHoc, int soTinChi) {
        return new MonHoc(maMonHoc, null, tenMonHoc, soTinChi);
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String maMonHoc;
        private String maKhoa;
        private String tenMonHoc;
        private int soTinChi;

        public Builder maMonHoc(String maMonHoc) {
            this.maMonHoc = maMonHoc;
            return this;
        }

        public Builder maKhoa(String maKhoa) {
            this.maKhoa = maKhoa;
            return this;
        }

        public Builder tenMonHoc(String tenMonHoc) {
            this.tenMonHoc = tenMonHoc;
            return this;
        }

        public Builder soTinChi(int soTinChi) {
            this.soTinChi = soTinChi;
            return this;
        }

        public MonHoc build() {
            return new MonHoc(maMonHoc, maKhoa, tenMonHoc, soTinChi);
        }
    }
}
