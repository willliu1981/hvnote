package idv.kuan.hvnote.views.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

import idv.kuan.hvnote.R;
import idv.kuan.hvnote.database.daoimpl.StatementsDao;
import idv.kuan.hvnote.database.models.Statement;
import idv.kuan.hvnote.listener.SwitchAction;
import idv.kuan.hvnote.listener.ToggleListener;
import idv.kuan.hvnote.views.recyclerview.List1Adapter;

public class ManagePhrasesActivite extends AppCompatActivity implements ToggleListener {
    public final static String DC_EDIT = "ED";
    public final static String DC_COPY = "CP";

    private RecyclerView recyclerView;
    private Button btn_dcType;

    private List1Adapter adapter;
    private SwitchAction switchAction = new SwitchAction();


    //debug
    private TextView txtv_debug;
    private Button btn_debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_phrases_activite);

        init();


        btn_dcType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                if (button.getText().equals(DC_COPY)) {
                    button.setText(DC_EDIT);
                    switchAction.setToggle(DC_EDIT);

                } else {
                    button.setText(DC_COPY);
                    switchAction.setToggle(DC_COPY);

                }
            }
        });


        //debug
        initDebugComps();
        btn_debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void init() {

        initComps();


        switchAction.registerObserver(this);
        switchAction.setToggle(DC_COPY);
    }

    private void initComps() {
        recyclerView = findViewById(R.id.mp_rv_list1);
        {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            StatementsDao dao = new StatementsDao();
            ArrayList<Statement> all = null;
            try {
                all = (ArrayList<Statement>) dao.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            adapter = new List1Adapter(all);
            switchAction.registerObserver(adapter);
            recyclerView.setAdapter(adapter);

            System.out.println("xxx MPA:size=" + all.size());
        }

        btn_dcType = findViewById(R.id.mp_btn_db_type);

    }

    private void initDebugComps() {
        txtv_debug = findViewById(R.id.mp_txtv_debug);
        btn_debug = findViewById(R.id.mp_btn_debug);
    }


    @Override
    public void update(String data) {
        btn_dcType.setText(data);

    }
}