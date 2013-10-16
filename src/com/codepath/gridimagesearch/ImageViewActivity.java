package com.codepath.gridimagesearch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.loopj.android.image.SmartImageView;

public class ImageViewActivity extends Activity {

	SmartImageView ivResult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_view);
		String url = getIntent().getStringExtra("url");
		ivResult = (SmartImageView) findViewById(R.id.iv_result);
		ivResult.setImageUrl(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.image_view, menu);
		return true;
	}

}
