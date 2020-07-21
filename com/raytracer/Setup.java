package com.raytracer;

import com.raytracer.filter.Filter;
import com.raytracer.filter.color.NoiseFilter;
import com.raytracer.filter.color.ReplaceColorFilter;
import com.raytracer.filter.file.SaveFilter;
import com.raytracer.filter.raytrace.RayTracingFilter;
import com.raytracer.filter.raytrace.camera.SimplePerspectiveCamera;
import com.raytracer.filter.raytrace.environment.TestEnvironment;
import com.raytracer.image.Image;
import com.raytracer.math.vector.Vector4;

import java.util.ArrayList;
import java.util.List;

public class Setup {
	public static void runFilters(List<Filter> filters, Image image) {
		for (Filter filter : filters) {
			filter.processImage(image);
		}
	}

	public static void main(String[] args) {
		Image test = new Image(800, 600);

		SimplePerspectiveCamera testCamera = new SimplePerspectiveCamera(1.333f, 70);
		TestEnvironment testEnvironment = new TestEnvironment();
		RayTracingFilter testFilter = new RayTracingFilter(testCamera, testEnvironment);

		List<Filter> filters = new ArrayList<>();
		filters.add(new ReplaceColorFilter(new Vector4(0, 0.5f, 1, 1)));
		filters.add(new NoiseFilter(0.5f));
		filters.add(new SaveFilter("out.tga"));
		filters.add(testFilter);
		filters.add(new SaveFilter("out2.tga"));

		runFilters(filters, test);

		System.out.println(test.getPixel(500, 300));
	}
}
