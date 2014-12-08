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
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
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
	
	//display list id for the bull head
    private final int bullHeadList = 1;
    //display list id for the bull body
    private final int bullBodyList  = 2;
    //display list id for beam
    private final int beamList = 3;
    //display list id for planes
    private final int planeList  = 7;
    
    //boolean to check if bull enraged
    private boolean bullEnraged;
    //boolean to check if headDown
    private boolean headDown;
    
    //current Y position of bull body
    private int currentBodyPositionY;
    //max
    private int maxBodyPositionY;
    //res
    private int restBodyPositionY = 0;
    
    //current angle of bull head
    private int currentHeadPosition;
    //max (ie fully down)
    private int maxHeadPosition;
    //rest
    private int restHeadPosition = 0;
    
  //boolean to check if fore legs going forward (i.e true means going forward, false means going backward
    private boolean foreLegsGoingForward;
    //current angle of fore legs
    private int currentForeLegsPosition;
    //max
    private int maxForeLegsPosition = 0;
    //min
    private int minForeLegsPosition = 0;
    //rest
    private int restForeLegsPosition = 0;
    
    //boolean to check if rear legs going forward (i.e true means going forward, false means going backward
    private boolean rearLegsGoingForward;
    //current angle of rear legs
    private int currentRearLegsPosition;
    //max
    private int maxRearLegsPosition = 0;
    //min
    private int minRearLegsPosition = 0;
    //rest
    private int restRearLegsPosition = 0;
    
    //boolean to check if beam is hit
    private boolean beamHit;
    //current angle of beam
    private int currentBeamPosition;
    //max 
    private int maxBeamPosition;
    //rest
    private int restBeamPosition;

    
    //id for ground plane texture
    private Texture groundTexture;
    //
    
	public static void main(String args[]) {
		new CS2150Coursework().run(WINDOWED, "CS2150 Coursework Submission",
				0.01f);
	}

	protected void initScene() throws Exception {
		// TODO: finish texture loading and mess with lighting
		
		//load textures
		groundTexture = loadTexture("coursework/merrittj/textures/grass.bmp");
		
		//global ambient light
		float globalAmbient[] = {0.9f, 0.9f, 0.9f, 1.0f};
		//set global ambient light
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));
		
		//first light for the scene is xxxxx
		float diffuse0[] = {0.8f, 0.8f, 0.8f, 1.0f};
		//with xxx ambient
		float ambient0[] = {0.05f, 0.05f, 0.05f, 1.0f};
		//with position above viewpoint?
		float position0[] = {0.0f, 10.0f, 0.0f, 1.0f};
		
		//supply properties for first light and enable
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        GL11.glEnable(GL11.GL_LIGHT0);
        
        //enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        //ensure that all normals are automatically re-normalised after transformations
        GL11.glEnable(GL11.GL_NORMALIZE);
        
        // prep display lists
        GL11.glNewList(bullHeadList,GL11.GL_COMPILE);
        {   drawUnitBullHead();
        }
        GL11.glEndList();
        GL11.glNewList(bullBodyList,GL11.GL_COMPILE);
        {   drawUnitBullBody();
        }
        GL11.glEndList();
        GL11.glNewList(beamList,GL11.GL_COMPILE);
        {   drawUnitBeam();
        }
        GL11.glEndList();
        GL11.glNewList(planeList, GL11.GL_COMPILE);
        {   drawUnitPlane();
        }
        GL11.glEndList();
	}

	protected void checkSceneInput() {
		// TODO: Check for keyboard and mouse
		// input here
		
		if(Keyboard.isKeyDown(Keyboard.KEY_R))
        {   
			bullEnraged = true;
        }
		else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {   
			resetAnimations();
        }
	}

	protected void updateScene() {
		// TODO: Update your scene variables here - remember to use the current
		// animation scale value
		// (obtained via a call to getAnimationScale()) in your modifications so
		// that your animations
		// can be made faster or slower depending on the machine you are working
		// on
		
		if (bullEnraged && currentHeadPosition < maxHeadPosition) {
			//increment currentHeadPosition
			//increment colour red
		}
		else if(headDown && currentBodyPositionY < maxBodyPositionY) {
			//increment currentBodyPosition and move legs
		}
		else if(foreLegsGoingForward && currentForeLegsPosition > maxForeLegsPosition) {
			//increment fore legs forward
		}
		else if(rearLegsGoingForward && currentRearLegsPosition > maxRearLegsPosition) {
			//increment rear legs forward
		}
		else if(!foreLegsGoingForward && currentForeLegsPosition < maxForeLegsPosition) {
			//increment fore legs backward
		}
		else if(!rearLegsGoingForward && currentRearLegsPosition < maxRearLegsPosition) {
			//increment rear legs backward
		}
		else if(beamHit) {
			//rotate beam about a touching pole
		}
		
	}

	protected void renderScene() {
		// TODO: Render your scene here - remember
		// that a scene graph will help you write
		// this method!
		// It will probably call a number of other
		// methods you will write.
		
		//draw the ground plane
        GL11.glPushMatrix();
        {
            //disable lighting calculations 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            //change the geometry colour to white
            Colour.WHITE.submit();
            //enable texturing and bind ground texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTexture.getTextureID());
            
            //position ground plane
            GL11.glTranslatef(0.0f,1.0f,-10.0f);
            //scale "
            GL11.glScalef(25.0f, 1.0f, 20.0f);
            //draw "
            GL11.glCallList(planeList);
            
            //disable textures and reset local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
      //draw the sky plane
        GL11.glPushMatrix();
        {
            //disable lighting calculations 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            //change the geometry colour to cyan
            Colour.CYAN.submit();
            //enable texturing and bind ground texture
//            GL11.glEnable(GL11.GL_TEXTURE_2D);
//            GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTexture.getTextureID());
            
            //position sky plane
            GL11.glTranslatef(0.0f,-1.0f,-10.0f);
            //scale "
            GL11.glScalef(25.0f, 1.0f, 20.0f);
            //rotate " 90 deg
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            //draw "
            GL11.glCallList(planeList);
            
            //disable textures and reset local lighting changes
            //GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
     // draw sun
        GL11.glPushMatrix();
        {
            //sun shininess
            float sunFrontShininess  = 2.0f;
            //sun reflection
            float sunFrontSpecular[] = {0.6f, 0.6f, 0.6f, 1.0f};
            //sun diffuse
            float sunFrontDiffuse[]  = {0.6f, 0.6f, 0.6f, 1.0f};

            //set sun material properties
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, sunFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(sunFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(sunFrontDiffuse));

            //position then draw sun
            GL11.glTranslatef(4.0f, 20.0f, -19.0f);
            new Sphere().draw(0.5f,10,10);
        }
        GL11.glPopMatrix();

        //draw poles
        GL11.glPushMatrix();
        {
            //pole shininess
            float poleFrontShininess  = 2.0f;
            //pole reflection
            float poleFrontSpecular[] = {0.6f, 0.6f, 0.6f, 1.0f};
            //pole diffuse
            float poleFrontDiffuse[]  = {0.6f, 0.6f, 0.6f, 1.0f};

            //set pole material properties
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, poleFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(poleFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(poleFrontDiffuse));

            //TODO
            //position then draw pole #1
            GL11.glTranslatef(4.0f, 20.0f, -19.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10);
            //position then draw pole #2
            GL11.glTranslatef(4.0f, 20.0f, -19.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10);
            //position then draw pole #3
            GL11.glTranslatef(4.0f, 20.0f, -19.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10);
            //position then draw pole #4
            GL11.glTranslatef(4.0f, 20.0f, -19.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10);
            
        }
        GL11.glPopMatrix();
        
      //draw beams
        GL11.glPushMatrix();
        {
            //beam shininess
            float beamFrontShininess  = 2.0f;
            //beam reflection
            float beamFrontSpecular[] = {0.6f, 0.6f, 0.6f, 1.0f};
            //beam diffuse
            float beamFrontDiffuse[]  = {0.6f, 0.6f, 0.6f, 1.0f};

            //set beam material properties
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, beamFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(beamFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(beamFrontDiffuse));

            //TODO
            //position then draw beam #1
            GL11.glTranslatef(4.0f, 10.0f, -19.0f);
            GL11.glCallList(beamList);           
            //position then draw beam #2
            GL11.glTranslatef(4.0f, 20.0f, -19.0f);
            GL11.glCallList(beamList);            
            //position then draw beam #3
            GL11.glTranslatef(4.0f, 30.0f, -19.0f);
            GL11.glCallList(beamList);            
            //position then draw beam #4
            GL11.glTranslatef(4.0f, 40.0f, -19.0f);
            GL11.glCallList(beamList);            
        }
        GL11.glPopMatrix();
        
        //draw bull
        GL11.glPushMatrix();
        {
            //bull shininess
            float bullFrontShininess  = 2.0f;
            //bull reflection
            float bullFrontSpecular[] = {0.6f, 0.6f, 0.6f, 1.0f};
            //bull diffuse
            float bullFrontDiffuse[]  = {0.6f, 0.6f, 0.6f, 1.0f};

            //set bull material properties since head, body, horns, and legs will all be the same
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, bullFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(bullFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(bullFrontDiffuse));

            //TODO
            //position then draw bull head
            GL11.glTranslatef(4.0f, 10.0f, -19.0f);
            GL11.glCallList(bullHeadList); 
            //position then draw bull body
            GL11.glTranslatef(4.0f, 10.0f, -19.0f);
            GL11.glCallList(bullBodyList); 
            
            //position then draw bull horns
            //right 
            GL11.glTranslatef(4.0f, 10.0f, -19.0f);
            GL11.glRotatef(35.0f, 0.0f, 1.0f, 0.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10); 
            //left
            GL11.glTranslatef(4.0f, 10.0f, -19.0f);
            GL11.glRotatef(35.0f, 0.0f, 1.0f, 0.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10); 
            
            //position then draw bull legs 
            //front right
            GL11.glTranslatef(4.0f, 10.0f, -19.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10); 
          //front left
            GL11.glTranslatef(4.0f, 10.0f, -19.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10); 
          //back right
            GL11.glTranslatef(4.0f, 10.0f, -19.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10);  
          //back left
            GL11.glTranslatef(4.0f, 10.0f, -19.0f);
            new Cylinder().draw(0.5f,0.5f,0.5f,10,10); 
        }
        GL11.glPopMatrix();  
	}
	
	private void resetAnimations(){
		bullEnraged = false;
		currentBodyPositionY = restBodyPositionY;
		currentHeadPosition = restHeadPosition;
		currentForeLegsPosition = restForeLegsPosition;
		currentRearLegsPosition = restRearLegsPosition;
		currentBeamPosition = restBeamPosition;
		
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

	protected void cleanupScene() {
		// TODO: Clean up your resources here
	}

	protected void drawUnitBullHead() {

		Vertex v1 = new Vertex(0.0f, 0.0f, 0.0f);
		Vertex v2 = new Vertex(0.0f, 0.5f, 0.0f);
		Vertex v3 = new Vertex(0.0f, 0.5f, 1.0f);
		Vertex v4 = new Vertex(0.0f, 0.0f, 1.0f);
		Vertex v5 = new Vertex(0.5f, 0.5f, 0.0f);
		Vertex v6 = new Vertex(0.5f, 0.0f, 0.0f);
		Vertex v7 = new Vertex(0.5f, 0.0f, 1.0f);
		Vertex v8 = new Vertex(0.5f, 0.5f, 1.0f);
		Vertex v9 = new Vertex(0.5f, 1.5f, 1.0f);
		Vertex v10 = new Vertex(0.5f, 1.5f, 0.0f);
		Vertex v11 = new Vertex(1.0f, 1.5f, 0.0f);
		Vertex v12 = new Vertex(1.0f, 1.5f, 1.0f);
		Vertex v13 = new Vertex(1.0f, 0.0f, 0.0f);
		Vertex v14 = new Vertex(1.0f, 0.0f, 1.0f);

		// front of nose
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

		// top of nose
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v6.toVector(), v4.toVector(), v1.toVector(),
			// v5.toVector()).submit();

			v2.submit();
			v5.submit();
			v8.submit();
			v3.submit();

		}
		GL11.glEnd();

		// top front of face
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v8.toVector(), v6.toVector(), v5.toVector(),
			// v7.toVector()).submit();

			v5.submit();
			v10.submit();
			v9.submit();
			v8.submit();

			GL11.glEnd();

		}

		// top of head
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v8.toVector(), v7.toVector(),
			// v9.toVector()).submit();

			v10.submit();
			v11.submit();
			v12.submit();
			v9.submit();

			GL11.glEnd();

		}

		// back of head
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v11.toVector(), v12.toVector(), v9.toVector(),
			// v10.toVector()).submit();

			v13.submit();
			v14.submit();
			v12.submit();
			v11.submit();

			GL11.glEnd();

		}

		// bottom of head
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v11.toVector(), v3.toVector(), v2.toVector(),
			// v12.toVector()).submit();

			v14.submit();
			v13.submit();
			v6.submit();
			v7.submit();

			GL11.glEnd();

		}

		// bottom of snout
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v11.toVector(), v3.toVector(), v2.toVector(),
			// v12.toVector()).submit();

			v6.submit();
			v1.submit();
			v4.submit();
			v7.submit();

			GL11.glEnd();

		}

		// head far side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v9.toVector(), v12.toVector(), v13.toVector(),
			// v7.toVector()).submit();

			v12.submit();
			v14.submit();
			v7.submit();
			v9.submit();

			GL11.glEnd();

		}

		// head near side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v11.toVector(), v14.toVector(),
			// v8.toVector()).submit();

			v11.submit();
			v10.submit();
			v6.submit();
			v13.submit();

			GL11.glEnd();

		}

		// snout near side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v11.toVector(), v14.toVector(),
			// v8.toVector()).submit();

			v6.submit();
			v5.submit();
			v2.submit();
			v1.submit();

			GL11.glEnd();

		}

		// snout far side
		GL11.glBegin(GL11.GL_POLYGON);
		{
			// new Normal(v10.toVector(), v11.toVector(), v14.toVector(),
			// v8.toVector()).submit();

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

}
