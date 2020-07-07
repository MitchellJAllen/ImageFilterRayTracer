package com.raytracer.math.ray;

import com.raytracer.math.vector.Vector3;

public class Ray {
	private Vector3 origin, direction;

	public Ray(Vector3 origin, Vector3 direction) {
		this.origin = origin;
		this.direction = direction;
	}

	public Vector3 getOrigin() {
		return this.origin;
	}

	public void setOrigin(Vector3 origin) {
		this.origin = origin;
	}

	public Vector3 getDirection() {
		return this.direction;
	}

	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}
}
