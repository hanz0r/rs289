package org.hannes.scoundrel.rs.event.transformer;

import static org.joox.JOOX.$;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.hannes.scoundrel.event.Event;
import org.hannes.scoundrel.event.transform.TransformState;
import org.hannes.scoundrel.event.transform.TransformationContext;
import org.hannes.scoundrel.event.transform.Transformer;
import org.hannes.scoundrel.net.Session.State;
import org.hannes.scoundrel.util.Initializable;

@TransformState(State.DISCOVERED)
public class PreTransformer implements Transformer, Initializable {

	/**
	 * The collection of templates
	 */
	private static final Map<Integer, String> templates = new HashMap<>();

	@Override
	public void initialize() throws Exception {
		$($(new InputStreamReader(ClassLoader.getSystemResourceAsStream("pre-transformers.xml")))
				.document()).find("transformer").forEach(e -> templates.put(Integer.parseInt(e.getAttribute("opcode")), e.getAttribute("alias")));
	}

	@Override
	public Event transform(TransformationContext context) {
		return new Event(templates.get(context.getMessage().getHeader().getOpcode()), context.getMessage().getPayload());
	}

}