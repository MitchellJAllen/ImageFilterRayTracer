package com.raytracer.filter.raytrace.material;

import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.vector.Vector4;

public interface Material {
	Vector4 getColor(RayTriangleQuery query);
}
