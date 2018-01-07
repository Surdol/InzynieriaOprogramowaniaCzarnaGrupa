package pl.put.poznan.transformer.logic;

public interface Visitor {
    public void visit(MainStory st);

    public void visit(SubStory sb);

}
