package section2;

public class Hitokage extends Monster {
    Hitokage() {
        super.setCharacter("ヒトカゲ");
    }

    Hitokage(String trainer, String name) {
        super(trainer, name);
        super.setCharacter("ヒトカゲ");
    }

    Hitokage(String trainer, String name, int lv) {
        super(trainer, name, lv);
        super.setCharacter("ヒトカゲ");
    }

    Hitokage(String trainer, String name, int lv, String wazaNm) {
        super(trainer, name, lv, wazaNm);
        super.setCharacter("ヒトカゲ");
        if (lv > 1) {//レベルが1以上だと全てのステータスが変わってくる
            levelUp(lv - 1);
        }
    }

    @Override
    public void levelUp(int risingLevel) {
        setLv(getLv() + risingLevel * 1);
        setHp(getHp() + risingLevel * 29);
        setAtk(getAtk() + risingLevel * 8);
        setDef(getAtk() + risingLevel * 8);
        setSpd(getSpd() + risingLevel * 9);
        setHp(getHpMax());
    }
}
