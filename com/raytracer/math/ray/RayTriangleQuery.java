package com.raytracer.math.ray;

import com.raytracer.math.mesh.Triangle;
import com.raytracer.math.vector.Vector3;

public class RayTriangleQuery {
	private Ray ray;
	private Triangle triangle;
	private float depth, u, v;

	public RayTriangleQuery(Ray ray, Triangle triangle) {
		this.ray = ray;
		this.triangle = triangle;
	}

	public Ray getRay() {
		return this.ray;
	}

	public Triangle getTriangle() {
		return this.triangle;
	}

	public float getDepth() {
		return this.depth;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}

	public float getU() {
		return this.u;
	}

	public void setU(float u) {
		this.u = u;
	}

	public float getV() {
		return this.v;
	}

	public void setV(float v) {
		this.v = v;
	}

	public float getW() {
		return 1 - this.u - this.v;
	}

	public Vector3 getPosition() {
		return this.ray.getOrigin().add(this.ray.getDirection().scale(this.depth));
	}
}
