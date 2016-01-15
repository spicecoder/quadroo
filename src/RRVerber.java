import java.lang.reflect.Constructor;


public class RRVerber  extends QuadRR {

	  public RRVerber(QuadRR rr,String verb){
	  super(rr);
	Class cl; 
	
	String cmd = verb + "_S";
	 Class clarg;
	try {
		clarg = Class.forName("QuadRR");
	
	cl = Class.forName(cmd);
	System.out.println("Two Classes");
	System.out.println(cl);
	System.out.println(clarg);
	
	Class[] argTypes = {clarg};
		Constructor ctor = cl.getConstructor(argTypes);
		System.out.println("class construct: "+ctor);
		System.out.println("rr passed S:"+rr.S);
		// receive the reply from the verb action
		byD = (QuadRR)  (ctor.newInstance(rr));
		reply = byD.reply;	
		replystatus = byD.replystatus;
		//rr.statements.push(resorce.statement);
		    
	} catch ( Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // needed for constructor
	 
	
	  }
	
	
	

}
