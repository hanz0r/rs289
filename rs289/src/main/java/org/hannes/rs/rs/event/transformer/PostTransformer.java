package org.hannes.rs.rs.event.transformer;

import org.hannes.rs.event.Event;
import org.hannes.rs.event.transform.TransformState;
import org.hannes.rs.event.transform.TransformationContext;
import org.hannes.rs.event.transform.Transformer;
import org.hannes.rs.net.Session.State;

@TransformState(State.ACTIVE)
public class PostTransformer implements Transformer {

	@Override
	public Event transform(TransformationContext context) {
		return new Event("HIIII");
	}

}