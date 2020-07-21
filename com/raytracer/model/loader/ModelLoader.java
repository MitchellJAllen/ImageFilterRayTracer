package com.raytracer.model.loader;

import com.raytracer.model.Model;

public interface ModelLoader {
	Model loadModel(String fileName);
}
