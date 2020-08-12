package com.raytracer.image.loader;

import com.raytracer.image.Image;

public interface ImageLoader {
	Image loadImage(String filePath);
}
