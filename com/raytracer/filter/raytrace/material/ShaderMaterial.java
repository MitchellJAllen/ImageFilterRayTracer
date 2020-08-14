package com.raytracer.filter.raytrace.material;

import com.raytracer.filter.raytrace.camera.Camera;
import com.raytracer.filter.raytrace.light.Light;

public abstract class ShaderMaterial extends InterpolationMaterial {
	private Camera camera;
	private Light[] lights;

	public ShaderMaterial(Camera camera, Light[] lights) {
		this.camera = camera;
		this.lights = lights;
	}

	public Camera getCamera() {
		return this.camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Light[] getLights() {
		return this.lights;
	}

	public void setLights(Light[] lights) {
		this.lights = lights;
	}
}
