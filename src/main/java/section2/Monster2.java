package section2;

import java.math.BigDecimal;

public class Monster2 {
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
    private String wazaDmgRate;

    final String dmgCorection120 = "120";
    final String dmgCorection1 = "1";

    Monster2() {
        this.character = "unknown";
        this.trainer = "wild";
        this.name = "noname";
        this.lv = 1;
        this.hp = 80;
        this.atk = 15;
        this.def = 10;
        this.spd = 10;
        this.hpMax = 80;
        this.wazaNm = "たいあたり";
        this.wazaDmgRate = "1.0";
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

    public String getCharacter(){
        return this.character;
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
