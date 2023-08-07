package section2;

public class Fushigidane extends Monster {


    Fushigidane() {
        super.setCharacter("フシギダネ");
    }

    Fushigidane(String tn, String nm) {
        super(tn, nm);
        super.setCharacter("フシギダネ");
    }

    Fushigidane(String tn, String nm, int lv) {
        super(tn, nm, lv);
        super.setCharacter("フシギダネ");
    }

    @Override
    public void levelUp(int risingLevel) {
        setLv(getLv() + risingLevel * 1);
        setHp(getHp() + risingLevel * 31);
        setAtk(getAtk() + risingLevel * 6);
        setDef(getDef() + risingLevel * 7);
        setSpd(getSpd() + risingLevel * 8);
        setHp(getHpMax());
    }

}
