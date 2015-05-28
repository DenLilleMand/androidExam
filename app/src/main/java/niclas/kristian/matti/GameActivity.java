package niclas.kristian.matti;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Created by herpderp on 27/05/2015.
 */
public class GameActivity extends BaseGameActivity {
    private ResourcesManager resourcesManager;

    private final int CAMERA_WIDTH = 800;
    private final int CAMERA_HEIGHT = 480;

    private Engine engine;
    private Camera camera;


    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,new RatioResolutionPolicy(800, 480), this.camera);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        ResourcesManager.prepareManager(engine,this,camera,getVertexBufferObjectManager());
        resourcesManager = resourcesManager.getInstance();
        resourcesManager.loadGameResources();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
        final Scene scene = new Scene();


        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {



        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
}
