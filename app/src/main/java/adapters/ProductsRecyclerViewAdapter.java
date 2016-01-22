package adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import college.edu.tomer.sql_lec3.R;
import dbaccess.Dao;

/**
 * Created by master on 22/01/2016.
 */
public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductViewHolder> {

    private final LayoutInflater inflater;
    private final Context context;
    private Cursor cursor;


    //Constructor:
    public ProductsRecyclerViewAdapter(Cursor cursor, Context context) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cursor = cursor;
        this.context = context;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.rv_item_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        cursor.moveToPosition(position);

        String id = cursor.getString(cursor.getColumnIndex("_ID"));
        String name = cursor.getString(cursor.getColumnIndex("Name"));
        String unitprice = cursor.getString(cursor.getColumnIndex("UnitPrice"));
        holder.tvProductID.setText(id);
        holder.tvProductName.setText(name);
        holder.tvProductUnitPrice.setText(unitprice);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    //FindView By ID for all the items in a recycler view item
    class ProductViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvProductID)
        TextView tvProductID;
        @Bind(R.id.tvProductName)
        TextView tvProductName;
        @Bind(R.id.tvProductUnitPrice)
        TextView tvProductUnitPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            styleText(tvProductID, tvProductName, tvProductUnitPrice);
        }

        void styleText(TextView... tvs) {
            AssetManager assets = context.getAssets();
            Typeface mono = Typeface.createFromAsset(assets, "DroidSansMono.ttf");
            for (TextView tv : tvs) {
                tv.setTypeface(mono);
                tv.setTextSize(24);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tv.setTextAppearance(android.R.style.TextAppearance_Large);
                } else {
                    tv.setTextAppearance(context, android.R.style.TextAppearance_Large);
                }

                tv.setAllCaps(false);
            }
        }

        @OnClick(R.id.btnDelete)
        void deleteItem() {
            int adapterPosition = getAdapterPosition();
            Context context = tvProductID.getContext();
            Toast.makeText(context, adapterPosition + "", Toast.LENGTH_SHORT).show();
            Dao.getSharedInstance(context).deleteItem(tvProductID.getText().toString());
            Cursor cursor = Dao.getSharedInstance(context).getNotesCursor();
            setCursor(cursor);
        }

        @OnClick(R.id.btnEdit)
        void editItem() {
            int adapterPosition = getAdapterPosition();
            Context context = tvProductID.getContext();
            Toast.makeText(context, adapterPosition + "", Toast.LENGTH_SHORT).show();

            //
            dispEditDialog(tvProductID.getText().toString(),
                    tvProductName.getText().toString(),
                    tvProductUnitPrice.getText().toString());
        }

        private void dispEditDialog(final String productId, final String name, String unitprice) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Edit Product");
            View v = inflater.inflate(R.layout.dialog_add_product, null, false);
            builder.setView(v);

            final EditText etName = (EditText) v.findViewById(R.id.etProductName);
            final EditText etPrice = (EditText) v.findViewById(R.id.etUnitPrice);
            Button btnSave = (Button) v.findViewById(R.id.btnAddProduct);
            etName.setText(name);
            etPrice.setText(unitprice);
            btnSave.setText("Save");

            final AlertDialog dialog = builder.create();
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dao.getSharedInstance(context).updateProduct(productId, etName.getText().toString(), etPrice.getText().toString());
                    dialog.dismiss();

                    Cursor cursor = Dao.getSharedInstance(context).getNotesCursor();
                    setCursor(cursor);
                }
            });
            dialog.show();
        }
    }
}
