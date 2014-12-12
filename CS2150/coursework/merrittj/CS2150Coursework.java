/* CS2150Coursework.java
 * merrittj - Josh Merritt
 *
 * Scene Graph:
 *  [Ry(currentPlaneAngle)]Scene origin
 *  |
 *  +-- [S(60,1,60)]Ground Plane
 *  |	|
 *  |	+-- [T(7,0,-2.6)]Bull and Pen
 *  |		|
 *  |		+-- [T(0,0,CurrentBodyX) T(-3.5,0.75,-0.75) Ry(90)]Bull
 *  |		|	|
 *  |		|	+-- [S(1,0.5,1) Rz(currentHeadAngle) T(0,0.75,-0.5) Ry(270)]Head
 *  |		|	|	|
 *  |		|	|	+-- [T(0,0.3,0.5) Ry(-45) Rz(90)]Right Horn
 *  |		|	|	|
 *  |		|	|	+-- [T(2.5,0,0.5) Rx(180), R-x(90)]Left Horn
 *  |		|	|
 *  |		|	+-- [Rx(90)]Legs
 *  |		|		|
 *  |		|		+-- [Rx(currentForeLegsAngle)]Front Right Leg
 *  |		|		|
 *  |		|		+-- [Rx(currentRearLegsAngle) T(0,2,0)]Back Left Leg
 *  |		|		|
 *  |		|		+-- [Rx(currentRearLegsAngle) T(1,2,0)]Back Right Leg
 *  |		|		|
 *  |		|		+-- [Rx(currentForeLegsAngle) T(1,0,0)]Front Left Leg
 *  |		|
 *  |		+-- Pen
 *  |			|
 *  |			+-- Pair #1 - Far Left Pole, Left Beam
 *	|			|	|
 *	|			|	+-- [T(-3,1,-2) Rx(90)]Pole
 *	|			|	|
 *	|			|	+-- [Ry(currentBeamAngle) T(-3.15,1,1)]Beam
 *	|			|
 *  |			+-- Pair #2 - Far Right Pole, Far Beam
 *  |			|	|
 *	|			|	+-- [T(3,5,-2) Rx(90)]Pole
 *	|			|	|
 *	|			|	+-- [T(-1.1,1,-2) Ry(90) S(1.5,1,1)]Beam
 *	|			|	
 *  |			+-- Pair #3 - Near Right Pole, Right Beam
 *  |			|	|
 *	|			|	+-- [T(3,5,-2) Rx(90)]Pole
 *	|			|	|
 *	|			|	+-- [T92.9,1,1)]Beam
 *	|			|
 *  |			+-- Pair #4 - Near Left Pole, Near Beam
 *  |				|
 *	|				+-- [T(-3,5,-2) Rx(90)]Pole
 *	|				|
 *	|				+-- [T(-5.1,1,-2) Ry(90) S(1.5,1,1)]Beam
 *	|
 *	+-- [Rx(90) T(0,0.35,-40) S(100,60,1)]Sky Plane
 *  
 */
package coursework.merrittj;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 * Animation of a bull in a pen. When enraged, the bull will lower it's head and charge out of the pen, breaking a fence beam.
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
 * <li>Press r to enrage the bull and see it escape
 * <li>Press the left and right cursor keys to rotate the scene for a different viewpoint
 * <li>Press space to reset the bull and pen positions
 * </ul>
 * 
 * 
 */
public class CS2150Coursework extends GraphicsLab {

	// display list id for the bull head
	private final int bullHeadList = 1;
	// display list id for the bull body
	private final int bullBodyList = 2;
	// display list id for beam
	private final int beamList = 3;
	// display list id for planes
	private final int planeList = 4;

	// current angle of the scene (about y axis)
	private float currentPlaneAngle;

	// boolean to check if bull enraged
	private boolean bullEnraged;
	// boolean to check if head is fully lowered
	private boolean headDown;

