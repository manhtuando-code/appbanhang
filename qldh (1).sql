-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 14, 2022 lúc 05:35 PM
-- Phiên bản máy phục vụ: 10.4.19-MariaDB
-- Phiên bản PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qldh`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi_tiet_don_hang`
--

CREATE TABLE `chi_tiet_don_hang` (
  `DH_id` int(11) NOT NULL,
  `SP_id` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chi_tiet_don_hang`
--

INSERT INTO `chi_tiet_don_hang` (`DH_id`, `SP_id`, `so_luong`) VALUES
(1, 1, 1),
(4, 45, 1),
(4, 50, 2),
(5, 50, 6),
(6, 21, 2),
(6, 33, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `don_hang`
--

CREATE TABLE `don_hang` (
  `DH_id` int(11) NOT NULL,
  `tam_tinh` int(11) DEFAULT NULL,
  `tong_tien` int(11) DEFAULT NULL,
  `thoi_gian` datetime NOT NULL DEFAULT current_timestamp(),
  `trang_thai` int(11) NOT NULL,
  `KH_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `don_hang`
--

INSERT INTO `don_hang` (`DH_id`, `tam_tinh`, `tong_tien`, `thoi_gian`, `trang_thai`, `KH_id`) VALUES
(1, NULL, NULL, '2022-02-14 00:52:33', 0, 5),
(2, NULL, NULL, '2022-02-14 01:17:51', 0, 6),
(3, NULL, NULL, '2022-02-14 14:55:48', 0, 7),
(4, NULL, NULL, '2022-02-14 17:20:11', 0, 8),
(5, NULL, NULL, '2022-02-14 23:11:13', 0, 9),
(6, NULL, NULL, '2022-02-14 23:14:20', 0, 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khach_hang`
--

CREATE TABLE `khach_hang` (
  `KH_id` int(11) NOT NULL,
  `ho_tenKH` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `dia_chi` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khach_hang`
--

INSERT INTO `khach_hang` (`KH_id`, `ho_tenKH`, `email`, `sdt`, `dia_chi`) VALUES
(1, 'tuan', 'all', '096543', 'aaaaaaaaaaa'),
(2, 'tuan', 'all', '096543', 'aaaaaaaaaaa'),
(3, 'tuan1', 'all1', '0965431', 'aaa1aaaaaaaa'),
(4, 'tuan1', 'all1', '0965431', 'aaa1aaaaaaaa'),
(5, 'tuan1', 'all1', '0965431', 'aaa1aaaaaaaa'),
(6, 'do manh tuan', 'tuan@gmail.com', '0965487123', 'Quang ninh'),
(7, 'do manh tuan', 'tuan@email.com', '0965478123', 'Quang ninh Viet nam'),
(8, 'test', 'test@gmail.com', '0123456789', 'Quang ninh'),
(9, 'choi long', 'longcho@gmail.com', 'choi tien', '123456789'),
(10, '111', '1111', '111', '111');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loai_san_pham`
--

CREATE TABLE `loai_san_pham` (
  `loaiSP_id` char(3) NOT NULL,
  `loaiSP_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loai_san_pham`
--

INSERT INTO `loai_san_pham` (`loaiSP_id`, `loaiSP_name`) VALUES
('HOD', 'Hoodie'),
('JKT', 'Jacket'),
('PNT', 'Pants'),
('SHT', 'Shorts'),
('SWP', 'Sweatpants'),
('TEE', 'Tee');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `san_pham`
--

CREATE TABLE `san_pham` (
  `SP_id` int(11) NOT NULL,
  `SP_code` varchar(14) NOT NULL,
  `tenSP` varchar(30) NOT NULL,
  `hinh_anh` varchar(200) NOT NULL,
  `so_luong_ton` int(11) NOT NULL,
  `don_gia` int(11) NOT NULL,
  `mo_ta` varchar(10000) NOT NULL,
  `loaiSP_id` char(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `san_pham`
--

INSERT INTO `san_pham` (`SP_id`, `SP_code`, `tenSP`, `hinh_anh`, `so_luong_ton`, `don_gia`, `mo_ta`, `loaiSP_id`) VALUES
(1, 'EZY-TEE-ORG-S', 'EAZY TEE - ORANGE', 'https://product.hstatic.net/1000344185/product/7831_059fe716f733429ea7d02239950459f3_master.jpg', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng', 'TEE'),
(2, 'EZY-TEE-ORG-M', 'EAZY TEE - ORANGE', 'https://product.hstatic.net/1000344185/product/7831_059fe716f733429ea7d02239950459f3_master.jpg', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng', 'TEE'),
(3, 'EZY-TEE-ORG-L', 'EAZY TEE - ORANGE', 'https://product.hstatic.net/1000344185/product/7831_059fe716f733429ea7d02239950459f3_master.jpg', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng', 'TEE'),
(4, 'EZY-TEE-ORG-XL', 'EAZY TEE - ORANGE', 'https://product.hstatic.net/1000344185/product/7831_059fe716f733429ea7d02239950459f3_master.jpg', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng', 'TEE'),
(13, 'IDD-TEE-WHT-S', 'INTL DOODLE - WHITE', 'https://product.hstatic.net/1000344185/product/9037_a592c02b1a404bec87c1d87f88ada8a1_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(14, 'IDD-TEE-WHT-M', 'INTL DOODLE - WHITE', 'https://product.hstatic.net/1000344185/product/9037_a592c02b1a404bec87c1d87f88ada8a1_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(15, 'IDD-TEE-WHT-L', 'INTL DOODLE - WHITE', 'https://product.hstatic.net/1000344185/product/9037_a592c02b1a404bec87c1d87f88ada8a1_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(16, 'IDD-TEE-WHT-XL', 'INTL DOODLE - WHITE', 'https://product.hstatic.net/1000344185/product/9037_a592c02b1a404bec87c1d87f88ada8a1_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(17, 'NFS-TEE-MNT-S', 'NFSWE TEE - MINT', 'https://product.hstatic.net/1000344185/product/8803_dcfa2349f69a4856950473746602cb67_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(18, 'NFS-TEE-MNT-M', 'NFSWE TEE - MINT', 'https://product.hstatic.net/1000344185/product/8803_dcfa2349f69a4856950473746602cb67_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(19, 'NFS-TEE-MNT-L', 'NFSWE TEE - MINT', 'https://product.hstatic.net/1000344185/product/8803_dcfa2349f69a4856950473746602cb67_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(20, 'NFS-TEE-MNT-XL', 'NFSWE TEE - MINT', 'https://product.hstatic.net/1000344185/product/8803_dcfa2349f69a4856950473746602cb67_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(21, 'VLT-TEE-GRN-S', 'VOLT TEE - LIGHT BLUE', 'https://product.hstatic.net/1000344185/product/0476_949f76c0489843f9a91a76dddae6f0b3_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(22, 'VLT-TEE-GRN-M', 'VOLT TEE - LIGHT BLUE', 'https://product.hstatic.net/1000344185/product/0476_949f76c0489843f9a91a76dddae6f0b3_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(23, 'VLT-TEE-GRN-L', 'VOLT TEE - LIGHT BLUE', 'https://product.hstatic.net/1000344185/product/0476_949f76c0489843f9a91a76dddae6f0b3_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(24, 'VLT-TEE-GRN-XL', 'VOLT TEE - LIGHT BLUE', 'https://product.hstatic.net/1000344185/product/0476_949f76c0489843f9a91a76dddae6f0b3_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(25, 'JCK-TEE-BLK-S', 'JACK TEE - BLACK', 'https://product.hstatic.net/1000344185/product/9382_2a6fb721fa6245458e3fe64e73963030_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(26, 'JCK-TEE-BLK-M', 'JACK TEE - BLACK', 'https://product.hstatic.net/1000344185/product/9382_2a6fb721fa6245458e3fe64e73963030_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(27, 'JCK-TEE-BLK-L', 'JACK TEE - BLACK', 'https://product.hstatic.net/1000344185/product/9382_2a6fb721fa6245458e3fe64e73963030_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(28, 'JCK-TEE-BLK-XL', 'JACK TEE - BLACK', 'https://product.hstatic.net/1000344185/product/9382_2a6fb721fa6245458e3fe64e73963030_master.jpg', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(29, 'FCL-TEE-SND-S', 'FACILE TEE - SAND', 'https://product.hstatic.net/1000344185/product/2468_bbaa7ddf5b614db4991934505377333e_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(30, 'FCL-TEE-SND-M', 'FACILE TEE - SAND', 'https://product.hstatic.net/1000344185/product/2468_bbaa7ddf5b614db4991934505377333e_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(31, 'FCL-TEE-SND-L', 'FACILE TEE - SAND', 'https://product.hstatic.net/1000344185/product/2468_bbaa7ddf5b614db4991934505377333e_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(32, 'FCL-TEE-SND-XL', 'FACILE TEE - SAND', 'https://product.hstatic.net/1000344185/product/2468_bbaa7ddf5b614db4991934505377333e_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(33, 'PHT-TEE-BLK-S', 'PHOTO TEE - BLACK', 'https://product.hstatic.net/1000344185/product/7753_aae0ab3ebfbc4389a1caae14f9edbf22_master.png', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(34, 'PHT-TEE-BLK-M', 'PHOTO TEE - BLACK', 'https://product.hstatic.net/1000344185/product/7753_aae0ab3ebfbc4389a1caae14f9edbf22_master.png', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(35, 'PHT-TEE-BLK-L', 'PHOTO TEE - BLACK', 'https://product.hstatic.net/1000344185/product/7753_aae0ab3ebfbc4389a1caae14f9edbf22_master.png', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(36, 'PHT-TEE-BLK-XL', 'PHOTO TEE - BLACK', 'https://product.hstatic.net/1000344185/product/7753_aae0ab3ebfbc4389a1caae14f9edbf22_master.png', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(37, 'STC-TEE-BLK-S', 'STENCIL TEE - BLACK', 'https://product.hstatic.net/1000344185/product/8735_7a9c7e3f192b42a08ee0356a6c03f8b6_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(38, 'STC-TEE-BLK-M', 'STENCIL TEE - BLACK', 'https://product.hstatic.net/1000344185/product/8735_7a9c7e3f192b42a08ee0356a6c03f8b6_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(39, 'STC-TEE-BLK-L', 'STENCIL TEE - BLACK', 'https://product.hstatic.net/1000344185/product/8735_7a9c7e3f192b42a08ee0356a6c03f8b6_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(40, 'STC-TEE-BLK-XL', 'STENCIL TEE - BLACK', 'https://product.hstatic.net/1000344185/product/8735_7a9c7e3f192b42a08ee0356a6c03f8b6_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(41, 'CBE-TEE-BLK-S', 'CUBE TEE - BLACK', 'https://product.hstatic.net/1000344185/product/1740_520e486166e042dc8b6ddbc52aae9c38_master.png', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(42, 'CBE-TEE-BLK-M', 'CUBE TEE - BLACK', 'https://product.hstatic.net/1000344185/product/1740_520e486166e042dc8b6ddbc52aae9c38_master.png', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(43, 'CBE-TEE-BLK-L', 'CUBE TEE - BLACK', 'https://product.hstatic.net/1000344185/product/1740_520e486166e042dc8b6ddbc52aae9c38_master.png', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(44, 'CBE-TEE-BLK-XL', 'CUBE TEE - BLACK', 'https://product.hstatic.net/1000344185/product/1740_520e486166e042dc8b6ddbc52aae9c38_master.png', 50, 330000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(45, 'CLL-TEE-BLK-S', 'COLLAGE TEE -BLACK', 'https://product.hstatic.net/1000344185/product/img_7685_e833ba4d883943db980f1f90e43688f7_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(46, 'CLL-TEE-BLK-M', 'COLLAGE TEE -BLACK', 'https://product.hstatic.net/1000344185/product/img_7685_e833ba4d883943db980f1f90e43688f7_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(47, 'CLL-TEE-BLK-L', 'COLLAGE TEE -BLACK', 'https://product.hstatic.net/1000344185/product/img_7685_e833ba4d883943db980f1f90e43688f7_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(48, 'CLL-TEE-BLK-XL', 'COLLAGE TEE -BLACK', 'https://product.hstatic.net/1000344185/product/img_7685_e833ba4d883943db980f1f90e43688f7_master.png', 50, 320000, 'Form áo Châu Âu.\r\nĐịnh lượng : 220 gsm.\r\n100% cotton.\r\nSản phẩm được in lụa.\r\nTag logo được gắn ở tay áo.\r\nTem chống hàng giả của SWE được may trong sườn áo.\r\nCác bạn vui lòng tham khảo bảng size chart trước khi đặt hàng.', 'TEE'),
(50, 'MTH-HOD-BLK-S', 'MENTAL HOODIE - BLACK', 'https://product.hstatic.net/1000344185/product/0569_c4652a9c05d74a2dacff01a578da3304_master.jpg', 50, 590000, 'Áo hoodie chui đầu có nón.\r\n	Chất liệu nỉ bông 100% cotton.\r\n	Hình in được in kéo lụa.', 'HOD'),
(51, 'MTH-HOD-BLK-M', 'MENTAL HOODIE - BLACK', 'https://product.hstatic.net/1000344185/product/0569_c4652a9c05d74a2dacff01a578da3304_master.jpg', 50, 590000, 'Áo hoodie chui đầu có nón.\r\n	Chất liệu nỉ bông 100% cotton.\r\n	Hình in được in kéo lụa.', 'HOD'),
(52, 'MTH-HOD-BLK-L', 'MENTAL HOODIE - BLACK', 'https://product.hstatic.net/1000344185/product/0569_c4652a9c05d74a2dacff01a578da3304_master.jpg', 50, 590000, 'Áo hoodie chui đầu có nón.\r\n	Chất liệu nỉ bông 100% cotton.\r\n	Hình in được in kéo lụa.', 'HOD'),
(53, 'MTH-HOD-BLK-XL', 'MENTAL HOODIE - BLACK', 'https://product.hstatic.net/1000344185/product/0569_c4652a9c05d74a2dacff01a578da3304_master.jpg', 50, 590000, 'Áo hoodie chui đầu có nón.\r\n	Chất liệu nỉ bông 100% cotton.\r\n	Hình in được in kéo lụa.', 'HOD');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chi_tiet_don_hang`
--
ALTER TABLE `chi_tiet_don_hang`
  ADD PRIMARY KEY (`DH_id`,`SP_id`),
  ADD KEY `SP_id` (`SP_id`);

--
-- Chỉ mục cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  ADD PRIMARY KEY (`DH_id`),
  ADD KEY `KH_id` (`KH_id`);

--
-- Chỉ mục cho bảng `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`KH_id`);

--
-- Chỉ mục cho bảng `loai_san_pham`
--
ALTER TABLE `loai_san_pham`
  ADD PRIMARY KEY (`loaiSP_id`);

--
-- Chỉ mục cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  ADD PRIMARY KEY (`SP_id`),
  ADD UNIQUE KEY `SP_code` (`SP_code`),
  ADD KEY `loaiSP_id` (`loaiSP_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  MODIFY `DH_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `KH_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  MODIFY `SP_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chi_tiet_don_hang`
--
ALTER TABLE `chi_tiet_don_hang`
  ADD CONSTRAINT `chi_tiet_don_hang_ibfk_1` FOREIGN KEY (`DH_id`) REFERENCES `don_hang` (`DH_id`),
  ADD CONSTRAINT `chi_tiet_don_hang_ibfk_2` FOREIGN KEY (`SP_id`) REFERENCES `san_pham` (`SP_id`);

--
-- Các ràng buộc cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  ADD CONSTRAINT `don_hang_ibfk_1` FOREIGN KEY (`KH_id`) REFERENCES `khach_hang` (`KH_id`);

--
-- Các ràng buộc cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  ADD CONSTRAINT `san_pham_ibfk_1` FOREIGN KEY (`loaiSP_id`) REFERENCES `loai_san_pham` (`loaiSP_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
