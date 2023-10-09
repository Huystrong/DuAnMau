package com.example.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(Context context) {
        super(context, "QUANLYTHUVIEN", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tạo bảng loại sách
        String tLoaiSach = "CREATE TABLE LOAISACH(maloai integer primary key autoincrement,tenloai text)";
        db.execSQL(tLoaiSach);
        //data mẫu loại sách
        db.execSQL("INSERT INTO LOAISACH VALUES(1,'Thiếu Nhi')," +
                "(2,'Tình cảm')," +
                "(3,'Hành động')");


        //tạo bảng sách
        String tSach = " CREATE TABLE SACH (masach integer primary key autoincrement,tensach text,tacgia text,giaban integer , maloai integer references LOAISACH(maloai))";
        db.execSQL(tSach);
        //data mẫu bảng sách
        db.execSQL("INSERT INTO SACH VALUES(1,'Thiếu Nhi','Nhi Đồng',100000,1)," +
                "(2,'Trạng QUỲNH','kimdng',19999,1)");


        //tạo bảng người dùng
        //role:
        //1-người dùng
        //2-thủ thư
        //3- admin
        String tNguoiDung = "CREATE TABLE NGUOIDUNG(mand integer primary key autoincrement,tennd text ,sdt text,diachi text, tendangnhap text ,matkhau text,role integer)";
        db.execSQL(tNguoiDung);
        //data mẫu bảng người dùng
        db.execSQL("INSERT INTO NGUOIDUNG VALUES(1,'Nguyễn Đức Huy','097645231','Đại An -Nam Định','duchuy','123456',1)," +
                "(2,'Trịnh quoc viey','097645231','Đại An -Nam Định','viety','123456',2)," +
                "(3,'Trịnh quy','097645231','Đại An -Nam Định','quy','123456',3)");


        //tạo bảng  phiếu mượn
        String tPhieuMuon = "CREATE TABLE PHIEUMUON( mapm integer primary key autoincrement ,ngaymuon text,mand integer references NGUOIDUNG(mand)) ";
        db.execSQL(tPhieuMuon);
        //data mẫu bảng Phiếu mượn
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,'20/09/2023','26/09/2023')");

        //tạo bảng chi tiết phiếu mượn
        String tCTPM = "CREATE TABLE CTPM(mactpm integer primary key autoincrement,  mapm integer  references PHIEUMUON(mapm) ,masach integer  references SACH(masach),soluong integer) ";
        db.execSQL(tCTPM);
        //data mẫu bảng chi tiết phiếu mượn
        db.execSQL("INSERT INTO CTPM VALUES(1,1,1,2),(2,1,2,1)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS LOAISACH ");
            db.execSQL("DROP TABLE IF EXISTS SACH ");
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG ");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON ");
            db.execSQL("DROP TABLE IF EXISTS CTPM ");
            onCreate(db);
        }
    }
}
