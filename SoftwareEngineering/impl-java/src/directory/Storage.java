package directory;

import java.util.ArrayList;

public abstract class Storage implements IStorage {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String absAddr() {
        return null;
    }

    protected String name;
    protected int basicSize;
    protected Storage parent;
    protected ArrayList<Storage> children;
}
