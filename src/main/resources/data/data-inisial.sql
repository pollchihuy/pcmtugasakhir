/*
 Navicat Premium Data Transfer

 Source Server         : LOCAL_SQLEXPRESS
 Source Server Type    : SQL Server
 Source Server Version : 15002125
 Source Host           : localhost:1433
 Source Catalog        : KurtCobain
 Source Schema         : cobaan

 Target Server Type    : SQL Server
 Target Server Version : 15002125
 File Encoding         : 65001

 Date: 31/10/2024 06:37:26
*/


-- ----------------------------
-- Table structure for LogUser
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[cobaan].[LogUser]') AND type IN ('U'))
	DROP TABLE [cobaan].[LogUser]
GO

CREATE TABLE [cobaan].[LogUser] (
  [LogId] bigint  IDENTITY(1,1) NOT NULL,
  [IDAccess] bigint  NULL,
  [Alamat] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [createdAt] datetime2(6)  NULL,
  [CreatedBy] bigint  NOT NULL,
  [Email] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Flag] char(1) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [id] bigint  NULL,
  [NamaLengkap] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [NoHp] varchar(19) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Password] varchar(64) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [TanggalLahir] date  NULL,
  [Username] varchar(16) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [cobaan].[LogUser] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of LogUser
-- ----------------------------
SET IDENTITY_INSERT [cobaan].[LogUser] ON
GO

SET IDENTITY_INSERT [cobaan].[LogUser] OFF
GO


-- ----------------------------
-- Table structure for MapAksesMenu
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[cobaan].[MapAksesMenu]') AND type IN ('U'))
	DROP TABLE [cobaan].[MapAksesMenu]
GO

CREATE TABLE [cobaan].[MapAksesMenu] (
  [IDAkses] bigint  NOT NULL,
  [IDMenu] bigint  NOT NULL
)
GO

ALTER TABLE [cobaan].[MapAksesMenu] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of MapAksesMenu
-- ----------------------------
INSERT INTO [cobaan].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'1')
GO

INSERT INTO [cobaan].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'2')
GO

INSERT INTO [cobaan].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'3')
GO

INSERT INTO [cobaan].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'4')
GO

INSERT INTO [cobaan].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'5')
GO

INSERT INTO [cobaan].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'2', N'5')
GO


-- ----------------------------
-- Table structure for MstAkses
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[cobaan].[MstAkses]') AND type IN ('U'))
	DROP TABLE [cobaan].[MstAkses]
GO

CREATE TABLE [cobaan].[MstAkses] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [CreatedAt] datetime2(6)  NULL,
  [CreatedBy] bigint  NOT NULL,
  [ModifiedAt] datetime2(6)  NULL,
  [ModifiedBy] bigint  NULL,
  [NamaAkses] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [cobaan].[MstAkses] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of MstAkses
-- ----------------------------
SET IDENTITY_INSERT [cobaan].[MstAkses] ON
GO

INSERT INTO [cobaan].[MstAkses] ([id], [CreatedAt], [CreatedBy], [ModifiedAt], [ModifiedBy], [NamaAkses]) VALUES (N'1', NULL, N'1', NULL, NULL, N'ADMIN')
GO

INSERT INTO [cobaan].[MstAkses] ([id], [CreatedAt], [CreatedBy], [ModifiedAt], [ModifiedBy], [NamaAkses]) VALUES (N'2', NULL, N'1', NULL, NULL, N'MEMBER')
GO

SET IDENTITY_INSERT [cobaan].[MstAkses] OFF
GO


-- ----------------------------
-- Table structure for MstMenu
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[cobaan].[MstMenu]') AND type IN ('U'))
	DROP TABLE [cobaan].[MstMenu]
GO

CREATE TABLE [cobaan].[MstMenu] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [Nama] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Path] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO

ALTER TABLE [cobaan].[MstMenu] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of MstMenu
-- ----------------------------
SET IDENTITY_INSERT [cobaan].[MstMenu] ON
GO

INSERT INTO [cobaan].[MstMenu] ([id], [Nama], [Path]) VALUES (N'1', N'USER-MAN', N'/user')
GO

