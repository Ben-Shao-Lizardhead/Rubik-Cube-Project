import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends ShapeCoreFunction {
    // - Scene and Action ----------------------------------------------------------------------------------------------
    @Override
    public void start(Stage primaryStage) throws Exception {
        RubikCoreFunction k = new RubikCoreFunction();
        Group rubik = makeCube();

        for ( int x = 0; x < 6; x++ ) {
            Button button = new Button( name[x] );
            o.add( button );
            button.setOnAction( new ShapeCoreFunction.ButtonUnknown( x ) );
        }

        Button brute = new Button( "Brute Force" );
        brute.setOnAction( new ChooseMethod( 0 ) );
        Button hash = new Button( "Hash Table" );
        hash.setOnAction( new ChooseMethod( 1 ) );

        Button solve = new Button( "solve" );
        solve.setOnAction( new ShapeCoreFunction.SolveButton() );

        Button enter = new Button( "set value" );
        enter.setOnAction( new ShapeCoreFunction.EntryButton() );

        Button reset = new Button( "reset" );
        reset.setOnAction( new ShapeCoreFunction.ResetButton() );

        entry.setMinWidth( 300 );
        information.setMinWidth( 300 );

        //InputPermute( "abcdefghijklmnopqrstu" );

        HBox UserInput = new HBox( 10, enter, entry );
        HBox curState = new HBox( 20, stateLabel, Current );
        HBox Solving = new HBox( 10, solve, Solution );
        HBox opertionButtons = new HBox( 10, o.get(0), o.get(1), o.get(2), o.get(3),
                o.get(4), o.get(5), reset );
        HBox methods = new HBox( brute, hash );
        VBox mainSurface = new VBox( 10, UserInput, curState, Solving, opertionButtons, rubik, methods );
        VBox info = new VBox( 10, information, spec );
        HBox Whole = new HBox( 20, mainSurface, info );
        //Solving.setAlignment( Pos.CENTER );
        //curState.setAlignment( Pos.CENTER );
        //mainSurface.setAlignment( Pos.CENTER );
        //opertionButtons.setAlignment( Pos.CENTER );
        //UserInput.setAlignment( Pos.CENTER );

        Whole.setPadding( new Insets( 50 ) );

        Scene scene = new Scene( Whole );

        primaryStage.setTitle( "Rubik Prototype" );
        primaryStage.setScene( scene );
        // show the stage
        primaryStage.show();
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - Launch Stage --------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        // get everything running
        Application.launch( args );
    }
    // -----------------------------------------------------------------------------------------------------------------

}
