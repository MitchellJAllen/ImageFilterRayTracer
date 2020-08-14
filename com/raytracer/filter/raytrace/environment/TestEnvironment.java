package com.raytracer.filter.raytrace.environment;

import com.raytracer.filter.raytrace.surface.Surface;
import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector4;

public class TestEnvironment implements Environment {
	private Surface surface;

	public TestEnvironment(Surface surface) {
		this.surface = surface;
	}

	public Surface getSurface() {
		return this.surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public Vector4 getColor(Ray ray) {
		RayTriangleQuery query = this.surface.getModel().queryIntersection(ray);

		if (query != null) {
			return this.surface.getMaterial().getColor(query);
		}
		else {
			return new Vector4(); // transparent
		}
	}
}
