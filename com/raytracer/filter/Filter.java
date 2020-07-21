package com.raytracer.filter;

import com.raytracer.image.Image;

public interface Filter {
	void processImage(Image image);
}
