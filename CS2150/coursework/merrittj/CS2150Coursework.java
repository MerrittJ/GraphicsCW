/* CS2150Coursework.java
 * TODO: put your university username and full name here
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *
 *  TODO: Provide a scene graph for your submission
 */
package coursework.merrittj;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 * TODO: Briefly describe your submission here
 * 
 * <p>
 * Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis,
 * respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down
 * cursor keys to increase or decrease the viewpoint's distance from the scene
 * origin
 * </ul>
 * TODO: Add any additional controls for your sample to the list above
 * 
 */
public class CS2150Coursework extends GraphicsLab {
	// TODO: Feel free to change the window title and default animation scale
	// here
	public static void main(String args[]) {
		new CS2150Coursework().run(WINDOWED, "CS2150 Coursework Submission",
				0.01f);
	}

	protected void initScene() throws Exception {// TODO: Initialise your
													// resources here - might
													// well call other methods
													// you write.
	}

	protected void checkSceneInput() {// TODO: Check for keyboard and mouse
										// input here
	}

	protected void updateScene() {
		// TODO: Update your scene variables here - remember to use the current
		// animation scale value
		// (obtained via a call to getAnimationScale()) in your modifications so
		// that your animations
		// can be made faster or slower depending on the machine you are working
		// on
	}

	protected void renderScene() {// TODO: Render your scene here - remember
									// that a scene graph will help you write
									// this method!
									// It will probably call a number of other
									// methods you will write.
	}

	protected void setSceneCamera() {
		// call the default behaviour defined in GraphicsLab. This will set a
		// default perspective projection
		// and default camera settings ready for some custom camera positioning
		// below...
		super.setSceneCamera();

		// TODO: If it is appropriate for your scene, modify the camera's
		// position and orientation here
		// using a call to GL11.gluLookAt(...)
	}

	protected void cleanupScene() {// TODO: Clean up your resources here
	}

	protected void drawUnitBullHead() {
		Vertex v1 = new Vertex(0.0f, 0.5f, 0.0f);
		Vertex v2 = new Vertex(0.0f, 0.0f, 0.0f);
		Vertex v3 = new Vertex(1.0f, 0.0f, 0.0f);
		Vertex v4 = new Vertex(1.0f, 0.5f, 0.0f);
		Vertex v5 = new Vertex(0.0f, 0.5f, 0.5f);
		Vertex v6 = new Vertex(1.0f, 0.5f, 0.5f);
		Vertex v7 = new Vertex(0.0f, 1.5f, 0.5f);
		Vertex v8 = new Vertex(1.0f, 1.5f, 0.5f);
		Vertex v9 = new Vertex(0.0f, 1.5f, 1.0f);
		Vertex v10 = new Vertex(1.0f, 1.5f, 1.0f);
		Vertex v11 = new Vertex(1.0f, 0.0f, 1.0f);
		Vertex v12 = new Vertex(1.0f, 0.5f, 0.5f);

		// front of nose
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v4.toVector(), v3.toVector(), v2.toVector(),
			// v1.toVector()).submit();

			v4.submit();
			v3.submit();
			v2.submit();
			v1.submit();

		}
		GL11.glEnd();

		// top of nose
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v6.toVector(), v4.toVector(), v1.toVector(),
			// v5.toVector()).submit();

