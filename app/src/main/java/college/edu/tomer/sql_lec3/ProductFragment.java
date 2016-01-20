package college.edu.tomer.sql_lec3;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment implements View.OnClickListener{


    @Bind(R.id.addProduct)
    FloatingActionButton addProduct;
    @Bind(R.id.EditProduct)
    FloatingActionButton EditProduct;
    @Bind(R.id.rcProducts)
    RecyclerView rcProducts;
    private EditText etProductName;
    private EditText etUnitPrice;
    private AlertDialog addProductDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.addProduct)
    void addProduct(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Product");
        View v = getLayoutInflater(null).inflate(R.layout.activity_add_product, null, false);
        v.findViewById(R.id.btnAddProduct).setOnClickListener(this);
        etProductName = (EditText)v.findViewById(R.id.etProductName);
        etUnitPrice = (EditText)v.findViewById(R.id.etUnitPrice);
        builder.setView(v);

        addProductDialog = builder.show();
    }

    //AddProduct:
    @Override
    public void onClick(View v) {
        try {
            String name = etProductName.getText().toString();
            Double unitPrice = Double.valueOf(etUnitPrice.getText().toString());
            MyDbHelper helper = new MyDbHelper(getActivity());
            //Beware Of SQL Injections!!!
/*
            helper.getWritableDatabase().execSQL("INSERT INTO Products(Name, UnitPrice) " +
                    "                   VALUES('" + name + "', '" + unitPrice + "')");
*/
            ContentValues value = new ContentValues();
            value.put("Name", etProductName.getText().toString());
            value.put("UnitPrice", Double.valueOf(etUnitPrice.getText().toString()));
            helper.getWritableDatabase().insert("Products", null, value);

            addProductDialog.dismiss();
            Toast.makeText(getActivity(), "Got it", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            etUnitPrice.setError("Numbers Only!");
            //etUnitPrice.setText("");
            e.printStackTrace();
        }
    }
}
