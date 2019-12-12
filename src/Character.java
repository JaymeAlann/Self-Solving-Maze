
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;



public class Character extends Pane {

    /**================================================
     * This class handles all situations that would deal
     * with the character. Movement, orientation, location
     * and even the character symbol itself.
     *
     * NOTE: THIS PART IS FOR COMMAND LINE ONLY
     ==================================================*/

    public Character(){

    }

    //=========================================
    //  Default variables for the character
    //=========================================
    private char player = '@';
    private int charX = 2;
    private int charY = 0;
    private int orientation = 3;

    //==========================================
    //  Handles character movment along the maze
    //==========================================
    public void stepRight(){
        switch (orientation){
            case 1:
                setCharX(charX + 1);
                setOrientation(2);
                break;
            case 2:
                setCharY(charY + 1);
                setOrientation(3);
                break;
            case 3:
                setCharX(charX - 1);
                setOrientation(4);
                break;
            case 4:
                setCharY(charY - 1);
                setOrientation(1);
                break;

        }
    }

    public void stepForward(){
        switch (orientation){
            case 1:
                setCharY(charY - 1);
                break;
            case 2:
                setCharX(charX + 1);
                break;
            case 3:
                setCharY(charY + 1);
                break;
            case 4:
                setCharX(charX - 1);
                break;

        }
    }

    public void stepLeft(){
        switch (orientation){
            case 1:
                setCharX(charX - 1);
                setOrientation(4);
                break;
            case 2:
                setCharY(charY - 1);
                setOrientation(1);
                break;
            case 3:
                setCharX(charX + 1);
                setOrientation(2);
                break;
            case 4:
                setCharY(charY + 1);
                setOrientation(3);
                break;

        }
    }

    public void stepBack(int y, int x){
        switch (orientation){
            case 1:
                if(y == charY-1){
                    setLocation(y,x);
                }else if (y == charY + 1){
                    setOrientation(3);
                    setLocation(y,x);
                }else if (x == charX -1){
                    setOrientation(4);
                    setLocation(y,x);
                }else if (x == charX + 1){
                    setOrientation(2);
                    setLocation(y,x);
                }
                break;
            case 2:
                if(y == charY-1){
                    setOrientation(1);
                    setLocation(y,x);
                }else if (y == charY + 1){
                    setOrientation(3);
                    setLocation(y,x);
                }else if (x == charX -1){
                    setOrientation(4);
                    setLocation(y,x);
                }else if (x == charX + 1){
                    setOrientation(2);
                    setLocation(y,x);
                }
                break;
            case 3:
                if(y == charY-1){
                    setOrientation(1);
                    setLocation(y,x);
                }else if (y == charY + 1){
                    setLocation(y,x);
                }else if (x == charX -1){
                    setOrientation(4);
                    setLocation(y,x);
                }else if (x == charX + 1){
                    setOrientation(2);
                    setLocation(y,x);
                }
                break;
            case 4:
                if(y == charY-1){
                    setOrientation(1);
                    setLocation(y,x);
                }else if (y == charY + 1){
                    setOrientation(3);
                    setLocation(y,x);
                }else if (x == charX -1){
                    setLocation(y,x);
                }else if (x == charX + 1){
                    setOrientation(2);
                    setLocation(y,x);
                }
                break;

        }
    }

    //=========================================
    //  Handles Character Location Variables
    //=========================================
    public boolean checkLocation(int y, int x){
        if(y == charY && x == charX){
            return true;
        }
        else{
            return false;
        }
    }

    public void setLocation(int row, int column){
        charY = row;
        charX = column;
    }

    public int getX(){
        return charX;
    }

    public int getY(){
        return charY;
    }

    public void setCharX(int x){
        charX = x;
    }

    public void setCharY(int y){
        charY = y;
    }

    //========================================
    //  Handles setting the characters
    //  Orientation in regards to movement
    //  and map location.
    //========================================
    public int getOrientation(){
        return orientation;
    }

    public void setOrientation(int orientation){

        this.orientation = orientation;
    }

    public char getPlayer(){
        return player;
    }


    /**================================================
     *    The following methods are designed for
     *    GUI animation methods only!!
     ==================================================*/
    //Character Configurations for animation
    ImageView imageView;
    int count = 9;
    int columns = 10;
    int offsetX = 20;
    int offsetY = 0;
    double width = 17.5;
    int height = 20;

    Rectangle removeRect = null;
    CharacterAnimation animation;


    public Character(ImageView imageView){
        this.imageView = imageView;
        this.imageView.setY(charY*20);
        this.imageView.setX(charX*20);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width,height));
        animation = new CharacterAnimation(imageView, Duration.millis(600), count, columns, offsetX, offsetY, width,height);
        getChildren().addAll(imageView);

    }

    public void setImgCords(double y, double x){
        this.imageView.setX(x);
        this.imageView.setY(y);
    }
}

/**===================================================
 * This class is the default values of the character
 * animation. Extends transition and is used by
 * character class to move GUI character sprite.
 ====================================================*/
class CharacterAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final double width;
    private final int height;

    public CharacterAnimation(ImageView imageView, Duration duration, int count, int columns, int offsetX, int offsetY, double width, int height){
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;

        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    public void setOffsetX(int x){
        this.offsetX = x;
    }

    public void setOffsetY(int y){
        this.offsetY = y;
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(count * frac), count -1);
        final double x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        imageView.setViewport(new Rectangle2D(x,y,width,height));

    }
}
