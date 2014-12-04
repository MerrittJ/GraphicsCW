package Designer;

import org.lwjgl.opengl.GL11;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * The shape designer is a utility class which assits you with the design of a
 * new 3D object. Replace the content of the drawUnitShape() method with your
 * own code to creates vertices and draw the faces of your object.
 * 
 * You can use the following keys to change the view: - TAB switch between
 * vertex, wireframe and full polygon modes - UP move the shape away from the
 * viewer - DOWN move the shape closer to the viewer - X rotate the camera
 * around the x-axis (clockwise) - Y or C rotate the camera around the y-axis
 * (clockwise) - Z rotate the camera around the z-axis (clockwise) - SHIFT keep
 * pressed when rotating to spin anti-clockwise - A Toggle colour (only if using
 * submitNextColour() to specify colour) - SPACE reset the view to its initial
 * settings
 * 
 * @author Remi Barillec
 * 
 */
public class ShapeDesigner extends AbstractDesigner {

	/** Main method **/
	public static void main(String args[]) {
		new ShapeDesigner().run(WINDOWED, "Designer", 0.01f);
	}

	/** Draw the shape **/
	protected void drawUnitShape() {
		
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
//			new Normal(v4.toVector(), v3.toVector(), v2.toVector(),
//					v1.toVector()).submit();

			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();

		}
		GL11.glEnd();

		// top of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
//			new Normal(v6.toVector(), v4.toVector(), v1.toVector(),
//					v5.toVector()).submit();

			v4.submit();
			v3.submit();
			v5.submit();
			v8.submit();

		}
		GL11.glEnd();

		// back of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
//			new Normal(v8.toVector(), v6.toVector(), v5.toVector(),
//					v7.toVector()).submit();

			v5.submit();
			v6.submit();
			v7.submit();
			v8.submit();

			GL11.glEnd();

		}

		// bottom of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
//			new Normal(v10.toVector(), v8.toVector(), v7.toVector(),
//					v9.toVector()).submit();

			v6.submit();
			v2.submit();
			v1.submit();
			v7.submit();

			GL11.glEnd();

		}

		// right side of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
//			new Normal(v11.toVector(), v12.toVector(), v9.toVector(),
//					v10.toVector()).submit();

			v3.submit();
			v2.submit();
			v6.submit();
			v5.submit();

			GL11.glEnd();

		}

		// left side of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
//			new Normal(v11.toVector(), v3.toVector(), v2.toVector(),
//					v12.toVector()).submit();

			v8.submit();
			v7.submit();
			v1.submit();
			v4.submit();

			GL11.glEnd();

		}

	}
}
