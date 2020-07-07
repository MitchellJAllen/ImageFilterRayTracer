package com.raytracer.math.vector;

public class Vector4 {
	private float x, y, z, w;

	public Vector4() {
		this.x = this.y = this.z = this.w = 0;
	}

	public Vector4(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return this.z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return this.w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public Vector4 add(Vector4 vector) {
		return new Vector4(this.x + vector.x, this.y + vector.y, this.z + vector.z, this.w + vector.w);
	}

	public Vector4 scale(float scalar) {
		return new Vector4(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
	}

	public float dot(Vector4 vector) {
		return (this.x * vector.x + this.y * vector.y + this.z * vector.z + this.w * vector.w);
	}

	public Vector4 substract(Vector4 vector) {
		return this.add(vector.scale(-1));
	}

	public float length() {
		return (float)Math.sqrt(this.dot(this));
	}

	public Vector4 normalize() {
		return this.scale(1 / this.length());
	}

	public String toString() {
		return "<" + this.x + "," + this.y + "," + this.z + "," + this.w + ">";
	}
}
