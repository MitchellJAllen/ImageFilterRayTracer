package com.raytracer.filter.raytrace.light;

import com.raytracer.math.vector.Vector3;

public interface Light {
	Vector3 getColor();
	Vector3 getDirection(Vector3 position);
	float getAttenuation(Vector3 position);
}
