package com.raytracer.filter.raytrace.environment;

import com.raytracer.math.mesh.Triangle;
import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;

public class TriangleEnvironment implements Environment {
	private Triangle triangle;

	public TriangleEnvironment() {
		this.triangle = new Triangle(
			new Vector3(0, 1, -2),
			new Vector3(-1, -1, -2),
			new Vector3(1, -1, -2)
		);
	}

	public Vector4 getColor(Ray ray) {
		if (this.triangle.intersects(ray)) {
			return new Vector4(1, 0, 0, 1); // red
		}
		else {
			return new Vector4(0, 0, 0, 0); // transparent
		}
	}
}
