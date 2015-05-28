package examples2;




import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Stack;

/**
 * Created by herpderp on 26/05/2015.
 *
 * To create the rings, we need to first make a custom class that will extend Sprite. You do this because every ring
 * needs to know which stack it belongs to.
 *
 *
 * Most of the code here is pretty straightforward.
 * The object has three private variables.
 * One is used to keep track of the weight of the tower;
 * this is an integer value, i.e., the higher the value,
 * the bigger the ring. The other two variables are used
 * to store the stack that the ring belongs to
 * and the tower on which it is currently placed.
 * */
public class Ring extends Sprite{
    private int weight;
    private Stack stack;
    private Sprite tower;

    public Ring(int weight, float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    public void setStack(Stack stack){
        this.stack = stack;
    }

    public Stack getStack(){
        return stack;
    }

    public void setTower(Sprite tower){
        this.tower = tower;
    }

    public Sprite getTower(){
        return tower;
    }


}
