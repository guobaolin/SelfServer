package com.gbl.inherited;

import com.gbl.inherited.demo1.MyInheritedClass;
import com.gbl.inherited.demo2.IInheritedInterface;
import com.gbl.inherited.demo2.IInheritedInterfaceChild;
import com.gbl.inherited.demo2.MyInheritedClassUseInterface;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class MyInheritedClassTest {

    @Test
    public void testInherited() {
        {
            /// 类继承关系中，子类会继承父类使用的注解中被@Inherited修饰的注解
            Annotation[] annotations = MyInheritedClass.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).anyMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));   // true
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(NoInheritedAnnotation.class)));  // true
        }
        {
            /// 接口继承关系中，子接口不会继承父接口中的任何注解，不管父接口中使用的注解有没有被@Inherited修饰
            Annotation[] annotations = MyInheritedClassUseInterface.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));  // true
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(NoInheritedAnnotation.class)));  // true
        }
        {
            Annotation[] annotations = IInheritedInterface.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).anyMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));   // true
            assertTrue("", Arrays.stream(annotations).anyMatch(l -> l.annotationType().equals(NoInheritedAnnotation.class)));   // true
        }
        {
            /// 类实现接口时不会继承任何接口中定义的注解
            Annotation[] annotations = IInheritedInterfaceChild.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));  // true
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(NoInheritedAnnotation.class)));  // true
        }
    }
}
