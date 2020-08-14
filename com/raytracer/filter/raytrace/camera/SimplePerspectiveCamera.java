package com.raytracer.filter.raytrace.camera;

import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector2;
import com.raytracer.math.vector.Vector3;

public class SimplePerspectiveCamera implements Camera {
	private float aspectRatio, fieldOfView;

	public SimplePerspectiveCamera(float aspectRatio, float fieldOfView) {
		this.aspectRatio = aspectRatio;
		this.fieldOfView = fieldOfView;
	}

	public float getAspectRatio() {
		return this.aspectRatio;
	}

	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public float getFieldOfView() {
		return this.fieldOfView;
	}

	public void setFieldOfView(float fieldOfView) {
		this.fieldOfView = fieldOfView;
	}

	public Ray mapImageCoordinatesToRay(Vector2 imageCoordinates) {
		Vector3 planePixelPosition = new Vector3(
			2 * imageCoordinates.getX() - 1,
			(2 * imageCoordinates.getY() - 1) / this.aspectRatio,
			0
		);

		// FOV [0, 180] maps to tangent of [90, 0]
		float radians = (90 - this.fieldOfView / 2) * (float)Math.PI / 180;
		Vector3 cameraFocalPoint = new Vector3(0, 0, (float)Math.tan(radians));

		return new Ray(cameraFocalPoint, planePixelPosition.substract(cameraFocalPoint));
	}

	public Vector3 getDirection(Vector3 position) {
		return position.scale(-1).normalize();
	}
}
