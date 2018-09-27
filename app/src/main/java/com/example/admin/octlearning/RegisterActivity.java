package com.example.admin.octlearning;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.octlearning.API.OctApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RegisterActivity extends AppCompatActivity {
EditText fnEditText ;
EditText lnEditText ;
EditText emailAddEditText ;
EditText mobileEditText;
EditText uniEditText;
EditText passEditText;
EditText reTypePassEditText;
Button crteAccountBtn;
String apiResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fnEditText=findViewById(R.id.fn_editText);
        lnEditText=findViewById(R.id.ln_edittext);
        emailAddEditText=findViewById(R.id.emailadd_edittext);
        mobileEditText=findViewById(R.id.mobile_edittext);
        uniEditText=findViewById(R.id.uni_edittext);
        passEditText=findViewById(R.id.pass_edittext);
        reTypePassEditText=findViewById(R.id.retype_pass_edittext);
        crteAccountBtn=findViewById(R.id.crtacc_button);
        //Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        crteAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(OctApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();
                OctApi api = retrofit.create(OctApi.class);
                Call<String> call= api.getCreateAccount("UserAPI?FirstName="+fnEditText.getText()
                        +"&LastName="+lnEditText.getText()
                +"&EmailAddress="+emailAddEditText.getText()
                        +"&Mobile="+mobileEditText.getText()
                        +"&University="+uniEditText.getText()
                        +"&Password="+passEditText.getText()
                        +"&RetypePassword="+reTypePassEditText.getText());

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                       apiResponse= response.body();
                        Toast.makeText(RegisterActivity.this, "Account is created Successfully", Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this,"There is problem with creating your account,Please Try Again",Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==android.R.id.home){
            this.finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
