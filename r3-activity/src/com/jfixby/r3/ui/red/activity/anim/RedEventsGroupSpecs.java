
package com.jfixby.r3.ui.red.activity.anim;

import com.jfixby.r3.api.activity.animation.EventsGroupSpecs;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;

public class RedEventsGroupSpecs implements EventsGroupSpecs {
	final List<ID> events = Collections.newList();

	@Override
	public void addEventID (ID event_id) {
		events.add(event_id);
	}

	@Override
	public Collection<ID> listEvents () {
		return events;
	}

}
