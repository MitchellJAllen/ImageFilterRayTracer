package com.raytracer.filter.raytrace.camera;

import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector2;

public interface Camera {
	public Ray mapImageCoordinatesToRay(Vector2 imageCoordinates);
}
