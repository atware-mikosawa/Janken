package section1;

public class Character {
    private String name;
    private int hp;
    private Waza waza;

    public Character(String name, int hp, String wazaName, int wazaDamage) {
        this.name = name;
        this.hp = hp;
        this.waza = new Waza(wazaName, wazaDamage);
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
