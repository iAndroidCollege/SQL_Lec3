package college.edu.tomer.sql_lec3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragments.CustomersFragment;
import fragments.EmployeeFragment;
import fragments.OrdersFragment;
import fragments.ProductFragment;

/**
 * Created by master on 20/01/2016.
 */
public class NorthwindPagerAdapter extends FragmentStatePagerAdapter {
    public NorthwindPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new CustomersFragment();
            case 1:
                return new EmployeeFragment();
            case 2:
                return new ProductFragment();
            case 3:
                return new OrdersFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
              return "Customers";
            case 1:
                return "Employees";
            case 2:
                return "Products";
            case 3:
                return "Orders";
        }
        return null;
    }
}
