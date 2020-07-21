package com.raytracer.filter.raytrace.environment;

import com.raytracer.filter.raytrace.material.NormalColorMaterial;
import com.raytracer.filter.raytrace.surface.Surface;
import com.raytracer.model.loader.WavefrontModelLoader;
import com.raytracer.transformation.TranslateTransformation;
import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.ray.Ray;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;

public class TriangleEnvironment implements Environment {
	private Surface surface;

	public TriangleEnvironment() {
		this.surface = new Surface(
			new WavefrontModelLoader().loadModel("roundedCube.obj"),
			new NormalColorMaterial()
		);

		// test of basic transformation
		new TranslateTransformation(new Vector3(0, 0, -2)).processModel(this.surface.getModel());
	}

	public Vector4 getColor(Ray ray) {
		RayTriangleQuery query = this.surface.getModel().queryIntersection(ray);

		if (query != null) {
			return this.surface.getMaterial().getColor(query);
		}
		else {
			return new Vector4(0, 0, 0, 0); // transparent
		}
	}
}
