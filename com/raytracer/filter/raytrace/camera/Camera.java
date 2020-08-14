package com.raytracer.filter.raytrace.camera;

import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector2;
import com.raytracer.math.vector.Vector3;

public interface Camera {
	Ray mapImageCoordinatesToRay(Vector2 imageCoordinates);
	Vector3 getDirection(Vector3 position);
}
