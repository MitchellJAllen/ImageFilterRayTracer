package com.raytracer.filter.raytrace.environment;

import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;

public class DirectionEnvironment implements Environment {
	private float compressComponent(float component) {
		return 0.5f * component + 0.5f;
	}

	private Vector4 generateEnvironmentColor(Vector3 direction) {
		Vector3 normalizedDirection = direction.normalize();

		return new Vector4(
			compressComponent(normalizedDirection.getX()),
			compressComponent(normalizedDirection.getY()),
			compressComponent(normalizedDirection.getZ()),
			1
		);
	}

	public Vector4 getColor(Ray ray) {
		return generateEnvironmentColor(ray.getDirection());
	}
}
