package idv.kuan.hvnote.views.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import idv.kuan.hvnote.R;
import idv.kuan.hvnote.database.models.Statement;

public class ViewHolder1 extends RecyclerView.ViewHolder {
    private TextView txtv_statement;
    private TextView txtv_category;

    public ViewHolder1(@NonNull View itemView) {
        super(itemView);
        this.txtv_statement = itemView.findViewById(R.id.rv_list_item_txtv_statement);
        this.txtv_category = itemView.findViewById(R.id.rv_list_item_txtv_category);
    }

    public void bindData(Statement data) {
        txtv_statement.setText(data.getStatement());
        txtv_category.setText(data.getCategory());
    }


}
