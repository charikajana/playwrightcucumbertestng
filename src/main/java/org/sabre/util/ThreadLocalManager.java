package org.sabre.util;

import org.sabre.Browserfactory.BrowserFactory;

public class ThreadLocalManager {
    private static final ThreadLocal<BrowserFactory> threadLocalBrowserFactory = new ThreadLocal<>();

    public static void setBrowserFactory(BrowserFactory browserFactory) {
        threadLocalBrowserFactory.set(browserFactory);
    }
    public static BrowserFactory getBrowserFactory() {
        return threadLocalBrowserFactory.get();
    }
    public static void removeBrowserFactory() {
        threadLocalBrowserFactory.remove();
    }
}

