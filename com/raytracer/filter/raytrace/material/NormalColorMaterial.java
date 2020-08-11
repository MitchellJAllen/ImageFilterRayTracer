package com.raytracer.filter.raytrace.material;

import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;

public class NormalColorMaterial extends InterpolationMaterial {
	private float compressComponent(float component) {
		return 0.5f * component + 0.5f;
	}

	private Vector4 generateNormalColor(Vector3 normal) {
		return new Vector4(
			compressComponent(normal.getX()),
			compressComponent(normal.getY()),
			compressComponent(normal.getZ()),
			1
		);
	}

	public Vector4 getColor(RayTriangleQuery query) {
		return this.generateNormalColor(this.getInterpolatedNormal(query));
	}
}
