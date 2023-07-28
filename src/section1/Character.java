package section1;

public class Character {
    private String name;
    private int hp;
    private Waza waza;

    public Character(String name, int hp, Waza waza) {
        this.name = name;
        this.hp = hp;
        Waza waza2 = new Waza("わざ", 10);
        this.waza = waza2;
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public Waza getWaza() {
        return this.waza;
    }
}
