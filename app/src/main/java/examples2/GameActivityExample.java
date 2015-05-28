package examples2;

/**
 * Created by herpderp on 26/05/2015.
 */

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;


import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

/**SimpleBaseGameActivity is just one of the many activities we can choose from,
 * other activies could be TheLayoutGameActivity, that is usually used to implement advertisements.
 * because it enables us to include native android SDK views such as buttons,seek bars, spinners,
 * additional layouts etc.
 * Then we have SimpleBaseGameActivity and SimpleLayoutGameActivity, which are different in the way that
 * they dont demand us to return something from the overriden methods, AndEngine will take care of this for us
 * and do the callbacks.  Then in the end we have SimpleAsyncGameActivity, which brings us a bunch of Async
 * alternatives, as you might want when doing a scroller game forexample, we can make a progress integer,
 * and load resources based on that progress, which means that we could do a big progress bar in the top
 * of the game, with a finish line, and then being able to motorcycle towards it. forexample inside of the
 * onCreateResources we can call a method:
 * pProgressListener.onProgressChanged(10);  that would be able to call a method whenever progress hits 10. */
public class GameActivityExample extends SimpleBaseGameActivity {
    //camera settings(Most standard)
    // These two static variables define the width and height of the camera that the engine will use.
    // This means that the final dimensions of the game scene will be equal to the camera size and width:
    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_HEIGHT = 480;


    private ITextureRegion mBackgroundTextureRegion, mTowerTextureRegion, mRing1, mRing2, mRing3;

    /** WHen we're ready to make the game scene, we need to make some sprites.  */
    private Sprite mTower1, mTower2, mTower3;


    private Stack stack1, stack2, stack3;


    //We'll have a bunch of overriden methods, and most of them returns something, thats because
    //they're just hooks in the greater cycle of AndEngine, we hook into them, and whenever we return something,
    //the engine knows to get on to the next step. (As mentioned earlier SimpleBaseGameActivity and Simple
    // SimpleLayoutGameActivity is different in the way that AndEngine takes care of alot more for us.
    // so we dont actually have to return the things, the framework does the callbacks for us.)the calls being made goes as:

    /**
     * 1.Activity starts
     * 2.OnCreate
     * 3.OnResume
     * 4.Onsurface game
     * 5.OnCreate resources
     * 6.OnCreate Scene
     * 7.OnPopulate scene
     * 8.OnGame Created
     * 9.OnSurface changed
     * 10.OnResume Game
     * 11.Game loop running
     *
     * **onLoad resources is being called whenever we do OnResume, and onResumeGame.
     *
     * We have a bunch of other things like onPauseGame,onPause and onMinimized, and all of these bring
     * us back to onCreate().
     *
     */