	// current X position of bull body
	private float currentBodyPositionX;
	// rest position (ie in pen)
	private float restBodyPositionX = 0.0f;

	// current angle of bull head
	private float currentHeadAngle;
	// max (ie fully down)
	private float maxHeadAngle = 90.0f;
	// rest (ie fully upright)
	private float restHeadAngle = 0.0f;

	// boolean to check if fore legs going forward (i.e true means going forward, false means going backward)
	private boolean foreLegsGoingForward;
	// current angle of fore legs
	private float currentForeLegsAngle;
	// max
	private float maxForeLegsAngle = 45.0f;
	// min
	private float minForeLegsAngle = -45.0f;
	// rest
	private float restForeLegsAngle = 0.0f;

	// boolean to check if rear legs going forward (i.e true means going forward, false means going backward)
	private boolean rearLegsGoingForward;
	// current angle of rear legs
	private float currentRearLegsAngle;
	// max
	private float maxRearLegsAngle = 45.0f;
	// min
	private float minRearLegsAngle = -45.0f;
	// rest
	private float restRearLegsAngle = 0.0f;

	// boolean to check if beam should be rotating out of the way
	private boolean beamHit;
	// current angle of beam
	private float currentBeamAngle;
	// max
	private float maxBeamAngle = -110.0f;
	// rest
	private float restBeamAngle = 0.0f;

	// id for ground plane texture
	private Texture groundTexture;
	// id for sky texture
	private Texture skyTexture;

	public static void main(String args[]) {
		new CS2150Coursework().run(WINDOWED, "Josh Merritt - Don't Wear Red",
				0.1f);
	}

	protected void initScene() throws Exception {
		// TODO: mess with lighting

		// load ground and sky textures
		groundTexture = loadTexture("coursework/merrittj/textures/grass.jpg");
		skyTexture = loadTexture("coursework/merrittj/textures/360sky.jpg");

		// initialise booleans
		bullEnraged = false;
		headDown = false;
		// initialise angles
		currentForeLegsAngle = 0.0f;
		currentRearLegsAngle = 0.0f;
		currentPlaneAngle = 0.0f;

		// global ambient light
		float globalAmbient[] = { 0.9f, 0.9f, 0.9f, 1.0f };
		// set global ambient light
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,
				FloatBuffer.wrap(globalAmbient));

		// first light for the scene is xxxxx
		float diffuse0[] = { 0.9f, 0.9f, 0.9f, 1.0f };
		// with xxx ambient
		float ambient0[] = { 0.9f, 0.9f, 0.9f, 1.0f };
		// with position above viewpoint
		float position0[] = { -10.0f, 10.0f, 0.0f, 1.0f };

