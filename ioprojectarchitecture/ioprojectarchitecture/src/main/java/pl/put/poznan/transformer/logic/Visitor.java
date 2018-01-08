package pl.put.poznan.transformer.logic;

/**
 *
 */
public interface Visitor {
    /**
     * @param st
     */
    public void visit(MainStory st);

    /**
     * @param sb
     */
    public void visit(SubStory sb);

}
