package wongnailineman_assignmeng.nppdon;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<Coin> mCoins;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Coin> mCoins) {
        this.mContext = mContext;
        this.mCoins = mCoins;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        row = layoutInflater.inflate(R.layout.row,parent,false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textViewTitle.setText(mCoins.get(position).getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.textViewDescription.setText(Html.fromHtml(mCoins.get(position).getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.textViewDescription.setText(Html.fromHtml(mCoins.get(position).getDescription()));

            //textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
        }

        //Glide.with(mContext).load(mCoins.get(position).getImgUrl()).apply(option).into(holder.imageView);
        Glide.with(mContext).load("https://www.vitamaker.co.th/wp-content/uploads/2017/10/pic-5.jpg").apply(option).into(holder.imageView);
        /*Glide.with(mContext)
                .as(PictureDrawable.class)
                .apply(option)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());*/

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
            textViewTitle=itemView.findViewById(R.id.listViewTitle);
            textViewDescription=itemView.findViewById(R.id.listViewSubTitle);
            imageView=itemView.findViewById(R.id.image);

        }
    }
}
