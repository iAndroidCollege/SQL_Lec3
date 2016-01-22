package dbaccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by master on 20/01/2016.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MyNorthwind";
    private static final int DB_VERSION = 1;
    private String CREATE_TABLE_EMPLOYEES = "" +
            "CREATE TABLE Employees(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "                       FirstName TEXT NOT NULL," +
            "                       LastName TEXT NOT NULL," +
            "                       Address TEXT," +
            "                       City TEXT," +
            "                       Country TEXT," +
            "                       Phone TEXT," +
            "                       Email TEXT);";

    private String CREATE_TABLE_CUSTOMERS = "" +
            "CREATE TABLE Customers(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "                       FirstName TEXT NOT NULL," +
            "                       LastName TEXT NOT NULL," +
            "                       Address TEXT," +
            "                       City TEXT," +
            "                       Country TEXT," +
            "                       Phone TEXT," +
            "                       Email TEXT);";

    private String CREATE_TABLE_PRODUCTS = "" +
            "CREATE TABLE Products(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "                       Name TEXT NOT NULL," +
            "                       UnitPrice REAL NOT NULL" +
            "                       );";


    private String CREATE_TABLE_ORDERS = "" +
            "CREATE TABLE Orders(_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "                       ProductID INTEGER NOT NULL," +
            "                       Quantity INTEGER NOT NULL," +
            "                       EmployeeID INTEGER NOT NULL," +
            "                       CustomerID INTEGER NOT NULL," +
            "                       FOREIGN KEY (ProductID) REFERENCES Products(_ID)," +
            "                       FOREIGN KEY (EmployeeID) REFERENCES Employees(_ID)," +
            "                       FOREIGN KEY (CustomerID) REFERENCES Customers(_ID)" +
            ");";




    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EMPLOYEES);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_ORDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Orders");
        db.execSQL("DROP TABLE Employees");
        db.execSQL("DROP TABLE Customers");
        db.execSQL("DROP TABLE Products");

        onCreate(db);
    }
}
