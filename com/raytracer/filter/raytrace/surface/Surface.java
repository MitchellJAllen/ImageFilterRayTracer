package com.raytracer.filter.raytrace.surface;

import com.raytracer.filter.raytrace.material.Material;
import com.raytracer.model.Mesh;

public class Surface {
	private Mesh mesh;
	private Material material;

	public Surface(Mesh mesh, Material material) {
		this.mesh = mesh;
		this.material = material;
	}

	public Mesh getMesh() {
		return this.mesh;
	}

	public Material getMaterial() {
		return this.material;
	}
}
