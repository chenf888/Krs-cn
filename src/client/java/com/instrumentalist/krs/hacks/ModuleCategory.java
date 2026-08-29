package com.instrumentalist.krs.hacks;

public enum ModuleCategory {
    Combat,
    Movement,
    Player,
    Level,
    Exploit,
    Render,
    Dev;

    public String displayName() {
        return switch (this) {
            case Combat -> "战斗";
            case Movement -> "移动";
            case Player -> "玩家";
            case Level -> "世界";
            case Exploit -> "漏洞";
            case Render -> "渲染";
            case Dev -> "开发";
        };
    }
}
