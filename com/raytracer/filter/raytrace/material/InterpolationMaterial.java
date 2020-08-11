package com.raytracer.filter.raytrace.material;

import com.raytracer.math.ray.RayTriangleQuery;

import com.raytracer.math.vector.Vector2;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;

public abstract class InterpolationMaterial implements Material {
	public abstract Vector4 getColor(RayTriangleQuery query);

	public Vector3 getInterpolatedNormal(RayTriangleQuery query) {
		Vector3 contributionA = query.getTriangle().getA().getNormal().scale(query.getW());
		Vector3 contributionB = query.getTriangle().getB().getNormal().scale(query.getU());
		Vector3 contributionC = query.getTriangle().getC().getNormal().scale(query.getV());

		return contributionA.add(contributionB).add(contributionC).normalize();
	}

	public Vector2 getInterpolatedTextureCoordinates(RayTriangleQuery query, int layerIndex) {
		Vector2 contributionA = query.getTriangle().getA().getTextureCoordinates(layerIndex).scale(query.getW());
		Vector2 contributionB = query.getTriangle().getB().getTextureCoordinates(layerIndex).scale(query.getU());
		Vector2 contributionC = query.getTriangle().getC().getTextureCoordinates(layerIndex).scale(query.getV());

		return contributionA.add(contributionB).add(contributionC); // perspective magic needed?
	}
}
