package com.raytracer.filter.raytrace.environment;

import com.raytracer.math.mesh.Triangle;
import com.raytracer.math.mesh.Vertex;
import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;

public class TriangleEnvironment implements Environment {
	private Triangle triangle;

	public TriangleEnvironment() {
		Vertex a = new Vertex(new Vector3(0, 1, -2));
		Vertex b = new Vertex(new Vector3(-1, -1, -2));
		Vertex c = new Vertex(new Vector3(1, -1, -2));

		a.setProperty("color", new Vector4(1, 0, 0, 1));
		b.setProperty("color", new Vector4(0, 1, 0, 1));
		c.setProperty("color", new Vector4(0, 0, 1, 1));

		this.triangle = new Triangle(a, b, c);
	}

	public Vector4 getColor(Ray ray) {
		RayTriangleQuery query = this.triangle.queryIntersection(ray);

		if (query.isIntersection()) { // demonstration of interpolation
			Vector4 contributionA = this.triangle.getA().getProperty("color", Vector4.class).scale(query.getW());
			Vector4 contributionB = this.triangle.getB().getProperty("color", Vector4.class).scale(query.getU());
			Vector4 contributionC = this.triangle.getC().getProperty("color", Vector4.class).scale(query.getV());

			return contributionA.add(contributionB).add(contributionC);
		}
		else {
			return new Vector4(0, 0, 0, 0); // transparent
		}
	}
}
