package com.raytracer.math.vector;

public class Vector2 {
	private float x, y;

	public Vector2() {
		this.x = this.y = 0;
	}

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
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

	public Vector2 add(Vector2 vector) {
		return new Vector2(this.x + vector.x, this.y + vector.y);
	}

	public Vector2 scale(float scalar) {
		return new Vector2(this.x * scalar, this.y * scalar);
	}

	public float dot(Vector2 vector) {
		return (this.x * vector.x + this.y * vector.y);
	}

	public Vector2 substract(Vector2 vector) {
		return this.add(vector.scale(-1));
	}

	public float length() {
		return (float)Math.sqrt(this.dot(this));
	}

	public Vector2 normalize() {
		return this.scale(1 / this.length());
	}

	public String toString() {
		return "<" + this.x + "," + this.y + ">";
	}
}
