import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MazeGUI extends Application{

    //===============================================
    //  This array creates the maze base that sets
    //  Walls and paths to traverse.
    //===============================================
    private int[][] mazeArray = {
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    //===========================================
    //  Images for GUI
    //===========================================
    private Image playerIMG = new Image("images//spriteLink3.png", 175,175,true, true);
    private Image wallIMG = new Image("images//tree2.png", 20,20,true, true);
    private Image path2IMG = new Image("images//path2.png", 20,20,true, true);
    private Image path1IMG = new Image("images//path1.png", 20,20,true, true);
    private ImageView[][] img = new ImageView[mazeArray.length][mazeArray[0].length];
    private ImageView imageView = new ImageView(playerIMG);
    private Character player = new Character(imageView);

    //Panes
    private Pane pane = new Pane();
    private Pane lpane = new Pane();
    private StackPane stPane = new StackPane();
    private BorderPane mainPane = new BorderPane();
    private HBox hBox = new HBox(10);

    // Maze Constructor
    private Maze maze = new Maze(mazeArray);
    private int width = mazeArray[0].length;
    private int height = mazeArray.length;

    // User options
    private Button btFindExit = new Button(" Find Exit");
    private Button btTakeStep = new Button("Take Step");

    //  Maze Main Scene
    private  Scene scene = new Scene(mainPane, (width*20) + 30,height*25);

    CheckBox soundToggle = new CheckBox();

    @Override
    public void start(Stage primaryStage) throws Exception {
        createContent();
        primaryStage.setTitle("Maze Project");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
    }

    //=========================================
    //  This method holds and handles the
    //   content creation. Allows for future
    //   calling.
    //=========================================
    public void createContent(){

        AudioClip myStep = new AudioClip(this.getClass().getResource("sounds/step.wav").toExternalForm());
        AudioClip lullaby = new AudioClip(this.getClass().getResource("sounds/SongofStorms.mp4").toExternalForm());
        AudioClip music = new AudioClip(this.getClass().getResource("sounds/Lost Woods Dubstep Remix - Ephixa.mp4").toExternalForm());
        lullaby.setCycleCount(5);
        lullaby.play();


        //  Find Exit Button
        btFindExit.setPrefSize(100,20);
        btFindExit.setFocusTraversable(false);
        //  Take Step Button
        btTakeStep.setPrefSize(100,20);
        btTakeStep.setFocusTraversable(false);


        // Bottom Panel for Buttons
        hBox.setPadding(new Insets(5));
        hBox.getChildren().addAll(soundToggle, btFindExit,btTakeStep);
        hBox.setAlignment(Pos.CENTER);
        displayMaze();

        stPane.getChildren().addAll(pane, lpane);

        //==============================
        //  Button Action Listeners
        //==============================
        btFindExit.setOnAction(event -> {
            maze.findExit(maze.getPlayerY(), maze.getPlayerX());
            pane.getChildren().clear();
            lpane.getChildren().clear();
            displayMaze();
            if(maze.getSolved()==true){
                lullaby.stop();
                music.play();
                NotifyDone done = new NotifyDone();
            }
        });


        btTakeStep.setOnAction(event -> {
            maze.takeStep();
            pane.getChildren().clear();
            lpane.getChildren().clear();
            displayMaze();
            if(maze.getSolved()==true){
                lullaby.stop();
                music.play();
                NotifyDone done = new NotifyDone();
            }
            if(soundToggle.isSelected()){
                myStep.stop();
            }else{
                myStep.play();
            }
        });



        //=========================
        //  Puts all the pieces
        // together for the maze
        //=========================
        mainPane.setPadding(new Insets(15));
        mainPane.setCenter(stPane);
        mainPane.setBottom(hBox);
        mainPane.setStyle("-fx-background-image: url(\"images/exitScreen.jpg\");-fx-background-size: 800, 800;-fx-background-repeat: no-repeat;");



        //  Timer that handles the spite animation
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();

    }

    //============================================
    //  Sets the animation based off of character
    //  information sent from maze class such as
    //  orientation, location, and direction.
    //============================================
    public void update(){
        if(maze.getSolved() == false && maze.getPlayerOrientation()==1){
            player.animation.play();
            player.animation.setOffsetY(38);
            player.setImgCords(maze.getPlayerY()*20, maze.getPlayerX()*20);
        } else if (maze.getSolved() == false && maze.getPlayerOrientation()==3){
            player.animation.play();
            player.animation.setOffsetY(0);
            player.setImgCords(maze.getPlayerY()*20, maze.getPlayerX()*20);
        }else if (maze.getSolved() == false && maze.getPlayerOrientation()==2){
            player.animation.play();
            player.animation.setOffsetY(58);
            player.setImgCords(maze.getPlayerY()*20, maze.getPlayerX()*20);
        }
        else if (maze.getSolved() == false && maze.getPlayerOrientation()== 4){
            player.animation.play();
            player.animation.setOffsetY(20);
            player.setImgCords(maze.getPlayerY()*20, maze.getPlayerX()*20);
        }else{
            player.animation.stop();
            player.setImgCords(maze.getPlayerY()*20, maze.getPlayerX()*20);
        }
    }

    //  Displays the maze
    public void displayMaze(){
        for(int row = 0; row < mazeArray.length; row++){
            for (int column = 0; column < mazeArray[0].length; column++){
                if(mazeArray[row][column] == maze.getWall()){
                    img[row][column] = new ImageView(wallIMG);
                    img[row][column].setX(column*20);
                    img[row][column].setY(row*20);
                    pane.getChildren().addAll(img[row][column]);
                }
                else if (mazeArray[row][column] == maze.getPath()){
                    if(player.checkLocation(row,column)){
                        img[row][column] = new ImageView(path1IMG);
                        img[row][column].setX(column*20);
                        img[row][column].setY(row*20);
                        pane.getChildren().addAll(img[row][column]);
                        lpane.getChildren().addAll(player);
                    }else{
                        img[row][column] = new ImageView(path1IMG);
                        img[row][column].setX(column*20);
                        img[row][column].setY(row*20);
                        pane.getChildren().addAll(img[row][column]);
                    }
                }
                else if (mazeArray[row][column] == maze.getOpen()){
                    if(player.checkLocation(row,column)){
                        img[row][column] = new ImageView(path2IMG);
                        img[row][column].setX(column*20);
                        img[row][column].setY(row*20);
                        pane.getChildren().addAll(img[row][column]);
                        lpane.getChildren().addAll(player);
                    }else{
                        img[row][column] = new ImageView(path2IMG);
                        img[row][column].setX(column*20);
                        img[row][column].setY(row*20);
                        pane.getChildren().addAll(img[row][column]);
                    }
                }
                else if (mazeArray[row][column] == maze.getAttempted() || mazeArray[row][column] == maze.getNoSolution()){
                    if(player.checkLocation(row,column)){
                        img[row][column] = new ImageView(path2IMG);
                        img[row][column].setX(column*20);
                        img[row][column].setY(row*20);
                        pane.getChildren().addAll(img[row][column]);
                        lpane.getChildren().addAll(player);
                    }else{
                        img[row][column] = new ImageView(path2IMG);
                        img[row][column].setX(column*20);
                        img[row][column].setY(row*20);
                        pane.getChildren().addAll(img[row][column]);

                    }
                }
            }
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}

/**=====================================================
 *  This class handles end of game notifications! When
 *  'Exit' is clicked, the games shuts down.
 ======================================================*/

class NotifyDone {
    private Stage window = new Stage();
    private VBox pane = new VBox(15);
    private BorderPane borderPane = new BorderPane();
    private VBox btPane = new VBox(15);
    private Button exit = new Button("Exit");

    public NotifyDone(){

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("GAME OVER");
        window.setWidth(500);
        window.setHeight(200);
// setting the credits to allow for looping. made for shits and giggles.
        String credits =
                "\n\n\n\nDeveloper: James Grady\n\n\n"
                        + "Animation:   James Grady\n\n\n"
                        + "Logic:   James Grady\n\n\n"
                        + "PlayTester:   James Grady\n\n\n"
                        + "Artist:   \nWhoever Created the sprites... \nidk\n\n\n"
                        + "Music Producer:  \nEphixa...\n"
                        + "Thanks YouTube ;)\n\n\n\n"
                        + "Stress Management:  \n\nQueen Size Bed\n\n\n\n"
                        + "Stress team: \n\n"
                        + "Crown Royal\n"
                        + "TX Whiskey\n"
                        + "Lots of beer\n\n\n\n"
                        + "I Mean LOTS of beer...\n\n\n"
                        + "Person who will give me\n a A+++ on this project: \n\n " + "Tulin Kilinc \n\n\n\n"
                        + "Special Thanks: \n\n"
                        + "My Computer for not quitting on me.\n\n"
                        + "The QT for not running \nout of Monster and Redbull\n\n"
                        + "You guys for putting up \nwith this long credit reel\n\n\n\n\n"
                        + "Oh did you think this was DONE!?\n\n\n\n\n"
                        + "pfft... \n\n\n\n\n"
                        + "Plebs... \n\n\n\n"
                        + "There's more song left!!\n\n\n\n"
                        + "THE END!! "
                        + "BYE\nGOODBYE\n\n\n\n\n\n\n\n\n\n\nmore music... \n\n\n\n\n\n\n "
                        + "ARE YOU STILL HERE!? \n\n\n\nWHATS WRONG WITH YOU!?\n\n\n\n\n\n Well... its over now..\n\n LEAVE!\n\n\n\n\n\n\n\n\n\n\n SIKE! its still going...."
                        + "\n\n You know there is\n a exit button right...?";

        //  Style and Setup of credit roll
        Text creditText = new Text(credits);
        creditText.setTextOrigin(VPos.CENTER);
        creditText.setTextAlignment(TextAlignment.CENTER);
        creditText.setFill(Color.BLACK);
        creditText.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC,  18));

        double windowWidth = window.getWidth();
        double textHeight = creditText.getLayoutBounds().getHeight();

        pane.getChildren().addAll(creditText);
        pane.setStyle("-fx-border-color: black");

        exit.setPrefWidth(75);
        btPane.setPadding(new Insets(75,15,75,15));
        btPane.getChildren().addAll(exit);
        btPane.setStyle("-fx-border-color: black");
        exit.setAlignment(Pos.CENTER);

        //  close the program button action listener
        exit.setOnAction(event -> {
            System.exit(0);
        });

        borderPane.setCenter(pane);
        borderPane.setRight(btPane);

        borderPane.setStyle("-fx-background-image: url(\"images/exitScreen.jpg\");-fx-background-size: 500, 500;-fx-background-repeat: no-repeat;");
        pane.setPadding(new Insets(15));
        window.setScene(new Scene(borderPane));
        window.initStyle(StageStyle.TRANSPARENT);
        window.show();

        //==========================================
        //  Keyframe and Value are used to control
        //  the scrolling of the text to make it
        //  appear like credits.
        //==========================================
        KeyValue initialValue = new KeyValue(creditText.translateXProperty(), windowWidth);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initialValue);
        KeyValue endKeyValue = new KeyValue(creditText.translateYProperty(), -1.5
                * textHeight);
        KeyFrame endFrame = new KeyFrame(javafx.util.Duration.seconds(126), endKeyValue);
        Timeline timeline = new Timeline(initFrame, endFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
