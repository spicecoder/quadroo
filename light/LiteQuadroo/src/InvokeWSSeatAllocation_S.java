import java.io.IOException;
import java.net.MalformedURLException;

public class InvokeWSSeatAllocation_S extends QuadRR {
	InvokeWSSeatAllocation_S(QuadRR rr){ 
		super(rr);
		InvokeSeatAllocationWS  WebserviceTester =
				new InvokeSeatAllocationWS();
				try {
				 WebserviceTester.translateWord("amusing ");
				} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
				e.printStackTrace();
				}
				}   
	
	public static void main(String[] args) {
		InvokeSeatAllocationWS  WebserviceTester =
		new InvokeSeatAllocationWS();
		try {
		 WebserviceTester.translateWord("amusing ");
		} catch (MalformedURLException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
}
