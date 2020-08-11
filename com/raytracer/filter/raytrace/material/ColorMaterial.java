package com.raytracer.filter.raytrace.material;

import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.vector.Vector4;

public class ColorMaterial implements Material {
	private Vector4 color;

	public ColorMaterial(Vector4 color) {
		this.color = color;
	}

	public Vector4 getColor(RayTriangleQuery query) {
		return this.color;
	}
}
