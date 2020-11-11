package wongnailineman_assignmeng.nppdon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String APIUrl = "https://api.coinranking.com/v1/public/coins";

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    private List<Coin> coins;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coins = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar_main);
        APIRequestObject();

    }

    public void APIRequestObject() {
        jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, APIUrl, null,
                response -> {
                    JSONObject jsonObject = null;

                    int le = response.length();
                    String len = le + " ";
                    Log.i("json_length", len);
                    Log.i("API", response.toString());
                    progressBar.setVisibility(View.GONE);
                    try {
                        JSONObject jsonData = response.getJSONObject("data");
                        JSONArray jsonArray = jsonData.getJSONArray("coins");


                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);

                            try {
                                Log.i("APIResponse" + i, jsonObject.toString());

                                Coin coin = new Coin();

                                if (jsonObject.getString("name").isEmpty()) {
                                    continue;
                                } else {
                                    coin.setName(jsonObject.getString("name"));
                                    coin.setDescription(jsonObject.getString("description"));
                                    coin.setImgUrl(jsonObject.getString("iconUrl"));
                                    coin.setIconType(jsonObject.getString("iconType"));
                                    coins.add(coin);
                                }

                                Log.i("RESPONSE" + i, jsonObject.getString("name") + " " + jsonObject.getString("iconUrl"));

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }

                    } catch (JSONException e) {
                        Log.e("API", e.toString());
                    }


                    setupRecyclerView(coins);

                }, error -> {
            Log.e("API", "Fetch error");
            Log.e("API", error.toString());
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    private void setupRecyclerView(List<Coin> coins) {

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, coins);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);
    }

}