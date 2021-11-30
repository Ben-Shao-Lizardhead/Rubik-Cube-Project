import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ShapeCoreFunction extends Application {
    // - Instance Variables --------------------------------------------------------------------------------------------
    // set identity
    protected static final String E = "abcdefghijklmnopqrstu";
    // declare shape group g
    protected Group g = new Group();
    // other GUI elements and store current state
    protected String var = "abcdefghijklmnopqrstu";
    protected Label Current = new Label( MakeSpace() );
    protected Label Solution = new Label("Press solve button to view solution." );
    protected Label stateLabel = new Label( "Current state: " );
    protected TextField entry = new TextField( "enter block string here" );
    protected Label information = new Label( "Information" );
    protected Label spec = new Label( "" );
    // array list and array for GUI elements
    protected ArrayList<Rectangle> cube = new ArrayList<Rectangle>();
    // rotation buttons
    protected ArrayList<Button> o = new ArrayList<Button>();
    protected String name[] = {"A","B","C","A'","B'","C'"};

    // method classes instance
    protected RubikCoreFunction k = new RubikCoreFunction();

    // set solving method
    protected int method = 0;

    // to check permutation
    protected Integer index[] = {0,11,4, 1,9,10, 2,5,6, 3,7,8, 12,18,13, 14,19,15, 16,20,17};
    protected ArrayList<ArrayList<Character>> order = new ArrayList<>();
    {

        for ( int i = 0; i < 21; i += 3 ) {
            ArrayList<Character> c = new ArrayList<>();
            for( int j = 0; j < 3; j++ ) {
                c.add(E.charAt(index[i+j]));
            }
            order.add(c);
        }
    }
    // end of instance variables
    // -----------------------------------------------------------------------------------------------------------------


    // - Methods and Handler Classes -----------------------------------------------------------------------------------
    // - Construct Cube Method -----------------------------------------------------------------------------------------
    public Group makeCube() {
        for (int x = 0; x < 4; x++ ) {
            Rectangle c = new Rectangle( 70+(x%2)*35,(x/2)*35,30, 30 );
            c.setFill( Color.YELLOW );
            cube.add( c );
            g.getChildren().add( c );
        }

        for (int x = 0; x < 16; x++ ) {
            Rectangle c = new Rectangle( (x%8)*35,70 + (x/8)*35,30, 30 );
            if( x%8 == 0 || x%8 == 1 ) { c.setFill( Color.ORANGE ); }
            else if( x%8 == 2 || x%8 == 3 ) { c.setFill( Color.BLUE ); }
            else if( x%8 == 4 || x%8 == 5 ) { c.setFill( Color.RED ); }
            else { c.setFill( Color.GREEN ); }
            if( x != 8 && x != 15 ) { cube.add( c ); }
            g.getChildren().add( c );
        }

        for (int x = 0; x < 4; x++ ) {
            Rectangle c = new Rectangle( 70+(x%2)*35,140 + (x/2)*35,30, 30 );
            c.setFill( Color.GREY );
            if( x != 2 ) { cube.add( c ); }
            g.getChildren().add( c );
        }

        return g;
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - Input String Permute ------------------------------------------------------------------------------------------
    public void InputPermute( String Input ) {
        if( Input.length() != 21 ) { System.out.println( "Incorrect input size."); }
        else {
            for( int x = 0; x < Input.length(); x++ ) {
                if( Input.charAt(x) == 'a' || Input.charAt(x) == 'b'
                        || Input.charAt(x) == 'c' || Input.charAt(x) == 'd' ) {
                    cube.get( x ).setFill( Color.YELLOW );
                }
                else if( Input.charAt(x) == 'e' || Input.charAt(x) == 'f'
                        || Input.charAt(x) == 'm' ) {
                    cube.get( x ).setFill( Color.ORANGE );
                }
                else if( Input.charAt(x) == 'g' || Input.charAt(x) == 'h'
                        || Input.charAt(x) == 'n' || Input.charAt(x) == 'o' ) {
                    cube.get( x ).setFill( Color.BLUE );
                }
                else if( Input.charAt(x) == 'i' || Input.charAt(x) == 'j'
                        || Input.charAt(x) == 'p' || Input.charAt(x) == 'q' ) {
                    cube.get( x ).setFill( Color.RED );
                }
                else if( Input.charAt(x) == 'k' || Input.charAt(x) == 'l'
                        || Input.charAt(x) == 'r' ) {
                    cube.get( x ).setFill( Color.GREEN );
                }
                else { cube.get( x ).setFill( Color.GREY ); }
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    // - Operation Button Actions --------------------------------------------------------------------------------------
    class ButtonUnknown implements EventHandler<ActionEvent> {
        private int s = 0;

        public ButtonUnknown( int k ) {
            s = k;
        }
        @Override
        public void handle(ActionEvent event) {
            RubikCoreFunction k = new RubikCoreFunction();
            if ( s == 0 ) {
                var = k.A(var);
                InputPermute( var );
                Current.setText( MakeSpace() );
            }
            else if ( s == 1 ) {
                var = k.B(var);
                InputPermute( var );
                Current.setText( MakeSpace() );
            }
            else if ( s == 2 ) {
                var = k.C(var);
                InputPermute( var );
                Current.setText( MakeSpace() );
            }
            else if ( s == 3 ) {
                var = k.Ap(var);
                InputPermute( var );
                Current.setText( MakeSpace() );
            }
            else if ( s == 4 ) {
                var = k.Bp(var);
                InputPermute( var );
                Current.setText( MakeSpace() );
            }
            else {
                var = k.Cp(var);
                InputPermute( var );
                Current.setText( MakeSpace() );
            }
        }
    }
    // -----------------------------------------------------------------------------------------------------------------
    // - Method Button -------------------------------------------------------------------------------------------------
    class ChooseMethod implements EventHandler<ActionEvent> {
        private int m = 0;
        public ChooseMethod( int i ) {
            m = i;
        }
        @Override
        public void handle(ActionEvent event) {
            if(m == 0) {
                Solution.setText( "By Brute Force" );
                method = 0;
            }
            else if(m == 1) {
                Solution.setText( "By Hash Table" );
                method = 1;
            }
        }
    }
    // -----------------------------------------------------------------------------------------------------------------
    // - Solve Button Action -------------------------------------------------------------------------------------------
    class SolveButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            HashMapApproach h = new HashMapApproach();

            String output = "";

            if( method == 0 ) {
                output = k.FastestSolution( var );
                spec.setText( k.information() );
            }
            else if( method == 1) {
                output = h.FastestSolution( var );
                spec.setText( h.information() );
            }
            // translate output
            int i = 0, m = output.length();
            while( i < m ) {
                if( output.charAt(i) == 'a' ) {
                    output = output.substring(0,i) + "A' " + output.substring( i+1 );
                m += 2;
                i += 3;
                }
                else if( output.charAt(i) == 'b' ) {
                    output = output.substring(0,i) + "B' " + output.substring( i+1 );
                    m += 2;
                    i += 3;
                }
                else if( output.charAt(i) == 'c' ) {
                    output = output.substring(0,i) + "C' " + output.substring( i+1 );
                    m += 2;
                    i += 3;
                }
                else { i += 1;}
            }

            Solution.setText( output );
        }
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - User Entry ----------------------------------------------------------------------------------------------------
    class EntryButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String Input = entry.getText().replace(" ", "" );
            if ( !CheckInput(Input) ) {
                entry.setText( "illegal input" );
            }

            else if ( !CheckPermutation(Input) ) {
                entry.setText( "illegal permutation" );
            }

            else {
                InputPermute( Input );
                var = Input;
                Current.setText( MakeSpace() );
            }
        }
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - Check Input ---------------------------------------------------------------------------------------------------
    public Boolean CheckInput( String Input ) {
        int exist = 0;
        if ( Input.length() != 21 ) {
            return false;
        }
        for ( int i = 0; i < 21; i++ ) {
            for ( int j = 0; j < 21; j++ ) {
                if ( Input.charAt(j) == E.charAt(i) ) {
                    exist += 1;
                }
            }
            if ( exist != 1 ) { return false; }
            else { exist = 0; }
        }
        return true;
    }
    // -----------------------------------------------------------------------------------------------------------------

    public Boolean CheckPermutation( String Input ) {
        Character temp;
        int curb = -1, curc = -1;
        for( int i = 0; i < 7; i++ ) {
            temp = Input.charAt( index[3*i] );
            for( int j = 0; j < 7; j++ ) {
                if( order.get(j).contains( temp ) ) {
                    curb = j;
                    curc = order.get(j).indexOf( temp );
                }
            }

            if( curb == -1 || curc == -1 ) {
                return false;
            }
            else if( Input.charAt(index[3*i + 1]) != order.get(curb).get((curc+1)%3) ) {
                return false;
            }
            else if( Input.charAt(index[3*i + 2]) != order.get(curb).get((curc+2)%3) ) {
                return false;
            }
        }
        return true;
    }

    // - Reset ---------------------------------------------------------------------------------------------------------
    class ResetButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            var = E;
            Current.setText( MakeSpace() );
            InputPermute( var );
        }
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - MakeSpace -----------------------------------------------------------------------------------------------------
    public String MakeSpace() {
        String output = var.substring( 0, 4 ) + " " + var.substring( 4, 8 ) + " " + var.substring( 8, 12 ) +
                " " + var.substring( 12, 15 ) + " " + var.substring( 15, 18 ) + " " + var.substring( 18 );
        return output;
    }
    // -----------------------------------------------------------------------------------------------------------------
}
