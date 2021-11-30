import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class HashMapApproach extends RubikCoreFunction {

    // - Instance Variables --------------------------------------------------------------------------------------------
    // arrays to track methods as well as rotations
    private static final Integer[] callnum = {0, 1, 2, 3, 4, 5};
    private static final String[] callout = {"A", "B", "C", "a", "b", "c"};
    private Integer[] count = { 0,0,0,0,0,0, 0,0,0,0, 0,0,0,0 };

    // hashtable to track solution and traversal
    private HashMap<String, String> table = new HashMap<>();
    // prevent any node from being reached more than once
    private HashMap<String, Integer> track = new HashMap<>();
    // -----------------------------------------------------------------------------------------------------------------
    // - Table Initiation ----------------------------------------------------------------------------------------------
    /**
     * initializeTable()
     *  initialize the tables with identity values
     *
     */
    public void initializeTable () {
        table.put( E, "E" );
        track.put( E, 0 );
    }
    // -----------------------------------------------------------------------------------------------------------------
    // - Find Match ----------------------------------------------------------------------------------------------------
    /**
     * find()
     *  find the solution if it is in table
     *
     * @param input String
     * @return String
     */
    public String find(String input)
    {
        return table.get(input);
    }
    // -----------------------------------------------------------------------------------------------------------------
    // - Constructor ---------------------------------------------------------------------------------------------------
    /**
     * HashMapApproach()
     *  constructor to initiate the table
     *
     */
    public HashMapApproach() { initializeTable(); }
    // -----------------------------------------------------------------------------------------------------------------
    // - Solve Method --------------------------------------------------------------------------------------------------
    /**
     * FastestSolution()
     *  find the fastest solution while generating the hashtable at the sametime
     *
     * @param Input String, n int
     * @return String
     */
    @Override
    public String FastestSolution( String Input ) {
        if( Input.length() != 21 ) { return "Incorrect input size."; }

        if( table.containsKey( Input ) ) {
            return table.get( Input );
        }

        // https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html#offer(E)
        // https://www.youtube.com/watch?v=91CMnJeHJVc
        Queue<String> states = new LinkedList<String>();
        states.offer(E);
        Queue<String> outputs = new LinkedList<String>();
        outputs.offer(" " );
        String tempState = "";
        String tempOutput = "";

        // https://www.youtube.com/watch?v=UD8rCe_QVOE
        BigInteger n = new BigInteger( "0" );
        final BigInteger max = new BigInteger( "58593750" );

        while( n.intValue() < max.intValue()) {
            tempState = states.peek();
            tempOutput = outputs.peek();


            // https://stackoverflow.com/questions/16588492/update-all-values-at-a-time-in-hashmap
            track.replaceAll( (k,v)->v=0 );

            int i = 0;
            while( !table.containsKey( Input ) && i < 6 ) {
                if( !table.containsKey( call(tempState, callnum[i] ) ) ) {
                    table.put( call(tempState, callnum[i] ), tempOutput + callout[i] );
                    count[tempOutput.length()-1] += 1;
                }
                if( call(tempState, callnum[i] ).equals(Input) ) {
                    return tempOutput + callout[i];
                }
                else if( !track.containsKey( call(tempState, callnum[i] ) ) ) {
                    track.put( call(tempState, callnum[i] ), 0 );
                }
                else {
                    track.put( call(tempState, callnum[i] ) , track.get( call(tempState, callnum[i] ) ) + 1 );
                }

                if( track.get( call(tempState, callnum[i] ) ) == 0 ) {
                    states.offer( call(tempState, callnum[i] ) );
                    outputs.offer( (tempOutput + callout[i]) );
                }
                i += 1;
            }

            states.poll();
            outputs.poll();
            n = n.add( new BigInteger("1") );
        }
        return "Solution not found.";
    }
    // -----------------------------------------------------------------------------------------------------------------
    // - Call Method ---------------------------------------------------------------------------------------------------
    /**
     * call()
     *  make it easier to call each rotation method
     *
     * @param Input String, n int
     * @return String
     */
    public String call ( String Input, int n ) {
        if( n == 0 ) {
            return A(Input);
        }
        else if( n == 1 ) {
            return B(Input);
        }
        else if( n == 2 ) {
            return C(Input);
        }
        else if( n == 3 ) {
            return Ap(Input);
        }
        else if( n == 4 ) {
            return Bp(Input);
        }
        else if( n == 5 ) {
            return Cp(Input);
        }
        else { return "wrong number call"; }
    }
    // - Information Method --------------------------------------------------------------------------------------------
    /**
     * information()
     *  generate information label for the GUI
     *
     * @return String
     */
    @Override
    public String information() {
        String output = "Number of unique cases in table: \n";
        for( int i = 0; i < 14; i++ ) {
            output += (i+1) + " number of rotations: " + count[i] + "\n";
        }
        return output;
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
        HashMapApproach h = new HashMapApproach();
        String test = E;

        test = h.A( test );
        test = h.Bp( test );
        test = h.C( test );

        System.out.println(h.FastestSolution( test ));
        System.out.println(h.table.values().toString());
        String countin = "";
        for( int i = 0; i < 14; i++ ) {
            countin += h.count[i] + " ";
        }
        System.out.println( countin );
        System.out.println(h.track.values().toString());

        test = h.A( test );
        test = h.Bp( test );

        // cannot use FastestSolution a second time for some reason
        System.out.println(h.FastestSolution( test ));
        System.out.println(h.table.values().toString());
        for( int i = 0; i < 14; i++ ) {
            countin += h.count[i] + " ";
        }
        System.out.println( countin );
        System.out.println(h.track.values().toString());
    }
}
