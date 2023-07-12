package idv.kuan.hvnote.views.recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;

import idv.kuan.hvnote.R;
import idv.kuan.hvnote.database.daoimpl.StatementsDao;
import idv.kuan.hvnote.database.models.Statement;
import idv.kuan.hvnote.listener.ToggleListener;
import idv.kuan.hvnote.views.activites.ManagePhrasesActivite;

public class List1Adapter extends RecyclerView.Adapter implements ToggleListener {
    private ArrayList<Statement> list;
    private String dcType;

    public List1Adapter(ArrayList<Statement> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1_layout, parent, false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder1(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Statement statement = list.get(position);
        ViewHolder1 vh = (ViewHolder1) holder;
        vh.bindData(statement);

        //holder.itemView.findViewById(R.id.rv_list_item_txtv_category);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                if (dcType.equals(ManagePhrasesActivite.DC_EDIT)) {
                    Dialog dialog = new Dialog(view.getContext());
                    dialog.setContentView(R.layout.list_item_edit_dialog);

                    TextView txtv_title = dialog.findViewById(R.id.dig_list_item_edit_txtv_title);
                    EditText edtv_statement = dialog.findViewById(R.id.dig_list_item_edit_edtv_statement);
                    EditText edtv_category = dialog.findViewById(R.id.dig_list_item_edit_edtv_category);

                    Button btn_cancel = dialog.findViewById(R.id.dig_list_item_edit_btn_cancel);
                    Button btn_update = dialog.findViewById(R.id.dig_list_item_edit_btn_update);
                    Button btn_delete = dialog.findViewById(R.id.dig_list_item_edit_btn_delete);

                    txtv_title.setText(statement.getStatement());
                    edtv_statement.setText(statement.getStatement());
                    edtv_category.setText(statement.getCategory());


                    btn_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    btn_update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            StatementsDao dao = new StatementsDao();
                            statement.setStatement(edtv_statement.getText().toString());
                            statement.setCategory(edtv_category.getText().toString());

                            try {
                                dao.update(statement);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            dialog.dismiss();
                            List1Adapter.this.notifyDataSetChanged();

                        }
                    });

                    btn_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                            builder.setMessage("確定要刪除該項目嗎?")
                                    .setPositiveButton("確定刪除", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            StatementsDao dao = new StatementsDao();

                                            try {
                                                dao.delete(statement);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }

                                            dialog.dismiss();
                                            list.remove(statement);
                                            List1Adapter.this.notifyDataSetChanged();
                                        }
                                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialog.dismiss();
                                        }
                                    });
                            AlertDialog dialog1 = builder.create();
                            dialog1.show();

                        }
                    });

                    dialog.show();

                } else {
                    ClipboardManager clipboardManager = (ClipboardManager) view.getContext().
                            getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("label", statement.getStatement());
                    clipboardManager.setPrimaryClip(clipData);


                    Toast.makeText(view.getContext(), statement.getStatement() + " 已複製至剪貼薄", Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void update(String data) {
        this.dcType = data;
    }
}