    /*This is the function where you’ll load all
    the resources that the activity requires into the the VRAM.*/
    @Override
    protected void onCreateResources() {
        /*creates ITexture objects for all the assets you downloaded, and loads them into VRAM by calling the load method on each object.*/
        try {
            // 1 - Set up bitmap textures
            /*ITexture is an interface. An object of this type is initialized to a BitmapTexture object,
            which is used to load a bitmap into VRAM*/
            Debug.e("This is the textureManager:" + this.getTextureManager());
            ITexture backgroundTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/example/background.png");
                }
            });
            ITexture towerTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/example/tower.png");
                }
            });
            ITexture ring1 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/example/ring1.png");
                }
            });
            ITexture ring2 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/example/ring2.png");
                }
            });
            ITexture ring3 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/example/ring3.png");
                }
            });
            // 2 - Load bitmap textures into VRAM
            backgroundTexture.load();
            towerTexture.load();
            ring1.load();
            ring2.load();
            ring3.load();
            // 3 - Set up texture regions
            Debug.e("aparently this background texture is null" + backgroundTexture);
            this.mBackgroundTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
            Debug.e("the background texture:" + mBackgroundTextureRegion);
            this.mTowerTextureRegion = TextureRegionFactory.extractFromTexture(towerTexture);
            this.mRing1 = TextureRegionFactory.extractFromTexture(ring1);
            this.mRing2 = TextureRegionFactory.extractFromTexture(ring2);
            this.mRing3 = TextureRegionFactory.extractFromTexture(ring3);

        }catch(IOException e){
            Debug.e(e);
        }
    }


    /*This function is where you create an instance of the engine.
    Every activity that the game uses will have its own instance
    of the engine that will run within the activity lifecycle.*/
    @Override
    public EngineOptions onCreateEngineOptions() {
       /* create a new instance of the Camera class. Then we use that camera object
        to create the EngineOptions object that defines the options with which the
        engine will be initialized. */
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        /*The parameters that are passed while creating an instance of EngineOptions are:
        1.FullScreen: A boolean variable signifying whether or not the engine instance will use a fullscreen.
        2.ScreenOrientation: Specifies the orientation used while the game is running.
        3.ResolutionPolicy: Defines how the engine will scale the game assets on phones with different screen sizes.
        4.Camera: Defines the width and height of the final game scene.
        */
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
    }

    /*This function is called after the above two callbacks are executed.
This is where you create the scene for your game and use all the textures
that you previously loaded into memory.*/
    @Override
    protected Scene onCreateScene() {
       /* creates a Scene object*/
        final Scene scene = new Scene();
        /**  create a sprite called backgroundSprite
         * this sprite takes 4 parameters:
         * 1.xCoordinate: Defines the X-position of the sprite. The AndEngine coordinate system considers the top-left point as the origin.
         * 2. yCoordinate: Defines the Y-position of the sprite.
         * 3.TextureRegion: Defines what part of the texture the sprite will use to draw itself.
         * 4.VertexBufferObjectManager: Think of a vertex buffer as an array holding the coordinates of a texture. These coordinates are passed to the OpenGL ES pipeline and
         ultimately define what will be drawn. A VertexBufferObjectManager holds all the vertices of all the textures that need to be drawn on the scene. */
        Sprite backgroundSprite = new Sprite(0, 0, this.mBackgroundTextureRegion, getVertexBufferObjectManager());
        /** attach the sprite to the scene */
        scene.attachChild(backgroundSprite);

        // adding the towers
        mTower1 = new Sprite(192,63, this.mTowerTextureRegion,getVertexBufferObjectManager());
        mTower2 = new Sprite(400,63, this.mTowerTextureRegion,getVertexBufferObjectManager());
        mTower3 = new Sprite(604,63, this.mTowerTextureRegion,getVertexBufferObjectManager());

        scene.attachChild(mTower1);
        scene.attachChild(mTower2);
        scene.attachChild(mTower3);

       /* Notice that onAreaTouched returns a boolean value. When it returns true, the touch is consumed,
       otherwise it is passed down to other layers until someone consumes it. So the first thing you do in
        this method is check if the weight of the current ring is not equal to the weight of the first ring in
        the stack. If it is, that means this ring is the first element of the stack and so you can proceed to move it,
         otherwise you let the touch go since you can’t move this ring. You also check in onAreaTouched if the type of
         touch is an ACTION_UP event, triggered when the finger is lifted. If it is, you call checkForCollisionsWithTowers
         whose primary purpose is to check if the ring has collided with (or rather, is touching) a tower. As you may
        recall, you added a placeholder for checkForCollisionsWithTowers already. Let’s fill the function in now.*/
        Ring ring1 = new Ring(1,139,174,this.mRing1,getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                if(((Ring) this.getStack().peek()).getWeight() != this.getWeight()){
                    return false;
                }
                this.setPosition(pSceneTouchEvent.getX() - this.getWeight() / 2,pSceneTouchEvent.getY() - this.getHeight()/2);
                if(pSceneTouchEvent.getAction()==TouchEvent.ACTION_UP){
                    checkForCollisionsWithTowers(this);
                }
                return true;
            }
        };
        Ring ring2 = new Ring(2,118,212,this.mRing2,getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                if(((Ring) this.getStack().peek()).getWeight() != this.getWeight()){
                    return false;
                }
                this.setPosition(pSceneTouchEvent.getX() - this.getWeight() / 2,pSceneTouchEvent.getY() - this.getHeight()/2);
                if(pSceneTouchEvent.getAction()==TouchEvent.ACTION_UP){
                    checkForCollisionsWithTowers(this);
                }
                return true;
            }
        };
        Ring ring3 = new Ring(3,97,255,this.mRing3,getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                if(((Ring) this.getStack().peek()).getWeight() != this.getWeight()){
                    return false;
                }
                this.setPosition(pSceneTouchEvent.getX() - this.getWeight() / 2,pSceneTouchEvent.getY() - this.getHeight()/2);
                if(pSceneTouchEvent.getAction()==TouchEvent.ACTION_UP){
                    checkForCollisionsWithTowers(this);
                }
                return true;
            }
        };
        scene.attachChild(ring1);
        scene.attachChild(ring2);
        scene.attachChild(ring3);

        this.stack1.add(ring3);
        this.stack1.add(ring2);
        this.stack1.add(ring1);
        // 5 - Initialize starting position for each ring
        ring1.setStack(stack1);
        ring2.setStack(stack1);
        ring3.setStack(stack1);
        ring1.setTower(mTower1);
        ring2.setTower(mTower1);
        ring3.setTower(mTower1);
        // 6 - Add touch handlers
        scene.registerTouchArea(ring1);
        scene.registerTouchArea(ring2);
        scene.registerTouchArea(ring3);
        scene.setTouchAreaBindingOnActionDownEnabled(true);
        return scene;
    }

    private void checkForCollisionsWithTowers(Ring ring) {
        Stack stack = null;
        Sprite tower = null;
        if (ring.collidesWith(mTower1) && (stack1.size() == 0 || ring.getWeight() < ((Ring) stack1.peek()).getWeight())) {
            stack = stack1;
            tower = mTower1;
        } else if (ring.collidesWith(mTower2) && (stack2.size() == 0 || ring.getWeight() < ((Ring) stack2.peek()).getWeight())) {
            stack = stack2;
            tower = mTower2;
        } else if (ring.collidesWith(mTower3) && (stack3.size() == 0 || ring.getWeight() < ((Ring) stack3.peek()).getWeight())) {
            stack = stack3;
            tower = mTower3;
        } else {
            stack = ring.getStack();
            tower = ring.getTower();
        }
        ring.getStack().remove(ring);
        if (stack != null && tower !=null && stack.size() == 0) {
            ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, tower.getY() + tower.getHeight() - ring.getHeight());
        } else if (stack != null && tower !=null && stack.size() > 0) {
            ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, ((Ring) stack.peek()).getY() - ring.getHeight());
        }
        stack.add(ring);
        ring.setStack(stack);
        ring.setTower(tower);
    }

}