			v6.submit();
			v4.submit();
			v1.submit();
			v5.submit();

		}
		GL11.glEnd();

		// top of face
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v8.toVector(), v6.toVector(), v5.toVector(),
			// v7.toVector()).submit();

			v8.submit();
			v6.submit();
			v5.submit();
			v7.submit();

			GL11.glEnd();

		}

		// top of head
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v8.toVector(), v7.toVector(),
			// v9.toVector()).submit();

			v10.submit();
			v8.submit();
			v7.submit();
			v9.submit();

			GL11.glEnd();

		}

		// back of head
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v11.toVector(), v12.toVector(), v9.toVector(),
			// v10.toVector()).submit();

			v10.submit();
			v11.submit();
			v12.submit();
			v9.submit();

			GL11.glEnd();

		}

		// bottom of head
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v11.toVector(), v3.toVector(), v2.toVector(),
			// v12.toVector()).submit();

			v11.submit();
			v3.submit();
			v2.submit();
			v12.submit();

			GL11.glEnd();

		}

		// head left side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v9.toVector(), v12.toVector(), v13.toVector(),
			// v7.toVector()).submit();

			v9.submit();
			v12.submit();
			v2.submit();
			v1.submit();
			v5.submit();
			v7.submit();

			GL11.glEnd();

		}

		// head right side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v11.toVector(), v14.toVector(),
			// v8.toVector()).submit();

			v10.submit();
			v11.submit();
			v3.submit();
			v4.submit();
			v6.submit();
			v8.submit();

			GL11.glEnd();

		}
	}

	protected void drawUnitBullBody() {

		Vertex v1 = new Vertex(0.0f, 0.0f, 0.0f);
		Vertex v2 = new Vertex(0.0f, 1.0f, 0.0f);
		Vertex v3 = new Vertex(1.0f, 1.0f, 0.0f);
		Vertex v4 = new Vertex(1.0f, 0.0f, 0.0f);
		Vertex v5 = new Vertex(1.0f, 0.0f, 2.0f);
		Vertex v6 = new Vertex(1.0f, 1.0f, 2.0f);
		Vertex v7 = new Vertex(0.0f, 1.0f, 2.0f);
		Vertex v8 = new Vertex(0.0f, 0.0f, 2.0f);
		Vertex v9 = new Vertex(0.5f, 1.5f, 0.0f);
		Vertex v10 = new Vertex(0.5f, 1.0f, 2.0f);

		// near face of the body
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v3.toVector(), v2.toVector(),
					v1.toVector()).submit();

			v4.submit();
			v3.submit();
			v2.submit();
			v1.submit();

		}
		GL11.glEnd();

		// rear face of the body
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v7.toVector(), v6.toVector(),
					v5.toVector()).submit();

			v8.submit();
			v7.submit();
			v6.submit();
			v5.submit();

		}
		GL11.glEnd();

		// top right face of the body
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v6.toVector(), v10.toVector(),
					v9.toVector()).submit();

			v3.submit();
			v6.submit();
			v10.submit();
			v9.submit();

			GL11.glEnd();

		}

		// top left face of the body
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v7.toVector(), v10.toVector(),
					v9.toVector()).submit();

			v2.submit();
			v7.submit();
			v10.submit();
			v9.submit();

			GL11.glEnd();

		}

		// bottom of the body
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v1.toVector(), v8.toVector(),
					v5.toVector()).submit();

			v4.submit();
			v1.submit();
			v8.submit();
			v5.submit();

			GL11.glEnd();

		}

		// front top of the body
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v3.toVector(), v9.toVector()).submit();

			v2.submit();
			v3.submit();
			v9.submit();

			GL11.glEnd();

		}
	}

	protected void drawUnitPole() {

	}

	protected void drawUnitBeam() {

		Vertex v1 = new Vertex(0.0f, 0.0f, 0.0f);
		Vertex v2 = new Vertex(0.25f, 0.0f, 0.0f);
		Vertex v3 = new Vertex(0.25f, 0.75f, 0.0f);
		Vertex v4 = new Vertex(0.0f, 0.75f, 0.0f);
		Vertex v5 = new Vertex(0.25f, 0.75f, 4.0f);
		Vertex v6 = new Vertex(0.25f, 0.0f, 4.0f);
		Vertex v7 = new Vertex(0.0f, 0.0f, 4.0f);
		Vertex v8 = new Vertex(0.0f, 0.75f, 4.0f);

		// front of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v4.toVector(), v3.toVector(), v2.toVector(),
			// v1.toVector()).submit();

			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();

		}
		GL11.glEnd();

		// top of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v6.toVector(), v4.toVector(), v1.toVector(),
			// v5.toVector()).submit();

			v4.submit();
			v3.submit();
			v5.submit();
			v8.submit();

		}
		GL11.glEnd();

		// back of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v8.toVector(), v6.toVector(), v5.toVector(),
			// v7.toVector()).submit();

			v5.submit();
			v6.submit();
			v7.submit();
			v8.submit();

			GL11.glEnd();

		}

		// bottom of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v8.toVector(), v7.toVector(),
			// v9.toVector()).submit();

			v6.submit();
			v2.submit();
			v1.submit();
			v7.submit();

			GL11.glEnd();

		}

		// right side of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v11.toVector(), v12.toVector(), v9.toVector(),
			// v10.toVector()).submit();

			v3.submit();
			v2.submit();
			v6.submit();
			v5.submit();

			GL11.glEnd();

		}

		// left side of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v11.toVector(), v3.toVector(), v2.toVector(),
			// v12.toVector()).submit();

			v8.submit();
			v7.submit();
			v1.submit();
			v4.submit();

			GL11.glEnd();

		}

	}

	protected void drawUnitPlane() {

		Vertex v1 = new Vertex(0.0f, 0.0f, 0.0f);
		Vertex v2 = new Vertex(1.0f, 0.0f, 0.0f);
		Vertex v3 = new Vertex(1.0f, 0.0f, 1.0f);
		Vertex v4 = new Vertex(0.0f, 0.0f, 1.0f);

		// top of plane
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();

		}
		GL11.glEnd();
	}

	protected void drawUnitSun() {

	}
}
