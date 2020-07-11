# Image Filter and Ray Tracer

## Overview
This project provides a simple Java framework for queueable image filters, some of which implement ray tracing functionality.
Its purpose is to allow support for a broad range of graphics operations like diffuse lighting, anti-aliasing, HDR/bloom, etc.
Once an Image is instantiated, one can queue ray tracing of a given environment, post-process filters, and a save operation.

## Current Features
* The Image class stores colors brighter than [0,1] to allow for HDR/bloom operations and has an alpha channel for transparency.
* TGA file saving has been implemented in the SaveFilter class and preserves transparency but clamps colors beyond [0,1].
* Basic vector math can be found in the Vector2, Vector3, and Vector4 classes. Vector4 is often used for colors.
* The Setup class provides usage examples as well as a method of queuing image filters.

## Future Goals
* Implement an Environment that contains Surfaces and does basic ray tracing.
* Provide a Mesh interface so that its implementations can perform ray collision checking.
* Explore possibility of a meta-Mesh which contains other Mesh implementations.
* Provide a Material interface for complex lighting operations and add basic implementations.
* Create a Surface class that combines Mesh and Material interfaces to allow Material re-use.
* Explore possibility of a meta-Surface (contains other Surfaces).
* Extend RayTracingFilter class and perform multisample anti-aliasing in child class.
* Ideally, create post-process filters like brightness/contrast, HDR/bloom, sharpen, etc.
* Hopefully, allow pre-computation steps and scene storage for radiosity, photon mapping, ambient occlusion, etc.

## Compiling and Running
Provided in this project is a simple Makefile that can compile, run, and clean up after the Java program.
The commands themselves can be run directly from a terminal or command line manually.
If you are using a shell other than BASH, the clean command may not work.
All the clean command does is remove all "*.class" files in the com folder (or its subfolders).
