package examples1;


        import android.view.KeyEvent;

        import org.andengine.engine.Engine;
        import org.andengine.engine.LimitedFPSEngine;
        import org.andengine.engine.camera.Camera;
        import org.andengine.engine.handler.timer.ITimerCallback;
        import org.andengine.engine.handler.timer.TimerHandler;
        import org.andengine.engine.options.EngineOptions;
        import org.andengine.engine.options.ScreenOrientation;
        import org.andengine.engine.options.WakeLockOptions;
        import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
        import org.andengine.entity.scene.Scene;
        import org.andengine.ui.activity.BaseGameActivity;

/**
 * Created by matti on 25/05/2015.
 */
public class GameActivity extends BaseGameActivity {
    private Camera camera;
    private ResourcesManager resourcesManager;

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, 800, 480); //oftest brugte screen resolution
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(800, 480), this.camera); //fixed langscape, kunne overveje fixed portrait. med ratio resolution, resizer den automatisk,
        //hvis vi skifter skærmstørrelse. så det eneste vi faktisk skal bekymrer os om, det er ratio.
        engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true); //Audio set to true, so that we can use sound in our game.
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback)  {
        ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
        resourcesManager = ResourcesManager.getInstance();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }





    /**
     * execute the createSplashScene() method inside onCreateScene in your Activity.
     * @param pOnCreateSceneCallback
     * @throws Exception
     */
    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)  {
        SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) {
        mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback()
        {
            /**
             * Alt vi ligger ind i den her metode, bliver kørt imens vores slashscreen
             * er der, så vi skal loade alle vores billeder, scenes(?) osv. her.
             * @param pTimerHandler
             */
            public void onTimePassed(final TimerHandler pTimerHandler)
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                SceneManager.getInstance().createMenuScene();
            }
        }));
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    @Override
    protected void onDestroy()
    {
        /*We have to override the onDestroy method, to make sure our activity
         will be killed after leaving the game, because some devices tends to
          keep your activity alive even after leaving it, which leads to strange
          behaviour. To ensure it will be killed,
        override onDestroy inside your activity and call System.exit(0);*/
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
        }
        return false;
    }
}
