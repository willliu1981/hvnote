package idv.kuan.hvnote.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import idv.kuan.hvnote.R;

public class EntranceActivity extends AppCompatActivity {

    private Button btn_entrance;
    private TextView txtv_debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        btn_entrance = findViewById(R.id.entr_btn_entrance);
        txtv_debug = findViewById(R.id.entr_txtv_debug);

        btn_entrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtv_debug.setText("test");
            }
        });

    }
}