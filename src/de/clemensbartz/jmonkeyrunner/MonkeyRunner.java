/*
 * Copyright 2014 Clemens Bartz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.clemensbartz.jmonkeyrunner;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.TimeoutException;
import de.clemensbartz.chattychimpchat.adb.AdbBackend;
import de.clemensbartz.chattychimpchat.core.IChimpDevice;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clemens on 03.11.14.
 */
public class MonkeyRunner {
    private static Map<String, AdbBackend> backendMap = new HashMap<String, AdbBackend>();

    protected static AdbBackend getAdbBackend(String adbPath) {
        if (backendMap.get(adbPath) != null) {
            return backendMap.get(adbPath);
        }
        backendMap.put(adbPath, new AdbBackend(adbPath, false));
        return getAdbBackend(adbPath);
    }

    /**
     * Displays an alert dialog to the process running the current program. The dialog is modal, so the program pauses until the user clicks the dialog's button.
     * @param message The message to display in the dialog.
     * @param title The dialog's title.
     * @param okTitle The text displayed in the dialog button.
     */
    public static void alert(String message, String title, String okTitle) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Displays a dialog with a list of choices to the process running the current program.
     * @param message The prompt message displayed in the dialog.
     * @param choices An iterable containing one or more objects that are displayed as strings. The recommended form is an array of strings.
     * @param title The dialog's title. The default is "Input".
     * @return If the user makes a selection and clicks the "OK" button, the method returns the 0-based index of the selection within the iterable.
     *  If the user clicks the "Cancel" button, the method returns -1.
     */
    public static int choice(String message, Collection<String> choices, String title) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Displays a dialog with a list of choices to the process running the current program.
     * @param message The prompt message displayed in the dialog.
     * @param choices An iterable containing one or more objects that are displayed as strings. The recommended form is an array of strings.
     * @return If the user makes a selection and clicks the "OK" button, the method returns the 0-based index of the selection within the iterable.
     *  If the user clicks the "Cancel" button, the method returns -1.
     */
    public static int choice(String message, Collection<String> choices) {
        return choice(message, choices, "Input");
    }

    /**
     * Displays a dialog that accepts input and returns it to the program. The dialog is modal, so the program pauses until the user clicks one of the dialog's buttons.
     * The dialog contains two buttons, one of which displays the okTitle value and the other the cancelTitle value. If the user clicks the okTitle button,
     * the current value of the input box is returned. If the user clicks the cancelTitle button, an empty string is returned.
     * @param message The prompt message displayed in the dialog.
     * @param initialValue The initial value to display in the dialog. The default is an empty string.
     * @param title The dialog's title. The default is "Input".
     * @param okTitle The text displayed in the okTitle button. The default is "OK".
     * @param cancelTitle The text displayed in the cancelTitle button. The default is "Cancel".
     * @return
     */
    public static String input(String message, String initialValue, String title, String okTitle, String cancelTitle) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Displays a dialog that accepts input and returns it to the program. The dialog is modal, so the program pauses until the user clicks one of the dialog's buttons.
     * The dialog contains two buttons, one of which displays the okTitle value and the other the cancelTitle value. If the user clicks the okTitle button,
     * the current value of the input box is returned. If the user clicks the cancelTitle button, an empty string is returned.
     * @param message The prompt message displayed in the dialog.
     * @param initialValue The initial value to display in the dialog. The default is an empty string.
     * @param title The dialog's title. The default is "Input".
     * @param okTitle The text displayed in the okTitle button. The default is "OK".
     * @return
     */
    public static String input(String message, String initialValue, String title, String okTitle) {
        return input(message, initialValue, title, okTitle, "Cancel");
    }

    /**
     * Displays a dialog that accepts input and returns it to the program. The dialog is modal, so the program pauses until the user clicks one of the dialog's buttons.
     * The dialog contains two buttons, one of which displays the okTitle value and the other the cancelTitle value. If the user clicks the okTitle button,
     * the current value of the input box is returned. If the user clicks the cancelTitle button, an empty string is returned.
     * @param message The prompt message displayed in the dialog.
     * @param initialValue The initial value to display in the dialog. The default is an empty string.
     * @param title The dialog's title. The default is "Input".
     * @param cancelTitle The text displayed in the cancelTitle button. The default is "Cancel".
     * @return
     */
    public static String input1(String message, String initialValue, String title, String cancelTitle) {
        return input(message, initialValue, title, "OK", cancelTitle);
    }

