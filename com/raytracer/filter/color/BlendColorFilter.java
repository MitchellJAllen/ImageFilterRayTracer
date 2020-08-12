package com.raytracer.filter.color;

import com.raytracer.math.vector.Vector4;

public class BlendColorFilter extends ColorFilter {
	private Vector4 blendColor;
	private float intensity;

	public BlendColorFilter(Vector4 blendColor, float intensity) {
		this.blendColor = blendColor;
		this.intensity = intensity;
	}

	public Vector4 getBlendColor() {
		return this.blendColor;
	}

	public void setBlendColor(Vector4 blendColor) {
		this.blendColor = blendColor;
	}

	public float getIntensity() {
		return this.intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}

	public Vector4 processColor(Vector4 color) {
		return this.blendColor.scale(this.intensity).add(color.scale(1 - this.intensity));
	}
}
