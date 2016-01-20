package college.edu.tomer.sql_lec3;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.etProductName)
    EditText etProductName;
    @Bind(R.id.etUnitPrice)
    EditText etUnitPrice;
    @Bind(R.id.btnAddProduct)
    Button btnAddProduct;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @OnClick(R.id.btnAddProduct)
    void addProduct(){
        try {
            String name = etProductName.getText().toString();
            Double unitPrice = Double.valueOf(etUnitPrice.getText().toString());
            MyDbHelper helper = new MyDbHelper(this);
            //Beware Of SQL Injections!!!
/*
            helper.getWritableDatabase().execSQL("INSERT INTO Products(Name, UnitPrice) " +
                    "                   VALUES('" + name + "', '" + unitPrice + "')");
*/
            ContentValues value = new ContentValues();
            value.put("Name", etProductName.getText().toString());
            value.put("UnitPrice", Double.valueOf(etUnitPrice.getText().toString()));
            helper.getWritableDatabase().insert("Products", null, value);
            Toast.makeText(AddProductActivity.this, "Got it", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            etUnitPrice.setError("Numbers Only!");
            //etUnitPrice.setText("");
            e.printStackTrace();
        }
    }
}
