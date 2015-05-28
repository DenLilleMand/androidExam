package examples1;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

/**
 * Created by herpderp on 25/05/2015.
 */
public class SplashScene extends BaseScene {

    /**
     * What we just did is initialize our splash sprite, and attached it in the middle of the screen.
     * We also enabled dithering - to improve quality,
     * since its gradient based art (see included link, for more information about dithering)
     */
    private Sprite splash;

    @Override
    public void createScene() {

        splash = new Sprite(0, 0, resourcesManager.splash_region, vbom)
        {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        splash.setScale(1.5f);
        splash.setPosition(400, 240);
        attachChild(splash);
    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_SPLASH;
    }

    /**
     * This will dispose of the splash sprite, and detach it from the scene. Okay, our splash scene is done,
     * now we just have to initialize it inside our activity, more below.
     */
    @Override
    public void disposeScene() {
        splash.detachSelf();
        splash.dispose();
        this.detachSelf();
        this.dispose();
    }
}
