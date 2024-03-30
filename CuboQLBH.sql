USE QLBH_CBtest
GO
/****** Object:  Table [dbo].[ChatLieu]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create database QLBH_CBtest
CREATE TABLE [dbo].[ChatLieu](
	[MaChatLieu] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaChatLieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DongSP]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DongSP](
	[MaDongSp] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDongSp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHoaDon] [bigint] IDENTITY(1,1) NOT NULL,
	[IdKH] [varchar](6) NULL,
	[IdNV] [varchar](6) NULL,
	[NgayTao] [date] NULL,
	[NgayThanhToan] [date] NULL,
	[TinhTrang] [int] NULL,
	[GhiChu] [nvarchar](100) NULL,
	[Tongtien] [decimal](20, 3) NULL,
	[Chietkhau] [decimal](20, 3) NULL,
	[ThanhToan] [decimal](20, 3) NULL,
	[PhuongThucThanhToan] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonChiTiet]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonChiTiet](
	[MaHDCT] [bigint] IDENTITY(1,1) NOT NULL,
	[MaHoaDon] [bigint] NULL,
	[MaSP] [varchar](6) NULL,
	[tenSp] [nvarchar](50) NULL,
	[SoLuong] [int] NULL,
	[DonGia] [decimal](20, 3) NULL,
	[chietkhau] [decimal](20, 3) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHDCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKhachHang] [varchar](6) NOT NULL,
	[TenKhachHang] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](max) NULL,
	[SoDienThoai] [varchar](15) NULL,
	[Email] [varchar](50) NULL,
	[GioiTinh] [bit] NULL,
	[TrangThai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMai](
	[MaKM] [varchar](10) NOT NULL,
	[TenKhuyenMai] [nvarchar](20) NULL,
	[GiaTriGiam] [money] NULL,
	[MoTa] [nvarchar](max) NULL,
	[MaSanPham] [varchar](6) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MauSac]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MauSac](
	[MaMau] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaMau] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNhanVien] [varchar](6) NOT NULL,
	[TenNhanVien] [nvarchar](50) NULL,
	[GioiTinh] [nvarchar](10) NULL,
	[NgaySinh] [date] NULL,
	[DiaChi] [nvarchar](100) NULL,
	[Sdt] [varchar](30) NULL,
	[Email] [varchar](50) NULL,
	[Taikhoan] [varchar](30) NULL,
	[MatKhau] [varchar](50) NULL,
	[chucvu] [nvarchar](20) NULL,
	[TrangThai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NSX]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NSX](
	[MaNsx] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNsx] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 12/1/2023 12:25:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSP] [varchar](6) NOT NULL,
	[MaNsx] [int] NULL,
	[MaMauSac] [int] NULL,
	[MaDongSP] [int] NULL,
	[MaChatLieu] [int] NULL,
	[Size] [varchar](10) NULL,
	[MoTa] [nvarchar](50) NULL,
	[SoLuongTon] [int] NULL,
	[GiaNhap] [decimal](20, 3) NULL,
	[GiaBan] [decimal](20, 3) NULL,
	[Anh] [varchar](50) NULL,
	[Tensp] [nvarchar](50) NULL,
	[TrangThai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (NULL) FOR [NgayTao]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (NULL) FOR [NgayThanhToan]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT ((0)) FOR [TinhTrang]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (NULL) FOR [GhiChu]
GO
ALTER TABLE [dbo].[HoaDonChiTiet] ADD  DEFAULT ((0)) FOR [DonGia]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (NULL) FOR [GioiTinh]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (NULL) FOR [NgaySinh]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (NULL) FOR [DiaChi]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT (NULL) FOR [Sdt]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[SanPham] ADD  DEFAULT (NULL) FOR [MoTa]
GO
ALTER TABLE [dbo].[SanPham] ADD  DEFAULT ((0)) FOR [GiaNhap]
GO
ALTER TABLE [dbo].[SanPham] ADD  DEFAULT ((0)) FOR [GiaBan]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([IdKH])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([IdNV])
REFERENCES [dbo].[NhanVien] ([MaNhanVien])
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD FOREIGN KEY([MaHoaDon])
REFERENCES [dbo].[HoaDon] ([MaHoaDon])
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[KhuyenMai]  WITH CHECK ADD FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD FOREIGN KEY([MaChatLieu])
REFERENCES [dbo].[ChatLieu] ([MaChatLieu])
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD FOREIGN KEY([MaDongSP])
REFERENCES [dbo].[DongSP] ([MaDongSp])
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD FOREIGN KEY([MaMauSac])
REFERENCES [dbo].[MauSac] ([MaMau])
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD FOREIGN KEY([MaNsx])
REFERENCES [dbo].[NSX] ([MaNsx])
GO

INSERT INTO [dbo].[ChatLieu] ([Ten])
VALUES
    (N'Chất liệu 1'),
    (N'Chất liệu 2')
  
    


INSERT INTO [dbo].[DongSP] ([Ten])
VALUES
    (N'Dòng sản phẩm 1'),
    (N'Dòng sản phẩm 2')
  
    


INSERT INTO [dbo].[NSX] ([Ten])
VALUES
    (N'Nhà sản xuất 1'),
    (N'Nhà sản xuất 2')
  
    


INSERT INTO [dbo].[MauSac] ([Ten])
VALUES
    (N'Màu sắc 1'),
    (N'Màu sắc 2')
  
    


INSERT INTO [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [Sdt], [Email], [Taikhoan], [MatKhau], [chucvu], [TrangThai])
VALUES
    ('NV001', N'Nhân viên 1', N'Nam', '1990-01-01', N'Địa chỉ 1', '123456789', 'nhanvien1@email.com', 'user1', 'password1', N'admin', 1),
    ('NV002', N'Nhân viên 2', N'Nữ', '1995-05-05', N'Địa chỉ 2', '987654321', 'nhanvien2@email.com', 'user2', 'password2', N'user', 1)
   
    


INSERT INTO [dbo].[SanPham] ([MaSP], [MaNsx], [MaMauSac], [MaDongSP], [MaChatLieu], [Size], [MoTa], [SoLuongTon], [GiaNhap], [GiaBan], [Anh], [Tensp], [TrangThai])
VALUES
    ('SP001', 1, 1, 1, 1, 'L', N'Mô tả sản phẩm 1', 100, 50000.000, 75000.000, 'anh1.jpg', N'Sản phẩm 1', 1),
    ('SP002', 2, 2, 2, 2, 'M', N'Mô tả sản phẩm 2', 150, 70000.000, 95000.000, 'anh2.jpg', N'Sản phẩm 2', 1)
   
    


INSERT INTO [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SoDienThoai], [Email], [GioiTinh], [TrangThai])
VALUES
    ('KH001', N'Khách hàng 1', N'Địa chỉ 1', '123456789', 'khachhang1@email.com', 1, 1),
    ('KH002', N'Khách hàng 2', N'Địa chỉ 2', '987654321', 'khachhang2@email.com', 0, 1)
    
    


INSERT INTO [dbo].[HoaDon] ([IdKH], [IdNV], [NgayTao], [NgayThanhToan], [TinhTrang], [GhiChu], [Tongtien], [Chietkhau], [ThanhToan], [PhuongThucThanhToan])
VALUES
    ('KH001', 'NV001', '2023-01-01', '2023-01-02', 1, N'Giao hàng nhanh', 100000.000, 5000.000, 95000.000, N'Thanh toán khi nhận hàng'),
    ('KH002', 'NV002', '2023-02-01', '2023-02-02', 2, N'Giao hàng tiêu chuẩn', 150000.000, 10000.000, 140000.000, N'Thanh toán qua chuyển khoản')
   
    


INSERT INTO [dbo].[HoaDonChiTiet] ([MaHoaDon], [MaSP], [tenSp], [SoLuong], [DonGia], [chietkhau])
VALUES
    (1, 'SP001', N'Sản phẩm 1', 2, 75000.000, 2000.000),
    (2, 'SP002', N'Sản phẩm 2', 3, 95000.000, 5000.000)
    
    

INSERT INTO [dbo].[KhuyenMai] ([MaKM], [TenKhuyenMai], [GiaTriGiam], [MoTa], [MaSanPham])
VALUES
    ('KM001', N'Khuyến mãi 1', 10000.000, N'Giảm giá đặc biệt', 'SP001'),
    ('KM002', N'Khuyến mãi 2', 15000.000, N'Giảm giá mùa hè', 'SP002')

    
