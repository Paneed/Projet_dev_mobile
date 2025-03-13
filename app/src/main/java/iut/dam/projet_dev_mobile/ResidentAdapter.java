package iut.dam.projet_dev_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ResidentAdapter extends RecyclerView.Adapter<ResidentAdapter.ResidentViewHolder> {
    private List<Resident> residentList;

    public ResidentAdapter(List<Resident> residentList) {
        this.residentList = residentList;
    }

    @NonNull
    @Override
    public ResidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resident, parent, false);
        return new ResidentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentViewHolder holder, int position) {
        Resident resident = residentList.get(position);
        holder.nameTextView.setText(resident.getName());
        holder.habitatTextView.setText("Habitat: " + resident.getHabitat());
        holder.equipmentTextView.setText("Équipements: " + String.join(", ", resident.getEquipments()));
    }

    @Override
    public int getItemCount() {
        return residentList.size();
    }

    static class ResidentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, habitatTextView, equipmentTextView;

        public ResidentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_name);
            habitatTextView = itemView.findViewById(R.id.text_habitat);
            equipmentTextView = itemView.findViewById(R.id.text_equipments);
        }
    }
}