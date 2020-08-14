package com.raytracer.filter.raytrace.light;

import com.raytracer.math.vector.Vector3;

public class DirectionalLight implements Light {
	private Vector3 color;
	private Vector3 direction;

	public DirectionalLight(Vector3 color, Vector3 direction) {
		this.color = color;
		this.direction = direction;
	}

	public Vector3 getColor() {
		return this.color;
	}

	public void setColor(Vector3 color) {
		this.color = color;
	}

	public Vector3 getDirection() {
		return this.direction;
	}

	public Vector3 getDirection(Vector3 position) {
		return this.direction;
	}

	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}

	public float getAttenuation(Vector3 position) {
		return 1;
	}
}
