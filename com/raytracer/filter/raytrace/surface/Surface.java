package com.raytracer.filter.raytrace.surface;

import com.raytracer.filter.raytrace.material.Material;
import com.raytracer.model.Model;

public class Surface {
	private Model model;
	private Material material;

	public Surface(Model model, Material material) {
		this.model = model;
		this.material = material;
	}

	public Model getModel() {
		return this.model;
	}

	public Material getMaterial() {
		return this.material;
	}
}
