package model;

import java.util.Objects;

/**
 * POJO representing the LopHocPhan table.
 * Links a MonHoc (Subject) to a GiangVien (Lecturer) for a specific semester.
 */
public class LopHocPhan {
    // Constants for semester values
    public static final int HOC_KY_1 = 1;
    public static final int HOC_KY_2 = 2;
    public static final int HOC_KY_HE = 3;

    private String maLop;
    private String maMonHoc;
    private String maGiangVien;
    private int hocKy;
    private int namHoc;

    public LopHocPhan() {}

    public LopHocPhan(String maLop, String maMonHoc, String maGiangVien, int hocKy, int namHoc) {
        setMaLop(maLop);
        setMaMonHoc(maMonHoc);
        setMaGiangVien(maGiangVien);
        setHocKy(hocKy);
        setNamHoc(namHoc);
    }

    // Getters and Setters with validation
    public String getMaLop() { return maLop; }
    
    public void setMaLop(String maLop) {
        if (maLop == null || maLop.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma lop cannot be null or empty");
        }
        this.maLop = maLop.trim();
    }

    public void setHocKy(int hocKy) {
        if (hocKy < HOC_KY_1 || hocKy > HOC_KY_HE) {
            throw new IllegalArgumentException("Hoc ky must be between " + HOC_KY_1 + " and " + HOC_KY_HE);
        }
        this.hocKy = hocKy;
    }

    public void setNamHoc(int namHoc) {
        int currentYear = java.time.Year.now().getValue();
        if (namHoc < 2000 || namHoc > currentYear + 1) {
            throw new IllegalArgumentException("Nam hoc must be between 2000 and " + (currentYear + 1));
        }
        this.namHoc = namHoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LopHocPhan that = (LopHocPhan) o;
        return Objects.equals(maLop, that.maLop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLop);
    }

    @Override
    public String toString() {
        return String.format("LHP: [%s] (GV: %s) - HK%d %d-%d", 
            maMonHoc, maGiangVien, hocKy, namHoc, namHoc + 1);
    }

    // Static factory method
    public static LopHocPhan of(String maLop, String maMonHoc, String maGiangVien, int hocKy, int namHoc) {
        return new LopHocPhan(maLop, maMonHoc, maGiangVien, hocKy, namHoc);
    }
}
