package com.raytracer.filter.raytrace.environment;

import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector4;

public interface Environment {
	Vector4 getColor(Ray ray);
}
