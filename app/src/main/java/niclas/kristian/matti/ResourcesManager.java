package niclas.kristian.matti;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import examples1.*;

/**
 * Created by matti on 27/05/2015.
 */
public class ResourcesManager {
    private static ResourcesManager instance;
    public Engine engine;
    public GameActivity activity;
    public Camera camera;
    public VertexBufferObjectManager vbom;
    private BuildableBitmapTextureAtlas roadTextureAtlas;
    private BuildableBitmapTextureAtlas backgroundTextureAtlas;
    private BuildableBitmapTextureAtlas carTextureAtlas;

    private ITextureRegion backgroundTexture;
    private ITextureRegion carTexture;
    private ITextureRegion roadHorizontalTexture;
    private ITextureRegion roadVerticalTexture;
    private ITextureRegion roadUpLeftTexture;
    private ITextureRegion roadUpRightTexture;
    private ITextureRegion roadDownLeftTexture;
    private ITextureRegion roadDownRightTexture;


    private ResourcesManager(){
        if(instance != null){
            throw new IllegalAccessError();
        }
    }

    public static ResourcesManager getInstance(){
        if(instance == null){
            instance = new ResourcesManager();
        }
        return instance;
    }

    public static void prepareManager(Engine engine, GameActivity activity, Camera camera, VertexBufferObjectManager vbom)  {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }

    public void loadGameResources(){
        loadGameGraphics();
    }

    private void loadTexturesForRoad(){
        roadTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        roadVerticalTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(roadTextureAtlas, activity, "roadvertical.png");

        roadHorizontalTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(roadTextureAtlas, activity, "roadhorizontal.png");

        roadUpRightTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(roadTextureAtlas, activity, "roadupright.png");

        roadUpLeftTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(roadTextureAtlas, activity, "roadupleft.png");

        roadDownRightTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(roadTextureAtlas, activity, "roaddownright.png");

        roadDownLeftTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(roadTextureAtlas, activity, "roaddownleft.png");

        try {
            roadTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            roadTextureAtlas.load();
        }
        catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
    }


    private void loadTextureAtlasGraphics(BuildableBitmapTextureAtlas textureAtlas, String fileName, ITextureRegion resultingRegion){
        textureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        resultingRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(textureAtlas, activity, fileName);


        try {
            textureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            textureAtlas.load();
        }
        catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
    }

    private void loadGameGraphics()  {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        loadTextureAtlasGraphics(backgroundTextureAtlas,"background.png",backgroundTexture);
        loadTexturesForRoad();
        loadTextureAtlasGraphics(carTextureAtlas,"car.png",carTexture);
    }

    public void unloadGameTextures()  {
        backgroundTextureAtlas.unload();
        roadTextureAtlas.unload();
        carTextureAtlas.unload();
    }

    public void loadMenuTextures() {
        backgroundTextureAtlas.load();
        roadTextureAtlas.load();
        carTextureAtlas.load();
    }


    public ITextureRegion getBackgroundTexture() {
        return backgroundTexture;
    }

    public void setBackgroundTexture(ITextureRegion backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public ITextureRegion getCarTexture() {
        return carTexture;
    }

    public void setCarTexture(ITextureRegion carTexture) {
        this.carTexture = carTexture;
    }

    public ITextureRegion getRoadHorizontalTexture() {
        return roadHorizontalTexture;
    }

    public void setRoadHorizontalTexture(ITextureRegion roadHorizontalTexture) {
        this.roadHorizontalTexture = roadHorizontalTexture;
    }

    public ITextureRegion getRoadVerticalTexture() {
        return roadVerticalTexture;
    }

    public void setRoadVerticalTexture(ITextureRegion roadVerticalTexture) {
        this.roadVerticalTexture = roadVerticalTexture;
    }

    public ITextureRegion getRoadUpLeftTexture() {
        return roadUpLeftTexture;
    }

    public void setRoadUpLeftTexture(ITextureRegion roadUpLeftTexture) {
        this.roadUpLeftTexture = roadUpLeftTexture;
    }

    public ITextureRegion getRoadUpRightTexture() {
        return roadUpRightTexture;
    }

    public void setRoadUpRightTexture(ITextureRegion roadUpRightTexture) {
        this.roadUpRightTexture = roadUpRightTexture;
    }

    public ITextureRegion getRoadDownRightTexture() {
        return roadDownRightTexture;
    }

    public void setRoadDownRightTexture(ITextureRegion roadDownRightTexture) {
        this.roadDownRightTexture = roadDownRightTexture;
    }


    public ITextureRegion getRoadDownLeftTexture() {
        return roadDownLeftTexture;
    }

    public void setRoadDownLeftTexture(ITextureRegion roadDownLeftTexture) {
        this.roadDownLeftTexture = roadDownLeftTexture;
    }


}
