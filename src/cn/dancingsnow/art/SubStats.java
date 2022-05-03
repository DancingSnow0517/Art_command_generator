package cn.dancingsnow.art;

public enum SubStats {

    生命("501024"),
    防御("501084"),
    攻击("501054"),
    精通("501244"),
    百分比生命("501034"),
    百分比防御("501094"),
    百分比攻击("501064"),
    充能效率("501234"),
    暴击率("501204"),
    暴击伤害("501224"),

    ;
    public final String id;

    SubStats(String id) {
        this.id = id;
    }
}
