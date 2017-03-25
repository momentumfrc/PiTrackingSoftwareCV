
import edu.wpi.first.wpilibj.networktables.*;

public class Main {
	static localTable vT;
	
	public static String coord(double[] in) {
		if(in.length != 2) {
			System.err.println("What?");
			return "( , )";
		}
		return "(" + in[0] + ", " + in[1] + ")";
	}

	public static void main(String[] args) {
		NetworkTable.setServerMode();
		NetworkTable.setTeam(4999);
		NetworkTable.initialize();
		vT = new localTable("visionTable");
		while(true){
			System.out.println("Pt1: " + coord(vT.pt1));
			System.out.println("Pt2: " + coord(vT.pt2));
			System.out.println("Mid: " + coord(vT.center));
			System.out.println("LWH: " + coord(vT.lWH));
			System.out.println("RWH: " + coord(vT.rWH));
			System.out.println("Dst: " + vT.dist);
			System.out.println("");
		}

	}

}

class localTable {
	private NetworkTable table;
	
	Boolean foundTarget;
	double[] pt1; // X,Y
	double[] pt2;
	double[] center;
	double[] lWH; // Width, Height
	double[] rWH;
	double dist; // Distance between centers
	
	localTable(String table) {
		this.table = NetworkTable.getTable(table);
		updateTable();
	}
	
	public void updateTable() {
		pt1[0] = table.getNumber("x1", -1);
		pt1[1] = table.getNumber("y1", -1);
		
		pt2[0] = table.getNumber("x2", -1);
		pt2[1] = table.getNumber("y2", -1);
		
		center[0] = table.getNumber("cx", -1);
		center[1] = table.getNumber("cy", -1);
		
		lWH[0] = table.getNumber("wL", -1);
		lWH[1] = table.getNumber("hL", -1);
		
		rWH[0] = table.getNumber("wR", -1);
		rWH[1] = table.getNumber("hR", -1);
	}
	
}