package com.gal.digitalturbine.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.gal.digitalturbine.Product;
import com.gal.digitalturbine.R;
import com.gal.digitalturbine.adapters.ProductAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends Activity {

	public static final class Keys {
		public static final String
				NAME = "name",
				CATEGORY = "category",
				DESC = "desc",
				THUMBNAIL = "thumbnail",
				RATE = "rate",
				RATE_IMG = "rateImg",
				RATE_COUNT = "count",
				BID_RATE = "bidRate",
				MIN_OS_VERSION = "minOsVersion",
				ACTION = "action",
				HOME_SCREEN = "homeScreen";
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView rv = findViewById(R.id.productList);
		fetchProducts(rv);
	}

	@SuppressLint("StaticFieldLeak")
	private void fetchProducts(final RecyclerView rv) {
		new AsyncTask<Void, Void, Product[]>() {

			@Override
			protected Product[] doInBackground(Void... voids) {
				String url = "http://ads.appia.com/getAds?id=236&password=OVUJ1DJN&siteId=10777&deviceId=4230&sessionId=techtestsession&totalCampaignsRequested=10&lname=yedidovich";
				try {
					Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
					NodeList users = document.getElementsByTagName("ad");
					Product[] products = new Product[users.getLength()];
					for (int i = 0; i < users.getLength(); i++) {
						Element product = (Element) users.item(i);

						String name = getTextFromNode(product, "productName"),
								thumbnail = getTextFromNode(product, "productThumbnail"),
								rateImg = getTextFromNode(product, "averageRatingImageURL"),
								desc = getTextFromNode(product, "productDescription"),
								category = getTextFromNode(product, "categoryName"),
								numOfRates = getTextFromNode(product, "numberOfRatings"),
								rate = getTextFromNode(product, "rating"),
								bidRate = getTextFromNode(product, "bidRate"),
								callToAction = getTextFromNode(product, "callToAction"),
								minOSVersion = getTextFromNode(product, "minOSVersion");

						boolean isHomeScreen = false;
						if (product.getElementsByTagName("homeScreen") != null)
							isHomeScreen = product.getElementsByTagName("homeScreen").item(0).getTextContent().equals("true");

						//build items
						products[i] = new Product.Builder()
								.setName(name)
								.setThumbnail(thumbnail)
								.setRateImg(rateImg)
								.setDesc(desc)
								.setCategory(category)
								.setNumOfRates(numOfRates)
								.setRate(rate)
								.setBidRate(bidRate)
								.setCallToAction(callToAction)
								.setMinOSVersion(minOSVersion)
								.setHomeScreen(isHomeScreen)
								.build();
					}
					return products;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Product[] products) {
				rv.setAdapter(new ProductAdapter(MainActivity.this, products, listener));

			}
		}.execute();
	}

	private String getTextFromNode(Element e, String nodeName) {
		NodeList nList = e.getElementsByTagName(nodeName);
		if (nList != null && nList.getLength() > 0) return nList.item(0).getTextContent();
		else return null;
	}

	//click listener from each item in recycler view
	private ProductAdapter.OnItemClicked listener = p -> {
		Intent i = new Intent(MainActivity.this, InformationActivity.class);
		i.putExtra(Keys.NAME, p.getName());
		i.putExtra(Keys.THUMBNAIL, p.getThumbnail());
		i.putExtra(Keys.CATEGORY, p.getCategory());
		i.putExtra(Keys.DESC, p.getDesc());
		i.putExtra(Keys.RATE, p.getRate());
		i.putExtra(Keys.RATE_IMG, p.getRateImg());
		i.putExtra(Keys.RATE_COUNT, p.getRateCount());
		i.putExtra(Keys.BID_RATE, p.getBidRate());
		i.putExtra(Keys.MIN_OS_VERSION, p.getMinOSVersion());
		i.putExtra(Keys.ACTION, p.getCallToAction());
		i.putExtra(Keys.HOME_SCREEN, p.isHomeScreen());

		startActivity(i);
	};
}

