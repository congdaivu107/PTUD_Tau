USE [master]
GO
/****** Object:  Database [Tau]    Script Date: 11/15/2021 12:00:53 AM ******/
CREATE DATABASE [Tau]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Tau', FILENAME = N'D:\data\Tau.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Tau_log', FILENAME = N'D:\data\Tau_log.ldf' , SIZE = 816KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Tau] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Tau].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Tau] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Tau] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Tau] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Tau] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Tau] SET ARITHABORT OFF 
GO
ALTER DATABASE [Tau] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Tau] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Tau] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Tau] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Tau] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Tau] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Tau] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Tau] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Tau] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Tau] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Tau] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Tau] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Tau] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Tau] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Tau] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Tau] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Tau] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Tau] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Tau] SET  MULTI_USER 
GO
ALTER DATABASE [Tau] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Tau] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Tau] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Tau] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Tau] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Tau]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 11/15/2021 12:00:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[giamGia] [money] NOT NULL,
	[soLuong] [int] NOT NULL,
	[donGia] [money] NOT NULL,
	[maHD] [nvarchar](20) NOT NULL,
	[maSP] [nvarchar](20) NOT NULL,
	[thanhTien] [money] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maHD] ASC,
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ChiTietPhieuNhap]    Script Date: 11/15/2021 12:00:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietPhieuNhap](
	[thanhTien] [money] NOT NULL,
	[soLuong] [int] NOT NULL,
	[donGia] [money] NOT NULL,
	[maPhieuNhap] [nvarchar](20) NOT NULL,
	[maSP] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_ChiTietPhieuNhap] PRIMARY KEY CLUSTERED 
(
	[maPhieuNhap] ASC,
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 11/15/2021 12:00:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[maChucVu] [nvarchar](20) NOT NULL,
	[tenChucVu] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maChucVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 11/15/2021 12:00:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [nvarchar](20) NOT NULL,
	[ngayTao] [date] NOT NULL,
	[maKH] [nvarchar](20) NOT NULL,
	[maNV] [nvarchar](20) NOT NULL,
	[tongTien] [money] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 11/15/2021 12:00:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [nvarchar](20) NOT NULL,
	[tenKH] [nvarchar](20) NOT NULL,
	[diaChi] [nvarchar](20) NOT NULL,
	[sdt] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Loai]    Script Date: 11/15/2021 12:00:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Loai](
	[maLoai] [nvarchar](20) NOT NULL,
	[tenLoai] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhaCC]    Script Date: 11/15/2021 12:00:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCC](
	[maNhaCC] [nvarchar](20) NOT NULL,
	[tenNhaCC] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhaCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/15/2021 12:00:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [nvarchar](20) NOT NULL,
	[tenNV] [nvarchar](20) NOT NULL,
	[diaChi] [nvarchar](20) NOT NULL,
	[gioiTInh] [nvarchar](20) NOT NULL,
	[email] [nvarchar](20) NOT NULL,
	[ngayVaoLam] [date] NOT NULL,
	[sdt] [nvarchar](20) NOT NULL,
	[maChucVu] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PhieuNhap]    Script Date: 11/15/2021 12:00:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuNhap](
	[maPhieuNhap] [nvarchar](20) NOT NULL,
	[nhaCC] [nvarchar](20) NOT NULL,
	[ngayNhap] [date] NULL,
	[tongTien] [money] NOT NULL,
	[thue] [float] NOT NULL,
	[maNV] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhieuNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/15/2021 12:00:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSP] [nvarchar](20) NOT NULL,
	[tenSP] [nvarchar](60) NOT NULL,
	[donViSP] [nvarchar](50) NOT NULL,
	[giaNhap] [money] NOT NULL,
	[giaBan] [money] NOT NULL,
	[tinhTrang] [nvarchar](50) NOT NULL,
	[tonKho] [int] NOT NULL,
	[image] [nvarchar](70) NOT NULL,
	[maNhaCC] [nvarchar](20) NOT NULL,
	[maLoai] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 11/15/2021 12:00:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id] [nvarchar](20) NOT NULL,
	[password] [nvarchar](20) NOT NULL,
	[chucNang] [nvarchar](20) NOT NULL,
	[maNV] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
