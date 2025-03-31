package iut.dam.projet_dev_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class HabitatAdapter extends ArrayAdapter<Habitat> {

    public HabitatAdapter(Context context, List<Habitat> habitats) {
        super(context, 0, habitats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_habitats, parent, false);
        }

        Habitat habitat = getItem(position);

        TextView tvNom = convertView.findViewById(R.id.tvNom);
        TextView tvEtageNumber = convertView.findViewById(R.id.tvEtageNumber);
        TextView tvEquipementCount = convertView.findViewById(R.id.tvEquipementCount);
        LinearLayout TvEquipementList = convertView.findViewById(R.id.layoutEquipements);


        tvNom.setText(habitat.getNom());
        tvEtageNumber.setText(habitat.getEtage() + "");
        tvEquipementCount.setText(habitat.getNombreEquipement() + (habitat.getNombreEquipement() == 1 ? " Équipement" : " Équipements"));
        TvEquipementList.removeAllViews();

        for (String equipement : habitat.getEquipement()) {
            ImageView imageView = new ImageView(getContext());

            int resId = getContext().getResources().getIdentifier(equipement, "drawable", getContext().getPackageName());
            if (resId != 0) {
                imageView.setImageResource(resId);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
                params.setMargins(4, 0, 4, 0);
                imageView.setLayoutParams(params);

                TvEquipementList.addView(imageView);
            }
        }
        return convertView;
    }
}

