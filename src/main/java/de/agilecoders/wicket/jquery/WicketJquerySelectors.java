package de.agilecoders.wicket.jquery;

import de.agilecoders.wicket.jquery.settings.IWicketJquerySelectorsSettings;
import de.agilecoders.wicket.jquery.settings.WicketJquerySelectorsSettings;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * base wicket-jquery-selectors class that is responsible for installation of custom settings.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public final class WicketJquerySelectors {
    private static final Logger LOG = LoggerFactory.getLogger("wicket-jquery-selectors");

    /**
     * The {@link org.apache.wicket.MetaDataKey} used to retrieve the {@link de.agilecoders.wicket.jquery.settings.IWicketJquerySelectorsSettings}
     * from the Wicket {@link Appendable}.
     */
    private static final MetaDataKey<IWicketJquerySelectorsSettings> JQUERY_SELECTORS_SETTINGS_METADATA_KEY = new MetaDataKey<IWicketJquerySelectorsSettings>() {
    };

    /**
     * Checks whether this library support is already installed
     *
     * @param application the wicket application
     * @return {@code true} if library is already installed, otherwise {@code false}
     */
    public static boolean isInstalled(Application application) {
        return application != null && application.getMetaData(JQUERY_SELECTORS_SETTINGS_METADATA_KEY) != null;
    }
    /**
     * installs the library to given application
     *
     * @param app the wicket application
     */
    public static void install(final Application app) {
        install(app, null);
    }

    /**
     * installs the library settings to given app.
     *
     * @param app      the wicket application
     * @param settings the settings to use
     */
    public static void install(Application app, IWicketJquerySelectorsSettings settings) {
        final IWicketJquerySelectorsSettings existingSettings = settings(app);

        if (existingSettings == null) {
            if (settings == null) {
                settings = new WicketJquerySelectorsSettings();
            }

            app.setMetaData(JQUERY_SELECTORS_SETTINGS_METADATA_KEY, settings);

            LOG.info("initialize wicket jquery selectors with given settings: {}", settings);
        }
    }

    /**
     * returns the {@link IWicketJquerySelectorsSettings} which are assigned to given application
     *
     * @param app The current application
     * @return assigned {@link IWicketJquerySelectorsSettings}
     */
    public static IWicketJquerySelectorsSettings settings(final Application app) {
        return app.getMetaData(JQUERY_SELECTORS_SETTINGS_METADATA_KEY);
    }

    /**
     * returns the {@link IWicketJquerySelectorsSettings} which are assigned to given application or a new default instance.
     *
     * This is an internal API method, please don't use it.
     *
     * @return assigned {@link IWicketJquerySelectorsSettings}
     */
    public static IWicketJquerySelectorsSettings assignedSettingsOrDefault() {
        Application app = Application.exists() ? Application.get() : null;

        if (isInstalled(app)) {
            return settings();
        } else {
            LOG.info("try to get settings, but WicketJquerySelectors wasn't installed to current application. Fallback to default settings.");

            return new WicketJquerySelectorsSettings();
        }
    }

    /**
     * returns the {@link IWicketJquerySelectorsSettings} which are assigned to current application
     *
     * @return assigned {@link IWicketJquerySelectorsSettings}
     */
    public static IWicketJquerySelectorsSettings settings() {
        if (Application.exists()) {
            final IWicketJquerySelectorsSettings settings = Application.get().getMetaData(JQUERY_SELECTORS_SETTINGS_METADATA_KEY);

            if (settings != null) {
                return settings;
            } else {
                throw new IllegalStateException("you have to call WicketJquerySelectors.install()");
            }
        }

        throw new IllegalStateException("there is no active application assigned to this thread.");
    }

    /**
     * private constructor.
     */
    private WicketJquerySelectors() {
        throw new UnsupportedOperationException();
    }
}
