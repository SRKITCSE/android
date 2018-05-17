package com.example.srkit.info;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentDetail extends AppCompatActivity {
    ImageView iv;
    TextView tv;
    TextView tv9;
    TextView tv2;
    TextView tv10;
    TextView tv3;
    TextView tv11;
    TextView tv4;
    TextView tv12;
    TextView tv5;
    TextView tv13;
    TextView tv6;
    TextView tv14;
    TextView tv7;
    TextView tv15;
    TextView tv8;
    WebView webView;
    int imageResource,webView2;
    static String str;
    String uri,myPath;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_detail);
        webView = (WebView)findViewById(R.id.mapView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(myPath);

        //UI Components
        iv=(ImageView) findViewById(R.id.imageView2);
        tv=(TextView) findViewById(R.id.office);
        tv.setTextSize(15);

        tv9=(TextView) findViewById(R.id.descriptionhead);
        tv2=(TextView)findViewById(R.id.description);
        tv2.setTextSize(12);

        tv10=(TextView) findViewById(R.id.namehead);
        tv3=(TextView)findViewById(R.id.name);
        tv3.setTextSize(15);

        tv11=(TextView) findViewById(R.id.desighead);
        tv4=(TextView)findViewById(R.id.designation);
        tv4.setTextSize(15);

        tv12=(TextView) findViewById(R.id.addresshead);
        tv5=(TextView)findViewById(R.id.address);
        tv5.setTextSize(15);

        tv13=(TextView) findViewById(R.id.landhead);
        tv6=(TextView)findViewById(R.id.landmark);
        tv6.setTextSize(15);

        tv14=(TextView) findViewById(R.id.bushead);
        tv7=(TextView)findViewById(R.id.busnumbers);
        tv7.setTextSize(15);

        tv15=(TextView) findViewById(R.id.urlhead);
        tv8=(TextView)findViewById(R.id.url);
        tv8.setTextSize(15);

        //Functions
        Intent i=getIntent();
        str=(data.items[+ data.pos]);


        //Setting image

        switch (str) {
            case "CE, Central Designing Organisation":
                uri = "@drawable/" + "ce";
                myPath ="https://www.google.co.in/maps/place/Central+Designs+Organisation,+Water+Resources+Department/@16.5396617,80.5931631,17z/data=!3m1!4b1!4m5!3m4!1s0x3a35efb6c9a52ce5:0xe6be8035e1c6dbc6!8m2!3d16.5396617!4d80.5953518";
                break;
            case "Directorates Of Factories":
                uri = "@drawable/" + "directorateoffactories";
                myPath="https://www.google.com/maps/place/16%C2%B032'10.9%22N+80%C2%B035'04.3%22E/@16.536349,80.5839848,19z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.536349!4d80.584532";
                break;
            case "Bc Commission":
                uri = "@drawable/" + "bc";
                myPath="https://www.google.com/maps/place/16%C2%B030'07.0%22N+80%C2%B039'03.3%22E/@16.501958,80.6487363,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.501958!4d80.650925";
                break;
            case "Commissioner of police":
                uri = "@drawable/" + "compolice";
                myPath="https://www.google.com/maps/place/Office+Of+The+Commissioner+Of+Police/@16.5072397,80.6347802,17z/data=!4m8!1m2!2m1!1spolice+commissioner++vijayawada!3m4!1s0x3a35faaa3fea6835:0x5eef9e05b3eab52!8m2!3d16.508286!4d80.6341516";
                break;
            case "DGP OFFICE":
                uri = "@drawable/" + "dgp";
                myPath="https://www.google.com/maps/place/16%C2%B030'33.2%22N+80%C2%B037'58.1%22E/@16.5089852,80.6305748,17z/data=!4m5!3m4!1s0x0:0x0!8m2!3d16.509222!4d80.632812";
                break;
            case "Panchayati Raj, Rural Development":
                uri = "@drawable/" + "panchayatraj";
                myPath="https://www.google.com/maps/place/16%C2%B030'43.7%22N+80%C2%B037'55.3%22E/@16.512138,80.6298483,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.512138!4d80.632037";
                break;
            case "Engineer In Chief, Panchayati Raj":
                uri = "@drawable/" + "engineerinchiefpanchayatraj";
                myPath="https://www.google.com/maps/place/16%C2%B030'21.5%22N+80%C2%B037'47.2%22E/@16.505971,80.6275873,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.505971!4d80.629776";
                break;
            case "RTC Department":
                uri = "@drawable/" + "rtc";
                myPath="https://www.google.com/maps/place/APSRTC/@16.5087507,80.616861,17z/data=!4m8!1m2!2m1!1sAPSRTC+vijayawada!3m4!1s0x0:0x7da10a7d96aac58c!8m2!3d16.5094509!4d80.6148078";
                break;
            case "Transport Department":
                uri = "@drawable/" + "transport";
                myPath="https://www.google.com/maps/place/APSRTC/@16.5087507,80.616861,17z/data=!4m5!3m4!1s0x0:0x7da10a7d96aac58c!8m2!3d16.5094509!4d80.6148078";
                break;
            case "ACB Department":
                uri = "@drawable/" + "acb";
                myPath="https://www.google.com/maps/place/16%C2%B030'31.5%22N+80%C2%B036'56.7%22E/@16.50874,80.6135633,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.50874!4d80.615752";
                break;
            case "Department of Women Development and child Welfare":
                uri = "@drawable/" + "women";
                myPath="https://www.google.com/maps/place/16%C2%B030'30.9%22N+80%C2%B036'57.6%22E/@16.50858,80.6138153,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.50858!4d80.616004";
                break;
            case "State Disaster Response And Fire Department":
                uri = "@drawable/" + "fire";
                myPath="https://www.google.com/maps/place/AP+State+Disater+Response+%26+Fire+Services+Department/@16.5108338,80.6158696,17z/data=!3m1!4b1!4m5!3m4!1s0x3a35f01d9769f0a5:0xb628b35c09fa9dfb!8m2!3d16.5108338!4d80.6180583";
                break;
            case "APIIC Deparment":
                uri = "@drawable/" + "apiic";
                myPath="https://www.google.com/maps/place/APIIC+Head+Office/@16.5035277,80.663274,17z/data=!3m1!4b1!4m5!3m4!1s0x3a35facfe5cd36ab:0xa8625c33058f1a6!8m2!3d16.5035277!4d80.6654627";
                break;
            case "Irrigation Department":
                uri = "drawable/" + "irrigation";
                myPath="https://www.google.com/maps/place/16%C2%B030'30.1%22N+80%C2%B037'55.0%22E/@16.5083715,80.6297625,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.5083715!4d80.6319512";
                break;
            case "Amaravathi Metro Rail Corporation":
                uri = "drawable/" + "metrorail";
                myPath="https://www.google.com/maps/place/DMRC+Office+-+Amaravati+Metro+Rail+Corporation/@16.5014401,80.64704,18z/data=!4m5!3m4!1s0x3a35fab09bd4d2f7:0x5e3923d04a7dbe4e!8m2!3d16.5022322!4d80.6475925";
                break;
            case "IT Department":
                uri = "@drawable/" + "itdept";
                myPath="https://www.google.com/maps/place/16%C2%B031'12.2%22N+80%C2%B041'28.3%22E/@16.520053,80.6906518,19z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.520053!4d80.691199";
                break;
            case "Fisheries Department":
                uri = "@drawable/" + "fish";
                myPath="https://www.google.com/maps/place/AP+Fisheries/@16.4693579,80.7216663,16z/data=!4m5!3m4!1s0x3a35fb7125b5fbd1:0x1b59b6b192c494f8!8m2!3d16.4696154!4d80.725687";
                break;
            case "Rasthra Pranalika Sangham":
                uri = "@drawable/" + "rastrapranalika";
                myPath="https://www.google.com/maps/place/16%C2%B029'45.6%22N+80%C2%B039'13.7%22E/@16.49599,80.6516033,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.49599!4d80.653792";
                break;
            case "Directorate of Prosecution":
                uri = "@drawable/" + "directorateofprosecution";
                myPath="https://www.google.com/maps/place/16%C2%B030'29.6%22N+80%C2%B037'43.2%22E/@16.508225,80.6264673,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.508225!4d80.628656";
                break;
            case "Prohibition and Excise":
                uri = "@drawable/" + "exice";
                myPath="https://www.google.com/maps/place/16%C2%B030'56.4%22N+80%C2%B041'29.1%22E/@16.515668,80.6892343,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.515668!4d80.691423";
                break;
            case "MD, State Beverages Corporation":
                uri = "@drawable/" + "bevarage";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'56.8%22N+80%C2%B041'28.6%22E/@16.515771,80.6890803,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.515771!4d80.691269";
                break;
            case "Enforcement Directorate":
                uri = "@drawable/" + "enforcement";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'55.6%22N+80%C2%B041'31.3%22E/@16.515437,80.6898413,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.515437!4d80.69203";
                break;
            case "Tribal Welfare Department":
                uri = "@drawable/" + "tribal";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'50.3%22N+80%C2%B031'01.8%22E/@16.513967,80.5149703,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.513967!4d80.517159";
                break;
            case "Technical Education Department":
                uri = "@drawable/" + "technicaleducation";
                myPath="https://www.google.co.in/maps/place/Commissionarate+Of+Technical+Education,+Prasadampadu,+Vijayawada./@16.5165942,80.6852373,17z/data=!3m1!4b1!4m5!3m4!1s0x3a35e4d297e28f3f:0x4e72bf6312fc41a2!8m2!3d16.5165942!4d80.687426";
                break;
            case "Counter Intelligence":
                uri = "@drawable/" + "aplogo";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'30.4%22N+80%C2%B039'16.8%22E/@16.5079761,80.6542687,19z/data=!4m5!3m4!1s0x0:0x0!8m2!3d16.508439!4d80.654663";
                break;
            case "Sports Department":
                uri = "@drawable/" + "sports";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'15.6%22N+80%C2%B038'15.8%22E/@16.504322,80.6355483,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.504322!4d80.637737";
                break;
            case "Commercial Taxes Department":
                uri = "@drawable/" + "commercialtaxdept";
                myPath="https://www.google.co.in/maps/place/Commercial+Tax+Office/@16.4888472,80.6697643,17z/data=!3m1!4b1!4m5!3m4!1s0x3a35fae0d1eb014f:0xcbbfb14ae6c14d62!8m2!3d16.4888472!4d80.671953";
                break;
            case "Director of Medical Education":
                uri = "@drawable/" + "directorateofmedicaleducation";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'49.4%22N+80%C2%B037'09.7%22E/@16.513716,80.6171673,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.513716!4d80.619356";
                break;
            case "AP Para Medical":
                uri = "@drawable/" + "paramedical";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'48.9%22N+80%C2%B037'07.5%22E/@16.513589,80.6165563,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.513589!4d80.618745";
                break;
            case "Department Structure of Youth Advancement":
                uri = "@drawable/" +"youth";
                myPath="https://www.google.co.in/maps/place/16%C2%B031'00.7%22N+80%C2%B041'15.7%22E/@16.51686,80.6854973,17z/data=!4m12!1m6!4m5!1m0!1m3!2m2!1d80.687686!2d16.51686!3m4!1s0x0:0x0!8m2!3d16.51686!4d80.687686";
                break;
            case "Coconut Development Board":
                uri = "@drawable/" + "coconut";
                myPath="https://www.google.co.in/maps/place/16%C2%B031'14.9%22N+80%C2%B040'57.8%22E/@16.5208,80.6805223,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.5208!4d80.682711";
                break;
            case "Kapu Corporation":
                uri = "@drawable/" + "kapu";
                myPath="https://www.google.co.in/maps/place/Kapu+Corporation/@16.5149183,80.635108,17z/data=!3m1!4b1!4m5!3m4!1s0x3a35e55491a236bb:0xacdaaa6e14e31d8e!8m2!3d16.5149183!4d80.6372967";
                break;
            case "Warehousing Corporation":
                uri = "@drawable/" + "warehouse";
                myPath="https://www.google.co.in/maps/place/AP+State+Warehousing+Corporation/@16.5103242,80.6312429,17z/data=!3m1!4b1!4m5!3m4!1s0x3a35faaa8f774547:0xf464963a0016228c!8m2!3d16.5103242!4d80.6334316";
                break;
            case "Customs Department":
                uri = "@drawable/" + "customs";
                myPath="https://www.google.co.in/maps/place/16%C2%B029'33.6%22N+80%C2%B040'21.7%22E/@16.492662,80.6704993,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.492662!4d80.672688";
                break;
            case "Income Tax Department":
                uri = "@drawable/" + "incometax";
                myPath="https://www.google.co.in/maps/place/16%C2%B029'33.0%22N+80%C2%B040'21.2%22E/@16.492508,80.6703713,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.492508!4d80.67256";
                break;
            case "Agriculture Departments":
                uri = "@drawable/" + "agriculturedept";
                myPath="https://www.google.co.in/maps/place/Agricultural+Market+Committee/@16.2902589,80.4097214,17z/data=!4m12!1m6!3m5!1s0x3a4a750af757fcbf:0x3628ad8f70958704!2sAgricultural+Market+Committee!8m2!3d16.2902589!4d80.4119101!3m4!1s0x3a4a750af757fcbf:0x3628ad8f70958704!8m2!3d16.2902589!4d80.4119101";
                break;
            case "Marketing Department":
                uri = "@drawable/" + "market";
                myPath="https://www.google.co.in/maps/place/16%C2%B017'24.5%22N+80%C2%B024'43.0%22E/@16.290148,80.4097663,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.290148!4d80.411955";
                break;
            case "Commissioner of Collegiate":
                uri = "@drawable/" + "collegiate";
                myPath="https://www.google.co.in/maps/place/16%C2%B031'00.2%22N+80%C2%B041'15.4%22E/@16.516714,80.6854263,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.516714!4d80.687615";
                break;
            case "Minor Irrigation Department":
                uri = "@drawable/" + "minor";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'32.4%22N+80%C2%B037'58.9%22E/@16.508988,80.6308503,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.508988!4d80.633039";
                break;
            case "Municipal Administration":
                uri = "@drawable/" + "municipaladministration";
                myPath="https://www.google.co.in/maps/place/AP+Municipal+Administration+%26+Public+Health+Engineering+Dept.+%26+Directorate+of+Town+%26+Country+Planning,+H.O/@16.3368899,80.4317695,17z/data=!3m1!4b1!4m5!3m4!1s0x3a358abe117c53c1:0x35f5724a3c4f28c1!8m2!3d16.3368899!4d80.4339582";
                break;
            case "Forest Department":
                uri = "@drawable/" + "forest";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'50.2%22N+80%C2%B031'03.2%22E/@16.513949,80.5153753,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.513949!4d80.517564";
                break;
            case "AP NEDCAP":
                uri = "@drawable/" + "apnedcap";
                myPath="https://www.google.co.in/maps/place/16%C2%B030'50.5%22N+80%C2%B031'04.5%22E/@16.514034,80.5157333,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d16.514034!4d80.517922";
                break;

        }

        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        webView2 = getResources().getIdentifier(myPath,null,getPackageName());
        Drawable res= getResources().getDrawable(imageResource);
        webView.loadUrl(myPath);

        iv.setImageDrawable(res);

        //Setting text

        switch (str)
        {
            case "CE, Central Designing Organisation":
                getData("CE");
                break;
            case "Directorates Of Factories":
                getData("DF");
                break;
            case "Bc Commission":
                getData("BC");
                break;
            case  "Commissioner of police":
                getData("CP");
                break;
            case "DGP OFFICE":
                getData("DGP");
                break;
            case "Panchayati Raj, Rural Development":
                getData("PR");
                break;
            case "Engineer In Chief, Panchayati Raj":
                getData("EP");
                break;
            case "RTC Department":
                getData("RTC");
                break;
            case "Transport Department":
                getData("TD");
                break;
            case "ACB Department":
                getData("ACB");
                break;
            case "Department of Women Development and child Welfare":
                getData("DC");
                break;
            case "State Disaster Response And Fire Department":
                getData("SF");
                break;
            case "APIIC Deparment":
                getData("APIIC");
                break;
            case "Irrigation Department":
                getData("ID");
                break;
            case "Amaravathi Metro Rail Corporation":
                getData("AMR");
                break;
            case "IT Department":
                getData("ITD");
                break;
            case "Fisheries Department":
                getData("FD");
                break;
            case "Rasthra Pranalika Sangham":
                getData("RPS");
                break;
            case "Directorate of Prosecution":
                getData("DP");
                break;
            case "Prohibition and Excise":
                getData("PE");
                break;
            case "MD, State Beverages Corporation":
                getData("SBC");
                break;
            case "Enforcement Directorate":
                getData("ED");
                break;
            case "Tribal Welfare Department":
                getData("TWD");
                break;
            case "Technical Education Department":
                getData("TED");
                break;
            case "Counter Intelligence":
                getData("CI");
                break;
            case "Sports Department":
                getData("SD");
                break;
            case "Commercial Taxes Department":
                getData("CTD");
                break;
            case "Director of Medical Education":
                getData("DME");
                break;
            case "AP Para Medical":
                getData("APMB");
                break;
            case "Department Structure of Youth Advancement":
                getData("DSYA");
                break;
            case "Coconut Development Board":
                getData("CDB");
                break;
            case "Kapu Corporation":
                getData("KC");
                break;
            case "Warehousing Corporation":
                getData("WC");
                break;
            case "Customs Department":
                getData("CD");
                break;
            case "Income Tax Department":
                getData("ICTD");
                break;
            case "Agriculture Departments":
                getData("AD");
                break;
            case "Marketing Department":
                getData("MD");
                break;
            case "Commissioner of Collegiate":
                getData("COC");
                break;
            case "Minor Irrigation Department":
                getData("MID");
                break;
            case "Municipal Administration":
                getData("MA");
                break;
            case "Forest Department":
                getData("FSD");
                break;
            case "AP NEDCAP":
                getData("APN");
                break;
        }


    }

    private void getData(String stt) {


        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL+stt;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FragmentDetail.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
      //  String id="";
        String office = "";
        String description = "";
        String name="";
        String designation="";
        String address = "";
        String landmark = "";
        String busnumber = "";
        String url = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
          //  id = collegeData.getString(Config.KEY_ID);
            office = collegeData.getString(Config.KEY_OFFICE);
            description=collegeData.getString(Config.KEY_DESCRIPTION);
            name = collegeData.getString(Config.KEY_NAME);
            designation = collegeData.getString(Config.KEY_DESIGNATION);
            address = collegeData.getString(Config.KEY_ADDRESS);
            landmark=collegeData.getString(Config.KEY_LANDMARK);
            busnumber=collegeData.getString(Config.KEY_BUSNUMBERS);
            url=collegeData.getString(Config.KEY_URL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv.setText(office);
        tv9.setText("DESCRIPTION");
        tv2.setText(description);
        tv2.setTextColor(Color.parseColor("#720B4A"));
        tv10.setText("NAME");
        tv3.setText(name);
        tv3.setTextColor(Color.parseColor("#0B4A72"));
        tv11.setText("DESIGNATION");
        tv4.setText(designation);
        tv4.setTextColor(Color.parseColor("#720B4A"));
        tv12.setText("ADDRESS");
        tv5.setText(address);
        tv5.setTextColor(Color.parseColor("#0B4A72"));
        tv13.setText("LANDMARK");
        tv6.setText(landmark);
        tv6.setTextColor(Color.parseColor("#720B4A"));
        tv14.setText("BUSNUMBERS");
        tv7.setText(busnumber);
        tv7.setTextColor(Color.parseColor("#E60000"));
        tv15.setText("URL");
        tv8.setText(url);
        Linkify.addLinks(tv8,Linkify.WEB_URLS);
        tv8.setTextColor(Color.parseColor("#199B9E"));
    }

}




















