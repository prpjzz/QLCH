public class GiamGia {
    private String loaiGiamGia;
    private double phanTram;
    private String moTa;

    public GiamGia(String loaiGiamGia, double phanTram, String moTa) {
        this.loaiGiamGia = loaiGiamGia;
        this.phanTram = phanTram;
        this.moTa = moTa;
    }

    public String getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public double getPhanTram() {
        return phanTram;
    }

    public String getMoTa() {
        return moTa;
    }

    public double apDungGiamGia(double giaGoc) {
        return giaGoc * (1 - phanTram / 100);
    }

    @Override
    public String toString() {
        return String.format("%s - Giảm %.1f%% - %s", loaiGiamGia, phanTram, moTa);
    }
}
