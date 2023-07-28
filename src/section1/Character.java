package section1;

public class Character {
    private String name;
    private int hp = 100;
    private String waza = "わざ";

    public Character(String name, int hp, String waza) {
        this.name = name;
        this.hp = hp;
        this.waza = waza;
    }

    public String getName() {
        return this.name;
    }
    public int getHp(){
        return this.hp;
    }
    public String getWaza(){
        return this.waza;
    }
}
