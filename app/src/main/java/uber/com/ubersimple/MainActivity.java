package uber.com.ubersimple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button driver_btn, customer_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        driver_btn= findViewById(R.id.driver_btn);
        customer_btn= findViewById(R.id.customer_btn);
        driver_btn.setOnClickListener(this);
        customer_btn.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.driver_btn:
                Intent intent = new Intent(MainActivity.this,DriverLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.customer_btn:
                Intent customerIntent = new Intent(MainActivity.this,CustomerLoginActivity.class);
                startActivity(customerIntent);
                break;
        }
    }
}
