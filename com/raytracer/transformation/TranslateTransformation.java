package com.raytracer.transformation;

import com.raytracer.math.vector.Vector3;
import com.raytracer.model.Mesh;
import com.raytracer.model.Triangle;

public class TranslateTransformation implements Transformation {
	private Vector3 translateVector;

	public TranslateTransformation(Vector3 translateVector) {
		this.translateVector = translateVector;
	}

	public Vector3 getTranslateVector() {
		return this.translateVector;
	}

	public void setTranslateVector(Vector3 translateVector) {
		this.translateVector = translateVector;
	}

	public void processModel(Mesh mesh) {
		for (int index = 0; index < mesh.getTriangleCount(); index++) { // need to look for duplicate vertices
			Triangle triangle = mesh.getTriangleByIndex(index);

			triangle.getA().setPosition(triangle.getA().getPosition().add(this.translateVector));
			triangle.getB().setPosition(triangle.getB().getPosition().add(this.translateVector));
			triangle.getC().setPosition(triangle.getC().getPosition().add(this.translateVector));
		}
	}
}
