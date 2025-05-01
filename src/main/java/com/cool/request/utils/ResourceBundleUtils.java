/*
 * Copyright 2024 XIN LIN HOU<hxl49508@gmail.com>
 * ResourceBundleUtils.java is part of Cool Request
 *
 * License: GPL-3.0+
 *
 * Cool Request is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cool Request is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cool Request.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cool.request.utils;

import com.cool.request.common.state.SettingPersistentState;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtils {
    public static String getString(String key) {
        int languageValue = SettingPersistentState.getInstance().getState().languageValue;
        if (languageValue == -1) return getString(key, Locale.ENGLISH);
        if (languageValue == 0) return getString(key, Locale.ENGLISH);
        if (languageValue == 1) return getString(key, Locale.CHINESE);
        if (languageValue == 2) return getString(key, Locale.JAPANESE);
        if (languageValue == 3) return getString(key, Locale.KOREAN);
        if (languageValue == 4) return getString(key, Locale.TRADITIONAL_CHINESE);
        return getString(key, Locale.getDefault());
    }

    private static String getString(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        return bundle.getString(key);
    }
}
