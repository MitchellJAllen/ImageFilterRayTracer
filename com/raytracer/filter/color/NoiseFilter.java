package com.raytracer.filter.color;

import com.raytracer.math.vector.Vector4;

public class NoiseFilter extends ColorFilter {
	private float intensity;

	public NoiseFilter(float intensity) {
		this.intensity = intensity;
	}

	public float getIntensity() {
		return this.intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}


	private float addNoise(float componentValue) {
		return this.intensity * (float)Math.random() + (1 - intensity) * componentValue;
	}

	public Vector4 processColor(Vector4 color) {
		return new Vector4(
			this.addNoise(color.getX()),
			this.addNoise(color.getY()),
			this.addNoise(color.getZ()),
			color.getW()
		);
	}
}