USE [Tau]
GO
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (0.0000, 2, 498000.0000, N'MV00001', N'CT00004', 996000.0000)
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (6258.0000, 1, 298000.0000, N'MV00001', N'CT00015', 291742.0000)
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (0.0000, 2, 567000.0000, N'MV00001', N'CT00018', 1134000.0000)
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (24900.0000, 1, 498000.0000, N'MV00002', N'CT00001', 473100.0000)
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (39120.0000, 2, 978000.0000, N'MV00002', N'CT00003', 1916880.0000)
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (0.0000, 1, 598000.0000, N'MV00002', N'CT00012', 598000.0000)
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (0.0000, 1, 498000.0000, N'MV00003', N'CT00016', 498000.0000)
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (59760.0000, 3, 498000.0000, N'MV00004', N'CT00004', 1434240.0000)
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (0.0000, 1, 698000.0000, N'MV00004', N'CT00011', 698000.0000)
INSERT [dbo].[ChiTietHoaDon] ([giamGia], [soLuong], [donGia], [maHD], [maSP], [thanhTien]) VALUES (3960.0000, 2, 198000.0000, N'MV00004', N'CT00013', 392040.0000)
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (2098600.0000, 7, 299800.0000, N'PN00001', N'CT00001')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (3198400.0000, 8, 399800.0000, N'PN00001', N'CT00002')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (10557600.0000, 12, 879800.0000, N'PN00001', N'CT00003')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (3519200.0000, 4, 879800.0000, N'PN00002', N'CT00003')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (4797600.0000, 12, 399800.0000, N'PN00002', N'CT00004')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (13996000.0000, 20, 699800.0000, N'PN00002', N'CT00005')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (1207800.0000, 11, 109800.0000, N'PN00002', N'CT00006')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (4498200.0000, 9, 499800.0000, N'PN00002', N'CT00007')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (899400.0000, 3, 299800.0000, N'PN00003', N'CT00001')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (1499400.0000, 3, 499800.0000, N'PN00003', N'CT00007')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (3198400.0000, 8, 399800.0000, N'PN00003', N'CT00008')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (4798800.0000, 6, 799800.0000, N'PN00003', N'CT00009')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (2728600.0000, 7, 389800.0000, N'PN00004', N'CT00010')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (3358400.0000, 8, 419800.0000, N'PN00004', N'CT00011')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (5457400.0000, 13, 419800.0000, N'PN00004', N'CT00012')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (998000.0000, 10, 99800.0000, N'PN00004', N'CT00013')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (4477200.0000, 14, 319800.0000, N'PN00004', N'CT00014')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (999000.0000, 5, 199800.0000, N'PN00004', N'CT00015')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (3597600.0000, 12, 299800.0000, N'PN00005', N'CT00016')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (9597600.0000, 12, 799800.0000, N'PN00005', N'CT00017')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (1259400.0000, 3, 419800.0000, N'PN00006', N'CT00011')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (959400.0000, 3, 319800.0000, N'PN00006', N'CT00014')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (899400.0000, 3, 299800.0000, N'PN00006', N'CT00016')
INSERT [dbo].[ChiTietPhieuNhap] ([thanhTien], [soLuong], [donGia], [maPhieuNhap], [maSP]) VALUES (4287800.0000, 11, 389800.0000, N'PN00006', N'CT00018')
INSERT [dbo].[ChucVu] ([maChucVu], [tenChucVu]) VALUES (N'CV00001', N'Quan Ly')
INSERT [dbo].[ChucVu] ([maChucVu], [tenChucVu]) VALUES (N'CV00002', N'Nhan Vien')
INSERT [dbo].[HoaDon] ([maHD], [ngayTao], [maKH], [maNV], [tongTien]) VALUES (N'MV00001', CAST(N'2020-03-15' AS Date), N'KH00001', N'NV00001', 2421742.0000)
INSERT [dbo].[HoaDon] ([maHD], [ngayTao], [maKH], [maNV], [tongTien]) VALUES (N'MV00002', CAST(N'2020-10-24' AS Date), N'KH00002', N'NV00002', 2987980.0000)
INSERT [dbo].[HoaDon] ([maHD], [ngayTao], [maKH], [maNV], [tongTien]) VALUES (N'MV00003', CAST(N'2021-02-03' AS Date), N'KH00003', N'NV00001', 498000.0000)
INSERT [dbo].[HoaDon] ([maHD], [ngayTao], [maKH], [maNV], [tongTien]) VALUES (N'MV00004', CAST(N'2021-11-14' AS Date), N'KH00004', N'NV00004', 2524280.0000)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [diaChi], [sdt]) VALUES (N'KH00001', N'Le DInh Quy', N'12 Kha Vạn Cân', N'0831489639')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [diaChi], [sdt]) VALUES (N'KH00002', N'Le Thi Ngoc Giau', N'34 Phan Dinh Phung', N'0123456789')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [diaChi], [sdt]) VALUES (N'KH00003', N'Phan Tan Tai', N'12 Cong Hoa', N'0321654987')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [diaChi], [sdt]) VALUES (N'KH00004', N'Nguyen Xuan Mai', N'12 Ly Tu Trong', N'0564371231')
INSERT [dbo].[Loai] ([maLoai], [tenLoai]) VALUES (N'LO00001', N'Vip')
INSERT [dbo].[Loai] ([maLoai], [tenLoai]) VALUES (N'LO00002', N'Thuong')

