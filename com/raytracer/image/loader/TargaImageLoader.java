package com.raytracer.image.loader;

import com.raytracer.image.Image;
import com.raytracer.math.vector.Vector4;

import java.io.FileInputStream;

public class TargaImageLoader implements ImageLoader {
	private static final int headerSize = 18;

	private boolean isColorMapped(byte[] tgaData) {
		byte colorMapType = tgaData[0x01];

		return colorMapType != 0;
	}

	private boolean isCompressed(byte[] tgaData) {
		byte imageType = tgaData[0x02];

		return (imageType != 1 && imageType != 2 && imageType != 3);
	}

	private boolean is16BitColor(byte[] tgaData) {
		byte pixelDepth = tgaData[0x10];

		return (pixelDepth == 15 || pixelDepth == 16);
	}

	private String validateImageData(byte[] tgaData, String filePath) {
		if (isColorMapped(tgaData)) {
			return ("Color mappped images not supported: " + filePath);
		}

		if (isCompressed(tgaData)) {
			return ("Compressed images not supported: " + filePath);
		}

		if (is16BitColor(tgaData)) {
			return ("16-bit color images not supported: " + filePath);
		}

		return null;
	}

	private Vector4 parseColor(byte[] pixelData) {
		if (pixelData.length == 1) {
			float luminosity = (pixelData[0] & 0xFF) / 255.f;

			return new Vector4(luminosity, luminosity, luminosity, 1);
		}
		else {
			float blue = (pixelData[0] & 0xFF) / 255.f;
			float green = (pixelData[1] & 0xFF) / 255.f;
			float red = (pixelData[2] & 0xFF) / 255.f;

			if (pixelData.length == 3) {
				return new Vector4(red, green, blue, 1);
			}
			else {
				float alpha = (pixelData[3] & 0xFF) / 255.f;

				return new Vector4(red, green, blue, alpha);
			}
		}
	}

	private Image parseImage(byte[] tgaData) {
		byte imageType = tgaData[0x02];
		byte pixelDepth = tgaData[0x10];
		int bytesPerPixel = 4;

		if (imageType == 3) { // 1-channel black and white
			bytesPerPixel = 1;
		}
		else if (pixelDepth == 24) { // 3-channel RGB without alpha
			bytesPerPixel = 3;
		}

		int identifierHeaderSize = tgaData[0x00] & 0xFF;
		int width = (tgaData[0x0C] & 0xFF) | (tgaData[0x0D] & 0xFF) << 8;
		int height = (tgaData[0x0E] & 0xFF) | (tgaData[0x0F] & 0xFF) << 8;

		Image result = new Image(width, height);
		int pixelCount = width * height;
		byte[] pixelData = new byte[bytesPerPixel];

		for (int pixelIndex = 0; pixelIndex < pixelCount; pixelIndex++) {
			int x = pixelIndex % width;
			int y = pixelIndex / width;

			int dataIndex = headerSize + identifierHeaderSize + pixelIndex * bytesPerPixel;

			System.arraycopy(tgaData, dataIndex, pixelData, 0, bytesPerPixel);

			result.setPixel(x, y, parseColor(pixelData));
		}

		return result;
	}

	public Image loadImage(String filePath) {
		byte[] tgaData = null;

		try (
			FileInputStream inputFile = new FileInputStream(filePath);
		) {
			tgaData = inputFile.readAllBytes();
		}
		catch (Exception exception) {
			exception.printStackTrace();

			return null;
		}

		String errorMessage = validateImageData(tgaData, filePath);

		if (errorMessage != null) {
			System.err.println(errorMessage);

			return null;
		}

		return parseImage(tgaData);
	}
}
