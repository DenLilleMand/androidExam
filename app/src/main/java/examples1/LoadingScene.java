package examples1;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;


/**
 * Created by herpderp on 25/05/2015.
 */
public class LoadingScene extends BaseScene {
    @Override
    public void createScene() {
        setBackground(new Background(Color.WHITE));
        attachChild(new Text(400, 200, resourcesManager.font, "Loading...", vbom));
    }

    @Override
    public void onBackKeyPressed() {
       /* As you probably noticed, inside onBackKeyPressed() we
        just return, which means we do not perform any actions
        (because we do not want to do anything while the loading
        scene is being displayed and the player touches the phone`s back button)*/
        return;
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_LOADING;
    }

    @Override
    public void disposeScene() {

    }
}
