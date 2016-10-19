package clonepedia.templategeneration.diagram.layout;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.internal.commands.IPropertyValueDeferred;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.requests.ChangeBoundsDeferredRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.requests.SetAllBendpointRequest;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;

import template_model.Type;
import template_model.diagram.edit.parts.Class2EditPart;
import template_model.diagram.edit.parts.Field2EditPart;
import template_model.diagram.edit.parts.Field3EditPart;
import template_model.diagram.edit.parts.FieldEditPart;
import template_model.diagram.edit.parts.Method3EditPart;
import template_model.diagram.edit.parts.Method4EditPart;
import template_model.diagram.edit.parts.Method5EditPart;
import template_model.diagram.edit.parts.MethodEditPart;
import template_model.diagram.edit.parts.TemplateGraphEditPart;

@SuppressWarnings("restriction")
//@SuppressWarnings("rawtypes")
public class TemplateLayoutProvider extends AbstractLayoutEditPartProvider {
	public boolean provides(IOperation operation) {
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public Command layoutEditParts(
		GraphicalEditPart containerEP,
		IAdaptable layoutHint) {

		List children = containerEP.getChildren();
		return layout(containerEP, children);
	}

	@SuppressWarnings("rawtypes")
	public Command layoutEditParts(
		List selectedObjects,
		IAdaptable layoutHint) {

		if (selectedObjects.size()== 0){
			return null;
		}
		
		GraphicalEditPart editPart = (GraphicalEditPart) selectedObjects.get(0);		
		GraphicalEditPart containerEditPart = (GraphicalEditPart) editPart.getParent();
		
		return layout(containerEditPart, selectedObjects);
	}

	/**
	 * Method layout.
	 * 
	 * @param layoutType
	 * @param containerEP
	 * @param selectedObjects
	 * @param rootEP
	 * @return Command
	 * @throws InvalidParameterException
	 *             if either parameter is null.
	 */
	@SuppressWarnings("rawtypes")
	public Command layout(GraphicalEditPart containerEP, List selectedObjects) {

		if (containerEP == null || selectedObjects == null) {
			InvalidParameterException ipe = new InvalidParameterException();
			throw ipe;
		}

		ArrayList<ShapeEditPart> shapeParts = new ArrayList<>();
		ArrayList<ConnectionNodeEditPart> connectionParts = new ArrayList<>();

		for(Object obj: selectedObjects) {
			if (obj instanceof ShapeEditPart) {
				shapeParts.add((ShapeEditPart)obj);
			}
			else if(obj instanceof ConnectionNodeEditPart){
				connectionParts.add((ConnectionNodeEditPart)obj);
			}
		}

		CompoundCommand cc = new CompoundCommand(""); 
		
		TemplateLayout templateLayout = new TemplateLayout(containerEP, shapeParts, connectionParts);
		Command positionCommand = templateLayout.calculateLayout();
		if(positionCommand != null){
			cc.add(positionCommand);
		}
		
		Request req = new Request(RequestConstants.REQ_REFRESH);
		Command cmd = containerEP.getCommand(req);
		if (cmd != null){
			cc.add(cmd);			
		}
		
		return cc;
	}
	
	/**
	 * @author sshaw
	 * 
	 * Helper class to build the radial layout based on a root editpart.
	 */
	class TemplateLayout {
		
		class AreaData{
			ArrayList<Command> commands = new ArrayList<>();
			int height;
			/**
			 * @return the commands
			 */
			public ArrayList<Command> getCommands() {
				return commands;
			}
			/**
			 * @param commands the commands to set
			 */
			public void setCommands(ArrayList<Command> commands) {
				this.commands = commands;
			}
			/**
			 * @return the height
			 */
			public int getHeight() {
				return height;
			}
			/**
			 * @param height the height to set
			 */
			public void setHeight(int height) {
				this.height = height;
			}
			
			
		}
		
		private int margin = 10;
		
		private GraphicalEditPart containerEP;;
		private ArrayList<ShapeEditPart> shapeParts;
		private ArrayList<ConnectionNodeEditPart> connectionParts;
		
		public TemplateLayout(GraphicalEditPart containerEP, ArrayList<ShapeEditPart> shapeParts, ArrayList<ConnectionNodeEditPart> connectionParts) {
			this.containerEP = containerEP;
			this.shapeParts = shapeParts;
			this.connectionParts = connectionParts;
		}

		public Command calculateLayout() {
			CompoundCommand cc = new CompoundCommand("");
			
			/**
			 * inside some class or interface
			 */
			if(!(containerEP instanceof TemplateGraphEditPart)){
				int maxColumn = 5;
				int maxWidth = computeContainerWidth(shapeParts, maxColumn, margin);
				
				int startHeightForMethodArea = 0; 
				int startHeightForInnerClassArea = 0;
				
				ArrayList<ShapeEditPart> fieldParts = findFields(shapeParts);
				ArrayList<ShapeEditPart> methodParts = findMethods(shapeParts);
				ArrayList<ShapeEditPart> innerClassParts = findInnerClasses(shapeParts);
				
				if(fieldParts.size() > 0){
					AreaData fieldArea = calculateArea(fieldParts, maxWidth, maxColumn, margin, 0);
					addToCommand(cc, fieldArea.getCommands());
					startHeightForMethodArea = fieldArea.getHeight();
				}
				
				if(methodParts.size() > 0){
					AreaData methodArea = calculateArea(methodParts, maxWidth, maxColumn, 
							margin, startHeightForMethodArea);
					addToCommand(cc, methodArea.getCommands());
					startHeightForInnerClassArea = methodArea.getHeight();
				}
				
				if(innerClassParts.size() > 0){
					AreaData innerClassArea = calculateArea(innerClassParts, maxWidth, maxColumn, 
							margin, startHeightForInnerClassArea);
					addToCommand(cc, innerClassArea.getCommands());
				}
			}
			/**
			 * top level
			 */
			else{
				margin = 15;
				int maxColumn = 5;
				int maxWidth = computeContainerWidth(shapeParts, maxColumn, margin);
				
				int startHeightForConcrete = 0;
				
				ArrayList<ShapeEditPart> abstractTypes = findAbstractTypes(shapeParts);
				ArrayList<ShapeEditPart> concreteTypes = findConcreteTypes(shapeParts);
				
				if(abstractTypes.size() > 0){
					AreaData abArea = calculateArea(abstractTypes, maxWidth, maxColumn, margin, 0);
					addToCommand(cc, abArea.getCommands());
					startHeightForConcrete = abArea.getHeight();
				}
				
				if(concreteTypes.size() > 0){
					AreaData concreteArea = calculateArea(concreteTypes, maxWidth, maxColumn, margin, startHeightForConcrete);
					addToCommand(cc, concreteArea.getCommands());
				}
			}
			
			for(ConnectionNodeEditPart connection: connectionParts){
				Command cmd = routeConnection(connection);
				cc.add(cmd);
			}
			
			return cc;
		}

		private AreaData calculateArea(ArrayList<ShapeEditPart> editParts, 
				int maxWidth, int maxColumn, int margin, int startHeight) {
			
			AreaData area = new AreaData();
			
			int currentWidth = margin;
			int currentHeight = startHeight + margin;
			
			int currentSizeInOneRow = 0;
			
			ArrayList<Command> cmdList = new ArrayList<>();
			ArrayList<Integer> heightsInRow = new ArrayList<>();
			
			
			for(int i=0; i<editParts.size(); i++){
				int pointX = currentWidth;
				int pointY = currentHeight;
				EditPartPosition p = new EditPartPosition(new Point(pointX, pointY));
				
				ShapeEditPart sep = editParts.get(i);
				Command cmd = constructLocationCommand(sep, p);
				cmdList.add(cmd);
				
				IFigure figure = sep.getFigure();
				
				currentWidth += figure.getBounds().width;
				currentWidth += margin;
				
				heightsInRow.add(figure.getBounds().height);
				
				currentSizeInOneRow++;
				
				if(i < editParts.size()-1){
					int currentWidthOfNext = currentWidth + margin + editParts.get(i+1).getFigure().getBounds().width;
					if(currentSizeInOneRow > maxColumn || currentWidthOfNext > maxWidth){
						currentWidth = margin;
						int largestHeight = findLargestHeight(heightsInRow);
						currentHeight += (margin+largestHeight);
						
						currentSizeInOneRow = 0;
						heightsInRow.clear();
					}
				} 
			}
			
			int largestHeight = findLargestHeight(heightsInRow);
			area.setCommands(cmdList);
			area.setHeight(currentHeight + margin + largestHeight);
			
			return area;
		}
		
		private int findLargestHeight(ArrayList<Integer> heights){
			int height = 0;
			for(Integer h: heights){
				if(h > height){
					height = h;
				}
			}
			
			return height;
		}

		private ArrayList<ShapeEditPart> findInnerClasses(ArrayList<ShapeEditPart> shapeParts2) {
			ArrayList<ShapeEditPart> sepList = new ArrayList<>();
			for(ShapeEditPart sep: shapeParts2){
				if(sep instanceof Class2EditPart){
					sepList.add(sep);
				}
			}
			return sepList;
		}

		private ArrayList<ShapeEditPart> findMethods(ArrayList<ShapeEditPart> shapeParts2) {
			ArrayList<ShapeEditPart> sepList = new ArrayList<>();
			for(ShapeEditPart sep: shapeParts2){
				if(sep instanceof Method3EditPart || sep instanceof Method4EditPart || 
						sep instanceof Method5EditPart || sep instanceof MethodEditPart){
					sepList.add(sep);
				}
			}
			return sepList;
		}

		private ArrayList<ShapeEditPart> findFields(
				ArrayList<ShapeEditPart> shapeParts2) {
			ArrayList<ShapeEditPart> sepList = new ArrayList<>();
			for(ShapeEditPart sep: shapeParts2){
				if(sep instanceof FieldEditPart || sep instanceof Field2EditPart
						|| sep instanceof Field3EditPart){
					sepList.add(sep);
				}
			}
			return sepList;
		}

		private int computeContainerWidth(ArrayList<ShapeEditPart> shapeParts2, int maxColumn, int margin) {
			int largestWidth = 0;
			int sum = 0;
			
			for(ShapeEditPart sep: shapeParts2){
				int width = sep.getFigure().getBounds().width;
				if(width > largestWidth){
					largestWidth = width;
				}
				
				sum += (width+margin);
			}
			sum += margin;
			
			int average = sum/shapeParts2.size() + 1;
			int standardWidth = average*maxColumn + margin*(maxColumn+1);
			
			return (standardWidth > largestWidth)? standardWidth : largestWidth;
		}

		private ArrayList<ShapeEditPart> findConcreteTypes(
				ArrayList<ShapeEditPart> shapeParts2) {
			ArrayList<ShapeEditPart> sepList = new ArrayList<>();
			for(ShapeEditPart sep: shapeParts2){
				EObject e = sep.resolveSemanticElement();
				Type type = (Type)e;
				if(type.getSupportingElements() != null && type.getSupportingElements().size() >= 2){
					sepList.add(sep);
				}
			}
			return sepList;
		}

		private ArrayList<ShapeEditPart> findAbstractTypes(
				ArrayList<ShapeEditPart> shapeParts2) {
			ArrayList<ShapeEditPart> sepList = new ArrayList<>();
			for(ShapeEditPart sep: shapeParts2){
				EObject e = sep.resolveSemanticElement();
				Type type = (Type)e;
				if(type.getSupportingElements() == null || type.getSupportingElements().size() < 2){
					sepList.add(sep);
				}
			}
			return sepList;
		}

		private Command constructLocationCommand(ShapeEditPart sep, EditPartPosition pos){
			ChangeBoundsDeferredRequest request = new ChangeBoundsDeferredRequest(pos);
			Command cmd = sep.getCommand(request);
			return cmd;
		}

		private void addToCommand(CompoundCommand cc, ArrayList<Command> list){
			for(Command c: list){
				cc.add(c);
			}
		}


		/**
		 * Method routeConnections.
		 * 
		 * @param connections
		 *            List of connections that need to be routed.
		 * @return Command
		 */
		@SuppressWarnings("rawtypes")
		protected Command routeConnection(List connections) {

			CompoundCommand cc = new CompoundCommand(""); //$NON-NLS-1$
			ListIterator li = connections.listIterator();

			while (li.hasNext()) {
				EditPart editpart = (EditPart) li.next();
				if (editpart instanceof ConnectionNodeEditPart) {
					Command cmd =
						routeConnection((ConnectionNodeEditPart) editpart);
					if (cmd != null)
						cc.add(cmd);
				}
			}

			if (!cc.isEmpty())
				return cc;

			return null;
		}

		/**
		 * Method routeConnection. Route the given connection accordingly to the
		 * layout algorithm. TBD utilize the "avoid obstructions" routing.
		 * 
		 * @param connectionEP
		 *            ConnectionNodeEditPart connection to be routed.
		 * @return Command
		 */
		protected Command routeConnection(ConnectionNodeEditPart connectionEP) {
			if (connectionEP == null)
				throw new InvalidParameterException();

			// reset connections

			Connection connection = connectionEP.getConnectionFigure();
			PointList newPoints = new PointList(2);
			newPoints.addPoint(connection.getPoints().getFirstPoint());
			newPoints.addPoint(connection.getPoints().getLastPoint());
			SetAllBendpointRequest request =
				new SetAllBendpointRequest(
					RequestConstants.REQ_SET_ALL_BENDPOINT,
					newPoints);

			// recurse through the children to get the compound command
			return connectionEP.getCommand(request);
		}

	}
	
	class EditPartPosition implements IAdaptable {
		//private ShapeEditPart sep;
		private Point ptLocation;

		public EditPartPosition(Point point) {
			//this.sep = ep;
			this.ptLocation = point;
		}
		
		public String toString(){
			if(ptLocation != null){
				return ptLocation.toString();				
			}
			else{
				return "not assigned";
			}
		}

		/**
		 * Method getAdapter.
		 * 
		 * @param adapterType
		 * @return Object
		 */
		@SuppressWarnings("rawtypes")
		public Object getAdapter(Class adapterType) {
			if (adapterType == IPropertyValueDeferred.class) {
				return getPosition();
			}

			return null;
		}

		/**
		 * Method getPosition. Calculates the point based on stored
		 * information about the radius and index of the edit part.
		 * 
		 * @return Point
		 */
		public Point getPosition() {
//			if (null == ptLocation) {
//				ptLocation = new Point(0, 0);
//			}
			
			return ptLocation;
		}
	}
}
