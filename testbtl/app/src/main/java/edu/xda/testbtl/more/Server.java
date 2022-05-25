package edu.xda.testbtl.more;

public class Server {
    public static String localhost = "192.168.0.105:8080";
    public static String DuongdanNavigationview = "http://" + localhost + "/server/getData_navigationview.php";
    public static String LinkNewArrivalsHome = "http://" + localhost + "/server/getNewArrivalsHome.php";
    public static String LinkNewArrivalsActivity = "http://" + localhost + "/server/getNewArrivalsActivity.php?page=";
    public static String LinkTopsActivity = "http://" + localhost + "/server/getSanpham.php?page=";
    public static String LinkChosseSize = "http://" + localhost + "/server/getDataWithSP_code.php";
    public static String LinkKhachHangInfo = "http://" + localhost + "/server/setKhachHangInfo.php";
    public static String LinkChiTietDonHangInfo = "http://" + localhost + "/server/setChiTietDonHang.php";
}
