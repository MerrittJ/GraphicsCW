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
		new ShapeDesigner().run(WINDOWED, "Designer", 0.1f);
	}

	/** Draw the shape **/
	protected void drawUnitShape() {

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
		Vertex v13 = new Vertex(0.0f, 0.0f, 0.5f);
		Vertex v14 = new Vertex(1.0f, 0.0f, 0.5f);

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
			v13.submit();
			v7.submit();
			//v5.submit();
			//v7.submit();

			GL11.glEnd();

		}

		// head right side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v11.toVector(), v14.toVector(),
			// v8.toVector()).submit();

			v11.submit();
			v10.submit();
			v8.submit();
			v14.submit();
			//v6.submit();
			//v8.submit();

			GL11.glEnd();

		}
		
		// snout right side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v11.toVector(), v14.toVector(),
			// v8.toVector()).submit();

			v14.submit();
			v6.submit();
			v4.submit();
			v3.submit();

			GL11.glEnd();

		}
		
		// snout left side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v11.toVector(), v14.toVector(),
			// v8.toVector()).submit();

			v13.submit();
			v2.submit();
			v1.submit();
			v5.submit();

			GL11.glEnd();

		}
	}
}
