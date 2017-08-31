package com.nimblechapps.custom_dailog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShowDialog, btnAlertDialog;
    TextView tvName, tvNumber, tvGender;
    public String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGender = (TextView) findViewById(R.id.tvGender);
        tvNumber = (TextView) findViewById(R.id.tvNumber);
        tvName = (TextView) findViewById(R.id.tvName);


        btnShowDialog = (Button) findViewById(R.id.btnShowDialog);
        btnAlertDialog = (Button) findViewById(R.id.btnAlertDialog);

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });


        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);// create the object of alert dailog
        builder.setTitle("TITLE");// set the title of alert dialog using setTitle() method
        builder.setMessage("THIS IS ALERT DIALOG DEMO");//the the msg with alert dialog using setMessage() method
        // now set the postive button with alert dialog and handle it result using setPositiveButton() method
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Ok Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        // now set the negative button with alert dialog and handle it result using setNegativeButton() method
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Cancle Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();// now show the alert dialog using show() method
    }

    private void showCustomDialog() {

        final Dialog customDialog = new Dialog(MainActivity.this);
        // the setContentView() method is used to set the custom layout for the dialog
        customDialog.setContentView(R.layout.custom_dailog_layout);

        final EditText edtUname, edtPhome;
        final RadioGroup genderRadioGroup;
        final RadioButton maleRadioButton, femaleRadioButton;
        final Button btnOk;

        gender = "";

        // using window set the hight and width of custom dialog
        Window window = customDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        // bind all controlss with custom dialog
        btnOk = (Button) customDialog.findViewById(R.id.btnOk);
        genderRadioGroup = (RadioGroup) customDialog.findViewById(R.id.genderRadioGroup);
        maleRadioButton = (RadioButton) customDialog.findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton) customDialog.findViewById(R.id.femaleRadioButton);
        edtUname = (EditText) customDialog.findViewById(R.id.edtUname);
        edtPhome = (EditText) customDialog.findViewById(R.id.edtPhome);

        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {

                if (checkedId == R.id.maleRadioButton) {
                    gender = "Male";
                } else if (checkedId == R.id.femaleRadioButton) {
                    gender = "Female";
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edtPhome.getText().toString().trim().isEmpty()) {
                    edtPhome.setError("Please Enter Phone Number");
                    edtPhome.requestFocus();
                }
                if (edtPhome.getText().toString().trim().length() < 10) {
                    edtPhome.setError("Please Enter Valid Phone Number");
                    edtPhome.requestFocus();
                }

                if (edtUname.getText().toString().trim().isEmpty()) {
                    edtUname.setError("Please Entere User Name");
                    edtUname.requestFocus();
                }

                if (gender.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
                }

                if (!edtPhome.getText().toString().isEmpty() &&
                        !edtPhome.getText().toString().isEmpty() &&
                        edtPhome.getText().toString().trim().length() == 10 &&
                        !edtUname.getText().toString().isEmpty() &&
                        !gender.isEmpty()) {

                    tvName.setText(edtUname.getText().toString());
                    tvNumber.setText(edtPhome.getText().toString());
                    tvGender.setText(gender);
                    customDialog.dismiss();
                }
            }
        });
        customDialog.show();// this show() method is used to show custom dialog
    }
}
