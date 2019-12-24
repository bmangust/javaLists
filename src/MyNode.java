import java.awt.*;

public class MyNode {
    private Object value = null;
    private MyNode next = null;

    public Object getValue() {
        return (value);
    }

    public void setValue(Object val) {
        this.value = val;
    }

    public void updateLink(MyNode addr) {
        this.next = addr;
    }

    public MyNode getNextNode() {
        return (next);
    }

}
