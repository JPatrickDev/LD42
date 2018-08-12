package me.jack.ld42.GUI;

import com.jdp30.gui.GUIElement;
import me.jack.ld42.Weapon.Projectile;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Jack on 12/08/2018.
 */
public class WeaponSlot extends GUIElement {

    private static Image image, selected;
    private Projectile projectile;
    private Image scaled;
    private boolean isSelected = false;

    public WeaponSlot(int x, int y, Projectile projectile) {
        super(x, y, 40, 40);
        this.projectile = projectile;
        if (image == null)
            try {
                image = new Image("res/weaponSlot.png");
                selected = new Image("res/selectedWeaponSlot.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        scaled = projectile.getImage().getScaledCopy(2f);
    }

    @Override
    public void render(Graphics graphics) {
        if (!isSelected)
            graphics.drawImage(image, 0, 0);
        else
            graphics.drawImage(selected, 0, 0);
        graphics.drawImage(scaled, 4, 4);
    }

    @Override
    public void update(GameContainer container) {

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Projectile getProjectile() {
        return projectile;
    }
}
