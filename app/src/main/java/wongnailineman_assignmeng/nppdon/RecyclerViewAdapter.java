package wongnailineman_assignmeng.nppdon;


import android.app.DownloadManager;
import android.content.Context;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import com.bumptech.glide.request.RequestOptions;
import com.caverock.androidsvg.SVG;
import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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
        final MyViewHolder viewHolder = new MyViewHolder(row);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,CoinActivity.class);
                i.putExtra("coin_name",mCoins.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("coin_des",mCoins.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("coin_imgUrl",mCoins.get(viewHolder.getAdapterPosition()).getImgUrl());

                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textViewTitle.setText(mCoins.get(position).getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.textViewDescription.setText(Html.fromHtml(mCoins.get(position).getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.textViewDescription.setText(Html.fromHtml(mCoins.get(position).getDescription()));

        }
        if(mCoins.get(position).getImgUrl().isEmpty()){
            holder.imageView.setImageResource(R.drawable.loading_shape);
        }else {
            SVGSetter.fetchSvg(mContext,mCoins.get(position).getImgUrl(),holder.imageView);
            /*OkHttpClient httpClient = null;
            if (httpClient == null) {
                // Use cache for performance and basic offline capability
                httpClient = new OkHttpClient.Builder()
                        .cache(new Cache(mContext.getCacheDir(), 5 * 1024 * 1014))
                        .build();
            }
            Request request = new Request.Builder().url(mCoins.get(position).getImgUrl()).build();
            //Request request = new Request.Builder().url(url).build();
            httpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //holder.imageView.setImageDrawable(R.drawable.loading_shape);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    InputStream stream = response.body().byteStream();
                    Sharp.loadInputStream(stream).into(holder.imageView);
                    stream.close();
                }
            });*/
        }
        //}

        //Glide.with(mContext).load("https://www.vitamaker.co.th/wp-content/uploads/2017/10/pic-5.jpg").apply(option).into(holder.imageView);
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
        LinearLayout view_container;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            view_container=itemView.findViewById(R.id.container);
            textViewTitle=itemView.findViewById(R.id.listViewTitle);
            textViewDescription=itemView.findViewById(R.id.listViewSubTitle);
            imageView=itemView.findViewById(R.id.image);

        }
    }

}
