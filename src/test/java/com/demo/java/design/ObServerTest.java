package com.demo.java.design;

import com.demo.java.BaseTest;
import com.demo.java.design.observer.ConcreteObserver;
import com.demo.java.design.observer.ConcreteSubject;
import com.demo.java.design.observer.Observer;

/**
 * 观察者模式测试
 *
 * @author zhanghanlin
 */
public class ObServerTest extends BaseTest {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer_a = new ConcreteObserver("A", subject);
        Observer observer_b = new ConcreteObserver("B", subject);
        subject.pushMessage("测试观察者消息1");
        subject.pushMessage("测试观察者消息2");
        subject.deleteObserver(observer_a);
        System.out.println("--------------移除观察者A");
        subject.pushMessage("测试观察者消息3");
        subject.pushMessage("测试观察者消息4");
        subject.deleteObserver(observer_b);
        System.out.println("--------------移除观察者B");
        subject.pushMessage("测试观察者消息5");
        subject.pushMessage("测试观察者消息6");
        new ConcreteObserver("C", subject);
        System.out.println("--------------添加观察者C");
        subject.pushMessage("测试观察者消息7");
        subject.pushMessage("测试观察者消息8");
    }
}
