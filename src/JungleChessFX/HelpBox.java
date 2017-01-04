package JungleChessFX;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by Tenbay on 2017/1/1.
 */
public interface HelpBox {
    VBox createHelpBox();

    Text makeText(String string);
}
