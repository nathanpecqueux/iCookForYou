package fr.univ_littoral.nathan.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import fr.univ_littoral.nathan.myapplication.GridAdapter;
import fr.univ_littoral.nathan.myapplication.GridDetailActivity;
import fr.univ_littoral.nathan.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends Fragment {


    public GridFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new GridAdapter(getActivity()));

        /**
         * On Click event for Single Gridview Item
         * */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent intentf = new Intent(getActivity(), GridDetailActivity.class);
                intentf.putExtra("id", position);
                startActivity(intentf);

            }
        });

        return view;
    }


}
