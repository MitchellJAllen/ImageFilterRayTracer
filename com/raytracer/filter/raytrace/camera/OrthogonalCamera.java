package com.raytracer.filter.raytrace.camera;

import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector2;
import com.raytracer.math.vector.Vector3;

public class OrthogonalCamera implements Camera {
	private Vector3 position, direction;
	private float aspectRatio, scale;

	public OrthogonalCamera(Vector3 position, Vector3 direction) {
		this.position = position;
		this.direction = direction;
		this.aspectRatio = 1;
		this.scale = 1;
	}

	public OrthogonalCamera(Vector3 position, Vector3 direction, float aspectRatio, float scale) {
		this.position = position;
		this.direction = direction;
		this.aspectRatio = aspectRatio;
		this.scale = scale;
	}

	public Vector3 getPosition() {
		return this.position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public Vector3 getDirection() {
		return this.direction;
	}

	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}

	public float getAspectRatio() {
		return this.aspectRatio;
	}

	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public float getScale() {
		return this.scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Ray mapImageCoordinatesToRay(Vector2 imageCoordinates) {
		return new Ray(this.position, this.direction); // INCORRECT - still needs ray origin positioning
	}
}
