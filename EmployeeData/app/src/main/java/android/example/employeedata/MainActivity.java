package android.example.employeedata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Employee person;

    public TextView FirstName;
    public TextView Surname;
    public TextView Age;
    public TextView Occupation;
    public TextView Grade;
    public Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstName = (EditText) findViewById(R.id.FirstName);
        Surname = (EditText) findViewById(R.id.Surname);
        Age = (EditText) findViewById(R.id.Age);
        Occupation = (EditText) findViewById(R.id.Occupation);
        Grade = (EditText) findViewById(R.id.Grade);
        button = (Button) findViewById(R.id.BtnSend);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = FirstName.getText().toString();
                String age = Age.getText().toString();
                String occupation = Occupation.getText().toString();
                String grade = Grade.getText().toString();

                Intent intent = new Intent(MainActivity.this, EmployeeDetails.class);
                intent.putExtra("Name", firstname);
                intent.putExtra("Age", age);
                intent.putExtra("Status", (occupation + grade));
                startActivity(intent);
            }
        });
    }


    }

