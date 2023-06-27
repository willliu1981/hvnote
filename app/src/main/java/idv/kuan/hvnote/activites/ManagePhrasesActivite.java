package idv.kuan.hvnote.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import idv.kuan.hvnote.R;
import idv.kuan.hvnote.database.models.Statement;

public class ManagePhrasesActivite extends AppCompatActivity {

    private TextView txtv_debug;
    private Button btn_debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_phrases_activite);


        Intent intent = getIntent();
        String msg1 = intent.getStringExtra("msg1");


        System.out.println("xxx MP:" + msg1);

        txtv_debug = findViewById(R.id.mp_txtv_debug);
        txtv_debug.setText("dbg:" + msg1);

        btn_debug = findViewById(R.id.mp_btn_debug);
        btn_debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Statement st1 = null;
                st1 = (Statement) intent.getSerializableExtra("st1");


                txtv_debug.setText("dbg:" + st1);
            }
        });

    }
}