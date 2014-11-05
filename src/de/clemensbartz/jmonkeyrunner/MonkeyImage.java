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

import de.clemensbartz.chattychimpchat.core.IChimpImage;

import java.awt.image.BufferedImage;

/**
 * Created by clemens on 01.11.14.
 */
public class MonkeyImage {
    IChimpImage image;
    protected MonkeyImage(IChimpImage image) {
        this.image = image;
    }

    /**
     * Converts the current image to a particular format and returns it as a string that you can then access as an iterable of binary bytes.
     * @param format The desired output format. All of the common raster output formats are supported. The default value is "png" (Portable Network Graphics).
     * @return
     * @throws MonkeyException
     */
    public byte[] convertToBytes(String format) throws MonkeyException{
        try {
            isImageValid(this.image);
            return image.convertToBytes(format);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Returns the single pixel at the image location (x,y), as an an integer. Use this method to economize on memory.
     * @param x The horizontal position of the pixel, starting with 0 at the left of the screen in the orientation it had when the screenshot was taken.
     * @param y The vertical position of the pixel, starting with 0 at the top of the screen in the orientation it had when the screenshot was taken.
     * @return The a,r,g, and b values of the pixel as 8-bit values combined into a 32-bit integer, with a as the leftmost 8 bits, r the next rightmost, and so forth.
     * @throws MonkeyException
     */
    public int getRawPixelInt(int x, int y) throws MonkeyException {
        try {
            isImageValid(this.image);
            return image.getPixel(x, y);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Returns the single pixel at the image location (x,y), as an an integer. Use this method to economize on memory.
     * @param x The horizontal position of the pixel, starting with 0 at the left of the screen in the orientation it had when the screenshot was taken.
     * @param y The vertical position of the pixel, starting with 0 at the top of the screen in the orientation it had when the screenshot was taken.
     * @return The a,r,g, and b values of the pixel as 8-bit values combined into a 32-bit integer, with a as the leftmost 8 bits, r the next rightmost, and so forth.
     * @throws MonkeyException
     */
    public MonkeyARGB getRawPixel(int x, int y) throws MonkeyException {
        int argb = this.getRawPixelInt(x, y);
        int a = (argb >> 32) & 0xFF;
        int r = (argb >> 16) & 0xFF;
        int g = (argb >> 8) & 0xFF;
        int b = argb & 0xFF;
        return new MonkeyARGB(a, r, g, b);
    }

    /**
     * Creates a new MonkeyImage object from a rectangular selection of the current image.
     * A tuple (x, y, w, h) specifying the selection. x and y specify the 0-based pixel position of the upper left-hand corner of the selection. w specifies the width of the region, and h specifies its height, both in units of pixels.
     * The image's orientation is the same as the screen orientation at the time the screenshot was made.
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     * @throws MonkeyException
     */
    public MonkeyImage getSubImage(int x, int y, int width, int height) throws MonkeyException {
        try {
            isImageValid(this.image);
            return new MonkeyImage(image.getSubImage(x, y, width, height));
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }


    /**
     * Compares this MonkeyImage object to another and returns the result of the comparison. The percent argument specifies the percentage difference that is allowed for the two images to be "equal".
     * @param other Another MonkeyImage object to compare to this one.
     * @param percent A float in the range 0.0 to 1.0, inclusive, indicating the percentage of pixels that need to be the same for the method to return true. The default is 1.0, indicating that all the pixels must match.
     * @return Boolean true if the images match, or boolean false otherwise.
     * @throws MonkeyException
     */
    public boolean sameAs(MonkeyImage other, double percent) throws MonkeyException {
        // Source code is copied from:
        // https://github.com/miracle2k/android-platform_sdk/blob/master/monkeyrunner/src/com/android/monkeyrunner/MonkeyImage.java
        try {
            isImageValid(this.image);
            BufferedImage otherImage = other.getBufferedImage();
            BufferedImage myImage = image.getBufferedImage();
            // Easy size check
            if (otherImage.getWidth() != myImage.getWidth()) {
                return false;
            }
            if (otherImage.getHeight() != myImage.getHeight()) {
                return false;
            }
            int width = myImage.getWidth();
            int height = myImage.getHeight();
            int numDiffPixels = 0;
            // Now, go through pixel-by-pixel and check that the images are the same;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (myImage.getRGB(x, y) != otherImage.getRGB(x, y)) {
                        numDiffPixels++;
                    }
                }
            }
            double numberPixels = (height * width);
            double diffPercent = numDiffPixels / numberPixels;
            return percent <= 1.0 - diffPercent;
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Writes the current image to the file specified by filename, in the format specified by format.
     * @param filename The fully-qualified filename and extension of the output file.
     * @param format The output format to use for the file. If no format is provided, then the method tries to guess the format from the filename's extension. If no extension is provided and no format is specified, then the default format of "png" (Portable Network Graphics) is used.
     * @throws MonkeyException
     */
    public void writeToFile(String filename, String format) throws MonkeyException{
        try {
            isImageValid(this.image);
            image.writeToFile(filename, format);
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    /**
     * Returns the image as a BufferedImage
     * @return A BufferedImage
     * @throws MonkeyException
     */
    public BufferedImage getBufferedImage() throws MonkeyException {
        try {
            isImageValid(this.image);
            return image.getBufferedImage();
        } catch (Exception e) {
            throw new MonkeyException(e.getMessage());
        }
    }

    private boolean isImageValid(IChimpImage image) {
        if (image == null) {
            return false;
        }
        return true;
    }
}
