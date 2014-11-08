package ExpressionParser;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DiagramGenerator 
{
	// OFFSET X and Y will be used to the determined how long the line should be
	public static final double OFFSET_X = 100;
	public static final double OFFSET_Y = -100;
	
	// coordPointer is the point where the line should start and the length is determined by the OFFSET constants
	private Point2D.Double coordPointer;
	
	// Stores all Line2D object constructed inside constructLines. By calling initDraw() method, it will generate all lines
	private DrawLine lineDrawer;
	
	public DiagramGenerator ()
	{
		this.coordPointer = new Point2D.Double(OFFSET_X, (DrawLine.CANVAS_HEIGHT / 2)  - OFFSET_Y);
		this.lineDrawer = new DrawLine();
	}
	
	public void constructLines (ArrayList<OperationGroup> operationGroups)
	{
		// Add the Lines2D.Double object directly to the DrawLine object
		OperationGroup og = operationGroups.get(operationGroups.size() - 1);
		double[] pointerMovement = new double[] {0.0, 0.0}; // Keep track how far the coordPointer has moved
		if (og.getOperator().equalsIgnoreCase("||"))
		{
			og.generateDiagram(this.lineDrawer, operationGroups, this.coordPointer, true, false, 1, false);
		}
		else if (og.getOperator().equalsIgnoreCase("->"))
		{
			og.generateDiagram(this.lineDrawer, operationGroups, this.coordPointer, false , false, 1, false);
		}
	}
	
	public void generateDiagram ()
	{
		this.lineDrawer.initDraw();
	}
	
	public void printOutLine ()
	{
		for (Line2D.Double l: this.lineDrawer.getLines())
		{
			System.out.println("Start point:");
			System.out.println("X: " + l.x1);
			System.out.println("Y: " + l.y1);
			
			System.out.println("End point:");
			System.out.println("X: " + l.x2);
			System.out.println("Y: " + l.y2);
			System.out.println("");
		}
	}
}
