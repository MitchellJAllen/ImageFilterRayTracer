package com.raytracer.math.vector;

public class Vector3 {
	private float x, y, z;

	public Vector3() {
		this.x = this.y = this.z = 0;
	}

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
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

	public Vector3 add(Vector3 vector) {
		return new Vector3(this.x + vector.x, this.y + vector.y, this.z + vector.z);
	}

	public Vector3 scale(float scalar) {
		return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
	}

	public float dot(Vector3 vector) {
		return (this.x * vector.x + this.y * vector.y + this.z * vector.z);
	}

	public Vector3 substract(Vector3 vector) {
		return this.add(vector.scale(-1));
	}

	public float length() {
		return (float)Math.sqrt(this.dot(this));
	}

	public Vector3 normalize() {
		return this.scale(1 / this.length());
	}

	public Vector3 cross(Vector3 vector) {
		return new Vector3(
			this.y * vector.z - this.z * vector.y,
			this.z * vector.x - this.x * vector.z,
			this.x * vector.y - this.y * vector.x
		);
	}

	public String toString() {
		return "<" + this.x + "," + this.y + "," + this.z + ">";
	}
}
