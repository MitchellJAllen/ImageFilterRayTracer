package com.raytracer.model;

import java.util.HashMap;

import com.raytracer.math.vector.Vector2;
import com.raytracer.math.vector.Vector3;

public class Vertex {
	private Vector3 position;
	private Vector3 normal;
	private HashMap<Integer, Vector2> textureCoordinates;

	public Vertex(Vector3 position) {
		this.position = position;
		this.textureCoordinates = new HashMap<>();
	}

	public Vector3 getPosition() {
		return this.position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public Vector3 getNormal() {
		return this.normal;
	}

	public void setNormal(Vector3 normal) {
		this.normal = normal;
	}

	public Vector2 getTextureCoordinates(int layerIndex) {
		return this.textureCoordinates.get(layerIndex);
	}

	public void setTextureCoordinates(int layerIndex, Vector2 textureCoordinates) {
		this.textureCoordinates.put(layerIndex, textureCoordinates);
	}
}
