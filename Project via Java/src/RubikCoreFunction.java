import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

public class RubikCoreFunction {

    protected static final String E = "abcdefghijklmnopqrstu";
    private String count = "";

    // - Counterclockwise Rotations ------------------------------------------------------------------------------------
    /**
     * A()
     *  counterclockwise rotation on top
     *
     * @param s String
     * @return String
     */
    public String A( String s ) {
        String output;

        output = stringPermutation( s, 0, 1, 2, 3, 1, 3, 0, 2 );
        output = stringPermutation( output, 4, 6, 8, 10, 10, 4, 6, 8 );
        output = stringPermutation( output, 5, 7, 9, 11, 11, 5, 7, 9 );

        return output;
    }
    /**
     * B()
     *  counterclockwise rotation on front
     *
     * @param s String
     * @return String
     */
    public String B( String s ) {
        String output;

        output = stringPermutation( s, 6, 7, 13, 14, 7, 14, 6, 13 );
        output = stringPermutation( output, 3, 5, 15, 18, 15, 3, 18, 5 );
        output = stringPermutation( output, 2, 8, 12, 19, 8, 19, 2, 12 );

        return output;
    }
    /**
     * C()
     *  counterclockwise rotation on side
     *
     * @param s String
     * @return String
     */
    public String C( String s ) {
        String output;

        output = stringPermutation( s, 8, 9, 15, 16, 9, 16, 8, 15 );
        output = stringPermutation( output, 1, 7, 17, 19, 17, 1, 19, 7 );
        output = stringPermutation( output, 3, 10, 14, 20, 10, 20, 3, 14 );

        return output;
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - Clockwise Rotations -------------------------------------------------------------------------------------------
    /**
     * Ap()
     *  clockwise rotation on top
     *
     * @param s String
     * @return String
     */
    public String Ap( String s ) {
        String output;

        output = stringPermutation( s, 0, 1, 2, 3, 2, 0, 3, 1 );
        output = stringPermutation( output, 4, 6, 8, 10, 6, 8, 10, 4 );
        output = stringPermutation( output, 5, 7, 9, 11, 7, 9, 11, 5 );

        return output;
    }
    /**
     * Bp()
     *  clockwise rotation on front
     *
     * @param s String
     * @return String
     */
    public String Bp( String s ) {
        String output;
        output = "";

        output = stringPermutation( s, 6, 7, 13, 14, 13, 6, 14, 7 );
        output = stringPermutation( output, 3, 5, 15, 18, 5, 18, 3, 15 );
        output = stringPermutation( output, 2, 8, 12, 19, 12, 2, 19, 8 );

        return output;
    }
    /**
     * Cp()
     *  clockwise rotation on Side
     *
     * @param s String
     * @return String
     */
    public String Cp( String s ) {
        String output;

        output = stringPermutation( s, 8, 9, 15, 16, 15, 8, 16, 9 );
        output = stringPermutation( output, 1, 7, 17, 19, 7, 19, 1, 17 );
        output = stringPermutation( output, 3, 10, 14, 20, 14, 3, 20, 10 );

        return output;
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - Core Permutation Function -------------------------------------------------------------------------------------
    /**
     * stringPermutation()
     *  do permutation based on indicated side
     *
     * @param s String
     * @param a, b, c, d, e, f, g, h int
     * @return String
     */
    public String stringPermutation( String s, int a, int b, int c, int d, int e, int f, int g, int h ) {
        String output;
        if ( s.length() == 0 ) { return "";}

        output = s.substring( 0, a ) + s.charAt( e) +
                s.substring( a + 1, b ) + s.charAt( f) +
                s.substring( b + 1, c ) + s.charAt( g) +
                s.substring( c + 1, d ) + s.charAt( h) +
                s.substring( d + 1 );

        return output;
    }
    // -----------------------------------------------------------------------------------------------------------------

    // - Generate Fastest Solution -------------------------------------------------------------------------------------
    /**
     * FastestSolution()
     *  find the fastest solution using brute force bfs
     *
     * @param Input String
     * @return String
     */
    public String FastestSolution( String Input ) {
        if( Input.length() != 21 ) { return "Incorrect input size."; }
        if( Input.equals( E ) ) {
            return "E";
        }

        // https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html#offer(E)
        // https://www.youtube.com/watch?v=91CMnJeHJVc
        Queue<String> states = new LinkedList<>();
        states.offer(Input);
        Queue<String> outputs = new LinkedList<>();
        outputs.offer(" " );
        String tempState;
        String tempOutput;

        // https://www.youtube.com/watch?v=UD8rCe_QVOE
        BigInteger n = new BigInteger( "0" );
        final BigInteger max = new BigInteger( "58593750" );

        while( n.intValue() < max.intValue() ) {
            tempState = states.peek();
            tempOutput = outputs.peek();

            assert tempOutput != null;
            if( tempOutput.charAt(tempOutput.length()-1) != 'a' ) {
                states.offer(A(tempState));
                outputs.offer((tempOutput + "A"));
                if (A(tempState).equals(E)) {
                    count = n.toString();
                    return tempOutput + "A";
                }
            }

            if( tempOutput.charAt(tempOutput.length()-1) != 'b' ) {
                states.offer(B(tempState));
                outputs.offer((tempOutput + "B"));
                if (B(tempState).equals(E)) {
                    count = n.toString();
                    return tempOutput + "B";
                }
            }

            if( tempOutput.charAt(tempOutput.length()-1) != 'c' ) {
                states.offer(C(tempState));
                outputs.offer((tempOutput + "C"));
                if (C(tempState).equals(E)) {
                    count = n.toString();
                    return tempOutput + "C";
                }
            }

            if( tempOutput.charAt(tempOutput.length()-1) != 'A' ) {
                states.offer(Ap(tempState));
                outputs.offer((tempOutput + "a"));
                if (Ap(tempState).equals(E)) {
                    count = n.toString();
                    return tempOutput + "a";
                }
            }

            if( tempOutput.charAt(tempOutput.length()-1) != 'B' ) {
                states.offer(Bp(tempState));
                outputs.offer((tempOutput + "b"));
                if (Bp(tempState).equals(E)) {
                    count = n.toString();
                    return tempOutput + "b";
                }
            }

            if( tempOutput.charAt(tempOutput.length()-1) != 'C' ) {
                states.offer(Cp(tempState));
                outputs.offer((tempOutput + "c"));
                if (Cp(tempState).equals(E)) {
                    count = n.toString();
                    return tempOutput + "c";
                }
            }

            states.poll();
            outputs.poll();
            n = n.add( new BigInteger("1") );
        }
        return "Solution not found.";
    }
    // -----------------------------------------------------------------------------------------------------------------
    // - Main ----------------------------------------------------------------------------------------------------------
    /**
     * main()
     *  testing the codes
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        RubikCoreFunction k = new RubikCoreFunction();
        String test = "abcdefghijklmnopqrstu";
        test = k.Cp( test );
        System.out.println( test );

        String output;
        String a;
        a = "";

        a = k.B( test );
        a = k.A( a );
        a = k.B( a );
        a = k.C( a );
        output = k.FastestSolution( a );
        System.out.println( output );
        a = k.Bp(k.Ap(k.Bp(k.Cp(k.A(a)))));
        System.out.println( a );
        System.out.println( k.count );

        BigInteger n = new BigInteger( "0" );
        BigInteger b = new BigInteger( "1" );
        n = n.add( b );
        System.out.println(n);

        output = k.FastestSolution( k.A(a) );
        System.out.println( output );
    }
    // -----------------------------------------------------------------------------------------------------------------
    // - Information Method --------------------------------------------------------------------------------------------
    /**
     * information()
     *  generate information label for the GUI
     *
     * @return String
     */
    public String information() {
        return "Number of cases checked: " + count;
    }
}
