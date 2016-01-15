//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;

public class RQSetVSW_S  extends QuadRR {
	public ArrayList<Integer>  dbwid = new ArrayList<Integer>();
	public ArrayList<Integer>  jsonwid = new ArrayList<Integer>();
	public ArrayList<HashMap> td = new ArrayList<HashMap>();

	public RQSetVSW_S(QuadRR rr) {	
		System.out.println("Get class loader "+this.getClass().getClassLoader());
		System.out.println("Get rquad rr class loader "+rr.getClass().getClassLoader());
		
		int sid =  rr.sid;
		int luid =rr.uid;
		int wid =rr.wid;
		String m = (rr.m);
		statement = rr.statement;
		ResultSet rs ;


		JSONArray jsonArray = rr.WA;

		if (jsonArray != null) { 
			int len = jsonArray.length();
			for (int i=0;i<len;i++){ 
				//System.out.println("inside for");
				try {
					//System.out.println("inside try");
					String name = jsonArray.getJSONObject(i).getString("name");
					String value = jsonArray.getJSONObject(i).getString("value");
					rs = statement.executeQuery("select wid from words where name = '"+name+"'");
					//System.out.println("select wid from words where name = '"+name+"'");
					if (rs.next()){
						//System.out.println("inside if");
						int x =rs.getInt(1);
						HashMap<Integer, String> hm = new HashMap<>();
						hm.put(x, value);
						td.add(hm);
						jsonwid.add(x);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		} 
		try {
			W.trim();
			rs = statement.executeQuery("select wid from quadroo where sid = " + sid+ "  AND fid = 0 ");
			if (rs.isBeforeFirst() ){
				while (rs.next()) {
					dbwid.add(rs.getInt(1));
				}
			}
			else {
				rr.replystatus="No Scene Found";
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				reply.put("result","error:" + e.getMessage());
				replystatus="error";
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("DBWID "+dbwid.toString());
		System.out.println("JSONWID "+jsonwid.toString());

		ArrayList<Integer> common = new ArrayList<Integer>(dbwid);
		common.retainAll(jsonwid);

		System.out.println("Common :"+common.toString());

		for (Integer wid1 : common) {

			System.out.println("TD :" + td);
			for (HashMap<Integer, String> hms : td) {
				System.out.println("Get key of hashmap "+hms.get(wid1));

				Collection<?> ck =hms.keySet();
				Iterator<?> itt = ck.iterator();
				while (itt.hasNext()){
					Integer ii = (Integer)itt.next();

					System.out.println("The keys :"+ii);		



					if(ii==wid1){
						System.out.println("Update now");			
						try {
							statement.executeUpdate("update quadroo set m = '" + hms.get(wid1)+ "' where wid="+wid1+" AND fid=0 AND sid="+sid);
							System.out.println("update quadroo set m = '" + hms.get(wid1)+ "' where wid="+wid1+" AND fid=0 AND sid="+sid);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}	
		}
	}
}