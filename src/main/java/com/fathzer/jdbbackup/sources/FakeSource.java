package com.fathzer.jdbbackup.sources;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.function.Function;

import com.fathzer.jdbbackup.SourceManager;
import com.fathzer.jdbbackup.utils.BasicExtensionBuilder;

public class FakeSource implements SourceManager {

	@Override
	public String getScheme() {
		return "fake";
	}

	@Override
	public void save(String source, File destFile) throws IOException {
		Files.writeString(destFile.toPath(), "This is a fake DB Dump for "+source, StandardCharsets.UTF_8); 
	}

	@Override
	public Function<String, CharSequence> getExtensionBuilder() {
		return new BasicExtensionBuilder("txt");
	}
}
