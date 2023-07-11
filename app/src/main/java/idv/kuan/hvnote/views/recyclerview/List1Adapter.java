package idv.kuan.hvnote.views.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import idv.kuan.hvnote.R;
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
        Statement data = list.get(position);
        ViewHolder1 vh = (ViewHolder1) holder;
        vh.bindData(data);

        holder.itemView.findViewById(R.id.rv_list_item_txtv_category);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                if (dcType.equals(ManagePhrasesActivite.DC_EDIT)) {
                    Toast.makeText(view.getContext(), "edit type", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(view.getContext(), "copy type", Toast.LENGTH_LONG).show();
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
