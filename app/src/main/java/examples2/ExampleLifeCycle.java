package examples2;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Created by herpderp on 27/05/2015.
 */
public class ExampleLifeCycle extends BaseGameActivity {
    @Override
    public EngineOptions onCreateEngineOptions() {
        /**This method allows us to return a Custom engine object to the activity,
         * which usually means how the frame updates handled. Depending on the engine object used,
         * The overall feel of the gameplay can alter drastically. */


        /**There are different Engine objects, the ordinary 'engine' object, is probably not ideal for most games,
         * because it has absolutely no limitations for frames per second. on two seperate devices it is very likely
         * that you will notice change in the speed of the game.
         *
         * Another type of engine is the FixedStepEngine, this is the ideal engine, because it forces the game
         * to loop at a surden frames per second, regardless of the device. This is done, updateing the game based
         * on the amount of time passed instead of codes execution speed. the following snippet:
         *
         * new FixedStepEngine(pEngineOptions, 60);
         *
         * Brings us a engine with 60 steps pr. second.
         *
         * a third engine is the LimitedFPSEngine this engine will allow us to set a limit on the FPS.
         * the following snippet will create such an engine, that runs with maximum 60 fps.
         *
         * return new LimitedFPSEngine(pEngineOptions, 60);
         *
         *
         * Two other engines is the SingleSceneSplitEngine and the DoubleSceneSplitEngine,
         * the singleScene is usually used for singleplayer, while the double scene is usually used
         * for multiplayer on a single device. These two engines have a wide amount of use, such as
         * minimaps, multiple perspectives, menu systems and much more.
         *
         *
         * Resolution policy, we must choose one when we make a engine, this is a very delicate subject
         * because android devices range from 3.1 inch displays to 10.1, whatever resolution policy we pick,
         * we must include it in the Engine constructor.
         * e.g.
         *
         * EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), mCamera);
         *
         * We can change the resolutionpolicy by just adding another policy.
         *
         * The FillResolution policy is the standard policy if we just want to fill up the width and height of the application,
         * though this resolution allows our game to run in full width/height, this might allso bring along a large
         * amount of stretching of the resources we use.
         *
         * FixedResolutionPolicy allows us to choose a fixed size, regardles of the size of the device, this
         * can be given to the engine as new FixedResolutionPolicy(width, height),
         *
         * RatioResolutionPolicy is the best choice if we need to obtain the maximum display size without
         * causing any distortion of sprites, on the other hand, it is possible to to the varity in android devices,
         * that some will see a black bar at the edges. This policy can be declared as follows:
         * new RatioResolutionPolicy(1.6f)
         to define a ratio, or
         new RatioResolutionPolicy(mCameraWidth, mCameraHeight)
         , assuming
         mCameraWidth  and mCameraHeight  are the defined Camera object dimensions.

         the last resolution is the relativeResolutionPolicy.
         *
         * */




         return null;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {

    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {

    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {

    }
}
