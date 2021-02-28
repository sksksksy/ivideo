package factory;

import java.io.Serializable;

public class YQ implements YQInterface, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void say() {
        System.out.println("hello");
    }
}
