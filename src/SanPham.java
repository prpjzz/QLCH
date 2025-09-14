import java.text.DecimalFormat;

public class SanPham {

    private int id, tonKho;
    private String tenSP, moTa, danhMuc;
    private double gia;

    public SanPham(int id, String tenSP, double gia, String moTa, String danhMuc, int tonKho) {
        this.id = id;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.danhMuc = danhMuc;
        this.gia = gia;
        this.tonKho = tonKho;
    }

    public void capNhatTonKho(int soLuong) {
        this.tonKho -= soLuong;
    }

    public void capNhatGiamGia(double giamGia) {
        this.gia = this.gia * (1 - giamGia/100);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("ID: %d | Tên: %s | Giá: %s VND | Mô tả: %s | Danh mục: %s | Tồn kho: %d",
                id, tenSP, df.format(gia), moTa, danhMuc, tonKho);
    }

    // getter setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }


}
