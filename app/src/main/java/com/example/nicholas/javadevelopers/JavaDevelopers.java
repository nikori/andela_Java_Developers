package com.example.nicholas.javadevelopers;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kori on 2017-08-28.
 */

public class JavaDevelopers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DevelopersAdapter adapter;
    private List<Developers> developersList;
    private JSONArray id_result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.java_developers);

        initialise();
        getDataDevelopers();
        setRecyclerView();

    }

    public void initialise(){

        try{

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

            developersList = new ArrayList<>();
            adapter = new DevelopersAdapter(getApplicationContext(), developersList);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
        catch(Exception e){



        }
    }



    private void getDataDevelopers(){
        final Progress pr=new Progress();
        pr.progressing(JavaDevelopers.this,"Loading..","Getting Developers..Please Wait");
        StringRequest stringRequest = new StringRequest(Config.DATA_URLDEVELOPERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pr.DissmissProgress();



                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            id_result = j.getJSONArray(Config.JSON_ARRAYDEVELOPERS);

                            getDevelopers(id_result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("main error ****************"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pr.DissmissProgress();
                        Toast.makeText(getApplicationContext(), "error occured "+error, Toast.LENGTH_SHORT).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }




    private void getDevelopers(JSONArray j){

        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);


                String mylogin=json.getString(Config.KEY_DEVELOPERLOGIN);
                String myid=json.getString(Config.KEY_DEVELOPERID);
                String myavatar=json.getString(Config.KEY_DEVELOPERAVATARURL);
                String myurl=json.getString(Config.KEY_DEVELOPERHTMLURL);
                String mytype=json.getString(Config.KEY_DEVELOPERTYPE);
                String mysiteadmin=json.getString(Config.KEY_DEVELOPERSITEADMIN);
                String myscore=json.getString(Config.KEY_DEVELOPERSCORE);

                Developers d=new Developers(mylogin,myid,myavatar,myurl,mytype,mysiteadmin,myscore);
                developersList.add(d);


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "an error occured "+ e, Toast.LENGTH_SHORT).show();
            }
        }
        adapter.notifyDataSetChanged();

    }



    public void setRecyclerView(){

        try{




            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {

                    Developers dev=developersList.get(position);
                    System.out.println("********"+dev.getLogin()+" "+dev.getId()+" "+dev.getScore());

                    Intent myint=new Intent(getApplicationContext(),ClickedDeveloper.class);
                    myint.putExtra("mylogin",dev.getLogin());
                    myint.putExtra("myid",dev.getId());
                    myint.putExtra("myavatar",dev.getAvatar_url());
                    myint.putExtra("myurl",dev.getHtml_url());
                    myint.putExtra("mytype",dev.getType());
                    myint.putExtra("mysiteadmin",dev.getSite_admin());
                    myint.putExtra("myscore",dev.getScore());


                    startActivity(myint);




                }

                @Override
                public void onLongClick(View view, int position) {

                    Toast.makeText(JavaDevelopers.this, "long click on item", Toast.LENGTH_SHORT).show();


                }
            }));



        }
        catch(Exception e){



        }
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