INSERT [dbo].[NhaCC] ([maNhaCC], [tenNhaCC]) VALUES (N'NG00001', N'VNT')
INSERT [dbo].[NhaCC] ([maNhaCC], [tenNhaCC]) VALUES (N'NG00002', N'SSH')
INSERT [dbo].[NhaCC] ([maNhaCC], [tenNhaCC]) VALUES (N'NG00003', N'DEV')
INSERT [dbo].[NhaCC] ([maNhaCC], [tenNhaCC]) VALUES (N'NG00004', N'ABC')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [diaChi], [gioiTInh], [email], [ngayVaoLam], [sdt], [maChucVu]) VALUES (N'NV00001', N'Bui Thi Thuy Duong', N'23 Quang Trung', N'nữ', N'thuyduong@gmail.com', CAST(N'2015-07-23' AS Date), N'0359629843', N'CV00001')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [diaChi], [gioiTInh], [email], [ngayVaoLam], [sdt], [maChucVu]) VALUES (N'NV00002', N'Giang Anh Kiet', N'67 Nguyen Van Nghi', N'nam', N'giângnhkiet@gmail.com', CAST(N'2024-02-24' AS Date), N'0543428617', N'CV00002')
INSERT [dbo].[PhieuNhap] ([maPhieuNhap], [nhaCC], [ngayNhap], [tongTien], [thue], [maNV]) VALUES (N'PN00001', N'VNT', CAST(N'2019-05-09' AS Date), 2470764.0000, 3, N'NV00001')
INSERT [dbo].[PhieuNhap] ([maPhieuNhap], [nhaCC], [ngayNhap], [tongTien], [thue], [maNV]) VALUES (N'PN00002', N'SSH', CAST(N'2019-11-15' AS Date), 29260083.6000, 2.6, N'NV00002')
INSERT [dbo].[PhieuNhap] ([maPhieuNhap], [nhaCC], [ngayNhap], [tongTien], [thue], [maNV]) VALUES (N'PN00003', N'DEV', CAST(N'2020-07-21' AS Date), 10635108.0000, 2.3, N'NV00001')
INSERT [dbo].[PhieuNhap] ([maPhieuNhap], [nhaCC], [ngayNhap], [tongTien], [thue], [maNV]) VALUES (N'PN00004', N'ABC', CAST(N'2020-10-31' AS Date), 18499950.0000, 5, N'NV00002')
INSERT [dbo].[PhieuNhap] ([maPhieuNhap], [nhaCC], [ngayNhap], [tongTien], [thue], [maNV]) VALUES (N'PN00005', N'ABC', CAST(N'2021-01-27' AS Date), 13447162.1700, 1.9, N'NV00002')
INSERT [dbo].[PhieuNhap] ([maPhieuNhap], [nhaCC], [ngayNhap], [tongTien], [thue], [maNV]) VALUES (N'PN00006', N'DEV', CAST(N'2021-11-14' AS Date), 7621499.4900, 2.7, N'NV00001')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00001', N'Gold Trim Pull On Jeans-S', N'Italian Star', 299800.0000, 498000.0000, N'con ve', 10, N'cap nhat sau', N'NG00003', N'LO00004')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00002', N'Never Ride Faster', N'Citire', 399800.0000, 618000.0000, N'con ve', 8, N'tinh sao', N'NG00002', N'LO00002')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00003', N'Aybrey Drop Crotch Joggers-M', N'Basic Sate', 879800.0000, 978000.0000, N'con ve', 16, N'cap nhat sau', N'NG00001', N'LO00005')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00004', N'Bondi Pant-M', N'Basic Sate', 399800.0000, 498000.0000, N'con ve', 12, N'cap nhat sau', N'NG00002', N'LO00004')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00005', N'Nam Straight Fit-S', N'ACFC', 699800.0000, 708000.0000, N'con ve', 20, N'tinh sao', N'NG00002', N'LO00005')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00006', N'Slim Fit', N'Six Do', 109800.0000, 246000.0000, N'con ve', 17, N'cap nhat sau', N'NG00004', N'LO00004')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00007', N'Perfect Pocket', N'Thundered', 499800.0000, 568000.0000, N'con ve', 12, N'tinh sao', N'NG00001', N'LO00001')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00008', N'Skinny A04-M', N'YaMe', 399800.0000, 428000.0000, N'con ve', 8, N'tinh sao', N'NG00004', N'LO00004')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00009', N'KHAKI ULTRA STRETCH ', N'Skiny', 799800.0000, 818000.0000, N'con ve', 6, N'tinh sao', N'NG00003', N'LO00004')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00010', N'Nano-Tech Easycare-M', N'Cool Mate', 389800.0000, 445000.0000, N'con ve', 7, N'cap nhat sau', N'NG00001', N'LO00001')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00011', N'COZY COMFY CUTE', N'Sydan', 419800.0000, 698000.0000, N'con ve', 11, N'cap nhat sau', N'NG00002', N'LO00003')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00012', N'STEVEN TODDLER', N'Baby Beaty', 419800.0000, 598000.0000, N'con ve', 13, N'cap nhat sau', N'NG00002', N'LO00003')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00013', N'LEVI Ls', N'ACFC', 99800.0000, 198000.0000, N'con ve', 10, N'cap nhat sau', N'NG00002', N'LO00001')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00014', N'Saigon Skyscraper T', N'TeeWorld', 319800.0000, 498000.0000, N'con ve', 17, N'tinh sao', N'NG00001', N'LO00002')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00015', N'8811-M', N'Old Sailor', 199800.0000, 298000.0000, N'con ve', 5, N'tinh sao', N'NG00003', N'LO00001')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00016', N'Man', N'Thundreds', 299800.0000, 498000.0000, N'con ve', 15, N'cap nhat sau', N'NG00004', N'LO00002')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00017', N'Arabella Drop Crotch', N'Basic', 799800.0000, 998000.0000, N'con ve', 12, N'tinh sao', N'NG00002', N'LO00001')
INSERT [dbo].[SanPham] ([maSP], [tenSP], [donViSP], [giaNhap], [giaBan], [tinhTrang], [tonKho], [image], [maNhaCC], [maLoai]) VALUES (N'CT00018', N'THE KIDS', N'Baby', 389800.0000, 567000.0000, N'con ve', 22, N'tinh sao', N'NG00002', N'LO00003')
INSERT [dbo].[User] ([id], [password], [chucNang], [maNV]) VALUES (N'ID00001', N'123', N'CV00001', N'NV00001')
INSERT [dbo].[User] ([id], [password], [chucNang], [maNV]) VALUES (N'ID00002', N'1234', N'CV00002', N'NV00002')

