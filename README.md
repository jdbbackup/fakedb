![Maven Central](https://img.shields.io/maven-central/v/com.fathzer/jdbbackup-fakesource)
![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jdbbackup_jdbbackup-fakesource&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jdbbackup_jdbbackup-fakesource)
[![javadoc](https://javadoc.io/badge2/com.fathzer/jdbbackup-fakesource/javadoc.svg)](https://javadoc.io/doc/com.fathzer/jdbbackup-fakesource)

# jdbbackup-fakesource
A fake [JDBBackupp](https://github.com/jdbbackup/jdbbackup-core) SourceManager implementation that can be used for tests.

## Source format
fake://*content*

It generates a file that contains a short text that ends with *content*.

Special *content* values:
- IO: The save method throws an IOException
- Illegal: The save method throws an IllegalArgumentException
- Err: The save method throws a RuntimeException that is not an IllegalArgumentException