    /**
     * Displays a dialog that accepts input and returns it to the program. The dialog is modal, so the program pauses until the user clicks one of the dialog's buttons.
     * The dialog contains two buttons, one of which displays the okTitle value and the other the cancelTitle value. If the user clicks the okTitle button,
     * the current value of the input box is returned. If the user clicks the cancelTitle button, an empty string is returned.
     * @param message The prompt message displayed in the dialog.
     * @param initialValue The initial value to display in the dialog. The default is an empty string.
     * @param title The dialog's title. The default is "Input".
     * @return
     */
    public static String input(String message, String initialValue, String title) {
        return input(message, initialValue, title, "OK", "Cancel");
    }

    /**
     * Displays a dialog that accepts input and returns it to the program. The dialog is modal, so the program pauses until the user clicks one of the dialog's buttons.
     * The dialog contains two buttons, one of which displays the okTitle value and the other the cancelTitle value. If the user clicks the okTitle button,
     * the current value of the input box is returned. If the user clicks the cancelTitle button, an empty string is returned.
     * @param message The prompt message displayed in the dialog.
     * @param initialValue The initial value to display in the dialog. The default is an empty string.
     * @return
     */
    public static String input(String message, String initialValue) {
        return input(message, initialValue, "Input", "OK", "Cancel");
    }

    /**
     * Pauses the current program for the specified number of seconds.
     * @param seconds The number of seconds to pause.
     */
    public static void sleep(double seconds) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Tries to make a connection between the monkeyrunner backend and the specified device or emulator.
     * @param adbPath Path to adb
     * @param timeout The number of seconds to wait for a connection. The default is to wait forever.
     * @param tries How many times to try to get the device. Default is infinite times.
     * @param deviceId A regular expression that specifies the serial number of the device or emulator. See the topic Android Debug Bridge for a description of device and emulator serial numbers.
     * @return The device or null if no connection could be made.
     */
    public static MonkeyDevice waitForConnection(String adbPath, long timeout, int tries, String deviceId) {
        if (tries < 1) {
            tries = 1;
        }
        AdbBackend backend = getAdbBackend(adbPath);
        MonkeyDevice device = null;
        int itries = 0;
        while (itries < tries) {
            try {
                IChimpDevice chimpDevice = (deviceId == null)
                        ? backend.waitForConnection()
                        : backend.waitForConnection(timeout, deviceId);
                device = new MonkeyDevice(chimpDevice);
                break;
            }
            catch (IOException e) {

            }
            catch (AdbCommandRejectedException e) {

            }
            catch (InterruptedException e) {

            }
            catch (TimeoutException e) {

            }
            catch (MonkeyException e) {

            }
            itries++;
        }
        return device;
    }

    /**
     * Tries to make a connection between the monkeyrunner backend and the specified device or emulator.
     * @param adbPath Path to adb
     * @param tries How many times to try to get the device. Default is infinite times.
     * @return The device or null if no connection could be made.
     */
    public static MonkeyDevice waitForConnection(String adbPath, int tries) {
        return waitForConnection(adbPath, Long.MAX_VALUE, tries, null);
    }

    /**
     * Tries to make a connection between the monkeyrunner backend and the specified device or emulator.
     * @param adbPath Path to adb
     * @param timeout The number of seconds to wait for a connection. The default is to wait forever.
     * @param deviceId A regular expression that specifies the serial number of the device or emulator. See the topic Android Debug Bridge for a description of device and emulator serial numbers.
     * @return The device or null if no connection could be made.
     */
    public static MonkeyDevice waitForConnection(String adbPath, long timeout, String deviceId) {
        return waitForConnection(adbPath, timeout, Integer.MAX_VALUE, deviceId);
    }

    /**
     * Tries to make a connection between the monkeyrunner backend and the specified device or emulator.
     * @param adbPath Path to adb
     * @return The device or null if no connection could be made.
     */
    public static MonkeyDevice waitForConnection(String adbPath) {
        return waitForConnection(adbPath, Long.MAX_VALUE, Integer.MAX_VALUE, null);
    }

    /**
     * Tries to make a connection between the monkeyrunner backend and the specified device or emulator.
     * @param adbPath Path to adb
     * @param deviceId A regular expression that specifies the serial number of the device or emulator. See the topic Android Debug Bridge for a description of device and emulator serial numbers.
     * @return The device or null if no connection could be made.
     */
    public static MonkeyDevice waitForConnection(String adbPath, String deviceId) {
        return waitForConnection(adbPath, Long.MAX_VALUE, Integer.MAX_VALUE, deviceId);
    }
}
