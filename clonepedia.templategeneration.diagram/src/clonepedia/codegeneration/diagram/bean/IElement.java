package clonepedia.codegeneration.diagram.bean;

public interface IElement {
	public double computeSimilarity(IElement element);
	public boolean isMarked();
	public void setMarked(boolean mark);
	
	public Multiset getBelongingMultiset();
	public void setBelongingMultiset(Multiset multiset);
}
