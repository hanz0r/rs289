package org.hannes.scoundrel.rs.event.transformer;

import org.hannes.scoundrel.event.Event;
import org.hannes.scoundrel.event.transform.TransformState;
import org.hannes.scoundrel.event.transform.TransformationContext;
import org.hannes.scoundrel.event.transform.Transformer;
import org.hannes.scoundrel.net.Session.State;

@TransformState(State.ACTIVE)
public class PostTransformer implements Transformer {

	@Override
	public Event transform(TransformationContext context) {
		return new Event("HIIII");
	}

}