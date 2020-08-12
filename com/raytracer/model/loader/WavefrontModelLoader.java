package com.raytracer.model.loader;

import com.raytracer.math.vector.Vector2;
import com.raytracer.math.vector.Vector3;
import com.raytracer.model.Model;
import com.raytracer.model.Triangle;
import com.raytracer.model.Vertex;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class WavefrontModelLoader implements ModelLoader {
	private static final String POSITION_LINE_PATTERN = "^[ \\t]*v[ \\t].*";
	private static final String TEXTURE_COORDINATE_LINE_PATTERN = "^[ \\t]*vt[ \\t].*";
	private static final String NORMAL_LINE_PATTERN = "^[ \\t]*vn[ \\t].*";
	private static final String FACE_LINE_PATTERN = "^[ \\t]*f[ \\t].*";

	private Vector3 parsePosition(String fileLine) { // may need to handle 4-component positions
		String[] tokens = fileLine.split("[ \\t]");

		return new Vector3(
			Float.parseFloat(tokens[1]),
			Float.parseFloat(tokens[2]),
			Float.parseFloat(tokens[3])
		);
	}

	private Vector2 parseTextureCoordinate(String fileLine) { // may need to handle 3-component coordinates
		String[] tokens = fileLine.split("[ \\t]");

		return new Vector2(
			Float.parseFloat(tokens[1]),
			Float.parseFloat(tokens[2])
		);
	}

	private Vector3 parseNormal(String fileLine) {
		String[] tokens = fileLine.split("[ \\t]");

		return new Vector3(
			Float.parseFloat(tokens[1]),
			Float.parseFloat(tokens[2]),
			Float.parseFloat(tokens[3])
		);
	}

	private void parseFace(
		String fileLine,
		Model model,
		ArrayList<Vector3> positions,
		ArrayList<Vector2> textureCoordinates,
		ArrayList<Vector3> normals
	) {
		String[] tokens = fileLine.split("[ \\t]");

		for (int vertexIndex = 0; vertexIndex < tokens.length - 3; vertexIndex++) {
			model.addTriangle(
				new Triangle(
					this.parseVertex(tokens[1], positions, textureCoordinates, normals),
					this.parseVertex(tokens[vertexIndex + 2], positions, textureCoordinates, normals),
					this.parseVertex(tokens[vertexIndex + 3], positions, textureCoordinates, normals)
				)
			);
		}
	}

	private Vertex parseVertex( // potentially look for duplicate vertices ("1/2/3" == "1/2/3")
		String vertexToken,
		ArrayList<Vector3> positions,
		ArrayList<Vector2> textureCoordinates,
		ArrayList<Vector3> normals
	) {
		String[] tokens = vertexToken.split("\\/");

		Vertex result = new Vertex(positions.get(Integer.parseInt(tokens[0]) - 1));

		if (tokens.length > 1 && tokens[1].length() > 0) {
			result.setTextureCoordinates(0, textureCoordinates.get(Integer.parseInt(tokens[1]) - 1));
		}

		if (tokens.length > 2 && tokens[2].length() > 0) {
			result.setNormal(normals.get(Integer.parseInt(tokens[2]) - 1));
		}

		return result;
	}

	public Model loadModel(String filePath) {
		Model result = new Model();

		ArrayList<Vector3> positions = new ArrayList<>();
		ArrayList<Vector2> textureCoordinates = new ArrayList<>();
		ArrayList<Vector3> normals = new ArrayList<>();

		try {
			File wavefrontFile = new File(filePath);
			Scanner fileScanner = new Scanner(wavefrontFile);

			while (fileScanner.hasNextLine()) {
				String fileLine = fileScanner.nextLine();

				if (fileLine.matches(POSITION_LINE_PATTERN)) {
					positions.add(this.parsePosition(fileLine));
				}
				else if (fileLine.matches(TEXTURE_COORDINATE_LINE_PATTERN)) {
					textureCoordinates.add(this.parseTextureCoordinate(fileLine));
				}
				else if (fileLine.matches(NORMAL_LINE_PATTERN)) {
					normals.add(this.parseNormal(fileLine));
				}
				else if (fileLine.matches(FACE_LINE_PATTERN)) {
					this.parseFace(fileLine, result, positions, textureCoordinates, normals);
				}
			}

			fileScanner.close();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		return result;
	}
}
