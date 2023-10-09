package com.example.duanmau.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmau.database.DbHelper;

public class NguoiDungDAO {
    private DbHelper dbHelper;

    public NguoiDungDAO(Context context) {
        dbHelper = new DbHelper((context));

    }

    // kiểm tra thông tin đăng nhâp
    //nếu có giá trị (nhập uer +pas đúng
    //nguọc lại trả về fall

    public boolean KiemTraDangNhap(String username, String pasword) {

        SQLiteDatabase sqLiteOpenHelper = dbHelper.getReadableDatabase();
        Cursor cursor =sqLiteOpenHelper.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau= ?", new String[]{username,pasword});
//   if (cursor.getCount()>0)
//       return true;
//   else return false;
        return cursor.getCount()>0;
    }
}
