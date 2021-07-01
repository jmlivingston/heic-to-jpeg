# heic-to-jpeg

This project provides an example in Java for converting [HEIC](https://en.wikipedia.org/wiki/High_Efficiency_Image_File_Format) files to JPEG using [ImageMagick](https://imagemagick.org/) or [Grahics Magick](http://www.graphicsmagick.org/), a fork of ImageMagick. There are two examples using `im4java` and `jmagick` which both use `ImageMagick` or `GraphicsMagick` under the hood. The `jmagick` code does not work, but may just need some tweaking. This has only been tested on a Mac Mini (M1).

## Installation

- Ensure that ImageMagick is installed locally.

## Running with VS Code

- Ensure you have Java installed using the following instructions: [Java in Visual Studio Code](https://code.visualstudio.com/docs/languages/java).
- Do one of the following:
    - Open up `demo/src/main/java/com/example` and click on the `Run` triangle in the top right.
    - Click the `Run and Debug`, pick from the dropdown, then click the `Run` triangle.

## Misc

CLIENT
https://github.com/alexcorvi/heic2any (no good with large files)

https://www.npmjs.com/package/libheif-js


--------------------------------------------------------------------------------

NODE
https://github.com/nokiatech/heif/wiki
Home · nokiatech/heif Wiki (github.com)

https://www.npmjs.com/package/heic-convert
heic-convert (no good with large files)

node-imagemagick (uses imagemagick)
https://github.com/yourdeveloper/node-imagemagick

https://github.com/monostream/tifig
monostream/tifig: A fast HEIF image converter aimed at thumbnailing (github.com)

https://www.npmjs.com/package/heic-decode
heic-decode (no good with large images)

https://www.npmjs.com/package/libheif-js

https://www.npmjs.com/package/jpeg-js

https://github.com/saschazar21/webassembly/tree/main/packages/heif

--------------------------------------------------------------------------------

CLOUD
https://cloudmersive.com/

--------------------------------------------------------------------------------

PYTHON
https://docs.wand-py.org
uses ImageMagick
https://www.geeksforgeeks.org/wand-python-introduction-and-installation/

https://github.com/hhatto/pgmagick - uses GraphicsMagick

--------------------------------------------------------------------------------

Google WebP
https://developers.google.com/speed/webp

--------------------------------------------------------------------------------

C++
https://imagemagick.org/index.php
http://www.graphicsmagick.org/
https://github.com/strukturag/libheif
https://github.com/monostream/tifig

--------------------------------------------------------------------------------

Java
https://github.com/yanzhonghui/HEIC-Convert-Java
https://github.com/techblue/jmagick
https://blog.groupdocs.com/2021/05/10/xmp-and-exif-data-of-heif-heic-images-using-java/
https://thinktibits.blogspot.com/2012/12/convert-png-to-jpeg-using-jmagick-java.html
https://jaibeermalik.wordpress.com/2012/10/16/image-manipulation-using-imagemagick-from-java/
https://github.com/cherryleafroad/Android-ImageMagick7/issues/2
https://github.com/cherryleafroad/Android-ImageMagick7
https://support.idrsolutions.com/jdeli/faqs/can-jdeli-directly-convert-between-image-formats
https://github.com/nokiatech/heif

--------------------------------------------------------------------------------

CLI
https://github.com/robertsicoie/wateresize
relies on ImageMagick


AWS
Deploy Python with Container - https://docs.aws.amazon.com/lambda/latest/dg/python-image.html

