package com.raytracer.filter.raytrace;

import com.raytracer.filter.Filter;
import com.raytracer.filter.raytrace.camera.Camera;
import com.raytracer.filter.raytrace.environment.Environment;
import com.raytracer.image.Image;
import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector2;
import com.raytracer.math.vector.Vector4;

public class RayTracingFilter implements Filter {
	private Camera camera;
	private Environment environment;

	public RayTracingFilter(Camera camera, Environment environment) {
		this.camera = camera;
		this.environment = environment;
	}

	public Camera getCamera() {
		return this.camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Environment getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	// override this method for multi-sampling in child class
	private Vector4 getEnvironmentColorFromPixelCoordinates(Vector2 pixelCoordinates, Image image) {
		Vector2 imageCoordinates = new Vector2(
			pixelCoordinates.getX() / (image.getWidth() - 1),
			pixelCoordinates.getY() / (image.getHeight() - 1)
		);

		Ray cameraRay = this.camera.mapImageCoordinatesToRay(imageCoordinates);

		return this.environment.getColor(cameraRay);
	}

	public void processImage(Image image) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Vector2 pixelCoordinates = new Vector2(x, y);

				image.setPixel(x, y, this.getEnvironmentColorFromPixelCoordinates(pixelCoordinates, image));
			}
		}
	}
}
