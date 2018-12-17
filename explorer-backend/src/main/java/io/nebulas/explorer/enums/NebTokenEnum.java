package io.nebulas.explorer.enums;

public enum NebTokenEnum {

    NAS("NAS"),
    ATP("ATP");

    NebTokenEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public static NebTokenEnum parse(String desc) {
        for (NebTokenEnum typeEnum : NebTokenEnum.values()) {
            if (typeEnum.getDesc().equals(desc)) {
                return typeEnum;
            }
        }
        return null;
    }

}
