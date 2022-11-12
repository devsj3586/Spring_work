//package com.fastcampus.ch3;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//
//
////@Component
//class Engine {}   // ==   <bean id = "engine"  class = "com.fastcampus.ch3.Engine" />
//@Component class SuperEngine extends Engine {}
//@Component class TurboEngine extends Engine {}
//@Component
//class Door {}
//@Component
//class Car {
//    @Value("red") String color;
//    @Value("100") int oil;
////    @Autowired                    // byType
////    @Qualifier("superEngine")     // byType
//    @Resource(name = "superEngine")    // byName
//    Engine engine;  //Autowired=byType -타입으로 먼저 검색, 여러개면 이름으로 검색, 이름으로 검색이 안될시 Qualifier 로 지정해주면 찾음
//    @Autowired Door[] doors;  // <context:annotation-config> 있어야함
//
//    public Car() {} // 기본 생성자를 꼭 만들어주자
//
//    public Car(String color, int oil, Engine engine, Door[] doors) {
//        this.color = color;
//        this.oil = oil;
//        this.engine = engine;
//        this.doors = doors;
//    }
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public void setOil(int oil) {
//        this.oil = oil;
//    }
//
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//
//    public void setDoors(Door[] doors) {
//        this.doors = doors;
//
//    }
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//public class SpringDiTest {
//    public static void main(String[] args) {
//        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
////        Car car = (Car) ac.getBean("car");  // byname
//        Car car = ac.getBean("car", Car.class);  // byname , Car.class 타입정보 형변환 불필요  위와 같은 문장
////        Car car2 = (Car) ac.getBean(Car.class);  // byType  // 싱글톤 - getBean 사용시 같은 객체 이용, xml scope->  defalut 가 싱글톤 // prototype면 새로운 객체가 만들어짐
////        Engine engine = (Engine) ac.getBean("engine");// byName
//////        Engine engine = (Engine) ac.getBean("superEngine");// byType -> 같은 Type 으로 찾을땐 한개가 있어야함 중복X, 여러개 존재시 error
////        Door door = (Door) ac.getBean("door");
//
//
////        car.setColor("red");  // 1 . setter 로 호출   2 . xml 파일에서 property태그(Setter 무조건 필요 ) 로 사용 3. 생성자(초기화해 )로 사용 가능 constructor-arg 로 가능
////        car.setOil(100);
////        car.setEngine(engine);
////        car.setDoors(new Door[]{ ac.getBean("door", Door.class), ac.getBean("door", Door.class)});
//        System.out.println("car = " + car);
////        System.out.println("engine = " + engine);
//
//        }
//}
