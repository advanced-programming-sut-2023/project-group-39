package main.java.model.map.tree;

public enum Tree {
    ;

    private String type;

    Tree(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
