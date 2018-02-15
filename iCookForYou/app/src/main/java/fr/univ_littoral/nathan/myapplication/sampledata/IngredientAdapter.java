
package fr.univ_littoral.nathan.myapplication.sampledata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import fr.univ_littoral.nathan.myapplication.R;

public class IngredientAdapter extends BaseAdapter {

    public static final String TAG = IngredientAdapter.class.getSimpleName();

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Ingredient> mDataSource;


    public IngredientAdapter(Context context, ArrayList<Ingredient> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        // check if the view already exists if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.list_ingredients_user, parent, false);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.ingredient_list_name);
            holder.quantityTextView = (TextView) convertView.findViewById(R.id.ingredient_list_quantity);
            holder.unityTextView = (TextView) convertView.findViewById(R.id.ingredient_list_unity);

            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        }
        else {

            // skip all the expensive inflation/findViewById and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        // Get relevant subviews of row view
        TextView nameTextView = holder.nameTextView;
        TextView quantityTextView = holder.quantityTextView;
        TextView unityTextView = holder.unityTextView;

        //Get corresponding recipe for row
        Ingredient ingredient = (Ingredient) getItem(position);

        // Update row view's textviews to display recipe information
        nameTextView.setText(ingredient.name);
        quantityTextView.setText(ingredient.quantity);
        unityTextView.setText(ingredient.unity);

        // Use Picasso to load the image. Temporarily have a placeholder in case it's slow to load

        return convertView;
    }

    private static class ViewHolder {
        public TextView nameTextView;
        public TextView quantityTextView;
        public TextView unityTextView;
    }
}
