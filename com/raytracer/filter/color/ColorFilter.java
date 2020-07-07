package com.raytracer.filter.color;

import com.raytracer.image.Image;

import com.raytracer.filter.Filter;
import com.raytracer.math.vector.Vector4;

public abstract class ColorFilter implements Filter {
	public abstract Vector4 processColor(Vector4 color);

	public void processImage(Image image) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Vector4 currentColor = image.getPixel(x, y);

				image.setPixel(x, y, this.processColor(currentColor));
			}
		}
	}
}
