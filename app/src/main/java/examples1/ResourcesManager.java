package examples1;


import android.content.res.AssetManager;
import android.graphics.Color;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;


public class ResourcesManager
{

    public ITextureRegion splash_region;
    private BitmapTextureAtlas splashTextureAtlas;
    private static final ResourcesManager INSTANCE = new ResourcesManager();

    public Engine engine;
    public GameActivity activity;
    public Camera camera;
    public VertexBufferObjectManager vbom;

    //Menu screen regions.
    public ITextureRegion menu_background_region;
    public ITextureRegion play_region;
    public ITextureRegion options_region;

    private BuildableBitmapTextureAtlas menuTextureAtlas;

    public Font font;

    /*Open our ResourcesManager class, and create fields for texture
    regions for our graphics, and for new texture:*/
    // Game Texture
    public BuildableBitmapTextureAtlas gameTextureAtlas;

    // Game Texture Regions
    public ITextureRegion platform1_region;
    public ITextureRegion platform2_region;
    public ITextureRegion platform3_region;
    public ITextureRegion coin_region;

    private void loadMenuFonts()
    {
        FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.TTF", 50, true, Color.WHITE, 2, Color.BLACK);
        font.load();
    }

   /* inside our ResourcesManager, create new method responsible for unloading
    game texture(s) since we did not create any textures for game scene,
    we will leave it blank for now, and fill later with game textures unloading code/
    As you can see we created new texture, and loaded 4 texture regions for our platforms and coin, loading assets from gfx/game/ folder. */
   public void unloadGameTextures()
   {
       BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
       gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

       platform1_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "greenplatform.png");
       platform2_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "redplatform.png");
       platform3_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "yellowplatform.png");
       coin_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "coin.png");

       try
       {
           this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
           this.gameTextureAtlas.load();
       }
       catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e)
       {
           Debug.e(e);
       }
   }

    /*Its quite self-explanatory what it just help us to free some memory while
    texture is currently not needed, later we can load it again with one line
            (we do not have to recreate texture regions etc, we just unloaded
                    texture from memory, we can load it again)*/
    public void unloadMenuTextures()
    {
        menuTextureAtlas.unload();
    }

    public void loadMenuTextures()
    {
        menuTextureAtlas.load();
    }

    public void loadMenuResources()
    {
        loadMenuGraphics();
        loadMenuAudio();
        loadMenuFonts();
    }

    public void loadGameResources()
    {
        loadGameGraphics();
        loadGameFonts();
        loadGameAudio();
    }

    private void loadMenuGraphics()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        menu_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_background.png");
        play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "play.png");
        options_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "options.png");


        try
        {
            this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            this.menuTextureAtlas.load();
        }
        catch (final ITextureAtlasBuilder.TextureAtlasBuilderException e)
        {
            Debug.e(e);
        }
    }

    private void loadMenuAudio()
    {

    }

    private void loadGameGraphics()
    {

    }

    private void loadGameFonts()
    {

    }

    private void loadGameAudio()
    {

    }

    public void loadSplashScreen()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.png", 0, 0);
        splashTextureAtlas.load();
    }

    public void unloadSplashScreen()
    {
        splashTextureAtlas.unload();
        splash_region = null;
    }

    /**
     * @param engine
     * @param activity
     * @param camera
     * @param vbom
     * <br><br>
     * We use this method at beginning of game loading, to prepare Resources Manager properly,
     * setting all needed parameters, so we can latter access them from different classes (eg. scenes)
     */
    public static void prepareManager(Engine engine, GameActivity activity, Camera camera, VertexBufferObjectManager vbom)
    {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }

    public static ResourcesManager getInstance()
    {
        return INSTANCE;
    }
}
