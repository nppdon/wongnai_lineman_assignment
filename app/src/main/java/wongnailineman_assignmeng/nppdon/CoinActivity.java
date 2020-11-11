package wongnailineman_assignmeng.nppdon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class CoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        getSupportActionBar().hide();

        String name = getIntent().getExtras().getString("coin_name");
        String description = getIntent().getExtras().getString("coin_des");
        String imgUrl = getIntent().getExtras().getString("coin_imgUrl");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView coin_name = findViewById(R.id.coinTitle);
        TextView coin_subtitile = findViewById(R.id.CoinSubTitle);
        TextView coin_description = findViewById(R.id.coin_description);
        ImageView coin_imageView = findViewById(R.id.coin_image);

        collapsingToolbarLayout.setTitle(name);
        coin_name.setText(name);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            coin_subtitile.setText(Html.fromHtml(description,Html.FROM_HTML_MODE_COMPACT));
            coin_description.setText(Html.fromHtml(description,Html.FROM_HTML_MODE_COMPACT));
            //holder.textViewDescription.setText(Html.fromHtml(mCoins.get(position).getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            coin_subtitile.setText(Html.fromHtml(description));
            coin_description.setText(Html.fromHtml(description));
            //holder.textViewDescription.setText(Html.fromHtml(mCoins.get(position).getDescription()));

        }

        SVGSetter.fetchSvg(this,imgUrl,coin_imageView);
    }
}