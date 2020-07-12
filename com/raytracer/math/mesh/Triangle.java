package com.raytracer.math.mesh;

import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector3;

public class Triangle implements Mesh {
	private Vector3 vertexA, vertexB, vertexC;

	public Triangle(Vector3 vertexA, Vector3 vertexB, Vector3 vertexC) {
		this.vertexA = vertexA;
		this.vertexB = vertexB;
		this.vertexC = vertexC;
	}

	public Vector3 getVertexA() {
		return this.vertexA;
	}

	public void setVertexA(Vector3 vertexA) {
		this.vertexA = vertexA;
	}

	public Vector3 getVertexB() {
		return this.vertexB;
	}

	public void setVertexB(Vector3 vertexB) {
		this.vertexB = vertexB;
	}

	public Vector3 getVertexC() {
		return this.vertexC;
	}

	public void setVertexC(Vector3 vertexC) {
		this.vertexC = vertexC;
	}

	public boolean intersects(Ray ray) { // Moller-Trumbore algorithm
		Vector3 edgeAB = this.vertexB.substract(this.vertexA);
		Vector3 edgeAC = this.vertexC.substract(this.vertexA);

		float viewVolume = edgeAB.dot(ray.getDirection().cross(edgeAC));

		if (Math.abs(viewVolume) < 0.0001) {
			return false;
		}

		float divisor = 1 / viewVolume;
		Vector3 vertexAToOrigin = ray.getOrigin().substract(this.vertexA);

		float u = vertexAToOrigin.dot(ray.getDirection().cross(edgeAC)) * divisor;

		if (u < 0 || u > 1) {
			return false;
		}

		Vector3 someRandomVector = vertexAToOrigin.cross(edgeAB);

		float v = ray.getDirection().dot(someRandomVector) * divisor;

		if (v < 0 || u + v > 1) {
			return false;
		}

		float t = edgeAC.dot(someRandomVector) * divisor;

		if (t > 0.0001) {
			return true;
		}

		return false;
	}
}
