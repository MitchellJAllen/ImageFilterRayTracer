package com.raytracer.filter.color;

import com.raytracer.math.vector.Vector4;

public class ReplaceColorFilter extends ColorFilter {
	private Vector4 replaceColor;

	public ReplaceColorFilter(Vector4 replaceColor) {
		this.replaceColor = replaceColor;
	}

	public Vector4 getReplaceColor() {
		return this.replaceColor;
	}

	public void setReplaceColor(Vector4 replaceColor) {
		this.replaceColor = replaceColor;
	}

	public Vector4 processColor(Vector4 color) {
		return this.replaceColor;
	}
}
