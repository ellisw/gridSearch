package com.codepath.gridimagesearch;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	private EditText etQuery;
	private GridView gvResults;
	private ImageResultArrayAdapter resultsAdapter;
	private List<ImageResult> imageResults;
	private String query;
	private int REQUEST_CODE = 1;
	private GoogleSettings settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupViews();

		settings = new GoogleSettings();
		
		imageResults = new ArrayList<ImageResult>();
		resultsAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(resultsAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
				Log.v("TESTING", ""+position);
				Intent i = new Intent(getApplicationContext(), ImageViewActivity.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra("url", imageResult.getUrl());
				startActivity(i);
			}
		});
		gvResults.setOnScrollListener(new EndlessScrollListener() {
			
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				Log.v("TESTING", "page" + page);
				Log.v("TESTING", "totalItemsCount" + totalItemsCount);
				fetchResults(page*8);
			}
		});
	}

	private void setupViews() {
		etQuery = (EditText) findViewById(R.id.et_query);
		gvResults = (GridView) findViewById(R.id.gv_results);
	}

	public void onImageSearch(View v) {
		query = etQuery.getText().toString();
		String toastText;
		if (query.equals("")) {
			toastText = "Please enter a search term!";
		} else {
			toastText = "Searching for \"" + query + "\"...";
		}
		Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
		resultsAdapter.clear();
		fetchResults(0);
		fetchResults(8);
	}
	
	private void fetchResults(int start) {
		AsyncHttpClient client = new AsyncHttpClient();
		String queryString = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
				+ "start=" + start + "&v=1.0&q=" + Uri.encode(query) + settings.toString();
		Log.v("TESTING", "query: "+ queryString);
		client.get(queryString, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONObject response) {
				try {
					JSONArray resultsArray = response.getJSONObject("responseData")
							.getJSONArray("results");
					for (int i = 0; i < resultsArray.length(); i++) {
						resultsAdapter.add(new ImageResult((JSONObject) resultsArray.get(i)));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public void loadSettings(MenuItem m) {
		Log.v("TESTING", "TESTING");
		
		Intent i = new Intent(this, SettingsActivity.class);
		Log.v("TESTING", settings.toString());
		i.putExtra("settings", settings);
		startActivityForResult(i, REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
			if(data.hasExtra("settings")){
				Log.v("TESTING", data.getSerializableExtra("settings").toString());
				settings = (GoogleSettings) data.getSerializableExtra("settings");
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