INSERT INTO [cobaan].[MstMenu] ([id], [Nama], [Path]) VALUES (N'2', N'AKSES', N'/akses')
GO

INSERT INTO [cobaan].[MstMenu] ([id], [Nama], [Path]) VALUES (N'3', N'GROUP-MENU', N'/group-menu')
GO

INSERT INTO [cobaan].[MstMenu] ([id], [Nama], [Path]) VALUES (N'4', N'MENU', N'/menu')
GO

INSERT INTO [cobaan].[MstMenu] ([id], [Nama], [Path]) VALUES (N'5', N'ARTIKEL-1', N'/artikel-1')
GO

SET IDENTITY_INSERT [cobaan].[MstMenu] OFF
GO


-- ----------------------------
-- Table structure for MstUser
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[cobaan].[MstUser]') AND type IN ('U'))
	DROP TABLE [cobaan].[MstUser]
GO

CREATE TABLE [cobaan].[MstUser] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [Alamat] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [createdAt] datetime2(6)  NULL,
  [CreatedBy] bigint  NOT NULL,
  [Email] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [ModifiedAt] datetime2(6)  NULL,
  [ModifiedBy] bigint  NULL,
  [NamaLengkap] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [NoHp] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [OTP] varchar(64) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Password] varchar(64) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [TanggalLahir] date  NULL,
  [Username] varchar(16) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [IDAccess] bigint  NULL
)
GO

ALTER TABLE [cobaan].[MstUser] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of MstUser
-- ----------------------------
SET IDENTITY_INSERT [cobaan].[MstUser] ON
GO

INSERT INTO [cobaan].[MstUser] ([id], [Alamat], [createdAt], [CreatedBy], [Email], [ModifiedAt], [ModifiedBy], [NamaLengkap], [NoHp], [OTP], [Password], [TanggalLahir], [Username], [IDAccess]) VALUES (N'1', N'Jl. Juanda No. 01, Bone, ND 30008', N'2024-10-31 06:35:50.050000', N'1', N'poll.chihuy@gmail.com', N'2024-10-31 06:35:50.050000', NULL, N'Paul Christian', N'+6281286016416', NULL, N'$2a$11$sLWfeE2opFnpmjpBWYfuUO8jJ8lSAbigqPf/C63jhZ4T6vBfglzuC', N'2002-05-02', N'poll.chihuy', N'1')
GO

SET IDENTITY_INSERT [cobaan].[MstUser] OFF
GO


