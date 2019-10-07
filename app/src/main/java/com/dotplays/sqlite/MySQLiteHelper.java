package com.dotplays.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private Context context;

    // ham khoi tao
    public MySQLiteHelper(Context context) {
        super(context, "myData.db", null, 1);
        this.context = context;
    }

    // khởi tạo các bảng
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // khai báo câu lệnh tạo bảng
        String taoBangSV = "CREATE TABLE SinhVien (Id INTERGER PRIMARY KEY," +
                "Name TEXT, Phone TEXT)";
        sqLiteDatabase.execSQL(taoBangSV);

    }

    // chỉnh sửa thông số csdl hoặc bảng, cột
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void themSinhVien(SinhVien sinhVien) {


        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // ghep cap ten cot vs gia tri
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", sinhVien.id);
        contentValues.put("Name", sinhVien.name);
        contentValues.put("Phone", sinhVien.phone);

        long ketQua = sqLiteDatabase.insert("SinhVien",
                null, contentValues);

        if (ketQua > 0) {
            // them thanh cong
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        } else {
            // khong thanh cong
            Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();
        }

        sqLiteDatabase.close();

    }

    public ArrayList<SinhVien> danhSachSinhVien() {
        String danhSach = "SELECT * FROM SinhVien";

        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // lay gia tri truy van vao bien Cursor
        Cursor cursor = sqLiteDatabase.rawQuery(danhSach, null);

        ArrayList<SinhVien> sinhViens = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                // 0 1 2 la ColumnIndex
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                SinhVien sV = new SinhVien();
                sV.id = id;
                sV.name = name;
                sV.phone = phone;
                sinhViens.add(sV);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();

        return sinhViens;
    }

    public void capNhatSinhVien(SinhVien sinhVien) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", sinhVien.id);
        contentValues.put("Name", sinhVien.name);
        contentValues.put("Phone", sinhVien.phone);

        int ketQua = sqLiteDatabase.update("SinhVien", contentValues,
                "Id=?",
                new String[]{String.valueOf(sinhVien.id)});
        if (ketQua > 0) {
            // them thanh cong
            Toast.makeText(context, "Cập Nhật thành công", Toast.LENGTH_SHORT).show();
        } else {
            // khong thanh cong
            Toast.makeText(context, "Cập Nhật Không thành công", Toast.LENGTH_SHORT).show();
        }

        sqLiteDatabase.close();

    }

    public void xoaSV(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int ketQua = sqLiteDatabase.delete("SinhVien",
                "Id=?",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        if (ketQua > 0) {
            // them thanh cong
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        } else {
            // khong thanh cong
            Toast.makeText(context, "Xóa Không thành công", Toast.LENGTH_SHORT).show();
        }
    }

}
