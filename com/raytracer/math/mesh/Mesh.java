package com.raytracer.math.mesh;

import com.raytracer.math.ray.Ray;

public interface Mesh {
	public boolean intersects(Ray ray);
}
