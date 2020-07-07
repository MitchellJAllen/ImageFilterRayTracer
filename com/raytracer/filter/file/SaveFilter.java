package com.raytracer.filter.file;

import com.raytracer.filter.Filter;
import com.raytracer.image.Image;
import com.raytracer.math.vector.Vector4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveFilter implements Filter {
	private static final int headerSize = 18;
	private static final int redOffset = 2;
	private static final int greenOffset = 1;
	private static final int blueOffset = 0;
	private static final int alphaOffset = 3;

	private String fileName;

	public SaveFilter(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private void writePixel(byte[] tgaData, int pixelIndex, Vector4 pixelColor) {
		tgaData[headerSize + pixelIndex * 4 + redOffset] = processPixelComponent(pixelColor.getX());
		tgaData[headerSize + pixelIndex * 4 + greenOffset] = processPixelComponent(pixelColor.getY());
		tgaData[headerSize + pixelIndex * 4 + blueOffset] = processPixelComponent(pixelColor.getZ());
		tgaData[headerSize + pixelIndex * 4 + alphaOffset] = processPixelComponent(pixelColor.getW());
	}

	private byte processPixelComponent(float componentValue) {
		float clampedValue = Math.max(Math.min(componentValue, 1), 0);

		return (byte)Math.round(255 * clampedValue);
	}

	public void processImage(Image image) {
		int pixelCount = image.getWidth() * image.getHeight();

		byte[] tgaData = new byte[headerSize + 4 * pixelCount];

		// TGA header
		tgaData[0x02] = 2; // uncompressed RGB
		tgaData[0x0C] = (byte)image.getWidth();
		tgaData[0x0D] = (byte)(image.getWidth() >> 8);
		tgaData[0x0E] = (byte)image.getHeight();
		tgaData[0x0F] = (byte)(image.getHeight() >> 8);
		tgaData[0x10] = 32; // bits per pixel

		for (int pixelIndex = 0; pixelIndex < pixelCount; pixelIndex++) {
			int x = pixelIndex % image.getWidth();
			int y = pixelIndex / image.getWidth();

			Vector4 pixelColor = image.getPixel(x, y);

			writePixel(tgaData, pixelIndex, pixelColor);
		}

		try (
			OutputStream outputFile = new FileOutputStream(this.fileName);
		) {
			outputFile.write(tgaData);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
