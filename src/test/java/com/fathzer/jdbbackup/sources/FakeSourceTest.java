package com.fathzer.jdbbackup.sources;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.fathzer.jdbbackup.SourceManager;
import com.fathzer.plugin.loader.classloader.ClassLoaderPluginLoader;

class FakeSourceTest {
	@TempDir Path tempDir;
	
	@Test
	void test() throws IOException {
		final Map<String, SourceManager> registry = new HashMap<>();
		new ClassLoaderPluginLoader().getPlugins(SourceManager.class).forEach(p -> registry.put(p.getScheme(), p));

		final SourceManager m = registry.get("fake");
		assertNotNull(m);
		
	    final File tempFile = Files.createFile(tempDir.resolve("testfile.txt")).toFile();
		assertThrows(IOException.class, ()->m.save("IO", tempFile));
		assertThrows(IllegalArgumentException.class, ()->m.save("Illegal", tempFile));
		try {
			m.save("Err", tempFile);
		} catch (RuntimeException e) {
			assertNotEquals(e.getClass(), IllegalArgumentException.class);
		}
		
		m.save("test", tempFile);
		assertTrue(Files.readString(tempFile.toPath()).endsWith("test"));
		
		assertEquals("toto.txt", m.getExtensionBuilder().apply("toto"));
	}

}
