package android.example.listofpatients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PatientList extends AppCompatActivity implements ListViewAdapter.OnListListener {

public List<Patient>listofPatients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_patient);



        listofPatients = new ArrayList<Patient>();


        listofPatients.add(new Patient("David", "Cardiology", 23230));
        listofPatients.add(new Patient("Sarah", "Intensive Care", 23231));
        listofPatients.add(new Patient("James", "Oncology", 23232));
        listofPatients.add(new Patient("Frank", "Emergency", 23233));
        listofPatients.add(new Patient("Francis", "Oncology", 23234));
        listofPatients.add(new Patient("Kate", "Intensive Care", 23235));
        listofPatients.add(new Patient("Peter", "Emergency", 23236));
        listofPatients.add(new Patient("David", "Neurology", 23237));
        listofPatients.add(new Patient("Ciara", "Cardiology", 23238));
        listofPatients.add(new Patient("Ian", "Neurology", 23239));



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new ListViewAdapter(listofPatients, this );
        recyclerView.setAdapter(mAdapter);



    }

    @Override
    public void onListClick(int position) {

        String name = "";

      for(Patient patient : listofPatients) {
          if(patient == listofPatients.get(position)){
              name = patient.name;
          }

      }


        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

    }
}
