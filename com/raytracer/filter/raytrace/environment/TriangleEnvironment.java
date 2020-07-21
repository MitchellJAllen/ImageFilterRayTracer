package com.raytracer.filter.raytrace.environment;

import com.raytracer.filter.raytrace.material.VertexColorMaterial;
import com.raytracer.filter.raytrace.surface.Surface;
import com.raytracer.model.Mesh;
import com.raytracer.model.Triangle;
import com.raytracer.model.Vertex;
import com.raytracer.transformation.TranslateTransformation;
import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;

public class TriangleEnvironment implements Environment {
	private Surface surface;

	public TriangleEnvironment() {
		Vertex a = new Vertex(new Vector3(0, 1, 0));
		Vertex b = new Vertex(new Vector3(-1, -1, 0));
		Vertex c = new Vertex(new Vector3(1, -1, 0));

		a.setProperty("color", new Vector4(1, 0, 0, 1)); // used in material
		b.setProperty("color", new Vector4(0, 1, 0, 1));
		c.setProperty("color", new Vector4(0, 0, 1, 1));

		this.surface = new Surface(
			new Mesh(new Triangle(a, b, c)),
			new VertexColorMaterial()
		);

		// test of basic transformation
		new TranslateTransformation(new Vector3(0, 0, -2)).processModel(this.surface.getMesh());
	}

	public Vector4 getColor(Ray ray) {
		RayTriangleQuery query = this.surface.getMesh().queryIntersection(ray);

		if (query != null) {
			return this.surface.getMaterial().getColor(query);
		}
		else {
			return new Vector4(0, 0, 0, 0); // transparent
		}
	}
}
