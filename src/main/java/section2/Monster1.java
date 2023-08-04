package section2;

import java.math.BigDecimal;

import static java.util.regex.Pattern.matches;

public class Monster1 {
    public String character = "unknown";
    public String name = "noname";
    public String trainer = "wild";
    public int lv = 1;
    public int hp = 80;
    public int atk = 15;
    public int def = 10;
    public int spd = 10;
    public int hpMax = 80;
    public String wazaNm = "たいあたり";
    public String wazaDmgRate = "1.0";
    final String dmgCorection120 = "120";
    final String dmgCorection1 = "1";

//    Monster1(String character, String trainer, String name, int lv, int hp, int atk, int def, int spd, int hpMax, String wazaNm, String wazaDmgRate) {
//        this.character = character;
//        this.trainer = trainer;
//        this.name = name;
//        this.lv = lv;
//        this.hp = hp;
//        this.atk = atk;
//        this.def = def;
//        this.spd = spd;
//        this.hpMax = hpMax;
//        this.wazaNm = wazaNm;
//        this.wazaDmgRate = wazaDmgRate;
//    }

//    public String getCharacter() {
//        return this.character;
//    }
//
//    public String getTrainer() {
//        return this.trainer;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public int getLv() {
//        return this.lv;
//    }
//
//    public int getHp() {
//        return this.hp;
//    }
//
//    public int getAtk() {
//        return this.atk;
//    }
//
//    public int getDef() {
//        return this.def;
//    }
//
//    public int getSpd() {
//        return this.spd;
//    }
//
//    public int getHpMax() {
//        return this.hpMax;
//    }
//
//    public String getWazaNm() {
//        return this.wazaNm;
//    }
//
//    public String getWazaDmgRate() {
//        return this.wazaDmgRate;
//    }


    //なぜ、toString()が呼び出される？
    @Override
    public String toString() {
        String status = "キャラクター：" + character + "　トレーナー：" + trainer + "　名前：" + name + "　lv：" + lv + "　hp：" + hp + "　Atk：" + atk + "　Def：" + def + "　spd" + spd + "　hpMax：" + hpMax + "　持ち技:" + wazaNm + "　ダメージレート:" + wazaDmgRate;
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
