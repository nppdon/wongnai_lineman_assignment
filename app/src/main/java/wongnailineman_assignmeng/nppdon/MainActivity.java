package wongnailineman_assignmeng.nppdon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String APIUrl = "https://api.coinranking.com/v1/public/coins";

    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    private List<Coin> coins;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coins = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        APIRequest();

    }

    public void APIRequest(){
        jsonArrayRequest = new JsonArrayRequest(APIUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for(int i = 0 ; i< response.length();i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        Coin coin = new Coin();
                        coin.setName(jsonObject.getString("name"));
                        coin.setDescription(jsonObject.getString("description"));
                        coin.setImgUrl(jsonObject.getString("iconUrl"));
                        coins.add(coin);

                    }
                    catch (JSONException e){
                        e.printStackTrace();

                    }
                }
                setupRecyclerView(coins);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    private void setupRecyclerView(List<Coin> coins){

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, coins);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);
    }

}