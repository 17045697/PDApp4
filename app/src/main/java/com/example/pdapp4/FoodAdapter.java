package com.example.pdapp4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {
    private ArrayList<Food> food;
    private Context context;
    private TextView tvLocation, tvAddress, tvDate, tvBestDish;
    private ImageView ivdish, ivStar, ivStar2, ivStar3, ivStar4, ivStar5;

    public FoodAdapter(Context context, int resource, ArrayList<Food> objects) {
        super(context, resource, objects);
        food = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvLocation = rowView.findViewById(R.id.textViewLocation);
        tvAddress = rowView.findViewById(R.id.textViewAddress);
        tvDate = rowView.findViewById(R.id.textViewDate);
        tvBestDish = rowView.findViewById(R.id.textViewBestDish);

        ivdish = rowView.findViewById(R.id.ivBestDish);
        ivStar = rowView.findViewById(R.id.iv1);
        ivStar2 = rowView.findViewById(R.id.iv2);
        ivStar3 = rowView.findViewById(R.id.iv3);
        ivStar4 = rowView.findViewById(R.id.iv4);
        ivStar5 = rowView.findViewById(R.id.iv5);

        Food currentFood = food.get(position);

        tvLocation.setText(currentFood.getLocation());
        tvAddress.setText(currentFood.getAddress());
        tvDate.setText(currentFood.getDate());
        tvBestDish.setText(currentFood.getBestdish());

        int stars = currentFood.getStars();

        if(stars == 5) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.star);
            ivStar3.setImageResource(R.drawable.star);
            ivStar4.setImageResource(R.drawable.star);
            ivStar5.setImageResource(R.drawable.star);

        }
        else if(stars == 4) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.star);
            ivStar3.setImageResource(R.drawable.star);
            ivStar4.setImageResource(R.drawable.star);
            ivStar5.setImageResource(R.drawable.nostar);
        }

        else if(stars == 3) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.star);
            ivStar3.setImageResource(R.drawable.star);
            ivStar4.setImageResource(R.drawable.nostar);
            ivStar5.setImageResource(R.drawable.nostar);
        }

        else if(stars == 2) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.star);
            ivStar3.setImageResource(R.drawable.nostar);
            ivStar4.setImageResource(R.drawable.nostar);
            ivStar5.setImageResource(R.drawable.nostar);
        }

        else if(stars == 1) {
            ivStar.setImageResource(R.drawable.star);
            ivStar2.setImageResource(R.drawable.nostar);
            ivStar3.setImageResource(R.drawable.nostar);
            ivStar4.setImageResource(R.drawable.nostar);
            ivStar5.setImageResource(R.drawable.nostar);
        }

        else {
            ivStar.setImageResource(R.drawable.nostar);
            ivStar2.setImageResource(R.drawable.nostar);
            ivStar3.setImageResource(R.drawable.nostar);
            ivStar4.setImageResource(R.drawable.nostar);
            ivStar5.setImageResource(R.drawable.nostar);
        }


        return rowView;
    }
}
