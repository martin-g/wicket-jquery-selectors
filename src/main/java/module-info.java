module de.agilecoders.wicket.jquery {
	exports de.agilecoders.wicket.jquery;
	exports de.agilecoders.wicket.jquery.function;
	exports de.agilecoders.wicket.jquery.settings;
	exports de.agilecoders.wicket.jquery.util;
	exports de.agilecoders.wicket.jquery.util.serializer;

	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;

	requires org.apache.wicket.core;
	requires org.apache.wicket.util;

	requires org.slf4j;
}
