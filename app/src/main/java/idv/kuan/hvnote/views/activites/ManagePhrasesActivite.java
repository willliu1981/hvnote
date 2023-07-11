package idv.kuan.hvnote.views.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button btn_dcType, btn_listAddItem;


    private List1Adapter adpStatement;
    ArrayList<Statement> lstStatement;
    private SwitchAction switchAction = new SwitchAction();


    //debug
    private TextView txtv_debug;
    private Button btn_debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_phrases_activite);

        init();


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
        compsSetAction();


        switchAction.registerObserver(this);
        switchAction.setToggle(DC_COPY);
    }


    private void initComps() {
        recyclerView = findViewById(R.id.mp_rv_list1);
        {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            StatementsDao dao = new StatementsDao();

            try {
                lstStatement = (ArrayList<Statement>) dao.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            adpStatement = new List1Adapter(lstStatement);
            switchAction.registerObserver(adpStatement);
            recyclerView.setAdapter(adpStatement);

            System.out.println("xxx MPA:size=" + lstStatement.size());
        }

        btn_dcType = findViewById(R.id.mp_btn_dc_type);
        btn_listAddItem = findViewById(R.id.mp_btn_list_add_item);

    }

    private void compsSetAction() {

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

        btn_listAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ManagePhrasesActivite.this);
                dialog.setContentView(R.layout.list_item_add_dialog);

                EditText edtv_statement = dialog.findViewById(R.id.dig_list_add_item_edtv_statement);
                EditText edtv_category = dialog.findViewById(R.id.dig_list_add_item_edtv_category);

                Button btn_cancel = dialog.findViewById(R.id.dig_list_add_item_btn_cancel);
                Button btn_create = dialog.findViewById(R.id.dig_list_add_item_btn_create);

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btn_create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StatementsDao dao = new StatementsDao();
                        Statement statement = new Statement();
                        statement.setStatement(edtv_statement.getText().toString());
                        statement.setCategory(edtv_category.getText().toString());

                        try {
                            dao.create(statement);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();
                        lstStatement.add(statement);
                        adpStatement.notifyDataSetChanged();
                    }
                });

                dialog.show();

            }
        });
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