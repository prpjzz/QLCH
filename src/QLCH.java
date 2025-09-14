import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;


public class QLCH {
    private CuaHang cuaHang;
    private Scanner scanner;

    public QLCH() {
        this.cuaHang = new CuaHang();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== HỆ THỐNG QUẢN LÝ CỬA HÀNG ===");

        while (true) {
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                handleMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== MENU CHÍNH ===");
        System.out.println("1. Thêm sản phẩm mới");
        System.out.println("2. Cập nhật thông tin sản phẩm");
        System.out.println("3. Hiển thị sản phẩm theo giá");
        System.out.println("4. Hiển thị sản phẩm theo danh mục");
        System.out.println("5. Tính giá trị tồn kho theo danh mục");
        System.out.println("6. Áp dụng giảm giá");
        System.out.println("7. Tạo đơn hàng");
        System.out.println("8. Hiển thị tất cả sản phẩm");
        System.out.println("0. Thoát");
        System.out.print("Chọn chức năng (0-8): ");
    }


    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1: menuThemSP(); nhannutReturn(); break;
            case 2: menuCapNhatSP(); nhannutReturn(); break;
            case 3: cuaHang.hienThiSPTheoGia(); nhannutReturn(); break;
            case 4: cuaHang.hienThiSPTheoDanhMuc(); nhannutReturn(); break;
            case 5: tinhGiaTriDanhMuc(); nhannutReturn(); break;
            case 6: menuApDungGiamGia(); nhannutReturn(); break;
            case 7: menuTaoDon(); nhannutReturn(); break;
            case 8: cuaHang.hienThiTatCaSanPham(); nhannutReturn(); break;
            case 0:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private void menuThemSP() {
        try {
            System.out.print("Nhập ID sản phẩm: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (cuaHang.timSPbangId(id) != null) {
                System.out.println("ID sản phẩm đã tồn tại!");
                return;
            }

            System.out.print("Nhập tên sản phẩm: ");
            String ten = scanner.nextLine();

            System.out.print("Nhập giá: ");
            double gia = Double.parseDouble(scanner.nextLine());

            System.out.print("Nhập mô tả: ");
            String moTa = scanner.nextLine();

            System.out.print("Nhập danh mục: ");
            String danhMuc = scanner.nextLine();

            System.out.print("Nhập số lượng tồn kho: ");
            int tonKho = Integer.parseInt(scanner.nextLine());

            cuaHang.themSP(new SanPham(id, ten, gia, moTa, danhMuc, tonKho));
            System.out.println("Thêm sản phẩm thành công!");

        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu nhập không hợp lệ!");
        }
    }

    private void menuCapNhatSP() {
        try {
            System.out.print("Nhập ID sản phẩm cần cập nhật: ");
            int id = Integer.parseInt(scanner.nextLine());

            SanPham sanPham = cuaHang.timSPbangId(id);
            if (sanPham == null) {
                System.out.println("Không tìm thấy sản phẩm!");
                return;
            }

            System.out.println("Thông tin hiện tại: " + sanPham);

            System.out.print("Nhập giá mới (Enter để bỏ qua): ");
            String giaDangNhap = scanner.nextLine();
            Double giaMoi = giaDangNhap.isEmpty() ? null : Double.parseDouble(giaDangNhap);

            System.out.print("Nhập mô tả mới (Enter để bỏ qua): ");
            String moTaMoi = scanner.nextLine();
            if (moTaMoi.isEmpty()) moTaMoi = null;

            cuaHang.capNhatSanPham(id, giaMoi, moTaMoi);

        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu nhập không hợp lệ!");
        }
    }

    private void tinhGiaTriDanhMuc() {
        System.out.print("Nhập tên danh mục: ");
        String danhMuc = scanner.nextLine();

        double tongGiaTri = cuaHang.tinhGiaTriDanhMuc(danhMuc);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.printf("Tổng giá trị tồn kho danh mục '%s': %s VND\n",
                danhMuc, df.format(tongGiaTri));
    }

    private void menuApDungGiamGia() {
        try {
            cuaHang.hienThiTatCaSanPham();

            System.out.print("Nhập ID sản phẩm cần giảm giá: ");
            int productId = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhập phần trăm giảm giá (0-100): ");
            double phanTram = Double.parseDouble(scanner.nextLine());

            if (phanTram < 0 || phanTram > 100) {
                System.out.println("Phần trăm giảm giá không hợp lệ!");
                return;
            }

            System.out.print("Nhập mô tả chương trình giảm giá: ");
            String moTa = scanner.nextLine();

            GiamGia giamGia = new GiamGia("Flash Sale", phanTram, moTa);
            cuaHang.apDungGiamGiaSP(productId, giamGia);

        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu nhập không hợp lệ!");
        }
    }

    private void menuTaoDon() {
        DonHang donHang = cuaHang.taoDonHang();

        while (true) {
            System.out.println("\n=== TẠO ĐƠN HÀNG #" + donHang.getOrderId() + " ===");
            System.out.println("1. Thêm sản phẩm vào đơn hàng");
            System.out.println("2. Xem giỏ hàng hiện tại");
            System.out.println("3. Hoàn thành đơn hàng");
            System.out.println("0. Hủy đơn hàng");
            System.out.print("Chọn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        cuaHang.hienThiTatCaSanPham();
                        System.out.print("Nhập ID sản phẩm: ");
                        int productId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nhập số lượng: ");
                        int soLuong = Integer.parseInt(scanner.nextLine());

                        cuaHang.themSPvaoDonHang(donHang, productId, soLuong);
                        nhannutReturn();
                        break;

                    case 2:
                        if (donHang.getItems().isEmpty()) {
                            System.out.println("Giỏ hàng trống!");
                        } else {
                            System.out.println(donHang);
                        }
                        nhannutReturn();
                        break;

                    case 3:
                        if (donHang.getItems().isEmpty()) {
                            System.out.println("Không thể hoàn thành đơn hàng trống!");
                        } else {
                            cuaHang.hoanThanhDonHang(donHang);
                            return;
                        }
                        nhannutReturn();
                        break;

                    case 0:
                        System.out.println("Đã hủy đơn hàng!");
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập không hợp lệ!");
            }
        }
    }

    public void nhannutReturn(){
        System.out.println("Nhấn \"Enter\" để tiếp tục ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}