package practice;

public class Piyo implements Bar, Foo {
    @Override
    public void sayYes() {
        System.out.println("Piyo says YES!");
    }

    @Override
    public void sayHello() {
        System.out.println("Piyo says Hello");
    }

    @Override
    public void sayGoodbye() {
        System.out.println("Piyo says Goodbye");
    }

    public void sayGoodNaight() {
        System.out.println("Piyo says Goodnight!");
    }
}
