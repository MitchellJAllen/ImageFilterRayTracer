package com.raytracer.filter.raytrace.material;

import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;

public class NormalColorMaterial implements Material {
	private float compressComponent(float component) {
		return 0.5f * component + 0.5f;
	}

	private Vector4 generateNormalColor(Vector3 direction) {
		Vector3 normalizedDirection = direction.normalize();

		return new Vector4(
			compressComponent(normalizedDirection.getX()),
			compressComponent(normalizedDirection.getY()),
			compressComponent(normalizedDirection.getZ()),
			1
		);
	}

	public Vector4 getColor(RayTriangleQuery query) {
		Vector3 contributionA = query.getTriangle().getA().getProperty("normal", Vector3.class).scale(query.getW());
		Vector3 contributionB = query.getTriangle().getB().getProperty("normal", Vector3.class).scale(query.getU());
		Vector3 contributionC = query.getTriangle().getC().getProperty("normal", Vector3.class).scale(query.getV());

		return this.generateNormalColor(contributionA.add(contributionB.add(contributionC)));
	}
}
