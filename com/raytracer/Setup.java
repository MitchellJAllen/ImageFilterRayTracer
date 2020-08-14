package com.raytracer;

import com.raytracer.filter.Filter;
import com.raytracer.filter.color.BlendColorFilter;
import com.raytracer.filter.color.NoiseFilter;
import com.raytracer.filter.color.ReplaceColorFilter;
import com.raytracer.filter.file.SaveFilter;
import com.raytracer.filter.raytrace.RayTracingFilter;
import com.raytracer.filter.raytrace.camera.SimplePerspectiveCamera;
import com.raytracer.filter.raytrace.environment.TestEnvironment;
import com.raytracer.filter.raytrace.light.DirectionalLight;
import com.raytracer.filter.raytrace.light.Light;
import com.raytracer.filter.raytrace.material.BlinnPhongMaterial;
import com.raytracer.filter.raytrace.surface.Surface;
import com.raytracer.image.Image;
import com.raytracer.image.loader.TargaImageLoader;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;
import com.raytracer.model.Model;
import com.raytracer.model.loader.WavefrontModelLoader;
import com.raytracer.transformation.TranslateTransformation;

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
		DirectionalLight testLight = new DirectionalLight(new Vector3(1, 1, 1), new Vector3(1, 1, 0).normalize());
		DirectionalLight testLight2 = new DirectionalLight(new Vector3(1, 1, 1), new Vector3(-1, 1, 0).normalize());

		Light[] testLights = {testLight, testLight2};

		Model testModel = new WavefrontModelLoader().loadModel("roundedCube.obj");
		BlinnPhongMaterial testMaterial = new BlinnPhongMaterial(testCamera, testLights, new Vector3(1, 0.75f, 0.5f));
		Surface testSurface = new Surface(testModel, testMaterial);

		// test of basic transformation
		new TranslateTransformation(new Vector3(0, 0, -2)).processModel(testModel);

		TestEnvironment testEnvironment = new TestEnvironment(testSurface);
		RayTracingFilter testFilter = new RayTracingFilter(testCamera, testEnvironment);

		List<Filter> filters = new ArrayList<>();
		filters.add(new ReplaceColorFilter(new Vector4(0, 0.5f, 1, 1)));
		filters.add(new NoiseFilter(0.5f));
		filters.add(new SaveFilter("out.tga"));
		filters.add(testFilter);
		filters.add(new SaveFilter("out2.tga"));

		runFilters(filters, test);

		Image redTest = new TargaImageLoader().loadImage("red.tga");

		new BlendColorFilter(new Vector4(0, 0, 1, 1), 0.5f).processImage(redTest);
		new SaveFilter("out3.tga").processImage(redTest);

		System.out.println(test.getPixel(500, 300));
	}
}
