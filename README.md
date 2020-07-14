[![Build Status](https://travis-ci.com/VolkerHartmann/ocrd_metadata.svg?branch=master)](https://travis-ci.com/VolkerHartmann/ocrd_metadata)
[![codecov](https://codecov.io/gh/VolkerHartmann/ocrd_metadata/branch/master/graph/badge.svg)](https://codecov.io/gh/VolkerHartmann/ocrd_metadata)
# OCR-D Metadata Extractor

This java library contains utility classes to extract all metadata of OCR-D workspaces.

## Prerequisites

In order to build this library you'll need:

* Java SE Development Kit 8 or higher

## Building library
Build the library and make it accessible for other projects.
```bash=bash
# Build library
user@localhost:/home/user/ocrd_metadata/$./gradlew -Prelease clean build
BUILD SUCCESSFUL in 5s
7 actionable tasks: 7 executed
```
As a result, a jar containing all the utility classes is created at 'build/libs/ocrd-metadata-0.4.0.jar'.


## More Information

* [OCR-D](https://ocr-d.github.io/)

## License

The library is licensed under the Apache License, Version 2.0.
