package examples2;

/**
 * Created by herpderp on 27/05/2015.
 */

/**
 *    Pattern: Factory pattern.
 *
 * Object factories, in game development these we can use to spawn enemy/collideing objects etc.
 * AndEngine even uses the factory pattern when generateing fonts, sounds and textures among others.
 *
 * In this exampleObjectFactory, we're going to make a factory based on the "ObjectFactory",
 * and it should make objects of the "BaseObject"(Which is a object we made up, has nothing to do with AndEngine)
 * type, Our example
 * will contain inner classes, normally we would split our objects into a "Object" folder and just reference
 * them from this class.
 *
 * We should consider making a SpriteFactory aswell, Basically a factory for allmost all sorts of things we might need?
 * SpriteFactory could contain methods suchs as:
 *  via
 SpriteFactory.createSprite()
 ,
 SpriteFactory.createButtonSprite()
 , and
 SpriteFactory.createTiledSprite()
 * */
public class ObjectFactory  {

    public static BaseObject createLargeObject(int x, int y){
        return new LargeObject();
    }

    public static BaseObject createSmallObject(int x, int y){
        return new SmallObject();
    }


    private static class BaseObject  {
        protected int x,y;

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }


    }

    public static class LargeObject extends BaseObject{
        public LargeObject(){
            x = 40;
            y = 50;
        }
    }

    public static class SmallObject extends BaseObject{
        public SmallObject(){
            x = 20;
            y = 20;
        }
    }

}

