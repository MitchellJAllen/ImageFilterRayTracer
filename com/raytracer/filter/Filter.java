package com.raytracer.filter;

import com.raytracer.image.Image;

public interface Filter {
	public void processImage(Image image);
}
