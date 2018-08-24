package com.gal.digitalturbine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * static factory method & flyweight
 */
public class ImageCache {
	private ImageCache() {
	}

	private final static HashMap<String, Bitmap> CACHE = new HashMap<>();

	public static Bitmap createBitmap(String url) {
		if (url == null) return null;
		if (CACHE.containsKey(url)) return CACHE.get(url);

		try {
			byte[] imgData = new HttpRequest(url).prepare().sendAndReadBytes();
			Bitmap newBitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
			CACHE.put(url, newBitmap);
			return newBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("error in fetching image");
		}


	}
}
