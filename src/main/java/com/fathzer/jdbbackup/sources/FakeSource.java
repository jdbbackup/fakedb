package com.fathzer.jdbbackup.sources;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.function.Function;

import com.fathzer.jdbbackup.SourceManager;
import com.fathzer.jdbbackup.utils.BasicExtensionBuilder;

/**
 * A fake <a href="https://github.com/jdbbackup/jdbbackup-core">JDBBackupp</a> SourceManager implementation that can be used for tests.
 * <br>
 * <br>Source format: fake://<i>content</i>
 * <br>It generates a file that contains a short text that ends with <i>content</i>.
 * <br>
 * <br>Special <i>content</i> values:<ul>
 * <li>IO: The save method throws an IOException</li>
 * <li>Illegal: The save method throws an IllegalArgumentException</li>
 * <li>Err: The save method throws a RuntimeException that is not an IllegalArgumentException</li>
 * </ul>
 */
public class FakeSource implements SourceManager {

	@Override
	public String getScheme() {
		return "fake";
	}

	@Override
	public void save(String source, File destFile) throws IOException {
		if ("IO".equals(source)) {
			throw new IOException("IOException requested");
		} else if ("Illegal".equals(source)) {
			throw new IllegalArgumentException("IllegalArgumentException requested");
		} else if ("Err".equals(source)) {
			throw new IllegalStateException("Other exception requested");
		}
		Files.writeString(destFile.toPath(), "This is a fake source dump for "+source, StandardCharsets.UTF_8); 
	}

	@Override
	public Function<String, CharSequence> getExtensionBuilder() {
		return new BasicExtensionBuilder("txt");
	}
}
