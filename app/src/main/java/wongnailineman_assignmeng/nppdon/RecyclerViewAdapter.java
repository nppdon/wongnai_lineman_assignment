package wongnailineman_assignmeng.nppdon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<Coin> mCoins;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Coin> mCoins) {
        this.mContext = mContext;
        this.mCoins = mCoins;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        row = layoutInflater.inflate(R.layout.row,parent,false);


        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewTitle.setText(mCoins.get(position).getName());
        holder.textViewDescription.setText(mCoins.get(position).getDescription());

        Glide.with(mContext).load(mCoins.get(position).getImgUrl()).apply(option).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mCoins.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        TextView textViewDescription;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            textViewTitle.findViewById(R.id.listViewTitle);
            textViewDescription.findViewById(R.id.listViewSubTitle);
            imageView.findViewById(R.id.image);

        }
    }
}
