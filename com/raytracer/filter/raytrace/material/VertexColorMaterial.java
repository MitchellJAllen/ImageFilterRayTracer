package com.raytracer.filter.raytrace.material;

import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.vector.Vector4;

public class VertexColorMaterial implements Material {
	public Vector4 getColor(RayTriangleQuery query) {
		Vector4 contributionA = query.getTriangle().getA().getProperty("color", Vector4.class).scale(query.getW());
		Vector4 contributionB = query.getTriangle().getB().getProperty("color", Vector4.class).scale(query.getU());
		Vector4 contributionC = query.getTriangle().getC().getProperty("color", Vector4.class).scale(query.getV());

		return contributionA.add(contributionB).add(contributionC);
	}
}
