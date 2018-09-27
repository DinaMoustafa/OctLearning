package com.example.admin.octlearning;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.octlearning.API.OctApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    TextView createAccountTextView;
    TextView forgotPassTextView;
    EditText emailAddressEditText;
    EditText passwordEditText;
    Button logInBtn;
    String apiResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createAccountTextView = findViewById(R.id.create_account_text_view);
        forgotPassTextView = findViewById(R.id.forgot_pass_text_view);
        emailAddressEditText = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edit_text);
        logInBtn = findViewById(R.id.login_button);
        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        forgotPassTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailAddressEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(OctApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OctApi api = retrofit.create(OctApi.class);
                Call<String> call = api.getLogin("user?Email=" + email + "&" + "Password=" + password);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        apiResponse = response.body();
                        if (apiResponse.equals("true")) {
                            Toast.makeText(MainActivity.this, "Email & Password Passed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong User Name Or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "OOPS! Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
