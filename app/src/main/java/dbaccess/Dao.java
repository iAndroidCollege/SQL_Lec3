package dbaccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dao {
    private static Dao instance;
    private final SQLiteDatabase db;

    private Dao(Context context) {
        MyDbHelper helper = new MyDbHelper(context);
        db = helper.getWritableDatabase();
    }

    public static synchronized Dao getSharedInstance(Context context) {
        if (instance == null)
            instance = new Dao(context);
        return instance;
    }

    public void addProduct(String name, Double unitPrice) {
        ContentValues value = new ContentValues();
        value.put("Name", name);
        value.put("UnitPrice", unitPrice);
        db.insert("Products", null, value);
    }

    public Cursor getNotesCursor() {
        return db.rawQuery("SELECT * FROM Products", null);
    }

    public void deleteItem(String id) {
        db.delete("Products", "_ID=?", new String[]{id});
    }

    public void updateProduct(String id, String name, String unitPrice) {
        ContentValues value = new ContentValues();
        value.put("Name", name);
        value.put("UnitPrice", unitPrice);

        db.update("Products", value, "_ID=?", new String[]{id});
    }
}
