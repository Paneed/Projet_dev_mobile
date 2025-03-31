package iut.dam.projet_dev_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class HabitatActivity extends AppCompatActivity {

    private ListView listViewHabitats;
    private HabitatAdapter adapter;
    private List<Habitat> habitatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitats);
        habitatList = generateListeEquipement();
        listViewHabitats = findViewById(R.id.lvEquipements);
        adapter = new HabitatAdapter(this, habitatList);
        listViewHabitats.setAdapter(adapter);

        listViewHabitats.setOnItemClickListener((parent, view, position, id) -> {
            Habitat habitat = adapter.getItem(position);

            if (habitat != null) {
                Intent intent = new Intent(HabitatActivity.this, MyHabitatActivity.class);
                intent.putExtra("habitat", habitat);
                startActivity(intent);

            }
        });
    }


    public List<Habitat> generateListeEquipement() {
        List<Habitat> listeAppartement = new ArrayList<>();
        listeAppartement.add(new Habitat("Gaëtan Leclair", 1, new ArrayList<>(List.of("ic_machine_a_laver", "ic_aspirateur", "ic_climatiseur", "ic_fer_a_repasser"))));
        listeAppartement.add(new Habitat("Cédric Boudet", 1, new ArrayList<>(List.of("ic_machine_a_laver"))));
        listeAppartement.add(new Habitat("Gaylord Thibodeaux", 2, new ArrayList<>(List.of("ic_fer_a_repasser", "ic_aspirateur"))));
        listeAppartement.add(new Habitat("Adam Jacquinot", 3, new ArrayList<>(List.of("ic_machine_a_laver", "ic_fer_a_repasser", "ic_aspirateur"))));
        listeAppartement.add(new Habitat("Abel Fresnel", 3, new ArrayList<>(List.of("ic_aspirateur"))));
        listeAppartement.add(new Habitat("Jean Paul", 4, new ArrayList<>(List.of("ic_aspirateur", "ic_fer_a_repasser"))));
        return listeAppartement;
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}