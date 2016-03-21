/**
 * Facilitates dispensing stamps for a postage stamp machine.
 */
public class StampDispenser
{
    /**
     * Constructs a new StampDispenser that will be able to dispense the given 
     * types of stamps.
     *
     * @param stampDenominations The values of the types of stamps that the 
     *     machine should have.  Should be sorted in descending order and 
     *     contain at least a 1.
     */
    private int[] stampDenominations;

    public StampDispenser(int[] stampDenominations)
    { 
    	if(!isDescending(stampDenominations))
    	{
    		throw new IllegalArgumentException("Denominations array is not in descending order (i.e. largest to smallest)");
    	}
    	else if(stampDenominations[stampDenominations.length-1] != 1)
    	{
    		throw new IllegalArgumentException("Denominations array does not contain 1");
    	}  
    	else 
    	{
    		this.stampDenominations = stampDenominations;
    	} 
    	
    }
 
    /**
     * Returns the minimum number of stamps that the machine can dispense to
     * fill the given request.
     *
     * @param request The total value of the stamps to be dispensed.
     */
    public int calcMinNumStampsToFillRequest(int request)
    {  
    	int numStamps = 0;

    	for(int i = 0; i < stampDenominations.length; i++)
    	{
    		numStamps += request / stampDenominations[i];		// first finds the number of stamps that can be dispensed with current denomination
    		request = request % stampDenominations[i];			// then updates the request to reflect remainder

    		if(request == 0)
    			break;
    	}

        return numStamps;
    }
    
    public static void main(String[] args)
    {
        int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
        StampDispenser stampDispenser = new StampDispenser(denominations);
        assert stampDispenser.calcMinNumStampsToFillRequest(18) == 3;
    }
    

    // checks the array to make sure it is in descending order
    private boolean isDescending(int[] stampDenominations) 
    {
    	if(stampDenominations.length > 1) 
    	{
    		for(int i = 0; i < stampDenominations.length - 1; i++) 
    		{
    			if(stampDenominations[i]  < stampDenominations[i+1]) 
    				return false;
    		}
    	}
    	return true;
    }
}
