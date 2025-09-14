import java.text.DecimalFormat;

public class ItemDonHang {
    private SanPham sanPham;
    private int soLuong;

    public ItemDonHang(SanPham sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaTien(){
        return sanPham.getGia() * soLuong;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("%s x %d = %s VND",
                sanPham.getTenSP(), soLuong, df.format(getGiaTien()));
    }
}