		// supply properties for first light and enable
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT,FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE,FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR,FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION,FloatBuffer.wrap(position0));
		GL11.glEnable(GL11.GL_LIGHT0);

		// enable lighting calculations
		GL11.glEnable(GL11.GL_LIGHTING);
		// ensure that all normals are automatically re-normalised after transformations
		GL11.glEnable(GL11.GL_NORMALIZE);

		// prep display lists
		GL11.glNewList(bullHeadList, GL11.GL_COMPILE);
		{
			drawUnitBullHead();
		}
		GL11.glEndList();
		GL11.glNewList(bullBodyList, GL11.GL_COMPILE);
		{
			drawUnitBullBody();
		}
		GL11.glEndList();
		GL11.glNewList(beamList, GL11.GL_COMPILE);
		{
			drawUnitBeam();
		}
		GL11.glEndList();
		GL11.glNewList(planeList, GL11.GL_COMPILE);
		{
			drawUnitPlane();
		}
		GL11.glEndList();

		// disable face culling
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	protected void checkSceneInput() {

		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			bullEnraged = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			currentPlaneAngle += 0.25 * getAnimationScale();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				currentPlaneAngle -= 0.25 * getAnimationScale();
		} 
		else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {

			resetAnimations();
		}
	}

	protected void updateScene() {

		// if r pressed, lower head to 90 deg
		if (bullEnraged && (currentHeadAngle < maxHeadAngle)) {
			currentHeadAngle += 1.0f * getAnimationScale();
			if (currentHeadAngle >= maxHeadAngle) {
				headDown = true;
				foreLegsGoingForward = true;
				rearLegsGoingForward = false;
			}
///////////// increment colour red
		} 
		
		// if head is lowered, start moving bull out of pen
		else if (headDown) {
			currentBodyPositionX -= 0.1f * getAnimationScale();
			// immediately open fence when bull starts moving
			beamHit = true;
		}
		
		if (headDown) {
			
			// if fore legs are supposed to be moving forwards, increment angle
			if (foreLegsGoingForward && currentForeLegsAngle < maxForeLegsAngle) {
				currentForeLegsAngle += 1.0f * getAnimationScale();
				// if fore legs reach max forward angle, set legs to go backwards
				if (currentForeLegsAngle >= maxForeLegsAngle) {
					foreLegsGoingForward = false;
				}
			}
			
			// if fore legs are supposed to be going backwards, decrement angle
			if (!foreLegsGoingForward && currentForeLegsAngle >= minForeLegsAngle) {
				currentForeLegsAngle -= 1.0f * getAnimationScale();
				// if fore legs reach min angle, set legs to go forwards
				if (currentForeLegsAngle <= minForeLegsAngle) {
					foreLegsGoingForward = true;
				}
			}

			// if rear legs are supposed to be moving forwards, increment angle 
			if (rearLegsGoingForward && currentRearLegsAngle < maxRearLegsAngle) {
				// increment rear legs forward
				currentRearLegsAngle += 1.0f * getAnimationScale();
				// if fore legs reach max angle, set legs to go backwards
				if (currentRearLegsAngle >= maxRearLegsAngle) {
					rearLegsGoingForward = false;
				}
			}

			// if rear legs are supposed to be moving backwards, decrement angle
			if (!rearLegsGoingForward && currentRearLegsAngle > minRearLegsAngle) {
				currentRearLegsAngle -= 1.0f * getAnimationScale();
				// if rear legs reach min angle, set legs to go forwards
				if (currentRearLegsAngle <= minRearLegsAngle) {
					rearLegsGoingForward = true;
				}
			}
			
			// if beam hit and beam isn't at max angle, increment angle
			if (beamHit && currentBeamAngle > maxBeamAngle) {
				currentBeamAngle -= 2.0f * getAnimationScale();
				// if beam is at max angle, stop movement
				if (currentBeamAngle <= maxBeamAngle) {
					beamHit = false;
				}
			}
		}

	}

	protected void renderScene() {

		GL11.glPushMatrix();
		{

			// rotate according to user interaction
			GL11.glRotatef(currentPlaneAngle, 0.0f, 1.0f, 0.0f);
			
			// draw the ground plane
			GL11.glPushMatrix();
			{
				// disable lighting calculations
				GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
				GL11.glDisable(GL11.GL_LIGHTING);
				// change the geometry colour to white
				Colour.WHITE.submit();
				// enable texturing and bind ground texture
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, groundTexture.getTextureID());

				GL11.glPushMatrix();
				{
					// scale ground plane
					GL11.glScalef(60.0f, 1.0f, 60.0f);
					// draw ground plane
					GL11.glCallList(planeList);

					// disable textures and reset local lighting changes
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glPopAttrib();
				}
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				{
					//move bull and pen
					GL11.glTranslatef(7.0f, 0.0f, -2.6f);
					
					// draw bull
					GL11.glPushMatrix();
					{
						// set bull material properties since head, body, horns, and legs will all be the same
						
						// bull shininess
						float bullFrontShininess = 0.0f;
						// bull reflection
						float bullFrontSpecular[] = { 0.2f, 0.2f, 0.2f, 1.0f };
						// bull diffuse
						float bullFrontDiffuse[] = { 0.5f, 0.5f, 0.5f, 1.0f };

						
						GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS,
								bullFrontShininess);
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR,
								FloatBuffer.wrap(bullFrontSpecular));
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE,
								FloatBuffer.wrap(bullFrontDiffuse));

						// rotate body to face left
						GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
						// position head
						GL11.glTranslatef(-3.5f, 0.75f, -0.75f);
						// move bull as if charging
						GL11.glTranslatef(0.0f, 0.0f, currentBodyPositionX);
						// draw body
						GL11.glCallList(bullBodyList);

						// head
						GL11.glPushMatrix();
						{
							// rotate head to face left
							GL11.glRotatef(270.0f, 0.0f, 1.0f, 0.0f);
							// position head
							GL11.glTranslatef(0.0f, 0.75f, -0.5f);
							// rotate head down when enraged
							GL11.glRotatef(currentHeadAngle, 0.0f, 0.0f, 1.0f);
							// scale head
							GL11.glScalef(1.0f, 0.5f, 1.0f);
							// draw head;
							GL11.glCallList(bullHeadList);

							GL11.glPushMatrix();
							{
								// rotate, position then draw bull horns
								// right
								GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
								GL11.glRotatef(-45.0f, 0.0f, 1.0f, 0.0f);
								GL11.glTranslatef(0.0f, 0.3f, -2.5f);
								new Cylinder().draw(0.125f, 0.125f, 2.0f, 7, 10);

								// left
								GL11.glRotatef(90.0f, 0.0f, -1.0f, 0.0f);
								GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
								GL11.glTranslatef(2.5f, 0.0f, 0.5f);
								new Cylinder().draw(0.125f, 0.125f, 2.0f, 7, 10);
							}
							GL11.glPopMatrix();

						}
						GL11.glPopMatrix();

						GL11.glPushMatrix();
						{
							// rotate upright
							GL11.glRotatef(90.0f, 1.5f, 0.0f, 0.0f);

							GL11.glPushMatrix();
							{
								// front right
								// rotate if bull moving
								GL11.glRotatef(currentForeLegsAngle, 1.0f, 0.0f, 0.0f);
								// draw leg
								new Cylinder().draw(0.125f, 0.125f, 0.8f, 10, 10);
							}
							GL11.glPopMatrix();

							GL11.glPushMatrix();
							{
								// front left
								// position leg
								GL11.glTranslatef(1.0f, 0.0f, 0.0f);
								// rotate if bull moving
								GL11.glRotatef(currentForeLegsAngle, 1.0f, 0.0f, 0.0f);
								// draw leg
								new Cylinder().draw(0.125f, 0.125f, 0.8f, 10, 10);
							}
							GL11.glPopMatrix();

							GL11.glPushMatrix();
							{
								// back left
								// position leg
								GL11.glTranslatef(0.0f, 2.0f, 0.0f);
								// rotate if bull moving
								GL11.glRotatef(currentRearLegsAngle, 1.0f, 0.0f, 0.0f);
								// draw leg
								new Cylinder().draw(0.125f, 0.125f, 0.8f, 10, 10);
							}
							GL11.glPopMatrix();

							GL11.glPushMatrix();
							{
								// back right
								// position leg
								GL11.glTranslatef(1.0f, 2.0f, 0.0f);
								// rotate if bull moving
								GL11.glRotatef(currentRearLegsAngle, 1.0f, 0.0f, 0.0f);
								// draw leg
								new Cylinder().draw(0.125f, 0.125f, 0.8f, 10, 10);
							}
							GL11.glPopMatrix();

						}
						GL11.glPopMatrix();

					}
					GL11.glPopMatrix();

					// draw pen
					GL11.glPushMatrix();
					{
						// set all pen material properties to be the same
						// wood shininess
						float woodFrontShininess = 2.0f;
						// wood reflection
						float woodFrontSpecular[] = { 0.2f, 0.0f, 0.0f, 1.0f };
						// wood diffuse
						float woodFrontDiffuse[] = { 0.2f, 0.2f, 0.2f, 1.0f };

						// set pole material properties
						GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, woodFrontShininess);
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(woodFrontSpecular));
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(woodFrontDiffuse));

						GL11.glPushMatrix();
						{
							// pair #1 - far left pole, left beam

							// pole #1
							GL11.glPushMatrix();
							{
								// rotate pole upright
								GL11.glRotatef(90.0f, 1.5f, 0.0f, 0.0f);
								// position pole
								GL11.glTranslatef(-3.0f, 1.0f, -2.0f);
								// draw pole
								new Cylinder().draw(0.125f, 0.125f, 2.0f, 4, 10);
							}
							GL11.glPopMatrix();

							// beam #1
							GL11.glPushMatrix();
							{
								// position beam
								GL11.glTranslatef(-3.15f, 1.0f, 1.0f);
								// rotate beam when hit
								GL11.glRotatef(currentBeamAngle, 0.0f, 1.0f, 0.0f);
								// draw beam
								GL11.glCallList(beamList);
							}
							GL11.glPopMatrix();

						}
						GL11.glPopMatrix();

						GL11.glPushMatrix();
						{
							// pair #2 - far right pole, far beam

							// pole #2
							GL11.glPushMatrix();
							{
								// rotate pole upright
								GL11.glRotatef(90.0f, 1.5f, 0.0f, 0.0f);
								// position pole
								GL11.glTranslatef(3.0f, 1.0f, -2.0f);
								// draw pole
								new Cylinder().draw(0.125f, 0.125f, 2.0f, 4, 10);
							}
							GL11.glPopMatrix();

							// beam #2
							GL11.glPushMatrix();
							{
								// scale beam
								GL11.glScalef(1.5f, 1.0f, 1.0f);
								// rotate beam sideways
								GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
								// position beam
								GL11.glTranslatef(-1.1f, 1.0f, -2.0f);
								// draw beam
								GL11.glCallList(beamList);
							}
							GL11.glPopMatrix();

						}
						GL11.glPopMatrix();

						GL11.glPushMatrix();
						{
							// pair #3 - near right pole, right beam

							// pole #3
							GL11.glPushMatrix();
							{
								// rotate pole upright
								GL11.glRotatef(90.0f, 1.5f, 0.0f, 0.0f);
								// position pole
								GL11.glTranslatef(3.0f, 5.0f, -2.0f);
								// draw pole
								new Cylinder().draw(0.125f, 0.125f, 2.0f, 4, 10);
							}
							GL11.glPopMatrix();

							// beam #3
							GL11.glPushMatrix();
							{
								// position beam
								GL11.glTranslatef(2.9f, 1.0f, 1.0f);
								// draw beam
								GL11.glCallList(beamList);
							}
							GL11.glPopMatrix();

							GL11.glPushMatrix();
							{
								// pair #4 - near left pole, near beam

								// pole #4
								GL11.glPushMatrix();
								{
									// rotate pole upright
									GL11.glRotatef(90.0f, 1.5f, 0.0f, 0.0f);
									// position pole
									GL11.glTranslatef(-3.0f, 5.0f, -2.0f);
									// draw pole
									new Cylinder().draw(0.125f, 0.125f, 2.0f, 4, 10);
								}
								GL11.glPopMatrix();

								// beam #4
								GL11.glPushMatrix();
								{
									// scale beam
									GL11.glScalef(1.5f, 1.0f, 1.0f);
									// rotate beam sideways
									GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
									// position beam
									GL11.glTranslatef(-5.1f, 1.0f, -2.0f);
									// draw beam
									GL11.glCallList(beamList);
								}
								GL11.glPopMatrix();
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
					}
					GL11.glPopMatrix();

				}

				GL11.glPopMatrix();
			}
			GL11.glPopMatrix();

		}
		GL11.glPopMatrix();

		// draw the sky plane
		GL11.glPushMatrix();
		{
			// disable lighting calculations
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white
			Colour.WHITE.submit();
			// enable texturing and bind sky texture
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, skyTexture.getTextureID());

			// scale sky plane
			GL11.glScalef(100.0f, 60.0f, 1.0f);
			// position sky plane
			GL11.glTranslatef(0.0f, 0.35f, -40.0f);
			// rotate sky plane 90 deg
			GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			// draw sky plane
			GL11.glCallList(planeList);

			// disable textures and reset local lighting changes
			// GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	// reset any variables altered during animation
	private void resetAnimations() {
		bullEnraged = false;
		headDown = false;
		beamHit = false;
		currentBodyPositionX = restBodyPositionX;
		currentHeadAngle = restHeadAngle;
		currentForeLegsAngle = restForeLegsAngle;
		currentRearLegsAngle = restRearLegsAngle;
		currentBeamAngle = restBeamAngle;

	}

	protected void setSceneCamera() {
		super.setSceneCamera();

		// set camera up and back from origin for better viewpoint
		GLU.gluLookAt(0.0f, 10.0f, 30.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
	}

	protected void cleanupScene() {
	}

	protected void drawUnitBullHead() {

		Vertex v1 = new Vertex(-1.0f, 0.0f, -0.5f);
		Vertex v2 = new Vertex(-1.0f, 0.5f, -0.5f);
		Vertex v3 = new Vertex(-1.0f, 0.5f, 0.5f);
		Vertex v4 = new Vertex(-1.0f, 0.0f, 0.5f);
		Vertex v5 = new Vertex(-0.5f, 0.5f, -0.5f);
		Vertex v6 = new Vertex(-0.5f, 0.0f, -0.5f);
		Vertex v7 = new Vertex(-0.5f, 0.0f, 0.5f);
		Vertex v8 = new Vertex(-0.5f, 0.5f, 0.5f);
		Vertex v9 = new Vertex(-0.5f, 1.5f, 0.5f);
		Vertex v10 = new Vertex(-0.5f, 1.5f, -0.5f);
		Vertex v11 = new Vertex(0.0f, 1.5f, -0.5f);
		Vertex v12 = new Vertex(0.0f, 1.5f, 0.5f);
		Vertex v13 = new Vertex(0.0f, 0.0f, -0.5f);
		Vertex v14 = new Vertex(0.0f, 0.0f, 0.5f);

		// front of nose
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v1.toVector(), v2.toVector(), v3.toVector(),
			 v4.toVector()).submit();

			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();

		}
		GL11.glEnd();

		// top of nose
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v2.toVector(), v5.toVector(), v8.toVector(),
			 v3.toVector()).submit();

			v2.submit();
			v5.submit();
			v8.submit();
			v3.submit();

		}
		GL11.glEnd();

		// top front of face
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v5.toVector(), v10.toVector(), v9.toVector(),
			 v8.toVector()).submit();

			v5.submit();
			v10.submit();
			v9.submit();
			v8.submit();

			GL11.glEnd();

		}

		// top of head
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v10.toVector(), v11.toVector(), v12.toVector(),
			 v9.toVector()).submit();

			v10.submit();
			v11.submit();
			v12.submit();
			v9.submit();

			GL11.glEnd();

		}

		// back of head
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v13.toVector(), v14.toVector(), v12.toVector(),
			 v11.toVector()).submit();

			v13.submit();
			v14.submit();
			v12.submit();
			v11.submit();

			GL11.glEnd();

		}

		// bottom of head
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v14.toVector(), v13.toVector(), v6.toVector(),
			 v12.toVector()).submit();

			v14.submit();
			v13.submit();
			v6.submit();
			v7.submit();

			GL11.glEnd();

		}

		// bottom of snout
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v6.toVector(), v1.toVector(), v4.toVector(),
			 v7.toVector()).submit();

			v6.submit();
			v1.submit();
			v4.submit();
			v7.submit();

			GL11.glEnd();

		}

		// head far side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v12.toVector(), v14.toVector(), v7.toVector(),
			 v9.toVector()).submit();

			v12.submit();
			v14.submit();
			v7.submit();
			v9.submit();

			GL11.glEnd();

		}

		// head near side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v11.toVector(), v10.toVector(), v6.toVector(),
			 v13.toVector()).submit();

			v11.submit();
			v10.submit();
			v6.submit();
			v13.submit();

			GL11.glEnd();

		}

		// snout near side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v6.toVector(), v5.toVector(), v2.toVector(),
			 v1.toVector()).submit();

			v6.submit();
			v5.submit();
			v2.submit();
			v1.submit();

			GL11.glEnd();

		}

		// snout far side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v4.toVector(), v3.toVector(), v8.toVector(),
			 v7.toVector()).submit();

			v4.submit();
			v3.submit();
			v8.submit();
			v7.submit();

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

		// right side of the body
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v6.toVector(), v5.toVector(), v4.toVector(),
			 v3.toVector()).submit();

			v6.submit();
			v5.submit();
			v4.submit();
			v3.submit();

			GL11.glEnd();

		}
		// left side of the body
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v7.toVector(),
			 v8.toVector()).submit();

			v1.submit();
			v2.submit();
			v7.submit();
			v8.submit();

			GL11.glEnd();
		}
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
			 new Normal(v1.toVector(), v2.toVector(), v3.toVector(),
			 v4.toVector()).submit();

			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();

		}
		GL11.glEnd();

		// top of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v4.toVector(), v3.toVector(), v5.toVector(),
					 v8.toVector()).submit();

			v4.submit();
			v3.submit();
			v5.submit();
			v8.submit();

		}
		GL11.glEnd();

		// back of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v5.toVector(), v6.toVector(), v7.toVector(),
			 v8.toVector()).submit();

			v5.submit();
			v6.submit();
			v7.submit();
			v8.submit();

			GL11.glEnd();

		}

		// bottom of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v6.toVector(), v2.toVector(), v1.toVector(),
			 v7.toVector()).submit();

			v6.submit();
			v2.submit();
			v1.submit();
			v7.submit();

			GL11.glEnd();

		}

		// right side of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v3.toVector(), v2.toVector(), v6.toVector(),
			 v5.toVector()).submit();

			v3.submit();
			v2.submit();
			v6.submit();
			v5.submit();

			GL11.glEnd();

		}

		// left side of beam
		GL11.glBegin(GL11.GL_POLYGON);
		{
			 new Normal(v8.toVector(), v7.toVector(), v1.toVector(),
			 v4.toVector()).submit();

			v8.submit();
			v7.submit();
			v1.submit();
			v4.submit();

			GL11.glEnd();

		}
	}

	protected void drawUnitPlane() {

		Vertex v1 = new Vertex(-0.5f, 0.0f, -0.5f);
		Vertex v2 = new Vertex(0.5f, 0.0f, -0.5f);
		Vertex v3 = new Vertex(0.5f, 0.0f, 0.5f);
		Vertex v4 = new Vertex(-0.5f, 0.0f, 0.5f);

		// draw plane
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v3.toVector(), v2.toVector(),
					v1.toVector()).submit();

			GL11.glTexCoord2f(0.0f, 0.0f);
			v4.submit();

			GL11.glTexCoord2f(1.0f, 0.0f);
			v3.submit();

			GL11.glTexCoord2f(1.0f, 1.0f);
			v2.submit();

			GL11.glTexCoord2f(0.0f, 1.0f);
			v1.submit();
		}
		GL11.glEnd();
	}
}
