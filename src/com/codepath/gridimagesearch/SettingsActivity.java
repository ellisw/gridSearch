package com.codepath.gridimagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends Activity {

	Spinner sType;
	Spinner sSize;
	Spinner sColor;
	
	EditText etSiteFilter;
	
	GoogleSettings settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		settings = (GoogleSettings) getIntent().getSerializableExtra("settings");
		
		etSiteFilter = (EditText) findViewById(R.id.et_site_filter);
		
		sType = (Spinner) findViewById(R.id.s_image_type);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.type, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sType.setAdapter(adapter);
		if(settings.getType() != null){
			sType.setSelection(adapter.getPosition(settings.getType()));
		}
		sType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				Log.v("TESTING", (String) parent.getItemAtPosition(pos));
				settings.setType((String) parent.getItemAtPosition(pos));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				settings.setType(null);
			}
		});
		
		sSize = (Spinner) findViewById(R.id.s_image_size);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
		        R.array.size, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sSize.setAdapter(adapter2);
		if(settings.getSize() != null){
			sType.setSelection(adapter2.getPosition(settings.getSize()));
		}
		sSize.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				Log.v("TESTING", (String) parent.getItemAtPosition(pos));
				settings.setSize((String) parent.getItemAtPosition(pos));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				settings.setSize(null);
			}
		});
		
		sColor = (Spinner) findViewById(R.id.s_color);
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
		        R.array.colors, android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sColor.setAdapter(adapter3);
		if(settings.getColor() != null){
			sColor.setSelection(adapter3.getPosition(settings.getColor()));
		}
		sColor.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				Log.v("TESTING", (String) parent.getItemAtPosition(pos));
				settings.setColor((String) parent.getItemAtPosition(pos));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				settings.setColor(null);
			}
		});
		
		if(settings.getSiteFilter() != null){
			etSiteFilter.setText(settings.getSiteFilter());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void inputDone(View v){
		settings.setSiteFilter(etSiteFilter.getText().toString());
		Intent i = new Intent();
		i.putExtra("settings", settings);
		setResult(RESULT_OK, i);
		finish();
	}

}
