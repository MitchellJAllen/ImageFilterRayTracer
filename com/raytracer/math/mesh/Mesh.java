package com.raytracer.math.mesh;

import com.raytracer.math.ray.Ray;
import com.raytracer.math.ray.RayTriangleQuery;

import java.util.ArrayList;
import java.util.Collection;

public class Mesh {
	private ArrayList<Triangle> triangles; // may need more spatially-aware storage option

	public Mesh() {
		this.triangles = new ArrayList<>();
	}

	public Mesh(Triangle... triangles) {
		this.triangles = new ArrayList<>(triangles.length);

		for (Triangle triangle : triangles) {
			this.triangles.add(triangle);
		}
	}

	public Mesh(Collection<Triangle> triangles) {
		this.triangles = new ArrayList<>(triangles.size());

		for (Triangle triangle : triangles) {
			this.triangles.add(triangle);
		}
	}

	public void addTriangle(Triangle triangle) {
		this.triangles.add(triangle);
	}

	public RayTriangleQuery queryIntersection(Ray ray) { // ideally, more efficient algorithm
		RayTriangleQuery closestTriangleIntersection = null;

		for (Triangle triangle : this.triangles) {
			RayTriangleQuery query = triangle.queryIntersection(ray);

			if (
				query.isIntersection() && (
					closestTriangleIntersection == null ||
					closestTriangleIntersection.getDepth() > query.getDepth()
				)
			) {
				closestTriangleIntersection = query;
			}
		}

		return closestTriangleIntersection;
	}
}
