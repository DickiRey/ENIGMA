package com.example.enigma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class DosenActivity extends AppCompatActivity {
    private Button buttonmasukdosen,buttondaftardosen;
    private String email, pass;
    private ImageView backlogdosen;
    private TextView showpassdsn;
    private EditText passdsnlogin;
    private EditText emaildsnlogin;
    Boolean CheckEditText ;
    public static final String UserEmail = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

//        Button Masuk Dosen
        buttonmasukdosen = (Button) findViewById(R.id.buttonmasukdosen);
        buttonmasukdosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMasukDosen();
            }
        });
//        Button back
        backlogdosen = (ImageView) findViewById(R.id.backlogdosen);
        backlogdosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKembaliDosen();
            }
        });
//        Button sign up
        buttondaftardosen = (Button) findViewById(R.id.buttonsignupdosen);
        buttondaftardosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDaftarDosen();
            }
        });

        emaildsnlogin = (EditText) findViewById(R.id.emaildsnlogin);
        passdsnlogin = (EditText) findViewById(R.id.passdsnlogin);
        showpassdsn = (TextView)findViewById(R.id.showpassdsn);

        showpassdsn.setVisibility(View.GONE);
        passdsnlogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passdsnlogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passdsnlogin.getText().length()>0){
                    showpassdsn.setVisibility(View.VISIBLE);
                }
                else{
                    showpassdsn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        showpassdsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showpassdsn.getText() == "SHOW"){
                    showpassdsn.setText("HIDE");
                    passdsnlogin.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passdsnlogin.setSelection(passdsnlogin.length());
                }
                else{
                    showpassdsn.setText("SHOW");
                    passdsnlogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passdsnlogin.setSelection(passdsnlogin.length());
                }
            }
        });
    }

//    Tampil ke Masuk Dosen
    public void openMasukDosen(){
        CheckEditTextIsEmptyOrNot();

        if(CheckEditText){

            Login(email, pass);

        }
        else {

            Toast.makeText(DosenActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

        }
//        Intent intent = new Intent(this, MasukDosen.class);
//        startActivity(intent);
    }
    public void openKembaliDosen(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void openDaftarDosen(){
        Intent intent = new Intent(this, SignUpDosen.class);
        startActivity(intent);
    }
    public void CheckEditTextIsEmptyOrNot(){

        email = emaildsnlogin.getText().toString();
        pass = passdsnlogin.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }
    public void Login(final String email, final String password){

        class LoginClass extends AsyncTask<String,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(DosenActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if(httpResponseMsg.equalsIgnoreCase("Data Matched")){

                    finish();

                    Intent intent = new Intent(DosenActivity.this, MasukDosen.class);

                    intent.putExtra(UserEmail,email);

                    startActivity(intent);

                }
                else{

                    Toast.makeText(DosenActivity.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... s) {

                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_dsn_EMAIL,email);
                params.put(konfigurasi.KEY_dsn_PASS,password);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_LOGIN_DSN, params);
                return res;
            }
        }

        LoginClass userLoginClass = new LoginClass();
        userLoginClass.execute(email,password);
    }

}
