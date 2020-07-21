package com.raytracer.model;

import com.raytracer.math.ray.Ray;
import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.vector.Vector3;

public class Triangle {
	private static final float EPSILON = 0.0001f;

	private Vertex A, B, C;

	public Triangle(Vertex A, Vertex B, Vertex C) {
		this.A = A;
		this.B = B;
		this.C = C;
	}

	public Vertex getA() {
		return this.A;
	}

	public void setA(Vertex A) {
		this.A = A;
	}

	public Vertex getB() {
		return this.B;
	}

	public void setB(Vertex B) {
		this.B = B;
	}

	public Vertex getC() {
		return this.C;
	}

	public void setC(Vertex C) {
		this.C = C;
	}

	public RayTriangleQuery queryIntersection(Ray ray) { // Moller-Trumbore algorithm
		Vector3 edgeAB = this.B.getPosition().substract(this.A.getPosition());
		Vector3 edgeAC = this.C.getPosition().substract(this.A.getPosition());

		float viewVolume = edgeAB.dot(ray.getDirection().cross(edgeAC));

		if (Math.abs(viewVolume) < EPSILON) {
			return null; // no intersection
		}

		float divisor = 1 / viewVolume;
		Vector3 vertexAToOrigin = ray.getOrigin().substract(this.A.getPosition());

		float u = vertexAToOrigin.dot(ray.getDirection().cross(edgeAC)) * divisor;

		if (u < 0 || u > 1) {
			return null; // no intersection
		}

		Vector3 someRandomVector = vertexAToOrigin.cross(edgeAB);

		float v = ray.getDirection().dot(someRandomVector) * divisor;

		if (v < 0 || u + v > 1) {
			return null; // no intersection
		}

		float t = edgeAC.dot(someRandomVector) * divisor;

		if (t > EPSILON) {
			RayTriangleQuery query = new RayTriangleQuery(ray, this);

			query.setDepth(t);
			query.setU(u);
			query.setV(v);

			return query;
		}

		return null; // no intersection
	}
}
