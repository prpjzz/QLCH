import java.util.*;

public class CuaHang {
    private List<SanPham> sanPhams;
    private List<DonHang> donHangs;
    private int idDonHangTT;

    public CuaHang() {
        this.sanPhams = new ArrayList<>();
        this.donHangs = new ArrayList<>();
        this.idDonHangTT = 1;
        InitSampleData();
    }

    // sample data
    private void InitSampleData() {
        themSP(new SanPham(1, "iPhone 14", 20000000, "Điện thoại thông minh", "Điện thoại", 10));
        themSP(new SanPham(2, "Samsung Galaxy S23", 18000000, "Điện thoại Android", "Điện thoại", 15));
        themSP(new SanPham(3, "MacBook Air", 25000000, "Laptop Apple", "Máy tính", 8));
        themSP(new SanPham(4, "Dell XPS 13", 22000000, "Laptop Dell", "Máy tính", 12));
    }

    public void themSP(SanPham sanPham) {
        sanPhams.add(sanPham);
    }

    public void xoaSP(int idSanPham) {
        sanPhams.removeIf(p -> p.getId() == idSanPham);
    }

    public SanPham timSPbangId(int id) {
        return sanPhams.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void capNhatSanPham(int id, Double giaMoi, String moTaMoi) {
        SanPham sanPham = timSPbangId(id);
        if (sanPham != null) {
            if (giaMoi != null) sanPham.setGia(giaMoi);
            if (moTaMoi != null) sanPham.setMoTa(moTaMoi);
            System.out.println("Cap nhat San Pham thanh cong!");
        } else {
            System.out.println("Khong tim thay san pham voi ID: " + id);
        }
    }

    public void hienThiSPTheoGia() {
        List<SanPham> sapXepsanPham = new ArrayList<>(sanPhams);
        sapXepsanPham.sort(Comparator.comparingDouble(SanPham::getGia));

        System.out.println("\n=== DANH SÁCH SẢN PHẨM THEO GIÁ (Tăng dần) ===");
        for (SanPham p : sapXepsanPham) {
            System.out.println(p);
        }
    }

    public void hienThiSPTheoDanhMuc() {
        Map<String, List<SanPham>> mapDanhMuc = new TreeMap<>();

        for (SanPham p : sanPhams) {
            mapDanhMuc.computeIfAbsent(p.getDanhMuc(), k -> new ArrayList<>()).add(p);
        }

        System.out.println("\n=== DANH SÁCH SẢN PHẨM THEO DANH MỤC ===");
        for (Map.Entry<String, List<SanPham>> entry : mapDanhMuc.entrySet()) {
            System.out.println("\n--- " + entry.getKey() + " ---");
            for (SanPham p : entry.getValue()) {
                System.out.println(p);
            }
        }
    }

    public double tinhGiaTriDanhMuc(String danhMuc) {
        double tong = 0;
        tong = sanPhams.stream()
                .filter(p -> p.getDanhMuc().equalsIgnoreCase(danhMuc))
                .mapToDouble(p -> p.getGia() * p.getTonKho())
                .sum();
        return tong;
    }

    public void apDungGiamGiaSP(int idSanPham, GiamGia giamGia) {
        SanPham sanPham = timSPbangId(idSanPham);
        if (sanPham != null) {
            double giaCu = sanPham.getGia();
            sanPham.capNhatGiamGia(giamGia.getPhanTram());
            System.out.printf("Áp dụng giảm giá thành công!\nGiá cũ: %.0f VND -> Giá mới: %.0f VND\n",
                    giaCu, sanPham.getGia());
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + idSanPham);
        }
    }

    public DonHang taoDonHang(){
        return new DonHang(idDonHangTT++);
    }

    public void themSPvaoDonHang(DonHang donHang, int idSanPham, int soLuong) {
        SanPham sanPham = timSPbangId(idSanPham);
        if (sanPham != null) {
            donHang.themDon(sanPham, soLuong);
        } else {
            System.out.println("Khong tim thay san pham voi ID: " + idSanPham);
        }
    }

    public void hoanThanhDonHang(DonHang donHang){
        for (ItemDonHang item : donHang.getItems()) {
            item.getSanPham().capNhatTonKho(item.getSoLuong());
        }
        donHangs.add(donHang);
        System.out.println("Đặt hàng thành công!");
        System.out.println(donHang);
    }

    public void hienThiTatCaSanPham() {
        System.out.println("\n=== TẤT CẢ SẢN PHẨM ===");
        if (sanPhams.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
            return;
        }
        for (SanPham p : sanPhams) {
            System.out.println(p);
        }
    }

    public List<SanPham> getSanPhams() { return sanPhams; }
}
