package sg.edu.rp.c364.id22014057.l03_billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup rgMode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        numPax =findViewById(R.id.numPax);
        svs =findViewById(R.id.svs);
        gst =findViewById(R.id.gst);
        totalBill =findViewById(R.id.totalBill);
        eachPays =findViewById(R.id.eachPays);
        split =findViewById(R.id.split);
        reset =findViewById(R.id.reset);
        discount =findViewById(R.id.discount);
        rgMode =findViewById(R.id.rgMode);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String said="";
                int checkedRadioId=rgMode.getCheckedRadioButtonId();
                if(checkedRadioId==R.id.rgMode){
                    said=" via Cash ";
                }
                else{
                    said=" via PayNow ";
                }
                if(amount.getText().toString().length()!=0 && numPax.getText().toString().length()!=0) {
                    double newAmount = 0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString());
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
                    if (discount.getText().toString().length() != 0) {
                        newAmount *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }
                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmount));
                    int numPerson = Integer.parseInt(numPax.getText().toString());
                    if (numPerson != 1)
                        eachPays.setText("Each Pays: $" + String.format("%.2f", newAmount / numPerson) + said);
                    else
                        eachPays.setText("Each Pays: $" + newAmount + said);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.setText("");
                numPax.setText("");
                discount.setText("");
                svs.setChecked(false);
                gst.setChecked(false);

            }
        });
    }
}
