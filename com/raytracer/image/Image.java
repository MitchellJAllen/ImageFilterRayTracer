package com.raytracer.image;

import com.raytracer.math.vector.Vector4;

import java.util.ArrayList;
import java.util.List;

public class Image {
	private int width, height;
	private List<Vector4> pixels;

	public Image(int width, int height) {
		this.width = width;
		this.height = height;

		int imageSize = width * height;

		this.pixels = new ArrayList<>(imageSize); // sets CAPACITY

		for (int index = 0; index < imageSize; index++) { // ensures SIZE matches CAPACITY
			this.pixels.add(new Vector4());
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	private int getIndex(int x, int y) {
		return y * this.width + x;
	}

	public Vector4 getPixel(int x, int y) {
		return this.pixels.get(this.getIndex(x, y));
	}

	public void setPixel(int x, int y, Vector4 color) {
		this.pixels.set(this.getIndex(x, y), color);
	}
}
