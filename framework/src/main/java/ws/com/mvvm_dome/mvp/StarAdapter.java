package ws.com.mvvm_dome.mvp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ws.com.mvvm_dome.R;

public class StarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static List<Star> mStar;
    private static StarAdapter instance;

    public StarAdapter() {
    }

    public static StarAdapter getInstance() {
        if (instance == null) {
            synchronized (StarAdapter.class) {
                if (instance == null) {
                    instance = new StarAdapter();
                }
            }
        }
        return instance;
    }

    public void setStar(List<Star> star) {
        mStar = star;
        notifyDataSetChanged();
    }

    public List<Star> getStar() {
        return mStar;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_mvp, null);
        InnerHolder innerHolder = new InnerHolder(view);
        return innerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((InnerHolder)holder).setData(mStar.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mStar.size();
    }

    class InnerHolder extends RecyclerView.ViewHolder {

        TextView name;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
        }

        public void  setData(Star star, int position) {
            name.setText(star.getName());
        }
    }
}
