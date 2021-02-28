package factory;

import com.java.tool.ObjectTools;

public class ProxyTest {
    public static void main(String[] args) {
        YQ yq = new YQ();
        YQ yy = yq;
//        ObjectFactory factory = new ObjectFactory();
//        YQInterface yy = factory.JdkCreateObject(YQ.class);
//        yy.say();
        YQ yq1 = ObjectTools.deepCopy(yq);
        System.out.println(yq == yq1);
        System.out.println(yq == yy);
        yq.say();
        yq1.say();
    }
}
