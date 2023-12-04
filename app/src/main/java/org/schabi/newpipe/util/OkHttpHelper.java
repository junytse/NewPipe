package org.schabi.newpipe.util;

import android.content.Context;
import androidx.preference.PreferenceManager;
import org.schabi.newpipe.R;

import java.net.InetSocketAddress;
import java.net.Proxy;

public final class OkHttpHelper {
    private OkHttpHelper() {
    }

    public static Proxy getProxy(final Context context) {
        final String proxyType = PreferenceManager
                .getDefaultSharedPreferences(context).getString(
                        context.getString(R.string.settings_proxy_protocol_key),
                        context.getString(R.string.settings_proxy_protocol_value_direct)
                );
        final String proxyServer = PreferenceManager
                .getDefaultSharedPreferences(context).getString(
                        context.getString(R.string.settings_proxy_server_key),
                        "0.0.0.0"
                );
        final String proxyPort = PreferenceManager
                .getDefaultSharedPreferences(context).getString(
                        context.getString(R.string.settings_proxy_port_key),
                        "0"
                );
        Proxy proxy = Proxy.NO_PROXY;
        if (proxyType.equals(
                context.getString(R.string.settings_proxy_protocol_value_http))) {
            proxy = new Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress(proxyServer, Integer.parseInt(proxyPort)));
        } else if (proxyType.equals(
                context.getString(R.string.settings_proxy_protocol_value_socks))) {
            proxy = new Proxy(Proxy.Type.SOCKS,
                    new InetSocketAddress(proxyServer, Integer.parseInt(proxyPort)));
        }
        return proxy;
    }
}
