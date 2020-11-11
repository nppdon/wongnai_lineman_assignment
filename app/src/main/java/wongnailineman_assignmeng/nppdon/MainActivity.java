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
    JsonObjectRequest jsonObjectRequest;
    private List<Coin> coins;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coins = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        APIRequestObject();

    }

    /*public void APIRequestArray(){
        jsonArrayRequest = new JsonArrayRequest(APIUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                int le = response.length();
                String len = le+" ";
                Log.i("jsonlength",len);


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

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e("API","Fetch error");
                    Log.e("API",error.toString());
                }
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }*/

    public void APIRequestObject() {
        jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, APIUrl, null,
                response -> {
                    JSONObject jsonObject = null;

                    int le = response.length();
                    String len = le + " ";
                    Log.i("json_length", len);
                    Log.i("API", response.toString());
                    try {
                        JSONObject jsonData = response.getJSONObject("data");
                        JSONArray jsonArray = jsonData.getJSONArray("coins");


                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);

                            try {
                                Log.i("APIResponse" + i, jsonObject.toString());

                                Coin coin = new Coin();


                                //bug fix here about null descript

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
                                //coins.add(coin);

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