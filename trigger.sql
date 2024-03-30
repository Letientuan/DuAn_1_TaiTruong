-- Hàm tạo mã tiếp theo
CREATE FUNCTION func_NextID(@lastMa varchar(6), @prefix varchar(4), @size int)
RETURNS varchar(6)
AS
BEGIN
	IF (@lastMa = '')
		SET @lastMa = @prefix + REPLICATE('0', @size - LEN(@prefix))
	
	DECLARE @num_NextMa int, @NextMa varchar(6)
	SET @lastMa = LTRIM(RTRIM(@lastMa))
	SET @num_NextMa = CAST(REPLACE(@lastMa, @prefix, '') AS int) + 1
	SET @size = @size - LEN(@prefix)
	SET @NextMa = @prefix + RIGHT(REPLICATE('0', @size) + CAST(@num_NextMa AS varchar(max)), @size)
	
	RETURN @NextMa
END
GO

-- Trigger cho bảng SanPham
CREATE TRIGGER tr_NextMaSP ON [SanPham]
AFTER INSERT
AS
BEGIN 
	DECLARE @lastMa varchar(6)
	SET @lastMa = (SELECT TOP 1 Masp FROM [SanPham] ORDER BY Masp DESC)
	UPDATE [SanPham] SET Masp = dbo.func_NextID(@lastMa, 'SP', 6) WHERE Masp = ''
END
GO

-- Trigger cho bảng KhachHang
CREATE TRIGGER tr_NextMaKH ON [KhachHang]
AFTER INSERT
AS
BEGIN 
	DECLARE @lastMa varchar(6)
	SET @lastMa = (SELECT TOP 1 MaKhachHang FROM [KhachHang] ORDER BY MaKhachHang DESC)
	UPDATE [KhachHang] SET MaKhachHang = dbo.func_NextID(@lastMa, 'KH', 6) WHERE MaKhachHang = ''
END
GO

-- Trigger cho bảng NhanVien
CREATE TRIGGER tr_NextMaNV ON [NhanVien]
AFTER INSERT
AS
BEGIN 
	DECLARE @lastMa varchar(6)
	SET @lastMa = (SELECT TOP 1 MaNhanVien FROM [NhanVien] ORDER BY MaNhanVien DESC)
	UPDATE [NhanVien] SET MaNhanVien = dbo.func_NextID(@lastMa, 'NV', 6) WHERE MaNhanVien = ''
END
GO
