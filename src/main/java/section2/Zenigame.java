package section2;

public class Zenigame extends Monster {
    Zenigame() {
        super.setCharacter("ゼニガメ");
    }

    Zenigame(String trainer, String name) {
        super(trainer, name);
        super.setCharacter("ゼニガメ");
    }

    Zenigame(String trainer, String name, int lv) {
        super(trainer, name);
        super.setCharacter("ゼニガメ");
        if (lv > 1) {//レベルが1以上だと全てのステータスが変わってくる
            levelUp(lv - 1);
        }
    }

    Zenigame(String trainer, String name, int lv, String wazaNm) {
        super(trainer, name);
        super.setCharacter("ゼニガメ");
        super.setWazaNm(wazaNm);
        if (lv > 1) {//レベルが1以上だと全てのステータスが変わってくる
            levelUp(lv - 1);
        }
    }

    @Override
    public void levelUp(int risingLevel) {
        setLv(getLv() + risingLevel);
        setHp(getHp() + risingLevel * 20);
        setAtk(getAtk() + risingLevel * 6);
        setDef(getAtk() + risingLevel * 10);
        setSpd(getSpd() + risingLevel * 6);
        setHp(getHpMax());
    }
}
