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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DosenActivity extends AppCompatActivity {
    private Button buttonmasukdosen,buttondaftardosen;
    private ImageView backlogdosen;
    private TextView showpassdsn;
    private String nip, pass;
    private EditText passdsnlogin;
    private EditText nipdsnlogin;
    Boolean CheckEditText;
    public static final String UserNIP = "";
    public static final String UserNama = "";
    public static final String UserID = "";
    private String JSON_STRING;


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

        nipdsnlogin = (EditText) findViewById(R.id.nipdsnlogin);
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

            LoginDsn(nip, pass);

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

        nip = nipdsnlogin.getText().toString();
        pass = passdsnlogin.getText().toString();

        if(TextUtils.isEmpty(nip) || TextUtils.isEmpty(pass))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }
    public void LoginDsn(final String nip, final String password){

        class LoginDsnClass extends AsyncTask<String,Void,String> {
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
                    //getJSON();

                    Intent intent = new Intent(DosenActivity.this, MasukDosen.class);

                    //showDosen();
                    //intent.putExtra(UserNama,konfigurasi.TAG_dsn_NAMA);
                    //intent.putExtra(UserNIP,konfigurasi.TAG_dsn_NIP);
                    intent.putExtra(UserNIP,nip);
                    //intent.putExtra(UserNama,email);

                    startActivity(intent);

                }
                else{

                    Toast.makeText(DosenActivity.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... s) {

                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_dsn_NIP,nip);
                params.put(konfigurasi.KEY_dsn_PASS,password);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_LOGIN_DSN, params);
                return res;
            }
        }

        LoginDsnClass dosenLoginClass = new LoginDsnClass();
        dosenLoginClass.execute(nip,password);
    }

    private void showDosen(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            JSONObject jo = result.getJSONObject(0);
            String id = jo.getString(konfigurasi.TAG_dsn_ID);
            String name = jo.getString(konfigurasi.TAG_dsn_NAMA);
            String nip = jo.getString(konfigurasi.TAG_dsn_NIP);

            Intent intent = new Intent(DosenActivity.this, MasukDosen.class);
            intent.putExtra(UserNama,name);
            intent.putExtra(UserNIP,nip);
            intent.putExtra(UserID,id);

            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DosenActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showDosen();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParamMail(konfigurasi.URL_GETDATA_DSN,nip,pass);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
