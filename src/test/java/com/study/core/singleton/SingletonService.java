package com.study.core.singleton;

/**
 * 싱글톤 패턴 적용
 * 그러나 이코드는 DIP를 위반했다. 또한, 싱글톤을 구현하기 위한 코드가 너무 복잡함. (싱글톤 자체가 DIP를 위반하는 것은 아님)
 * DIP: 인터페이스에 의존하는 것이 아닌 구체 클래스에 의존하면 DIP를 위반한 것
 * SingletonTest.singletonServiceTest() 테스트 코드는 DIP를 위반함.
 */
public class SingletonService {

    // static 영역의 객체 하나 생성
    private static final SingletonService instance = new SingletonService();

    // public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private 선언, 외부에서 new 키워드로 객체 생성 못하게 막음
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
