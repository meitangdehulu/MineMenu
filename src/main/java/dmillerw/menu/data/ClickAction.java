package dmillerw.menu.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dmillerw
 */
public enum ClickAction {

	COMMAND(false),
	KEYBIND(false),
	ITEM_USE(true);

	public final boolean requiresServer;

	private ClickAction(boolean requiresServer) {
		this.requiresServer = requiresServer;
	}

	public static ClickAction[] clientValues;
	public static ClickAction[] values;

	public static List<ClickAction> getClientValues() {
		if (clientValues == null) {
			List<ClickAction> temp = new ArrayList<ClickAction>();
			for (ClickAction action : getValues()) {
				if (!action.requiresServer) {
					temp.add(action);
				}
			}
			clientValues = temp.toArray(new ClickAction[temp.size()]);
		}
		return Arrays.asList(clientValues);
	}

	public static List<ClickAction> getValues() {
		if (values == null) {
			values = values();
		}
		return Arrays.asList(values);
	}
}
