/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.linguaclassica.fullcalendar.callback;

import com.linguaclassica.fullcalendar.net.CalendarResponse;
import com.linguaclassica.fullcalendar.net.EventCalendar;
import com.linguaclassica.fullcalendar.net.EventSource;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.Request;

public abstract class EventClickedCallback extends AbstractAjaxCallback implements CallbackWithHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String configureCallbackScript(String script, String urlTail) {
		return script.replace(urlTail, "&eventId=\"+event.id+\"&sourceId=\"+event.source.data."
			+ EventSource.Const.UUID + "+\"");
	}

	@Override
	public String getHandlerScript() {
		return "function(event) { " + getCallbackScript() + "}";
	}

	@Override
	protected void respond(AjaxRequestTarget target) {
		Request r = getCalendar().getRequest();
		String eventId = r.getRequestParameters().getParameterValue("eventId").toString();
		String sourceId = r.getRequestParameters().getParameterValue("sourceId").toString();

		EventSource source = getCalendar().getEventManager().getEventSource(sourceId);
		EventCalendar event = source.getEventProvider().getEventForId(eventId);

		onClicked(new ClickedEvent(source, event), new CalendarResponse(getCalendar(), target));
	}

	protected abstract void onClicked(ClickedEvent event, CalendarResponse response);
}
