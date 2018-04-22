package pl.edu.osp.tiles;

public interface Tile {
    byte getaType();
    void nextDay();
    byte getLev();
    byte getFertility();
    String getName();
}
