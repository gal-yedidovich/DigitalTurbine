package com.gal.digitalturbine;

public class Product {
	private String name, thumbnail, rateImg, desc, category, numOfRates, rate, bidRate, callToAction, minOSVersion;
	private boolean isHomeScreen;

	public String getName() {
		return name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public String getRateImg() {
		return rateImg;
	}

	public String getDesc() {
		return desc;
	}

	public String getCategory() {
		return category;
	}

	public String getRate() {
		return rate;
	}

	public String getRateCount() {
		return numOfRates;
	}

	public boolean isHomeScreen() {
		return isHomeScreen;
	}

	public String getBidRate() {
		return bidRate;
	}

	public String getCallToAction() {
		return callToAction;
	}

	public String getMinOSVersion() {
		return minOSVersion;
	}

	public static class Builder {
		private String name, thumbnail, rateImg, desc, category, numOfRates, rate, bidRate, callToAction, minOSVersion;
		private boolean isHomeScreen;

		public Product build() {
			Product p = new Product();
			p.name = name;
			p.thumbnail = thumbnail;
			p.rateImg = rateImg;
			p.desc = desc;
			p.category = category;
			p.numOfRates = numOfRates;
			p.rate = rate;
			p.bidRate = bidRate;
			p.callToAction = callToAction;
			p.minOSVersion = minOSVersion;
			p.isHomeScreen = isHomeScreen;

			return p;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
			return this;
		}

		public Builder setRateImg(String rateImg) {
			this.rateImg = rateImg;
			return this;
		}

		public Builder setDesc(String desc) {
			this.desc = desc;
			return this;
		}

		public Builder setCategory(String category) {
			this.category = category;
			return this;
		}

		public Builder setNumOfRates(String numOfRates) {
			this.numOfRates = numOfRates;
			return this;
		}

		public Builder setRate(String rate) {
			this.rate = rate;
			return this;
		}

		public Builder setBidRate(String bidRate) {
			this.bidRate = bidRate;
			return this;
		}

		public Builder setCallToAction(String callToAction) {
			this.callToAction = callToAction;
			return this;
		}

		public Builder setMinOSVersion(String minOSVersion) {
			this.minOSVersion = minOSVersion;
			return this;
		}

		public Builder setHomeScreen(boolean homeScreen) {
			isHomeScreen = homeScreen;
			return this;
		}
	}
}
