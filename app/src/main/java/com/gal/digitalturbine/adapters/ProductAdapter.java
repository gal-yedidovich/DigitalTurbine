package com.gal.digitalturbine.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gal.digitalturbine.ImageCache;
import com.gal.digitalturbine.Product;
import com.gal.digitalturbine.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
	private Product[] products;
	private Context context;
	private OnItemClicked listener;

	public ProductAdapter(Context context, Product[] products, OnItemClicked listener) {
		this.context = context;
		this.products = products;
		this.listener = listener;
	}

	@NonNull
	@Override
	public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false); //inflate xml file
		return new ProductViewHolder(v); //create and return view holder
	}

	@Override
	public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
		final Product current = products[position];
		holder.nameTxt.setText(current.getName());
		holder.countTxt.setText(current.getRateCount());
		holder.descTxt.setText(current.getDesc());
		holder.rateTxt.setText(current.getRate());
		holder.homeScreenTxt.setVisibility(current.isHomeScreen() ? View.VISIBLE : View.INVISIBLE);

		fetchImg(current.getRateImg(), holder.rateImg);
		fetchImg(current.getThumbnail(), holder.thumbnail);

		//set on click listener
		holder.itemView.setOnClickListener(v -> {
			if (listener != null) {
				if (holder.thumbnail.getDrawable() != null && holder.rateImg.getDrawable() != null) listener.onClick(current);
				else Toast.makeText(context, "Still Loading..", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * request the image from cache, and present it in UI
	 *
	 * @param url  - the image to display
	 * @param imgV - an imageView to present the bitmap image
	 */
	@SuppressLint("StaticFieldLeak")
	private void fetchImg(final String url, final ImageView imgV) {
		new AsyncTask<Void, Void, Bitmap>() {
			@Override
			protected Bitmap doInBackground(Void... voids) {
				return ImageCache.createBitmap(url);
			}

			@Override
			protected void onPostExecute(Bitmap bitmap) {
				imgV.setImageBitmap(bitmap);
			}
		}.execute();
	}

	@Override
	public int getItemCount() {
		return products.length;
	}

	/**
	 * onClick interface that I export to MainActivity
	 */
	public interface OnItemClicked {
		void onClick(Product p);
	}
}

class ProductViewHolder extends RecyclerView.ViewHolder {
	TextView nameTxt, countTxt, rateTxt, descTxt, homeScreenTxt;
	ImageView thumbnail, rateImg;

	ProductViewHolder(View itemView) {
		super(itemView);

		nameTxt = itemView.findViewById(R.id.nameTxt);
		countTxt = itemView.findViewById(R.id.countTxt);
		rateTxt = itemView.findViewById(R.id.rateTxt);
		descTxt = itemView.findViewById(R.id.descTxt);
		homeScreenTxt = itemView.findViewById(R.id.isHomeScreen);
		thumbnail = itemView.findViewById(R.id.thumbnail);
		rateImg = itemView.findViewById(R.id.rateImg);
	}
}