package pl.put.poznan.transformer.logic;

/**
 *
 */
public interface Visitor {
    /**
     * @param st Mainstory hy hy
     */
    public void visit(MainStory st);

    /**
     * @param sb SUBstory hy hy
     */
    public void visit(SubStory sb);

}
