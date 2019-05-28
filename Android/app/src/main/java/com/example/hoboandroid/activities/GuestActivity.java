package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoboandroid.CONSTANTS;
import com.example.hoboandroid.R;
import com.example.hoboandroid.models.order.OrderMe;
import com.example.hoboandroid.models.order.OrderProductMe;
import com.example.hoboandroid.services.OrderService;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuestActivity extends BaseActivity {

    EditText guest_email,guest_address1,guest_address2,guest_city,guest_pincode;
    Button submit,loginSignUp;
    String emailPattern,pincodePattern;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        bundle = getIntent().getBundleExtra("BuyNow");


        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        pincodePattern = "[0-9]";

        guest_email=findViewById(R.id.guest_email);
        guest_address1=findViewById(R.id.guest_address1);
        guest_address2=findViewById(R.id.guest_address2);
        guest_city=findViewById(R.id.guest_city);
        guest_pincode=findViewById(R.id.guest_pincode);

        loginSignUp = findViewById(R.id.guest_login_prompt_button);
        loginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });


        if(isLoggedIn()){
            guest_email.setText(getUserEmailId());
            guest_email.setEnabled(false);
            loginSignUp.setVisibility(View.GONE);
        }




        /*DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        final Date orderDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(orderDate); // Now use today date.
        c.add(Calendar.DATE, 5); // Adding 5 days
        final String deliveryDate = df.format(c.getTime());
*/




        submit=findViewById(R.id.guest_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });

    }
    void placeOrder(){
        final int address1=(guest_address1.getText().toString()).length();
        final int address2=(guest_address2.getText().toString()).length();
        final int city=(guest_city.getText().toString()).length();


        final LocalDate orderDate=java.time.LocalDate.now();
        final LocalDate deliveryDate=orderDate.plusDays(5);


        if(guest_email.getText().toString().trim().matches(emailPattern) & address1>1 & address2>1 & city >1) {

            submit.setEnabled(false);

            Retrofit retrofit3 = new Retrofit.Builder()
                    .baseUrl(CONSTANTS.ORDER_BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            OrderService orderService1 = retrofit3.create(OrderService.class);
            Map<String, Object> jsonParams = new ArrayMap<>();
            jsonParams.put("address1", guest_address1.getText().toString());
            jsonParams.put("address2", guest_address2.getText().toString());
            jsonParams.put("city", guest_city.getText().toString());
            jsonParams.put("deliveryDate", deliveryDate);
            jsonParams.put("orderDate", orderDate);

            jsonParams.put("orderPrice", bundle.getInt("ProductPrice", 8500));
            jsonParams.put("pincode", guest_pincode.getText().toString());
            jsonParams.put("userEmailId", guest_email.getText().toString());
            if(!isLoggedIn())
                jsonParams.put("userId",0);

            Log.d("GuestActivity","Json Value "+jsonParams.toString());

            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

            orderService1.saveOrder(body)
                    .enqueue(new Callback<OrderMe>() {
                        @Override
                        public void onResponse(Call<OrderMe> call, Response<OrderMe> response) {
                            if(response.body() != null) {
                                Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                Log.d("GuestActivity","Order save response body - "+ response.body().toString());

                                Retrofit retrofit4 = new Retrofit.Builder()
                                        .baseUrl(CONSTANTS.ORDER_BASE_URL)
                                        .client(new OkHttpClient())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                OrderService orderService2 = retrofit4.create(OrderService.class);
                                Map<String, Object> jsonParams1 = new ArrayMap<>();
                                jsonParams1.put("orderId", response.body().getData().getOrderId());
                                jsonParams1.put("productId", bundle.getInt("ProductId", 1));
                                jsonParams1.put("merchantId", bundle.getInt("MerchantId", 2));
                                jsonParams1.put("quantity", "1");
                                jsonParams1.put("productPrice", bundle.getInt("ProductPrice", 8500));

                                RequestBody body1 = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams1)).toString());

                                orderService2.saveProduct(body1)
                                        .enqueue(new Callback<OrderProductMe>() {
                                            @Override
                                            public void onResponse(Call<OrderProductMe> call, Response<OrderProductMe> response) {
                                                Log.d("GuestActivity", "OrderedProducts - "+response.body().toString());
                                            }

                                            @Override
                                            public void onFailure(Call<OrderProductMe> call, Throwable t) {
                                                Log.e("GuestActivity", "OrderedProducts failure");

                                            }
                                        });
                            }

                        }

                        @Override
                        public void onFailure(Call<OrderMe> call, Throwable t) {
                            Log.e("inorder save", "failed");

                        }
                    });



            Intent intentThanks = new Intent(getApplicationContext(), CheckoutPromptActivity.class);
            startActivity(intentThanks);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid Details", Toast.LENGTH_SHORT).show();
        }
    }
}