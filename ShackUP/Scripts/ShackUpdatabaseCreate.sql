USE master
GO

If EXISTS(SELECT * FROM sys.databases WHERE name='ShackUp')
Drop DATABASE ShackUp
GO

CREATE DATABASE ShackUp
GO