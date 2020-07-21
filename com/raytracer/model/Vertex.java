package com.raytracer.model;

import java.util.HashMap;

import com.raytracer.math.vector.Vector3;

public class Vertex {
	private static final String POSITION_NAME = "position";

	private HashMap<String, String> propertyType;
	private HashMap<String, Object> propertyData;

	public Vertex(Vector3 position) {
		this.propertyType = new HashMap<>();
		this.propertyData = new HashMap<>();

		this.setPosition(position);
	}

	public Vector3 getPosition() {
		return this.getProperty(POSITION_NAME, Vector3.class);
	}

	public void setPosition(Vector3 position) {
		this.setProperty(POSITION_NAME, position);
	}

	private <DataType> String calculateTypeName(Class<DataType> propertyType) {
		if (propertyType == null || propertyType.getCanonicalName() == null) {
			return Object.class.getCanonicalName();
		}

		return propertyType.getCanonicalName();
	}

	public <DataType> DataType getProperty(String propertyName, Class<DataType> propertyType) {
		String inputTypeName = this.calculateTypeName(propertyType);
		String storedTypeName = this.propertyType.get(propertyName);

		if (inputTypeName.equals(storedTypeName)) {
			return (DataType)this.propertyData.get(propertyName);
		}

		return null;
	}

	public void setProperty(String propertyName, Object propertyValue) {
		String inputTypeName = Object.class.getCanonicalName();

		if (propertyValue != null) {
			inputTypeName = this.calculateTypeName(propertyValue.getClass());
		}

		String storedTypeName = this.propertyType.get(propertyName);

		if (storedTypeName == null) {
			storedTypeName = inputTypeName;
			this.propertyType.put(propertyName, storedTypeName);
		}

		if (inputTypeName.equals(storedTypeName)) {
			this.propertyData.put(propertyName, propertyValue);
		}
	}

	public void deleteProperty(String propertyName) {
		if (POSITION_NAME.equals(propertyName)) {
			return; // not allowed to delete position property
		}

		this.propertyType.remove(propertyName);
		this.propertyData.remove(propertyName);
	}
}
