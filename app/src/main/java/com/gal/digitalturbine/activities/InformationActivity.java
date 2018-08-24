package com.gal.digitalturbine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gal.digitalturbine.ImageCache;
import com.gal.digitalturbine.R;

public class InformationActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		Intent intent = getIntent();

		String name = intent.getStringExtra(MainActivity.Keys.NAME);
		String thumbnail = intent.getStringExtra(MainActivity.Keys.THUMBNAIL);
		String desc = intent.getStringExtra(MainActivity.Keys.DESC);
		String rateImg = intent.getStringExtra(MainActivity.Keys.RATE_IMG);
		String rate = intent.getStringExtra(MainActivity.Keys.RATE);
		String count = intent.getStringExtra(MainActivity.Keys.RATE_COUNT);
		String category = intent.getStringExtra(MainActivity.Keys.CATEGORY);
		String bidRate = intent.getStringExtra(MainActivity.Keys.BID_RATE);
		String minOsVersion = intent.getStringExtra(MainActivity.Keys.MIN_OS_VERSION);
		String action = intent.getStringExtra(MainActivity.Keys.ACTION);
		boolean homeScreen = intent.getBooleanExtra(MainActivity.Keys.HOME_SCREEN, false);

		((TextView) findViewById(R.id.nameTxt)).setText(name);
		((TextView) findViewById(R.id.descTxt)).setText(desc);
		((TextView) findViewById(R.id.rateTxt)).setText(rate);
		((TextView) findViewById(R.id.countTxt)).setText(count);
		((TextView) findViewById(R.id.categoryTxt)).setText(category);
		((TextView) findViewById(R.id.bidRateTxt)).setText(bidRate);
		((TextView) findViewById(R.id.minOsTxt)).setText(minOsVersion);
		((TextView) findViewById(R.id.actionBtn)).setText(action);
		findViewById(R.id.isHomeScreen).setVisibility(homeScreen ? View.VISIBLE : View.INVISIBLE);

		((ImageView) findViewById(R.id.thumbnail)).setImageBitmap(ImageCache.createBitmap(thumbnail));
		((ImageView) findViewById(R.id.rateImg)).setImageBitmap(ImageCache.createBitmap(rateImg));
	}
}
