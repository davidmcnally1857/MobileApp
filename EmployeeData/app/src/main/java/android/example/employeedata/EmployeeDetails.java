package android.example.employeedata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeDetails extends AppCompatActivity {

    public TextView Name;
    public TextView Age;
    public TextView Status;
    public Button buttonOk;
    public Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        Name = (TextView) findViewById(R.id.Name);
        Age = (TextView) findViewById(R.id.Age);
        Status = (TextView) findViewById(R.id.Status);
        buttonOk = (Button) findViewById(R.id.btnOk);
        buttonCancel = (Button) findViewById(R.id.btnCancel);



        Intent intent = getIntent();
        Name.setText(intent.getStringExtra("Name"));
        Age.setText(intent.getStringExtra("Age"));
        Status.setText(intent.getStringExtra("Status"));

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Name.getText().toString();
                String age = Age.getText().toString();
                String status = Status.getText().toString();

                SharedPreferences sp = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Name", name);
                editor.putString("Age", age);
                editor.putString("Status", status);
                editor.commit();

                Intent intent = new Intent(EmployeeDetails.this, MainActivity.class);
                startActivity(intent);

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