USE [Tau]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_HoaDon] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon] ([maHD])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_HoaDon]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_SanPham] FOREIGN KEY([maSP])
REFERENCES [dbo].[SanPham] ([maSP])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_SanPham]
GO
ALTER TABLE [dbo].[ChiTietPhieuNhap]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietPhieuNhap_PhieuNhap] FOREIGN KEY([maPhieuNhap])
REFERENCES [dbo].[PhieuNhap] ([maPhieuNhap])
GO
ALTER TABLE [dbo].[ChiTietPhieuNhap] CHECK CONSTRAINT [FK_ChiTietPhieuNhap_PhieuNhap]
GO
ALTER TABLE [dbo].[ChiTietPhieuNhap]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietPhieuNhap_SanPham] FOREIGN KEY([maSP])
REFERENCES [dbo].[SanPham] ([maSP])
GO
ALTER TABLE [dbo].[ChiTietPhieuNhap] CHECK CONSTRAINT [FK_ChiTietPhieuNhap_SanPham]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_KhachHang] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChucVu] FOREIGN KEY([maChucVu])
REFERENCES [dbo].[ChucVu] ([maChucVu])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChucVu]
GO
ALTER TABLE [dbo].[PhieuNhap]  WITH CHECK ADD  CONSTRAINT [FK_PhieuNhap_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[PhieuNhap] CHECK CONSTRAINT [FK_PhieuNhap_NhanVien]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_SanPham_Loai] FOREIGN KEY([maLoai])
REFERENCES [dbo].[Loai] ([maLoai])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_SanPham_Loai]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_SanPham_NhaCC] FOREIGN KEY([maNhaCC])
REFERENCES [dbo].[NhaCC] ([maNhaCC])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_SanPham_NhaCC]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_NhanVien]
GO
USE [master]
GO
ALTER DATABASE [Tau] SET  READ_WRITE 
GO

USE [Tau]
GO
DELETE FROM ChiTietPhieuNhap
WHERE maPhieuNhap IN (SELECT maPhieuNhap FROM PhieuNhap WHERE maNV = 'NV00003');

DELETE FROM PhieuNhap
WHERE maNV = 'NV00003';


USE [Tau]
GO

UPDATE [dbo].[NhanVien]
SET [email] = 'anhkiet@gmail.com'
WHERE [maNV] = 'NV00002';

