package section2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Monster {
    private String character;
    private String trainer;
    private String name;
    private int lv;
    private int hp;
    private int atk;
    private int def;
    private int spd;
    private int hpMax;
    private String wazaNm;
    private double wazaDmgRate;

    final String dmgCorection120 = "120";
    final String dmgCorection1 = "1";

    Monster() {
        character = "unknown";
        trainer = "wild";
        name = "noname";
        lv = 1;
        hp = 80;
        atk = 15;
        def = 10;
        spd = 10;
        hpMax = 80;
        wazaNm = "たいあたり";
        wazaDmgRate = 1;
    }

    Monster(String trainer, String name) {
        this();
        this.trainer = trainer;
        this.name = name;
    }

    Monster(String trainer, String name, int lv) {
        this(trainer, name);
        if (lv > 1) {//レベルが1以上だと全てのステータスが変わってくる
            levelUp(lv - 1);
        }
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return this.character;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getTrainer() {
        return this.trainer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getLv() {
        return this.lv;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return this.hp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getAtk() {
        return this.atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public String getWazaNm() {
        return wazaNm;
    }

    public void setWazaNm(String wazaNm) {
        this.wazaNm = wazaNm;
    }

    public double getWazaDmgRate() {
        return wazaDmgRate;
    }

    public void setWazaDmgRate(double wazaDmgRate) {
        this.wazaDmgRate = wazaDmgRate;
    }

    public String monsterStatus(Monster monster) {
        String status
                = "character：" + monster.getCharacter() + "　"
                + "trainer："   + monster.getTrainer()   + "　"
                + "name："      + monster.getName()      + "　"
                + "lv："        + monster.getLv()        + "　"
                + "hp："        + monster.getHp()        + "　"
                + "Atk："       + monster.getAtk()       + "　"
                + "Def："       + monster.getDef()       + "　"
                + "spd"         + monster.getSpd()       + "　"
                + "hpMax："     + monster.getHpMax()     + "　"
                + "wazaNm"      + monster.getWazaNm()    + "　"
                + "wazaDmgRate" + monster.getWazaDmgRate();
        return status;
    }

    public void levelUp(int risingLevel) {
        lv = lv + risingLevel;
        hpMax = hpMax + risingLevel * 30;
        atk = atk + risingLevel * 5;
        def = def + risingLevel * 5;
        spd = spd + risingLevel * 5;
        hp = hpMax;
    }

    public int wazaDmg(int toAtkDmg, double wazaDmgRate, int defenseRate) {
        BigDecimal bdAtk = new BigDecimal(toAtkDmg);
        BigDecimal bdDmrt = new BigDecimal(wazaDmgRate);
        BigDecimal bdDefenseRate = new BigDecimal(defenseRate);
        int wazaDmgPoint = bdAtk.multiply(bdDmrt).subtract(bdDefenseRate).intValue();
        if (wazaDmgPoint < 0) {
            wazaDmgPoint = 1;
        }
        return wazaDmgPoint;
    }

    //受けるダメージの計算
    public int calculationDamaged(int toAttackDmg) {
        BigDecimal bgDmg = new BigDecimal(toAttackDmg);
        BigDecimal value1 = new BigDecimal(dmgCorection120);
        BigDecimal value2 = new BigDecimal(dmgCorection1);
        BigDecimal def1 = new BigDecimal(getDef());

        //ダメージ減算率　value2 / (value2 + (def1 / value1))
        BigDecimal Subtraction = value2.divide(
                value2.add(def1.divide(value1, 2, RoundingMode.DOWN)), 2, RoundingMode.DOWN);
        int receiveDamage = bgDmg.multiply(Subtraction).intValue();
        return receiveDamage;
    }
}
