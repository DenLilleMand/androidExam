package examples1;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.SAXUtils;


import org.andengine.util.color.Color;
import org.andengine.util.level.constants.LevelConstants;
import org.xml.sax.Attributes;

import java.io.IOException;

/**
 * Created by herpderp on 25/05/2015.
 */
public class GameScene extends BaseScene {


    private HUD gameHUD;
    private Text scoreText;
    private int score = 0;
    private PhysicsWorld physicsWorld;
    private static final String TAG_ENTITY = "entity";
    private static final String TAG_ENTITY_ATTRIBUTE_X = "x";
    private static final String TAG_ENTITY_ATTRIBUTE_Y = "y";
    private static final String TAG_ENTITY_ATTRIBUTE_TYPE = "type";

    private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM1 = "platform1";
    private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM2 = "platform2";
    private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM3 = "platform3";
    private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_COIN = "coin";

    /**
     * We used FixedStepPhysicsWorld class, this class tries to achieve a specific amount
     * of steps per second (in our case 60) second parameter is our gravity parameter,
     * its up to you to modify it depends on your needs,
     * on the end, we register our physics world as a update handler to our scene.
     */
    private void createPhysics()
    {
        physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0, -17), false);
        registerUpdateHandler(physicsWorld);
    }

    private void addToScore(int i)
    {
        score += i;
        scoreText.setText("Score: " + score);
    }



   /* execute all creation methods inside createScene() method:*/
    @Override
    public void createScene() {
        createBackground();
        createHUD();
        createPhysics();
        loadLevel(1);
    }

    /*Execute method responsible for switching back to the menu scene inside
    your GameScene, by executing this method inside onBackKeyPressed() method.*/
    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuScene(engine);
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
        camera.setHUD(null);
        camera.setCenter(400, 240);

        // TODO code responsible for disposing scene
        // removing all game scene objects.
    }

    private void createBackground()
    {
        setBackground(new Background(Color.BLUE));
    }


    private void createHUD()
    {
        gameHUD = new HUD();

        // CREATE SCORE TEXT
        scoreText = new Text(0, 0, resourcesManager.font, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        scoreText.setText("Score: 0");
        gameHUD.attachChild(scoreText);

        camera.setHUD(gameHUD);
    }
    /**
     * Created by herpderp on 25/05/2015.
     * Now I will try to explain what this code does, in registerEntityLoader,

     1) we return "mother entity" for all entities loaded from xml file. In another words, entity where
     all loaded entities are going to be attached to. As you can conclude, we should return our game
     scene (like we do in the code above)

     2) in second registerEntityLoader we actually parse our xml file, we check what type variable
     equals, if it equals "platform1" we create and return new sprite for our first
     platform type, we also create body and set user data to "platform1" (we will later need those
     user data to recognise physical objects inside contact listener) You can ask why we also register
     physics connector in platform 2 and platform 3, we do it, because as you should conclude from
     platforms description on the beginning of this article, those two platforms are going to collapse
     in certain circumstances, that`s why we register it (so sprite will update its positions,
     following body) We also have code responsible for creating our coin, as you can see, we overridden
     onManageUpdate method, we will later use it to check if player collide with coin, to execute certain
     code, such as setting coin invisible and adding score. We also registered loop
     scale entity modifier to the coin to make it looks better (bit animated)
     */
    private void loadLevel(int levelID)
    {
        /*final SimpleLevelLoader levelLoader = new SimpleLevelLoader(vbom);

        final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(0, 0.01f, 0.5f);

        levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(LevelConstants.TAG_LEVEL)
        {
            public IEntity onLoadEntity(final String pEntityName, final IEntity pParent, final Attributes pAttributes, final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData) throws IOException
            {
                final int width = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH);
                final int height = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT);

                // TODO later we will specify camera BOUNDS and create invisible walls
                // on the beginning and on the end of the level.

                return GameScene.this;
            }
        });

        levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(TAG_ENTITY)
        {
            public IEntity onLoadEntity(final String pEntityName, final IEntity pParent, final Attributes pAttributes, final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData) throws IOException
            {
                final int x = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_X);
                final int y = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_Y);
                final String type = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_TYPE);

                final Sprite levelObject;

                if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM1))
                {
                    levelObject = new Sprite(x, y, resourcesManager.platform1_region, vbom);
                    PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyDef.BodyType.StaticBody, FIXTURE_DEF).setUserData("platform1");
                }
                else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM2))
                {
                    levelObject = new Sprite(x, y, resourcesManager.platform2_region, vbom);
                    final Body body = PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyDef.BodyType.StaticBody, FIXTURE_DEF);
                    body.setUserData("platform2");
                    physicsWorld.registerPhysicsConnector(new PhysicsConnector(levelObject, body, true, false));
                }
                else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM3))
                {
                    levelObject = new Sprite(x, y, resourcesManager.platform3_region, vbom);
                    final Body body = PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyDef.BodyType.StaticBody, FIXTURE_DEF);
                    body.setUserData("platform3");
                    physicsWorld.registerPhysicsConnector(new PhysicsConnector(levelObject, body, true, false));
                }
                else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_COIN))
                {
                    levelObject = new Sprite(x, y, resourcesManager.coin_region, vbom)
                    {
                        @Override
                        protected void onManagedUpdate(float pSecondsElapsed)
                        {
                            super.onManagedUpdate(pSecondsElapsed);


                        }
                    };
                    levelObject.registerEntityModifier(new LoopEntityModifier(new ScaleModifier(1, 1, 1.3f)));
                }
                else
                {
                    throw new IllegalArgumentException();
                }

                levelObject.setCullingEnabled(true);

                return levelObject;
            }
        });

        levelLoader.loadLevelFromAsset(activity.getAssets(), "level/" + levelID + ".lvl");*/
    }
}
