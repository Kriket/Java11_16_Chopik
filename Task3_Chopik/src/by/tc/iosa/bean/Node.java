package by.tc.iosa.bean;

/**
 * Created by User on 19.01.2017.
 */
public class Node {

    private String content;
    private NodeType nodeType;

    public Node(String content, NodeType nodeType) {
        this.content = content;
        this.nodeType = nodeType;
    }

    public String getContent() {
        return content;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }
}
