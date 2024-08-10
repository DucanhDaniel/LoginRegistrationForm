package styleClass;

import javafx.scene.control.PasswordField;
import javafx.scene.control.skin.TextFieldSkin;

public class PasswordFieldSkin extends TextFieldSkin {

    private static final String BULLET = "•";


    public PasswordFieldSkin(PasswordField control) {
        super(control);
    }

    @Override
    protected String maskText(String text) {
        return BULLET.repeat(text.length());
    }
}