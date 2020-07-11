compile:
	javac -cp . com/raytracer/Setup.java

run: compile
	java com.raytracer.Setup

clean:
	find com -type f -name "*.class" -delete
