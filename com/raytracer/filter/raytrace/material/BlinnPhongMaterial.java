package com.raytracer.filter.raytrace.material;

import com.raytracer.filter.raytrace.camera.Camera;
import com.raytracer.filter.raytrace.light.Light;
import com.raytracer.math.ray.RayTriangleQuery;
import com.raytracer.math.vector.Vector3;
import com.raytracer.math.vector.Vector4;

public class BlinnPhongMaterial extends ShaderMaterial {
	private Vector3 diffuseColor;
	private Vector3 specularColor;

	public BlinnPhongMaterial(Camera camera, Light[] lights) {
		super(camera, lights);

		this.diffuseColor = new Vector3(1, 1, 1);
		this.specularColor = new Vector3(1, 1, 1);
	}

	public BlinnPhongMaterial(Camera camera, Light[] lights, Vector3 diffuseColor) {
		super(camera, lights);

		this.diffuseColor = diffuseColor;
		this.specularColor = new Vector3(1, 1, 1);
	}

	public BlinnPhongMaterial(Camera camera, Light[] lights, Vector3 diffuseColor, Vector3 specularColor) {
		super(camera, lights);

		this.diffuseColor = diffuseColor;
		this.specularColor = specularColor;
	}

	public Vector4 getColor(RayTriangleQuery query) {
		Vector3 position = query.getPosition();
		Vector3 normal = this.getInterpolatedNormal(query);

		Vector4 finalColor = new Vector4();

		for (Light light : this.getLights()) {
			Vector3 lightDirection = light.getDirection(position);
			Vector3 cameraDirection = this.getCamera().getDirection(position);

			Vector3 halfVector = lightDirection.add(cameraDirection).normalize();

			float diffuseDotProduct = Math.max(0, normal.dot(lightDirection));
			float specularDotProduct = (float)Math.pow(Math.max(0, normal.dot(halfVector)), 50);

			Vector3 lightColor = light.getColor();
			Vector3 diffuseColor = new Vector3(
				this.diffuseColor.getX() * lightColor.getX(),
				this.diffuseColor.getY() * lightColor.getY(),
				this.diffuseColor.getZ() * lightColor.getZ()
			);
			Vector3 specularColor = new Vector3(
				this.specularColor.getX() * lightColor.getX(),
				this.specularColor.getY() * lightColor.getY(),
				this.specularColor.getZ() * lightColor.getZ()
			);

			Vector3 color = diffuseColor.scale(diffuseDotProduct).add(specularColor.scale(specularDotProduct));

			finalColor = new Vector4(
				finalColor.getX() + color.getX(),
				finalColor.getY() + color.getY(),
				finalColor.getZ() + color.getZ(),
				1
			);
		}

		return finalColor;
	}
}