-- ----------------------------
-- Auto increment value for LogUser
-- ----------------------------
DBCC CHECKIDENT ('[cobaan].[LogUser]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table LogUser
-- ----------------------------
ALTER TABLE [cobaan].[LogUser] ADD CONSTRAINT [PK__LogUser__5E548648F78DD861] PRIMARY KEY CLUSTERED ([LogId])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Uniques structure for table MapAksesMenu
-- ----------------------------
ALTER TABLE [cobaan].[MapAksesMenu] ADD CONSTRAINT [unique-akses-menu] UNIQUE NONCLUSTERED ([IDAkses] ASC, [IDMenu] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for MstAkses
-- ----------------------------
DBCC CHECKIDENT ('[cobaan].[MstAkses]', RESEED, 2)
GO


-- ----------------------------
-- Indexes structure for table MstAkses
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [UKng7cd4jbvlld3y6hhc78k3k1n]
ON [cobaan].[MstAkses] (
  [NamaAkses] ASC
)
WHERE ([NamaAkses] IS NOT NULL)
GO


-- ----------------------------
-- Primary Key structure for table MstAkses
-- ----------------------------
ALTER TABLE [cobaan].[MstAkses] ADD CONSTRAINT [PK__MstAkses__3213E83F4C9E6CEA] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for MstMenu
-- ----------------------------
DBCC CHECKIDENT ('[cobaan].[MstMenu]', RESEED, 5)
GO


-- ----------------------------
-- Uniques structure for table MstMenu
-- ----------------------------
ALTER TABLE [cobaan].[MstMenu] ADD CONSTRAINT [UK1pmqjgtk7t17sjit03fkmtsxn] UNIQUE NONCLUSTERED ([Path] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

ALTER TABLE [cobaan].[MstMenu] ADD CONSTRAINT [UKhigfmut8rhkknygmdq21gg5kl] UNIQUE NONCLUSTERED ([Nama] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table MstMenu
-- ----------------------------
ALTER TABLE [cobaan].[MstMenu] ADD CONSTRAINT [PK__MstMenu__3213E83FD58229D4] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for MstUser
-- ----------------------------
DBCC CHECKIDENT ('[cobaan].[MstUser]', RESEED, 1)
GO


-- ----------------------------
-- Indexes structure for table MstUser
-- ----------------------------
CREATE UNIQUE NONCLUSTERED INDEX [UKoboap7j0f37yn6as1f4bdg8ge]
ON [cobaan].[MstUser] (
  [Email] ASC
)
WHERE ([Email] IS NOT NULL)
GO

CREATE UNIQUE NONCLUSTERED INDEX [UKftqfpu08362mh0w1ccyoqa4x3]
ON [cobaan].[MstUser] (
  [NoHp] ASC
)
WHERE ([NoHp] IS NOT NULL)
GO

CREATE UNIQUE NONCLUSTERED INDEX [UKqjyie9v29y4i2hwga63gip2gr]
ON [cobaan].[MstUser] (
  [Username] ASC
)
WHERE ([Username] IS NOT NULL)
GO


-- ----------------------------
-- Primary Key structure for table MstUser
-- ----------------------------
ALTER TABLE [cobaan].[MstUser] ADD CONSTRAINT [PK__MstUser__3213E83F396FE7A1] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table MapAksesMenu
-- ----------------------------
ALTER TABLE [cobaan].[MapAksesMenu] ADD CONSTRAINT [fk_to_map_menu] FOREIGN KEY ([IDMenu]) REFERENCES [cobaan].[MstMenu] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [cobaan].[MapAksesMenu] ADD CONSTRAINT [fk_to_map_akses] FOREIGN KEY ([IDAkses]) REFERENCES [cobaan].[MstAkses] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table MstUser
-- ----------------------------
ALTER TABLE [cobaan].[MstUser] ADD CONSTRAINT [fk-to-akses] FOREIGN KEY ([IDAccess]) REFERENCES [cobaan].[MstAkses] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


DROP TRIGGER IF EXISTS cobaan.trgInsertUser;
GO
CREATE TRIGGER cobaan.trgInsertUser
    ON cobaan.MstUser
    FOR INSERT
AS
    INSERT INTO cobaan.LogUser(ID,Alamat,Email,NamaLengkap,NoHp,Password,TanggalLahir,Username,IDAccess,CreatedBy,CreatedAt,flag)
SELECT ID,Alamat,Email,NamaLengkap,NoHp,Password,TanggalLahir,Username,IDAccess,CreatedBy,GETDATE(),'I' FROM INSERTED; --virtual table INSERTED
GO

DROP TRIGGER IF EXISTS cobaan.trgUpdateUser;
GO
CREATE TRIGGER cobaan.trgUpdateUser
    ON cobaan.MstUser
    FOR UPDATE
            AS
        INSERT INTO cobaan.LogUser(ID,Alamat,Email,NamaLengkap,NoHp,Password,TanggalLahir,Username,IDAccess,CreatedBy,CreatedAt,flag)
SELECT ID,Alamat,Email,NamaLengkap,NoHp,Password,TanggalLahir,Username,IDAccess,CreatedBy,GETDATE(),'U' FROM DELETED; --virtual table INSERTED

GO
DROP TRIGGER IF EXISTS cobaan.trgDeleteUser;
GO
CREATE TRIGGER cobaan.trgDeleteUser
    ON cobaan.MstUser
    FOR DELETE
AS
    INSERT INTO cobaan.LogUser(ID,Alamat,Email,NamaLengkap,NoHp,Password,TanggalLahir,Username,IDAccess,CreatedBy,CreatedAt,flag)
SELECT ID,Alamat,Email,NamaLengkap,NoHp,Password,TanggalLahir,Username,IDAccess,CreatedBy,GETDATE(),'D' FROM DELETED; --virtual table INSERTED