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

import de.clemensbartz.chattychimpchat.adb.AdbBackend;
import de.clemensbartz.chattychimpchat.core.IChimpDevice;
import de.clemensbartz.chattychimpchat.core.PhysicalButton;
import de.clemensbartz.chattychimpchat.core.TouchPressType;

import java.util.Map;

/**
 * Created by clemens on 01.11.14.
 */
public class MonkeyDevice {
    private IChimpDevice device;

    public MonkeyDevice(IChimpDevice device) throws MonkeyException {
        if (!isDeviceValid(device)) {
            throw new MonkeyException("Device is not valid");
        }
        this.device = device;
    }

    /**
     *  Broadcasts an Intent to this device, as if the Intent were coming from an application. See Intent for more information about the arguments.
     * @param uri The URI for the Intent. (see Intent.setData()).
     * @param action The action for this Intent (see Intent.setAction()).
     * @param data The data URI for this Intent (see Intent.setData()).
     * @param mimetype The MIME type for the Intent (see Intent.setType()).
     * @param categories An iterable data structure containing strings that define categories for this Intent (see Intent.addCategory()).
     * @param extras A dictionary of extra data for this Intent (see Intent.putExtra() for an example).
     *               The key for each dictionary item should be a string. The item's value can be any simple or structured data type.
     * @param component The component for this Intent (see ComponentName). Using this argument will direct the Intent to a specific class within a specific Android package.
     * @param flags An iterable data structure containing flags that control how the Intent is handled (see Intent.setFlags()).
     */
    public void broadcastIntent(@com.android.annotations.Nullable String uri,
                                @com.android.annotations.Nullable String action,
                                @com.android.annotations.Nullable String data,
                                @com.android.annotations.Nullable String mimetype,
                                java.util.Collection<String> categories,
                                Map<String,Object> extras,
                                @com.android.annotations.Nullable String component,
                                int flags) throws MonkeyException {
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            device.broadcastIntent(uri, action, data, mimetype, categories, extras, component, flags);
        }
        catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Simulates a drag gesture (touch, hold, and move) on this device's screen.
     * @param startX The x starting point of the drag gesture
     * @param startY The y starting point of the drag gesture
     * @param endX The x end point of the drag gesture
     * @param endY The y end point of the drag gesture
     * @param duration The duration of the drag gesture in seconds. The default is 1.0 seconds.
     * @param steps The number of steps to take when interpolating points. The default is 10.
     * @throws MonkeyException
     */
    public void drag(int startX, int startY, int endX, int endY, long duration, int steps) throws MonkeyException {
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            device.drag(startX, startY, endX, endY, steps, duration);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Simulates a drag gesture (touch, hold, and move) on this device's screen.
     * @param startX The x starting point of the drag gesture
     * @param startY The y starting point of the drag gesture
     * @param endX The x end point of the drag gesture
     * @param endY The y end point of the drag gesture
     * @param steps The number of steps to take when interpolating points. The default is 10.
     * @throws MonkeyException
     */
    public void drag(int startX, int startY, int endX, int endY, int steps) throws MonkeyException {
        this.drag(startX, startY, endX, endY, (long) 1.0, steps);
    }

    /**
     * Simulates a drag gesture (touch, hold, and move) on this device's screen.
     * @param startX The x starting point of the drag gesture
     * @param startY The y starting point of the drag gesture
     * @param endX The x end point of the drag gesture
     * @param endY The y end point of the drag gesture
     * @param duration The duration of the drag gesture in seconds. The default is 1.0 seconds.
     * @throws MonkeyException
     */
    public void drag(int startX, int startY, int endX, int endY, long duration) throws MonkeyException{
        this.drag(startX, startY, endX, endY, duration, 10);
    }

    /**
     * Simulates a drag gesture (touch, hold, and move) on this device's screen.
     * @param startX The x starting point of the drag gesture
     * @param startY The y starting point of the drag gesture
     * @param endX The x end point of the drag gesture
     * @param endY The y end point of the drag gesture
     * @throws MonkeyException
     */
    public void drag(int startX, int startY, int endX, int endY) throws MonkeyException{
        this.drag(startX, startY, endX, endY, (long) 1.0, 10);
    }

    /**
     * Given the name of a system environment variable, returns its value for this device.
     * @param key The name of the system environment variable. The available variable names are listed in Table 1. Property variable names at the end of this topic.
     * @return The value of the variable. The data format varies according to the variable requested.
     * @throws MonkeyException
     */
    public String getProperty(String key) throws MonkeyException {
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            return device.getProperty(key);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Synonym for getProperty().
     * @param key
     * @return
     * @throws MonkeyException
     */
    public String getSystemProperty(String key) throws MonkeyException {
        return this.getProperty(key);
    }

    /***
     * Installs the Android application or test package contained in packageFile onto this device. If the application or test package is already installed, it is replaced.
     * @param path The fully-qualified path and filename of the .apk file to install.
     */
    public void installPackage(String path) throws MonkeyException{
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            device.installPackage(path);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Runs the specified component with Android instrumentation, and returns the results in a dictionary whose exact format is dictated by the component being run. The component must already be present on this device.
     * Use this method to start a test case that uses one of Android's test case classes. See Testing Fundamentals to learn more about unit testing with the Android testing framework.
     * @param className The name of an Android component that is already installed on this device, in the standard form packagename/classname, where packagename is the Android package name of a .apk file on this device, and classname is the class name of an Android component (Activity, ContentProvider, Service, or BroadcastReceiver) in that file. Both packagename and classname must be fully qualified. See ComponentName for more details.
     * @param args A dictionary containing flags and their values. These are passed to the component as it is started. If the flag does not take a value, set its dictionary value to an empty string.
     * @throws MonkeyException
     */
    public Map<String, Object> instrument(String className, Map<String, Object> args) throws MonkeyException{
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            return device.instrument(className, args);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Sends the key event specified by type to the key specified by keycode.
     * @param name The name of the keycode to send. See KeyEvent for a list of keycode names. Use the keycode name, not its integer value.
     * @param type The type of key event to send. The allowed values are DOWN, UP, and DOWN_AND_UP.
     * @throws MonkeyException
     */
    public void press(String name, TouchPressType type) throws MonkeyException{
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            device.press(name, type);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Sends the key event specified by type to the key specified by keycode.
     * @param button The name of the keycode to send. See KeyEvent for a list of keycode names. Use the keycode name, not its integer value.
     * @param type The type of key event to send. The allowed values are DOWN, UP, and DOWN_AND_UP.
     * @throws MonkeyException
     */
    public void press(PhysicalButton button, TouchPressType type) throws MonkeyException{
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            device.press(button, type);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Reboots this device into the bootloader specified by bootloadType.
     * @param bootloadType The type of bootloader to reboot into. The allowed values are "bootloader", "recovery", or "None".
     * @throws MonkeyException
     */
    public void reboot(String bootloadType) throws MonkeyException {
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            device.reboot(bootloadType);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Deletes the specified package from this device, including its data and cache.
     * @param packge The Android package name of an .apk file on this device.
     * @throws MonkeyException
     */
    public void removePackage(String packge) throws MonkeyException {
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            device.removePackage(packge);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Executes an adb shell command and returns the result, if any.
     * @param cmd The command to execute in the adb shell. The form of these commands is described in the topic Android Debug Bridge.
     * @return The results of the command, if any. The format of the results is determined by the command.
     * @throws MonkeyException
     */
    public String shell(String cmd) throws MonkeyException {
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            return device.shell(cmd);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Executes an adb shell command and returns the result, if any.
     * @param cmd The command to execute in the adb shell. The form of these commands is described in the topic Android Debug Bridge.
     * @param options
     * @return The results of the command, if any. The format of the results is determined by the command.
     * @throws MonkeyException
     */
    public String shell(String cmd, int options) throws MonkeyException {
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            return device.shell(cmd, options);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Starts an Activity on this device by sending an Intent constructed from the supplied arguments.
     * @param uri The URI for the Intent. (see Intent.setData()).
     * @param action The action for the Intent (see Intent.setAction()).
     * @param data The data URI for the Intent (see Intent.setData()).
     * @param mimetype The MIME type for the Intent (see Intent.setType()).
     * @param categories An iterable data structure containing strings that define categories for the Intent (see Intent.addCategory()).
     * @param extras A dictionary of extra data for the Intent (see Intent.putExtra() for an example). The key for each dictionary item should be a string. The item's value can be any simple or structured data type.
     * @param component	The component for the Intent (see ComponentName). Using this argument will direct the Intent to a specific class within a specific Android package.
     * @param flags An iterable data structure containing flags that control how the Intent is handled (see Intent.setFlags()).
     * @throws MonkeyException
     */
    public void startActivity(@com.android.annotations.Nullable String uri,
                              @com.android.annotations.Nullable String action,
                              @com.android.annotations.Nullable String data,
                              @com.android.annotations.Nullable String mimetype,
                              java.util.Collection<String> categories,
                              Map<String,Object> extras,
                              @com.android.annotations.Nullable String component,
                              int flags) throws MonkeyException {
        if (!isDeviceValid(this.device)) {
            throw new MonkeyException("Device is not valid");
        }
        try {
            device.startActivity(uri, action, data, mimetype, categories, extras, component, flags);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Captures the entire screen buffer of this device, yielding a screen capture of the current display.
     * @return A MonkeyImage object containing the image of the current display.
     */
    public MonkeyImage takeSnapshot() throws MonkeyException{
        try {
            isDeviceValid(this.device);
            return new MonkeyImage(device.takeSnapshot());
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Sends a touch event specified by type to the screen location specified by x and y.
     * @param x The horizontal position of the touch in actual device pixels, starting from the left of the screen in its current orientation.
     * @param y The vertical position of the touch in actual device pixels, starting from the top of the screen in its current orientation.
     * @param type The type of key event to send. The allowed values are DOWN, UP, and DOWN_AND_UP.
     * @throws MonkeyException
     */
    public void touch(int x, int y, TouchPressType type) throws MonkeyException {
        try {
            isDeviceValid(this.device);
            device.touch(x, y, type);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Sends the characters contained in message to this device, as if they had been typed on the device's keyboard. This is equivalent to calling press() for each keycode in message using the key event type DOWN_AND_UP.
     * @param message A string containing the characters to send.
     * @throws MonkeyException
     */
    public void type(String message) throws MonkeyException {
        try {
            isDeviceValid(this.device);
            device.type(message);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Wakes the screen of this device.
     * @throws MonkeyException
     */
    public void wake() throws MonkeyException{
        try {
            isDeviceValid(this.device);
            device.wake();
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    private boolean isDeviceValid(IChimpDevice device) throws MonkeyException {
        try {
            if (device == null) {
                return false;
            }
            if (device.getProperty("display.width") == null) {
                return false;
            }
            return true;
        }
        catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }
}
