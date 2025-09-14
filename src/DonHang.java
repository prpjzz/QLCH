import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonHang {
    private int orderId;
    private List<ItemDonHang> items;
    private double tongDonHang;
    private Date ngayDatHang;

    public DonHang(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.tongDonHang = 0;
        this.ngayDatHang = new Date();
    }

    public void themDon(SanPham sanPham, int soLuong) {
        if (sanPham.getTonKho() >= soLuong) {
            // Kiểm tra xem sản phẩm đã có trong đơn hàng chưa
            for (ItemDonHang item : items) {
                if (item.getSanPham().getId() == sanPham.getId()) {
                    item.setSoLuong(item.getSoLuong() + soLuong);
                    tinhTong();
                    return;
                }
            }
            // Nếu chưa có, thêm mới
            items.add(new ItemDonHang(sanPham, soLuong));
            tinhTong();
        } else {
            System.out.println("Không đủ hàng tồn kho! Chỉ còn " + sanPham.getTonKho() + " sản phẩm.");
        }
    }

    public void xoaDon(int idSanPham) {
        items.removeIf(item -> item.getSanPham().getId() == idSanPham);
        tinhTong();
    }
    public double tinhTong() {
        tongDonHang = items.stream().mapToDouble(ItemDonHang::getGiaTien).sum();
        return tongDonHang;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<ItemDonHang> getItems() {
        return items;
    }

    public double getTongDonHang() {
        return tongDonHang;
    }

    public Date getNgayDatHang() {
        return ngayDatHang;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== ĐƠN HÀNG #").append(orderId).append(" ===\n");
        sb.append("Ngày đặt: ").append(ngayDatHang).append("\n");
        sb.append("Chi tiết:\n");
        for (ItemDonHang item : items) {
            sb.append("- ").append(item.toString()).append("\n");
        }
        sb.append("TỔNG TIỀN: ").append(df.format(tongDonHang)).append(" VND");
        return sb.toString();
    }
}
