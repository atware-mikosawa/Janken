package section2;

import java.math.BigDecimal;

public class Monster2 {
    public String character;
    public String trainer;
    public String name;
    public int lv;
    public int hp;
    public int atk;
    public int def;
    public int spd;
    public int hpMax;
    public String wazaNm;
    public String wazaDmgRate;

    final String dmgCorection120 = "120";
    final String dmgCorection1 = "1";

    Monster2() {
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
        wazaDmgRate = "1.0";
    }

    Monster2(String trainer, String name) {
        this();
        this.trainer = trainer;
        this.name = name;
    }

    Monster2(String trainer, String name, int lv) {
        this(trainer, name);
        if (lv > 1) {//レベルが1以上だと全てのステータスが変わってくる
            levelUp(lv - 1 );
        }
    }

    @Override
    public String toString() {
        String status = "character：" + character + "trainer：" + trainer + "name：" + name + "lv：" + lv + "hp：" + hp + "Atk：" + atk + "Def：" + def + "spd" + spd + "hpMax：" + hpMax + "wazaNm" + wazaNm + "wazaDmgRate" + wazaDmgRate;
        return status;
    }

    void levelUp(int risingLevel) {
        lv = lv + risingLevel;
        hpMax = hpMax + risingLevel * 30;
        atk = atk + risingLevel * 5;
        def = def + risingLevel * 5;
        spd = spd + risingLevel * 5;
        hp = hpMax;
    }

    public void setWaza(String waza, String wdr) {
        //引数2のバリデーションチェック
        String valueCheck = "^[0-9]+\\.[0-9]$";
        if (wazaDmgRate.matches(valueCheck)) {
            wazaNm = waza;
            wazaDmgRate = wdr;
        } else {        //１のチャックがNGであれば、フィールドは更新せずにエラーメッセージ
            System.out.println("[ERROR]技の設定に失敗しました");
        }
    }

    public String getStatus() {
        return "[" + name + " lv" + lv + " HP" + hp + "/" + hpMax + "]";
    }

    public int useWaza() {
        BigDecimal bdAtk = new BigDecimal(atk);
        BigDecimal bdDmrt = new BigDecimal(wazaDmgRate);
        int wazaDmgPoint = bdAtk.multiply(bdDmrt).intValue();
        return wazaDmgPoint;
    }

    public int damaged(int dmg) {
        BigDecimal bgDmg = new BigDecimal(dmg);
        BigDecimal value1 = new BigDecimal(dmgCorection120);
        BigDecimal value2 = new BigDecimal(dmgCorection1);
        BigDecimal def1 = new BigDecimal(def);

//      ダメージ減算率
        BigDecimal Subtraction = value2.divide(value2.add(def1).divide(value1));
//      実際に受けるダメージ
        int receiveDamage = bgDmg.multiply(Subtraction).intValue();

        if (hp > receiveDamage) { //HP>ダメージであればダメージを差し引いた値 をHPに代入
            hp = hp - receiveDamage;
        } else { //HP<ダメージであればHPに0を代入
            hp = 0;
        }

        return receiveDamage;
    }
}
